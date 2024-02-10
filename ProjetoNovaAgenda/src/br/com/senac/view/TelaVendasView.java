package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.ContelVO;
import br.com.senac.vo.ProdutoVO;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaVendasView extends JFrame {

	private JPanel contentPane;
	private JList<String> nomeList;
	private JList<String> emailList;
	private JList<String> descricaoList;
	private JFormattedTextField ftfNome;
	private JFormattedTextField ftfEmail;
	private JFormattedTextField ftfTelefone;
	private JFormattedTextField ftfDD;
	private JList<String> listaTelefone;
	private JFormattedTextField ftfDescricao;
	private JFormattedTextField ftfValor;
	private JFormattedTextField ftfQuantidade;
	private JFormattedTextField ftfTotalProd;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVendasView frame = new TelaVendasView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public TelaVendasView() throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaVendasView.class.getResource("/br/com/senac/view/img/business.png")));
		setTitle("VENDAS ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 727);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblVendaProdutos = new JLabel("Venda de produtos ");
		lblVendaProdutos.setForeground(new Color(120, 120, 120));
		lblVendaProdutos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVendaProdutos.setBounds(10, 20, 133, 20);
		contentPane.add(lblVendaProdutos);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/gel.png")));
		lblNewLabel_1.setBounds(143, 11, 29, 29);
		contentPane.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel.setBounds(10, 104, 320, 212);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(92, 92, 92));
		lblNome.setBounds(10, 52, 46, 14);
		panel.add(lblNome);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(92, 92, 92));
		lblEmail.setBounds(10, 92, 46, 14);
		panel.add(lblEmail);

		JLabel lblDD = new JLabel("DD:");
		lblDD.setForeground(new Color(92, 92, 92));
		lblDD.setBounds(10, 130, 46, 14);
		panel.add(lblDD);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setForeground(new Color(92, 92, 92));
		lblTelefone.setBounds(10, 166, 60, 14);
		panel.add(lblTelefone);

		// Lista de telefones
		listaTelefone = new JList();
		listaTelefone.setBounds(80, 177, 186, 68);
		panel.add(listaTelefone);

		ftfNome = new JFormattedTextField();
		ftfNome.setBounds(71, 49, 207, 17);
		panel.add(ftfNome);

		// Lista de Nomes na busca de campos
		nomeList = new JList<>();
		nomeList.setBounds(80, 65, 186, 77);
		panel.add(nomeList);

		ftfEmail = new JFormattedTextField();
		ftfEmail.setBounds(71, 89, 207, 17);
		panel.add(ftfEmail);

		// Lista de e-mail na busca de campos
		emailList = new JList<>();
		emailList.setBounds(80, 103, 186, 77);
		panel.add(emailList);

		MaskFormatter maskDD = new MaskFormatter("(##)");
		ftfDD = new JFormattedTextField(maskDD);
		ftfDD.setBounds(71, 127, 207, 17);
		panel.add(ftfDD);

		ftfTelefone = new JFormattedTextField();
		ftfTelefone.setBounds(69, 163, 209, 17);
		panel.add(ftfTelefone);

		JLabel lblCod = new JLabel("Cód:");
		lblCod.setForeground(new Color(92, 92, 92));
		lblCod.setBounds(10, 14, 46, 14);
		panel.add(lblCod);

		JFormattedTextField ftfNome_1 = new JFormattedTextField();
		ftfNome_1.setEditable(false);
		ftfNome_1.setEnabled(false);
		ftfNome_1.setBounds(71, 11, 207, 17);
		panel.add(ftfNome_1);

		JButton btnPesquisarNome = new JButton("");
		btnPesquisarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarPorNome();
			}
		});
		btnPesquisarNome
				.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnPesquisarNome.setBounds(288, 49, 18, 17);
		panel.add(btnPesquisarNome);

		JButton btnPesquisarEmail = new JButton("");
		btnPesquisarEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarPorEmail();
			}
		});
		btnPesquisarEmail
				.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnPesquisarEmail.setBounds(288, 88, 18, 17);
		panel.add(btnPesquisarEmail);

		JButton btnPesquisarTelefone = new JButton("");
		btnPesquisarTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarPorTelefone();
			}
		});
		btnPesquisarTelefone
				.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnPesquisarTelefone.setBounds(288, 162, 18, 17);
		panel.add(btnPesquisarTelefone);

		JLabel lblClienteTitulo = new JLabel("Cliente");
		lblClienteTitulo.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/user222.png")));
		lblClienteTitulo.setForeground(new Color(120, 120, 120));
		lblClienteTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblClienteTitulo.setBounds(10, 70, 77, 29);
		contentPane.add(lblClienteTitulo);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel_1.setBounds(351, 104, 365, 212);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblDescrição = new JLabel("Descrição:");
		lblDescrição.setForeground(new Color(92, 92, 92));
		lblDescrição.setBounds(10, 15, 87, 14);
		panel_1.add(lblDescrição);

		JLabel lblValor = new JLabel("Valor R$:");
		lblValor.setForeground(new Color(92, 92, 92));
		lblValor.setBounds(10, 53, 77, 14);
		panel_1.add(lblValor);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setForeground(new Color(92, 92, 92));
		lblQuantidade.setBounds(10, 89, 87, 14);
		panel_1.add(lblQuantidade);

		ftfDescricao = new JFormattedTextField();
		ftfDescricao.setBounds(97, 12, 233, 17);
		panel_1.add(ftfDescricao);

		// Lista de Descrição na busca de campos
		descricaoList = new JList<>();
		descricaoList.setBounds(107, 26, 206, 68);
		panel_1.add(descricaoList);

		MaskFormatter mask = new MaskFormatter("##.00");
		mask.setValidCharacters("0123456789");
		ftfValor = new JFormattedTextField();
		ftfValor.setEnabled(false);
		ftfValor.setEditable(false);
		ftfValor.setBounds(97, 50, 233, 17);
		panel_1.add(ftfValor);

		ftfQuantidade = new JFormattedTextField();
		ftfQuantidade.setBounds(97, 86, 233, 17);
		panel_1.add(ftfQuantidade);

		JLabel lblTotal = new JLabel("Total R$:");
		lblTotal.setForeground(new Color(92, 92, 92));
		lblTotal.setBounds(10, 123, 72, 14);
		panel_1.add(lblTotal);

		ftfTotalProd = new JFormattedTextField();
		ftfTotalProd.setEnabled(false);
		ftfTotalProd.setEditable(false);
		ftfTotalProd.setFont(new Font("Tahoma", Font.BOLD, 11));
		ftfTotalProd.setBounds(97, 120, 119, 17);
		panel_1.add(ftfTotalProd);

		JButton btnPesquisarDescricao = new JButton("");
		btnPesquisarDescricao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PesquisarPorDescricao();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnPesquisarDescricao
				.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnPesquisarDescricao.setBounds(340, 11, 18, 17);
		panel_1.add(btnPesquisarDescricao);
		
		JButton btnPesquisarDescricao_1 = new JButton("");
		btnPesquisarDescricao_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularValorTotal();
			}
		});
		btnPesquisarDescricao_1.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/click.png")));
		btnPesquisarDescricao_1.setBounds(340, 85, 18, 17);
		panel_1.add(btnPesquisarDescricao_1);

		descricaoList.setVisible(false);
		// edição campo descrição
		descricaoList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String selectedDescricao = descricaoList.getSelectedValue();
					if (selectedDescricao != null) {
						ftfDescricao.setText(selectedDescricao);
						descricaoList.setVisible(false);
					}
				}
			}
		});

		JLabel lblProdutoTitulos = new JLabel("Produto");
		lblProdutoTitulos
				.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/comprarProdut.png")));
		lblProdutoTitulos.setForeground(new Color(120, 120, 120));
		lblProdutoTitulos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProdutoTitulos.setBounds(351, 70, 100, 29);
		contentPane.add(lblProdutoTitulos);

		JLabel lblResumo = new JLabel("Resumo");
		lblResumo.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/resumo.png")));
		lblResumo.setForeground(new Color(120, 120, 120));
		lblResumo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblResumo.setBounds(10, 405, 100, 29);
		contentPane.add(lblResumo);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel_1_1.setBounds(10, 470, 706, 158);
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel_2.setBounds(10, 445, 706, 1);
		contentPane.add(panel_2);

		nomeList.setVisible(false);
		// edição campo nome
		nomeList.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String selectedNome = nomeList.getSelectedValue();
					if (selectedNome != null) {
						ftfNome.setText(selectedNome);
						nomeList.setVisible(false);
					}
				}
			}
		});

		emailList.setVisible(false);
		// edição campo email
		emailList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String selectedEmail = emailList.getSelectedValue();
					if (selectedEmail != null) {
						ftfEmail.setText(selectedEmail);
						emailList.setVisible(false);
					}
				}
			}
		});

		listaTelefone.setVisible(false);
		// edição campo telefone
		listaTelefone.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String selectedTelefone = listaTelefone.getSelectedValue();
					if (selectedTelefone != null) {
						ftfTelefone.setText(selectedTelefone);
						listaTelefone.setVisible(false);
					}
				}
			}
		});

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarVenda();

			}
		});
		btnSalvar.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/salvar.png")));
		btnSalvar.setBounds(10, 343, 100, 23);
		contentPane.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarVenda();

			}
		});
		btnCancelar.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/cancelar22.png")));
		btnCancelar.setBounds(603, 343, 113, 23);
		contentPane.add(btnCancelar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAcessosView telaAcessosView = new TelaAcessosView();
				telaAcessosView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		btnVoltar.setIcon(new ImageIcon(TelaVendasView.class
				.getResource("/br/com/senac/view/img/2303132_arrow_back_direction_left_navigation_icon.png")));
		btnVoltar.setBounds(603, 654, 113, 23);
		contentPane.add(btnVoltar);
	}


	
	//Esse método é uma situação que o calculo deverá ir pra classe BO
	protected void calcularValorTotal() {
		
		String valorCal = ftfValor.getText().trim().replace(',', '.');
	    double valorDouble = Double.parseDouble(valorCal);

	    String qntParticipantesCalString = ftfQuantidade.getText().trim();
	    if (!qntParticipantesCalString.isEmpty()) {
	        try {
	            int qntParticipantesCal = Integer.parseInt(qntParticipantesCalString);

	            double totalDouble = qntParticipantesCal * valorDouble;
	            String totalFormatted = String.format("%.2f", totalDouble);

	            // Atualize apenas o texto do ftfTotalProd
	            ftfTotalProd.setText(totalFormatted);

	        } catch (NumberFormatException e) {
	            System.err.println("Erro ao converter para número: " + e.getMessage());
	        }
	    } else {
	        System.err.println("A quantidade de participantes não pode estar vazia.");
	    }
		
		
	}

	protected void PesquisarPorDescricao() throws ParseException {
		TelaProdutos telaProdutos = new TelaProdutos();
		telaProdutos.listarProdutos();

		String descricao = null;

		try {

			System.out.println("******* Iniciando consulta de produtos por Descrição *******");
			EntityManager em = HibernateUtil.getEntityManager();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

			// Clausula from
			Root<ProdutoVO> produtoVOFrom = criteria.from(ProdutoVO.class);
			criteria.select(produtoVOFrom);

			if (this.ftfDescricao.getText() != null && ftfDescricao.getText().trim().length() > 0) {
				descricao = ftfDescricao.getText().trim();
			}

			if (descricao != null) {
				String searchTerm = "%" + descricao.toLowerCase() + "%";
				criteria.where(cb.like(cb.lower(produtoVOFrom.get("descricao")), searchTerm));
			}

			
			
			TypedQuery<ProdutoVO> query = em.createQuery(criteria);
			List<ProdutoVO> listaProdutoNome = query.getResultList();
			System.out.println(listaProdutoNome);

			// edição lista descrção
			DefaultListModel<String> model = new DefaultListModel<>();
			String valor = null;
			String item = null;
			String itemValor = null;
			ProdutoVO produtoVO2 = null;
			
			for (ProdutoVO produtoVO : listaProdutoNome) {
				item = produtoVO.getIndentificacao().toString() + " - " + produtoVO.getDescricao();
				model.addElement(item);
				
			}
			
			
			MaskFormatter mask = new MaskFormatter("##.00");
			mask.setValidCharacters("0123456789");
			descricaoList.setModel(model);
			
			
			
			if (descricaoList.getModel().getSize() == 0) {
				descricaoList.setVisible(false);
			} else {
				descricaoList.setVisible(true);
			}
			
			descricaoList.addListSelectionListener(new ListSelectionListener() {
			    public void valueChanged(ListSelectionEvent event) {
			        if (!event.getValueIsAdjusting()) {
			            int selectedIndex = descricaoList.getSelectedIndex();
			            if (selectedIndex != -1) {
			                String selectedItem = descricaoList.getModel().getElementAt(selectedIndex);
			                String[] parts = selectedItem.split(" - ");
			                String selectedItemId = parts[0].trim(); // Supondo que o ID seja a primeira parte do item selecionado
			                // Procurando o produto correspondente ao ID selecionado
			                for (ProdutoVO produtoVO : listaProdutoNome) {
			                    if (produtoVO.getIndentificacao().toString().equals(selectedItemId)) {
			                        // Quando encontrar o produto correspondente, definir o valor no ftfValor
			                        ftfValor.setValue(produtoVO.getPreco());
			                        break; // Saia do loop assim que encontrar o produto correspondente
			                    }
			                }
			            }
			        }
			    }
			});

			
			
			
			

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de sistema", JOptionPane.ERROR_MESSAGE);
		} finally {
			System.out.println("Finally");
		}

	}

	protected void pesquisarPorIdenficacao() throws ParseException {

		/*
		TelaProdutos telaProdutos = new TelaProdutos();
		telaProdutos.listarProdutos();

		String identificao = null;

		try {

			System.out.println("******* Iniciando consulta de identificação de produtos *******");
			EntityManager em = HibernateUtil.getEntityManager();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

			// Clausula from
			Root<ProdutoVO> identificacaoFrom = criteria.from(ProdutoVO.class);
			criteria.select(identificacaoFrom);

			if (this.ftfIdentificacao.getText() != null && ftfIdentificacao.getText().trim().length() > 0) {
				identificao = ftfIdentificacao.getText().trim();
			}

			if (identificao != null) {
				String searchTerm = "%" + identificao.toLowerCase() + "%";
				criteria.where(cb.like(cb.lower(identificacaoFrom.get("indentificacao")), searchTerm));
			}

			TypedQuery<ProdutoVO> query = em.createQuery(criteria);
			List<ProdutoVO> listaIdentificacao = query.getResultList();
			System.out.println(listaIdentificacao);

			// edição lista identificação
			DefaultListModel<String> model = new DefaultListModel<>();
			for (ProdutoVO produtoVO : listaIdentificacao) {
				model.addElement(produtoVO.getIndentificacao().toString());
			}

			JList<String> listaIdentificacaoJList = new JList<>(model);
			
			//listaIdentificacao.setModel(model);
			if (model.getSize() == 0) {
				listaIdentificacaoJList.setVisible(false);
			} else {
				listaIdentificacaoJList.setVisible(true);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de sistema", JOptionPane.ERROR_MESSAGE);
		} finally {
			System.out.println("Finally");
		}
		
		*/

	}

	protected void pesquisarPorTelefone() {
		CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
		cadastroPessoaView.pesquisar();

		String telefone = null;

		try {

			System.out.println("******* Iniciando consulta de Telefones *******");
			EntityManager em = HibernateUtil.getEntityManager();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ContelVO> criteria = cb.createQuery(ContelVO.class);

			// Clausula from
			Root<ContelVO> contatosFrom = criteria.from(ContelVO.class);
			criteria.select(contatosFrom);

			if (this.ftfTelefone.getText() != null && ftfTelefone.getText().trim().length() > 0) {
				telefone = ftfTelefone.getText().trim();
			}

			if (telefone != null) {
				String searchTerm = "%" + telefone.toLowerCase() + "%";
				criteria.where(cb.like(cb.lower(contatosFrom.get("numero")), searchTerm));
			}

			TypedQuery<ContelVO> query = em.createQuery(criteria);
			List<ContelVO> listaContat = query.getResultList();
			System.out.println(listaContat);

			// edição lista e-mail
			DefaultListModel<String> model = new DefaultListModel<>();
			for (ContelVO contelVO : listaContat) {
				model.addElement(contelVO.getNumero());
			}

			listaTelefone.setModel(model);
			if (listaTelefone.getModel().getSize() == 0) {
				listaTelefone.setVisible(false);
			} else {
				listaTelefone.setVisible(true);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de sistema", JOptionPane.ERROR_MESSAGE);
		} finally {
			System.out.println("Finally");
		}

	}

	protected void pesquisarPorEmail() {
		CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
		cadastroPessoaView.pesquisar();

		String email = null;

		try {

			System.out.println("******* Iniciando consulta de contatos por Email *******");
			EntityManager em = HibernateUtil.getEntityManager();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ContelVO> criteria = cb.createQuery(ContelVO.class);

			// Clausula from
			Root<ContelVO> contatosFrom = criteria.from(ContelVO.class);
			criteria.select(contatosFrom);

			if (this.ftfEmail.getText() != null && ftfEmail.getText().trim().length() > 0) {
				email = ftfEmail.getText().trim();
			}

			if (email != null) {
				String searchTerm = "%" + email.toLowerCase() + "%";
				criteria.where(cb.like(cb.lower(contatosFrom.get("emails")), searchTerm));
			}

			TypedQuery<ContelVO> query = em.createQuery(criteria);
			List<ContelVO> listaContat = query.getResultList();
			System.out.println(listaContat);

			// edição lista e-mail
			DefaultListModel<String> model = new DefaultListModel<>();
			for (ContelVO contelVO : listaContat) {
				model.addElement(contelVO.getEmails());
			}

			emailList.setModel(model);
			if (emailList.getModel().getSize() == 0) {
				emailList.setVisible(false);
			} else {
				emailList.setVisible(true);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de sistema", JOptionPane.ERROR_MESSAGE);
		} finally {
			System.out.println("Finally");
		}

	}

	protected void pesquisarPorNome() {
		CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
		cadastroPessoaView.pesquisar();

		String nome = null;

		try {

			System.out.println("******* Iniciando consulta de contatos por Nome *******");
			EntityManager em = HibernateUtil.getEntityManager();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ContatoVO> criteria = cb.createQuery(ContatoVO.class);

			// Clausula from
			Root<ContatoVO> contatosFrom = criteria.from(ContatoVO.class);
			criteria.select(contatosFrom);

			if (this.ftfNome.getText() != null && ftfNome.getText().trim().length() > 0) {
				nome = ftfNome.getText().trim();
			}

			if (nome != null) {
				String searchTerm = "%" + nome.toLowerCase() + "%";
				criteria.where(cb.like(cb.lower(contatosFrom.get("nome")), searchTerm));
			}

			TypedQuery<ContatoVO> query = em.createQuery(criteria);
			List<ContatoVO> listaContat = query.getResultList();
			System.out.println(listaContat);

			// edição lista nome
			DefaultListModel<String> model = new DefaultListModel<>();
			for (ContatoVO contatoVO : listaContat) {
				model.addElement(contatoVO.getNome());
			}

			nomeList.setModel(model);
			if (nomeList.getModel().getSize() == 0) {
				nomeList.setVisible(false);
			} else {
				nomeList.setVisible(true);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de sistema", JOptionPane.ERROR_MESSAGE);
		} finally {
			System.out.println("Finally");
		}

	}

	protected void cancelarVenda() {
		
		ftfValor.setValue(null);
		ftfDescricao.setValue("");
		ftfNome.setValue("");
		ftfTelefone.setValue("");
		ftfEmail.setValue("");
		ftfDD.setValue("");
		ftfQuantidade.setValue("");
		ftfTotalProd.setValue("");

	}

	protected void salvarVenda() {
		// TODO Auto-generated method stub

	}
}
