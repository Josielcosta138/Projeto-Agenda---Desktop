package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.exception.BOValidationException;
import br.com.senac.service.IService;
import br.com.senac.service.Service;
import br.com.senac.vo.ContatoVO;
import br.com.senac.vo.ContelVO;
import br.com.senac.vo.EventoVO;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TelaAgendamentos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private TableModel tableModel;
	private CadastroPessoaView cadastroPessoaView;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAgendamentos frame = new TelaAgendamentos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaAgendamentos() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
				cadastroPessoaView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaAgendamentos.class.getResource("/br/com/senac/view/img/LogoSTYLEMANAGER black.png")));
		setTitle("Eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1016, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Exibe a data e hora atuais no campo "Data e Hora"
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(23, 74, 952, 233);
		contentPane.add(scrollPane);
 
		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Local");
		tableModel.addColumn("Status");
		tableModel.addColumn("Participantes");
		tableModel.addColumn("Cliente");
		tableModel.addColumn("Tipo serviço");
		tableModel.addColumn("E-mails-Vinculado(Pessoa)");
		tableModel.addColumn("Data hora inicio");
		tableModel.addColumn("Data hora fim");
		
		
		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(60);
		tcm.getColumn(1).setPreferredWidth(60);
		tcm.getColumn(2).setPreferredWidth(60);
		tcm.getColumn(3).setPreferredWidth(100);
		tcm.getColumn(4).setPreferredWidth(100);
		tcm.getColumn(5).setPreferredWidth(100);
		tcm.getColumn(6).setPreferredWidth(180);
		tcm.getColumn(7).setPreferredWidth(140);
		tcm.getColumn(8).setPreferredWidth(150);

		scrollPane.setViewportView(table);

		JLabel lblListaAgendamento = new JLabel("Agendamentos:");
		lblListaAgendamento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblListaAgendamento.setBounds(28, 49, 108, 14);
		contentPane.add(lblListaAgendamento);

		JFormattedTextField ftfDataAtual = new JFormattedTextField();
		ftfDataAtual.setEditable(false);
		ftfDataAtual.setBounds(859, 43, 113, 20);
		contentPane.add(ftfDataAtual);
		ftfDataAtual.setText(sdf.format(new Date()));

		JButton btnPesquisar = new JButton("Pesq");
		btnPesquisar.setIcon(new ImageIcon(TelaAgendamentos.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarAgendamentos();

			}
		}); 
		btnPesquisar.setBounds(23, 318, 113, 23);
		contentPane.add(btnPesquisar);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(TelaAgendamentos.class.getResource("/br/com/senac/view/img/dateTime.png")));
		btnNewButton.setBounds(834, 40, 26, 23);
		contentPane.add(btnNewButton);
		
		JButton btnVoltar = new JButton("Volt");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (cadastroPessoaView == null) {
					cadastroPessoaView = new CadastroPessoaView();
				}
				cadastroPessoaView.setVisible(true);
				dispose();
			}
				
		});
		btnVoltar.setIcon(new ImageIcon(TelaAgendamentos.class.getResource("/br/com/senac/view/img/2303132_arrow_back_direction_left_navigation_icon.png")));
		btnVoltar.setBounds(881, 318, 94, 23);
		contentPane.add(btnVoltar);

	}

	protected void listarAgendamentos() {
		if (tableModel != null) {
			tableModel.clearTable();
		}
		
		BigInteger id = null;
		Date dataHoraInicio = null;
		Date dataHoraFim = null;
		String local = null;
		BigInteger participantes = null;
		//List<BigInteger> participantes = null;
		String status = null;
		String tipoServico = null;
		
		
		try {
			
				try {
				
					IService service = new Service();
					System.out.println("******* Iniciando consulta de agendamentos *******");
					EntityManager em = HibernateUtil.getEntityManager();

					CriteriaBuilder cb = em.getCriteriaBuilder();
					CriteriaQuery<EventoVO> criteria = cb.createQuery(EventoVO.class);

					// Clausula from
					Root<EventoVO> agendamentosFrom = criteria.from(EventoVO.class);
					criteria.select(agendamentosFrom);

				 
					TypedQuery<EventoVO> query = em.createQuery(criteria);
					//Order contatoOrderBy = cb.asc(agendamentosFrom.get("nome"));
					//criteria.orderBy(contatoOrderBy);

					List<EventoVO> listaAgendamentos = query.getResultList();
					

					System.out.println("Lista agendamentos --> "+listaAgendamentos);
					//Collections.sort(listaAgendamentos, (contato1, contato2) -> contato1.getNome().compareTo(contato2.getNome()));

					for (EventoVO eventoVO : listaAgendamentos) {

						if (eventoVO.getId() != null) {
							System.out.println("Lista agendamentos --> "+listaAgendamentos);
							RowData rowData = new RowData();
							rowData.getValues().put(0, eventoVO.getId().toString());
							rowData.getValues().put(1, eventoVO.getLocal());
							rowData.getValues().put(2, eventoVO.getStatus());
							rowData.getValues().put(3, eventoVO.getParticipantes());
							rowData.getValues().put(4, eventoVO.getNomeCliente());
							rowData.getValues().put(5, eventoVO.getTipoServico());
							rowData.getValues().put(6, eventoVO.getEmail());
							rowData.getValues().put(7, eventoVO.getDataHoraInicio());
							rowData.getValues().put(8, eventoVO.getDataHoraFim());
		
							rowData.setElement(eventoVO);
							tableModel.addRow(rowData);
						}else {
							JOptionPane.showMessageDialog(null, "Sem Agendamentos no momento!", null, JOptionPane.WARNING_MESSAGE);
						}
						
					}
					
					
				} catch (Exception e) {
					throw new BOValidationException("Código: erro de validação" + " valor inválido");
				}
			
			
			
		} catch (BOValidationException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de validação", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de sistema", JOptionPane.ERROR_MESSAGE);
		}

	}
}
