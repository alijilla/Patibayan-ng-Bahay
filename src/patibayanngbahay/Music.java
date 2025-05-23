/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patibayanngbahay;
import java.io.*;
import javax.sound.sampled.*;


public class Music {
    private static Clip bgClip;
    private static float musicVolume = 1.0f;  // Default 100% volume
    private static float clickVolume = 1.0f;  // Default 100% volume

    public static void playMusic(String path) {
        try {
            if (bgClip != null && bgClip.isRunning()) {
                bgClip.stop();
                bgClip.close();
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(Music.class.getResource(path));
            bgClip = AudioSystem.getClip();
            bgClip.open(audioIn);
            setMusicVolume(musicVolume); // Apply volume
            bgClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        if (bgClip != null) {
            bgClip.stop();
            bgClip.close();
        }
    }

    public static void setMusicVolume(float volume) {
        musicVolume = volume;
        if (bgClip != null) {
            FloatControl gainControl = (FloatControl) bgClip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log10(volume) * 20); // Convert to decibels
            gainControl.setValue(dB);
        }
    }

    public static void playClickSound() {
        try {
            AudioInputStream clickSound = AudioSystem.getAudioInputStream(
                Music.class.getResource("/patibayanngbahay/sounds/click.wav")
            );
            Clip clip = AudioSystem.getClip();
            clip.open(clickSound);
            setClickVolume(clickVolume, clip); // Apply volume
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setClickVolume(float volume, Clip clip) {
        clickVolume = volume;
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log10(volume) * 20);
            gainControl.setValue(dB);
        }
    }

    public static void setClickVolume(float volume) {
        clickVolume = volume;
    }
}
