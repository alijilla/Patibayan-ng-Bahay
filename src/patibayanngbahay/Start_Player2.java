/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package patibayanngbahay;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author micko
 */
public class Start_Player2 extends javax.swing.JFrame {
      private HomePage homePage;
      private String selectedHouse1 = "";
       private String player2House="" ;
       private String player1House;
      private String player1Bakod;
      private final String[] houses = {"H1", "H2", "H3"};

      
  public Start_Player2(String player1House, String player1Bakod) {
     this.player1House = player1House;
     this.player1Bakod = player1Bakod;
     
    initComponents();
    setTitle("Start Game");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);
    setVisible(true);

  addClickSoundToButtons();     
    }
    private void addClickSoundToButtons() {
    JButton[] buttons = { next,back,H1,H2,H3};
    for (JButton button : buttons) {
        button.addActionListener(e -> Music.playClickSound());
    }
}
    
   private void selectHouse(JButton selectedButton, int houseIndex) {
    // Set the selected house
    selectedHouse1 = houses[houseIndex];

    // Reset all button colors and images
    resetHouseSelection();

    // Highlight the selected button
    selectedButton.setBackground(new java.awt.Color(204, 205, 205, 150));
   
    // Update image to indicate selection
    updateHouseImage(selectedButton, houseIndex);
}


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JButton();
        setting = new javax.swing.JButton();
        H3 = new javax.swing.JButton();
        H2 = new javax.swing.JButton();
        H1 = new javax.swing.JButton();
        next = new javax.swing.JButton();
        H3img = new javax.swing.JLabel();
        H2img = new javax.swing.JLabel();
        H1img = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back.setBackground(new java.awt.Color(204, 204, 204));
        back.setFont(new java.awt.Font("Papyrus", 1, 36)); // NOI18N
        back.setText("BACK");
        back.setToolTipText("Back To Menu");
        back.setAlignmentY(0.0F);
        back.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(50, 50, 50), 3), // Shadow border
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding
        ));
        back.setDefaultCapable(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(1520, 950, 270, 60));

        setting.setBackground(new java.awt.Color(204, 204, 204));
        setting.setFont(new java.awt.Font("Papyrus", 1, 100)); // NOI18N
        setting.setText("=");
        setting.setToolTipText("Game Setting");
        setting.setAlignmentY(0.0F);
        setting.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(50, 50, 50), 3), // Shadow border
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding
        ));
        setting.setDefaultCapable(false);
        setting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingActionPerformed(evt);
            }
        });
        getContentPane().add(setting, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 80, 60));

        H3.setBackground(new java.awt.Color(204, 204, 204));
        H3.setFont(new java.awt.Font("Papyrus", 1, 36)); // NOI18N
        H3.setText("Select");
        H3.setToolTipText("Back To Menu");
        H3.setAlignmentY(0.0F);
        H3.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(50, 50, 50), 3), // Shadow border
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding
        ));
        H3.setDefaultCapable(false);
        H3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H3ActionPerformed(evt);
            }
        });
        getContentPane().add(H3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 880, 340, 60));

        H2.setBackground(new java.awt.Color(204, 204, 204));
        H2.setFont(new java.awt.Font("Papyrus", 1, 36)); // NOI18N
        H2.setText("Select");
        H2.setToolTipText("Back To Menu");
        H2.setAlignmentY(0.0F);
        H2.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(50, 50, 50), 3), // Shadow border
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding
        ));
        H2.setDefaultCapable(false);
        H2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H2ActionPerformed(evt);
            }
        });
        getContentPane().add(H2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 880, 340, 60));

        H1.setBackground(new java.awt.Color(204, 204, 204));
        H1.setFont(new java.awt.Font("Papyrus", 1, 36)); // NOI18N
        H1.setText("Select");
        H1.setToolTipText("Back To Menu");
        H1.setAlignmentY(0.0F);
        H1.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(50, 50, 50), 3), // Shadow border
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding
        ));
        H1.setDefaultCapable(false);
        H1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H1ActionPerformed(evt);
            }
        });
        getContentPane().add(H1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 880, 340, 60));

        next.setBackground(new java.awt.Color(204, 204, 204));
        next.setFont(new java.awt.Font("Papyrus", 1, 120)); // NOI18N
        next.setText(">");
        next.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(50, 50, 50), 3), // Shadow border
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding
        ));
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });
        getContentPane().add(next, new org.netbeans.lib.awtextra.AbsoluteConstraints(1760, 440, 100, 100));

        H3img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patibayanngbahay/pic/H_3.png"))); // NOI18N
        H3img.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(H3img, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 590, -1, -1));

        H2img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patibayanngbahay/pic/H_2.png"))); // NOI18N
        H2img.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(H2img, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 660, -1, -1));

        H1img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patibayanngbahay/pic/H_1.png"))); // NOI18N
        H1img.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(H1img, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 600, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 51));
        jLabel4.setFont(new java.awt.Font("Papyrus", 1, 48)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Player 2");
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.setOpaque(true);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 380, 250, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patibayanngbahay/pic/gs.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed

        if (homePage == null) {
            homePage = new HomePage();
        }
        homePage.setVisible(true);
        homePage.toFront();
        homePage.requestFocus();
        setVisible(false);
        dispose(); 

        // TODO add your handling code here:
    }//GEN-LAST:event_backActionPerformed

    private void H3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H3ActionPerformed
        // TODO add your handling code here:
        selectHouse(H3,2);
     
    }//GEN-LAST:event_H3ActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        // TODO add your handling code here:
        player2House = selectedHouse1;
        if (!selectedHouse1.isEmpty()){
        
        GameFrame1 game = new GameFrame1(selectedHouse1, player1House,player1Bakod, player2House);
        game.setVisible(true);
        this.dispose(); // Optional: close the current frame
          
        }else{
            System.out.println("No house Selected!");
             JOptionPane.showMessageDialog(this, "Please select a house first!");
        }

        
    }//GEN-LAST:event_nextActionPerformed

    private void H1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H1ActionPerformed
        // TODO add your handling code here:
      selectHouse(H1,0);
    }//GEN-LAST:event_H1ActionPerformed

    private void H2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H2ActionPerformed
        // TODO add your handling code here:
        selectHouse(H2,1);
    }//GEN-LAST:event_H2ActionPerformed

    private void settingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingActionPerformed
        // TODO add your handling code here:
        new setting().setVisible(true);
    }//GEN-LAST:event_settingActionPerformed

    /**
     * @param args the command line arguments
     */
    
   
   
   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton H1;
    javax.swing.JLabel H1img;
    javax.swing.JButton H2;
    javax.swing.JLabel H2img;
    javax.swing.JButton H3;
    javax.swing.JLabel H3img;
    javax.swing.JButton back;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel4;
    javax.swing.JButton next;
    javax.swing.JButton setting;
    // End of variables declaration//GEN-END:variables


