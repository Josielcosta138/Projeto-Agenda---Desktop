package br.com.senac.dao;

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
import br.com.senac.vo.ContelVO;
import br.com.senac.vo.EventoVO;

public class EventoDAO implements IEventoDAO {

	@Override
	public EventoVO buscarAgendamentoPorId(EventoVO eventoVO) throws BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();
		EventoVO eventoVO2 = em.find(EventoVO.class, eventoVO.getId());
		System.out.println("Contato Telefonico: " + eventoVO2.getId() + " - " + eventoVO2.getStatus());

		em.close();
		return eventoVO2;
	}

	@Override
	public List<EventoVO> listarAgendamentos(EventoVO eventoVO) throws BOException {
	
		System.out.println("***** Começando listagem *****");
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EventoVO> criteria = cb.createQuery(EventoVO.class);

		// Clausula from
		Root<EventoVO> agendamentoFrom = criteria.from(EventoVO.class);

		// Class é do pacote Java.persistente
		Predicate agendamentoTelWhere = cb.equal(agendamentoFrom.get("eventoVO"), eventoVO);

		// Clausula OrderBy
		//Order telefoneOrderBy = cb.asc(agendamentoFrom.get("emails"));

		criteria.select(agendamentoFrom);
		criteria.where(agendamentoTelWhere);
		//criteria.orderBy(telefoneOrderBy);

		TypedQuery<EventoVO> query = em.createQuery(criteria);

		// List do pacote util
		List<EventoVO> listaContatosTel = query.getResultList();
		System.out.println("********************Encerrou!**********************");
		em.close();

		return listaContatosTel;
	}

	@Override
	public void salvar(EventoVO eventoVO) throws BOValidationException, BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();

		try {

			em.getTransaction().begin();

			if (eventoVO.getId() == null) {
				em.persist(eventoVO);
			} else {
				em.merge(eventoVO);
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
	public void excluir(EventoVO eventoVO) throws BOValidationException, BOException {
		
	}

}
