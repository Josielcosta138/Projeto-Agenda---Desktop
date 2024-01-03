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
import br.com.senac.bo.ITipoServicoBO;
import br.com.senac.bo.TipoServicoBO;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.ContelVO;
import br.com.senac.vo.EventoVO;
import br.com.senac.vo.TipoServicoVO;

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
	
	
	
	// EVENTO

	@Override
	public List<EventoVO> listarAgendamentos(EventoVO eventoVO) throws BOException {
		
		IEventoBO eventoBO = new EventoBO();
		return eventoBO.listarContato(eventoVO);
	}

	@Override
	public EventoVO buscarContatosPorId(EventoVO eventoVO) throws BOException {
		IEventoBO eventoBO = new EventoBO();
		return eventoBO.buscarContatoPorId(eventoVO);
	}

	@Override
	public void salvar(EventoVO eventoVO) throws BOValidationException, BOException {
		IEventoBO eventoBO = new EventoBO();
		eventoBO.salvar(eventoVO);
		
	}

	@Override
	public void excluir(EventoVO eventoVO) throws BOValidationException, BOException {
		
		IEventoBO evento = new EventoBO();
		evento.excluir(eventoVO);
		
	}
	
	
	
	
	// SERVIÇOS

	@Override
	public TipoServicoVO buscarTipoServicoPorId(TipoServicoVO tipoServicoVO) throws BOException {
		ITipoServicoBO tipoServico = new TipoServicoBO();
		return tipoServico.buscarTipoServicoPorId(tipoServicoVO);
	
	}

	@Override
	public List<TipoServicoVO> listarServicos(TipoServicoVO tipoServicoVO) throws BOException {
		ITipoServicoBO tipoServico = new TipoServicoBO();
		return tipoServico.listarServicos(tipoServicoVO);
		
	}

	@Override
	public void salvar(TipoServicoVO tipoServicoVO) throws BOValidationException, BOException {
		ITipoServicoBO tipoServico = new TipoServicoBO();
		tipoServico.salvar(tipoServicoVO);
				
	}

	@Override
	public void excluir(TipoServicoVO tipoServicoVO) throws BOValidationException, BOException {
		ITipoServicoBO tipoServico = new TipoServicoBO();
		tipoServico.excluir(tipoServicoVO);
		
	}

}
