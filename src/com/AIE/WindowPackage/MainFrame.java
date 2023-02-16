package com.AIE.WindowPackage;

import com.AIE.Canvas;
import com.AIE.Main;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MainFrame extends JFrame {

    // TODO: Will need to remove instance and clean up the code so singletons are not needed
    public static MainFrame instance;
    private static ArrayList<Canvas> canvasList;
    public static int currentCanvasIndex;
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static void init(int width, int height) {
        instance = new MainFrame(width, height);
    }

    private MainFrame(int width, int height) {
        setLookAndFeel();

        SCREEN_WIDTH = width;
        SCREEN_HEIGHT = height;
        canvasList = new ArrayList<>();

        this.setSize(width, height);
        this.setLayout(null);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0x303031));

        new WindowMenuBar(this);
    }

    private void setLookAndFeel() {
        FlatDarculaLaf.setup();
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCanvas(Canvas... canvasesToAdd) {
        canvasList.addAll(Arrays.asList(canvasesToAdd));

        currentCanvasIndex = canvasesToAdd.length-1;

        setCurrentCanvas(currentCanvasIndex);
    }

    public static void setCurrentCanvas(int i) {
        instance.remove(canvasList.get(currentCanvasIndex));
        instance.add(canvasList.get(i));
        currentCanvasIndex = i;
    }

    //TODO: For future use
    public static Canvas getCurrentCanvas() {
        return canvasList.get(currentCanvasIndex);
    }

    public void createWindow() {
        this.setVisible(true);
    }

    //TODO: Need other way to implement changing cursor for every tool or action
    public void changeCursor(Cursor cursor) {
        setCursor(cursor);
    }

    public static ImageIcon loadImage(String file, float scale) {
        try {
            BufferedImage img = ImageIO.read(
                    Objects.requireNonNull(
                            Main.class.getResource(file + ".png")));
            System.out.println("Successfully Loaded[image]: " + Main.class.getResource(file + ".png"));
            return new ImageIcon(img.getScaledInstance(
                    (int)(img.getWidth()*scale),
                    (int)(img.getHeight()*scale),
                    BufferedImage.SCALE_SMOOTH));
        } catch (IOException e) {
            System.out.println("/" + file + ".png");
            e.printStackTrace();
        }
        return null;
    }

}
