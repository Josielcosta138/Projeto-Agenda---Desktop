package br.com.senac.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField tFUser;
	private JButton btnNewButton;
	private JButton btnAcessar;
	private JPasswordField passwordField;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel;
	
	public LoginView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/br/com/senac/view/novaGeracaoAgenda.jpg")));
		setTitle("Agenda - Josiel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(LoginView.class.getResource("/br/com/senac/view/agenda-online.jpg")));
		btnNewButton.setBounds(339, 10, 380, 363);
		contentPane.add(btnNewButton);
		
		tFUser = new JTextField();
		tFUser.setBounds(60, 148, 220, 19);
		contentPane.add(tFUser);
		tFUser.setColumns(10);
		tFUser.setText("admin@teste.com");
		
		btnAcessar = new JButton("Entre");
		btnAcessar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					validaSenha();
                }
				
			}
		});
		btnAcessar.setMnemonic('A');
		btnAcessar.setIcon(new ImageIcon(LoginView.class.getResource("/br/com/senac/view/img/10131905_door_enter_line_icon.png")));
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validaSenha();
			}
		});
		
		
		
		btnAcessar.setFont(new Font("Arial", Font.BOLD, 14));
		btnAcessar.setBounds(114, 283, 111, 21);
		contentPane.add(btnAcessar);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(60, 241, 220, 19);
		contentPane.add(passwordField);
		passwordField.setText("****");
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(LoginView.class.getResource("/br/com/senac/view/img/user2.png")));
		btnNewButton_1.setBounds(142, 109, 43, 32);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(LoginView.class.getResource("/br/com/senac/view/img/key.png")));
		btnNewButton_2.setBounds(142, 198, 43, 32);
		contentPane.add(btnNewButton_2);
		
		lblNewLabel = new JLabel("Seja bem-vindo(a).");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(114, 58, 134, 14);
		contentPane.add(lblNewLabel);
	}
	
	public void validaSenha() {
		String usuario= tFUser.getText();
		String senha= passwordField.getText();
		
		if (usuario.equals("a") && senha.equals("a")) {
			TelaAcessosView telaAcessos= new TelaAcessosView();
			telaAcessos.setVisible(true);			
			dispose();
		} else if(usuario.isEmpty() || senha.isEmpty() || usuario != "admin" || senha != "admin" ) { 
			JOptionPane.showMessageDialog(null,"Usuário e/ ou senha inválidos.", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}
