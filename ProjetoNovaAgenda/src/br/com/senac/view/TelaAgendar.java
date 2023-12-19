package br.com.senac.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.senac.vo.StatusEnum;
import br.com.senac.vo.StatusServico;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
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
				CadastroPessoaView cadastroPessoaView= new CadastroPessoaView();
				cadastroPessoaView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaAgendar.class.getResource("/br/com/senac/view/img/LogoSTYLEMANAGER black.png")));
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
				
				
			}
		});
		btnAgendar.setBounds(447, 279, 114, 28);
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
		
		//testeStatus 
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
		
		JComboBox comboBoxNomeCliente = new JComboBox();
		comboBoxNomeCliente.setBounds(120, 133, 274, 20);
		contentPane.add(comboBoxNomeCliente);
		
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
		
		JComboBox comboBoxEmail = new JComboBox();
		comboBoxEmail.setBounds(120, 164, 274, 22);
		contentPane.add(comboBoxEmail);
		
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
		
	
		btnAgendar.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed1(ActionEvent e) {

				
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	protected void cancelarVoltar() {
		if (cadastroPessoaView == null) {
			cadastroPessoaView = new CadastroPessoaView();
		}
		cadastroPessoaView.setVisible(true);
		dispose();
		
	}
}
