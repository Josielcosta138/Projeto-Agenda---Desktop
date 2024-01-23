package br.com.senac.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.ProdutoVO;
import br.com.senac.vo.TipoServicoVO;

public class ProdutoDAO implements IProdutoDAO {

	@Override
	public ProdutoVO buscarProdutoPorId(ProdutoVO produtoVO) throws BOException {
	
		EntityManager em = HibernateUtil.getEntityManager();
		ProdutoVO produtoVOBuscaPorId = em.find(ProdutoVO.class, produtoVO.getId());
		System.out.println("Tipo de produto: " + produtoVOBuscaPorId.getId() + " - " + produtoVO.getNome());

		em.close();
		return produtoVOBuscaPorId;
	}

	@Override
	public List<ProdutoVO> listarProdutos(ProdutoVO produto) throws BOException {

		System.out.println("***** Começando listagem de tipos de produtos *****");
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

		// Clausula from
		Root<ProdutoVO> tipoProdutosFrom = criteria.from(ProdutoVO.class);

		// Class é do pacote Java.persistente
		Predicate agendamentoTelWhere = cb.equal(tipoProdutosFrom.get("tipoServicoVO"), produto);

		// Clausula OrderBy
		//Order telefoneOrderBy = cb.asc(agendamentoFrom.get("emails"));

		criteria.select(tipoProdutosFrom);
		criteria.where(agendamentoTelWhere);
		//criteria.orderBy(telefoneOrderBy);

		TypedQuery<ProdutoVO> query = em.createQuery(criteria);

		// List do pacote util
		List<ProdutoVO> listaTiposProdutos = query.getResultList();
		System.out.println("********************Encerrou!**********************");
		em.close();

		return listaTiposProdutos;
	}

	@Override
	public void salvar(ProdutoVO produtoVO) throws BOValidationException, BOException {
		EntityManager em = HibernateUtil.getEntityManager();

		try {

			em.getTransaction().begin();

			if (produtoVO.getId() == null) {
				em.persist(produtoVO);
			} else {
				em.merge(produtoVO);
			}
			em.getTransaction().commit();
			System.out.println("Produto inserido com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new BOException(e);
		}


	}

	@Override
	public void excluir(ProdutoVO produtoVO) throws BOValidationException, BOException {
		EntityManager em = HibernateUtil.getEntityManager();
 
		try {
			em.getTransaction().begin();
			ProdutoVO produto = em.find(ProdutoVO.class, produtoVO.getId());
			em.remove(produto); // merge edicao
			em.getTransaction().commit();
   
		} catch (Exception e) { 
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new BOException(e);

		} finally {
			em.close();
		}
		

	}

	

}
