package patibayanngbahay;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class PatibayanNgBahay {
    public static void main(String[] args) {
        new WelcomePage();
    }
}

class WelcomePage extends JFrame {
    public WelcomePage() {
        setTitle("Welcome");
        setSize(400, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image correctly
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("pic/background.png"));
        JLabel background = new JLabel(backgroundImage);

        setLayout(new BorderLayout()); // Fixed: No need for casting
        add(background, BorderLayout.CENTER);

        setVisible(true);

        // Timer to switch to HomePage after 2 seconds
        Timer timer = new Timer(2000, (ActionEvent e) -> {
            new HomePage().setVisible(true);
            dispose(); // Close WelcomePage
        });

        timer.setRepeats(false);
        timer.start();
    }
}
