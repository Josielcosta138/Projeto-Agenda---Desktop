package br.com.senac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.exception.BOValidationException;
import br.com.senac.vo.EventoVO;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Disponibilidade extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private CadastroPessoaView cadastroPessoaView;
	private TableModel tableModel;
	private List<EventoVO> listaAgendamentos;

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
		tableModel.addColumn("Cód");
		tableModel.addColumn("Status");
		tableModel.addColumn("Data hora inicio");
		tableModel.addColumn("Data hora fim");
		tableModel.addColumn("Local");
		
		
		
		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(40);
		tcm.getColumn(1).setPreferredWidth(140);
		tcm.getColumn(2).setPreferredWidth(140);
		tcm.getColumn(3).setPreferredWidth(140);
		tcm.getColumn(4).setPreferredWidth(120);
		

		scrollPane.setViewportView(table);
		
		//table = new JTable();
		//scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Disponibilidade de horários");
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
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				listarDisponibilidade();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Disponibilidade.class.getResource("/br/com/senac/view/img/pesquisar.png")));
		btnNewButton.setBounds(10, 301, 125, 22);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				voltar();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Disponibilidade.class.getResource("/br/com/senac/view/img/2303132_arrow_back_direction_left_navigation_icon.png")));
		btnNewButton_1.setBounds(654, 301, 105, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Disponibilidade.class.getResource("/br/com/senac/view/img/dateTime.png")));
		lblNewLabel_2.setBounds(630, 11, 24, 20);
		contentPane.add(lblNewLabel_2);
	}

	protected void listarDisponibilidade() {
		
		if (tableModel != null) {
			tableModel.clearTable();
		}


		try {

			try {

				System.out.println("******* Iniciando consulta de agendamentos *******");
		        EntityManager em = HibernateUtil.getEntityManager();
		        CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<EventoVO> criteria = cb.createQuery(EventoVO.class);

				Root<EventoVO> agendamentosFrom = criteria.from(EventoVO.class);
		        criteria.select(agendamentosFrom);

		        // Adicionando filtro para trazer apenas eventos do mês atual
		        Predicate predicate = cb.and(
		                cb.equal(cb.function("MONTH", Integer.class, agendamentosFrom.get("dataHoraInicio")), cb.function("MONTH", Integer.class, cb.currentDate())),
		                cb.equal(cb.function("YEAR", Integer.class, agendamentosFrom.get("dataHoraInicio")), cb.function("YEAR", Integer.class, cb.currentDate())),
		                cb.not(agendamentosFrom.get("status").in("AGENDANDO", "AGUARDANDO_CONFIRMACAO"))
		        );
		        
		        criteria.where(predicate);
		        
		        // Adicionando ORDER BY para ordenar por ID em ordem decrescente
		        criteria.orderBy(cb.desc(agendamentosFrom.get("id")));
		        TypedQuery<EventoVO> query = em.createQuery(criteria);

		        //query.setMaxResults(1);
		        listaAgendamentos = query.getResultList();
		        
		        
		        // Criar uma estrutura para armazenar os dias e horários já agendados
		        Map<Integer, Set<Integer>> diasEHorariosAgendados = new HashMap<>();
		        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
		        
		        
		        for (EventoVO eventoVO : listaAgendamentos) {
		            int dia = Integer.parseInt(dayFormat.format(eventoVO.getDataHoraInicio()));
		            int horario = Integer.parseInt(hourFormat.format(eventoVO.getDataHoraInicio()));
		            
		            diasEHorariosAgendados.computeIfAbsent(dia, k -> new HashSet<>()).add(horario);
		        }
		        
		        
		     // Obter o último dia do mês atual
		        Calendar calendar = Calendar.getInstance();
		        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		        int ultimoDiaDoMes = Integer.parseInt(dayFormat.format(calendar.getTime()));

		        // Adicionar os dias e horários livres na tabela
		        for (int dia = 1; dia <= ultimoDiaDoMes; dia++) {
		            for (int horario = 0; horario < 24; horario++) {
		                if (!diasEHorariosAgendados.getOrDefault(dia, Collections.emptySet()).contains(horario)) {
		                    RowData rowData = new RowData();
		                    rowData.getValues().put(0, "");  // Coluna de código vazia para dias/horários livres
		                    rowData.getValues().put(1, "Livre");
		                    rowData.getValues().put(2, String.format("%02d/%02d", dia, horario));  // Data/hora para dias/horários livres
		                    rowData.getValues().put(3, "");  // Data/hora fim vazia para dias/horários livres
		                    rowData.getValues().put(4, "");  // Local vazio para dias/horários livres
		                    tableModel.addRow(rowData);
		                }
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

	protected void voltar() {
		if (cadastroPessoaView == null) {
			cadastroPessoaView = new CadastroPessoaView();
		}
		cadastroPessoaView.setVisible(true);
		dispose();
	}
		
}
