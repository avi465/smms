package com.avi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        printThisBill();
    }

    public static void printThisBill()
    {

        String[][] mod = {{"Milk","2","23","46"},{"Milk","2","23","46"},{"Milk","2","23","46"},{"Milk","2","23","46"}};
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        //get current date time with Date()
        Date date = new Date();
        Date time = new Date();
        String Date = dateFormat.format(date);
        String Time = timeFormat.format(time);
        String Header =
                "   ****Super Market****       \n"
                        + "Date: "+Date+"     Time: "+Time+"\n"
                        + "---------------------------------\n"
                        + "Name          Qty    Rate     Amt\n"
                        + "---------------------------------\n";

        String amt  =
                "\n \n \nTotal Amount = "+  "1000"   +"\n"
                        + "Tax ="   +  "5.89%"    + "\n"
                        + "*********************************\n"
                        + "Thank you. \n";

        String bill = Header;
        int i = 0;
        do
        {

            String name =     ""+ mod[i][0];
            String qty =      ""+ mod[i][1];
            String rate =     ""+ mod[i][2];
            String amount =   ""+ mod[i][3];

            if(name.length() > 12)
            {
                name = name.substring(0, 12)+"  ";
            }
            else
            {
                for(int j= name.length()-12; j<= name.length(); j++);
                {
                    name = name+" ";
                }
            }


            if(qty.length()<=5)
            {
                for(int j= 0; j<= qty.length()-5; j++);
                {
                    qty = qty+" ";
                }
            }

            rate = rate;
            String items =
                    name+"\t"+qty+"\t"+rate+"\t"+amount+"\n";

            bill = bill+ items;
            i++;

        } while(i <= 3);

        bill = bill+amt;
        System.out.println(bill);
        printCard(bill);
//        dispose();
    }

    public static void printCard(final String bill )
    {
        Printable contentToPrint = new Printable(){
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int page) throws PrinterException
            {
                if (page > 0) {
                    return NO_SUCH_PAGE;
                }
                pageFormat.setOrientation(PageFormat.LANDSCAPE);
                Graphics2D g2d = (Graphics2D)graphics.create();

                g2d.setPaint(Color.black);
                g2d.setFont(new Font("Arial", Font.BOLD, 10));
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableX());


                g2d.drawString(bill, 0, 0);
                g2d.drawOval(100,100,400,400);
                return PAGE_EXISTS;
            }
        };

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(contentToPrint);
        //You can show a print dialog before printing by job by wrapping the following blocks with a conditional statement if(job.printDialog()){...}
        if(job.printDialog()){
            try
            {
                job.print();
            } catch (PrinterException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }
}