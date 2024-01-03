package br.com.senac.bo;

import java.math.BigDecimal;
import java.util.List;

import br.com.senac.dao.ITipoServicoDAO;
import br.com.senac.dao.TipoServicoDAO;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.TipoServicoVO;

public class TipoServicoBO implements ITipoServicoBO {
	
	private ITipoServicoDAO tipoServicoDAO;
	
	public TipoServicoBO() {
		tipoServicoDAO = new TipoServicoDAO();
	}

	@Override
	public TipoServicoVO buscarTipoServicoPorId(TipoServicoVO tipoServicoVO) throws BOException {
		
		if (tipoServicoVO == null || tipoServicoVO.getId() == null) {
			//GRAVAR EM ARQUIVO LOG.
			throw new BOException("Código identificador inválido.");
		}
		return tipoServicoDAO.buscarTipoServicoPorId(tipoServicoVO);
		
	}

	@Override
	public List<TipoServicoVO> listarServicos(TipoServicoVO tipoServicoVO) throws BOException {
		
		if (tipoServicoVO == null || tipoServicoVO.getId() == null) {
			throw new BOException("Contato não pode ser nulo na " + "Consulta de contatos. ");
		}
	return tipoServicoDAO.listarTiposServicos(tipoServicoVO);
	}

	@Override
	public void salvar(TipoServicoVO tipoServicoVO) throws BOValidationException, BOException {
		
		if (tipoServicoVO == null) {
			throw new BOException("Tipo de serviço nulo ou inválido.");
		}else if (tipoServicoVO.getNome() == null) {
			throw new BOException("Descrição: Erro de validação: " + "descrição Tipo de serviço nulo ou inválido. ");
		}else if (tipoServicoVO.getNome().length() < 2) {
			throw new BOValidationException("Descrição: Erro de validação: " + "a descrição do Tipo de serviço é muito curta.");
		}else if (tipoServicoVO.getValor() == null) {
			throw new BOException("Valor: Erro de validação: " + "Valor do Tipo de serviço nula. ");
		}else if (tipoServicoVO.getValor().compareTo(BigDecimal.ZERO) == 0) {
			throw new BOException("Valor: Erro de validação: Valor do Tipo de serviço é 0. ");
		}else if (tipoServicoVO.getDuracao() == 0) {
		throw new BOException("Duração: Erro de validação: " + "tempo do Tipo de serviço nula. ");
	}
		
		
		tipoServicoDAO.salvar(tipoServicoVO);
	}

	@Override
	public void excluir(TipoServicoVO tipoServicoVO) throws BOValidationException, BOException {
		if (tipoServicoVO == null || tipoServicoVO.getId() == null) {
			throw new BOException("Produto nulo ou inválido." + "Impossivel de excluir.");
		}
		
		tipoServicoDAO.excluir(tipoServicoVO);

	}

}
