package org.aps.views;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class Table {

    private int rowHeight;
    private boolean enabled;
    private boolean dragEnabled;

    public Table() {
    }

    public Table
        (
             int rowHeight,
            boolean enabled,
            boolean dragEnabled
        ) 
        {
            this.rowHeight = rowHeight;
            this.enabled = enabled;
            this.dragEnabled = dragEnabled;
        }

    public int getRowHeight() {
        return this.rowHeight;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public boolean getDragEnabled() {
        return this.dragEnabled;
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setDragEnabled(boolean dragEnabled) {
        this.dragEnabled = dragEnabled;
    }

    public JTable create(TableModel model) {

        JTable table = new JTable(model);
        table.setEnabled(getEnabled());
        table.setDragEnabled(getDragEnabled());
        table.setRowHeight(getRowHeight());

        return table;
    }
}
