package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Relatorio.RelatorioPdfSimples;
import Relatorio.RelatorioVendas;

import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class TelaRelatorios extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRelatorios frame = new TelaRelatorios();
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
	public TelaRelatorios() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				TelaAcessosView telaAcessosView = new TelaAcessosView();
				telaAcessosView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaRelatorios.class.getResource("/br/com/senac/view/img/business.png")));
		setTitle("RELATÓRIOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirRelatorio();
				
			}
		});
		btnImprimir.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/br/com/senac/view/img/relatorio.png")));
		btnImprimir.setBounds(254, 86, 125, 23);
		contentPane.add(btnImprimir);
		
		JLabel lblNewLabel = new JLabel("Relatórios");
		lblNewLabel.setForeground(new Color(121, 121, 121));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 23, 90, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/br/com/senac/view/img/relatorioPdf.png")));
		lblNewLabel_1.setBounds(81, 15, 31, 31);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(121, 121, 121)));
		panel.setBounds(10, 57, 460, 1);
		contentPane.add(panel);
		
		JLabel lblVendas = new JLabel("Vendas:");
		lblVendas.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/br/com/senac/view/img/vendas.png")));
		lblVendas.setForeground(new Color(121, 121, 121));
		lblVendas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVendas.setBounds(10, 85, 102, 23);
		contentPane.add(lblVendas);
		
		JLabel lblPdf = new JLabel("PDF:");
		lblPdf.setForeground(new Color(121, 121, 121));
		lblPdf.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPdf.setBounds(202, 85, 42, 23);
		contentPane.add(lblPdf);
		
		JLabel lblAgendamentos = new JLabel("Agendamentos:");
		lblAgendamentos.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/br/com/senac/view/img/tipo.png")));
		lblAgendamentos.setForeground(new Color(121, 121, 121));
		lblAgendamentos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAgendamentos.setBounds(10, 141, 141, 23);
		contentPane.add(lblAgendamentos);
		
		JLabel lblPdf_1 = new JLabel("PDF:");
		lblPdf_1.setForeground(new Color(121, 121, 121));
		lblPdf_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPdf_1.setBounds(202, 141, 42, 23);
		contentPane.add(lblPdf_1);
		
		JButton btnImprimir_1 = new JButton("Imprimir");
		btnImprimir_1.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/br/com/senac/view/img/relatorio.png")));
		btnImprimir_1.setBounds(254, 142, 125, 23);
		contentPane.add(btnImprimir_1);
		
		JLabel lblSevios = new JLabel("Seviços:");
		lblSevios.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/br/com/senac/view/img/manutencao.png")));
		lblSevios.setForeground(new Color(121, 121, 121));
		lblSevios.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSevios.setBounds(10, 197, 102, 23);
		contentPane.add(lblSevios);
		
		JLabel lblPdf_2 = new JLabel("PDF:");
		lblPdf_2.setForeground(new Color(121, 121, 121));
		lblPdf_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPdf_2.setBounds(202, 197, 42, 23);
		contentPane.add(lblPdf_2);
		
		JButton btnImprimir_2 = new JButton("Imprimir");
		btnImprimir_2.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/br/com/senac/view/img/relatorio.png")));
		btnImprimir_2.setBounds(254, 198, 125, 23);
		contentPane.add(btnImprimir_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(121, 121, 121)));
		panel_1.setBounds(10, 249, 460, 1);
		contentPane.add(panel_1);
		
		JButton btnImprimir_2_1 = new JButton("Voltar");
		btnImprimir_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAcessosView telaAcessosView = new TelaAcessosView();
				telaAcessosView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		btnImprimir_2_1.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/br/com/senac/view/img/2303132_arrow_back_direction_left_navigation_icon.png")));
		btnImprimir_2_1.setBounds(345, 278, 125, 23);
		contentPane.add(btnImprimir_2_1);
	}

	protected void imprimirRelatorio() {
		
		RelatorioVendas relatorioVendas = new RelatorioVendas();
		
		try {
			relatorioVendas.gerarImprimir();
			relatorioVendas.gerarCabecalho();
			relatorioVendas.gerarCopor();
			relatorioVendas.gerarRodape();
			
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
		
	}
}