private void resetHouseSelection() {
    // Reset button colors
    H1.setBackground(new java.awt.Color(204, 204, 204));
    H2.setBackground(new java.awt.Color(204, 204, 204));
    H3.setBackground(new java.awt.Color(204, 204, 204));

    // Reset images to default
    // Reset images to default
    H1img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patibayanngbahay/pic/H_1.png")));
    H1img.setBounds(250,600,240,240);
    H2img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patibayanngbahay/pic/H_2.png")));
    H2img.setBounds(830,660,240,160);
    H3img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patibayanngbahay/pic/H_3.png")));
    H3img.setBounds(1420,620,240,240);
}
private void updateHouseImage(JButton selectedButton, int houseIndex) {
    switch (houseIndex) {
        case 0:
            
           ImageIcon originalIcon = new ImageIcon(getClass().getResource("/patibayanngbahay/pic/H1.png"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(480, 500, Image.SCALE_SMOOTH);
            H1img.setIcon(new ImageIcon(scaledImage));

             ImageIcon icon = (ImageIcon) H1img.getIcon();
                int width = icon.getIconWidth();
                int height = icon.getIconHeight();

              H1img.setBounds(150, 360, width, height);
                 H1img.setOpaque(true);
              H1img.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            break;
        case 1:
            ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/patibayanngbahay/pic/H2.png"));
            Image scaledImage1 = originalIcon1.getImage().getScaledInstance(480, 340, Image.SCALE_SMOOTH);
            H2img.setIcon(new ImageIcon(scaledImage1));
              ImageIcon icon1 = (ImageIcon) H2img.getIcon();
            // Get the width and height of the icon
            int width1 = icon1.getIconWidth();
            int height1 = icon1.getIconHeight();
            H2img.setBounds(710,520,width1,height1);
               H2img.setOpaque(true);
            H2img.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            break;
        case 2:
            ImageIcon originalIcon2 = new ImageIcon(getClass().getResource("/patibayanngbahay/pic/H3.png"));
            Image scaledImage2 = originalIcon2.getImage().getScaledInstance(480, 480, Image.SCALE_SMOOTH);
            H3img.setIcon(new ImageIcon(scaledImage2));
              ImageIcon icon2 = (ImageIcon) H3img.getIcon();
            // Get the width and height of the icon
            int width2 = icon2.getIconWidth();
            int height2 = icon2.getIconHeight();
            H3img.setBounds(1270,380,width2,height2);
               H3img.setOpaque(true);
            H3img.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            break;
    }
}
}