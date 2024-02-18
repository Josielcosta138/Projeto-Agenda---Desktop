package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import Relatorio.RelatorioPdfSimples;
import Relatorio.RelatorioTeste;
import br.com.senac.dao.HibernateUtil;
import br.com.senac.exception.BOValidationException;
import br.com.senac.service.Service;
import br.com.senac.vo.ProdutoVO;
import br.com.senac.vo.StatusAgendamento;
import br.com.senac.vo.StatusServico;
import br.com.senac.vo.StatusVenda;
import br.com.senac.vo.TipoServicoVO;
import net.sf.jasperreports.engine.JRException;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class TelaProdutos extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField ftfQuantidade;
	private JFormattedTextField ftfPreco;
	private JFormattedTextField ftfIdentificacao;
	private JFormattedTextField ftfNome;
	private JFormattedTextField ftfMarca;
	private JFormattedTextField ftfDescricao;
	private JFormattedTextField ftfValidade;
	private JFormattedTextField ftfCodigo;
	private JRadioButton RadioButtonSim;
	private JRadioButton RadioButtonNao;
	private String statusVenda = null;
	private JTable table;
	private TableModel tableModel;
	private  List<ProdutoVO> listagemDeProdutos;
	private JFormattedTextField ftfNome_Pesquisa;
	private JFormattedTextField ftfIdentificacao_Pesquisa;
	private List<ProdutoVO> listagemProdutoParaRelatorio;
	List<String> strings1 = new ArrayList<>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaProdutos frame = new TelaProdutos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaProdutos() throws ParseException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
				cadastroPessoaView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaProdutos.class.getResource("/br/com/senac/view/img/business.png")));
		setTitle("PRODUTOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 769);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(160, 160, 160)));
		panel.setBounds(10, 48, 625, 270);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIdentificacao = new JLabel("Identificação:");
		lblIdentificacao.setForeground(new Color(95, 95, 95));
		lblIdentificacao.setBounds(10, 28, 81, 14);
		panel.add(lblIdentificacao);
		
		MaskFormatter maskNum = new MaskFormatter("#########");
		ftfIdentificacao = new JFormattedTextField(maskNum);
		ftfIdentificacao.setBounds(91, 25, 243, 20);
		ftfIdentificacao.setFocusLostBehavior(JFormattedTextField.PERSIST);
		panel.add(ftfIdentificacao);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(95, 95, 95));
		lblNome.setBounds(10, 66, 71, 14);
		panel.add(lblNome);
		
		ftfNome = new JFormattedTextField();
		ftfNome.setBounds(91, 63, 243, 20);
		panel.add(ftfNome);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setForeground(new Color(95, 95, 95));
		lblMarca.setBounds(10, 102, 71, 14);
		panel.add(lblMarca);
		
		ftfMarca = new JFormattedTextField();
		ftfMarca.setBounds(91, 99, 243, 20);
		panel.add(ftfMarca);
		
		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setForeground(new Color(95, 95, 95));
		lblDescricao.setBounds(10, 142, 71, 14);
		panel.add(lblDescricao);
		
		ftfDescricao = new JFormattedTextField();
		ftfDescricao.setBounds(91, 139, 243, 20);
		panel.add(ftfDescricao);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setForeground(new Color(95, 95, 95));
		lblQuantidade.setBounds(369, 28, 71, 14);
		panel.add(lblQuantidade);
		
		
		MaskFormatter maskQnt = new MaskFormatter("#########");
		ftfQuantidade = new JFormattedTextField(maskQnt); 
		ftfQuantidade.setBounds(437, 25, 176, 20);
		ftfQuantidade.setFocusLostBehavior(JFormattedTextField.PERSIST);
		panel.add(ftfQuantidade);
		
		JLabel lblPreco = new JLabel("Preço R$:");
		lblPreco.setForeground(new Color(95, 95, 95));
		lblPreco.setBounds(369, 66, 55, 14);
		panel.add(lblPreco);
		
		
		MaskFormatter maskPreco = new MaskFormatter("##.##");
		maskPreco.setValidCharacters("0123456789");
		ftfPreco = new JFormattedTextField(maskPreco); 
		ftfPreco.setBounds(437, 63, 176, 20);
		panel.add(ftfPreco);
		
		
		JLabel lblDataValidade = new JLabel("Data de val:");
		lblDataValidade.setForeground(new Color(95, 95, 95));
		lblDataValidade.setBounds(369, 105, 65, 14);
		panel.add(lblDataValidade);
		
		MaskFormatter maskDataVal = new MaskFormatter("##/##/####");
		ftfValidade = new JFormattedTextField(maskDataVal);
		ftfValidade.setBounds(437, 99, 176, 20);
		panel.add(ftfValidade);
		
		JLabel lblVenda = new JLabel("Venda:");
		lblVenda.setForeground(new Color(95, 95, 95));
		lblVenda.setBounds(369, 142, 43, 14);
		panel.add(lblVenda);
		
		RadioButtonSim = new JRadioButton("Sim");
		RadioButtonSim.setForeground(new Color(95, 95, 95));
		RadioButtonSim.setBounds(437, 138, 48, 23);
		panel.add(RadioButtonSim);
		
		RadioButtonNao = new JRadioButton("Não");
		RadioButtonNao.setForeground(new Color(95, 95, 95));
		RadioButtonNao.setBounds(487, 138, 55, 23);
		panel.add(RadioButtonNao);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarProduto();
			}
		});
		btnSalvar.setIcon(new ImageIcon(TelaProdutos.class.getResource("/br/com/senac/view/img/salvar.png")));
		btnSalvar.setForeground(new Color(95, 95, 95));
		btnSalvar.setBounds(10, 221, 101, 23);
		panel.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		
		btnCancelar.setIcon(new ImageIcon(TelaProdutos.class.getResource("/br/com/senac/view/img/cancelar22.png")));
		btnCancelar.setForeground(new Color(95, 95, 95));
		btnCancelar.setBounds(487, 221, 114, 23);
		panel.add(btnCancelar);
		
		JLabel lblCodigo = new JLabel("Cód:");
		lblCodigo.setForeground(new Color(95, 95, 95));
		lblCodigo.setBounds(10, 175, 71, 14);
		panel.add(lblCodigo);
		
		ftfCodigo = new JFormattedTextField();
		ftfCodigo.setEnabled(false);
		ftfCodigo.setEditable(false);
		ftfCodigo.setBounds(91, 172, 65, 20);
		panel.add(ftfCodigo);
		
		
	
		
		
		JButton btnRelatorio = new JButton("Relatorio");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//RelatorioTeste relatorioTeste = new RelatorioTeste();
				RelatorioPdfSimples relatorioPdfSimples = new RelatorioPdfSimples();
				
				try {
					relatorioPdfSimples.gerarImprimir();
					relatorioPdfSimples.gerarCabecalho();
					relatorioPdfSimples.gerarCopor();
					relatorioPdfSimples.gerarRodape();
					
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnRelatorio.setForeground(new Color(95, 95, 95));
		btnRelatorio.setBounds(220, 221, 101, 23);
		panel.add(btnRelatorio);
		
		JLabel lblCadastroProduto = new JLabel("Cadastro de produtos");
		lblCadastroProduto.setForeground(new Color(115, 115, 115));
		lblCadastroProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCadastroProduto.setBounds(10, 23, 141, 14);
		contentPane.add(lblCadastroProduto);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaProdutos.class.getResource("/br/com/senac/view/img/product.png")));
		lblNewLabel_1.setBounds(147, 11, 33, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblEstoque = new JLabel("Estoque de produtos");
		lblEstoque.setForeground(new Color(115, 115, 115));
		lblEstoque.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstoque.setBounds(10, 393, 141, 14);
		contentPane.add(lblEstoque);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(504, 39, 51, -26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaProdutos.class.getResource("/br/com/senac/view/img/storage.png")));
		lblNewLabel_2.setBounds(147, 378, 33, 29);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(95, 95, 95)));
		panel_2.setBounds(10, 415, 625, 2);
		contentPane.add(panel_2);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TelaAcessosView telaAcessosView = new TelaAcessosView();
					telaAcessosView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		btnVoltar.setIcon(new ImageIcon(TelaProdutos.class.getResource("/br/com/senac/view/img/2303132_arrow_back_direction_left_navigation_icon.png")));
		btnVoltar.setForeground(new Color(95, 95, 95));
		btnVoltar.setBounds(534, 687, 101, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblNome_Pesquisa = new JLabel("Nome:");
		lblNome_Pesquisa.setForeground(new Color(95, 95, 95));
		lblNome_Pesquisa.setBounds(10, 447, 43, 14);
		contentPane.add(lblNome_Pesquisa);
		
		ftfNome_Pesquisa = new JFormattedTextField();
		ftfNome_Pesquisa.setBounds(51, 444, 176, 20);
		contentPane.add(ftfNome_Pesquisa);
		
		JLabel lblidentificacao = new JLabel("Identificação:");
		lblidentificacao.setForeground(new Color(95, 95, 95));
		lblidentificacao.setBounds(247, 447, 86, 14);
		contentPane.add(lblidentificacao);
		
		ftfIdentificacao_Pesquisa = new JFormattedTextField();
		ftfIdentificacao_Pesquisa.setBounds(337, 444, 152, 20);
		contentPane.add(ftfIdentificacao_Pesquisa);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarProdutos();
			}
		});
		
		btnPesquisar.setIcon(new ImageIcon(TelaProdutos.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnPesquisar.setForeground(new Color(95, 95, 95));
		btnPesquisar.setBounds(504, 443, 123, 23);
		contentPane.add(btnPesquisar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(10, 687, 101, 23);
		contentPane.add(btnEditar);
		btnEditar.setIcon(new ImageIcon(TelaProdutos.class.getResource("/br/com/senac/view/img/editar.png")));
		btnEditar.setForeground(new Color(95, 95, 95));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(232, 687, 101, 23);
		contentPane.add(btnExcluir);
		btnExcluir.setIcon(new ImageIcon(TelaProdutos.class.getResource("/br/com/senac/view/img/remove.png")));
		btnExcluir.setForeground(new Color(95, 95, 95));
		
		JButton btnSalvar_2 = new JButton("Salvar");
		btnSalvar_2.setIcon(new ImageIcon(TelaProdutos.class.getResource("/br/com/senac/view/img/salvar.png")));
		btnSalvar_2.setForeground(new Color(95, 95, 95));
		btnSalvar_2.setBounds(121, 687, 101, 23);
		contentPane.add(btnSalvar_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 482, 625, 190);
		contentPane.add(scrollPane);
		
		tableModel = new TableModel();
		tableModel.addColumn("Código"); 
		tableModel.addColumn("Identitifacação");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Descrição");
		tableModel.addColumn("Preço");
		tableModel.addColumn("Quantidade");
		tableModel.addColumn("Data validade");
		tableModel.addColumn("Venda");

		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new MonthColorRenderer());
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(80);
		tcm.getColumn(1).setPreferredWidth(100);
		tcm.getColumn(2).setPreferredWidth(120);
		tcm.getColumn(3).setPreferredWidth(120);
		tcm.getColumn(4).setPreferredWidth(80);
		tcm.getColumn(5).setPreferredWidth(80);
		tcm.getColumn(6).setPreferredWidth(100);
		tcm.getColumn(7).setPreferredWidth(120);
	
		scrollPane.setViewportView(table);
		listarProdutos();
	}

	
	protected void listarProdutos() {
		
		
		String nome = null;
		String nomePesquisa = null;
		BigInteger identificacao = null;
		
		try {
			try {
				
				if (this.ftfNome.getText() != null && ftfNome.getText().trim().length() > 0) {
					nome = ftfNome.getText().trim();
				}
				
				if (this.ftfNome_Pesquisa.getText() != null && ftfNome_Pesquisa.getText().trim().length() > 0) {
					nomePesquisa = ftfNome_Pesquisa.getText().trim();
				}
				
				if (this.ftfIdentificacao_Pesquisa.getText() != null && this.ftfIdentificacao_Pesquisa.getText().trim().length() > 0) {
					try {
						identificacao = new BigInteger(ftfIdentificacao_Pesquisa.getText().trim());
					} catch (Exception e) {
						throw new BOValidationException("Código: erro de validação" + " identificação inválida");
					}
				}
				
								
				
				System.out.println("******* Iniciando liestagem de produtos *******");
				EntityManager em = HibernateUtil.getEntityManager();

				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

				// Clausula from
				Root<ProdutoVO> produtosFrom = criteria.from(ProdutoVO.class);
				criteria.select(produtosFrom);
				
				//CORRIGIR 
				
				if (nomePesquisa != null) {
					String searchTerm = "%" + nomePesquisa.toLowerCase() + "%";
					criteria.where(cb.like(cb.lower(produtosFrom.get("nome")), searchTerm));
				} 
				
				
				if (identificacao != null) {
					criteria.where(cb.equal(produtosFrom.get("indentificacao"), identificacao));
				}
				
				
				
				TypedQuery<ProdutoVO> query = em.createQuery(criteria);
				listagemDeProdutos = query.getResultList();
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

				tableModel.clearTable(); 

				for (ProdutoVO produtossVO : listagemDeProdutos) {
					if (produtossVO.getId() != null) {
						
						RowData rowData = new RowData();
					
						
						SimpleDateFormat sdfMes = new SimpleDateFormat("dd/MM/yyyy");
						rowData.getValues().put(0, produtossVO.getId().toString());
						rowData.getValues().put(1, produtossVO.getIndentificacao());
						rowData.getValues().put(2, produtossVO.getNome());
						rowData.getValues().put(3, produtossVO.getDescricao());
						rowData.getValues().put(4, produtossVO.getPreco().toString() + " R$");
						rowData.getValues().put(5, produtossVO.getQuantidadeEstoque());
						rowData.getValues().put(6, sdfMes.format(produtossVO.getDataValidade()));
						rowData.getValues().put(7, produtossVO.getStatusVenda());
						

						rowData.setElement(produtossVO);
						tableModel.addRow(rowData);
					} else {
						JOptionPane.showMessageDialog(null, "Sem Agendamentos no momento!", null,
								JOptionPane.WARNING_MESSAGE);
					}
				}

			} catch (Exception e) {
				throw new BOValidationException("Código: erro de validação" + " valor inválido");
			}

		} catch (BOValidationException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de validação", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de sistema", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
	}

	protected void salvarProduto() {
		
		System.out.println("*****************Iniciando Salvar produto*****************");

		try {

			Service service = new Service();
			ProdutoVO produtoVO = new ProdutoVO();

			String codigo = ftfCodigo.getText().trim();
			String preco = ftfPreco.getText().trim();
			String identificacao = ftfIdentificacao.getText().trim();
			String marca = ftfMarca.getText().trim();
			String descricao = ftfDescricao.getText().trim();
			String nome = ftfNome.getText().trim();
			String quantidade = ftfQuantidade.getText().trim();
			String dataVal = ftfValidade.getText().trim();
			

			if (codigo != null && codigo.length() > 0) {
				produtoVO.setId(new BigInteger(codigo));
				produtoVO = service.buscarProdutoPorId(produtoVO);
			}
			
			
	        if (RadioButtonSim.isSelected()) {
	            produtoVO.setStatusVenda(StatusVenda.SIM);
	           
	        } else if (RadioButtonNao.isSelected()) {
	        	 produtoVO.setStatusVenda(StatusVenda.NAO);
	        }

		
			String pattern = "dd/MM/yyyy";
			DateFormat dateFormat = new SimpleDateFormat(pattern);

			try {
				if (!dataVal.isEmpty()) {
					Date date = dateFormat.parse(dataVal);
					produtoVO.setDataValidade(date);
				} else {
					JOptionPane.showMessageDialog(this, "Data de nascimento vazia!", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}

			} catch (ParseException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Formato de data inválido! Formato correto dd/MM/yyyy HH:mm:ss",
						"Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
 
			

			if (preco != null) {
				preco = preco.replace(",", ".");
				produtoVO.setPreco(new BigDecimal(preco));
			}

			produtoVO.setNome(nome);
			produtoVO.setDescricao(descricao);
			int qntd = Integer.parseInt(quantidade); 
			produtoVO.setQuantidadeEstoque(qntd);
			produtoVO.setMarca(marca);
			BigInteger ident = new BigInteger(identificacao);
			produtoVO.setIndentificacao(ident); 
			
			service.salvar(produtoVO);

			JOptionPane.showMessageDialog(null, "Cadastro salvo com sucesso!");

		
		} catch (BOValidationException b) {
			b.printStackTrace();
			JOptionPane.showMessageDialog(this, b.getMessage(), "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);

		} catch (Exception b) {
			b.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Erro",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			System.out.println("Finally");
		}
	}
	

	
	protected void cancelar() {
	
			ftfPreco.setValue(null);
			ftfIdentificacao.setValue("");
			ftfNome.setValue("");
			ftfMarca.setValue("");
			ftfDescricao.setValue("");
			ftfValidade.setValue(null);
			ftfQuantidade.setValue("");
	}
	
	
	
	public void buscarListaDeProdutosParaRelatorio() {
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
			String string = produto.getId() + ", " + produto.getIndentificacao() + ", " + produto.getNome() + ", "
					+ produto.getMarca() + ", " + produto.getDescricao() + ", " + produto.getPreco() + ", "
					+ produto.getQuantidadeEstoque() + ", " + produto.getDataValidade() + ", "
					+ produto.getStatusVenda();
			strings1.add(string);
		}
		
	}
	
}
