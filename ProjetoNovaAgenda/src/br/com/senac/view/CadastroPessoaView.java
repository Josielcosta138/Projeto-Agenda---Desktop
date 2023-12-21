package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.service.IService;
import br.com.senac.service.Service;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.ContelVO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;

public class CadastroPessoaView extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTable table;
	private JFormattedTextField ftfCodigo;
	private TableModel tableModel;
	private TableModel tableModel1;
	private JFormattedTextField ftfNome;
	private TelaAcessosView telaAcessos;
	private JTable table_1;
	private TelaAgendar telaAgendar;
	private TelaCalendario telaCalendario;
	private Disponibilidade disponibilidade;
	private TelaAgendamentos telaAgendamentos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPessoaView frame = new CadastroPessoaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroPessoaView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				voltar();
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastroPessoaView.class.getResource("/br/com/senac/view/img/LogoSTYLEMANAGER black.png")));

		table = new JTable();
		tableModel = new TableModel();
		table.setModel(tableModel);

		setTitle("MENU PRINCIPAL -STYLE MANAGER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1089, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setBounds(10, 21, 605, 79);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setForeground(new Color(92, 92, 92));
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodigo.setBounds(10, 10, 74, 13);
		panel.add(lblCodigo);

		ftfCodigo = new JFormattedTextField();
		ftfCodigo.setBounds(10, 30, 105, 19);
		panel.add(ftfCodigo);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(92, 92, 92));
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setBounds(160, 10, 54, 13);
		panel.add(lblNome);

		ftfNome = new JFormattedTextField();
		ftfNome.setBounds(159, 30, 312, 19);
		panel.add(ftfNome);

		JButton btnPesquisar = new JButton("Pesq");
		btnPesquisar
				.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setMnemonic('P');
		btnPesquisar.setBounds(481, 29, 100, 21);
		panel.add(btnPesquisar);

		JButton btnAdcionar = new JButton("Adc");
		btnAdcionar
				.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/adicionar.png")));

		btnAdcionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarContato();
			}
		});

		btnAdcionar.setMnemonic('A');
		btnAdcionar.setBounds(20, 137, 85, 23);
		contentPane.add(btnAdcionar);

		JButton btnEditar = new JButton("Edt");
		btnEditar.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/editar.png")));

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarContato();
			}
		});

		btnEditar.setBounds(165, 139, 85, 21);
		contentPane.add(btnEditar);

		JButton btnExcluir = new JButton("Exc");
		btnExcluir.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/remove.png")));

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirContato();
			}
		});

		btnExcluir.setBounds(308, 139, 85, 21);
		contentPane.add(btnExcluir);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 171, 595, 207);
		contentPane.add(scrollPane);

		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Descrição");
		tableModel.addColumn("Data");
		tableModel.addColumn("Observação");

		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listarContatosTelefonicos();
			}
		});

		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(70);
		tcm.getColumn(1).setPreferredWidth(180);
		tcm.getColumn(2).setPreferredWidth(140);
		tcm.getColumn(3).setPreferredWidth(180);

		scrollPane.setViewportView(table);

		JButton btnVoltar = new JButton("Volt");
		btnVoltar.setIcon(new ImageIcon(CadastroPessoaView.class
				.getResource("/br/com/senac/view/img/2303132_arrow_back_direction_left_navigation_icon.png")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voltar();
			}
		});

		btnVoltar.setMnemonic('V');
		btnVoltar.setBounds(530, 139, 85, 21);
		contentPane.add(btnVoltar);

		JScrollPane scrolPContelefone = new JScrollPane();
		scrolPContelefone.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrolPContelefone.setBounds(635, 42, 417, 284);
		contentPane.add(scrolPContelefone);

		table_1 = new JTable();

		tableModel1 = new TableModel();
		tableModel1.addColumn("Código");
		tableModel1.addColumn("DDD");
		tableModel1.addColumn("Número");
		tableModel1.addColumn("Email");

		table_1 = new JTable(tableModel1);
		table_1.setAutoscrolls(true);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm1 = table_1.getColumnModel();
		tcm1.getColumn(0).setPreferredWidth(70);
		tcm1.getColumn(1).setPreferredWidth(60);
		tcm1.getColumn(2).setPreferredWidth(100);
		tcm1.getColumn(3).setPreferredWidth(180);

		scrolPContelefone.setViewportView(table_1);

		JButton btnAdc = new JButton("Adc");
		btnAdc.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/adicionar2.png")));
		btnAdc.setBounds(645, 341, 101, 21);
		contentPane.add(btnAdc);

		btnAdc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarContatoTelefonico();
			}
		});

		JButton btnEdtar1 = new JButton("Edt");
		btnEdtar1.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/editar.png")));
		btnEdtar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarContatoTelefonico();
			}
		});
		btnEdtar1.setBounds(756, 341, 85, 21);
		contentPane.add(btnEdtar1);

		JButton btnExcluir1 = new JButton("Exc");
		btnExcluir1.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/remove.png")));
		btnExcluir1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirContatoTelefonico();
			}
		});
		btnExcluir1.setBounds(853, 341, 85, 21);
		contentPane.add(btnExcluir1);

		JLabel lblContatoTelef = new JLabel("Contatos");
		lblContatoTelef.setForeground(new Color(92, 92, 92));
		lblContatoTelef.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContatoTelef.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/zap.png")));
		lblContatoTelef.setBounds(815, 11, 79, 31);
		contentPane.add(lblContatoTelef);

		JButton btnSincronizar = new JButton("Sinc");
		btnSincronizar.setIcon(
				new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/8726304_refresh_icon.png")));
		btnSincronizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sincronizarContTelefonico();
			}
		});
		btnSincronizar.setBounds(952, 340, 89, 23);
		contentPane.add(btnSincronizar);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(20, 0, 185, 22);
		contentPane.add(menuBar);

		JMenu mnEventos = new JMenu("Eventos");
		mnEventos.setForeground(new Color(92, 92, 92));
		mnEventos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnEventos.setMnemonic('E');
		menuBar.add(mnEventos);

		JMenuItem mntmCalendario = new JMenuItem("Calendário ");
		mntmCalendario.setForeground(new Color(92, 92, 92));
		mntmCalendario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmCalendario.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/calendario.png")));
		mntmCalendario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (telaCalendario == null) {
					telaCalendario = new TelaCalendario();
				}
				telaCalendario.setVisible(true);
				dispose();
				System.out.println("Clicou no Calendário");

			}
		});
		mnEventos.add(mntmCalendario);

		JMenuItem mntmAgendar = new JMenuItem("Agendar");
		mntmAgendar.setForeground(new Color(92, 92, 92));
		mntmAgendar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmAgendar.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/novo.png")));
		mntmAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (telaAgendar == null) {
					telaAgendar = new TelaAgendar();
				}
				telaAgendar.setVisible(true);
				dispose();
				System.out.println("Clicou no Agendar");
			}
		});
		mnEventos.add(mntmAgendar);

		JMenuItem mntmDisponibilidades = new JMenuItem("Disponbilidades");
		mntmDisponibilidades.setForeground(new Color(92, 92, 92));
		mntmDisponibilidades.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmDisponibilidades.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/availability.png")));
		mntmDisponibilidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (disponibilidade == null) {
					disponibilidade = new Disponibilidade();
				}
				disponibilidade.setVisible(true);
				dispose();
				System.out.println("Clicou na Disponibilidades");
			}
		});
		
		JMenuItem mntmAgendamentos = new JMenuItem("Agendamentos");
		mntmAgendamentos.setForeground(new Color(92, 92, 92));
		mntmAgendamentos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmAgendamentos.setIcon(new ImageIcon(CadastroPessoaView.class.getResource("/br/com/senac/view/img/tipo.png")));
		mntmAgendamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarAgendamentos();
				
			}
		});
		mnEventos.add(mntmAgendamentos);
		mnEventos.add(mntmDisponibilidades);

		JMenu mnManutencao = new JMenu("Manutençao");
		mnManutencao.setForeground(new Color(92, 92, 92));
		mnManutencao.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnManutencao.setMnemonic('M');
		menuBar.add(mnManutencao);

		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnManutencao.add(mntmNewMenuItem);

		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setForeground(new Color(92, 92, 92));
		mnAjuda.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnAjuda);
		
		
	}

	protected void listarAgendamentos() {
		if (telaAgendamentos == null) {
			telaAgendamentos = new TelaAgendamentos();
		}
		telaAgendamentos.setVisible(true);
		dispose();
		System.out.println("Clicou no Agendamentos");
		
	}

	public void sincronizarContTelefonico() {

		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar ao menos uma Pessoa para Sincronizar!",
					"Mensagem de aviso.", JOptionPane.WARNING_MESSAGE);
		} else {
			listarContatosTelefonicos();
		}

	}

	protected void excluirContatoTelefonico() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro! ", "Mensagem de aviso",
					JOptionPane.WARNING_MESSAGE);
		} else {
			Object[] options = { "Sim!", "Não" };
			int n = JOptionPane.showOptionDialog(this, // não deixa clicar na tela de baixo
					"Deseja realmente exluir o registro? ", "Confirmação", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (n == 0) {
				ContelVO contato = (ContelVO) tableModel1.getRows().get(table_1.getSelectedRow()).getElement();
				Service service = new Service();

				try {
					service.excluir(contato);

					JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Mensagem de aviso",
							JOptionPane.INFORMATION_MESSAGE);
					pesquisar();

				} catch (BOValidationException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de aviso",
							JOptionPane.WARNING_MESSAGE);

				} catch (BOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	protected void editarContatoTelefonico() {
		if (table_1.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro!", "Mensagem de aviso.",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				EditarContato edt = new EditarContato(getContatoSelecionado());
				ContelVO aux = (ContelVO) tableModel1.getRows().get(table_1.getSelectedRow()).getElement();

				edt.editar(aux);
				edt.setVisible(true);

			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocorreu um erro", "Erro.", JOptionPane.WARNING_MESSAGE);
			}
		}

	}

	protected void adicionarContatoTelefonico() {
		try {
			ContatoVO contatoSelecionado = getContatoSelecionado();
			EditarContato editarContato = new EditarContato(contatoSelecionado);
			editarContato.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de aviso.",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	protected void voltar() {
		if (telaAcessos == null) {
			telaAcessos = new TelaAcessosView();
		}
		telaAcessos.setVisible(true);
		dispose();
	}

	protected void excluirContato() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro! ", "Mensagem de aviso",
					JOptionPane.WARNING_MESSAGE);
		} else {
			Object[] options = { "Sim!", "Não" };
			int n = JOptionPane.showOptionDialog(this, // não deixa clicar na tela de baixo
					"Deseja realmente exluir o registro? ", "Confirmação", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (n == 0) {
				ContatoVO contato = (ContatoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();

				Service service = new Service();

				try {
					service.excluir(contato);

					JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Mensagem de aviso",
							JOptionPane.INFORMATION_MESSAGE);
					pesquisar();

				} catch (BOValidationException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de aviso",
							JOptionPane.WARNING_MESSAGE);

				} catch (BOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	public void listarContatosTelefonicos() {

		if (tableModel1 != null) {
			tableModel1.clearTable();
		}

		ContatoVO contatos = (ContatoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();

		if (contatos != null) {
			try {
				IService service = new Service();
				List<ContelVO> telefones = service.listarContatoTel(contatos);

				BigInteger id = null;
				String numero = null;
				String dddnum = null;
				String emails = null;

				for (ContelVO contelVO : telefones) {
					RowData rowData = new RowData();
					rowData.getValues().put(0, contelVO.getId().toString());
					rowData.getValues().put(1, contelVO.getDddnum());
					rowData.getValues().put(2, contelVO.getNumero());
					rowData.getValues().put(3, contelVO.getEmails());

					System.out.println();

					rowData.setElement(contelVO);
					tableModel1.addRow(rowData);
				}

			} catch (BOException e) {
				e.printStackTrace();
			}

		}
	}

	private void editarContato() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro!", "Mensagem de aviso.",
					JOptionPane.WARNING_MESSAGE);
		} else {

			try {

				EditarPessoa edt = new EditarPessoa();
				ContatoVO aux = (ContatoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();

				edt.editar(aux);
				edt.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocorreu um erro", "Erro.", JOptionPane.WARNING_MESSAGE);
			}
		}

	}

	public void pesquisar() {
		if (tableModel != null) {
			tableModel.clearTable();
		}
		BigInteger id = null;
		String nome = null;
		Date datnas = null;
		String observ = null;

		try {

			if (this.ftfCodigo.getText() != null && this.ftfCodigo.getText().trim().length() > 0) {
				try {
					id = new BigInteger(ftfCodigo.getText().trim());
				} catch (Exception e) {
					throw new BOValidationException("Código: erro de validação" + " valor inválido");
				}
			}

			if (this.ftfNome.getText() != null && ftfNome.getText().trim().length() > 0) {
				nome = ftfNome.getText().trim();
			}

			if (this.ftfNome.getText() != null && ftfNome.getText().trim().length() > 0) {
				nome = ftfNome.getText().trim();
			}

			System.out.println("******* Iniciando consulta de contatos *******");
			EntityManager em = HibernateUtil.getEntityManager();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ContatoVO> criteria = cb.createQuery(ContatoVO.class);

			// Clausula from
			Root<ContatoVO> contatosFrom = criteria.from(ContatoVO.class);
			criteria.select(contatosFrom);

			if (id != null) {
				criteria.where(cb.equal(contatosFrom.get("id"), id));
			}

			if (nome != null) {
				String searchTerm = "%" + nome.toLowerCase() + "%";
				criteria.where(cb.like(cb.lower(contatosFrom.get("nome")), searchTerm));
			}

			TypedQuery<ContatoVO> query = em.createQuery(criteria);
			Order contatoOrderBy = cb.asc(contatosFrom.get("nome"));
			criteria.orderBy(contatoOrderBy);

			List<ContatoVO> listaContat = query.getResultList();

			System.out.println(listaContat);
			Collections.sort(listaContat, (contato1, contato2) -> contato1.getNome().compareTo(contato2.getNome()));

			for (ContatoVO contatoVO : listaContat) {
				RowData rowData = new RowData();
				rowData.getValues().put(0, contatoVO.getId().toString());
				rowData.getValues().put(1, contatoVO.getNome());
				rowData.getValues().put(2, contatoVO.getDatnas());
				rowData.getValues().put(3, contatoVO.getObserv());

				System.out.println();

				rowData.setElement(contatoVO);
				tableModel.addRow(rowData);
			}

		} catch (BOValidationException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de validação", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de sistema", JOptionPane.ERROR_MESSAGE);
		}
	}

//
	private void adicionarContato() {
		try {
			EditarPessoa editarPessoa = new EditarPessoa();
			editarPessoa.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de aviso",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void abrirTelaAcessos() {
		if (telaAcessos == null) {
			telaAcessos = new TelaAcessosView();
		}
		telaAcessos.setVisible(true);
	}

	private ContatoVO getContatoSelecionado() {
		if (table.getSelectedRow() >= 0) {
			return (ContatoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();
		}
		return null;
	}
}
