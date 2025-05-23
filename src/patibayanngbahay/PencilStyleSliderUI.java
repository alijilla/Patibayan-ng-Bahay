/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patibayanngbahay;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import javax.swing.JSlider;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 *
 * @author micko
 */


class PencilVolumeSliderUI extends BasicSliderUI {

    public PencilVolumeSliderUI(JSlider slider) {
        super(slider);
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int y = trackRect.y + trackRect.height / 2;
        int x1 = trackRect.x;
        int x2 = trackRect.x + trackRect.width;
        int fillX = thumbRect.x + (thumbRect.width / 2); // Position where the knob is

        // Pencil-style strokes
        Stroke pencilStroke = new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2.setStroke(pencilStroke);

        // Left (filled part) - Darker
        g2.setColor(Color.BLACK);
        g2.drawLine(x1, y, fillX, y);

        // Right (empty part) - Lighter
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawLine(fillX, y, x2, y);
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = 12; // Size of the knob
        int x = thumbRect.x + (thumbRect.width / 2) - (size / 2);
        int y = thumbRect.y + (thumbRect.height / 2) - (size / 2);

        // Knob - Solid black circle
        g2.setColor(Color.BLACK);
        g2.fillOval(x, y, size, size);
    }
}
