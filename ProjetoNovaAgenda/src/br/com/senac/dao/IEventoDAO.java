package br.com.senac.dao;

import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.ContelVO;
import br.com.senac.vo.EventoVO;

public interface IEventoDAO {
	
	public abstract EventoVO buscarAgendamentoPorId(EventoVO eventoVO) throws BOException;

	public abstract List<EventoVO> listarAgendamentos(EventoVO eventoVO) throws BOException;

	public abstract void salvar(EventoVO eventoVO) throws BOValidationException, BOException;

	public abstract void excluir(EventoVO eventoVO) throws BOValidationException, BOException;

}
