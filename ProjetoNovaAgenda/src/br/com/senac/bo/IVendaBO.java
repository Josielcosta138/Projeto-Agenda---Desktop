package br.com.senac.bo;

import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.EventoVO;
import br.com.senac.vo.VendaVO;

public interface IVendaBO {
	
	public abstract VendaVO buscarVendaPorId(VendaVO vendaVO) throws BOException;

	public abstract List<VendaVO> listarVendasBO(VendaVO vendaVO) throws BOException;

	public abstract void salvar(VendaVO vendaVO) throws BOValidationException, BOException;

	public abstract void excluir(VendaVO vendaVO) throws BOValidationException, BOException;
	
	public abstract void calcularTotal(VendaVO vendaVO) throws BOValidationException, BOException;
	

}
