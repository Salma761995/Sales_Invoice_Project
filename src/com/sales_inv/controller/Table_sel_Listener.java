/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales_inv.controller;
import com.sales_inv.model.InvoiceHeader;
import com.sales_inv.model.InvoiceLine;
import com.sales_inv.model.InvoiceLineTableModel;
import javax.swing.event.ListSelectionEvent;
import javax .swing.event.ListSelectionListener;
import com.sales_inv.view.sales_invoice_frame;
import java.util.ArrayList;
/**
 *
 * @author Abdalla
 */
public class Table_sel_Listener implements ListSelectionListener {
    
private sales_invoice_frame frame ;

    public Table_sel_Listener(sales_invoice_frame frame) {
        this.frame = frame;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedInvIndex = frame.getInvoice_Header_table().getSelectedRow();
       System.out.println("the selected invoice"+selectedInvIndex);
       if (selectedInvIndex!=-1){
           
       
       
       InvoiceHeader selected_inv =frame.getInvoicesArray().get(selectedInvIndex);
       ArrayList<InvoiceLine>lines =selected_inv.getLines();
       InvoiceLineTableModel linetablemodel = new InvoiceLineTableModel (lines);
       
       frame.setArray_lines(lines);
       frame.getInvoice_Header_table().setModel(linetablemodel);
       frame.getCustomer_name().setText(selected_inv.getInv_customer());
      frame.getInvoice_number().setText(""+selected_inv.getInv_num());
      frame.getInvoice_date().setText(sales_invoice_frame.getDateFormat().format(selected_inv.getInv_Date()));
          
      frame.getInvoicetotal().setText(""+selected_inv.getInvoiceTotal());
}
}}
