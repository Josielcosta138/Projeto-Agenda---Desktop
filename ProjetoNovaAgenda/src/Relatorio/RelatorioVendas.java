package Relatorio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.view.TelaProdutos;
import br.com.senac.view.TelaVendasView;
import br.com.senac.vo.ProdutoVO;
import br.com.senac.vo.VendaVO;

public class RelatorioVendas implements IRelatorio {

	private Document documentPDF;
	private List<VendaVO> listagemProdutoParaRelatorio;
	private String caminhoRelatorio = "C:\\Users\\josie\\OneDrive\\Área de Trabalho\\relatorio\\arquivoVendasPdf.pdf";
	List<String> strings1 = new ArrayList<>();
	TelaProdutos produtos = null;
	TelaVendasView telaVendasView = null;
	int qntdClientes;
	int qntProdutoVendas;
	int qntdProdutoNome;
	String totalValor;
	String totalValorProduto;
	String totalProduto;

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

		// --------- MONTANDO PARâMETROS PARA ADC AO PDF

		Paragraph paragrafoTitulo = new Paragraph();
		paragrafoTitulo.setAlignment(Element.ALIGN_CENTER);
		paragrafoTitulo.add(new Chunk("RELATÓRIO DE VENDAS", new Font(Font.HELVETICA, 20)));

		Paragraph paragrafoSubTitulo = new Paragraph();
		paragrafoSubTitulo.setAlignment(Element.ALIGN_CENTER);
		paragrafoSubTitulo.add(new Chunk("Clientes", new Font(Font.HELVETICA, 14)));

		LocalDateTime dataHoraAtual = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String fataFormatada = dataHoraAtual.format(formatador);
		Paragraph paragrafoDataHoraAtual = new Paragraph();
		paragrafoDataHoraAtual.setAlignment(Element.ALIGN_RIGHT);
		paragrafoDataHoraAtual.add(new Chunk("Data e hora da emissão: " + fataFormatada, new Font(Font.HELVETICA, 8)));

		Paragraph paragrafoCliente = new Paragraph();
		paragrafoCliente.setAlignment(Element.ALIGN_LEFT);
		ArrayList<String> listaDeClientes = new ArrayList<>();

		buscarListaDeVendasParaRelatorio(listaDeClientes);
		for (String clientesString : listaDeClientes) {
			// String cliente = clientesString.
			paragrafoCliente.add(new Chunk(clientesString, new Font(Font.BOLD, 12)));

			paragrafoCliente.add(Chunk.NEWLINE);
		}
		
		Paragraph paragrafoQntdCliente = new Paragraph();
		paragrafoQntdCliente.setAlignment(Element.ALIGN_RIGHT);
		paragrafoQntdCliente.add(new Chunk("Qntde: " + qntdClientes, new Font(Font.HELVETICA, 10)));
		
		

		Paragraph paragrafoSessao = new Paragraph(
				"---------------------------------------------------------------------------------------------------------------------------");
		paragrafoSessao.setAlignment(Element.ALIGN_LEFT);

