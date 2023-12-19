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
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JComboBox;

public class TelaAgendar extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDataHora;
	private JTextField tftHoraAtual;

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
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaAgendar.class.getResource("/br/com/senac/view/novaGeracaoAgenda.jpg")));
		setTitle("Agendar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		//

		JLabel lblNomeDoEvento = new JLabel("Nome do Evento:");
		lblNomeDoEvento.setBounds(10, 30, 100, 20);
		contentPane.add(lblNomeDoEvento);

		JTextField textFieldNomeEvento = new JTextField();
		textFieldNomeEvento.setBounds(120, 30, 150, 20);
		contentPane.add(textFieldNomeEvento);
		textFieldNomeEvento.setColumns(10);
		
		// Exibe a data e hora atuais no campo "Data e Hora"
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		JLabel lblDataHoraInicio = new JLabel("Data e Hora Inicio:");
		lblDataHoraInicio.setBounds(10, 60, 112, 20);
		contentPane.add(lblDataHoraInicio);

		JTextField textFieldDataHora = new JTextField();
		textFieldDataHora.setBounds(120, 60, 150, 20);
		contentPane.add(textFieldDataHora);
		textFieldDataHora.setColumns(10);
		textFieldDataHora.setText(sdf.format(new Date()));

		JLabel lblDataHoraAtual = new JLabel("Data e Hora Atuais:");
		lblDataHoraAtual.setBounds(325, 11, 117, 20);
		contentPane.add(lblDataHoraAtual);

		JTextField textFieldDataHoraAtual = new JTextField();
		textFieldDataHoraAtual.setBounds(120, 122, 223, 44);
		contentPane.add(textFieldDataHoraAtual);
		textFieldDataHoraAtual.setColumns(10);

		

		JButton btnAgendar = new JButton("Agendar");
		btnAgendar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnAgendar.setBounds(360, 269, 86, 20);
		contentPane.add(btnAgendar);

		tftHoraAtual = new JTextField();
		tftHoraAtual.setEditable(false);
		tftHoraAtual.setBounds(452, 11, 112, 20);
		contentPane.add(tftHoraAtual);
		tftHoraAtual.setColumns(10);
		tftHoraAtual.setText(sdf.format(new Date()));
		
		JLabel lblDataHoraFim = new JLabel("Data e Hora Fim:");
		lblDataHoraFim.setBounds(10, 91, 100, 14);
		contentPane.add(lblDataHoraFim);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(120, 91, 150, 20);
		contentPane.add(formattedTextField);
		
		JLabel lblLocal = new JLabel("Local:");
		lblLocal.setBounds(10, 125, 86, 14);
		contentPane.add(lblLocal);
		
		JLabel lblParticipantes = new JLabel("N° Participantes:");
		lblParticipantes.setBounds(10, 194, 86, 14);
		contentPane.add(lblParticipantes);
		
		JComboBox comboBoxNumPartipantes = new JComboBox();
		comboBoxNumPartipantes.setBounds(120, 188, 51, 20);
		contentPane.add(comboBoxNumPartipantes);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 219, 46, 14);
		contentPane.add(lblStatus);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(120, 216, 51, 20);
		contentPane.add(comboBox);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(456, 268, 86, 21);
		contentPane.add(btnCancelar);

		btnAgendar.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed1(ActionEvent e) {

				// Aqui você pode adicionar a lógica para agendar o evento
				String nomeEvento = textFieldNomeEvento.getText();
				String dataHora = textFieldDataHora.getText();
				// Adicione a lógica para lidar com os dados do evento
				System.out.println("Evento Agendado: " + nomeEvento + ", Data e Hora: " + dataHora);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}
}
