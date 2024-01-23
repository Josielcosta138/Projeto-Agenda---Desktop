package br.com.senac.bo;

import java.math.BigDecimal;
import java.util.List;

import br.com.senac.dao.IProdutoDAO;
import br.com.senac.dao.ProdutoDAO;
import br.com.senac.dao.TipoServicoDAO;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ProdutoVO;

public class ProdutoBO implements IProdutoBO {
	
	
	private IProdutoDAO produtoDAO;
	
	public ProdutoBO() {
		produtoDAO = new ProdutoDAO();
	}
	
	

	@Override
	public ProdutoVO buscarTipoServicoPorId(ProdutoVO produtoVO) throws BOException {
		
		if (produtoVO == null || produtoVO.getId() == null) {
			//GRAVAR EM ARQUIVO LOG.
			throw new BOException("Código identificador inválido.");
		}
		return produtoDAO.buscarProdutoPorId(produtoVO);
	}

	@Override
	public List<ProdutoVO> listarServicos(ProdutoVO produtoVO) throws BOException {
	
		if (produtoVO == null || produtoVO.getId() == null) {
			throw new BOException("Produto não pode ser nulo na " + "Consulta de produtos. ");
		}
	return produtoDAO.listarProdutos(produtoVO);
	}

	@Override
	public void salvar(ProdutoVO produtoVO) throws BOValidationException, BOException {
		
		if (produtoVO == null) {
			throw new BOException("Tipo de produto nulo ou inválido.");
		}else if (produtoVO.getNome() == null) {
			throw new BOException("Descrição: Erro de validação: " + "descrição do produto nulo ou inválido. ");
		}else if (produtoVO.getNome().length() < 2) {
			throw new BOValidationException("Descrição: Erro de validação: " + "a descrição do produto é muito curta.");
		}else if (produtoVO.getPreco() == null) {
			throw new BOException("Valor: Erro de validação: " + "Preço do produto nula. ");
		}else if (produtoVO.getPreco().compareTo(BigDecimal.ZERO) == 0) {
			throw new BOException("Valor: Erro de validação: Preço do produto é 0. ");
		}
		produtoDAO.salvar(produtoVO);

	}

	@Override
	public void excluir(ProdutoVO produtoVO) throws BOValidationException, BOException {
		
		if (produtoVO == null || produtoVO.getId() == null) {
			throw new BOException("Serviço nulo ou inválido." + "Impossivel de excluir.");
		}
		
		produtoDAO.excluir(produtoVO);
	}

}
