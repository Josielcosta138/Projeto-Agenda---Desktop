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
import br.com.senac.vo.TipoServicoVO;

public class TipoServicoDAO implements ITipoServicoDAO {

	@Override
	public TipoServicoVO buscarTipoServicoPorId(TipoServicoVO tipoServicoVO) throws BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();
		TipoServicoVO tipoServicoVO2 = em.find(TipoServicoVO.class, tipoServicoVO.getId());
		System.out.println("Tipo de serviço: " + tipoServicoVO2.getId() + " - " + tipoServicoVO.getNome());

		em.close();
		return tipoServicoVO2;
	}

	@Override
	public List<TipoServicoVO> listarTiposServicos(TipoServicoVO tipoServicoVO) throws BOException {
		System.out.println("***** Começando listagem de tipos de serviço *****");
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TipoServicoVO> criteria = cb.createQuery(TipoServicoVO.class);

		// Clausula from
		Root<TipoServicoVO> tipoServicoFrom = criteria.from(TipoServicoVO.class);

		// Class é do pacote Java.persistente
		Predicate agendamentoTelWhere = cb.equal(tipoServicoFrom.get("tipoServicoVO"), tipoServicoVO);

		// Clausula OrderBy
		//Order telefoneOrderBy = cb.asc(agendamentoFrom.get("emails"));

		criteria.select(tipoServicoFrom);
		criteria.where(agendamentoTelWhere);
		//criteria.orderBy(telefoneOrderBy);

		TypedQuery<TipoServicoVO> query = em.createQuery(criteria);

		// List do pacote util
		List<TipoServicoVO> listaTiposDeServicos = query.getResultList();
		System.out.println("********************Encerrou!**********************");
		em.close();

		return listaTiposDeServicos;
		
	
	}

	@Override
	public void salvar(TipoServicoVO tipoServicoVO) throws BOValidationException, BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();

		try {

			em.getTransaction().begin();

			if (tipoServicoVO.getId() == null) {
				em.persist(tipoServicoVO);
			} else {
				em.merge(tipoServicoVO);
			}
			em.getTransaction().commit();
			System.out.println("Serviço inserido com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new BOException(e);
		}

	}

	@Override
	public void excluir(TipoServicoVO tipoServicoVO) throws BOValidationException, BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			TipoServicoVO agendamento = em.find(TipoServicoVO.class, tipoServicoVO.getId());
			em.remove(agendamento); // merge edicao
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
