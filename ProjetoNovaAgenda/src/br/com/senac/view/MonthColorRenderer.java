package br.com.senac.view;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MonthColorRenderer extends DefaultTableCellRenderer{

	  private static final long serialVersionUID = 1L;
	  
	  
	  
	  @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	        if (!isSelected) {
	            Date date = getDateFromTable(table, row);
	            if (date != null) {
	                int month = getMonthFromDate(date);
	                Color backgroundColor = (month % 2 == 0) ? new Color(173, 216, 230) : Color.WHITE; // Alternando cores
	                component.setBackground(backgroundColor);
	            }
	        }

	        return component;
	    }

	    private Date getDateFromTable(JTable table, int row) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	        try {
	            return dateFormat.parse(table.getValueAt(row, 2).toString()); // Assuming column index 2 is the date column
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    private int getMonthFromDate(Date date) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        return calendar.get(Calendar.MONTH);
	    }
	
}
