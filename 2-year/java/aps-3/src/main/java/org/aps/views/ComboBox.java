package org.aps.views;


import java.util.ArrayList;

import javax.swing.JComboBox;
import org.aps.implementations.*;

public class ComboBox {
    private final int width;
    private final int height;
    private final Boolean editable;

    public ComboBox
    (
        int width,
        int height,
        Boolean editable
    )
    {
        this.width = width;
        this.height = height;
        this.editable = editable;

    }

    public JComboBox createType(ArrayList<Type> list) {
        JComboBox<String> comboBox  = new JComboBox<String>();
        comboBox.addItem(""); // default value

        for(int i = 0; i < list.size(); i++) {
            comboBox.addItem(list.get(i).getName());
        }
        comboBox.setEditable(false);

        return comboBox;
    }

    public JComboBox createGroup(ArrayList<Group> list) {

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem(""); // default value

        for(int i = 0; i < list.size(); i++) {
            comboBox.addItem(list.get(i).getName());
        }
        comboBox.setEditable(false);

        return comboBox;
    }

    public JComboBox createStates(ArrayList<State> list) {

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem(""); // default value

        for(int i = 0; i < list.size(); i++) {
            comboBox.addItem(list.get(i).getName());
        }
        comboBox.setEditable(false);

        return comboBox;
    }

    public JComboBox createThreatCategories(ArrayList<ThreatCategory> list) {

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem(""); // default value

        for(int i = 0; i < list.size(); i++) {
            comboBox.addItem(list.get(i).getName());
        }

        comboBox.setEditable(false);

        return comboBox;
    }

    public JComboBox createBiomes(ArrayList<Biome> list) {

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem(""); // default value

        for (int i = 0; i < list.size(); i++) {
            comboBox.addItem(list.get(i).getName());
        }

        comboBox.setEditable(false);

        return comboBox;
    }


}
