package br.com.senac.dao;

import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.TipoServicoVO;

public interface ITipoServicoDAO {
	public abstract TipoServicoVO buscarTipoServicoPorId(TipoServicoVO tipoServicoVO) throws BOException;

	public abstract List<TipoServicoVO> listarTiposServicos(TipoServicoVO tipoServicoVO) throws BOException;

	public abstract void salvar(TipoServicoVO tipoServicoVO) throws BOValidationException, BOException;

	public abstract void excluir(TipoServicoVO tipoServicoVO) throws BOValidationException, BOException;
}
