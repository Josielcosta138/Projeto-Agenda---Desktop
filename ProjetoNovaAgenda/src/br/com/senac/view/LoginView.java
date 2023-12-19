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

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField tFUser;
	private JButton btnNewButton;
	private JButton btnAcessar;
	private JPasswordField passwordField;
	
	public LoginView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/br/com/senac/view/novaGeracaoAgenda.jpg")));
		setTitle("AGENDA - NOVA GERAÇÃO");
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
		
		JLabel lblUser = new JLabel("Usuário");
		lblUser.setFont(new Font("Arial", Font.BOLD, 14));
		lblUser.setBounds(134, 103, 133, 13);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Senha");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
		lblPassword.setBounds(134, 156, 133, 13);
		contentPane.add(lblPassword);
		
		tFUser = new JTextField();
		tFUser.setBounds(60, 126, 220, 19);
		contentPane.add(tFUser);
		tFUser.setColumns(10);
		
		btnAcessar = new JButton("ACESSAR");
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
		btnAcessar.setBounds(60, 227, 220, 21);
		contentPane.add(btnAcessar);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(60, 178, 220, 19);
		contentPane.add(passwordField);
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
