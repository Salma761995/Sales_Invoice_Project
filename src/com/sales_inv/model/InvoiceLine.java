/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales_inv.model;

public class InvoiceLine {
    private int inv_numb;
    private String item ; 
    private double price ; 
    private int count ; 
    InvoiceHeader header ; 
    public InvoiceLine() {
    }

    public InvoiceLine(int inv_numb, String item, double price, int count) {
        this.inv_numb = inv_numb;
        this.item = item;
        this.price = price;
        this.count = count;
    }

    public InvoiceLine(String str2, double price, int count, InvoiceHeader Inv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getInv_numb() {
        return inv_numb;
    }

    public void setInv_numb(int inv_numb) {
        this.inv_numb = inv_numb;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double getLineTotal ()
    {
        return price*count ; 
    }

    

    @Override
    public String toString() {
        return "InvoiceLine{" + "inv_numb=" + inv_numb + ", item=" + item + ", price=" + price + ", count=" + count + ", header=" + header + '}';
    }
    
    
}