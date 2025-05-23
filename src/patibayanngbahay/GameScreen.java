package patibayanngbahay;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Path2D;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.sound.sampled.*;
import java.io.File;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.sound.sampled.*;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.*;
import java.util.List;

public class GameScreen extends JFrame {
	// Image resources
	private HashMap<String, BufferedImage> powerUpImages = new HashMap<>();
	private HashMap<String, BufferedImage> sabotageImages = new HashMap<>();
	private HashMap<String, BufferedImage> projectileImages = new HashMap<>();
	
	
	// Game constants  setSize(1920, 1080);
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private static final int MAX_HEALTH = 150;
    private static final int POWERUP_SPAWN_RATE = 30;
    private static final int SABOTAGE_SPAWN_RATE = 30;
    private static final int TURN_TIME = 30;

    // Game elements
    private JPanel gamePanel;
    private JLabel projectileLabel;
    private JPanel powerBar;
    private HealthBarPanel player1HealthBar;
    private HealthBarPanel player2HealthBar;
    private JLabel player1HouseL;
    private JLabel player2HouseL;
    
    private String player1House;
    private String player2House;
    private String player1Bakod;
    private String player2Bakod;
    private JLabel turnIndicator;
    private JLabel shieldIndicator1;
    private JLabel shieldIndicator2;
    private JLabel doubleDamageIndicator;
    private JLabel turnTimerLabel;
    private JLabel houseImageLabel;
    private JLabel houseImageLabel1;
    
    private JLabel backgroundLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton setting;
    // Game state
    private Projectile currentProjectile;
    private final Random random = new Random();
    private List<Point> trajectoryPoints = new ArrayList<>();
    private List<PowerUp> activePowerUps = new ArrayList<>();
    private List<Sabotage> activeSabotages = new ArrayList<>();
    private List<Explosion> activeExplosions = new ArrayList<>();
   
    
    // Player stats
    private int player1Health ;
    private int player2Health;
    private boolean isPlayer1Turn = true;
    private int turnTimeRemaining = TURN_TIME;
    
    // Powerups and effects
    private boolean hasShield = false;
    private boolean hasDoubleDamage = false;
    private boolean hasExtraTurn = false;
    private boolean weakThrow = false;
    private boolean heavyProjectile = false;
    private int shieldOwner = 0;
    private double strengthMultiplier = 1.0;
    private boolean isFluctuating = false;
    private boolean isShaking = false;
    private int shakeOffset = 0;
    
    // Physics
    private int power = 0;
    private int dragStartX, dragStartY;
    private boolean isDragging = false;
    
    // Audio
    private Clip explosionSound;
    
    // Timers
    private Timer turnTimer;
    private Timer fluctuationTimer;
    private Timer explosionTimer;
    private Timer shakeTimer;

    public GameScreen(String player1House, String player1Bakod, String player2House, String player2Bakod) {
   this.player2House = player2House;
   this.player1House = player1House; 
   this.player2Bakod = player2Bakod;
   this.player1Bakod = player1Bakod;
    	setTitle("Player 1 Vs. Player 2");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        
  
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        
        // Initialize background
        backgroundLabel = new JLabel();
        try {
            ImageIcon bgIcon = new ImageIcon(getClass().getResource("/patibayanngbahay/pic/gs.png"));
            backgroundLabel.setIcon(bgIcon);
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
        }
        backgroundLabel.setBounds(0, 0, WIDTH, HEIGHT);
       
        Health(player1House, player1Bakod, player2House, player2Bakod);
        initializeGamePanel();
        initializeUIElements();
        loadResources();
        initializeTimers();
        getContentPane().add(backgroundLabel);
        addClickSoundToButtons(); // ✅ OK
        spawnProjectile();
        displayPlayersHouses(player1House, player1Bakod, player2House, player2Bakod); // ✅ Moved here

        
        setVisible(true);
    }

