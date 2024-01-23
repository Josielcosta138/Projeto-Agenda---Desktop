package br.com.senac.bo;

import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ProdutoVO;
import br.com.senac.vo.TipoServicoVO;

public interface IProdutoBO {
	public abstract ProdutoVO buscarTipoServicoPorId(ProdutoVO produtoVO) throws BOException;

	public abstract List<ProdutoVO> listarServicos(ProdutoVO produtoVO) throws BOException;

	public abstract void salvar(ProdutoVO produtoVO) throws BOValidationException, BOException;

	public abstract void excluir(ProdutoVO produtoVO) throws BOValidationException, BOException;

}
