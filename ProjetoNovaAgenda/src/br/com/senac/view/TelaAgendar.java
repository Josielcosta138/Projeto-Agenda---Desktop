package br.com.senac.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.exception.BOValidationException;
import br.com.senac.service.Service;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.EventoVO;
import br.com.senac.vo.StatusEnum;
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
import javax.swing.ImageIcon;
import java.awt.Font;

public class TelaAgendar extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDataHora;
	private JTextField tftHoraAtual;
	private CadastroPessoaView cadastroPessoaView;
	private JComboBox comboBoxStatus;
	private JComboBox comboBoxStatusServico;
	private JFormattedTextField ftfNome;
	private JFormattedTextField ftfEmail;

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

	public TelaAgendar() {
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
		setBounds(100, 100, 704, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		//

		JLabel lblTipoServico = new JLabel("Tipo de serviço:");
		lblTipoServico.setBounds(10, 44, 100, 20);
		contentPane.add(lblTipoServico);

		// Exibe a data e hora atuais no campo "Data e Hora"
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		JLabel lblDataHoraInicio = new JLabel("Data e Hora Inicio:");
		lblDataHoraInicio.setBounds(10, 74, 112, 20);
		contentPane.add(lblDataHoraInicio);

		JTextField ftfHoraInicio = new JTextField();
		ftfHoraInicio.setBounds(120, 74, 150, 20);
		contentPane.add(ftfHoraInicio);
		ftfHoraInicio.setColumns(10);
		ftfHoraInicio.setText(sdf.format(new Date()));

		JButton btnAgendar = new JButton("Agendar");
		btnAgendar.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/edit.png")));
		btnAgendar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Teste agendar");
				agendar();
			}
		});
		btnAgendar.setBounds(446, 279, 114, 28);
		contentPane.add(btnAgendar);

		tftHoraAtual = new JTextField();
		tftHoraAtual.setEditable(false);
		tftHoraAtual.setBounds(571, 11, 112, 20);
		contentPane.add(tftHoraAtual);
		tftHoraAtual.setColumns(10);
		tftHoraAtual.setText(sdf.format(new Date()));

		JLabel lblDataHoraFim = new JLabel("Data e Hora Fim:");
		lblDataHoraFim.setBounds(10, 108, 100, 14);
		contentPane.add(lblDataHoraFim);

		JFormattedTextField ftfHoraFim = new JFormattedTextField();
		ftfHoraFim.setBounds(120, 105, 150, 20);
		contentPane.add(ftfHoraFim);
		ftfHoraFim.setText(sdf.format(new Date()));

		JLabel lblParticipantes = new JLabel("N° Participantes:");
		lblParticipantes.setBounds(10, 198, 100, 14);
		contentPane.add(lblParticipantes);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 229, 46, 14);
		contentPane.add(lblStatus);

		comboBoxStatus = new JComboBox();
		comboBoxStatus.setBounds(120, 226, 130, 20);

		// testeStatus
		DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel<>(StatusEnum.values());
		comboBoxStatus.setModel(defaultComboBoxModel);
		defaultComboBoxModel.insertElementAt(null, 0);
		comboBoxStatus.setSelectedIndex(1);

		contentPane.add(comboBoxStatus);

		JButton btnCancelar = new JButton("Canc");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarVoltar();
			}
		});
		btnCancelar.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/cancel.png")));
		btnCancelar.setBounds(571, 279, 98, 28);
		contentPane.add(btnCancelar);

		JFormattedTextField ftfQntParticipantes = new JFormattedTextField();
		ftfQntParticipantes.setBounds(120, 195, 83, 20);
		contentPane.add(ftfQntParticipantes);

		JLabel lblPessoa = new JLabel("Nome:");
		lblPessoa.setBounds(10, 136, 86, 14);
		contentPane.add(lblPessoa);

		DefaultComboBoxModel defaultComboBoxModelNome = new DefaultComboBoxModel<>(StatusEnum.values());
		defaultComboBoxModel.insertElementAt(null, 0);

		JLabel lblNewLabel = new JLabel("Agendamento de horários");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 14, 177, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/availability.png")));
		lblNewLabel_1.setBounds(179, 11, 24, 22);
		contentPane.add(lblNewLabel_1);

		JLabel lblEmailVincPessoa = new JLabel("E-mail:");
		lblEmailVincPessoa.setBounds(10, 168, 86, 14);
		contentPane.add(lblEmailVincPessoa);

		JLabel lblNewLabel_2 = new JLabel("Local:");
		lblNewLabel_2.setBounds(10, 262, 46, 14);
		contentPane.add(lblNewLabel_2);

		JFormattedTextField ftfLocal = new JFormattedTextField();
		ftfLocal.setBounds(120, 257, 155, 20);
		contentPane.add(ftfLocal);

		comboBoxStatusServico = new JComboBox();
		comboBoxStatusServico.setBounds(120, 43, 150, 21);
		DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel<>(StatusServico.values());
		comboBoxStatusServico.setModel(defaultComboBoxModel2);
		defaultComboBoxModel2.insertElementAt(null, 0);
		comboBoxStatusServico.setSelectedIndex(1);

		contentPane.add(comboBoxStatusServico);

		ftfNome = new JFormattedTextField();
		ftfNome.setBounds(120, 133, 150, 20);
		contentPane.add(ftfNome);

		JButton btnPesquisarNome = new JButton("");
		btnPesquisarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pesquisarNome();
				} catch (BOValidationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPesquisarNome.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnPesquisarNome.setBounds(274, 133, 29, 23);
		contentPane.add(btnPesquisarNome);

		ftfEmail = new JFormattedTextField();
		ftfEmail.setBounds(120, 165, 184, 20);
		contentPane.add(ftfEmail);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarPorNomeEmail();

			}
		});
		btnNewButton.setIcon(new ImageIcon(TelaAgendar.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnNewButton.setBounds(308, 164, 24, 23);
		contentPane.add(btnNewButton);

	}

	protected void pesquisarPorNomeEmail() {
		
		CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
		cadastroPessoaView.pesquisar();

		String email = null;

		try {

			System.out.println("******* Iniciando consulta de contatos *******");
			EntityManager em = HibernateUtil.getEntityManager();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ContatoVO> criteria = cb.createQuery(ContatoVO.class);

			// Clausula from
			Root<ContatoVO> contatosFrom = criteria.from(ContatoVO.class);
			criteria.select(contatosFrom);

			if (this.ftfEmail.getText() != null && ftfEmail.getText().trim().length() > 0) {
				email = ftfEmail.getText().trim();
			}

			if (email != null) {
				String searchTerm = "%" + email.toLowerCase() + "%";
				criteria.where(cb.like(cb.lower(contatosFrom.get("emails")), searchTerm));
			}

			TypedQuery<ContatoVO> query = em.createQuery(criteria);
			List<ContatoVO> listaContat = query.getResultList();
			System.out.println(listaContat);

			String nome2 = null;
			for (ContatoVO contatoVO : listaContat) {

				nome2 = contatoVO.getNome();

			}

			ftfNome.setText(nome2);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de sistema", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		

	}

	public void pesquisarNome() throws BOValidationException {
		CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
		cadastroPessoaView.pesquisar();

		String nome = null;

		try {

			System.out.println("******* Iniciando consulta de contatos *******");
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

			String nome2 = null;
			for (ContatoVO contatoVO : listaContat) {

				nome2 = contatoVO.getNome();

			}

			ftfNome.setText(nome2);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de sistema", JOptionPane.ERROR_MESSAGE);
		}

	}

	protected void agendar() {
		/*
		 * 
		 * try { Service service = new Service(); ContatoVO contatoVO = new ContatoVO();
		 * EventoVO eventoVO = new EventoVO();
		 * 
		 * String nome = comboBoxNome.getText().trim(); String datNasc =
		 * ftfDataNasc.getText().trim(); String observ = ftfObservacao.getText().trim();
		 * String status = null; StatusEnum statusEnum =
		 * (StatusEnum)cbStatus.getSelectedItem(); if (statusEnum != null) { status =
		 * statusEnum.name(); }
		 * 
		 * if (codigo != null && codigo.length() > 0) { contatoVO.setId(new
		 * BigInteger(codigo)); contatoVO = service.buscarContatosPorId(contatoVO); }
		 * 
		 * String pattern = "dd/MM/yyyy HH:mm:ss"; DateFormat dateFormat = new
		 * SimpleDateFormat(pattern);
		 * 
		 * try { if (!datNasc.isEmpty()) { Date date = dateFormat.parse(datNasc +
		 * " 00:00:00"); contatoVO.setDatnas(date); } else {
		 * JOptionPane.showMessageDialog(this, "Data de nascimento vazia!", "Erro",
		 * JOptionPane.ERROR_MESSAGE); return; } } catch (ParseException e) { // Trata a
		 * exceção de formato de data inválido e.printStackTrace();
		 * JOptionPane.showMessageDialog(this,
		 * "Formato de data inválido! Formato correto dd/MM/yyyy HH:mm:ss", "Erro",
		 * JOptionPane.ERROR_MESSAGE); return; }
		 * 
		 * contatoVO.setNome(nome); contatoVO.setObserv(observ);
		 * 
		 * service.salvar(contatoVO);
		 * 
		 * JOptionPane.showMessageDialog(null, "Cadastro salvo com sucesso!");
		 * setVisible(false); dispose();
		 * 
		 * } catch (BOValidationException b) { b.printStackTrace();
		 * JOptionPane.showMessageDialog(this, b.getMessage(), "Mensagem de aviso",
		 * JOptionPane.WARNING_MESSAGE);
		 * 
		 * } catch (Exception b) { b.printStackTrace();
		 * JOptionPane.showMessageDialog(this,
		 * "Ocorreu um erro ao realizar a operação!", "Erro",
		 * JOptionPane.ERROR_MESSAGE); } finally { System.out.println("Finally"); }
		 * 
		 */

	}

	protected void cancelarVoltar() {
		if (cadastroPessoaView == null) {
			cadastroPessoaView = new CadastroPessoaView();
		}
		cadastroPessoaView.setVisible(true);
		dispose();

	}
}