    private void initializeGamePanel() {
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Apply shake effect if active
                if (isShaking) {
                    g2.translate(shakeOffset, shakeOffset);
                }

                // Draw ground (static)
                g2.setColor(new Color(34, 139, 34));
                g2.fillRect(0, HEIGHT - 50, WIDTH, 50);

                // Draw power fluctuations (dynamic)
                if (isFluctuating) {
                    drawPowerFluctuation(g2);
                }

                // Draw trajectory (dynamic)
                if (isDragging && !trajectoryPoints.isEmpty()) {
                    drawTrajectory(g2);
                }

                // Draw powerups and sabotages (dynamic)
                drawGameObjects(g2);

                // Draw explosions LAST so they appear on top of everything
                drawExplosions(g2);
            }
        };
        gamePanel.setOpaque(false);
        gamePanel.setLayout(null);
        gamePanel.setBounds(0, 0, WIDTH, HEIGHT);
        getContentPane().add(gamePanel);
    }

    private void drawPowerFluctuation(Graphics2D g2) {
        // Enable sketchy rendering
        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        // Background (paper)
        g2.setColor(new Color(240, 240, 220, 200));
        g2.fillRect(30, 360, 200, 10);

        // Fluctuation bar (colored pencil)
        int fluctuationWidth = (int)(200 * (strengthMultiplier - 0.8) / 0.4);
        for (int i = 30; i < 30 + fluctuationWidth; i += 2) {
            int yOffset = (int)(Math.random() * 3) - 1;
            g2.setColor(new Color(255, 100, 0)); // Orange-red pencil
            g2.drawLine(i, 360 + yOffset, i, 370 + yOffset);
            
            // Add texture
            if (i % 5 == 0) {
                g2.setColor(new Color(255, 50, 0)); // Darker strokes
                g2.drawLine(i, 362 + yOffset, i, 368 + yOffset);
            }
        }

        // Hand-drawn border
        g2.setColor(Color.BLACK);
        for (int i = 0; i < 2; i++) {
            int wobble = (int)(Math.random() * 2) - 1;
            g2.drawRect(30 + wobble, 360 + wobble, 200, 10);
        }

        // Hand-written label
        g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        String text = "Power: " + String.format("%.0f%%", strengthMultiplier * 100);
        
        // Text shadow
        g2.setColor(new Color(0, 0, 0, 100));
        g2.drawString(text, 33, 355);
        
        // Main text
        g2.setColor(Color.BLACK);
        g2.drawString(text, 30, 353);
    }

    private void drawTrajectory(Graphics2D g2) {
        g2.setColor(new Color(255, 100, 100, 150));
        g2.setStroke(new BasicStroke(5));
        
        Path2D path = new Path2D.Double();
        path.moveTo(trajectoryPoints.get(0).x, trajectoryPoints.get(0).y);
        
        for (int i = 1; i < trajectoryPoints.size(); i++) {
            Point p = trajectoryPoints.get(i);
            path.lineTo(p.x, p.y);
        }
        g2.draw(path);
        
        g2.setColor(Color.BLACK);
        for (Point p : trajectoryPoints) {
            g2.fillOval(p.x-2, p.y-2, 4, 4);
        }
    }

    private void drawGameObjects(Graphics2D g2) {
        for (PowerUp pu : activePowerUps) {
            pu.draw(g2);
        }
        
        for (Sabotage s : activeSabotages) {
            s.draw(g2);
        }
    }

    private void drawExplosions(Graphics2D g2) {
        for (Explosion explosion : activeExplosions) {
            explosion.draw(g2);
        }
    }

    private void initializeUIElements() {
        // Power bar
        powerBar = new JPanel();
        powerBar.setBackground(new Color(255, 100, 100));
        powerBar.setBounds(30, 300, 0, 200);
        gamePanel.add(powerBar, 0);

        // Health bars
        player1HealthBar = new HealthBarPanel(player1Health, MAX_HEALTH, new Color(50, 150, 255));
        player1HealthBar.setBounds(200, 200, 415, 70);
        gamePanel.add(player1HealthBar, 0);

        player2HealthBar = new HealthBarPanel(player2Health, MAX_HEALTH, new Color(255, 100, 100));
        player2HealthBar.setBounds(1320,200, 415, 70);
        gamePanel.add(player2HealthBar, 0);

        // Turn indicator
        turnIndicator = new JLabel("Player 1's Turn", SwingConstants.CENTER);
        turnIndicator.setFont(new Font("Papyrus", Font.BOLD, 54));
        turnIndicator.setBounds(WIDTH/2-150, 860, 450, 250);
        gamePanel.add(turnIndicator, 0);
        
        // Turn timer
        turnTimerLabel = new JLabel(String.valueOf(TURN_TIME), SwingConstants.CENTER);
        turnTimerLabel.setFont(new Font("Papyrus", Font.BOLD, 54));
        turnTimerLabel.setBounds(WIDTH/2-150, 750, 250, 210);
        gamePanel.add(turnTimerLabel, 0);

        // Shield indicators
        shieldIndicator1 = new JLabel("SHIELD", SwingConstants.CENTER);
        shieldIndicator1.setFont(new Font("Papyrus", Font.BOLD, 14));
        shieldIndicator1.setBounds(50, 250, 100, 20);
        shieldIndicator1.setForeground(Color.BLUE);
        shieldIndicator1.setVisible(false);
        gamePanel.add(shieldIndicator1, 0);
        
        shieldIndicator2 = new JLabel("SHIELD", SwingConstants.CENTER);
        shieldIndicator2.setFont(new Font("Papyrus", Font.BOLD, 14));
        shieldIndicator2.setBounds(WIDTH-150, 250, 100, 20);
        shieldIndicator2.setForeground(Color.RED);
        shieldIndicator2.setVisible(false);
        gamePanel.add(shieldIndicator2, 0);
        
        // Double damage indicator
        doubleDamageIndicator = new JLabel("DOUBLE DAMAGE!", SwingConstants.CENTER);
        doubleDamageIndicator.setFont(new Font("Papyrus", Font.BOLD, 18));
        doubleDamageIndicator.setBounds(WIDTH/2-150, 50, 300, 30);
        doubleDamageIndicator.setForeground(Color.RED);
        doubleDamageIndicator.setVisible(false);
        gamePanel.add(doubleDamageIndicator, 0);
        
        // BackgroundHouses

        setting = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        
        // Add these new components
        jLabel3 = new JLabel();
        jLabel3.setBackground(new Color(255, 255, 255));
        jLabel3.setBorder(new LineBorder(new Color(0, 51, 204), 4, true));
        jLabel3.setBounds(1320, 200, 415, 70);
        gamePanel.add(jLabel3);

        jLabel2 = new JLabel();
        jLabel2.setBackground(new Color(255, 255, 255));
        jLabel2.setBorder(new LineBorder(new Color(255, 51, 51), 5, true));
        jLabel2.setIconTextGap(0);
        jLabel2.setBounds(200, 200, 415, 70);
        gamePanel.add(jLabel2);

        jLabel4 = new JLabel("Vs.", SwingConstants.CENTER);
        jLabel4.setFont(new Font("Papyrus", Font.BOLD, 150));
        jLabel4.setBounds(850, 350, 250, 210);
        gamePanel.add(jLabel4);

        // Settings button
        setting = new JButton("=");
        setting.setBackground(new Color(204, 204, 204));
        setting.setFont(new Font("Papyrus", Font.BOLD, 100));
        setting.setToolTipText("Game Setting");
        setting.setAlignmentY(0.0F);
        setting.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(50, 50, 50), 3),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        setting.setBounds(140, 100, 80, 60);
        setting.addActionListener(e -> ingActionPerformed(e));
        gamePanel.add(setting);
    
   
    }

   /* private JLabel createHouse(String text, int x, int y, Color color) {
        JLabel house = new JLabel(text, SwingConstants.CENTER);
        house.setBounds(x, y, 200, 150);
        house.setBackground(color);
        house.setOpaque(true);
        house.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        house.setFont(new Font("Arial", Font.BOLD, 16));
        gamePanel.add(house);
        return house;
        
    }
    */

