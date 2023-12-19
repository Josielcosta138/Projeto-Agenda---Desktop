package br.com.senac.dao;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ContatoVO;

public interface IContatoDao {
	
	public abstract ContatoVO buscarContatoPorId( ContatoVO contatVO)
			throws BOException;
		
		public abstract List<ContatoVO> listarContato(ContatoVO contatVO, 
													BigInteger id, 
													String descri, 
													Date datnas,
													String observ) throws BOException;
		
		public abstract void salvar(ContatoVO produtoVO) 
				throws BOValidationException, BOException;
		
		
		public abstract void excluir(ContatoVO produtoVO) 
				throws BOValidationException, BOException;

}
