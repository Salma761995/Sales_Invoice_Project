/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales_inv.model;

import com.sales_inv.view.sales_invoice_frame;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;



public class InvoiceHeaderTableModel extends AbstractTableModel
{
    private ArrayList<InvoiceHeader>invoicesArray;
    private String[] columns =  { "number of invoice", "Date of invoice","customer's name","invoice total"};

    public ArrayList<InvoiceHeader> getInvoicesArray() {
        return invoicesArray;
    }

    public void setInvoicesArray(ArrayList<InvoiceHeader> invoicesArray) {
        this.invoicesArray = invoicesArray;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }
           
            
              
               
           
                 
        

    public InvoiceHeaderTableModel(ArrayList<InvoiceHeader> invoicesArray) {
        this.invoicesArray = invoicesArray;
    }
    



    @Override
    public int getRowCount() {
        return invoicesArray.size() ; 
    }

    @Override
    public int getColumnCount() {
         return columns.length;    
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader Inv = invoicesArray.get(rowIndex);
        switch(columnIndex){
        case 0 : return Inv.getInv_num();
        case 1:return  Inv.getInv_customer();
        case 2 :return sales_invoice_frame.getDateFormat().format(Inv.getInv_Date());
        case 3 :return Inv.getInvoiceTotal();
        }
          return "success" ;    
    }
    @Override
    public String getColumnName(int column)
    {
        
        return columns[column];
    }

    public int getSelectedRow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
    

