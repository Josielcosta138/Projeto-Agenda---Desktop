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
import java.awt.Panel;
import java.awt.Canvas;
import java.awt.Label;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.TextField;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField tFUser;
	private JButton btnAcessar;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;
	
	public LoginView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/br/com/senac/view/img/stLOG.png")));
		setTitle("STYLE MANAGER SHOP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
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
		
		lblNewLabel = new JLabel("Seja bem-vindo(a).");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(114, 58, 134, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setIcon(new ImageIcon(LoginView.class.getResource("/br/com/senac/view/img/user2.png")));
		lblNewLabel_1.setBounds(152, 98, 36, 39);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setIcon(new ImageIcon(LoginView.class.getResource("/br/com/senac/view/img/key.png")));
		lblNewLabel_2.setBounds(152, 208, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(LoginView.class.getResource("/br/com/senac/view/img/BarbeariaAgendaAberta - Copia.png")));
		lblNewLabel_3.setBounds(331, 43, 220, 233);
		contentPane.add(lblNewLabel_3);
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
