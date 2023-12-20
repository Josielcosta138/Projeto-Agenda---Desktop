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
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

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
		setBounds(100, 100, 546, 374);
		contentPane = new JPanel();
		
		contentPane.setBackground(new Color(201, 201, 201));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		

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
				
			}
		});
		btnBtnSystem
				.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/BarbeariaAgendaAberta - Copia.png")));
		btnBtnSystem.setBounds(310, 74, 199, 194);
		contentPane.add(btnBtnSystem);
		
		JLabel lblAgendaInicio = new JLabel("Inciei seus Agendamentos!");
		lblAgendaInicio.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/8956785_login_arrow_enter_button_BLUE_icon.png")));
		lblAgendaInicio.setBackground(new Color(30, 144, 255));
		lblAgendaInicio.setFont(new Font("Arial", Font.BOLD, 14));
		lblAgendaInicio.setBounds(10, 25, 286, 42);
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
		btnVoltarLogin.setBounds(445, 296, 27, 26);
		contentPane.add(btnVoltarLogin);
		
		JLabel lblNewLabel = new JLabel("Voltar");
		lblNewLabel.setBounds(474, 302, 46, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnAgendar = new JButton("");
		btnAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAgendar agendar = new TelaAgendar();
				agendar.setVisible(true);
				dispose(); 
			} 
		});
		btnAgendar.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/agendamentoMenu.png")));
		btnAgendar.setBackground(new Color(192, 192, 192));
		btnAgendar.setBounds(157, 88, 89, 65);
		contentPane.add(btnAgendar);
		
		JButton btnPessoas = new JButton("");
		btnPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPessoaView cadastroPessoaView= new CadastroPessoaView();
				cadastroPessoaView.setVisible(true);
				dispose();
				
			}
		});
		btnPessoas.setBackground(new Color(192, 192, 192));
		btnPessoas.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/userInicial.png")));
		btnPessoas.setBounds(20, 88, 89, 65);
		contentPane.add(btnPessoas);
		
		JButton btnAgendamentos = new JButton("");
		btnAgendamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAgendamentos agendamentos = new TelaAgendamentos();
				agendamentos.setVisible(true);
				
			}
		});
		btnAgendamentos.setBackground(new Color(192, 192, 192));
		btnAgendamentos.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/listaAgenda.png")));
		btnAgendamentos.setBounds(20, 197, 89, 65);
		contentPane.add(btnAgendamentos);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Disponibilidade disponibilidade = new Disponibilidade();
				disponibilidade.setVisible(true);
				
			}
		});
		btnNewButton.setBackground(new Color(201, 201, 201));
		btnNewButton.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/disponibilidade.png")));
		btnNewButton.setBounds(157, 197, 89, 65);
		contentPane.add(btnNewButton);
	}

	protected void voltarLogin() {
		if (loginView == null) {
			loginView = new LoginView();
		}
		loginView.setVisible(true);
		dispose();
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	}

