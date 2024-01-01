package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.exception.BOException;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

public class TelaAgendamentos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private TableModel tableModel;
	private CadastroPessoaView cadastroPessoaView;
	private JList<String> clienteList;
	private JFormattedTextField ftfCliente;
	private List<EventoVO> listaAgendamentos;
	private JFormattedTextField ftfSFiltroStatus;
	

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
	


	public TelaAgendamentos() throws ParseException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				CadastroPessoaView cadastroPessoaView = new CadastroPessoaView();
				cadastroPessoaView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaAgendamentos.class.getResource("/br/com/senac/view/img/LogoSTYLEMANAGER black.png")));
		setTitle("EVENTOS - AGENDA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1244, 400);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// Lista de Clientes na busca de campos
		clienteList = new JList<>();
		clienteList.setBounds(325, 49, 142, 141);
		contentPane.add(clienteList);
		clienteList.setVisible(false);

		// Exibe a data e hora atuais no campo "Data e Hora"
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(23, 60, 942, 242);
		contentPane.add(scrollPane);

		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Local");
		tableModel.addColumn("Data hora inicio");
		tableModel.addColumn("Data hora fim");
		tableModel.addColumn("Status");
		tableModel.addColumn("Tipo serviço");
		tableModel.addColumn("Cliente");
		tableModel.addColumn("E-mail");
		tableModel.addColumn("N°-Pessoas");

		table = new JTable(tableModel);
	    table.setDefaultRenderer(Object.class, new MonthColorRenderer());
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(40);
		tcm.getColumn(1).setPreferredWidth(80);
		tcm.getColumn(2).setPreferredWidth(120);
		tcm.getColumn(3).setPreferredWidth(120);
		tcm.getColumn(4).setPreferredWidth(190);
		tcm.getColumn(5).setPreferredWidth(120);
		tcm.getColumn(6).setPreferredWidth(110);
		tcm.getColumn(7).setPreferredWidth(140);
		tcm.getColumn(8).setPreferredWidth(60);

		scrollPane.setViewportView(table);

		JLabel lblListaAgendamento = new JLabel("Agendamentos:");
		lblListaAgendamento.setForeground(new Color(131, 131, 131));
		lblListaAgendamento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblListaAgendamento.setBounds(79, 31, 127, 18);
		contentPane.add(lblListaAgendamento);

		JFormattedTextField ftfDataAtual = new JFormattedTextField();
		ftfDataAtual.setEditable(false);
		ftfDataAtual.setBounds(1087, 34, 113, 20);
		contentPane.add(ftfDataAtual);
		ftfDataAtual.setText(sdf.format(new Date()));

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(TelaAgendamentos.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarAgendamentos();

			}
		});
		btnPesquisar.setBounds(23, 318, 127, 23);
		contentPane.add(btnPesquisar);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(TelaAgendamentos.class.getResource("/br/com/senac/view/img/dateTime.png")));
		btnNewButton.setBounds(1062, 31, 26, 23);
		contentPane.add(btnNewButton);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cadastroPessoaView == null) {
					cadastroPessoaView = new CadastroPessoaView();
				}
				cadastroPessoaView.setVisible(true);
				dispose();
			}

		});
		btnVoltar.setIcon(new ImageIcon(TelaAgendamentos.class
				.getResource("/br/com/senac/view/img/2303132_arrow_back_direction_left_navigation_icon.png")));
		btnVoltar.setBounds(1100, 323, 100, 23);
		contentPane.add(btnVoltar);

		JLabel lbliconePessoasAgend = new JLabel("");
		lbliconePessoasAgend.setIcon(new ImageIcon(
				TelaAgendamentos.class.getResource("/br/com/senac/view/img/46832_group_search_icon.png")));
		lbliconePessoasAgend.setBackground(new Color(192, 192, 192));
		lbliconePessoasAgend.setBounds(23, 11, 60, 54);
		contentPane.add(lbliconePessoasAgend);

		JLabel lblAgendaAberta = new JLabel("");
		lblAgendaAberta
				.setIcon(new ImageIcon(TelaAgendamentos.class.getResource("/br/com/senac/view/img/AgendaAberta.png")));
		lblAgendaAberta.setBounds(994, 70, 206, 235);
		contentPane.add(lblAgendaAberta);

		JButton btnNewButton_1 = new JButton("Excluir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();

			}
		});
		btnNewButton_1.setIcon(new ImageIcon(TelaAgendamentos.class.getResource("/br/com/senac/view/img/remove.png")));
		btnNewButton_1.setBounds(160, 318, 113, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setForeground(new Color(128, 128, 128));
		lblCliente.setBounds(281, 35, 49, 14);
		contentPane.add(lblCliente);
		
		// edição campo nome
		clienteList.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()) {
							String selectedNome = clienteList.getSelectedValue();
							if (selectedNome != null) {
								ftfCliente.setText(selectedNome);
								clienteList.setVisible(false);
							}
						}
					}
				});
		
		MaskFormatter maskaraData = new MaskFormatter("##/##/#### ##:##:##");

		ftfCliente = new JFormattedTextField();
		ftfCliente.setBounds(325, 32, 142, 18);
		contentPane.add(ftfCliente);
		
		JButton btnOrdenar = new JButton("Ordenar data");
		btnOrdenar.setIcon(new ImageIcon(TelaAgendamentos.class.getResource("/br/com/senac/view/img/ordenar.png")));
		btnOrdenar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOrdenar.setForeground(new Color(138, 138, 138));
		btnOrdenar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenarData();
			}
		});
		btnOrdenar.setBounds(754, 31, 142, 18);
		contentPane.add(btnOrdenar);
		
		JLabel lblNewLabel = new JLabel("Status:");
		lblNewLabel.setForeground(new Color(138, 138, 138));
		lblNewLabel.setBounds(504, 35, 46, 14);
		contentPane.add(lblNewLabel);
		
		ftfSFiltroStatus = new JFormattedTextField();
		ftfSFiltroStatus.setBounds(550, 33, 155, 18);
		contentPane.add(ftfSFiltroStatus);
		
		JButton btnEditarAgendamento = new JButton("Editar");
		btnEditarAgendamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarAgendamento();
			}
		});
		btnEditarAgendamento.setIcon(new ImageIcon(TelaAgendamentos.class.getResource("/br/com/senac/view/img/editar.png")));
		btnEditarAgendamento.setBounds(283, 318, 113, 23);
		contentPane.add(btnEditarAgendamento);

	}


	protected void editarAgendamento() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar ao menos um agendamento para Editar!",
					"Mensagem de aviso.", JOptionPane.WARNING_MESSAGE);
		} else {
			System.out.println("Método de edição do Agendamento.");
			
			
			try {
				TelaAgendar edtAgd = new TelaAgendar();
				EventoVO aux = (EventoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();

				edtAgd.editar(aux);
				edtAgd.setVisible(true);

			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocorreu um erro", "Erro.", JOptionPane.WARNING_MESSAGE);
			}
			
			
		}
		
		
		
	}
	
	



	protected void ordenarData() {
		listarAgendamentos();
		Collections.sort(listaAgendamentos, Comparator.comparing(EventoVO::getDataHoraInicio));
		tableModel.clearTable(); 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		for (EventoVO eventoVO : listaAgendamentos) {

			if (eventoVO.getId() != null) {
				System.out.println("Lista agendamentos --> " + listaAgendamentos);
				RowData rowData = new RowData();
				rowData.getValues().put(0, eventoVO.getId().toString());
				rowData.getValues().put(1, eventoVO.getLocal());
				rowData.getValues().put(2, sdf.format(eventoVO.getDataHoraInicio()));
		        rowData.getValues().put(3, sdf.format(eventoVO.getDataHoraFim()));
				rowData.getValues().put(4, eventoVO.getStatus());
				rowData.getValues().put(5, eventoVO.getTipoServico());
				rowData.getValues().put(6, eventoVO.getNomeCliente());
				rowData.getValues().put(7, eventoVO.getEmail());
				rowData.getValues().put(8, eventoVO.getParticipantes());

				rowData.setElement(eventoVO);
				tableModel.addRow(rowData);
			} else {
				JOptionPane.showMessageDialog(null, "Sem Agendamentos no momento!", null,
						JOptionPane.WARNING_MESSAGE);
			}

		}
		
	}

	protected void excluir() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro! ", "Mensagem de aviso",
					JOptionPane.WARNING_MESSAGE);
		} else {
			Object[] options = { "Sim!", "Não" };
			int n = JOptionPane.showOptionDialog(this, // não deixa clicar na tela de baixo
					"Deseja realmente exluir o registro? ", "Confirmação", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (n == 0) {
				EventoVO evento = (EventoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();
				Service service = new Service();

				try {
					service.excluir(evento);

					JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!", "Mensagem de aviso",
							JOptionPane.INFORMATION_MESSAGE);
					listarAgendamentos();

				} catch (BOValidationException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de aviso",
							JOptionPane.WARNING_MESSAGE);

				} catch (BOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	
	}
	@SuppressWarnings("unused")
	protected void listarAgendamentos() {
		if (tableModel != null) {
			tableModel.clearTable();
		}

		BigInteger id = null;
		Date dataHoraInicio = null;
		Date dataHoraFim = null;
		String local = null;
 		BigInteger participantes = null;
		// List<BigInteger> participantes = null;
		String status = null;
		String filtroStatus = null;
		String tipoServico = null;
		String nomeCliente = null;

		try {

			try {

				
				if (this.ftfCliente.getText() != null && ftfCliente.getText().trim().length() > 0) {
					nomeCliente = ftfCliente.getText().trim();
				}
				
				
				if (this.ftfSFiltroStatus.getText() != null && ftfSFiltroStatus.getText().trim().length() > 0) {
					filtroStatus = ftfSFiltroStatus.getText().trim();
				}
				

				if (this.ftfCliente.getText() != null && ftfCliente.getText().trim().length() > 0) {
					nomeCliente = ftfCliente.getText().trim();
				}
				   
				
				System.out.println("******* Iniciando consulta de agendamentos *******");
				EntityManager em = HibernateUtil.getEntityManager();

				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<EventoVO> criteria = cb.createQuery(EventoVO.class);

				// Clausula from
				Root<EventoVO> agendamentosFrom = criteria.from(EventoVO.class);
				criteria.select(agendamentosFrom);
				
				
				if (nomeCliente != null) {
					String searchTerm = "%" + nomeCliente.toLowerCase() + "%";
					criteria.where(cb.like(cb.lower(agendamentosFrom.get("nomeCliente")), searchTerm));
				} 
				
				//filtroStatus
				if (filtroStatus != null) {
					String searchTerm = "%" + filtroStatus.toLowerCase() + "%";
					criteria.where(cb.like(cb.lower(agendamentosFrom.get("status")), searchTerm));
				}

				
				TypedQuery<EventoVO> query = em.createQuery(criteria);
				listaAgendamentos = query.getResultList();

				System.out.println("Lista agendamentos --> " + listaAgendamentos);
				

				for (EventoVO eventoVO : listaAgendamentos) {

					if (eventoVO.getId() != null) {
						System.out.println("Lista agendamentos --> " + listaAgendamentos);
						RowData rowData = new RowData();
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					
						rowData.getValues().put(0, eventoVO.getId().toString());
						rowData.getValues().put(1, eventoVO.getLocal());
						rowData.getValues().put(2, dateFormat.format(eventoVO.getDataHoraInicio()));
				        rowData.getValues().put(3, dateFormat.format(eventoVO.getDataHoraFim()));
						rowData.getValues().put(4, eventoVO.getStatus());
						rowData.getValues().put(5, eventoVO.getTipoServico());
						rowData.getValues().put(6, eventoVO.getNomeCliente());
						rowData.getValues().put(7, eventoVO.getEmail());
						rowData.getValues().put(8, eventoVO.getParticipantes());

						rowData.setElement(eventoVO);
						tableModel.addRow(rowData);
					} else {
						JOptionPane.showMessageDialog(null, "Sem Agendamentos no momento!", null,
								JOptionPane.WARNING_MESSAGE);
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

 
	