private void ingActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	new setting().setVisible(true);
		
	}

private void Health(String player1House, String player1Bakod, String player2House, String player2Bakod){
    
// --- H1 Combinations ---
if (player1House.equals("H1") && player1Bakod.equals("brick")) player1Health = 200;
if (player2House.equals("H1") && player2Bakod.equals("brick")) player2Health = 200;

if (player1House.equals("H1") && player1Bakod.equals("wiredF")) player1Health = 180;
if (player2House.equals("H1") && player2Bakod.equals("wiredF")) player2Health = 180;

if (player1House.equals("H1") && player1Bakod.equals("sand")) player1Health = 170;
if (player2House.equals("H1") && player2Bakod.equals("sand")) player2Health = 170;

if (player1House.equals("H1") && player1Bakod.equals("fence")) player1Health = 160;
if (player2House.equals("H1") && player2Bakod.equals("fence")) player2Health = 160;

// --- H2 Combinations ---
if (player1House.equals("H2") && player1Bakod.equals("brick")) player1Health = 180;
if (player2House.equals("H2") && player2Bakod.equals("brick")) player2Health = 180;

if (player1House.equals("H2") && player1Bakod.equals("wiredF")) player1Health = 160;
if (player2House.equals("H2") && player2Bakod.equals("wiredF")) player2Health = 160;

if (player1House.equals("H2") && player1Bakod.equals("sand")) player1Health = 150;
if (player2House.equals("H2") && player2Bakod.equals("sand")) player2Health = 150;

if (player1House.equals("H2") && player1Bakod.equals("fence")) player1Health = 140;
if (player2House.equals("H2") && player2Bakod.equals("fence")) player2Health = 140;

// --- H3 Combinations ---
if (player1House.equals("H3") && player1Bakod.equals("brick")) player1Health = 220;
if (player2House.equals("H3") && player2Bakod.equals("brick")) player2Health = 220;

if (player1House.equals("H3") && player1Bakod.equals("wiredF")) player1Health = 200;
if (player2House.equals("H3") && player2Bakod.equals("wiredF")) player2Health = 200;

if (player1House.equals("H3") && player1Bakod.equals("sand")) player1Health = 190;
if (player2House.equals("H3") && player2Bakod.equals("sand")) player2Health = 190;

if (player1House.equals("H3") && player1Bakod.equals("fence")) player1Health = 180;
if (player2House.equals("H3") && player2Bakod.equals("fence")) player2Health = 180;


}
private void displayPlayersHouses(String player1House, String player1Bakod, String player2House, String player2Bakod) {
    try {
        // First player
        String imagePath = "/patibayanngbahay/pic/" + player1House + player1Bakod + ".png";
        System.out.println("Trying to load: " + imagePath);
        ImageIcon originalIcon1 = new ImageIcon(getClass().getResource(imagePath));
        houseImageLabel = new JLabel(originalIcon1);
        Image scaledImage1 = originalIcon1.getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH);
        houseImageLabel.setIcon(new ImageIcon(scaledImage1));
        houseImageLabel.setBounds(290, 650, 250, 300);
       houseImageLabel.setOpaque(false);
            houseImageLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            player1HouseL = houseImageLabel;
            gamePanel.add(player1HouseL, Integer.valueOf(1));  // Lower layer
        // Second player
        String imagePath1 = "/patibayanngbahay/pic/" + player2House + player2Bakod + ".png";
        System.out.println("Trying to load: " + imagePath1);
        ImageIcon originalIcon2 = new ImageIcon(getClass().getResource(imagePath1));
        houseImageLabel1 = new JLabel(originalIcon2);
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH);
        houseImageLabel1.setIcon(new ImageIcon(scaledImage2));
        houseImageLabel1.setBounds(1380, 650, 250, 300);
         houseImageLabel1.setOpaque(false);
            houseImageLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            player2HouseL = houseImageLabel1;
            gamePanel.add(player2HouseL, Integer.valueOf(1)); 
        repaint();

        System.out.println("P1 House: " + player1House + ", P1 Bakod: " + player1Bakod);
        System.out.println("P2 House: " + player2House + ", P2 Bakod: " + player2Bakod);

    } catch (Exception e) {
        System.err.println("Error loading house images: " + e.getMessage());
        e.printStackTrace();
    }
}

 private void addClickSoundToButtons() {
    JButton[] buttons = { };
    for (JButton button : buttons) {
        button.addActionListener(e -> Music.playClickSound());
    }}

 private void loadResources() {
	    try {
	        // Load sound effect (keep if you want explosion audio)
	        URL soundUrl = getClass().getResource("/patibayanngbahay/sounds/explosion.wav");
	        if (soundUrl != null) {
	            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundUrl);
	            explosionSound = AudioSystem.getClip();
	            explosionSound.open(audioIn);
	        }

	        // Load power-up images (if using image-based power-ups)
	        powerUpImages.put("HEALTH", ImageIO.read(getClass().getResource("/patibayanngbahay/pic/health_pu.png")));
	        powerUpImages.put("SHIELD", ImageIO.read(getClass().getResource("/patibayanngbahay/pic/shield_pu.png")));
	        powerUpImages.put("EXTRA_TURN", ImageIO.read(getClass().getResource("/patibayanngbahay/pic/extraturn_pu.png")));
	        powerUpImages.put("DOUBLE_DAMAGE", ImageIO.read(getClass().getResource("/patibayanngbahay/pic/doubledamage_pu.png")));

	        // Load sabotage images (if using image-based sabotages)
	        sabotageImages.put("HEAVY", ImageIO.read(getClass().getResource("/patibayanngbahay/pic/heavy_sabotage.png")));
	        sabotageImages.put("POWER_DRAIN", ImageIO.read(getClass().getResource("/patibayanngbahay/pic/powerdrain_sabotage.png")));
	        sabotageImages.put("WEAK", ImageIO.read(getClass().getResource("/patibayanngbahay/pic/weak_sabotage.png")));

	        // Load projectile images (if using image-based projectiles)
	        projectileImages.put("BOMB", ImageIO.read(getClass().getResource("/patibayanngbahay/pic/bomb_projectile.png")));
	        projectileImages.put("BRICK", ImageIO.read(getClass().getResource("/patibayanngbahay/pic/brick_projectile.png")));
	        projectileImages.put("PAPER", ImageIO.read(getClass().getResource("/patibayanngbahay/pic/paper_projectile.png")));

	    } catch (Exception e) {
	        System.err.println("Error loading resources: " + e.getMessage());
	        e.printStackTrace();
	        
	        // Create placeholder graphics if images fail to load
	        createPlaceholderGraphics();
	    }
	}

	// Optional: Fallback graphics if images fail to load
	private void createPlaceholderGraphics() {
	    try {
	        // Simple colored squares as placeholders
	        int size = 30;
	        
	        // Power-up placeholders
	        powerUpImages.put("HEALTH", createColoredImage(size, size, Color.GREEN));
	        powerUpImages.put("SHIELD", createColoredImage(size, size, Color.BLUE));
	        powerUpImages.put("EXTRA_TURN", createColoredImage(size, size, Color.CYAN));
	        powerUpImages.put("DOUBLE_DAMAGE", createColoredImage(size, size, Color.RED));

	        // Sabotage placeholders
	        sabotageImages.put("HEAVY", createColoredImage(size, size, Color.MAGENTA));
	        sabotageImages.put("POWER_DRAIN", createColoredImage(size, size, Color.ORANGE));
	        sabotageImages.put("WEAK", createColoredImage(size, size, Color.PINK));

	        // Projectile placeholders
	        projectileImages.put("BOMB", createColoredImage(40, 40, Color.BLACK));
	        projectileImages.put("BRICK", createColoredImage(40, 40, Color.GRAY));
	        projectileImages.put("PAPER", createColoredImage(40, 40, Color.WHITE));
	        
	    } catch (Exception e) {
	        System.err.println("Failed to create placeholders: " + e.getMessage());
	    }
	}

	// Helper method to create colored rectangles
	private BufferedImage createColoredImage(int width, int height, Color color) {
	    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = img.createGraphics();
	    g2.setColor(color);
	    g2.fillRect(0, 0, width, height);
	    g2.setColor(Color.BLACK);
	    g2.drawRect(0, 0, width-1, height-1);
	    g2.dispose();
	    return img;
	}
    private void initializeTimers() {
        // Power fluctuation timer
        fluctuationTimer = new Timer(1000, e -> {
            strengthMultiplier = 0.8 + (random.nextDouble() * 0.4);
            isFluctuating = true;
            
            new Timer(500, ev -> {
                isFluctuating = false;
                ((Timer)ev.getSource()).stop();
            }).start();
        });
        fluctuationTimer.start();
        
        // Turn timer
        turnTimer = new Timer(1000, e -> {
            turnTimeRemaining--;
            turnTimerLabel.setText(String.valueOf(turnTimeRemaining));
            
            if (turnTimeRemaining <= 0) {
                endTurn();
            }
        });
        turnTimer.start();
        
        // Explosion animation timer
        explosionTimer = new Timer(30, e -> {
            Iterator<Explosion> it = activeExplosions.iterator();
            while (it.hasNext()) {
                if (!it.next().update()) {
                    it.remove();
                }
            }
            gamePanel.repaint();
            
            if (activeExplosions.isEmpty()) {
                explosionTimer.stop();
            }
        });
        
        // Screen shake timer
        shakeTimer = new Timer(10, e -> {
            if (isShaking) {
                shakeOffset = (int)(Math.random() * 10) - 5;
                gamePanel.setLocation(shakeOffset, shakeOffset);
            } else {
                gamePanel.setLocation(0, 0);
            }
        });
        shakeTimer.start();
    }

    private void spawnProjectile() {
        // Remove any existing projectile first
        if (projectileLabel != null) {
            gamePanel.remove(projectileLabel);
        }
        
        spawnRandomPowerUps();
        
        String[] types = {"BOMB", "BRICK", "PAPER"};
        currentProjectile = Projectile.create(types[random.nextInt(types.length)]);
        
        // Create projectile label with image
        projectileLabel = new JLabel(new ImageIcon(projectileImages.get(currentProjectile.type)));
        projectileLabel.setBounds(isPlayer1Turn ? 150 : WIDTH - 250, 500, 60, 60);
        
        gamePanel.add(projectileLabel, 1);
        setupProjectileListeners();
        updateTurnIndicator();
        resetTurnTimer();
        gamePanel.repaint();
    }

    private void setupProjectileListeners() {
        projectileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (projectileLabel.contains(e.getPoint())) {
                    dragStartX = e.getXOnScreen();
                    dragStartY = e.getYOnScreen();
                    isDragging = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isDragging) {
                    isDragging = false;
                    int dx = e.getXOnScreen() - dragStartX;
                    int dy = e.getYOnScreen() - dragStartY;
                    int throwPower = (int) Math.sqrt(dx * dx + dy * dy);
                    
                    if (throwPower > 30) {
                        launchProjectile(dx / 10, dy / 10);
                    } else {
                        trajectoryPoints.clear();
                        gamePanel.repaint();
                    }
                }
            }
        });

        projectileLabel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging) {
                    int dx = e.getXOnScreen() - dragStartX;
                    int dy = e.getYOnScreen() - dragStartY;
                    power = (int) Math.sqrt(dx * dx + dy * dy);
                    powerBar.setBounds(30, 300, Math.min(power / 2, 200), 20);
                    updateTrajectory(dx / 10, dy / 10);
                }
            }
        });
    }

    private void updateTrajectory(int velocityX, int velocityY) {
        trajectoryPoints.clear();
        int x = projectileLabel.getX() + projectileLabel.getWidth()/2;
        int y = projectileLabel.getY() + projectileLabel.getHeight()/2;
        
        double vX = isPlayer1Turn ? velocityX : -velocityX;
        double vY = -velocityY;
        
        vX *= strengthMultiplier;
        vY *= strengthMultiplier;

        for (int t = 0; t < 100; t++) {
            int px = (int) (x + vX * t);
            int py = (int) (y + vY * t + 0.5 * currentProjectile.gravity * t * t);
            
            if (px < 0 || px > WIDTH || py > HEIGHT) break;
            
            trajectoryPoints.add(new Point(px, py));
        }
        gamePanel.repaint();
    }

    private void launchProjectile(int velocityX, int velocityY) {
        int x = projectileLabel.getX();
        int y = projectileLabel.getY();

        final int finalVelocityX = (int)(velocityX * strengthMultiplier);
        final int finalVelocityY = (int)(velocityY * strengthMultiplier);
        
        int vxFinal = isPlayer1Turn ? finalVelocityX : -finalVelocityX;
        final int adjustedVelocityX = Math.max(finalVelocityX, currentProjectile.minVelocity);

        final JLabel flyingProjectile = projectileLabel;
        final int initialX = x;
        final int initialY = y;
        final int finalVx = vxFinal;
        final double gravityFinal = currentProjectile.gravity;

        Timer timer = new Timer(20, null);
        timer.addActionListener(new ActionListener() {
            int posX = initialX;
            int posY = initialY;
            double currentVY = -finalVelocityY;

            @Override
            public void actionPerformed(ActionEvent e) {
                posX += finalVx;
                currentVY += gravityFinal;
                posY += (int) currentVY;

                flyingProjectile.setLocation(posX, posY);
               
                Rectangle projectileBounds = flyingProjectile.getBounds();
                checkPowerUpCollisions(projectileBounds);
                
                if (isPlayer1Turn && projectileBounds.intersects(player2HouseL.getBounds())) {
                    createExplosion(
                        player2HouseL.getX() + player2HouseL.getWidth()/2,
                        player2HouseL.getY() + player2HouseL.getHeight()/2
                    );
                    applyDamageToPlayer(2);
                    cleanupAfterThrow(timer, flyingProjectile);
                    return;
                } else if (!isPlayer1Turn && projectileBounds.intersects(player1HouseL.getBounds())) {
                    createExplosion(
                        player1HouseL.getX() + player1HouseL.getWidth()/2,
                        player1HouseL.getY() + player1HouseL.getHeight()/2
                    );
                    applyDamageToPlayer(1);
                    cleanupAfterThrow(timer, flyingProjectile);
                    return;
                }
                
                if (posY > HEIGHT || posX < 0 || posX > WIDTH) {
                    cleanupAfterThrow(timer, flyingProjectile);
                }
            }
        });
        timer.start();
    }

    private void createExplosion(int x, int y) {
        Explosion explosion = new Explosion(x, y);
        activeExplosions.add(explosion);
        isShaking = true;
        
        if (explosionSound != null) {
            explosionSound.setFramePosition(0);
            explosionSound.start();
        }
        
        new Timer(500, e -> {
            isShaking = false;
            ((Timer)e.getSource()).stop();
        }).start();
        
        if (!explosionTimer.isRunning()) {
            explosionTimer.start();
        }
        
        // Force repaint to show explosion immediately
        gamePanel.repaint();
    }

    private void checkPowerUpCollisions(Rectangle projectileBounds) {
        Iterator<PowerUp> puIter = activePowerUps.iterator();
        while (puIter.hasNext()) {
            PowerUp pu = puIter.next();
            if (projectileBounds.intersects(pu.bounds)) {
                activatePowerUp(pu.type);
                puIter.remove();
                gamePanel.repaint();
            }
        }
        
        Iterator<Sabotage> sIter = activeSabotages.iterator();
        while (sIter.hasNext()) {
            Sabotage s = sIter.next();
            if (projectileBounds.intersects(s.bounds)) {
                activateSabotage(s.type);
                sIter.remove();
                gamePanel.repaint();
            }
        }
    }

    private void activatePowerUp(String type) {
        String message = "";
        switch(type) {
            case "HEALTH":
                if (isPlayer1Turn) {
                    player1Health = Math.min(player1Health + 10, MAX_HEALTH);
                    player1HealthBar.setHealth(player1Health);
                    message = "Player 1 gained +10 HP!";
                } else {
                    player2Health = Math.min(player2Health + 10, MAX_HEALTH);
                    player2HealthBar.setHealth(player2Health);
                    message = "Player 2 gained +10 HP!";
                }
                break;
                
            case "SHIELD":
                hasShield = true;
                shieldOwner = isPlayer1Turn ? 1 : 2;
                updateShieldIndicators();
                message = (isPlayer1Turn ? "Player 1" : "Player 2") + " got a Shield! Next attack against you will be halved!";
                break;
                
            case "EXTRA_TURN":
                hasExtraTurn = true;
                message = (isPlayer1Turn ? "Player 1" : "Player 2") + " gets an extra turn!";
                break;
                
            case "DOUBLE_DAMAGE":
                hasDoubleDamage = true;
                doubleDamageIndicator.setVisible(true);
                new Timer(2000, e -> doubleDamageIndicator.setVisible(false)).start();
                message = (isPlayer1Turn ? "Player 1" : "Player 2") + "'s next attack will do double damage!";
                break;
        }
        showEffectMessage(message);
    }

    private void activateSabotage(String type) {
        String message = "";
        switch(type) {
            case "HEAVY":
                heavyProjectile = true;
                currentProjectile.gravity *= 2.5;
                message = "Projectile became HEAVY! It will fall much faster!";
                break;
                
            case "POWER_DRAIN":
                power = Math.max(20, power / 5);
                powerBar.setBounds(30, 300, Math.min(power / 2, 200), 20);
                message = "Your throwing power was DRAINED!";
                break;
                
            case "WEAK":
                weakThrow = true;
                message = "Your next throw will be WEAK!";
                break;
        }
        showEffectMessage(message);
    }

    private void showEffectMessage(String message) {
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JOptionPane.showMessageDialog(this, messageLabel, "Effect Activated", JOptionPane.INFORMATION_MESSAGE);
    }

    private void applyDamageToPlayer(int player) {
        int damage = currentProjectile.baseDamage + random.nextInt(11);
        
        if (hasDoubleDamage && ((player == 2 && isPlayer1Turn) || (player == 1 && !isPlayer1Turn))) {
            damage *= 2;
            hasDoubleDamage = false;
        }
        
        if (hasShield && 
            ((player == 1 && shieldOwner == 1 && !isPlayer1Turn) || 
             (player == 2 && shieldOwner == 2 && isPlayer1Turn))) {
            damage = Math.max(1, damage / 2);
            hasShield = false;
            shieldOwner = 0;
            updateShieldIndicators();
            showEffectMessage((player == 1 ? "Player 1" : "Player 2") + "'s shield blocked some damage!");
        }
        
        if (weakThrow) {
            damage = Math.max(1, damage / 3);
            weakThrow = false;
        }
        
        if (player == 1) {
            player1Health = Math.max(0, player1Health - damage);
            player1HealthBar.setHealth(player1Health);
        } else {
            player2Health = Math.max(0, player2Health - damage);
            player2HealthBar.setHealth(player2Health);
        }
        
        // Update house images after damage
        updateHouseImages();
        
        checkGameOver();
    }

    private void cleanupAfterThrow(Timer timer, JLabel projectile) {
        timer.stop();
        if (projectile != null) {
            gamePanel.remove(projectile);
            gamePanel.revalidate();
            gamePanel.repaint();
        }
        trajectoryPoints.clear();
        powerBar.setBounds(30, 300, 0, 20);
        
        if (hasExtraTurn) {
            hasExtraTurn = false;
        } else {
            isPlayer1Turn = !isPlayer1Turn;
        }
        
        weakThrow = false;
        heavyProjectile = false;
        currentProjectile = Projectile.create(currentProjectile.type);
        
        updateTurnIndicator();
        spawnProjectile();
    }

    private void endTurn() {
        isPlayer1Turn = !isPlayer1Turn;
        updateTurnIndicator();
        resetTurnTimer();
    }

    private void updateTurnIndicator() {
        turnIndicator.setText(isPlayer1Turn ? "Player 1's Turn" : "Player 2's Turn");
        turnIndicator.setForeground(isPlayer1Turn ? Color.BLUE : Color.RED);
    }

    private void resetTurnTimer() {
        turnTimeRemaining = TURN_TIME;
        turnTimerLabel.setText(String.valueOf(TURN_TIME));
    }

    private void updateShieldIndicators() {
        shieldIndicator1.setVisible(hasShield && shieldOwner == 1);
        shieldIndicator2.setVisible(hasShield && shieldOwner == 2);
    }

    private void checkGameOver() {
        if (player1Health <= 0 || player2Health <= 0) {
            String winner = player1Health <= 0 ? "Player 2 Wins!" : "Player 1 Wins!";
            int choice = JOptionPane.showConfirmDialog(this, 
                winner + " Play again?", "Game Over", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (choice == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        }
    }

    private void resetGame() {
        // Stop all timers
        turnTimer.stop();
        fluctuationTimer.stop();
        explosionTimer.stop();
        
        // Remove any existing projectile
        if (projectileLabel != null) {
            gamePanel.remove(projectileLabel);
            projectileLabel = null;
        }
        
        // Reset game state
        player1Health = getMaxHealthForHouse(player1House, player1Bakod);
        player2Health = getMaxHealthForHouse(player2House, player2Bakod);
        isPlayer1Turn = true;
        turnTimeRemaining = TURN_TIME;
        
        hasShield = false;
        hasDoubleDamage = false;
        hasExtraTurn = false;
        weakThrow = false;
        heavyProjectile = false;
        shieldOwner = 0;
        strengthMultiplier = 1.0;
        isFluctuating = false;
        isShaking = false;
        
        // Clear all active objects
        activePowerUps.clear();
        activeSabotages.clear();
        activeExplosions.clear();
        trajectoryPoints.clear();
        
        // Reset UI
        player1HealthBar.setHealth(player1Health);
        player2HealthBar.setHealth(player2Health);
        updateTurnIndicator();
        updateShieldIndicators();
        doubleDamageIndicator.setVisible(false);
        
        // Reset house images
        displayPlayersHouses(player1House, player1Bakod, player2House, player2Bakod);
        
        // Force UI update
        gamePanel.revalidate();
        gamePanel.repaint();
        
        // Restart timers
        turnTimer.start();
        fluctuationTimer.start();
        
        // Start new round
        spawnProjectile();
    }
    
    private void updateHouseImages() {
        try {
            // Update player 1 house
            String p1DamageLevel = getDamageLevel(player1Health, getMaxHealthForHouse(player1House, player1Bakod));
            String p1ImagePath = "/patibayanngbahay/pic/" + player1House + player1Bakod + p1DamageLevel + ".png";
            updateHouseImage(player1HouseL, p1ImagePath);
            
            // Update player 2 house
            String p2DamageLevel = getDamageLevel(player2Health, getMaxHealthForHouse(player2House, player2Bakod));
            String p2ImagePath = "/patibayanngbahay/pic/" + player2House + player2Bakod + p2DamageLevel + ".png";
            updateHouseImage(player2HouseL, p2ImagePath);
        } catch (Exception e) {
            System.err.println("Error updating house images: " + e.getMessage());
        }
    }

    private void updateHouseImage(JLabel houseLabel, String imagePath) {
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
            Image scaledImage = originalIcon.getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH);
            houseLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("Error loading house image: " + imagePath + " - " + e.getMessage());
        }
    }

    private String getDamageLevel(int currentHealth, int maxHealth) {
        double percentage = (double) currentHealth / maxHealth;
        
        if (currentHealth <= 0) return "0"; // 0% (destroyed)
        if (percentage <= 0.25) return "25"; // 0-25%
        if (percentage <= 0.50) return "50"; // 25-50%
        if (percentage <= 0.75) return "75"; // 50-75%
        return "100"; // 75-100%
    }

    private int getMaxHealthForHouse(String house, String bakod) {
        // This should match your Health() method logic
        if (house.equals("H1") && bakod.equals("brick")) return 200;
        if (house.equals("H1") && bakod.equals("wiredF")) return 180;
        if (house.equals("H1") && bakod.equals("sand")) return 170;
        if (house.equals("H1") && bakod.equals("fence")) return 160;
        
        if (house.equals("H2") && bakod.equals("brick")) return 180;
        if (house.equals("H2") && bakod.equals("wiredF")) return 160;
        if (house.equals("H2") && bakod.equals("sand")) return 150;
        if (house.equals("H2") && bakod.equals("fence")) return 140;
        
        if (house.equals("H3") && bakod.equals("brick")) return 220;
        if (house.equals("H3") && bakod.equals("wiredF")) return 200;
        if (house.equals("H3") && bakod.equals("sand")) return 190;
        if (house.equals("H3") && bakod.equals("fence")) return 180;
        
        return 150; // default
    }
    private void spawnRandomPowerUps() {
        if (random.nextInt(100) < POWERUP_SPAWN_RATE) {
            String[] types = {"HEALTH", "SHIELD", "EXTRA_TURN", "DOUBLE_DAMAGE"};
            int x = 200 + random.nextInt(WIDTH - 400);
            int y = 200 + random.nextInt(HEIGHT - 400);
            
            String type = types[random.nextInt(types.length)];
            activePowerUps.add(new PowerUp(type, powerUpImages.get(type), x, y));
        }
        
        if (random.nextInt(100) < SABOTAGE_SPAWN_RATE) {
            String[] types = {"HEAVY", "POWER_DRAIN", "WEAK"};
            int x = 200 + random.nextInt(WIDTH - 400);
            int y = 200 + random.nextInt(HEIGHT - 400);
            
            String type = types[random.nextInt(types.length)];
            activeSabotages.add(new Sabotage(type, sabotageImages.get(type), x, y));
        }
    }
    }

