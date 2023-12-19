package br.com.senac.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaAcessosView extends JFrame {

	private JPanel contentPane;
	private LoginView loginView;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAcessosView frame = new TelaAcessosView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
//   
	public TelaAcessosView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				voltarLogin();
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			}
		});
		setForeground(new Color(0, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaAcessosView.class.getResource("/br/com/senac/view/img/LogoSTYLEMANAGER black.png")));
		setTitle("TELA DE INCIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 289);
		contentPane = new JPanel();
		
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 // Usando GridBagLayout para centralizar os componentes
        //contentPane.setLayout(new GridBagLayout());
        //GridBagConstraints gbc = new GridBagConstraints();
        //gbc.insets = new Insets(8, 8, 8, 8);
		

		JButton btnBtnSystem = new JButton("");
		btnBtnSystem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CadastroPessoaView cadastroPessoaView= new CadastroPessoaView();
					cadastroPessoaView.setVisible(true);
					dispose();
                }
				
			}
		});
		btnBtnSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPessoaView cadastroPessoaView= new CadastroPessoaView();
				cadastroPessoaView.setVisible(true);
				dispose();
			}
		});
		btnBtnSystem
				.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/agendaNovaGerationjpg.jpg")));
		btnBtnSystem.setBounds(310, 11, 199, 194);
		contentPane.add(btnBtnSystem);
		
		JLabel lblAgendaInicio = new JLabel("Inciei seus Agendamentos aqui!");
		lblAgendaInicio.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/8956785_login_arrow_enter_button_BLUE_icon.png")));
		lblAgendaInicio.setBackground(new Color(30, 144, 255));
		lblAgendaInicio.setFont(new Font("Arial", Font.BOLD, 14));
		lblAgendaInicio.setBounds(10, 88, 286, 42);
		GridBagConstraints gbc_lblAgendaInicio = new GridBagConstraints();
		gbc_lblAgendaInicio.gridy = 0;
		contentPane.add(lblAgendaInicio, gbc_lblAgendaInicio);
		
		JButton btnVoltarLogin = new JButton("");
		btnVoltarLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voltarLogin();
			}
		});
		btnVoltarLogin.setBackground(new Color(192, 192, 192));
		btnVoltarLogin.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/acessVoltar.png")));
		btnVoltarLogin.setBounds(442, 216, 27, 26);
		contentPane.add(btnVoltarLogin);
		
		JLabel lblNewLabel = new JLabel("Voltar");
		lblNewLabel.setBounds(471, 222, 46, 14);
		contentPane.add(lblNewLabel);
	}

	protected void voltarLogin() {
		if (loginView == null) {
			loginView = new LoginView();
		}
		loginView.setVisible(true);
		dispose();
	}
	}

