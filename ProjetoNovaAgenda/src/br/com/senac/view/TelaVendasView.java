package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class TelaVendasView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVendasView frame = new TelaVendasView();
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
	public TelaVendasView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaVendasView.class.getResource("/br/com/senac/view/img/business.png")));
		setTitle("VENDAS ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1034, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVendaProdutos = new JLabel("Venda de produtos ");
		lblVendaProdutos.setForeground(new Color(120, 120, 120));
		lblVendaProdutos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVendaProdutos.setBounds(10, 20, 133, 20);
		contentPane.add(lblVendaProdutos);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/gel.png")));
		lblNewLabel_1.setBounds(143, 11, 29, 29);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 104, 320, 262);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblClienteTitulo = new JLabel("Cliente");
		lblClienteTitulo.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/user222.png")));
		lblClienteTitulo.setForeground(new Color(120, 120, 120));
		lblClienteTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblClienteTitulo.setBounds(10, 70, 77, 29);
		contentPane.add(lblClienteTitulo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(351, 104, 320, 262);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblProdutoTitulos = new JLabel("Produto");
		lblProdutoTitulos.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/comprarProdut.png")));
		lblProdutoTitulos.setForeground(new Color(120, 120, 120));
		lblProdutoTitulos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProdutoTitulos.setBounds(351, 70, 100, 29);
		contentPane.add(lblProdutoTitulos);
		
		JLabel lblResumo = new JLabel("Resumo");
		lblResumo.setIcon(new ImageIcon(TelaVendasView.class.getResource("/br/com/senac/view/img/resumo.png")));
		lblResumo.setForeground(new Color(120, 120, 120));
		lblResumo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblResumo.setBounds(690, 70, 100, 29);
		contentPane.add(lblResumo);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(690, 104, 320, 262);
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(null);
	}
}
