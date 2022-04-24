/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales_inv.model;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Abdalla
 */
public class InvoiceHeader {
    private int Inv_num ; 
    private String Inv_customer;
    private Date Inv_Date ;
    private ArrayList<InvoiceLine> lines;
    public InvoiceHeader() {
    
}
     public InvoiceHeader(int Inv_num, String Inv_customer, Date Inv_Date) {
        this.Inv_num = Inv_num;
        this.Inv_customer = Inv_customer;
        this.Inv_Date = Inv_Date;
    }

    public Date getInv_Date() {
        return Inv_Date;
    }

    public void setInv_Date(Date Inv_Date) {
        this.Inv_Date = Inv_Date;
    }

    public int getInv_num() {
        return Inv_num;
    }

    public void setInv_num(int Inv_num) {
        this.Inv_num = Inv_num;
    }

    public String getInv_customer() {
        return Inv_customer;
    }

    public void setInv_customer(String Inv_customer) {
        this.Inv_customer = Inv_customer;
    }

    public ArrayList<InvoiceLine> getLines() {
        
        if (lines == null)
        {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    public double getInvoiceTotal() {
        double Total = 0; 
        for (int i = 0 ; i <getLines().size();i++)
        {
            Total+=getLines().get(i).getLineTotal();
                    
        }
        return Total;
        
    
    }

   

    @Override
    public String toString() {
        return "InvoiceHeader{" + "Inv_num=" + Inv_num + ", Inv_customer=" + Inv_customer + ", Inv_Date=" + Inv_Date + '}';
    }

    public int getNum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
