package br.com.senac.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;



public class TelaCalendario extends JFrame {

	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCalendario frame = new TelaCalendario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCalendario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Disponibilidade.class.getResource("/br/com/senac/view/novaGeracaoAgenda.jpg")));
		setTitle("Calendário");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				CadastroPessoaView cadastroPessoaView= new CadastroPessoaView();
				cadastroPessoaView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			}
		});
		
		
		//setTitle("Calendário");
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 356, 215);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        contentPane.setLayout(new BorderLayout(0, 0));
	        setContentPane(contentPane);
	        
	        //Adiciona o JLabel
	        JLabel label = new JLabel("Exibir calendário");
	        contentPane.add(label, BorderLayout.NORTH);
	        
	        //FlowLayout para centralizar o componente
	        contentPane.setLayout((LayoutManager) new FlowLayout(FlowLayout.CENTER, 10, 5));
	        setContentPane(contentPane);

	        UtilDateModel model = new UtilDateModel();
	        Properties properties = new Properties();
	        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
	        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
	        
	        
	        //Defina o tamanho preferido do componente para torná-lo centralizado
	        datePicker.setPreferredSize(new Dimension(200, 30));
	        contentPane.add(datePicker);

	        //contentPane.add(datePicker, BorderLayout.CENTER);

	}
}
