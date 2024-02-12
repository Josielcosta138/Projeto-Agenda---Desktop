package br.com.senac.bo;

import java.util.List;

import br.com.senac.dao.IVendaDAO;
import br.com.senac.dao.VendaDAO;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.EventoVO;
import br.com.senac.vo.VendaVO;

public class VendaBO implements IVendaBO {
	
	
	
	private IVendaDAO vendaDAO;
	
	public VendaBO() {
		vendaDAO = new VendaDAO();
	}



	@Override
	public VendaVO buscarVendaPorId(VendaVO vendaVO) throws BOException {
		if (vendaVO == null || vendaVO.getId() == null) {
			//GRAVAR EM ARQUIVO LOG.
			throw new BOException("Código identificador inválido.");
		}
		
		return vendaDAO.buscarVendaPorId(vendaVO);
	}

	@Override
	public List<VendaVO> listarVendasBO(VendaVO vendaVO) throws BOException {
		if (vendaVO == null || vendaVO.getId() == null) {
			throw new BOException("Venda não pode ser nulo na " + "Consulta de vendas. ");
		}
	return vendaDAO.listarVendasDAO(vendaVO);
	}

	
	@Override
	public void salvar(VendaVO vendaVO) throws BOValidationException, BOException {
		
		if (vendaVO == null) {
			throw new BOException("Venda nulo ou inválido.");
		}else if (vendaVO.getNome() == null) {
			throw new BOException("Nome: Erro de validação: " + "Nome nulo. ");
		}else if (vendaVO.getNome().length() < 2) {
			throw new BOValidationException("Nome: Erro de validação: " + "o Nome é muito curta.");
		
		}
		
		vendaDAO.salvar(vendaVO);

	}

	@Override
	public void excluir(VendaVO vendaVO) throws BOValidationException, BOException {
		
		if (vendaVO == null || vendaVO.getId() == null) {
			throw new BOException("Venda nulo ou inválido." + "Impossivel de excluir.");
		}
		
		vendaDAO.excluir(vendaVO);

	}

	@Override
	public void calcularTotal(VendaVO vendaVO) throws BOValidationException, BOException {
		// TODO Auto-generated method stub

	}

}
