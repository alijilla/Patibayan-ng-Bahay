/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package patibayanngbahay;


import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author micko
 */
public class GameFrame1 extends javax.swing.JFrame {
      private Start startPage;
      private String selectedHouse1;
      private String selectedHouse;
      private int save1 = 0;
      private String player1House;
      private String player1Bakod;
      private String player2House ;
      private String player2Bakod = "";
      private JLabel houseImageLabel;
      private String selectedBakod1 = "";
      private String selectedBakod ;
      private final String[] bakod = {"brick", "sand", "wiredF","fence"};

 public GameFrame1(String selectedHouse1, String player1House, String player1Bakod, String player2House){
    this.selectedHouse1 = selectedHouse1;  
    this.player1House = player1House; 
    this.player1Bakod = player1Bakod;
    this.player2House = player2House;

 
    initComponents();
    setTitle("GameFrame1");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);
    setVisible(true);

     addClickSoundToButtons();     
    
     System.out.println("Selected house in GameFrame1: "+ player1House+ player1Bakod + ".png");
    displayHouseImage();
    
}

  private void displayHouseImage() {
    String imagePath = "";
    switch (selectedHouse1) {
        case "H1" : {
            imagePath = "/patibayanngbahay/pic/H1.png";
           
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
            houseImageLabel = new JLabel(originalIcon);
            Image scaledImage = originalIcon.getImage().getScaledInstance(480, 480, Image.SCALE_SMOOTH);
            houseImageLabel.setIcon(new ImageIcon(scaledImage));
            
            ImageIcon icon = (ImageIcon) houseImageLabel.getIcon();
            int width = icon.getIconWidth();
            int height = icon.getIconHeight();
            
            // Create and set up the label
            
            houseImageLabel.setBounds(275, 400, width, height);
            houseImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            houseImageLabel.setOpaque(true);
            houseImageLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            getContentPane().add(houseImageLabel, 0);
            break;
              }
      
        case "H2" : {
            imagePath = "/patibayanngbahay/pic/H2.png";
            ImageIcon originalIcon1 = new ImageIcon(getClass().getResource(imagePath));
            houseImageLabel = new JLabel(originalIcon1);
            Image scaledImage1 = originalIcon1.getImage().getScaledInstance(480, 360, Image.SCALE_SMOOTH);
            houseImageLabel.setIcon(new ImageIcon(scaledImage1));
            houseImageLabel.setOpaque(true);
            ImageIcon icon1 = (ImageIcon) houseImageLabel.getIcon();
            int width1 = icon1.getIconWidth();
            int height1 = icon1.getIconHeight();
            
            // Create and set up the label
            
            houseImageLabel.setBounds(275, 400, width1, height1);
            houseImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            houseImageLabel.setOpaque(true);
            houseImageLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            getContentPane().add(houseImageLabel, 0);
            break;
              }
       
        case "H3" : {
            imagePath = "/patibayanngbahay/pic/H3.png";
            ImageIcon originalIcon2 = new ImageIcon(getClass().getResource(imagePath));
            houseImageLabel = new JLabel(originalIcon2);
            Image scaledImage2 = originalIcon2.getImage().getScaledInstance(480, 480, Image.SCALE_SMOOTH);
            houseImageLabel.setIcon(new ImageIcon(scaledImage2));
            ImageIcon icon2 = (ImageIcon) houseImageLabel.getIcon();
            int width2 = icon2.getIconWidth();
            int height2 = icon2.getIconHeight();
            
            // Create and set up the label
            houseImageLabel.setBounds(275, 400, width2, height2);
            houseImageLabel.setOpaque(true);
            houseImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            houseImageLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            getContentPane().add(houseImageLabel, 0);
            break;
              }
        
       
    }
    // Check if the image is loaded correctly
    URL imageURL = getClass().getResource(imagePath);
    if (imageURL != null) {
        System.out.println("Image loaded from: " + imageURL.toString());
    } else {
        System.out.println("Image not found: " + imagePath);
        return; // If the image is not found, exit
    }
    // Create ImageIcon with the correct path
   
    
    revalidate(); // Ensure layout is updated
    repaint(); // Force a repaint to show the image
}
   

       private void addClickSoundToButtons() {
    JButton[] buttons = { next,back,Fence,brick,sandb,wired};
    for (JButton button : buttons) {
        button.addActionListener(e -> Music.playClickSound());
    }
    
}
    
  

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        save = new javax.swing.JButton();
        back = new javax.swing.JButton();
        brick = new javax.swing.JButton();
        Fence = new javax.swing.JButton();
        setting = new javax.swing.JButton();
        sandb = new javax.swing.JButton();
        wired = new javax.swing.JButton();
        next = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        save.setBackground(new java.awt.Color(204, 204, 204));
        save.setFont(new java.awt.Font("Papyrus", 1, 36)); // NOI18N
        save.setText("SAVE");
        save.setToolTipText("Back To Menu");
        save.setAlignmentY(0.0F);
        save.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(50, 50, 50), 3), // Shadow border
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding
        ));
        save.setDefaultCapable(false);
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        getContentPane().add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 940, 270, 60));

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
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 940, 270, 60));

        brick.setBackground(new java.awt.Color(204, 204, 204));
        brick.setFont(new java.awt.Font("Papyrus", 1, 36)); // NOI18N
        brick.setText("Select");
        brick.setToolTipText("Brick");
        brick.setAlignmentY(0.0F);
        brick.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(50, 50, 50), 3), // Shadow border
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding
        ));
        brick.setDefaultCapable(false);
        brick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brickActionPerformed(evt);
            }
        });
        getContentPane().add(brick, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 470, 230, 60));

        Fence.setBackground(new java.awt.Color(204, 204, 204));
        Fence.setFont(new java.awt.Font("Papyrus", 1, 36)); // NOI18N
        Fence.setText("Select");
        Fence.setToolTipText("SandBag");
        Fence.setAlignmentY(0.0F);
        Fence.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(50, 50, 50), 3), // Shadow border
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding
        ));
        Fence.setDefaultCapable(false);
        Fence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FenceActionPerformed(evt);
            }
        });
        getContentPane().add(Fence, new org.netbeans.lib.awtextra.AbsoluteConstraints(1490, 820, 230, 60));

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

        sandb.setBackground(new java.awt.Color(204, 204, 204));
        sandb.setFont(new java.awt.Font("Papyrus", 1, 36)); // NOI18N
        sandb.setText("Select");
        sandb.setToolTipText("SandBag");
        sandb.setAlignmentY(0.0F);
        sandb.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(50, 50, 50), 3), // Shadow border
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding
        ));
        sandb.setDefaultCapable(false);
        sandb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sandbActionPerformed(evt);
            }
        });
        getContentPane().add(sandb, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 470, 230, 60));

        wired.setBackground(new java.awt.Color(204, 204, 204));
        wired.setFont(new java.awt.Font("Papyrus", 1, 36)); // NOI18N
        wired.setText("Select");
        wired.setToolTipText("SandBag");
        wired.setAlignmentY(0.0F);
        wired.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(50, 50, 50), 3), // Shadow border
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding
        ));
        wired.setDefaultCapable(false);
        wired.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wiredActionPerformed(evt);
            }
        });
        getContentPane().add(wired, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 820, 230, 60));

        next.setBackground(new java.awt.Color(204, 204, 204));
        next.setFont(new java.awt.Font("Papyrus", 1, 120)); // NOI18N
        next.setText(">");
        next.setToolTipText("Next");
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

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patibayanngbahay/pic/wiredFence.png"))); // NOI18N
        jLabel4.setToolTipText("Wired Fence");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.setPreferredSize(new java.awt.Dimension(600, 600));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 590, 250, 190));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patibayanngbahay/pic/fence.png"))); // NOI18N
        jLabel5.setToolTipText("Fence");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.setPreferredSize(new java.awt.Dimension(600, 600));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 590, 270, 190));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patibayanngbahay/pic/sandbag.png"))); // NOI18N
        jLabel3.setToolTipText("SandBag");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setPreferredSize(new java.awt.Dimension(600, 600));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 250, 250, 190));

        jLabel6.setBackground(new java.awt.Color(255, 255, 51));
        jLabel6.setFont(new java.awt.Font("Papyrus", 1, 48)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Player 2");
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.setOpaque(true);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 930, 250, 70));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patibayanngbahay/pic/brick.png"))); // NOI18N
        jLabel2.setToolTipText("Brick");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.setPreferredSize(new java.awt.Dimension(600, 600));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 250, 270, 190));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/patibayanngbahay/pic/gs2.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void selectBakod( int bakodIndex) {
    // Set the selected house
    selectedBakod1 = bakod[bakodIndex];

    // Reset all button colors and images
   resetBakodSelection();
   setBackground(new java.awt.Color(255, 215, 0));
    // Highlight the selected button
  

    // Update image to indicate selection
    
}

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed

        if (startPage == null) {
            startPage = new Start();
        }
         startPage.setVisible(true);
         startPage.toFront();
         startPage.requestFocus();
        setVisible(false);
        dispose(); 

        // TODO add your handling code here:
    }//GEN-LAST:event_backActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        // TODO add your handling code here:
    int save1 = this.save1;
