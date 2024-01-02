package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaServicos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableModel tableModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaServicos frame = new TelaServicos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaServicos() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
				cadastroPessoaView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaServicos.class.getResource("/br/com/senac/view/img/LogoSTYLEMANAGER black.png")));
		setTitle("SERVIÇOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 443);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(192, 192, 192));
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(127, 127, 127),
				new Color(127, 127, 127), new Color(127, 127, 127), new Color(127, 127, 127)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		panel.setBounds(10, 40, 705, 128);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNomeServico = new JLabel("Nome:");
		lblNomeServico.setBounds(10, 23, 47, 14);
		panel.add(lblNomeServico);
		lblNomeServico.setForeground(new Color(100, 100, 100));

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(10, 74, 107, 23);
		panel.add(btnSalvar);
		btnSalvar.setIcon(new ImageIcon(TelaServicos.class.getResource("/br/com/senac/view/img/salvar.png")));

		JComboBox comboBoxNomeServico = new JComboBox();
		comboBoxNomeServico.setBounds(48, 20, 199, 20);
		panel.add(comboBoxNomeServico);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(140, 74, 107, 23);
		panel.add(btnExcluir);
		btnExcluir.setIcon(new ImageIcon(TelaServicos.class.getResource("/br/com/senac/view/img/remove.png")));

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(272, 23, 46, 14);
		panel.add(lblValor);
		lblValor.setForeground(new Color(100, 100, 100));

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(272, 74, 107, 23);
		panel.add(btnEditar);
		btnEditar.setIcon(new ImageIcon(TelaServicos.class.getResource("/br/com/senac/view/img/editar.png")));

		JLabel lblTempo = new JLabel("Duração:");
		lblTempo.setBounds(513, 23, 53, 14);
		panel.add(lblTempo);
		lblTempo.setForeground(new Color(100, 100, 100));

		JFormattedTextField ftfTempo = new JFormattedTextField();
		ftfTempo.setBounds(576, 20, 80, 18);
		panel.add(ftfTempo);

		JFormattedTextField ftfValor = new JFormattedTextField();
		ftfValor.setBounds(315, 20, 172, 18);
		panel.add(ftfValor);

		JLabel lblNewLabel = new JLabel("Cadastro de serviços");
		lblNewLabel.setForeground(new Color(96, 96, 96));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 21, 124, 14);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 236, 705, 145);
		contentPane.add(scrollPane);
				
		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Profissional");
		tableModel.addColumn("Valor");
		tableModel.addColumn("Duração");
		
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new MonthColorRenderer());
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(60);
		tcm.getColumn(1).setPreferredWidth(200);
		tcm.getColumn(2).setPreferredWidth(160);
		tcm.getColumn(3).setPreferredWidth(140);
		tcm.getColumn(4).setPreferredWidth(140);
		
		scrollPane.setViewportView(table);
		
		JLabel lblNomePesq = new JLabel("Nome:");
		lblNomePesq.setForeground(new Color(96, 96, 96));
		lblNomePesq.setBounds(20, 206, 46, 14);
		contentPane.add(lblNomePesq);
		
		JComboBox comboBoxNomeServico_1 = new JComboBox();
		comboBoxNomeServico_1.setBounds(63, 203, 222, 20);
		contentPane.add(comboBoxNomeServico_1);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(TelaServicos.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnPesquisar.setBounds(290, 202, 124, 23);
		contentPane.add(btnPesquisar);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
				cadastroPessoaView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(TelaServicos.class.getResource("/br/com/senac/view/img/2303132_arrow_back_direction_left_navigation_icon.png")));
		btnNewButton.setBounds(600, 202, 101, 23);
		contentPane.add(btnNewButton);
	}
}
