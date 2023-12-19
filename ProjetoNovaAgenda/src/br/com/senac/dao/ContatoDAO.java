package br.com.senac.dao;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;

public class ContatoDAO implements IContatoDao {

	@Override
	public ContatoVO buscarContatoPorId(ContatoVO contatVO) throws BOException {
		EntityManager em = HibernateUtil.getEntityManager();
		ContatoVO contato = em.find(ContatoVO.class, contatVO.getId());
		System.out.println("Contato: " + contato.getId() + " - " + contato.getNome());

		em.close();
		return contato;
	}

	@Override
	public List<ContatoVO> listarContato(ContatoVO contatVO, BigInteger id, String descri, Date datnas, String observ)
			throws BOException {

		System.out.println("********** Começando listagem ************");
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ContatoVO> criteria = cb.createQuery(ContatoVO.class);

		// Clausula from
		Root<ContatoVO> contatoFrom = criteria.from(ContatoVO.class);

		// Class é do pacote Java.persistente
		Predicate contatoWhere = cb.equal(contatoFrom.get("client"), contatVO);

		if (id != null) {
			contatoWhere = cb.and(contatoWhere, cb.equal(contatoFrom.get("id"), id));
		}

		if (descri != null) {
			contatoWhere = cb.and(contatoWhere,
					cb.like(cb.lower(contatoFrom.get("descri")), "%" + descri.toLowerCase() + "%"));
		}

		// Clausula OrderBy
		Order produtoOrderBy = cb.asc(contatoFrom.get("descri"));

		criteria.select(contatoFrom);
		criteria.where(contatoWhere);
		criteria.orderBy(produtoOrderBy);

		TypedQuery<ContatoVO> query = em.createQuery(criteria);

		// List do pacote util
		List<ContatoVO> listaProdutos = query.getResultList();
		System.out.println("***** Encerrou! *****");
		em.close();

		return listaProdutos;
	}

	@Override
	public void salvar(ContatoVO contatoVO) throws BOValidationException, BOException {

		EntityManager em = HibernateUtil.getEntityManager();

		try {

			em.getTransaction().begin();

			if (contatoVO.getId() == null) {
				em.persist(contatoVO);
			} else {
				em.merge(contatoVO);
			}
			em.getTransaction().commit();
			System.out.println("Contato inserido com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new BOException(e);
		}

	}

	@Override
	public void excluir(ContatoVO contatoVO) throws BOValidationException, BOException {

		EntityManager em = HibernateUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			ContatoVO contato = em.find(ContatoVO.class, contatoVO.getId());
			em.remove(contato); // merge edicao
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new BOException(e);

		} finally {
			em.close();
		}

	}

}
