package br.com.senac.bo;

import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.ContelVO;

public interface IContelBO {

	public abstract ContelVO buscarContatoTelPorId(ContelVO contelVO) throws BOException;

	public abstract List<ContelVO> listarContatoTel(ContatoVO contat) throws BOException;

	public abstract void salvar(ContelVO contelVO) throws BOValidationException, BOException;

	public abstract void excluir(ContelVO contelVO) throws BOValidationException, BOException;
}
