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
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaFinanceiro extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFinanceiro frame = new TelaFinanceiro();
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
	public TelaFinanceiro() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				TelaAcessosView telaAcessosView = new TelaAcessosView();
				telaAcessosView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaFinanceiro.class.getResource("/br/com/senac/view/img/business.png")));
		setTitle("FINANCEIRO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Financeiro ");
		lblNewLabel.setBounds(10, 27, 75, 14);
		lblNewLabel.setForeground(new Color(97, 97, 97));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(87, 11, 30, 40);
		lblNewLabel_1.setIcon(new ImageIcon(TelaFinanceiro.class.getResource("/br/com/senac/view/img/finance.png")));
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 52, 619, 1);
		panel.setBorder(new LineBorder(new Color(97, 97, 97)));
		contentPane.add(panel);
		
		JLabel lblAgendamentos = new JLabel("Totais Agendamentos R$");
		lblAgendamentos.setBounds(10, 86, 206, 28);
		lblAgendamentos.setIcon(new ImageIcon(TelaFinanceiro.class.getResource("/br/com/senac/view/img/tipo.png")));
		lblAgendamentos.setForeground(new Color(97, 97, 97));
		lblAgendamentos.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblAgendamentos);
		
		JLabel lblVendasProdutos = new JLabel("Totais Vendas produtos R$");
		lblVendasProdutos.setBounds(10, 305, 206, 28);
		lblVendasProdutos.setIcon(new ImageIcon(TelaFinanceiro.class.getResource("/br/com/senac/view/img/comprarProdut.png")));
		lblVendasProdutos.setForeground(new Color(97, 97, 97));
		lblVendasProdutos.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblVendasProdutos);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setBounds(112, 305, 30, 28);
		contentPane.add(lblNewLabel_1_1_1);
		
		JScrollPane scrollPaneAgendamentos = new JScrollPane();
		scrollPaneAgendamentos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneAgendamentos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneAgendamentos.setBounds(10, 127, 619, 167);
		contentPane.add(scrollPaneAgendamentos);
		
		tableModel = new TableModel();
		tableModel.addColumn("Código"); 
		tableModel.addColumn("Cliente"); 
		tableModel.addColumn("DD");
		tableModel.addColumn("Tefone"); 
		tableModel.addColumn("Total R$"); 

		
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new MonthColorRenderer());
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(60);
		tcm.getColumn(1).setPreferredWidth(180);
		tcm.getColumn(2).setPreferredWidth(100);
		tcm.getColumn(3).setPreferredWidth(140);
		tcm.getColumn(4).setPreferredWidth(140);
		
		scrollPaneAgendamentos.setViewportView(table);
		//listarVendas();
		
		
		JScrollPane scrollPaneVendProdutos = new JScrollPane();
		scrollPaneVendProdutos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneVendProdutos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneVendProdutos.setBounds(10, 344, 619, 167);
		contentPane.add(scrollPaneVendProdutos);
		
		
		tableModel = new TableModel();
		tableModel.addColumn("Código"); 
		tableModel.addColumn("Produto"); 
		tableModel.addColumn("Quantidade");
		tableModel.addColumn("Valor"); 
		tableModel.addColumn("Total R$"); 

		
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new MonthColorRenderer());
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm2 = table.getColumnModel();
		tcm2.getColumn(0).setPreferredWidth(60);
		tcm2.getColumn(1).setPreferredWidth(180);
		tcm2.getColumn(2).setPreferredWidth(100);
		tcm2.getColumn(3).setPreferredWidth(140);
		tcm2.getColumn(4).setPreferredWidth(140);
		
		scrollPaneVendProdutos.setViewportView(table);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAcessosView telaAcessosView = new TelaAcessosView();
				telaAcessosView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		btnVoltar.setIcon(new ImageIcon(TelaFinanceiro.class.getResource("/br/com/senac/view/img/2303132_arrow_back_direction_left_navigation_icon.png")));
		btnVoltar.setBounds(517, 522, 112, 23);
		contentPane.add(btnVoltar);
		
		
		
		
	}
}
