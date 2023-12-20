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
		
		if (eventoVO == null || eventoVO.getId() == null) {
			//GRAVAR EM ARQUIVO LOG.
			throw new BOException("Código identificador inválido.");
		}
		
		return eventoDAO.buscarAgendamentoPorId(eventoVO);
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
		if (eventoVO == null) {
			throw new BOException("Contato nulo ou inválido.");
		}else if (eventoVO.getNomeCliente() == null) {
			throw new BOException("Descrição: Erro de validação: " + "descrição do contato nulo. ");
		}else if (eventoVO.getNomeCliente().length() < 2) {
			throw new BOValidationException("Descrição: Erro de validação: " + "a descrição do contato é muito curta.");
		}else if (eventoVO.getDataHoraInicio() == null) {
			throw new BOException("Data: Erro de validação: " + "data do contato nula. ");
		}
		
		eventoDAO.salvar(eventoVO);
		
	}

	@Override
	public void excluir(EventoVO eventoVO) throws BOValidationException, BOException {
		if (eventoVO == null || eventoVO.getId() == null) {
			throw new BOException("Produto nulo ou inválido." + "Impossivel de excluir.");
		}
		
		eventoDAO.excluir(eventoVO);

	}

}
