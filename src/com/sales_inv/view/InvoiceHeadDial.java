/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales_inv.view;

import com.sales_inv.controller.InvoiceActionListener;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class InvoiceHeadDial extends JDialog {
    private JTextField customerName;
    private JTextField dateInvoice;
    private JLabel label_cust_name;
    private JLabel label_Date;
    private JButton ok_Button;
    private JButton cancel_Button;

    public InvoiceHeadDial(sales_invoice_frame frame) {
        customerName = new JTextField("CustomerName:");
        customerName = new JTextField(20);
        label_Date = new JLabel("Invoice Date:");
        dateInvoice = new JTextField(20);
        ok_Button = new JButton("OK");
        cancel_Button = new JButton("Cancel");
        
        ok_Button.setActionCommand("newInvoice");
        cancel_Button.setActionCommand("line_InvoiceCancel");
        
        ok_Button.addActionListener(frame.getActionListener());
        cancel_Button.addActionListener(frame.getActionListener());
        setLayout(new GridLayout(3, 2));
        
        add(label_Date);
        add(dateInvoice);
        add(label_cust_name);
        add(customerName);
        add(ok_Button);
        add(cancel_Button);
        
        pack();
        
    }

    public JTextField getCustNameField() {
        return customerName;
    }

    public JTextField getInvDateField() {
        return dateInvoice;
    }
    
}

