package br.com.senac.dao;

import java.text.ParseException;

import javax.swing.JOptionPane;

import br.com.senac.view.TelaAgendamentos;

public class MetodosDAO {
	

	//fechar tela agendamentos ao Editar
	public void fecharTelaAgendamento() {
		
		 TelaAgendamentos frame = TelaAgendamentos.getFrame();
	        if (frame != null) {
	            frame.setVisible(false);
	            frame.dispose();
	        }
		
			
	}

}