class HealthBarPanel extends JPanel {
    private int health;
    private int maxHealth;
    private Color fillColor;

    public HealthBarPanel(int health, int maxHealth, Color fillColor) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.fillColor = fillColor;
        setOpaque(false);
    }

    public void setHealth(int health) {
        this.health = health;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Enable "pencil sketch" effect
        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        // Draw background (paper-like texture)
        g2.setColor(new Color(240, 240, 220)); // Off-white paper color
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        // Draw health bar with colored pencil texture
        int width = (int)((double)health/maxHealth * getWidth());
        if (width > 0) {
            // Create a rough, hand-drawn fill effect
            for (int i = 0; i < width; i += 3) {
                int h = getHeight();
                int yOffset = (int)(Math.random() * 4) - 2; // Slight wobble
                g2.setColor(fillColor);
                g2.drawLine(i, 2 + yOffset, i, h - 2 + yOffset);
                
                // Add subtle color variation (like pencil shading)
                Color lighter = fillColor.brighter();
                Color darker = fillColor.darker();
                if (i % 5 == 0) {
                    g2.setColor(lighter);
                    g2.drawLine(i, 3 + yOffset, i, h - 3 + yOffset);
                } else if (i % 7 == 0) {
                    g2.setColor(darker);
                    g2.drawLine(i, 1 + yOffset, i, h - 1 + yOffset);
                }
            }
        }

        // Draw hand-drawn border (sketchy outline)
        g2.setColor(Color.BLACK);
        for (int i = 0; i < 3; i++) { // Rough, overlapping lines
            int wobbleX = (int)(Math.random() * 3) - 1;
            int wobbleY = (int)(Math.random() * 3) - 1;
            g2.drawRoundRect(
                wobbleX, wobbleY, 
                getWidth()-1 + wobbleX, getHeight()-1 + wobbleY, 
                10, 10
            );
        }

        // Draw hand-written text
        String text = health + "/" + maxHealth;
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
        
        // Text shadow effect
        g2.setColor(new Color(0, 0, 0, 100));
        g2.drawString(text, x + 1, y + 1);
        
        // Main text with slight color variation
        g2.setColor(fillColor.darker().darker());
        g2.drawString(text, x, y);
    }
}
class Projectile {
    String type;
    Color bgColor;
    Color fgColor;
    int baseDamage;
    double gravity;
    int minVelocity;
    double strengthFluctuation;

