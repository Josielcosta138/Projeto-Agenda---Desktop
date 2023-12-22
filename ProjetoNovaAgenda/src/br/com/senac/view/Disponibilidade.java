package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Disponibilidade extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private CadastroPessoaView cadastroPessoaView;
	private TableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Disponibilidade frame = new Disponibilidade();
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
	public Disponibilidade() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Disponibilidade.class.getResource("/br/com/senac/view/img/LogoSTYLEMANAGER black.png")));
		setTitle("DISPONIBILIDADE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 539, 227);
		contentPane.add(scrollPane);
		
		tableModel = new TableModel();
		tableModel.addColumn("Status");
		tableModel.addColumn("Data hora inicio");
		tableModel.addColumn("Data hora fim");
		tableModel.addColumn("Local");
		
		
		
		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(140);
		tcm.getColumn(1).setPreferredWidth(140);
		tcm.getColumn(2).setPreferredWidth(140);
		tcm.getColumn(3).setPreferredWidth(120);
		

		scrollPane.setViewportView(table);
		
		//table = new JTable();
		//scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Disponibilidade de horarios");
		lblNewLabel.setIcon(new ImageIcon(Disponibilidade.class.getResource("/br/com/senac/view/img/DisPonibilidade (2).png")));
		lblNewLabel.setForeground(new Color(107, 107, 107));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(20, 21, 262, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Disponibilidade.class.getResource("/br/com/senac/view/img/CopiaDispo.png")));
		lblNewLabel_1.setBounds(559, 63, 206, 227);
		contentPane.add(lblNewLabel_1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		JFormattedTextField ftfHoraAtual = new JFormattedTextField();
		ftfHoraAtual.setEditable(false);
		ftfHoraAtual.setBounds(654, 11, 105, 20);
		contentPane.add(ftfHoraAtual);
		ftfHoraAtual.setText(sdf.format(new Date()));
		
		JButton btnNewButton = new JButton("Pesq");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				listarDisponibilidade();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Disponibilidade.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnNewButton.setBounds(10, 301, 105, 22);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Volt");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				voltar();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Disponibilidade.class.getResource("/br/com/senac/view/img/2303132_arrow_back_direction_left_navigation_icon.png")));
		btnNewButton_1.setBounds(670, 301, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Disponibilidade.class.getResource("/br/com/senac/view/img/dateTime.png")));
		lblNewLabel_2.setBounds(630, 11, 24, 20);
		contentPane.add(lblNewLabel_2);
	}

	protected void listarDisponibilidade() {
		
		
		
		
		
		
		
		
	}

	protected void voltar() {
		if (cadastroPessoaView == null) {
			cadastroPessoaView = new CadastroPessoaView();
		}
		cadastroPessoaView.setVisible(true);
		dispose();
	}
		
}
