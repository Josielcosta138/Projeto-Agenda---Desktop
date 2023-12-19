package br.com.senac.bo;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import br.com.senac.dao.ContatoDAO;
import br.com.senac.dao.IContatoDao;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;

public class ContatoBO implements IContatoBO {
	
	private IContatoDao contatoDao;
	
	public ContatoBO() {
		contatoDao = new ContatoDAO();
	}

	@Override
	public ContatoVO buscarContatoPorId(ContatoVO contatVO) throws BOException {
	
		if (contatVO == null || contatVO.getId() == null) {
			//GRAVAR EM ARQUIVO LOG.
			throw new BOException("Código identificador inválido.");
		}
		
		return contatoDao.buscarContatoPorId(contatVO);
	}

	@Override
	public List<ContatoVO> listarContato(ContatoVO contatVO, BigInteger id, String descri, Date datnas, String observ)
			throws BOException {
	
		if (contatVO == null || contatVO.getId() == null) {
			throw new BOException("Contato não pode ser nulo na " + "Consulta de contatos. ");
		}
	return contatoDao.listarContato(contatVO, id, descri, datnas, observ);
	}

	@Override
	public void salvar(ContatoVO contatoVO) throws BOValidationException, BOException {
		if (contatoVO == null) {
			throw new BOException("Contato nulo ou inválido.");
		}else if (contatoVO.getNome() == null) {
			throw new BOException("Descrição: Erro de validação: " + "descrição do contato nulo. ");
		}else if (contatoVO.getNome().length() < 2) {
			throw new BOValidationException("Descrição: Erro de validação: " + "a descrição do contato é muito curta.");
		}else if (contatoVO.getDatnas() == null) {
			throw new BOException("Data: Erro de validação: " + "data do contato nula. ");
		}else if (contatoVO.getObserv() == null) {
			throw new BOException("Observação: Erro de validação: " + "observação do contato nula. ");
		}
		
		contatoDao.salvar(contatoVO);

	}

	@Override
	public void excluir(ContatoVO contatoVO) throws BOValidationException, BOException {
		if (contatoVO == null || contatoVO.getId() == null) {
			throw new BOException("Produto nulo ou inválido." + "Impossivel de excluir.");
		}
		
		contatoDao.excluir(contatoVO);

	}

}
