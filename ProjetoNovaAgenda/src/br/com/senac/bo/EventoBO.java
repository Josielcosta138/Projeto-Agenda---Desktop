package br.com.senac.bo;

import java.util.List;

import br.com.senac.dao.ContelDAO;
import br.com.senac.dao.EventoDAO;
import br.com.senac.dao.IEventoDAO;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.EventoVO;

public class EventoBO implements IEventoBO {
	
	private IEventoDAO eventoDAO;
	
	public EventoBO() {
		eventoDAO = new EventoDAO();
	}
	

	@Override
	public EventoVO buscarContatoPorId(EventoVO eventoVO) throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventoVO> listarContato(EventoVO eventoVO) throws BOException {
		if (eventoVO == null || eventoVO.getId() == null) {
			throw new BOException("Contato não pode ser nulo na " + "Consulta de contatos. ");
		}
	return eventoDAO.listarAgendamentos(eventoVO);
	}

	@Override
	public void salvar(EventoVO eventoVO) throws BOValidationException, BOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(EventoVO eventoVO) throws BOValidationException, BOException {
		// TODO Auto-generated method stub

	}

}
