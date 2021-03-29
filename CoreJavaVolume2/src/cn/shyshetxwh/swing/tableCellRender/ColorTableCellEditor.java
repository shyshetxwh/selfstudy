package cn.shyshetxwh.swing.tableCellRender;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.EventObject;

public class ColorTableCellEditor extends AbstractCellEditor implements TableCellEditor {
    private JColorChooser colorChooser;
    private JDialog colorDialog;
    private JPanel panel;

    public ColorTableCellEditor() {
        panel=new JPanel();
        colorChooser=new JColorChooser();
        colorDialog=JColorChooser.createDialog(null,"Planet Color",false,colorChooser,EventHandler.create(ActionListener.class, this, "stopCellEditing"),
                EventHandler.create(ActionListener.class, this, "cancelCellEditing"));
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        colorChooser.setColor((Color)value);
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return colorChooser.getColor();
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        colorDialog.setVisible(true);
        return true;
    }

    @Override
    public void cancelCellEditing() {
        colorDialog.setVisible(false);
        super.cancelCellEditing();
    }

    @Override
    public boolean stopCellEditing() {
        colorDialog.setVisible(false);
        super.stopCellEditing();
        return true;
    }
}
