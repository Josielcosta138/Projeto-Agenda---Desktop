package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.service.Service;
import br.com.senac.vo.EventoVO;
import br.com.senac.vo.StatusAgendamento;
import br.com.senac.vo.StatusServico;
import br.com.senac.vo.TipoServicoVO;

import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaServicos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableModel tableModel;
	private JFormattedTextField ftfValor;
	private JFormattedTextField ftfTempo;
	private JFormattedTextField ftfCodigo;
	private JComboBox comboBoxNomeServico;
	private JComboBox comboBoxListaServico;
	private List<TipoServicoVO> listagemDeTiposDeServicos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaServicos frame = new TelaServicos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaServicos() throws ParseException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
				cadastroPessoaView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaServicos.class.getResource("/br/com/senac/view/img/business.png")));
		setTitle("SERVIÇOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 511);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(192, 192, 192));
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(127, 127, 127),
				new Color(127, 127, 127), new Color(127, 127, 127), new Color(127, 127, 127)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		panel.setBounds(10, 40, 705, 137);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNomeServico = new JLabel("Nome:");
		lblNomeServico.setBounds(10, 23, 47, 14);
		panel.add(lblNomeServico);
		lblNomeServico.setForeground(new Color(100, 100, 100));

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarServico();

			}
		});
		btnSalvar.setBounds(10, 98, 107, 23);
		panel.add(btnSalvar);
		btnSalvar.setIcon(new ImageIcon(TelaServicos.class.getResource("/br/com/senac/view/img/salvar.png")));
		DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel<>(StatusServico.values());

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(272, 98, 107, 23);
		panel.add(btnExcluir);
		btnExcluir.setIcon(new ImageIcon(TelaServicos.class.getResource("/br/com/senac/view/img/remove.png")));

		JLabel lblValor = new JLabel("Valor R$:");
		lblValor.setBounds(286, 22, 61, 14);
		panel.add(lblValor);
		lblValor.setForeground(new Color(100, 100, 100));

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					editarServico();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnEditar.setBounds(142, 98, 107, 23);
		panel.add(btnEditar);
		btnEditar.setIcon(new ImageIcon(TelaServicos.class.getResource("/br/com/senac/view/img/editar.png")));

		JLabel lblTempo = new JLabel("Duração:");
		lblTempo.setBounds(552, 22, 53, 14);
		panel.add(lblTempo);
		lblTempo.setForeground(new Color(100, 100, 100));

		MaskFormatter maskTempo = new MaskFormatter("##:## min");
		ftfTempo = new JFormattedTextField(maskTempo);
		ftfTempo.setBounds(615, 19, 80, 18);
		panel.add(ftfTempo);

		MaskFormatter mask = new MaskFormatter("##.##");
		mask.setValidCharacters("0123456789");
		ftfValor = new JFormattedTextField(mask);
		ftfValor.setBounds(342, 21, 172, 18);
		ftfValor.setFocusLostBehavior(JFormattedTextField.PERSIST);
		panel.add(ftfValor);

		comboBoxNomeServico = new JComboBox();
		comboBoxNomeServico.setBounds(48, 20, 199, 20);
		panel.add(comboBoxNomeServico);
		comboBoxNomeServico.setModel(defaultComboBoxModel);
		comboBoxNomeServico.setSelectedIndex(1);

		JLabel lblCodigo = new JLabel("Cód:");
		lblCodigo.setForeground(new Color(116, 116, 116));
		lblCodigo.setBounds(10, 56, 33, 14);
		panel.add(lblCodigo);

		ftfCodigo = new JFormattedTextField();
		ftfCodigo.setEditable(false);
		ftfCodigo.setEnabled(false);
		ftfCodigo.setBounds(48, 50, 47, 20);
		panel.add(ftfCodigo);

		JLabel lblNewLabel = new JLabel("Cadastro de serviços");
		lblNewLabel.setForeground(new Color(96, 96, 96));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 21, 124, 14);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 308, 705, 145);
		contentPane.add(scrollPane);

		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Serviço");
		tableModel.addColumn("Valor");
		tableModel.addColumn("Duração");

		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new MonthColorRenderer());
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(100);
		tcm.getColumn(1).setPreferredWidth(200);
		tcm.getColumn(2).setPreferredWidth(200);
		tcm.getColumn(3).setPreferredWidth(200);

		scrollPane.setViewportView(table);

		JLabel lblNomePesq = new JLabel("Nome:");
		lblNomePesq.setForeground(new Color(96, 96, 96));
		lblNomePesq.setBounds(20, 278, 46, 14);
		contentPane.add(lblNomePesq);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarTiposDeServicos();
			}
		});
		btnPesquisar.setIcon(new ImageIcon(TelaServicos.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnPesquisar.setBounds(290, 274, 124, 23);
		contentPane.add(btnPesquisar);

		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
				cadastroPessoaView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();

			}
		});
		btnNewButton.setIcon(new ImageIcon(TelaServicos.class
				.getResource("/br/com/senac/view/img/2303132_arrow_back_direction_left_navigation_icon.png")));
		btnNewButton.setBounds(600, 274, 101, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1
				.setIcon(new ImageIcon(TelaServicos.class.getResource("/br/com/senac/view/img/dinheiroServico.png")));
		lblNewLabel_1.setBounds(130, 11, 33, 24);
		contentPane.add(lblNewLabel_1);

		DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel<>(StatusServico.values());
		defaultComboBoxModel3.addElement(null);
		comboBoxListaServico = new JComboBox();
		comboBoxListaServico.setBounds(63, 276, 202, 19);
		comboBoxListaServico.setModel(defaultComboBoxModel3);
		comboBoxListaServico.setSelectedItem(null); 
		contentPane.add(comboBoxListaServico);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(215, 215, 215));
		panel_1.setBorder(new LineBorder(new Color(110, 110, 110), 1, true));
		panel_1.setBounds(10, 249, 705, 1);
		contentPane.add(panel_1);

		JLabel lblNewLabel_2 = new JLabel("Serviços e valores");
		lblNewLabel_2.setForeground(new Color(116, 116, 116));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 224, 124, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(TelaServicos.class.getResource("/br/com/senac/view/img/relogioMoney.png")));
		lblNewLabel_3.setBounds(116, 210, 33, 32);
		contentPane.add(lblNewLabel_3);

	}

	protected void editarServico() throws ParseException {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar ao menos um agendamento para Editar!",
					"Mensagem de aviso.", JOptionPane.WARNING_MESSAGE);
		} else {
			System.out.println("Método de edição do Agendamento.");
			
			
			try {
				TelaServicos edtAgd = new TelaServicos();
				TipoServicoVO aux = (TipoServicoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();

				//edicao(aux);
				edtAgd.edicao(aux);
				edtAgd.setVisible(true);
				tableModel.fireTableDataChanged();

			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocorreu um erro", "Erro.", JOptionPane.WARNING_MESSAGE);
			}
			
			
			TelaServicos telaServico = new TelaServicos();
			telaServico.setVisible(false);
			dispose();
		}
		
	}
	
	
	public void edicao(TipoServicoVO servico) throws ParseException {
		// Exibe a data e hora atuais no campo "Data e Hora"
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				MaskFormatter mask = new MaskFormatter("##/##/#### ##:##:##");

				// Listar agendamentos
				EntityManager em = HibernateUtil.getEntityManager();
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<TipoServicoVO> criteria = cb.createQuery(TipoServicoVO.class);
				Root<TipoServicoVO> tipoServicoFrom = criteria.from(TipoServicoVO.class);
				criteria.select(tipoServicoFrom);
				TypedQuery<TipoServicoVO> query = em.createQuery(criteria);
				List<TipoServicoVO> listaDeServicos = query.getResultList();

				System.out.println("Lista Serviçoes ULTIMO --> " + listaDeServicos);
				for (TipoServicoVO servicoVO : listaDeServicos) {
					System.out.println("Status agendamento : " + servicoVO.getNome());
				}

				this.ftfCodigo.setText(servico.getId().toString());
				this.ftfValor.setText(servico.getValor().toString());
				this.ftfTempo.setText(servico.getDuracao().toString());
				
				

	}

	protected void excluir() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro! ", "Mensagem de aviso",
					JOptionPane.WARNING_MESSAGE);
		} else {
			Object[] options = { "Sim!", "Não" };
			int n = JOptionPane.showOptionDialog(this, // não deixa clicar na tela de baixo
					"Deseja realmente exluir o registro? ", "Confirmação", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (n == 0) {
				TipoServicoVO servico = (TipoServicoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();
				Service service = new Service();

				try {
					service.excluir(servico);

					JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Mensagem de aviso",
							JOptionPane.INFORMATION_MESSAGE);
					listarTiposDeServicos();

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

	@SuppressWarnings("null")
	protected void listarTiposDeServicos() {
		try {
			try {
				System.out.println("******* Iniciando consulta de serviços *******");
				EntityManager em = HibernateUtil.getEntityManager();

				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<TipoServicoVO> criteria = cb.createQuery(TipoServicoVO.class);

				// Clausula from
				Root<TipoServicoVO> tiposServicosFrom = criteria.from(TipoServicoVO.class);
				criteria.select(tiposServicosFrom);

				String statusNomeTipoServico = null;
				StatusServico statusServico = (StatusServico) comboBoxListaServico.getSelectedItem();
				
				String descricaoStatus = null;
				// Verifica se comboBoxListaServico é nulo
				if (statusServico != null) {
					statusNomeTipoServico = statusServico.name();
					criteria.where(cb.equal(tiposServicosFrom.get("nome"), statusNomeTipoServico));
				}
				
				
				TypedQuery<TipoServicoVO> query = em.createQuery(criteria);
				listagemDeTiposDeServicos = query.getResultList();

				tableModel.clearTable(); 

				for (TipoServicoVO tServicoVO : listagemDeTiposDeServicos) {
					if (tServicoVO.getId() != null) {
						int duracaoInt = tServicoVO.getDuracao();
						String duracaoString = String.format("%04d", duracaoInt);
						// Obter os dois primeiros dígitos (horas) e os dois últimos dígitos (minutos)
						String horas = duracaoString.substring(0, 2);
						String minutos = duracaoString.substring(2, 4);
						String duracaoFormatada = horas + ":" + minutos + " min";
						RowData rowData = new RowData();
						
						rowData.getValues().put(0, tServicoVO.getId().toString());
						rowData.getValues().put(1, tServicoVO.getNome());
						rowData.getValues().put(2, tServicoVO.getValor().toString() + " R$");
						rowData.getValues().put(3, duracaoFormatada);

						rowData.setElement(tServicoVO);
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

	public String formatarDuracao(int minutos) {
		int horas = minutos / 1; // Divida por 60 para obter as horas
		int minutosRestantes = minutos % 1;

		// Formatar para o estilo HH:mm
		return String.format("%02d:%02d min", horas, minutosRestantes);
	}

	protected void salvarServico() {
		System.out.println("*****************Iniciando Salvar*****************");

		try {

			Service service = new Service();
			TipoServicoVO tipoServicoVO = new TipoServicoVO();

			String codigo = ftfCodigo.getText().trim();
			String valor = ftfValor.getText().trim();
			String tempoDuracao = ftfTempo.getText().trim();

			if (codigo != null && codigo.length() > 0) {
				tipoServicoVO.setId(new BigInteger(codigo));
				tipoServicoVO = service.buscarTipoServicoPorId(tipoServicoVO);
			}

			String statusNomeTipoServico = null;
			StatusServico statusServico = (StatusServico) comboBoxNomeServico.getSelectedItem();
			if (statusServico != null) {
				statusNomeTipoServico = statusServico.name();
			}

			String pattern = "dd/MM/yyyy HH:mm:ss";
			DateFormat dateFormat = new SimpleDateFormat(pattern);

			if (!tempoDuracao.isEmpty()) {

				String tempoSemMascara = tempoDuracao.replaceAll("[^0-9]", "");
				Integer tempoDuracaoConv = Integer.parseInt(tempoSemMascara);
				tipoServicoVO.setDuracao(tempoDuracaoConv);
			} else {
				JOptionPane.showMessageDialog(this, "Tempo de duração de corte. Vazio!", "Erro",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (!valor.isEmpty()) {
				try {
					BigDecimal valorConv = new BigDecimal(valor);
					tipoServicoVO.setValor(valorConv);
				} catch (NumberFormatException | ArithmeticException e) {
					System.err.println("Erro ao converter String para BigDecimal: " + e.getMessage());
				}
			}

			if (statusNomeTipoServico != null) {
				tipoServicoVO.setNome(statusNomeTipoServico);
			}

			service.salvar(tipoServicoVO);

			JOptionPane.showMessageDialog(null, "Cadastro salvo com sucesso!");
			setVisible(true);

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
}
