/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.amnproject;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import javax.swing.WindowConstants;
import static com.mycompany.amnproject.image.getImage;

/**
 *
 * @author Crionic
 */

 public class MainClass{
   
     
     public static Connection con;
     public static double ertek =0;
     public static double szám =0;
     public static double bek =0;
     public static double tobb_hia =0;
     public static double selej =0;
     public static String url="jdbc:sqlserver://localhost:1433;databaseName=nyilvantarto;integratedSecurity=true";
     public static boolean correct;
     public static FileHandler fh; 

         
        
  
     public static JFrame frame = new JFrame("Szakdolgozat");
     
     public static void main(String[] args) throws IOException  {

         try{
            con = DriverManager.getConnection(url);            
            }catch(SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                System.exit(0);
            }        
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
            int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
            frame.setLocation(x, y);
            frame.pack();
            frame.setLocationRelativeTo(null);            
            frame.setContentPane(new JLabel(new ImageIcon(getImage("icons/logo.png"))));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            JButton btn3 = new JButton("Termékek");
            JButton btn = new JButton("Anyagok");
            JButton btn1 = new JButton("Új Anyag");
            JButton btn2 = new JButton("Új Termek");
            JButton btn4 = new JButton("Új Kapcsolat");
            JButton btn6 = new JButton("Kapcsolatok");
            JButton btn7 = new JButton("Új Visszáruzás");
            JButton btn8 = new JButton("Visszáru");
            JButton btn5 = new JButton(new ImageIcon(getImage("icons/megse3.png")));
            frame.add(btn);
            frame.add(btn3);
            frame.add(btn1);
            frame.add(btn2);
            frame.add(btn4);
            frame.add(btn5);
            frame.add(btn6);
            frame.add(btn7);
            frame.add(btn8);
            frame.setSize(800, 300);
            frame.setVisible(true);
            btn.setVisible(true);
            btn.setSize(120, 30);
            btn.setLocation(100, 100);
            btn3.setVisible(true);
            btn3.setSize(120, 30);
            btn3.setLocation(100, 150);
            btn4.setVisible(true);
            btn4.setSize(120, 30);
            btn4.setLocation(600, 50);            
            btn7.setVisible(true);
            btn7.setSize(120, 30);
            btn7.setLocation(600, 200);            
            btn8.setVisible(true);
            btn8.setSize(120, 30);
            btn8.setLocation(100, 200);
            btn1.setVisible(true);
            btn1.setSize(120, 30);
            btn1.setLocation(600, 100);
            btn6.setVisible(true);
            btn6.setSize(120, 30);
            btn6.setLocation(100, 50);
            btn2.setVisible(true);
            btn2.setSize(120, 30);
            btn2.setLocation(600, 150);            
            btn5.setLocation(350, 200);
            btn5.setSize(60,60);
            btn5.setVisible(true);
            btn5.addActionListener((ActionEvent e) -> {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                System.exit(0);
         });
            btn.addActionListener((ActionEvent e) -> {
                java.awt.EventQueue.invokeLater(() -> {
                    Termek dialog = new Termek();
                    dialog.setVisible(false);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        }
                    });
                    dialog.setVisible(true);
                });
         });
            
            btn3.addActionListener((ActionEvent e) -> {
                java.awt.EventQueue.invokeLater(() -> {
                    Anyag dialog = new Anyag();
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        }
                    });
                    dialog.setVisible(true);
                });
         });
               
            btn1.addActionListener((ActionEvent e) -> {
                java.awt.EventQueue.invokeLater(() -> {
                    NewJDialog dialog = new NewJDialog();
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        }
                    });
                    dialog.setVisible(true);
                });
         });
            btn2.addActionListener((ActionEvent e) -> {   
                java.awt.EventQueue.invokeLater(() -> {
                    ujTermek dialog = new ujTermek();
                    
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        }
                    });
                    dialog.setVisible(true);
                });
         });
            btn4.addActionListener((ActionEvent e) -> {   
                java.awt.EventQueue.invokeLater(() -> {
                    kapcsolatok dialog = new kapcsolatok();
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        }
                    });
                    dialog.setVisible(true);
                });
         });
            btn6.addActionListener((ActionEvent e) -> {   
                java.awt.EventQueue.invokeLater(() -> {
                    kapcsTabla dialog;
                    try {
                        dialog = new kapcsTabla();                       
                        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosing(java.awt.event.WindowEvent e) {
                                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                            }
                        });
                        dialog.setVisible(true);
                    } catch (SQLException ex) {
                        Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
         });
            btn7.addActionListener((ActionEvent e) -> {   
                java.awt.EventQueue.invokeLater(() -> {
                    ujNyilvan dialog;
                    dialog = new ujNyilvan();
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        }
                    });
                    dialog.setVisible(true);
                });
         });
            btn8.addActionListener((ActionEvent e) -> {
                java.awt.EventQueue.invokeLater(() -> {
                    nyilvan dialog;
                    try {
                        dialog = new nyilvan();
                        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosing(java.awt.event.WindowEvent e) {
                                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                            }
                        });
                        dialog.setVisible(true);
                    } catch (SQLException ex) {
                        JOptionPane.showConfirmDialog(frame, ex);
                        Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
         });
      
    }

    
     
 }




 
 
