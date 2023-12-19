package br.com.senac.bo;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.EventoVO;

public interface IEventoBO {
	
	public abstract EventoVO buscarContatoPorId(EventoVO eventoVO) throws BOException;

	public abstract List<EventoVO> listarContato(EventoVO eventoVO) throws BOException;

	public abstract void salvar(EventoVO eventoVO) throws BOValidationException, BOException;

	public abstract void excluir(EventoVO eventoVO) throws BOValidationException, BOException;
	

}
