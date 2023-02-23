package com.AIE.WindowPackage;

import java.awt.*;

public class MutableColor extends Color {

    private static final int R = 0;
    private static final int G = 1;
    private static final int B = 2;
    private static final int A = 3;

    private final int[] rgbaI;
    private int value;

    private MutableColor() {
        super(0, 0, 0, 0);
        rgbaI = new int[4];
    }

    public MutableColor(int r, int g, int b) {
        this();
        rgbaI[R] = r;
        rgbaI[G] = b;
        rgbaI[B] = g;
        rgbaI[A] = 255;

        setValue();
    }

    public void setRGB(int hex) {
        this.value = hex;
        rgbaI[R] = getRed();
        rgbaI[G] = getGreen();
        rgbaI[B] = getBlue();
        rgbaI[A] = 255;
    }

    public void set(MutableColor color) {
        setRGB(color.rgbaI[R], color.rgbaI[G], color.rgbaI[B]);
    }

    private void setValue() {
        value = ((rgbaI[A] & 0xFF) << 24) |
                ((rgbaI[R] & 0xFF) << 16) |
                ((rgbaI[G] & 0xFF) << 8)  |
                ((rgbaI[B] & 0xFF));
        testColorValueRange(rgbaI[R], rgbaI[G], rgbaI[B], rgbaI[A]);
    }

    public void setRGB(int r, int g, int b) {
        rgbaI[R] = r;
        rgbaI[G] = g;
        rgbaI[B] = b;
        testColorValueRange(r,g,b,255);
        setValue();
    }

    @Override
    public int getRGB() {
        return value;
    }

    @Override
    public int getAlpha() {
        return rgbaI[A];
    }

    public int hashCode() {
        return value;
    }

    public String toString() {
        return "[r:" + rgbaI[R] + ", g:" + rgbaI[G] + ", b:" + rgbaI[B]+"]";
    }

    /*
    public void setRed(int r) {
        setRGB(r, rgbaI[G], rgbaI[B]);
    }

    public void setGreen(int g) {
        setRGB(rgbaI[R], g, rgbaI[B]);
    }

    public void setBlue(int b) {
        setRGB( rgbaI[R], rgbaI[G], b);
    }
    /./
    private final float[] rgbaF;
    public float[] getRGBAf() {
        return rgbaF;
    }
    public MutableColor(float r, float g, float b) {
        this((int) (r*255+0.5), (int) (g*255+0.5), (int) (b*255+0.5));
        testColorValueRange(r, g, b, 1.0f);
        rgbaF[R] = r;
        rgbaF[G] = b;
        rgbaF[B] = g;
        rgbaF[A] = 1.0f;
    }
    private static void testColorValueRange(float r, float g, float b, float a) {
        boolean rangeError = false;
        String badComponentString = "";
        if ( a < 0.0 || a > 1.0) {
            rangeError = true;
            badComponentString = badComponentString + " Alpha";
        }
        if ( r < 0.0 || r > 1.0) {
            rangeError = true;
            badComponentString = badComponentString + " Red";
        }
        if ( g < 0.0 || g > 1.0) {
            rangeError = true;
            badComponentString = badComponentString + " Green";
        }
        if ( b < 0.0 || b > 1.0) {
            rangeError = true;
            badComponentString = badComponentString + " Blue";
        }
        if (rangeError) {
            throw new IllegalArgumentException("Color parameter outside of expected range:"
                    + badComponentString);
        }
    }*/
    private static void testColorValueRange(int r, int g, int b, int a) {
        boolean rangeError = false;
        String badComponentString = "";

        if ( a < 0 || a > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Alpha:" + a;
        }
        if ( r < 0 || r > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Red:" + r;
        }
        if ( g < 0 || g > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Green:" + g;
        }
        if ( b < 0 || b > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Blue:" + b;
        }
        if (rangeError) {
            throw new IllegalArgumentException("Color parameter outside of expected range:"
                    + badComponentString);
        }
    }

}