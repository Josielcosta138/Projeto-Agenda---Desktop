package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.com.senac.exception.BOException;
import br.com.senac.exception.BOValidationException;
import br.com.senac.service.IService;
import br.com.senac.service.Service;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.ContelVO;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class EditarContato extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField ftfCodigo;
	private JFormattedTextField ftfDDD;
	private JFormattedTextField ftfNumero;
	private JFormattedTextField ftfEmail;
	private ContelVO contatoAtual;
	private ContatoVO contato;
	private CadastroPessoaView cadastroPessoaView;
	private TableModel tableModel;
	private TableModel tableModel1;
	private JTable table;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContatoVO contatoSelecionado = new ContatoVO();
					EditarContato frame = new EditarContato(contatoSelecionado);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditarContato(ContatoVO contato) throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditarContato.class.getResource("/br/com/senac/view/img/LogoSTYLEMANAGER black.png")));
		contatoAtual = new ContelVO();
		
		this.contato = contato;

		setTitle("Contato");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(24, 36, 46, 14);
		contentPane.add(lblCodigo);

		JLabel lblNome = new JLabel("DDD:");
		lblNome.setBounds(24, 70, 46, 14);
		contentPane.add(lblNome);

		JLabel lblDatnas = new JLabel("Número:");
		lblDatnas.setBounds(24, 110, 85, 14);
		contentPane.add(lblDatnas);

		JLabel lblObservacao = new JLabel("E-mail:");
		lblObservacao.setBounds(24, 147, 85, 14);
		contentPane.add(lblObservacao);

		ftfCodigo = new JFormattedTextField();
		ftfCodigo.setEditable(false);
		ftfCodigo.setBounds(102, 33, 115, 20);
		contentPane.add(ftfCodigo);

		MaskFormatter maskDD = new MaskFormatter("(##)");
		ftfDDD = new JFormattedTextField(maskDD);
		//ftfDDD = new JFormattedTextField();
		ftfDDD.setBounds(102, 67, 62, 20);
		contentPane.add(ftfDDD);

		MaskFormatter mask = new MaskFormatter("#####-####");
	    ftfNumero = new JFormattedTextField(mask);
		ftfNumero.setBounds(102, 107, 127, 20);
		contentPane.add(ftfNumero);

		
	
		ftfEmail = new JFormattedTextField();
		ftfEmail.setBounds(102, 145, 252, 20);
		contentPane.add(ftfEmail);
		

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(EditarContato.class.getResource("/br/com/senac/view/img/salvar.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		
		btnSalvar.setMnemonic('C');
		btnSalvar.setBounds(102, 210, 115, 23);
		contentPane.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(EditarContato.class.getResource("/br/com/senac/view/img/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnCancelar.setMnemonic('C');
		btnCancelar.setBounds(227, 210, 115, 23);
		contentPane.add(btnCancelar);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		panel.setBounds(24, 194, 389, 5);
		contentPane.add(panel);
	}
	

	

	protected void salvar() {
		try {
			Service service = new Service();
			ContelVO contelVO = new ContelVO();

			String codigo = ftfCodigo.getText().trim();
			String ddd = ftfDDD.getText().trim();
			String numero = ftfNumero.getText().trim();
			String email = ftfEmail.getText().trim();

			if (codigo != null && codigo.length() > 0) {
				contelVO.setId(new BigInteger(codigo));
				contelVO = service.buscarContatosTelPorId(contelVO);
			}
			
			
			 
			if (!email.contains("@")) {
			    JOptionPane.showMessageDialog(this, "O e-mail deve conter o símbolo @", "Erro", JOptionPane.ERROR_MESSAGE);
			    ftfEmail.setText("");
			} else {
				contelVO.setEmails(email);
				contelVO.setDddnum(ddd);
				contelVO.setNumero(numero);
				contelVO.setContat(contato);
				service.salvar(contelVO);
				
				JOptionPane.showMessageDialog(null, "Cadastro salvo com sucesso!");
			
				
				setVisible(false);
				dispose();
				
			}

			
			 

		} catch (BOValidationException b) {
			b.printStackTrace();
			JOptionPane.showMessageDialog(this, b.getMessage(), "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);

		} catch (Exception b) {
			b.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Erro",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			System.out.println("Finally");
		}
	}
	

	public void editar(ContelVO cont) {
		contatoAtual = cont;

		this.ftfCodigo.setText(cont.getId().toString());
		this.ftfDDD.setText(cont.getDddnum().toString());
		this.ftfNumero.setText(cont.getNumero().toString());
		this.ftfEmail.setText(cont.getEmails().toString());
	}
	
	
	

}