		// --------- ADICIONANDO AO PDF
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
			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(paragrafoSubTitulo));
			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(paragrafoCliente));
			documentPDF.add(new Paragraph(paragrafoQntdCliente));
			documentPDF.add(new Paragraph(paragrafoSessao));
			documentPDF.add(new Paragraph(" "));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// documentPDF.close();
		}

		System.out.println("O arquivo PDF foi criado com sucesso!");
		JOptionPane.showMessageDialog(null, "Aguarde. Seu relatório estara disponível em instantes. \n"
				+ "Em: C:\\Users\\josie\\OneDrive\\Área de Trabalho\\relatorio\\arquivoVendasPdf.pdf");
	}

	@Override
	public void gerarCopor() {

		// --------- ADICIONANDO AO PDF
		Paragraph pProdutosVendidos = new Paragraph();
		pProdutosVendidos.setAlignment(Element.ALIGN_CENTER);
		pProdutosVendidos.add(new Chunk("PRODUTOS VENDIDOS", new Font(Font.HELVETICA, 12)));

		Paragraph paragrafoSubTituloProd = new Paragraph();
		paragrafoSubTituloProd.setAlignment(Element.ALIGN_CENTER);
		paragrafoSubTituloProd.add(new Chunk("VENDAS", new Font(Font.HELVETICA, 12)));

		Paragraph paragrafoSessao = new Paragraph(
				"---------------------------------------------------------------------------------------------------------------------------");
		paragrafoSessao.setAlignment(Element.ALIGN_LEFT);

		LocalDateTime dataHoraAtual = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String fataFormatada = dataHoraAtual.format(formatador);
		Paragraph paragrafoDataHoraAtual = new Paragraph();
		paragrafoDataHoraAtual.setAlignment(Element.ALIGN_RIGHT);
		paragrafoDataHoraAtual.add(new Chunk("Data e hora atual: " + fataFormatada, new Font(Font.HELVETICA, 8)));

		ArrayList<String> listaProdutos = new ArrayList<>();
		ArrayList<String> listaNomeProdutos = new ArrayList<>();
		buscarListaDeProdutos(listaProdutos);
		buscarListaDeProdutoNome(listaNomeProdutos);

		Paragraph paragrafoProduto = new Paragraph();
		Paragraph paragrafoProdutoNome = new Paragraph();
		paragrafoProdutoNome.setAlignment(Element.ALIGN_LEFT);

		Paragraph paragrafoQntdProduto = new Paragraph();
		paragrafoQntdProduto.setAlignment(Element.ALIGN_RIGHT);
		Paragraph paragrafoQntdVenda = new Paragraph();
		paragrafoQntdVenda.setAlignment(Element.ALIGN_RIGHT);
		Paragraph paragrafoQntdNomeProduto= new Paragraph();
		paragrafoQntdNomeProduto.setAlignment(Element.ALIGN_RIGHT);
		Paragraph paragrafoValorTotalFim= new Paragraph();
		paragrafoValorTotalFim.setAlignment(Element.ALIGN_LEFT);
		Paragraph paragrafoQntTotalFimProd= new Paragraph();
		paragrafoQntTotalFimProd.setAlignment(Element.ALIGN_LEFT);
		Paragraph paragrafoQntTotalFimVALOR= new Paragraph();
		paragrafoQntTotalFimVALOR.setAlignment(Element.ALIGN_LEFT);
		
		

		for (String produtoStringNome : listaNomeProdutos) {
			paragrafoProdutoNome.add(new Chunk(produtoStringNome, new Font(Font.HELVETICA, 14)));
			paragrafoProdutoNome.add(Chunk.NEWLINE);

			paragrafoProduto.setAlignment(Element.ALIGN_LEFT);

		}
		
		for (String stringListProduto : listaProdutos) {
			paragrafoProduto.add(new Chunk(stringListProduto, new Font(Font.HELVETICA, 12)));
			paragrafoProduto.add(Chunk.NEWLINE);
		}

		paragrafoQntdNomeProduto.add(new Chunk("Qtde: " + qntProdutoVendas, new Font(Font.HELVETICA, 10)));
		paragrafoQntdProduto.add(new Chunk("Qtde: " + qntdProdutoNome, new Font(Font.HELVETICA, 10)));
		
		paragrafoQntTotalFimVALOR.add(new Chunk("Valor total un R$: " + totalValorProduto, new Font(Font.HELVETICA, 11)));
		paragrafoQntTotalFimProd.add(new Chunk("Qtde total produtos: " + totalProduto, new Font(Font.HELVETICA, 11)));
		paragrafoValorTotalFim.add(new Chunk("Valor total vendas R$: " + totalValor, new Font(Font.HELVETICA, 11)));
		
		
		

		// ADICIONANDO AO RELATÓRIO
		try {

			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(pProdutosVendidos));
			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(paragrafoProdutoNome));
			documentPDF.add(new Paragraph(paragrafoQntdProduto));
			documentPDF.add(new Paragraph(paragrafoSessao));
			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(paragrafoSubTituloProd));
			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(paragrafoProduto));
			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(paragrafoQntTotalFimVALOR));
			documentPDF.add(new Paragraph(paragrafoQntTotalFimProd));
			documentPDF.add(new Paragraph(paragrafoValorTotalFim));
			documentPDF.add(new Paragraph(paragrafoQntdNomeProduto));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// documentPDF.close();
		}

		System.out.println("O arquivo PDF foi criado com sucesso!");
		// JOptionPane.showMessageDialog(null, "Aguarde. Produtos adicionando ao PDF."
		// );
	}

	@Override
	public void gerarRodape() {
		Paragraph paragrafoSessao = new Paragraph(
				"---------------------------------------------------------------------------------------------------------------------------");
		paragrafoSessao.setAlignment(Element.ALIGN_CENTER);

		Paragraph paragrafoRodape = new Paragraph();
		paragrafoRodape.setAlignment(Element.ALIGN_CENTER);
		paragrafoRodape
				.add(new Chunk("https://www.linkedin.com/in/josiel-costa-07b2aa140/", new Font(Font.TIMES_ROMAN, 14)));

		// ADICIONANDO AO RELATÓRIO
		try {

			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(paragrafoSessao));
			documentPDF.add(new Paragraph(" "));
			documentPDF.add(new Paragraph(paragrafoRodape));
			documentPDF.add(new Paragraph(" "));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			documentPDF.close();
		}

		System.out.println("O arquivo PDF foi criado com sucesso!");
	}

	@Override
	public void gerarImprimir() {
		if (this.documentPDF != null && this.documentPDF.isOpen()) {
			this.documentPDF.close();
		}

	}

	int b = 1;
	public ArrayList<String> buscarListaDeVendasParaRelatorio(ArrayList<String> retornoListaVendas) {
		System.out.println("******* Iniciando liestagem de Vendas para Relatório *******");
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<VendaVO> criteria = cb.createQuery(VendaVO.class);

		// Clausula from
		Root<VendaVO> produtosFrom = criteria.from(VendaVO.class);
		criteria.select(produtosFrom);

		TypedQuery<VendaVO> query = em.createQuery(criteria);
		List<VendaVO> listagemDeVendasParaRelatorio = query.getResultList();

		for (VendaVO venda : listagemDeVendasParaRelatorio) {
			qntdClientes += b;
			String string = String.format("Cód: %s | Nome: %s | Telefone: %s | Email: %s | %s", venda.getId(),
					venda.getNome(), venda.getDd(), venda.getTelefone(), venda.getEmail() + "\n");
			retornoListaVendas.add(string);
		}
		return retornoListaVendas;

	}


	int c = 1;
	BigDecimal totalFim = BigDecimal.ZERO;
	BigDecimal totalFimProduto = BigDecimal.ZERO;
	int qntProdutos;
	public ArrayList<String> buscarListaDeProdutos(ArrayList<String> retornoLista) {
		System.out.println("******* Iniciando liestagem de produtos *******");
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<VendaVO> criteria = cb.createQuery(VendaVO.class);

		// Clausula from
		Root<VendaVO> produtosFrom = criteria.from(VendaVO.class);
		criteria.select(produtosFrom);

		TypedQuery<VendaVO> query = em.createQuery(criteria);
		listagemProdutoParaRelatorio = query.getResultList();

		for (VendaVO produto : listagemProdutoParaRelatorio) {
	
			String string = String.format(
					"Nome: %s | Descrição: %s | Valor un R$: %.2f | Qntd: %d | Total R$: %.2f | Form pagto: %s  ",
					produto.getNome(), produto.getDescricao(), produto.getValor(), produto.getQuantidade(),
					produto.getTotal(), produto.getStatusFormaPagto() == null ? ""
							: produto.getStatusFormaPagto().toString() + "\n" + "------------------------------------");
			
			totalFim = totalFim.add(produto.getTotal());
			totalFimProduto = totalFimProduto.add(produto.getValor());
			qntProdutos += produto.getQuantidade();
						
			retornoLista.add(string);
			
			totalValor = totalFim.toString();
			totalValorProduto = totalFimProduto.toString();
			totalProduto = Integer.toString((qntProdutos));
			
			
		}
		return retornoLista;
		
	}

	int x = 1;
	int z = 1;
	public ArrayList<String> buscarListaDeProdutoNome(ArrayList<String> retornoListaNome) {
		System.out.println("******* Iniciando liestagem de produtos Nome *******");
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<VendaVO> criteria = cb.createQuery(VendaVO.class);

		// Clausula from
		Root<VendaVO> produtosFrom = criteria.from(VendaVO.class);
		criteria.select(produtosFrom);

		TypedQuery<VendaVO> query = em.createQuery(criteria);
		listagemProdutoParaRelatorio = query.getResultList();

		for (VendaVO produto : listagemProdutoParaRelatorio) {
			qntProdutoVendas += x;
			qntdProdutoNome += z;
			String string = String.format("Nome: %s ", produto.getDescricao() + "\n");
			retornoListaNome.add(string);
		}
		return retornoListaNome;

	}

}
