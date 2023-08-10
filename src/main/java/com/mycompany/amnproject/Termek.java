/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.amnproject;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import static com.mycompany.amnproject.MainClass.con;
import java.awt.Font;
import static com.mycompany.amnproject.image.getImage;
import static java.awt.Frame.NORMAL;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Crionic
 */
public class Termek extends javax.swing.JFrame {
    BufferedImage img;
    /**
     * Creates new form Termek
     */
    public Termek() {
        initComponents();
        Update();        
    }
     public double osszert(){
         double value = 0.0;
         try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select sum(bekerülési) from termék");
            rs.next();
            String er = rs.getString(1);
            value = Double.parseDouble(er);

        }catch(SQLException e){
            System.out.println(e);
        }
         return value;
     }
    public void Update(){
        try{
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("Select t.Név, t.Dátum, sz.név, sz_ár, v.név, t.varr_ár, t.kell_ár, t.Mennyiség, t.Komment, t.bekerülési_költség, t.id" 
                    + " from dbo.termék t, dbo.varró v, dbo.szabó sz where sz.id = t.szabóID and v.id = t.varróID");
            while(tabla.getRowCount() > 0) {
                ((DefaultTableModel) tabla.getModel()).removeRow(0);
            }
            int columns = rs.getMetaData().getColumnCount();
            while(rs.next()){  
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {  
                    row[i - 1] = rs.getObject(i);
                }
                ((DefaultTableModel) tabla.getModel()).insertRow(rs.getRow()-1,row);
            }
            tabla.setAutoCreateRowSorter(true);
            tabla.setDefaultEditor(tabla.getColumnClass(NORMAL), null);
            tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
            tabla.setFillsViewportHeight(true);
            TableColumnModel col = tabla.getColumnModel();
            tabla.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14)); 
            col.getColumn(0).setPreferredWidth(150);
            col.getColumn(8).setPreferredWidth(150);
            col.getColumn(3).setPreferredWidth(50);
            col.getColumn(5).setPreferredWidth(50);
            col.getColumn(6).setPreferredWidth(50);
            col.getColumn(9).setPreferredWidth(50);
            col.getColumn(10).setPreferredWidth(50);
            jLabel1.setVisible(false);
            hozzaval.setVisible(false);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "táblázat jelenités\n"+e);
            System.out.println(e);
             System.exit(0);
        }      
        keres.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(keres.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(keres.getText());           
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(keres.getText());
            }
            public void search(String str) {
           
           TableRowSorter sorter = (TableRowSorter) tabla.getRowSorter();
                if (str.length() == 0) {
               sorter.setRowFilter(null);
            } else {
               sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
            }
            }
        });
        ig.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent dce) {
            Date startDate = java.sql.Date.valueOf(tol.getDate());
            Date endDate = java.sql.Date.valueOf(ig.getDate());
            filter(startDate, endDate);
            }
            private void filter(Date startDate, Date endDate) {
            List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
            filters.add( RowFilter.dateFilter(ComparisonType.AFTER, startDate) );
            filters.add( RowFilter.dateFilter(ComparisonType.BEFORE, endDate) );
            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dtm);
            tabla.setRowSorter(tr);
            RowFilter<Object, Object> rf = RowFilter.andFilter(filters);
            tr.setRowFilter(rf);
            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        keres = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        hozzaval = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tol = new com.github.lgooddatepicker.components.DatePicker();
        ig = new com.github.lgooddatepicker.components.DatePicker();
        print = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Szakdolgozat");

        jButton1.setText("Hozzávalók");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Vissza");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        hozzaval.setColumns(20);
        hozzaval.setRows(5);
        jScrollPane1.setViewportView(hozzaval);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Név", "Dátum", "Szabász", "Sz_ár", "Varr", "varr_ár", "kell_ár", "Mennyiség", "Komment", "bekerülési", "kód"
            }
        ));
        tabla.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla.setRowHeight(22);
        jScrollPane2.setViewportView(tabla);

        jLabel2.setText("Lista mikből áll:");

        print.setIcon(new ImageIcon(getImage("icons/print.png")));
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(jButton1)
                            .addGap(30, 30, 30)
                            .addComponent(jButton2)
                            .addGap(44, 44, 44))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(keres, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(print)))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 931, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(keres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(tol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel2)))
                .addGap(61, 61, 61)
                .addComponent(print)
                .addContainerGap(321, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       jLabel1.setVisible(true);
       hozzaval.setVisible(true);
       int selectedRowIndex = tabla.getSelectedRow();       
       hozzaval.setText(hozzaval(selectedRowIndex));        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       jLabel1.setVisible(false);
       hozzaval.setVisible(false);
       jLabel2.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        MessageFormat header = new MessageFormat(" ");
        MessageFormat footer = new MessageFormat(" ");
    try {
        tabla.print(JTable.PrintMode.FIT_WIDTH, header, footer);
    } catch (java.awt.print.PrinterAbortException e) {
        Logger.getLogger(Termek.class.getName()).log(Level.SEVERE, null, e);
    } catch (PrinterException ex) {
        Logger.getLogger(Termek.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_printActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea hozzaval;
    private com.github.lgooddatepicker.components.DatePicker ig;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField keres;
    private javax.swing.JButton print;
    private javax.swing.JTable tabla;
    private com.github.lgooddatepicker.components.DatePicker tol;
    // End of variables declaration//GEN-END:variables
private String hozzaval(int selectedRowIndex) {
        String er = "";
        String ta="";
        try{
            String lek = "Select anyagokListája from dbo.hozzávaló where TermékID=?";
            PreparedStatement st = con.prepareStatement(lek);
            String index =  tabla.getModel().getValueAt(selectedRowIndex, 10).toString();
            int row=tabla.getSelectedRow();
            if (tabla.getRowSorter()!=null) {
                row = tabla.getRowSorter().convertRowIndexToModel(row);
                ta = tabla.getModel().getValueAt(row, 10).toString();
            }
            int inde = Integer.parseInt(ta);
            st.setInt(1, inde);
            ResultSet rs = st.executeQuery();
            LoadImageApp(inde);
            while(rs.next()){
                er += rs.getString(1)+"\n";
            }
        }catch(SQLException e){
        System.out.println(e);
        }
        return er;
    }

    private void LoadImageApp(int inde) {
       String ul=null;
        try {
           ul  = "C:\\"+inde+".jpg";
           img = ImageIO.read(new File(ul));
           Image newImage = img.getScaledInstance(450, 700, img.SCALE_DEFAULT);
           ImageIcon imgThisImg = new ImageIcon(newImage);
           jLabel1.setIcon(imgThisImg);
       } catch (IOException  e) {
          ul="";
       } catch (Exception ex) {
             ul="";
         }
    }

}
