package br.com.senac.dao;

import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.ContelVO;
import br.com.senac.vo.ProdutoVO;

public interface IProdutoDAO {

	public abstract ProdutoVO buscarProdutoPorId(ProdutoVO produtoVO) throws BOException;

	public abstract List<ProdutoVO> listarProdutos(ProdutoVO produto) throws BOException;

	public abstract void salvar(ProdutoVO produtoVO) throws BOValidationException, BOException;

	public abstract void excluir(ProdutoVO produtoVO) throws BOValidationException, BOException;
	
	
}
