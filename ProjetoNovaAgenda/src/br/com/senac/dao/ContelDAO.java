package br.com.senac.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.ContelVO;

public class ContelDAO implements IContelDAO {

	@Override
	public ContelVO buscarContatoTelefonePorId(ContelVO contelVO) throws BOException {

		EntityManager em = HibernateUtil.getEntityManager();
		ContelVO contatoTel = em.find(ContelVO.class, contelVO.getId());
		System.out.println("Contato Telefonico: " + contatoTel.getId() + " - " + contatoTel.getNumero());

		em.close();
		return contatoTel;
	}

	@Override
	public List<ContelVO> listarContatoTel(ContatoVO contat) throws BOException {

		System.out.println("***** Começando listagem *****");
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ContelVO> criteria = cb.createQuery(ContelVO.class);

		// Clausula from
		Root<ContelVO> contatoTelFrom = criteria.from(ContelVO.class);

		// Class é do pacote Java.persistente
		Predicate contatoTelWhere = cb.equal(contatoTelFrom.get("contat"), contat);

		// Clausula OrderBy
		Order telefoneOrderBy = cb.asc(contatoTelFrom.get("emails"));

		criteria.select(contatoTelFrom);
		criteria.where(contatoTelWhere);
		criteria.orderBy(telefoneOrderBy);

		TypedQuery<ContelVO> query = em.createQuery(criteria);

		// List do pacote util
		List<ContelVO> listaContatosTel = query.getResultList();
		System.out.println("********************Encerrou!**********************");
		em.close();

		return listaContatosTel;
	}

	@Override
	public void salvar(ContelVO contelVO) throws BOValidationException, BOException {

		EntityManager em = HibernateUtil.getEntityManager();

		try {

			em.getTransaction().begin();

			if (contelVO.getId() == null) {
				em.persist(contelVO);
			} else {
				em.merge(contelVO);
			}
			em.getTransaction().commit();
			System.out.println("Contato Telefonico inserido com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new BOException(e);
		}

	}

	@Override
	public void excluir(ContelVO contelVO) throws BOValidationException, BOException {

		EntityManager em = HibernateUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			ContelVO contatoTel = em.find(ContelVO.class, contelVO.getId());
			em.remove(contatoTel); // merge edicao
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
