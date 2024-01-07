package br.com.senac.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.exception.BOValidationException;
import br.com.senac.service.IService;
import br.com.senac.service.Service;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.ContelVO;
import br.com.senac.vo.EventoVO;
import br.com.senac.vo.StatusAgendamento;
import br.com.senac.vo.StatusServico;
import br.com.senac.vo.TipoServicoVO;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;

public class TelaAgendar extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDataHora;
	private JTextField tftHoraAtual;
	private CadastroPessoaView cadastroPessoaView;
	private JComboBox comboBoxStatus;
	private JComboBox comboBoxStatusServico;
	private JFormattedTextField ftfNome;
	private JFormattedTextField ftfEmail;
	private JFormattedTextField ftfQntParticipantes;
	private JFormattedTextField ftfLocal;
	private JFormattedTextField ftfHoraInicio;
	private JFormattedTextField ftfHoraFim;
	private JFormattedTextField ftfCodigo;
	private String novaDataHoraInicioStr;
	private String novaDataHoraFimStr;
	private JList<String> nomeList;
	private JList<String> emailList;
	private JList<String> listaTelefone;
	private TableModel tableModel;
	private JTable table;
	private EventoVO agendamentoAtual;
	private List<EventoVO> listaAgendamentos;
	private JFormattedTextField ftfDD;
	private JFormattedTextField ftfTelefone;
	private JFormattedTextField ftfValor;
	private List<TipoServicoVO> listagemDeTiposDeServicos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAgendar frame = new TelaAgendar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaAgendar() throws ParseException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
				cadastroPessoaView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaAgendar.class.getResource("/br/com/senac/view/img/LogoSTYLEMANAGER black.png")));
		setTitle("AGENDAMENTO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1055, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Exibe a data e hora atuais no campo "Data e Hora"
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		MaskFormatter mask = new MaskFormatter("##/##/#### ##:##:##");

		// Listar agendamentos
		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EventoVO> criteria = cb.createQuery(EventoVO.class);
		Root<EventoVO> agendamentosFrom = criteria.from(EventoVO.class);
		criteria.select(agendamentosFrom);
		TypedQuery<EventoVO> query = em.createQuery(criteria);
		List<EventoVO> listaAgendamentos = query.getResultList();

		System.out.println("Lista agendamentos ULTIMO --> " + listaAgendamentos);
		for (EventoVO eventoVO : listaAgendamentos) {
			System.out.println("ULTIMO HORARIO: " + eventoVO.getDataHoraFim());

		}

		if (!listaAgendamentos.isEmpty()) {
			// Ordena a lista de eventos pela dataHoraFim em ordem decrescente
			Collections.sort(listaAgendamentos, Comparator.comparing(EventoVO::getDataHoraFim).reversed());

			EventoVO ultimoEvento = listaAgendamentos.get(0);
			Date dataHoraFimUltimoEvento = ultimoEvento.getDataHoraFim();

			// HORAFIM+31
			Calendar cal = Calendar.getInstance();
			cal.setTime(dataHoraFimUltimoEvento);
			cal.add(Calendar.MINUTE, 30);
			Date novaDataHoraInicio = cal.getTime();
			novaDataHoraInicioStr = sdf.format(novaDataHoraInicio);

			// HORAFIM+31
			cal.setTime(novaDataHoraInicio);
			cal.add(Calendar.MINUTE, 30);
			Date novaDataHoraFim = cal.getTime();
			novaDataHoraFimStr = sdf.format(novaDataHoraFim);

		}

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JButton btnAgendar = new JButton("Agendar");
		btnAgendar.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/edit.png")));
		btnAgendar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Teste agendar");
				agendar();
			}
		});
		btnAgendar.setBounds(10, 429, 114, 28);
		contentPane.add(btnAgendar);

		tftHoraAtual = new JTextField();
		tftHoraAtual.setEditable(false);
		tftHoraAtual.setBounds(896, 14, 130, 20);
		contentPane.add(tftHoraAtual);
		tftHoraAtual.setColumns(10);
		tftHoraAtual.setText(sdf.format(new Date()));

		DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel<>(StatusAgendamento.values());

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarVoltar();
			}
		});
		btnVoltar.setIcon(new ImageIcon(TelaAgendar.class
				.getResource("/br/com/senac/view/img/2303132_arrow_back_direction_left_navigation_icon.png")));
		btnVoltar.setBounds(912, 429, 114, 28);
		contentPane.add(btnVoltar);

		JLabel lblNewLabel = new JLabel("Agendamento de horários");
		lblNewLabel.setForeground(new Color(103, 103, 103));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 14, 177, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/availability.png")));
		lblNewLabel_1.setBounds(179, 11, 24, 22);
		contentPane.add(lblNewLabel_1);
		DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel<>(StatusServico.values());

		MaskFormatter maskDD = new MaskFormatter("(##)");

		MaskFormatter maskTel = new MaskFormatter("#####-####");

		JPanel panel = new JPanel();
		panel.setForeground(new Color(192, 192, 192));
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setBounds(10, 72, 359, 332);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblPessoa = new JLabel("Nome:");
		lblPessoa.setBounds(10, 51, 86, 14);
		panel.add(lblPessoa);
		lblPessoa.setForeground(new Color(103, 103, 103));

		JLabel lblEmailVincPessoa = new JLabel("E-mail:");
		lblEmailVincPessoa.setBounds(10, 83, 86, 14);
		panel.add(lblEmailVincPessoa);
		lblEmailVincPessoa.setForeground(new Color(103, 103, 103));

		JLabel lblDD = new JLabel("DD:");
		lblDD.setBounds(10, 117, 46, 14);
		panel.add(lblDD);
		lblDD.setForeground(new Color(120, 120, 120)); 

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 152, 62, 14);
		panel.add(lblTelefone);
		lblTelefone.setForeground(new Color(106, 106, 106));

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 182, 46, 14);
		panel.add(lblStatus);
		lblStatus.setForeground(new Color(103, 103, 103));

		JLabel lblNewLabel_2 = new JLabel("Local:");
		lblNewLabel_2.setBounds(10, 216, 46, 14);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(103, 103, 103));

		JLabel lblCodigo = new JLabel("Cód:");
		lblCodigo.setBounds(10, 14, 46, 14);
		panel.add(lblCodigo);
		lblCodigo.setEnabled(false);

		ftfCodigo = new JFormattedTextField();
		ftfCodigo.setBounds(82, 11, 83, 20);
		panel.add(ftfCodigo);
		ftfCodigo.setEditable(false);
		ftfCodigo.setEnabled(false);

		ftfLocal = new JFormattedTextField();
		ftfLocal.setBounds(82, 213, 183, 20);
		panel.add(ftfLocal);

		// Lista de telefones
		listaTelefone = new JList();
		listaTelefone.setBounds(103, 163, 130, 100);
		panel.add(listaTelefone);

		comboBoxStatus = new JComboBox();
		comboBoxStatus.setBounds(82, 179, 183, 20);
		panel.add(comboBoxStatus);
		comboBoxStatus.setModel(defaultComboBoxModel);
		comboBoxStatus.setSelectedIndex(1);
		ftfTelefone = new JFormattedTextField(maskTel);
		ftfTelefone.setBounds(82, 148, 183, 20);
		panel.add(ftfTelefone);

		// Lista de e-mail na busca de campos
		emailList = new JList<>();
		emailList.setBounds(103, 96, 130, 100);
		panel.add(emailList);

		// Lista de Nomes na busca de campos
		nomeList = new JList<>();
		nomeList.setBounds(102, 49, 130, 77);
		panel.add(nomeList);

		ftfEmail = new JFormattedTextField();
		ftfEmail.setBounds(82, 80, 184, 20);
		panel.add(ftfEmail);

		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(272, 83, 24, 17);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarPorNomeEmail();

			}
		});
		btnNewButton.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/pesquisar.png")));

		JButton btnPesquisarNome = new JButton("");
		btnPesquisarNome.setBounds(272, 48, 24, 17);
		panel.add(btnPesquisarNome);
		btnPesquisarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pesquisarNome();
				} catch (BOValidationException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnPesquisarNome.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/pesquisar.png")));

		ftfNome = new JFormattedTextField();
		ftfNome.setBounds(82, 48, 183, 20);
		panel.add(ftfNome);
		ftfDD = new JFormattedTextField(maskDD);
		ftfDD.setBounds(82, 117, 38, 20);
		panel.add(ftfDD);

		JButton btnTelefones = new JButton("");
		btnTelefones.setBounds(272, 149, 24, 17);
		panel.add(btnTelefones);
		btnTelefones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarPorTelefone();

			}
		});
		btnTelefones.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/pesquisar.png")));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_1.setBounds(434, 72, 349, 332);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblTipoServico = new JLabel("Tipo de serviço:");
		lblTipoServico.setBounds(10, 26, 100, 20);
		panel_1.add(lblTipoServico);
		lblTipoServico.setForeground(new Color(103, 103, 103));

		JLabel lblDataHoraInicio = new JLabel("Data e Hora Inicio:");
		lblDataHoraInicio.setBounds(10, 56, 112, 20);
		panel_1.add(lblDataHoraInicio);
		lblDataHoraInicio.setForeground(new Color(103, 103, 103));

		JLabel lblDataHoraFim = new JLabel("Data e Hora Fim:");
		lblDataHoraFim.setBounds(10, 90, 100, 14);
		panel_1.add(lblDataHoraFim);
		lblDataHoraFim.setForeground(new Color(103, 103, 103));

		JLabel lblParticipantes = new JLabel("N° Participantes:");
		lblParticipantes.setBounds(10, 118, 100, 14);
		panel_1.add(lblParticipantes);
		lblParticipantes.setForeground(new Color(103, 103, 103));

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(10, 145, 46, 14);
		panel_1.add(lblValor);
		lblValor.setForeground(new Color(95, 95, 95));

		
		ftfValor = new JFormattedTextField();
		ftfValor.setBounds(120, 142, 83, 20);
		panel_1.add(ftfValor);
		

		ftfQntParticipantes = new JFormattedTextField();
		ftfQntParticipantes.setBounds(120, 115, 83, 20);
		panel_1.add(ftfQntParticipantes);

		ftfHoraFim = new JFormattedTextField(mask);
		ftfHoraFim.setBounds(120, 87, 150, 20);
		panel_1.add(ftfHoraFim);
		ftfHoraFim.setText(novaDataHoraFimStr);

		comboBoxStatusServico = new JComboBox();
		comboBoxStatusServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pesquisarValorPorServico();
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		comboBoxStatusServico.setBounds(120, 25, 150, 21);
		panel_1.add(comboBoxStatusServico);
		comboBoxStatusServico.setModel(defaultComboBoxModel2);
		comboBoxStatusServico.setSelectedIndex(1);
		pesquisarValorPorServico();

		ftfHoraInicio = new JFormattedTextField(mask);
		ftfHoraInicio.setBounds(120, 56, 150, 20);
		panel_1.add(ftfHoraInicio);
		ftfHoraInicio.setColumns(10);
		ftfHoraInicio.setText(novaDataHoraInicioStr);

		JButton btnAtualizarHorarios = new JButton("");
		btnAtualizarHorarios.setBounds(280, 56, 24, 20);
		panel_1.add(btnAtualizarHorarios);
		btnAtualizarHorarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					atualizarHorarios();

				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAtualizarHorarios
				.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/AttHoras.png")));

		JLabel lblNewLabel_4 = new JLabel("Cliente");
		lblNewLabel_4.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/user222.png")));
		lblNewLabel_4.setForeground(new Color(100, 100, 100));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(10, 52, 114, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Serviço");
		lblNewLabel_5.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/tesoura2.png")));
		lblNewLabel_5.setForeground(new Color(100, 100, 100));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(434, 47, 130, 20);
		contentPane.add(lblNewLabel_5);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_2.setBounds(10, 507, 988, 2);
		contentPane.add(panel_2);

		JLabel lblNewLabel_6 = new JLabel("Último agendamento");
		lblNewLabel_6.setForeground(new Color(100, 100, 100));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(10, 482, 177, 14);
		contentPane.add(lblNewLabel_6);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 532, 1016, 97);
		contentPane.add(scrollPane);
		
		
		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Local");
		tableModel.addColumn("Data hora inicio");
		tableModel.addColumn("Data hora fim");
		tableModel.addColumn("Status");
		tableModel.addColumn("Tipo serviço");//5
		tableModel.addColumn("Valor");
		tableModel.addColumn("Cliente");
		tableModel.addColumn("DD");
		tableModel.addColumn("Número");
		tableModel.addColumn("E-mail");
		
		
		
		table_1 = new JTable(tableModel);
		table_1.setDefaultRenderer(Object.class, new MonthColorRenderer());
		table_1.setAutoscrolls(true);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm = table_1.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(40);
		tcm.getColumn(1).setPreferredWidth(80);
		tcm.getColumn(2).setPreferredWidth(120);
		tcm.getColumn(3).setPreferredWidth(120);
		tcm.getColumn(4).setPreferredWidth(180);
		tcm.getColumn(5).setPreferredWidth(180);
		tcm.getColumn(6).setPreferredWidth(100);
		tcm.getColumn(7).setPreferredWidth(110);
		tcm.getColumn(8).setPreferredWidth(60);
		tcm.getColumn(9).setPreferredWidth(80);
		tcm.getColumn(10).setPreferredWidth(110);
		
		
		scrollPane.setViewportView(table_1);
		listarAgendamentos();
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/foto2Agenda.png")));
		lblNewLabel_3.setBounds(803, 72, 223, 332);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/listaAgdOk.png")));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7.setBounds(997, 493, 29, 28);
		contentPane.add(lblNewLabel_7);
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

	}

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private JTable table_1;

	public void atualizarHorarios() throws ParseException {
		// Listar agendamento
		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EventoVO> criteria = cb.createQuery(EventoVO.class);
		Root<EventoVO> agendamentosFrom = criteria.from(EventoVO.class);
		criteria.select(agendamentosFrom);
		TypedQuery<EventoVO> query = em.createQuery(criteria);
		listaAgendamentos = query.getResultList();

		if (!listaAgendamentos.isEmpty()) {
			// Ordena a lista de eventos pela dataHoraFim em ordem decrescente
			Collections.sort(listaAgendamentos, Comparator.comparing(EventoVO::getDataHoraFim).reversed());

			EventoVO ultimoEvento = listaAgendamentos.get(0);
			Date dataHoraFimUltimoEvento = ultimoEvento.getDataHoraFim();

			// HORAFIM+31
			Calendar cal = Calendar.getInstance();
			cal.setTime(dataHoraFimUltimoEvento);
			cal.add(Calendar.MINUTE, 30);

			// Atualiza o texto dos componentes existentes em vez de criar novos
			Date novaDataHoraInicio = cal.getTime();
			ftfHoraInicio.setText(sdf.format(novaDataHoraInicio));

			cal.add(Calendar.MINUTE, 30);
			Date novaDataHoraFim = cal.getTime();
			ftfHoraFim.setText(sdf.format(novaDataHoraFim));
		}
	}

	protected void pesquisarPorNomeEmail() {

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

	public void pesquisarNome() throws BOValidationException {
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

	protected void agendar() {

		try {

			Service service = new Service();
			EventoVO eventoVO = new EventoVO();

			String codigo = ftfCodigo.getText().trim();
			String nome = ftfNome.getText().trim();
			String email = ftfEmail.getText().trim();
			String qntParticipantes = ftfQntParticipantes.getText().trim();
			String local = ftfLocal.getText().trim();
			String dataHoraInicio = ftfHoraInicio.getText().trim();
			String dataHoraFim = ftfHoraFim.getText().trim();
			String dd = ftfDD.getText().trim();
			String numero = ftfTelefone.getText().trim();
			String telefone = ftfTelefone.getText().trim();
			String valor = ftfValor.getText().trim();

			/////////
			List<EventoVO> eventosAgendados = buscarEventosPorPeriodo(eventoVO.getDataHoraInicio(),
					eventoVO.getDataHoraFim());

			// Verifica se o intervalo de tempo está disponível
			if (!intervaloDisponivel(dataHoraInicio, dataHoraFim)) {
				JOptionPane.showMessageDialog(this,
						"Ops:x \nO horário especificado já está ocupado! "
								+ "\nConsulte o módulo Disponibilidade de horários!" + "\nOu atualize o horário.  ",
						"Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (codigo != null && codigo.length() > 0) {
				eventoVO.setId(new BigInteger(codigo));
				eventoVO = service.buscarContatosPorId(eventoVO);
			}

			String statusTipoServico = null;
			StatusServico statusServico = (StatusServico) comboBoxStatusServico.getSelectedItem();
			if (statusServico != null) {
				statusTipoServico = statusServico.name();
			}

			String status = null;
			StatusAgendamento statusEnum = (StatusAgendamento) comboBoxStatus.getSelectedItem();
			if (statusEnum != null) {
				status = statusEnum.name();
			}
			
			

			String pattern = "dd/MM/yyyy HH:mm:ss";
			DateFormat dateFormat = new SimpleDateFormat(pattern);

			try {
				if (!dataHoraInicio.isEmpty()) {
					Date date = dateFormat.parse(dataHoraInicio + " 00:00:00");
					eventoVO.setDataHoraInicio(date);
				} else {
					JOptionPane.showMessageDialog(this, "Data de nascimento vazia!", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!dataHoraFim.isEmpty()) {
					Date date2 = dateFormat.parse(dataHoraFim + " 00:00:00");
					eventoVO.setDataHoraFim(date2);
				} else {
					JOptionPane.showMessageDialog(this, "Data de nascimento vazia!", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}

			} catch (ParseException e) {
				// Trata a exceção de formato de data inválido
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Formato de data inválido! Formato correto dd/MM/yyyy HH:mm:ss",
						"Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (status != null) {
				eventoVO.setStatus(status);
			}

			if (statusTipoServico != null) {
				eventoVO.setTipoServico(statusTipoServico);
			}

			 
 
			if (valor != null) {
				valor = valor.replace(",", ".");
				eventoVO.setValor(new BigDecimal(valor));
			}

			eventoVO.setNomeCliente(nome);
			eventoVO.setEmail(email);
			BigInteger participantes = new BigInteger(qntParticipantes);
			eventoVO.setParticipantes(participantes);
			eventoVO.setLocal(local);
			eventoVO.setDd(dd);
			eventoVO.setNumero(telefone);

			service.salvar(eventoVO);

			JOptionPane.showMessageDialog(null, "Cadastro salvo com sucesso!");
			
			try {
				listarAgendamentos();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
			
			
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
	
	public void listarAgendamentos() {
		if (tableModel != null) {
			tableModel.clearTable();
		}


		try {

			try {

				System.out.println("******* Iniciando consulta de agendamentos *******");
		        EntityManager em = HibernateUtil.getEntityManager();
		        CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<EventoVO> criteria = cb.createQuery(EventoVO.class);

				Root<EventoVO> agendamentosFrom = criteria.from(EventoVO.class);
		        criteria.select(agendamentosFrom);

		        // Adicionando ORDER BY para ordenar por ID em ordem decrescente
		        criteria.orderBy(cb.desc(agendamentosFrom.get("id")));
		        TypedQuery<EventoVO> query = em.createQuery(criteria);

		        query.setMaxResults(1);
				listaAgendamentos = query.getResultList();
				

				for (EventoVO eventoVO : listaAgendamentos) {

					if (eventoVO.getId() != null) {
						System.out.println("Lista agendamentos --> " + listaAgendamentos);
						RowData rowData = new RowData();
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					
						rowData.getValues().put(0, eventoVO.getId().toString());
						rowData.getValues().put(1, eventoVO.getLocal());
						rowData.getValues().put(2, dateFormat.format(eventoVO.getDataHoraInicio()));
				        rowData.getValues().put(3, dateFormat.format(eventoVO.getDataHoraFim()));
						rowData.getValues().put(4, eventoVO.getStatus());
						rowData.getValues().put(5, eventoVO.getTipoServico());
						rowData.getValues().put(6, eventoVO.getValor() + " R$");
						rowData.getValues().put(7, eventoVO.getNomeCliente());
						rowData.getValues().put(8, eventoVO.getDd());
						rowData.getValues().put(9, eventoVO.getNumero());
						rowData.getValues().put(10, eventoVO.getEmail());

						rowData.setElement(eventoVO);
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

	public EventoVO getAgendamentoSelecionado() {
		if (table.getSelectedRow() >= 0) {
			return (EventoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();
		}
		return null;
	}

	public void editar(EventoVO agendamento) throws ParseException {

		// Exibe a data e hora atuais no campo "Data e Hora"
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		MaskFormatter mask = new MaskFormatter("##/##/#### ##:##:##");

		// Listar agendamentos
		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EventoVO> criteria = cb.createQuery(EventoVO.class);
		Root<EventoVO> agendamentosFrom = criteria.from(EventoVO.class);
		criteria.select(agendamentosFrom);
		TypedQuery<EventoVO> query = em.createQuery(criteria);
		List<EventoVO> listaAgendamentos = query.getResultList();

		System.out.println("Lista agendamentos ULTIMO --> " + listaAgendamentos);
		for (EventoVO eventoVO : listaAgendamentos) {
			System.out.println("Status agendamento : " + eventoVO.getStatus());
		}

		this.ftfCodigo.setText(agendamento.getId().toString());
		this.ftfNome.setText(agendamento.getNomeCliente().toString());
		this.ftfEmail.setText(agendamento.getEmail().toString());

		BigInteger participantes = agendamento.getParticipantes();
		String participantesString = participantes.toString();
		JTextField ftfQntParticipantes = new JTextField();
		this.ftfQntParticipantes.setText(participantesString);

		this.ftfLocal.setText(agendamento.getLocal().toString());
		this.ftfHoraInicio.setText(sdf.format(agendamento.getDataHoraInicio()).toString());
		this.ftfHoraFim.setText(sdf.format(agendamento.getDataHoraFim()).toString());

		if (agendamento.getStatus() != null) {

			String statusAgendamentoString = agendamento.getStatus();

			if ("AGENDADO".equalsIgnoreCase(statusAgendamentoString)) {
				comboBoxStatus.setSelectedItem(StatusAgendamento.AGENDADO);

				StatusAgendamento statusEnum = (StatusAgendamento) comboBoxStatus.getSelectedItem();
				if (statusEnum != null) {
					agendamento.setStatus(statusEnum.name());
				}

			} else if ("AGUARDANDO_CONFIRMACAO".equalsIgnoreCase(statusAgendamentoString)) {
				comboBoxStatus.setSelectedItem(StatusAgendamento.AGUARDANDO_CONFIRMACAO);

				StatusAgendamento statusEnum = (StatusAgendamento) comboBoxStatus.getSelectedItem();
				if (statusEnum != null) {
					agendamento.setStatus(statusEnum.name());
				}

			} else {
				comboBoxStatus.setSelectedItem(StatusAgendamento.CANCELADO);

				StatusAgendamento statusEnum = (StatusAgendamento) comboBoxStatus.getSelectedItem();
				if (statusEnum != null) {
					agendamento.setStatus(statusEnum.name());
				}

			}

		}

		if (agendamento.getTipoServico() != null) {

			String statusTipoServicoString = agendamento.getTipoServico();

			if ("CABELO".equalsIgnoreCase(statusTipoServicoString)) {
				comboBoxStatusServico.setSelectedItem(StatusServico.CABELO);

				StatusServico statusEnum = (StatusServico) comboBoxStatusServico.getSelectedItem();
				if (statusEnum != null) {
					agendamento.setTipoServico(statusEnum.name());
				}

			} else if ("CABELO_BARBA".equalsIgnoreCase(statusTipoServicoString)) {
				comboBoxStatusServico.setSelectedItem(StatusServico.CABELO_BARBA);

				StatusServico statusEnum = (StatusServico) comboBoxStatusServico.getSelectedItem();
				if (statusEnum != null) {
					agendamento.setTipoServico(statusEnum.name());
				}

			} else {
				comboBoxStatusServico.setSelectedItem(StatusServico.BARBA);

				StatusServico statusEnum = (StatusServico) comboBoxStatusServico.getSelectedItem();
				if (statusEnum != null) {
					agendamento.setTipoServico(statusEnum.name());
				}

			}

		}

		TelaAgendamentos agendamentos = new TelaAgendamentos();
		agendamentos.setVisible(false);
		dispose();

	}

	protected void cancelarVoltar() {
		if (cadastroPessoaView == null) {
			cadastroPessoaView = new CadastroPessoaView();
		}
		cadastroPessoaView.setVisible(true);
		dispose();

	}

	private List<EventoVO> buscarEventosPorPeriodo(Date dataHoraInicio, Date dataHoraFim) {
		try {
			EntityManager em = HibernateUtil.getEntityManager();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<EventoVO> criteria = cb.createQuery(EventoVO.class);

			Root<EventoVO> eventosFrom = criteria.from(EventoVO.class);
			criteria.select(eventosFrom);

			// Adicione as condições para verificar se existe algum evento no período
			criteria.where(cb.or(
					cb.and(cb.lessThanOrEqualTo(eventosFrom.get("dataHoraInicio"), dataHoraInicio),
							cb.greaterThanOrEqualTo(eventosFrom.get("dataHoraFim"), dataHoraInicio)),
					cb.and(cb.lessThanOrEqualTo(eventosFrom.get("dataHoraInicio"), dataHoraFim),
							cb.greaterThanOrEqualTo(eventosFrom.get("dataHoraFim"), dataHoraFim))));

			TypedQuery<EventoVO> query = em.createQuery(criteria);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace(); // Tratamento adequado para o erro no seu ambiente
			System.out.println("Lista vazia horas");
			return Collections.emptyList(); // Retorna uma lista vazia em caso de erro
		} finally {
			System.out.println("Finally");
		}
	}

	public void pesquisarValorPorServico() throws ParseException {
		 try {
		        // Obter o item selecionado no comboBoxStatusServico
		        StatusServico statusServico = (StatusServico) comboBoxStatusServico.getSelectedItem();

		        if (statusServico != null) {
		        	
		        	
		        	EntityManager em = HibernateUtil.getEntityManager();

					CriteriaBuilder cb = em.getCriteriaBuilder();
					CriteriaQuery<TipoServicoVO> criteria = cb.createQuery(TipoServicoVO.class);

					// Clausula from
					Root<TipoServicoVO> tiposServicosFrom = criteria.from(TipoServicoVO.class);
					criteria.select(tiposServicosFrom);
		        	TypedQuery<TipoServicoVO> query = em.createQuery(criteria);
					listagemDeTiposDeServicos = query.getResultList();
		        	
		            // Encontrar o TipoServicoVO correspondente ao statusServico selecionado
		            TipoServicoVO tipoServico = null;
		            for (TipoServicoVO tServicoVO : listagemDeTiposDeServicos) {
		                if (tServicoVO.getNome().equals(statusServico.name())) {
		                    tipoServico = tServicoVO;
		                    break;
		                }
		            }

		            // Atualizar o campo ftfValor com o  valor encontrado
		            if (tipoServico != null) {
		            	
		            	BigDecimal valorBigDecimal = tipoServico.getValor();
		            	double valor = valorBigDecimal.doubleValue();
		            	 
		                String valorFormatado = String.format("%.2f", valor);
		               // ftfValor.setValue(Double.parseDouble(valorFormatado));
		                ftfValor.setValue(valorFormatado);
		                
		            } else {
		                // Limpar o campo se o tipo de serviço não for encontrado
		                ftfValor.setValue(null);
		            }
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(this, "Erro ao pesquisar valor por serviço", "Erro", JOptionPane.ERROR_MESSAGE);
		    }
	}

	private boolean intervaloDisponivel(String dataHoraInicioStr, String dataHoraFimStr) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			// Convertendo as strings para objetos Date
			Date dataHoraInicio = dateFormat.parse(dataHoraInicioStr + " 00:00:00");
			Date dataHoraFim = dateFormat.parse(dataHoraFimStr + " 00:00:00");

			// Obtendo a lista de eventos já agendados para o período especificado
			List<EventoVO> eventosAgendados = buscarEventosPorPeriodo(dataHoraInicio, dataHoraFim);

			// Verificar se já existem eventos agendados para o período
			return eventosAgendados.isEmpty();
		} catch (ParseException e) {
			e.printStackTrace(); // Tratamento adequado para o erro no seu ambiente
			return false;
		} finally {
			System.out.println("Finally");
		}
	}
}
