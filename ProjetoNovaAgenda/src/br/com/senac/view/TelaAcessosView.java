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

	public TelaAcessosView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				voltarLogin();
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			}
		});
		setForeground(new Color(0, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaAcessosView.class.getResource("/br/com/senac/view/novaGeracaoAgenda.jpg")));
		setTitle("Agenda - Josiel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 435);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		btnBtnSystem.setBounds(229, 83, 220, 217);
		contentPane.add(btnBtnSystem);
		
		JLabel lblAgendaInicio = new JLabel("Inciei sua Agenda aqui!");
		lblAgendaInicio.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/8956785_login_arrow_enter_button_BLUE_icon.png")));
		lblAgendaInicio.setBackground(new Color(30, 144, 255));
		lblAgendaInicio.setFont(new Font("Arial", Font.BOLD, 14));
		lblAgendaInicio.setBounds(-1, 176, 220, 42);
		contentPane.add(lblAgendaInicio);
	}

	protected void voltarLogin() {
		if (loginView == null) {
			loginView = new LoginView();
		}
		loginView.setVisible(true);
		dispose();
	}
	}

