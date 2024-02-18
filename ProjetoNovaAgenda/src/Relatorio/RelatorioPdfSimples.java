package Relatorio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.view.TelaProdutos;
import br.com.senac.vo.ProdutoVO;

//import com.itextpdf.text.*;

public class RelatorioPdfSimples implements IRelatorio {

	private Document documentPDF;
	private List<ProdutoVO> listagemProdutoParaRelatorio;
	private String caminhoRelatorio = "C:\\Users\\josie\\OneDrive\\Área de Trabalho\\relatorio\\arquivoTestePdf.pdf";
	List<String> strings1 = new ArrayList<>();
	TelaProdutos produtos = null;

	public void preparador() {

		documentPDF = new Document();
		try {
			PdfWriter.getInstance(documentPDF, new FileOutputStream(caminhoRelatorio));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void gerarCabecalho() throws IOException {

		Paragraph paragrafoTitulo = new Paragraph();
		paragrafoTitulo.setAlignment(Element.ALIGN_CENTER);
		paragrafoTitulo.add(new Chunk("RELATÓRIO DE PRODUTOS", new Font(Font.HELVETICA, 24)));

		LocalDateTime dataHoraAtual = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String fataFormatada = dataHoraAtual.format(formatador);
		Paragraph paragrafoDataHoraAtual = new Paragraph();
		paragrafoDataHoraAtual.setAlignment(Element.ALIGN_RIGHT);
		paragrafoDataHoraAtual.add(new Chunk("Data e hora atual: " + fataFormatada, new Font(Font.HELVETICA, 8)));

		Paragraph paragrafoProduto = new Paragraph();
		paragrafoProduto.setAlignment(Element.ALIGN_LEFT);
		ArrayList<String> listaProdutos = new ArrayList<>();

		buscarListaDeProdutos(listaProdutos);
		for (String produtoString : listaProdutos) {
			paragrafoProduto.add(new Chunk(produtoString));

			// Adiciona quebra de linha após cada produto
			paragrafoProduto.add(Chunk.NEWLINE);
		}

		Paragraph paragrafoSessao = new Paragraph(
				"--------------------------------------------------------------------------------------------------------");
		paragrafoSessao.setAlignment(Element.ALIGN_CENTER);

		try {
			produtos = new TelaProdutos();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		produtos.buscarListaDeProdutosParaRelatorio();

		// PDF
		try {
			preparador();

			// ADICIONANDO AO RELATÓRIO
			documentPDF.open();
			documentPDF.setPageSize(PageSize.A4);
			documentPDF.add(new Paragraph(paragrafoTitulo));
			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(paragrafoDataHoraAtual));
			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(paragrafoProduto));
			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(paragrafoSessao));
			documentPDF.add(new Paragraph(" "));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			documentPDF.close();
		}

		System.out.println("O arquivo PDF foi criado com sucesso!");
	}
	
	
	

	@Override
	public void gerarCopor() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gerarRodape() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gerarImprimir() {
		if (this.documentPDF != null && this.documentPDF.isOpen()) {
			this.documentPDF.close();
		}

	}

	public ArrayList<String> buscarListaDeProdutos(ArrayList<String> retornoLista) {
		System.out.println("******* Iniciando liestagem de produtos *******");
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

		// Clausula from
		Root<ProdutoVO> produtosFrom = criteria.from(ProdutoVO.class);
		criteria.select(produtosFrom);

		TypedQuery<ProdutoVO> query = em.createQuery(criteria);
		listagemProdutoParaRelatorio = query.getResultList();

		for (ProdutoVO produto : listagemProdutoParaRelatorio) {
			String string = String.format(
					"Identificação: %s | Nome: %s | Marca: %s | Descrição: %s | Preço: %.2f | Qntd: %d | Validade: %s | %s",
					produto.getIndentificacao(), produto.getNome(), produto.getMarca(), produto.getDescricao(),
					produto.getPreco(), produto.getQuantidadeEstoque(), produto.getDataValidade(),
					produto.getStatusVenda() + "\n");
			retornoLista.add(string);
		}
		return retornoLista;

	}

}