    private Projectile(String type, Color bgColor, Color fgColor, int baseDamage, 
                      double gravity, int minVelocity, double strengthFluctuation) {
        this.type = type;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.baseDamage = baseDamage;
        this.gravity = gravity;
        this.minVelocity = minVelocity;
        this.strengthFluctuation = strengthFluctuation;
    }

    public static Projectile create(String type) {
        switch (type) {
            case "BOMB":
                return new Projectile("BOMB", Color.BLACK, Color.WHITE, 30, 1.8, 3, 0.3);
            case "BRICK":
                return new Projectile("BRICK", new Color(150, 150, 150), Color.ORANGE, 20, 2.2, 2, 0.2);
            case "PAPER":
                return new Projectile("PAPER", Color.WHITE, Color.BLUE, 10, 0.6, 4, 0.4);
            default:
                return new Projectile("DEFAULT", Color.GRAY, Color.BLACK, 15, 1.0, 2, 0.25);
        }
    }
}

class PowerUp {
    String type;
    BufferedImage image;
    int x, y;
    Rectangle bounds;
    
    public PowerUp(String type, BufferedImage image, int x, int y) {
        this.type = type;
        this.image = image;
        this.x = x;
        this.y = y;
        this.bounds = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }
    
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, null);
    }
}

class Sabotage {
    String type;
    BufferedImage image;
    int x, y;
    Rectangle bounds;
    
