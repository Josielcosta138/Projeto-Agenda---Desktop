package Relatorio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfWriter;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.vo.ProdutoVO;




public class RelatorioTeste {
	
	private List<ProdutoVO> listagemProdutoParaRelatorio;
	
		
	  public void gerarRelatorio() throws IOException {
		  System.out.println("******* Iniciando liestagem de produtos *******");
			EntityManager em = HibernateUtil.getEntityManager();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

			// Clausula from
			Root<ProdutoVO> produtosFrom = criteria.from(ProdutoVO.class);
			criteria.select(produtosFrom);
			
			TypedQuery<ProdutoVO> query = em.createQuery(criteria);
			listagemProdutoParaRelatorio = query.getResultList();
			
			List<String> strings1 = new ArrayList<>();

			for (ProdutoVO produto : listagemProdutoParaRelatorio) {
			    String string = produto.getId() + ", " + produto.getIndentificacao() + ", " + produto.getNome() + ", " +
			                    produto.getMarca() + ", " + produto.getDescricao() + ", " + produto.getPreco() + ", " +
			                    produto.getQuantidadeEstoque() + ", " + produto.getDataValidade() + ", " + produto.getStatusVenda();
			    strings1.add(string);
			}
			
			
			//TXT
			String caminho = "C:\\Users\\josie\\OneDrive\\Área de Trabalho\\relatorio\\arquivoTeste.txt";
			List<String> conteudo = strings1;
			FileWriter escritor = new FileWriter(caminho);
			for (String linha : conteudo) {
			    escritor.write(linha + "\n");
			}
			escritor.close();
			System.out.println("Inseriu arquivo " + strings1);
			
			// PDF
			Document documentPDF = new Document();
			
			try {
				
				PdfWriter.getInstance(documentPDF, new FileOutputStream("C:\\Users\\josie\\OneDrive\\Área de Trabalho\\relatorio\\arquivoTestePdf.pdf"));
				
				documentPDF.open();
				
				documentPDF.setPageSize(PageSize.A4);
				
				documentPDF.add(new Paragraph("GERANDO PDF COM PRODUTOS"));
				
				//documentPDF.newPage();
				
				//Image imagem = Image.getInstance("caminho da imagem."); 
				//imagem.scaleToFit(200, 200);
				//documentPDF.add(imagem);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				documentPDF.close();
			}

	            System.out.println("O arquivo PDF foi criado com sucesso!");

			
			
	    }
	}

