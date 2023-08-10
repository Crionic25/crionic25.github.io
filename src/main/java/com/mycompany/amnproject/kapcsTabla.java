/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.amnproject;

import static com.mycompany.amnproject.MainClass.con;
import static java.awt.Frame.NORMAL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Crionic
 */
public class kapcsTabla extends javax.swing.JFrame {

    /**
     * Creates new form kapcsTabla
     */
    public kapcsTabla() throws SQLException {
        initComponents();
        Update();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        varroda = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        anyagfajta = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        Besz = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        szab = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Szakdolgozat");

        varroda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "név"
            }
        ));
        jScrollPane1.setViewportView(varroda);

        anyagfajta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Név"
            }
        ));
        jScrollPane2.setViewportView(anyagfajta);

        Besz.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Név"
            }
        ));
        jScrollPane3.setViewportView(Besz);

        jLabel1.setText("Varrodák");

        jLabel2.setText("Anyagfajták");

        jLabel3.setText("Beszállitók");

        jLabel4.setText("Szabászok");

        szab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Név"
            }
        ));
        jScrollPane4.setViewportView(szab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(208, 208, 208)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(231, 231, 231)
                .addComponent(jLabel4)
                .addGap(10, 101, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Besz;
    private javax.swing.JTable anyagfajta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable szab;
    private javax.swing.JTable varroda;
    // End of variables declaration//GEN-END:variables

    private void Update() throws SQLException {
        try{
           Statement stat = con.createStatement();           
           ResultSet rs = stat.executeQuery("Select * from dbo.anyagfajta ");
           while(anyagfajta.getRowCount() > 0){
               ((DefaultTableModel) anyagfajta.getModel()).removeRow(0);
           }
           int columns = rs.getMetaData().getColumnCount();
           while(rs.next()){  
               Object[] row = new Object[columns];
               for (int i = 1; i <= columns; i++){  
                   row[i - 1] = rs.getObject(i);
               }
               ((DefaultTableModel) anyagfajta.getModel()).insertRow(rs.getRow()-1,row);
           }
           anyagfajta.setAutoCreateRowSorter(true);
           anyagfajta.setDefaultEditor(anyagfajta.getColumnClass(NORMAL), null);
           DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
           int alignment = 0;
           centerRenderer.setHorizontalAlignment(alignment);
           anyagfajta.setDefaultRenderer(String.class, centerRenderer);
           for (int columnIndex = 0; columnIndex < anyagfajta.getColumnCount(); columnIndex++){
               if(columnIndex == 10){
                   columnIndex++;
               }
               anyagfajta.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
           }
           anyagfajta.setPreferredScrollableViewportSize(anyagfajta.getPreferredSize());
           anyagfajta.setFillsViewportHeight(true);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "táblázat jelenités\n"+e);
            System.exit(0);
        }
        try{
            Statement stat = con.createStatement();        
            ResultSet rs = stat.executeQuery("Select * from dbo.varró ");
            while(varroda.getRowCount() > 0){
                ((DefaultTableModel) varroda.getModel()).removeRow(0);
            }
            int columns = rs.getMetaData().getColumnCount();
            while(rs.next()){  
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++){  
                    row[i - 1] = rs.getObject(i);
                }
                ((DefaultTableModel) varroda.getModel()).insertRow(rs.getRow()-1,row);
            }
            varroda.setAutoCreateRowSorter(true);
            varroda.setDefaultEditor(varroda.getColumnClass(NORMAL), null);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            int alignment = 0;
            centerRenderer.setHorizontalAlignment(alignment);
            varroda.setDefaultRenderer(String.class, centerRenderer);
            for (int columnIndex = 0; columnIndex < varroda.getColumnCount(); columnIndex++)
            {
                if(columnIndex == 10){
                    columnIndex++;
                }
                varroda.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
            }
            varroda.setPreferredScrollableViewportSize(varroda.getPreferredSize());
            varroda.setFillsViewportHeight(true);
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "táblázat jelenités\n"+e);
             System.exit(0);
        }
        try{
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("Select * from dbo.Beszállitó ");
            while(Besz.getRowCount() > 0){
                ((DefaultTableModel) Besz.getModel()).removeRow(0);
            }
            int columns = rs.getMetaData().getColumnCount();
            while(rs.next()){  
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++){  
                    row[i - 1] = rs.getObject(i);
                }
                ((DefaultTableModel) Besz.getModel()).insertRow(rs.getRow()-1,row);
            }
            Besz.setAutoCreateRowSorter(true);
            Besz.setDefaultEditor(Besz.getColumnClass(NORMAL), null);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            int alignment = 0;
            centerRenderer.setHorizontalAlignment(alignment);
            Besz.setDefaultRenderer(String.class, centerRenderer);
            for (int columnIndex = 0; columnIndex < Besz.getColumnCount(); columnIndex++){
                if(columnIndex == 10){
                    columnIndex++;
                }
                Besz.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
            }
            Besz.setPreferredScrollableViewportSize(Besz.getPreferredSize());
            Besz.setFillsViewportHeight(true);
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "táblázat jelenités\n"+e);
             System.exit(0);
        }
        try{
            Statement stat = con.createStatement();        
            ResultSet rs = stat.executeQuery("Select * from dbo.szabó ");
            while(szab.getRowCount() > 0){
                ((DefaultTableModel) szab.getModel()).removeRow(0);
            }
            int columns = rs.getMetaData().getColumnCount();
            while(rs.next()){  
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {  
                    row[i - 1] = rs.getObject(i);
                }
                ((DefaultTableModel) szab.getModel()).insertRow(rs.getRow()-1,row);
            }
            szab.setAutoCreateRowSorter(true);
            szab.setDefaultEditor(szab.getColumnClass(NORMAL), null);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            int alignment = 0;
            centerRenderer.setHorizontalAlignment(alignment);
            Besz.setDefaultRenderer(String.class, centerRenderer);
            for (int columnIndex = 0; columnIndex < szab.getColumnCount(); columnIndex++)
            {
                if(columnIndex == 10){
                    columnIndex++;
                }
                szab.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
            }
            szab.setPreferredScrollableViewportSize(szab.getPreferredSize());
            szab.setFillsViewportHeight(true);    
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "táblázat jelenités\n"+e);
             System.exit(0);
        }
        
    
}
}