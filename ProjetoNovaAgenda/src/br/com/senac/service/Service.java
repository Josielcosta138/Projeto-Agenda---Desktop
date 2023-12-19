package br.com.senac.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import br.com.senac.bo.ContatoBO;
import br.com.senac.bo.ContelBO;
import br.com.senac.bo.EventoBO;
import br.com.senac.bo.IContatoBO;
import br.com.senac.bo.IContelBO;
import br.com.senac.bo.IEventoBO;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.ContelVO;
import br.com.senac.vo.EventoVO;

public class Service implements IService {

	@Override
	public ContatoVO buscarContatosPorId(ContatoVO contatVO) throws BOException {
		IContatoBO contatoBO = new ContatoBO();
		return contatoBO.buscarContatoPorId(contatVO);
	}

	@Override
	public List<ContatoVO> listarContato(ContatoVO contatVO, BigInteger id, String descri, Date datnas, String observ)
			throws BOException {
		IContatoBO contatoBO = new ContatoBO();
		return contatoBO.listarContato(contatVO, id, descri, datnas, observ);
	}

	@Override
	public void salvar(ContatoVO contatoVO) throws BOValidationException, BOException {
		IContatoBO contatoBO = new ContatoBO();
		contatoBO.salvar(contatoVO);

	}

	@Override
	public void excluir(ContatoVO contatoVO) throws BOValidationException, BOException {
		IContatoBO contatoBO = new ContatoBO();
		contatoBO.excluir(contatoVO);
	}

	// CONTATO TELEFONICO
	@Override
	public ContelVO buscarContatosTelPorId(ContelVO contelVO) throws BOException {
		IContelBO contatoTelBO = new ContelBO();
		return contatoTelBO.buscarContatoTelPorId(contelVO);

	}

	@Override
	public List<ContelVO> listarContatoTel(ContatoVO contat) throws BOException {
		IContelBO contatoTelBO = new ContelBO();
		return contatoTelBO.listarContatoTel(contat);
	}

	@Override
	public void salvar(ContelVO contelVO) throws BOValidationException, BOException {

		IContelBO contatoTelBO = new ContelBO();
		contatoTelBO.salvar(contelVO);

	}

	@Override
	public void excluir(ContelVO contelVO) throws BOValidationException, BOException {

		IContelBO contatoTelBO = new ContelBO();
		contatoTelBO.excluir(contelVO);

	}

	@Override
	public List<EventoVO> listarAgendamentos(EventoVO eventoVO) throws BOException {
		
		IEventoBO eventoBO = new EventoBO();
		return eventoBO.listarContato(eventoVO);
	}

}
