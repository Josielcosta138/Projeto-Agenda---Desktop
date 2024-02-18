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
		setBounds(100, 100, 450, 300);
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
		btnImprimir.setBounds(163, 106, 125, 23);
		contentPane.add(btnImprimir);
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
