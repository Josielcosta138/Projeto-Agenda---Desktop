package br.com.senac.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Disponibilidade extends JFrame {

	private JPanel contentPane;

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
		  setIconImage(Toolkit.getDefaultToolkit().getImage(Disponibilidade.class.getResource("/br/com/senac/view/novaGeracaoAgenda.jpg")));
	        setTitle("Disponibilidade - agenda");
	        addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	                CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
	                cadastroPessoaView.setVisible(true);
	                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            }
	        });
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 706, 439);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(new BorderLayout(0, 0));

	        // Adicione os botões acima do modelo de tabela
	        JButton btnBotao1 = new JButton("");
	        btnBotao1.setIcon(new ImageIcon(Disponibilidade.class.getResource("/br/com/senac/view/img/planlist.png")));

	        contentPane.add(btnBotao1, BorderLayout.NORTH);

	        

	        // Cria um modelo de tabela simples para exemplo
	        DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] { "Data", "Status" });

	        // Adiciona algumas datas para teste (substitua isso com suas datas reais)
	        model.addRow(new Object[] { "2023-12-01", "Ocupada" });
	        model.addRow(new Object[] { "2023-12-02", "Vaga" });
	        model.addRow(new Object[] { "2023-12-03", "Ocupada" });

	        JTable table = new JTable(model);
	        table.getColumnModel().getColumn(0).setPreferredWidth(150);
	        table.getColumnModel().getColumn(1).setPreferredWidth(150);

	        // Define a cor das células na coluna "Status" com base no valor (Ocupada ou Vaga)
	        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
	        table.getColumnModel().getColumn(1).setCellRenderer(renderer);
	        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
	            @Override
	            public void setValue(Object value) {
	                super.setValue(value);
	                if ("Vaga".equals(value)) {
	                    setBackground(Color.GREEN);
	                    super.setValue(value);
	                } else if ("Ocupada".equals(value)) {
	                    setBackground(Color.ORANGE);
	                    super.setValue(value);
	                }
	            }
	        });

	        JScrollPane scrollPane = new JScrollPane(table);
	        contentPane.add(scrollPane, BorderLayout.CENTER);
	    }
	}


