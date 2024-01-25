package br.com.senac.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.senac.bo.ContelBO;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.ContelVO;
import br.com.senac.vo.EventoVO;
import br.com.senac.vo.ProdutoVO;
import br.com.senac.vo.TipoServicoVO;

public interface IService {

	
	// USUÁRIO - CLIENTE
	public abstract ContatoVO buscarContatosPorId(ContatoVO contatVO) throws BOException;

	public abstract List<ContatoVO> listarContato(ContatoVO contatVO, BigInteger id, String descri, Date datnas,
			String observ) throws BOException;

	public abstract void salvar(ContatoVO produtoVO) throws BOValidationException, BOException;

	public abstract void excluir(ContatoVO produtoVO) throws BOValidationException, BOException;

	
	
// CONTATO TELEFONICO
	public abstract ContelVO buscarContatosTelPorId(ContelVO contelVO) throws BOException;

	public abstract List<ContelVO> listarContatoTel(ContatoVO contat) throws BOException;

	public abstract void salvar(ContelVO contelVO) throws BOValidationException, BOException;

	public abstract void excluir(ContelVO contelVO) throws BOValidationException, BOException;
	
	
	
// AGENDAMENTO
	
	public abstract List<EventoVO> listarAgendamentos(EventoVO eventoVO) throws BOException;
	
	public abstract EventoVO buscarContatosPorId(EventoVO eventoVO) throws BOException;
	
	public abstract void salvar(EventoVO eventoVO) throws BOValidationException, BOException;

	public abstract void excluir(EventoVO eventoVO) throws BOValidationException, BOException;	
	
	public abstract void calcularTotal(EventoVO eventoVO) throws BOValidationException, BOException;	
	
	
// TIPOS DE SERVIÇOS
	
	public abstract TipoServicoVO buscarTipoServicoPorId(TipoServicoVO tipoServicoVO) throws BOException;

	public abstract List<TipoServicoVO> listarServicos(TipoServicoVO tipoServicoVO) throws BOException;

	public abstract void salvar(TipoServicoVO tipoServicoVO) throws BOValidationException, BOException;

	public abstract void excluir(TipoServicoVO tipoServicoVO) throws BOValidationException, BOException;
	
	
	
// TIPOS DE PRODUTOS
	
	public abstract ProdutoVO buscarProdutoPorId(ProdutoVO produtoVO) throws BOException;

	public abstract List<ProdutoVO> listarProduto(ProdutoVO produtoVO) throws BOException;

	public abstract void salvar(ProdutoVO produtoVO) throws BOValidationException, BOException;

	public abstract void excluir(ProdutoVO produtoVO) throws BOValidationException, BOException;
	
	
	
	
}
