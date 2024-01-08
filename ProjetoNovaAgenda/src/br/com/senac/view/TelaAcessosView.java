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
import java.text.ParseException;

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
import javax.swing.border.LineBorder;

public class TelaAcessosView extends JFrame {

	private JPanel contentPane;
	private LoginView loginView;
	private CadastroPessoaView cadastroPessoaView;
	private TelaAgendar telaAgendar;
	private TelaAgendamentos telaAgendamentos;
	private Disponibilidade disponibilidade;
	private TelaServicos telaServicos; 

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
		setBounds(100, 100, 546, 414);
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
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(131, 131, 131));
		lblNewLabel.setBounds(474, 302, 46, 14);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(45, 44, 188, 301);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCadastroPessoas = new JButton("Cadastrar");
		btnCadastroPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
				
			}
		});
		btnCadastroPessoas.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/user222.png")));
		btnCadastroPessoas.setBackground(new Color(214, 214, 214));
		btnCadastroPessoas.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCadastroPessoas.setBounds(10, 29, 168, 33);
		panel.add(btnCadastroPessoas);
		
		JButton btnAgendar = new JButton("Agendar");
		btnAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (telaAgendar == null) {
					try {
						telaAgendar = new TelaAgendar();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				telaAgendar.setVisible(true);
				dispose();
			}
		});
		btnAgendar.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/novo.png")));
		btnAgendar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAgendar.setBackground(new Color(214, 214, 214));
		btnAgendar.setBounds(10, 73, 168, 33);
		panel.add(btnAgendar);
		
		JButton btnAgendamento = new JButton("Agendamentos");
		btnAgendamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (telaAgendamentos == null) {
					try {
						telaAgendamentos = new TelaAgendamentos();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				telaAgendamentos.setVisible(true);
				dispose();
			}
		});
		btnAgendamento.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/tipo.png")));
		btnAgendamento.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAgendamento.setBackground(new Color(214, 214, 214));
		btnAgendamento.setBounds(10, 117, 168, 33);
		panel.add(btnAgendamento);
		
		JButton btnDisponibilidade = new JButton("Disponibilidade");
		btnDisponibilidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (disponibilidade == null) {
					disponibilidade = new Disponibilidade();
				}
				disponibilidade.setVisible(true);
				dispose();
				
			}
		});
		btnDisponibilidade.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/availability.png")));
		btnDisponibilidade.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDisponibilidade.setBackground(new Color(214, 214, 214));
		btnDisponibilidade.setBounds(10, 161, 168, 33);
		panel.add(btnDisponibilidade);
		
		JButton btnFinanceiro = new JButton("Financeiro");
		btnFinanceiro.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/financ2.png")));
		btnFinanceiro.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnFinanceiro.setBackground(new Color(214, 214, 214));
		btnFinanceiro.setBounds(10, 205, 168, 33);
		panel.add(btnFinanceiro);
		
		JButton btnServicos = new JButton("Serviços");
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (telaServicos == null) {
					try {
						telaServicos = new TelaServicos();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				telaServicos.setVisible(true);
				dispose();
				System.out.println("Clicou em Serviços");
			}
		});
		
		btnServicos.setIcon(new ImageIcon(TelaAcessosView.class.getResource("/br/com/senac/view/img/manutencao.png")));
		btnServicos.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnServicos.setBackground(new Color(214, 214, 214));
		btnServicos.setBounds(10, 249, 168, 33);
		panel.add(btnServicos);
		
		JLabel lblMenuRapido = new JLabel("Menu de acesso rápido");
		lblMenuRapido.setForeground(new Color(128, 128, 128));
		lblMenuRapido.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMenuRapido.setBounds(72, 19, 161, 14);
		contentPane.add(lblMenuRapido);
		
		JButton button = new JButton("New button");
		button.setBounds(383, 150, 89, 23);
		contentPane.add(button);
	}

	protected void cadastrar() {
		if (cadastroPessoaView == null) {
			cadastroPessoaView = new CadastroPessoaView();
		}
		cadastroPessoaView.setVisible(true);
		dispose();
		
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