if (!selectedBakod1.isEmpty() && save1 == 1) {
    GameScreen games = new GameScreen(player1House, player1Bakod, player2House, player2Bakod);
    games.setVisible(true);
    this.dispose(); // Optional: close the current frame
} else if (selectedBakod1.isEmpty() && save1 == 0) {
    System.out.println("No Bakod Selected and Did not save!");
    JOptionPane.showMessageDialog(this, "Please Select a Bakod First! and Save!");
} else if (selectedBakod1.isEmpty()) {
    System.out.println("No Bakod Selected!");
    JOptionPane.showMessageDialog(this, "Please Select a Bakod First!");
    save1--;
} else if (save1 == 0) {
    System.out.println("You Did not Save");
    JOptionPane.showMessageDialog(this, "Please Save First!");
}
     
        
    }//GEN-LAST:event_nextActionPerformed

    private void wiredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wiredActionPerformed
        // TODO add your handling code here:
       if (save1 == 1) {
        String[] options = {"Yes", "No"};
        int x = JOptionPane.showOptionDialog(
            null,
            "Do you want to change your Bakod?",
            "Thank you and Good Bye!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (x == 0) { // User chose "Yes"
            save1--;
            selectBakod(2);
            updateHouseImageBakod(wired, 2, selectedHouse1);
        }

        // No action needed if user chose "No"
    } else {
        selectBakod(2);
        updateHouseImageBakod(wired, 2, selectedHouse1);
    }
        
        
    }//GEN-LAST:event_wiredActionPerformed

    private void sandbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sandbActionPerformed
        // TODO add your handling code here:
       if (save1 == 1) {
        String[] options = {"Yes", "No"};
        int x = JOptionPane.showOptionDialog(
            null,
            "Do you want to change your Bakod?",
            "Thank you and Good Bye!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (x == 0) { // User chose "Yes"
            save1--;
        selectBakod(1);
        updateHouseImageBakod(sandb, 1 ,selectedHouse1);
        }

        // No action needed if user chose "No"
    } else {
        selectBakod(1);
        updateHouseImageBakod(sandb, 1 ,selectedHouse1);
    }
 
     
    }//GEN-LAST:event_sandbActionPerformed

    private void FenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FenceActionPerformed
        // TODO add your handling code here:
       if (save1 == 1) {
        String[] options = {"Yes", "No"};
        int x = JOptionPane.showOptionDialog(
            null,
            "Do you want to change your Bakod?",
            "Thank you and Good Bye!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (x == 0) { // User chose "Yes"
            save1--;
         selectBakod(3);
       updateHouseImageBakod(Fence, 3 ,selectedHouse1);
        }

        // No action needed if user chose "No"
    } else {
        selectBakod(3);
       updateHouseImageBakod(Fence, 3 ,selectedHouse1);
    }
 
    }//GEN-LAST:event_FenceActionPerformed

    private void brickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brickActionPerformed
        // TODO add your handling code here:
   if (save1 == 1) {
        String[] options = {"Yes", "No"};
        int x = JOptionPane.showOptionDialog(
            null,
            "Do you want to change your Bakod?",
            "Thank you and Good Bye!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (x == 0) { // User chose "Yes"
            save1--;
         selectBakod(0);
       updateHouseImageBakod(brick, 0 ,selectedHouse1);
        }

        // No action needed if user chose "No"
    } else {
       selectBakod(0);
       updateHouseImageBakod(brick, 0 ,selectedHouse1);
    }
 
    
      
    }//GEN-LAST:event_brickActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        
        save1 (save1,  selectedHouse1, selectedBakod1);
         save1++;
        JOptionPane.showMessageDialog(this, "Saved");
       
    }//GEN-LAST:event_saveActionPerformed

    private void settingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingActionPerformed
        // TODO add your handling code here:
        new setting().setVisible(true);
    }//GEN-LAST:event_settingActionPerformed


  public void save1 (int save1,  String selectedHouse1, String selectedBakod1){
     switch(save1){
     
         case 0:
            player2House = selectedHouse1;
            player2Bakod = selectedBakod1;
             System.out.println("Player 2 selected: House = " + player2House + ", Bakod = " + player2Bakod);
             System.out.println("Saved Game On");
             
             break;
         case 1:
  
             System.out.println("Saved Already");
             break;
       default:
            System.out.println("Selection phase is complete.");
            break;
     }
 
  }
    
   
   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton Fence;
    javax.swing.JButton back;
    javax.swing.JButton brick;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JLabel jLabel6;
    javax.swing.JButton next;
    javax.swing.JButton sandb;
    javax.swing.JButton save;
    javax.swing.JButton setting;
    javax.swing.JButton wired;
    // End of variables declaration//GEN-END:variables
