package br.com.senac.view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.*;
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
    	addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
				cadastroPessoaView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		}
    	});
        setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCalendario.class.getResource("/br/com/senac/view/img/business.png")));
        setTitle("Calendário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 356, 215);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

      
        JLabel label = new JLabel("Exibir calendário");
        label.setIcon(new ImageIcon(TelaCalendario.class.getResource("/br/com/senac/view/img/calendario.png")));
        label.setForeground(new Color(107, 107, 107));
        label.setFont(new Font("Tahoma", Font.BOLD, 11));
        contentPane.add(label, BorderLayout.NORTH);

        
        JLabel imagemLabel = new JLabel();
        contentPane.add(imagemLabel, BorderLayout.CENTER);

        // Adiciona o JDatePicker
        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);

        // Define o tamanho preferido do componente para torná-lo centralizado
        datePicker.setPreferredSize(new Dimension(200, 30));
        contentPane.add(datePicker, BorderLayout.CENTER);

       
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setIcon(new ImageIcon(TelaCalendario.class.getResource("/br/com/senac/view/img/2303132_arrow_back_direction_left_navigation_icon.png")));
        btnVoltar.addActionListener(e -> {
            CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
            cadastroPessoaView.setVisible(true);
            dispose();
        });

        // Adiciona o botão no canto inferior direito
        contentPane.add(btnVoltar, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }
}
