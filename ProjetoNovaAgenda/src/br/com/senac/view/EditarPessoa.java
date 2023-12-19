package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.com.senac.exception.BOValidationException;
import br.com.senac.service.Service;
import br.com.senac.vo.ContatoVO;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class EditarPessoa extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField ftfCodigo;
	private JFormattedTextField ftfNome;
	private JFormattedTextField ftfDataNasc;
	private JFormattedTextField ftfObservacao;
	private ContatoVO contatoAtual;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarPessoa frame = new EditarPessoa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditarPessoa() throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditarPessoa.class.getResource("/br/com/senac/view/novaGeracaoAgenda.jpg")));
		contatoAtual = new ContatoVO();

		setTitle("Pessoa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(24, 36, 46, 14);
		contentPane.add(lblCodigo);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(24, 70, 46, 14);
		contentPane.add(lblNome);

		JLabel lblDatnas = new JLabel("Data de nasc:");
		lblDatnas.setBounds(24, 110, 85, 14);
		contentPane.add(lblDatnas);

		JLabel lblObservacao = new JLabel("Observação:");
		lblObservacao.setBounds(24, 147, 85, 14);
		contentPane.add(lblObservacao);

		ftfCodigo = new JFormattedTextField();
		ftfCodigo.setEditable(false);
		ftfCodigo.setBounds(102, 33, 115, 20);
		contentPane.add(ftfCodigo);

		ftfNome = new JFormattedTextField();
		ftfNome.setBounds(102, 67, 252, 20);
		contentPane.add(ftfNome);

		MaskFormatter mask = new MaskFormatter("##/##/#### ##:##:##");
		ftfDataNasc = new JFormattedTextField(mask);
		ftfDataNasc.setBounds(102, 107, 127, 20);
		contentPane.add(ftfDataNasc);

		ftfObservacao = new JFormattedTextField();
		ftfObservacao.setBounds(102, 145, 252, 38);
		contentPane.add(ftfObservacao);

		JButton btnSalvar = new JButton("Save");
		btnSalvar.setIcon(new ImageIcon(EditarPessoa.class.getResource("/br/com/senac/view/img/salvar.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnSalvar.setMnemonic('C');
		btnSalvar.setBounds(128, 210, 89, 23);
		contentPane.add(btnSalvar);

		JButton btnCancelar = new JButton("Canc");
		btnCancelar.setIcon(new ImageIcon(EditarPessoa.class.getResource("/br/com/senac/view/img/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cancelar();
			}
		});
		btnCancelar.setMnemonic('C');
		btnCancelar.setBounds(265, 210, 89, 23);
		contentPane.add(btnCancelar);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		panel.setBounds(24, 194, 389, 5);
		contentPane.add(panel);
	}

	protected void cancelar() {
		this.setVisible(false);
		this.dispose();

	}

	protected void salvar() {
		try {
			Service service = new Service();
			ContatoVO contatoVO = new ContatoVO();

			String codigo = ftfCodigo.getText().trim();
			String nome = ftfNome.getText().trim();
			String datNasc = ftfDataNasc.getText().trim();
			String observ = ftfObservacao.getText().trim();

			if (codigo != null && codigo.length() > 0) {
				contatoVO.setId(new BigInteger(codigo));
				contatoVO = service.buscarContatosPorId(contatoVO);
			}

			String pattern = "dd/MM/yyyy HH:mm:ss";
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			
			try {
			    if (!datNasc.isEmpty()) {
			        Date date = dateFormat.parse(datNasc + " 00:00:00");
			        contatoVO.setDatnas(date);
			    } else {
			        JOptionPane.showMessageDialog(this, "Data de nascimento vazia!", "Erro",
			                JOptionPane.ERROR_MESSAGE);
			        return;
			    }
			} catch (ParseException e) {
			    // Trata a exceção de formato de data inválido
			    e.printStackTrace();
			    JOptionPane.showMessageDialog(this, "Formato de data inválido! Formato correto dd/MM/yyyy HH:mm:ss", "Erro",
			            JOptionPane.ERROR_MESSAGE);
			    return;
			}

			contatoVO.setNome(nome);
			contatoVO.setObserv(observ);

			service.salvar(contatoVO);

			JOptionPane.showMessageDialog(null, "Cadastro salvo com sucesso!");
			setVisible(false);
			dispose();

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

	public void editar(ContatoVO cont) {

		contatoAtual = cont;

		this.ftfCodigo.setText(cont.getId().toString());
		this.ftfNome.setText(cont.getNome().toString());
		this.ftfDataNasc.setText(cont.getDatnas().toString());
		this.ftfObservacao.setText(cont.getObserv().toString());

	}

}
