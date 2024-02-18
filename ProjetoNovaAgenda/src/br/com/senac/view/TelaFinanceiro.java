package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import Relatorio.RelatorioTeste;
import antlr.collections.List;
import br.com.senac.dao.HibernateUtil;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.EventoVO;
import br.com.senac.vo.RelatorioItem;
import br.com.senac.vo.StatusServico;
import br.com.senac.vo.VendaVO;
import net.sf.jasperreports.engine.JRException;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;

public class TelaFinanceiro extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableModel tableModel;
	private java.util.List<VendaVO> listagemDeVendas;
	private java.util.List<EventoVO> listaAgendamentos;
	private java.util.List<EventoVO> listaServicos;
	private java.util.List<VendaVO> listaServicosFinal;
	public java.util.List<String> listaRelatorio;
	public BigDecimal totalAgendamentoVal = BigDecimal.ZERO;
	public StatusServico statusServico; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFinanceiro frame = new TelaFinanceiro();
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
	public TelaFinanceiro() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				TelaAcessosView telaAcessosView = new TelaAcessosView();
				telaAcessosView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaFinanceiro.class.getResource("/br/com/senac/view/img/business.png")));
		setTitle("FINANCEIRO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 834);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Financeiro ");
		lblNewLabel.setBounds(10, 27, 92, 14);
		lblNewLabel.setForeground(new Color(97, 97, 97));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(112, 10, 30, 40);
		lblNewLabel_1.setIcon(new ImageIcon(TelaFinanceiro.class.getResource("/br/com/senac/view/img/finance.png")));
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 52, 619, 1);
		panel.setBorder(new LineBorder(new Color(97, 97, 97)));
		contentPane.add(panel);
		
		JLabel lblAgendamentos = new JLabel("Totais Agendamentos R$");
		lblAgendamentos.setBounds(10, 86, 206, 28);
		lblAgendamentos.setIcon(new ImageIcon(TelaFinanceiro.class.getResource("/br/com/senac/view/img/tipo.png")));
		lblAgendamentos.setForeground(new Color(97, 97, 97));
		lblAgendamentos.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblAgendamentos);
		
		JLabel lblVendasProdutos = new JLabel("Totais Vendas produtos R$");
		lblVendasProdutos.setBounds(10, 305, 206, 28);
		lblVendasProdutos.setIcon(new ImageIcon(TelaFinanceiro.class.getResource("/br/com/senac/view/img/comprarProdut.png")));
		lblVendasProdutos.setForeground(new Color(97, 97, 97));
		lblVendasProdutos.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblVendasProdutos);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setBounds(112, 305, 30, 28);
		contentPane.add(lblNewLabel_1_1_1);
		
		JScrollPane scrollPaneAgendamentos = new JScrollPane();
		scrollPaneAgendamentos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneAgendamentos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneAgendamentos.setBounds(10, 127, 619, 85);
		contentPane.add(scrollPaneAgendamentos);
		
		tableModel = new TableModel();
		tableModel.addColumn("Código"); 
		tableModel.addColumn("Cliente"); 
		tableModel.addColumn("DD");
		tableModel.addColumn("Tefone"); 
		tableModel.addColumn("Total R$"); 

		
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new MonthColorRenderer());
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(60);
		tcm.getColumn(1).setPreferredWidth(180);
		tcm.getColumn(2).setPreferredWidth(100);
		tcm.getColumn(3).setPreferredWidth(140);
		tcm.getColumn(4).setPreferredWidth(140);
		
		scrollPaneAgendamentos.setViewportView(table);
		listarVendasAgendamentos();
		
		
		JScrollPane scrollPaneVendProdutos = new JScrollPane();
		scrollPaneVendProdutos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneVendProdutos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneVendProdutos.setBounds(10, 344, 619, 85);
		contentPane.add(scrollPaneVendProdutos);
		
		
		tableModel = new TableModel();
		tableModel.addColumn("Código"); 
		tableModel.addColumn("Produto"); 
		tableModel.addColumn("Quantidade");
		tableModel.addColumn("Valor"); 
		tableModel.addColumn("Total R$"); 

		
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new MonthColorRenderer());
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm2 = table.getColumnModel();
		tcm2.getColumn(0).setPreferredWidth(60);
		tcm2.getColumn(1).setPreferredWidth(180);
		tcm2.getColumn(2).setPreferredWidth(100);
		tcm2.getColumn(3).setPreferredWidth(140);
		tcm2.getColumn(4).setPreferredWidth(140);
		
		scrollPaneVendProdutos.setViewportView(table);
		listarTotaisVendasProd();
		
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAcessosView telaAcessosView = new TelaAcessosView();
				telaAcessosView.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		btnVoltar.setIcon(new ImageIcon(TelaFinanceiro.class.getResource("/br/com/senac/view/img/2303132_arrow_back_direction_left_navigation_icon.png")));
		btnVoltar.setBounds(517, 769, 112, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblTotaisServiosR = new JLabel("Totais Serviços R$");
		lblTotaisServiosR.setIcon(new ImageIcon(TelaFinanceiro.class.getResource("/br/com/senac/view/img/tesoura2.png")));
		lblTotaisServiosR.setForeground(new Color(97, 97, 97));
		lblTotaisServiosR.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotaisServiosR.setBounds(10, 534, 206, 28);
		contentPane.add(lblTotaisServiosR);
		
		
		//---------------SERVIÇOS---------------
		JScrollPane scrollPaneVendServicos = new JScrollPane();
		scrollPaneVendServicos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneVendServicos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneVendServicos.setBounds(10, 573, 619, 114);
		contentPane.add(scrollPaneVendServicos);
				
		tableModel = new TableModel();
		tableModel.addColumn("Código"); 
		tableModel.addColumn("Serviço"); 
		tableModel.addColumn("Quantidade");
		tableModel.addColumn("Valor"); 
		tableModel.addColumn("Total R$"); 
		
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new MonthColorRenderer());
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm3 = table.getColumnModel();
		tcm3.getColumn(0).setPreferredWidth(60);
		tcm3.getColumn(1).setPreferredWidth(180);
		tcm3.getColumn(2).setPreferredWidth(100);
		tcm3.getColumn(3).setPreferredWidth(140);
		tcm3.getColumn(4).setPreferredWidth(140);
		scrollPaneVendServicos.setViewportView(table);
		listarTotaisDeServicos();
		
		
		
		//---------------AGENDAMENTOS---------------
		JScrollPane scrollPaneTotalAgendamento = new JScrollPane();
		scrollPaneTotalAgendamento.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneTotalAgendamento.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTotalAgendamento.setBounds(445, 223, 184, 40);
		contentPane.add(scrollPaneTotalAgendamento);
		
		
	
		tableModel = new TableModel();
		tableModel.addColumn("Total de agendamentos R$"); 
		
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new MonthColorRenderer());
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm4 = table.getColumnModel();
		tcm4.getColumn(0).setPreferredWidth(180);
		scrollPaneTotalAgendamento.setViewportView(table); 
		listarTotalFinalAgendamentos();
		
		
		//---------------PRODUTOS---------------
		JScrollPane scrollPaneTotalProdutos = new JScrollPane();
		scrollPaneTotalProdutos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneTotalProdutos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTotalProdutos.setBounds(445, 440, 184, 40);
		contentPane.add(scrollPaneTotalProdutos);
		
		tableModel = new TableModel();
		tableModel.addColumn("Total de produtos R$"); 
		
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new MonthColorRenderer());
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm5 = table.getColumnModel();
		tcm5.getColumn(0).setPreferredWidth(180);
		scrollPaneTotalProdutos.setViewportView(table); 
		listarTotalFinalVendaProdutos();
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(200, 200, 200));
		panel_1.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_1.setBounds(10, 274, 619, 1);
		contentPane.add(panel_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_1_1.setBackground(UIManager.getColor("ScrollBar.background"));
		panel_1_1.setBounds(10, 491, 619, 1);
		contentPane.add(panel_1_1);
		
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_1_1_1.setBackground(UIManager.getColor("ScrollBar.background"));
		panel_1_1_1.setBounds(10, 749, 619, 1);
		contentPane.add(panel_1_1_1);
		
		
		//---------------SERVIÇOS---------------
		JScrollPane scrollPaneTotalServicos = new JScrollPane();
		scrollPaneTotalServicos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneTotalServicos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTotalServicos.setBounds(257, 698, 372, 40);
		contentPane.add(scrollPaneTotalServicos);
		
		
		tableModel = new TableModel();
		tableModel.addColumn("Serviço com maior faturamento R$"); 
		tableModel.addColumn("Valor R$"); 
		
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new MonthColorRenderer());
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tcm6 = table.getColumnModel();
		tcm6.getColumn(0).setPreferredWidth(200);
		tcm6.getColumn(1).setPreferredWidth(200);
		scrollPaneTotalServicos.setViewportView(table);
		listarTotalFinalVendaServicos();
		
	
	}
	
	//DESENVOLVENDO
	@SuppressWarnings("unlikely-arg-type")
	private void listarTotalFinalVendaServicos() {
		System.out.println("******* Iniciando listagem total de final de agendamentos *******");
	    EntityManager em = HibernateUtil.getEntityManager();

	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    
	    // Define o tipo de retorno da consulta como um array de objetos
	    CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
	    Root<EventoVO> root = query.from(EventoVO.class);

	    // Seleciona o total do serviço e o tipo de serviço
	    query.multiselect(root.get("totalservico"), root.get("tiposervico"));

	    // Ordena em ordem decrescente pelo total do serviço
	    query.orderBy(cb.desc(root.get("totalservico")));

	    // Limita o resultado a apenas um registro (o maior valor)
	    TypedQuery<Object[]> typedQuery = em.createQuery(query).setMaxResults(1);

	    // Executa a consulta
	    Object[] result = typedQuery.getSingleResult();
	    
	    // Extrai o total do serviço e o tipo de serviço do resultado
	    BigDecimal totalSoma = (BigDecimal) result[0];
	    String tipoServico = (String) result[1];

	    StatusServico statusServicoEnum = StatusServico.valueOf(tipoServico);
	    tipoServico = statusServicoEnum.getDescricao();
	    
	    // Formata o total do serviço
	    String totalFormatted = String.format("%.2f", totalSoma.doubleValue()) + " R$";
	    
	    
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

	    // Adiciona o total do serviço e o tipo de serviço ao rowData
	    RowData rowData = new RowData();
	    rowData.getValues().put(0, tipoServico);
	    //rowData.getValues().put(0, statusServico.getDescricao());
	    rowData.getValues().put(1, totalFormatted);
	    

	    // Adiciona a linha ao tableModel
	    tableModel.addRow(rowData);
		
		
	}



	private void listarTotalFinalVendaProdutos() {
		
		System.out.println("******* Iniciando listagem total de final de produtos *******");

		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		
		CriteriaQuery<BigDecimal> sumQuery = cb.createQuery(BigDecimal.class);
		Root<VendaVO> root = sumQuery.from(VendaVO.class);
		sumQuery.select(cb.sum(root.get("total")));
		BigDecimal totalSoma = em.createQuery(sumQuery).getSingleResult();

	
		String totalFormattedProd = String.format("%.2f", totalSoma.doubleValue());

		
		CriteriaQuery<VendaVO> criteria = cb.createQuery(VendaVO.class);
		Root<VendaVO> rootVenda = criteria.from(VendaVO.class);
		criteria.select(rootVenda);

		
		listaServicosFinal = em.createQuery(criteria).getResultList();
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		RowData rowData = new RowData();
		for (VendaVO vendaVO : listaServicosFinal) {
		    
		    rowData.setElement(vendaVO);
			rowData.getValues().put(0, totalFormattedProd + " R$");
		    tableModel.addRow(rowData);
		   
		}
		
		
	}

	private void listarTotalFinalAgendamentos() {
		
		System.out.println("******* Iniciando listagem total de final de agendamentos *******");
		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> sumQuery = cb.createQuery(BigDecimal.class);
		Root<EventoVO> root = sumQuery.from(EventoVO.class);
		sumQuery.select(cb.sum(root.get("totalservico")));
		
		BigDecimal totalSoma = em.createQuery(sumQuery).getSingleResult();

		String totalFormatted = String.format("%.2f", totalSoma.doubleValue()); // Converte para double e formata com duas casas decimais
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		
		RowData rowData = new RowData();
		for (EventoVO eventoVO : listaAgendamentos) {

			if (eventoVO.getId() != null) {
				rowData.getValues().put(0, totalFormatted + " R$");
				rowData.setElement(eventoVO);
			} else {
				JOptionPane.showMessageDialog(null, "Sem Agendamentos no momento!", null,
						JOptionPane.WARNING_MESSAGE);
			}
		}
		tableModel.addRow(rowData);
	}

	private void listarTotaisDeServicos() {
	
		
		if (tableModel != null) {
			tableModel.clearTable();
		}

		try {

			try {				   
				
				System.out.println("******* Iniciando listagem de serviços *******");
				EntityManager em = HibernateUtil.getEntityManager();

				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<EventoVO> criteria = cb.createQuery(EventoVO.class);

				// Clausula from
				Root<EventoVO> agendamentosFrom = criteria.from(EventoVO.class);
				criteria.select(agendamentosFrom);
					
				TypedQuery<EventoVO> query = em.createQuery(criteria);
				listaServicos = query.getResultList();
				
				
				
				
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
				
				for (EventoVO eventoVO : listaServicos) {

					if (eventoVO.getId() != null) {
						System.out.println("Lista agendamentos --> " + listaServicos);
					
						 StatusServico statusServicoEnum = StatusServico.valueOf(eventoVO.getTipoServico());
						 String tipoServico = statusServicoEnum.getDescricao();
						
						RowData rowData = new RowData();
						rowData.getValues().put(0, eventoVO.getId().toString());
						rowData.getValues().put(1, tipoServico);
						rowData.getValues().put(2, eventoVO.getParticipantes());
						rowData.getValues().put(3, eventoVO.getValor() + " R$");
						rowData.getValues().put(4, eventoVO.getTotalServico() + " R$");

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

	private void listarVendasAgendamentos() {
		if (tableModel != null) {
			tableModel.clearTable();
		}

		try {

			try {				   
				
				System.out.println("******* Iniciando listagem de agendamentos *******");
				EntityManager em = HibernateUtil.getEntityManager();

				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<EventoVO> criteria = cb.createQuery(EventoVO.class);

				// Clausula from
				Root<EventoVO> agendamentosFrom = criteria.from(EventoVO.class);
				criteria.select(agendamentosFrom);
					
				TypedQuery<EventoVO> query = em.createQuery(criteria);
				listaAgendamentos = query.getResultList();
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
	
				
				 
				for (EventoVO eventoVO : listaAgendamentos) {

					if (eventoVO.getId() != null) {
						System.out.println("Lista agendamentos --> " + listaAgendamentos);
						RowData rowData = new RowData();
						rowData.getValues().put(0, eventoVO.getId().toString());
						rowData.getValues().put(1, eventoVO.getNomeCliente());
						rowData.getValues().put(2, eventoVO.getDd());
						rowData.getValues().put(3, eventoVO.getNumero());
						rowData.getValues().put(4, eventoVO.getTotalServico() + " R$");
						
						
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
		
	
	

	private void listarTotaisVendasProd() {
		
		try {
			try {
											
				System.out.println("******* Iniciando liestagem de vendas *******");
				EntityManager em = HibernateUtil.getEntityManager();

				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<VendaVO> criteria = cb.createQuery(VendaVO.class);

				// Clausula from
				Root<VendaVO> vendasFrom = criteria.from(VendaVO.class);
				criteria.select(vendasFrom);
				
		
				TypedQuery<VendaVO> query = em.createQuery(criteria);
				listagemDeVendas = query.getResultList();
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

				tableModel.clearTable(); 

				for (VendaVO vendaVOO : listagemDeVendas) {
					if (vendaVOO.getId() != null) {
						
						RowData rowData = new RowData();
						
						rowData.getValues().put(0, vendaVOO.getId().toString());
						rowData.getValues().put(1, vendaVOO.getDescricao());
						rowData.getValues().put(2, vendaVOO.getQuantidade());
						rowData.getValues().put(3, vendaVOO.getValor());
						rowData.getValues().put(4, vendaVOO.getTotal());
						

						rowData.setElement(vendaVOO);
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
