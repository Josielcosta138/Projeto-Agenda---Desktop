package br.com.senac.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.EventoVO;
import br.com.senac.vo.VendaVO;

public class VendaDAO implements IVendaDAO {

	@Override
	public VendaVO buscarVendaPorId(VendaVO vendaVO) throws BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();
		VendaVO vendaVO2 = em.find(VendaVO.class, vendaVO.getId());
		System.out.println("Venda: " + vendaVO.getId());

		em.close();
		return vendaVO2;
		
	}

	@Override
	public List<VendaVO> listarVendasDAO(VendaVO vendaVO) throws BOException {
		System.out.println("***** Começando listagem de vendas *****");
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<VendaVO> criteria = cb.createQuery(VendaVO.class);

		// Clausula from
		Root<VendaVO> vendasFrom = criteria.from(VendaVO.class);

		// Class é do pacote Java.persistente
		Predicate venasWhere = cb.equal(vendasFrom.get("eventoVO"), vendaVO);

		// Clausula OrderBy
		//Order telefoneOrderBy = cb.asc(agendamentoFrom.get("emails"));

		criteria.select(vendasFrom);
		criteria.where(venasWhere);
		//criteria.orderBy(telefoneOrderBy);

		TypedQuery<VendaVO> query = em.createQuery(criteria);

		// List do pacote util
		List<VendaVO> listaDevendas = query.getResultList();
		System.out.println("********************Encerrou!**********************");
		em.close();

		return listaDevendas;
	}

	@Override
	public void salvar(VendaVO vendaVO) throws BOValidationException, BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();

		try {

			em.getTransaction().begin();

			if (vendaVO.getId() == null) {
				em.persist(vendaVO);
			} else {
				em.merge(vendaVO);
			}
			em.getTransaction().commit();
			System.out.println("Venda inserida com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new BOException(e);
		}

	}

	@Override
	public void excluir(VendaVO vendaVO) throws BOValidationException, BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			VendaVO vendas = em.find(VendaVO.class, vendaVO.getId());
			em.remove(vendas); // merge edicao
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