// Declare the houseImageLabel globally to ensure only one image at a time


// Reset button colors and the displayed image
private void resetBakodSelection() {
    // Reset button colors to default (gray)
    brick.setBackground(new java.awt.Color(204, 204, 204));
    sandb.setBackground(new java.awt.Color(204, 204, 204));
    wired.setBackground(new java.awt.Color(204, 204, 204));
    Fence.setBackground(new java.awt.Color(204, 204, 204));

    // Reset images to default (assuming a default house image)
 
}
 
// This method is used to update the displayed house image based on the selected bakod
private void updateHouseImageBakod(JButton selectedButton, int bakodIndex, String selectedHouse1) {
    // Define a list of bakod images based on the selected house
    String[] bakodImages = {
        selectedHouse1 + "brick.png",
        selectedHouse1 + "sand.png",
        selectedHouse1 + "wiredF.png",
        selectedHouse1 + "fence.png"
    };

    // Default image path (if no specific bakod is selected)
    String imagePath = "/patibayanngbahay/pic/H_" + selectedHouse1.charAt(1) + ".png"; // default

    // Check if the selected bakod image exists and update path accordingly
    if (bakodIndex >= 0 && bakodIndex < bakodImages.length) {
        String testPath = "/patibayanngbahay/pic/" + bakodImages[bakodIndex];
        if (getClass().getResource(testPath) != null) {
            imagePath = testPath;
        }
    }
  
    // Determine the appropriate height based on house type
    if (selectedHouse1.equals("H1")){
      int width = houseImageLabel.getWidth();
      int height = houseImageLabel.getHeight();
         showHouseImage(imagePath, width, height);
    }
    
        if (selectedHouse1.equals("H2")){
      int width = houseImageLabel.getWidth();
      int height = houseImageLabel.getHeight();
         showHouseImage(imagePath, width, height);
    }
    
       if (selectedHouse1.equals("H3")){
      int width = houseImageLabel.getWidth();
      int height = houseImageLabel.getHeight();
         showHouseImage(imagePath, width, height);
    }

    // Call method to display the selected image
 
}

// This method displays the selected house image and ensures no overlap of images
private void showHouseImage(String imagePath, int width, int height) {
    // Remove the previous house image if it exists
    if (houseImageLabel != null) {
        getContentPane().remove(houseImageLabel);
        houseImageLabel = null;
    }

    // Load the new image icon
    ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
    Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

    // Create a new JLabel to display the image
    houseImageLabel = new JLabel(new ImageIcon(scaled));
    houseImageLabel.setBounds(275, 400, width, height); // Position the image
    houseImageLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
  
    // Add the new image label to the content pane
    houseImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       houseImageLabel.setOpaque(true);
    getContentPane().add(houseImageLabel, 0); // Layer index 0 (bottom layer)

    // Revalidate and repaint to apply changes
    getContentPane().revalidate();
    getContentPane().repaint();
}
}