    public Sabotage(String type, BufferedImage image, int x, int y) {
        this.type = type;
        this.image = image;
        this.x = x;
        this.y = y;
        this.bounds = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }
    
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, null);
    }
}

class Explosion {
    int x, y;
    int radius;
    int maxRadius;
    int life = 30; // Duration in frames
    List<Particle> particles = new ArrayList<>();
    Color[] fireColors = {
        new Color(255, 200, 0),   // Yellow
        new Color(255, 100, 0),   // Orange
        new Color(255, 50, 0),    // Red-orange
        new Color(200, 0, 0)      // Dark red
    };

    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
        this.maxRadius = 60 + (int)(Math.random() * 40);
        createParticles();
    }

    private void createParticles() {
        for (int i = 0; i < 50; i++) {
            particles.add(new Particle(x, y));
        }
    }

    public boolean update() {
        radius = (int)(maxRadius * (1 - (float)life / 30f));
        life--;
        
        Iterator<Particle> it = particles.iterator();
        while (it.hasNext()) {
            if (!it.next().update()) {
                it.remove();
            }
        }
        return life > 0 || !particles.isEmpty();
    }

    public void draw(Graphics2D g2) {
        // Draw expanding fireball
        if (life > 0) {
            float progress = 1 - (life / 30f);
            int alpha = (int)(200 * (1 - progress * 0.5f));
            
            // Gradient explosion
            GradientPaint gp = new GradientPaint(
                x - radius, y - radius, new Color(255, 200, 0, alpha),
                x + radius, y + radius, new Color(255, 50, 0, alpha/2)
            );
            g2.setPaint(gp);
            g2.fillOval(x - radius, y - radius, radius * 2, radius * 2);
            
            // Outer glow
            g2.setColor(new Color(255, 150, 0, alpha/3));
            g2.fillOval(x - radius - 10, y - radius - 10, (radius + 10) * 2, (radius + 10) * 2);
        }
        
        // Draw particles
        for (Particle p : particles) {
            p.draw(g2);
        }
    }
}
class Particle {
    int x, y;
    float dx, dy;
    Color color;
    int life;
    int size;
    
    public Particle(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.dx = (float)(Math.random() * 6 - 3);
        this.dy = (float)(Math.random() * 6 - 3);
        this.color = new Color(
            255, 
            (int)(100 + Math.random() * 155),
            0,
            (int)(150 + Math.random() * 105)
        );
        this.life = (int)(20 + Math.random() * 30);
        this.size = (int)(3 + Math.random() * 7);
    }
    
    public boolean update() {
        x += dx;
        y += dy;
        dy += 0.1;
        return --life > 0;
    }
    
    public void draw(Graphics2D g2) {
        float alpha = (float)life / 50f;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(color);
        g2.fillOval(x - size/2, y - size/2, size, size);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}