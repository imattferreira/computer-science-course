package org.aps.views;

import java.awt.*;

import javax.swing.JLabel;


public class Lbl {
    private String text;
    private int height;
    private int width;
    private int horizontalAlign;

    public Lbl
    (
        int height,
        int width,
        int horizontalAlign
    )
    {
        this.height = height;
        this.width = width;
        this.horizontalAlign = horizontalAlign;
    }

    public int getHeight() {
        return height;
    }

    public int getHorizontalAlign() {
        return horizontalAlign;
    }


    public int getWidth() {
        return width;
    }


    public JLabel create(String txt) {
        JLabel label = new JLabel(txt, null, getHorizontalAlign());
        label.setPreferredSize(new Dimension(getWidth(), getHeight()));

        return label;
    }
}
