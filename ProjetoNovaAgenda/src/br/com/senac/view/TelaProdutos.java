package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class TelaProdutos extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaProdutos frame = new TelaProdutos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaProdutos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaProdutos.class.getResource("/br/com/senac/view/img/LogoSTYLEMANAGER black.png")));
		setTitle("PRODUTOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(160, 160, 160)));
		panel.setBounds(10, 48, 370, 174);
		contentPane.add(panel);
		
		JLabel lblCadastroProduto = new JLabel("Cadastro de produtos");
		lblCadastroProduto.setForeground(new Color(115, 115, 115));
		lblCadastroProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCadastroProduto.setBounds(10, 23, 141, 14);
		contentPane.add(lblCadastroProduto);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaProdutos.class.getResource("/br/com/senac/view/img/product.png")));
		lblNewLabel_1.setBounds(147, 11, 33, 29);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(160, 160, 160)));
		panel_1.setBounds(448, 48, 370, 174);
		contentPane.add(panel_1);
		
		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setForeground(new Color(115, 115, 115));
		lblEstoque.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstoque.setBounds(450, 24, 64, 14);
		contentPane.add(lblEstoque);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(504, 39, 51, -26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaProdutos.class.getResource("/br/com/senac/view/img/storage.png")));
		lblNewLabel_2.setBounds(504, 11, 33, 29);
		contentPane.add(lblNewLabel_2);
	}
}
