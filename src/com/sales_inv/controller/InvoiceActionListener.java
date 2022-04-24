/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales_inv.controller;
import com.sales_inv.model.InvoiceHeader;
import com.sales_inv.model.InvoiceHeaderTableModel;
import com.sales_inv.model.InvoiceLine;
import com.sales_inv.model.InvoiceLineTableModel;
import com.sales_inv.view.InvoiceHeadDial;
import com.sales_inv.view.InvoiceLineDial;
import com.sales_inv.view.sales_invoice_frame;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.Files.list;
import java.nio.file.Path;
import java.nio.file.Paths;
//import static java.rmi.Naming.list;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import static java.util.Collections.list;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdalla
 */
public class InvoiceActionListener  implements ActionListener {
     private InvoiceHeadDial headerDialog;
     private InvoiceLineDial lineDialog;
    private sales_invoice_frame frame ;
    private SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yy");
    // this is to 
    public InvoiceActionListener (sales_invoice_frame frame) 
    {
        this.frame = frame ; 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       switch (e.getActionCommand ()){
           case "savefiles" :
               savefiles();
                   break ; 
           case " Loadfiles" :
               Loadfiles();
               break ; 
           case "createNewInvoice ":
               createNewInvoice();
               
               break ;
           case "delete invoice" :
               deleteInvoice();
               break ; 
           case "New Line " :
               CreateNewLine();
               break ;
           case "Delete_line" :
               DeleteLine();
               break ;
               
           case "newInvoice" :
                okDialogue ();
               break ; 
           case "InvoiceCancel":
              cancelDialouge();
               break ; 
           case  "cancel_line_invoice":
               cancelLineInvoice();
               break ; 
           case "create_new_line":
               okLineDial_Invoice();
               break  ; 
               
               
       }
    }

   private void Loadfiles() {
        JFileChooser fileChooser = new JFileChooser();
        { try {
        int result  =  fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION)
        {
          File headerFile=  fileChooser.getSelectedFile();
          //FileReader fr = new FileReader(headerFile);
          Path headerPath = Paths.get(headerFile.getAbsolutePath());
          List<String> headerLines = Files.readAllLines(headerPath);
          ArrayList<InvoiceHeader>invoiceHeaders = new ArrayList<>(); // its an array carry a group of strins each represented in line 
          for (String headerLine:headerLines){
           String[] arr = headerLine.split(",");
            String string1=arr [0]; 
            String string2=arr [1]; 
            String string3=arr [2]; 
            int code  = Integer.parseInt(string1);
            Date invoiceDate=dateFormat.parse(string2);//
            //this is to convert the string into date using the format upside which is the "dd-mm-yy"
            InvoiceHeader header = new InvoiceHeader(code,string3,invoiceDate);
            invoiceHeaders.add(header);
            frame.setInvoicesArray(invoiceHeaders);
          }
          result  =  fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION){
            File lineFile =  fileChooser.getSelectedFile();
            Path linePath=Paths.get(lineFile.getAbsolutePath());
              List<String>lineLines = Files.readAllLines(linePath); 
            ArrayList<InvoiceLine>invoiceLines = new ArrayList<>();
            for (String lineLine :lineLines)
            {String[] Arr = lineLine.split(",");
             String strg1=Arr [0]; // this is for storang the number of invoice which is will be int 
            String strg2=Arr [1]; // this is for storage the name of the item (string )
            String strg3=Arr[2]; // this is to know the price of the item 
             String strg4=Arr [2]; // that;s for the count which will be int 
             // its important to specify the return type of each variable to know the conversion 
             int invcode = Integer.parseInt(strg1);
             double price = Double.parseDouble(strg3);
             int count = Integer.parseInt(strg4);
           InvoiceHeader Inv =  frame.getInvObject(invcode);
          InvoiceLine line = new InvoiceLine(strg2, price, count, Inv);
           Inv.getLines().add(line);
        }
        }
        InvoiceHeaderTableModel headerTable = new InvoiceHeaderTableModel(invoiceHeaders);
         frame.setHeadertableModel(headerTable);
        frame.getInvoice_Header_table().setModel(headerTable);
             
        }}
        
        catch(IOException ex)
                {
                JOptionPane.showMessageDialog(frame,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
        
        catch (ParseException ex )
        {
          JOptionPane.showMessageDialog(frame,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);  
        }
        
        
        }}
    private void createNewInvoice() {
       InvoiceHeadDial headerDialog = new InvoiceHeadDial(frame)  ;
       headerDialog.setVisible(true);
       String customerName = headerDialog.getCustNameField().getText();
       String date= headerDialog.getInvDateField().getText();
       Date dat = new Date ();
       try{
       dat=sales_invoice_frame.getDateFormat().parse(date);
       }  catch (ParseException ex ){
               JOptionPane.showMessageDialog(frame,"convert to today","Invalid date ",JOptionPane.ERROR_MESSAGE);
               }
       
       int numberOfinvoice = 0 ;
     /*  for (InvoiceHeader Inv :frame.getInvoicesArray());
       {
           if (Inv.getInv_num()>numberOfinvoice)  numberOfinvoice=Inv.getInv_num();
       }
       numberOfinvoice++;*/
       InvoiceHeader inv_head = new InvoiceHeader (2,customerName,dat);
       frame.getInvoicesArray().add(inv_head);
       headerDialog.dispose();
        headerDialog = null ;
    
        }
    private void deleteInvoice() {
        
        int selectedInvoiceIndex = frame.getHeadertableModel().getSelectedRow();
       if (selectedInvoiceIndex!= -1)
       {
          frame.getInvoicesArray().remove(selectedInvoiceIndex);
          frame.getHeadertableModel().fireTableDataChanged();
          frame.getInvoice_Header_table().setModel(new InvoiceLineTableModel(null));
          frame.setArray_lines(null);
          
          frame.getCustomer_name().setText("");
          frame.getInvoice_number().setText("");
          frame.getInvoice_date().setText("");
          
          frame.getInvoicetotal().setText("");
       }
        
    }

    private void CreateNewLine() {
        InvoiceLineDial lineDialog = new InvoiceLineDial(frame)  ;
       lineDialog.setVisible(true);
      
       
        
    }

    private void DeleteLine() {
        
    }

    private void savefiles() {
        
    }

    private void cancelDialouge() {
        headerDialog.setVisible(false);
        headerDialog.dispose();
        headerDialog = null; 
       
    }

    private void okDialogue() {
        
    }

   

    private void cancelLineInvoice() {
      lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;  
    }

    private void okLineDial_Invoice() {
        lineDialog.setVisible(false);
        
       /* String name = lineDialog.getName().getText();
        String name_2 = lineDialog.getName().getText();
        String name_3 = lineDialog.getName().getText();*/
        lineDialog.dispose();
        lineDialog = null;    
    
    }
    
}

