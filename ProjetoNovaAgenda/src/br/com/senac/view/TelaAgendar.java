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

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		setBounds(100, 100, 966, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Lista de Nomes na busca de campos
		nomeList = new JList<>();
		nomeList.setBounds(102, 67, 130, 100);
		contentPane.add(nomeList);
		nomeList.setVisible(false);

		// Lista de e-mail na busca de campos
		emailList = new JList<>();
		emailList.setBounds(102, 108, 130, 100);
		contentPane.add(emailList);
		emailList.setVisible(false);

		// Lista de telefones
		listaTelefone = new JList();
		listaTelefone.setBounds(102, 178, 130, 100);
		contentPane.add(listaTelefone);
		listaTelefone.setVisible(false);

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

		JLabel lblTipoServico = new JLabel("Tipo de serviço:");
		lblTipoServico.setForeground(new Color(103, 103, 103));
		lblTipoServico.setBounds(369, 60, 100, 20);
		contentPane.add(lblTipoServico);

		JLabel lblDataHoraInicio = new JLabel("Data e Hora Inicio:");
		lblDataHoraInicio.setForeground(new Color(103, 103, 103));
		lblDataHoraInicio.setBounds(369, 90, 112, 20);
		contentPane.add(lblDataHoraInicio);

		ftfHoraInicio = new JFormattedTextField(mask);
		ftfHoraInicio.setBounds(479, 90, 150, 20);
		contentPane.add(ftfHoraInicio);
		ftfHoraInicio.setColumns(10);
		ftfHoraInicio.setText(novaDataHoraInicioStr);

		JButton btnAgendar = new JButton("Agendar");
		btnAgendar.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/edit.png")));
		btnAgendar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Teste agendar");
				agendar();
			}
		});
		btnAgendar.setBounds(24, 405, 114, 28);
		contentPane.add(btnAgendar);

		tftHoraAtual = new JTextField();
		tftHoraAtual.setEditable(false);
		tftHoraAtual.setBounds(763, 11, 130, 20);
		contentPane.add(tftHoraAtual);
		tftHoraAtual.setColumns(10);
		tftHoraAtual.setText(sdf.format(new Date()));

		JLabel lblDataHoraFim = new JLabel("Data e Hora Fim:");
		lblDataHoraFim.setForeground(new Color(103, 103, 103));
		lblDataHoraFim.setBounds(369, 124, 100, 14);
		contentPane.add(lblDataHoraFim);

		ftfHoraFim = new JFormattedTextField(mask);
		ftfHoraFim.setBounds(479, 121, 150, 20);
		contentPane.add(ftfHoraFim);
		ftfHoraFim.setText(novaDataHoraFimStr);

		JLabel lblParticipantes = new JLabel("N° Participantes:");
		lblParticipantes.setForeground(new Color(103, 103, 103));
		lblParticipantes.setBounds(369, 152, 100, 14);
		contentPane.add(lblParticipantes);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setForeground(new Color(103, 103, 103));
		lblStatus.setBounds(10, 194, 46, 14);
		contentPane.add(lblStatus);

		comboBoxStatus = new JComboBox();
		comboBoxStatus.setBounds(82, 191, 183, 20);

		DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel<>(StatusAgendamento.values());
		comboBoxStatus.setModel(defaultComboBoxModel);
		comboBoxStatus.setSelectedIndex(1);

		contentPane.add(comboBoxStatus);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarVoltar();
			}
		});
		btnCancelar.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/cancel.png")));
		btnCancelar.setBounds(788, 405, 127, 28);
		contentPane.add(btnCancelar);

		ftfQntParticipantes = new JFormattedTextField();
		ftfQntParticipantes.setBounds(479, 149, 83, 20);
		contentPane.add(ftfQntParticipantes);

		JLabel lblPessoa = new JLabel("Nome:");
		lblPessoa.setForeground(new Color(103, 103, 103));
		lblPessoa.setBounds(10, 63, 86, 14);
		contentPane.add(lblPessoa);

		JLabel lblNewLabel = new JLabel("Agendamento de horários");
		lblNewLabel.setForeground(new Color(103, 103, 103));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 14, 177, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/availability.png")));
		lblNewLabel_1.setBounds(179, 11, 24, 22);
		contentPane.add(lblNewLabel_1);

		JLabel lblEmailVincPessoa = new JLabel("E-mail:");
		lblEmailVincPessoa.setForeground(new Color(103, 103, 103));
		lblEmailVincPessoa.setBounds(10, 95, 86, 14);
		contentPane.add(lblEmailVincPessoa);

		JLabel lblNewLabel_2 = new JLabel("Local:");
		lblNewLabel_2.setForeground(new Color(103, 103, 103));
		lblNewLabel_2.setBounds(10, 228, 46, 14);
		contentPane.add(lblNewLabel_2);

		ftfLocal = new JFormattedTextField();
		ftfLocal.setBounds(82, 225, 183, 20);
		contentPane.add(ftfLocal);

		comboBoxStatusServico = new JComboBox();
		comboBoxStatusServico.setBounds(479, 59, 150, 21);
		DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel<>(StatusServico.values());
		comboBoxStatusServico.setModel(defaultComboBoxModel2);
		comboBoxStatusServico.setSelectedIndex(1);

		contentPane.add(comboBoxStatusServico);

		ftfNome = new JFormattedTextField();
		ftfNome.setBounds(82, 60, 183, 20);
		contentPane.add(ftfNome);

		JButton btnPesquisarNome = new JButton("");
		btnPesquisarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pesquisarNome();
				} catch (BOValidationException e1) {
					e1.printStackTrace();
				}
			}
		});

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
		
		 
	
		
		btnPesquisarNome.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnPesquisarNome.setBounds(272, 60, 24, 17);
		contentPane.add(btnPesquisarNome);

		ftfEmail = new JFormattedTextField();
		ftfEmail.setBounds(82, 92, 184, 20);
		contentPane.add(ftfEmail);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarPorNomeEmail();

			}
		});
		btnNewButton.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnNewButton.setBounds(272, 95, 24, 17);
		contentPane.add(btnNewButton);

		JLabel lblCodigo = new JLabel("Cód:");
		lblCodigo.setEnabled(false);
		lblCodigo.setBounds(10, 286, 46, 14);
		contentPane.add(lblCodigo);

		ftfCodigo = new JFormattedTextField();
		ftfCodigo.setEditable(false);
		ftfCodigo.setEnabled(false);
		ftfCodigo.setBounds(82, 283, 83, 20);
		contentPane.add(ftfCodigo);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(
				new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/AgendarFoto - Copia (2).png")));
		lblNewLabel_3.setBounds(684, 62, 224, 222);
		contentPane.add(lblNewLabel_3);

		JButton btnAtualizarHorarios = new JButton("");
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
		btnAtualizarHorarios.setBounds(639, 90, 24, 20);
		contentPane.add(btnAtualizarHorarios);

		JLabel lblDD = new JLabel("DD:");
		lblDD.setForeground(new Color(120, 120, 120));
		lblDD.setBounds(10, 129, 46, 14);
		contentPane.add(lblDD);

		MaskFormatter maskDD = new MaskFormatter("(##)");
		ftfDD = new JFormattedTextField(maskDD);
		ftfDD.setBounds(82, 129, 38, 20);
		contentPane.add(ftfDD);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(new Color(106, 106, 106));
		lblTelefone.setBounds(10, 164, 62, 14);
		contentPane.add(lblTelefone);

		MaskFormatter maskTel = new MaskFormatter("#####-####");
		ftfTelefone = new JFormattedTextField(maskTel);
		ftfTelefone.setBounds(82, 160, 183, 20);
		contentPane.add(ftfTelefone);


		JButton btnTelefones = new JButton("");
		btnTelefones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarPorTelefone();
				
			}
		});
		btnTelefones.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnTelefones.setBounds(272, 160, 24, 17);
		contentPane.add(btnTelefones);

	}

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

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
			cal.add(Calendar.MINUTE, 31);

			// Atualiza o texto dos componentes existentes em vez de criar novos
			Date novaDataHoraInicio = cal.getTime();
			ftfHoraInicio.setText(sdf.format(novaDataHoraInicio));

			cal.add(Calendar.MINUTE, 31);
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
		}finally {
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
		}finally {
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
		}finally {
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

			eventoVO.setNomeCliente(nome);
			eventoVO.setEmail(email);
			BigInteger participantes = new BigInteger(qntParticipantes);
			eventoVO.setParticipantes(participantes);
			eventoVO.setLocal(local);
			eventoVO.setDd(dd);
			eventoVO.setNumero(telefone);

			service.salvar(eventoVO);

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
		}finally {
			System.out.println("Finally");
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
		}
		finally {
			System.out.println("Finally");
		}
	}
}
