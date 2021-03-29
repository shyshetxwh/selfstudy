package cn.shyshetxwh.JDBC.view;

import cn.shyshetxwh.JDBC.utils.ConnectionUtils;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewDBFrame extends JFrame {
    private JButton previousButton;
    private JButton nextButton;
    private JButton deleteButton;
    private JButton saveButton;
    private DataPanel dataPanel;
    private Component scrollPanel;
    private JComboBox<String> tableNames;
    private CachedRowSet crs;
    private Connection conn;
    
    public ViewDBFrame()
    {
        tableNames=new JComboBox<String>();
        try {
            conn = ConnectionUtils.getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            try(ResultSet mrs=meta.getTables(null,null,null,new String[]{"TABLE"})){
                while(mrs.next())
                {
                    tableNames.addItem(mrs.getString(3));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
        tableNames.addActionListener(event->showTable((String)tableNames.getSelectedItem(),conn));
        add(tableNames,BorderLayout.NORTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (conn!=null)conn.close();
                } catch (SQLException e1) {
                    for (Throwable t : e1) {
                        t.printStackTrace();
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        add(buttonPanel,BorderLayout.SOUTH);
        
        previousButton=new JButton("Previous");
        previousButton.addActionListener(event->showPreviousRow());
        buttonPanel.add(previousButton);

        nextButton=new JButton("Next");
        nextButton.addActionListener(event->showNextRow());
        buttonPanel.add(nextButton);

        deleteButton=new JButton("Delete");
        deleteButton.addActionListener(event->deleteRow());
        buttonPanel.add(deleteButton);

        saveButton=new JButton("Save");
        saveButton.addActionListener(event->saveRow());
        buttonPanel.add(saveButton);

        if (tableNames.getItemCount()>0)showTable(tableNames.getItemAt(0),conn);
    }

    private void saveRow() {
        if (crs==null)return;
        new SwingWorker<Void,Void>(){
            @Override
            protected Void doInBackground() throws SQLException {
                dataPanel.showRow(crs);
                crs.acceptChanges(conn);
                return null;
            }


        }.execute();
    }

    private void deleteRow() {
        if (crs==null)return;
        new SwingWorker<Void,Void>(){
            @Override
            protected Void doInBackground() throws SQLException {
                crs.deleteRow();
                crs.acceptChanges(conn);
                if (!crs.isAfterLast())
                    if (!crs.last())crs=null;
                return null;
            }

            @Override
            protected void done() {
                dataPanel.showRow(crs);
            }
        }.execute();
    }

    private void showNextRow() {
        try {
            if (crs==null||crs.isLast())return;
            crs.next();
            dataPanel.showRow(crs);
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    private void showPreviousRow() {
        try {
            if (crs==null||crs.isFirst())return;
            crs.previous();
            dataPanel.showRow(crs);
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    private void showTable(String tableName, Connection conn) {
        try(Statement stat=conn.createStatement();
        ResultSet rs=stat.executeQuery("SELECT * FROM "+tableName))
        {
            RowSetFactory factory = RowSetProvider.newFactory();
            crs=factory.createCachedRowSet();
            crs.setTableName(tableName);
            crs.populate(rs);
            if (scrollPanel!=null)remove(scrollPanel);
            dataPanel=new DataPanel(crs);
            scrollPanel=new JScrollPane(dataPanel);
            add(scrollPanel,BorderLayout.CENTER);
            pack();
            showNextRow();

        } catch (SQLException e) {
            for (Throwable t: e) {
                t.printStackTrace();
            }
        }
    }

}

class DataPanel extends JPanel
{
    private List<JTextField> fields;

    public DataPanel(RowSet rs) throws SQLException {
        fields=new ArrayList<>();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth=1;
        gbc.gridheight=1;

        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            gbc.gridy=i-1;
            String columnName = rsmd.getColumnLabel(i);
            gbc.gridx=0;
            gbc.anchor=GridBagConstraints.EAST;
            add(new JLabel(columnName),gbc);

            int columnWidth = rsmd.getColumnDisplaySize(i);
            JTextField tb = new JTextField(columnWidth);
            if (!rsmd.getColumnClassName(i).equals("java.lang.String"))
            {
                tb.setEditable(false);
            }
            fields.add(tb);
            gbc.gridx=1;
            gbc.anchor=GridBagConstraints.WEST;
            add(tb,gbc);
        }

    }

    public void showRow(ResultSet rs)
    {
        try {
            if (rs==null)return;
            for (int i = 1; i <= fields.size(); i++) {
                String filed=rs==null?"":rs.getString(i);
                JTextField tb = fields.get(i - 1);
                tb.setText(filed);
            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    public void setRow(RowSet rs) throws SQLException {
        for (int i = 1; i <= fields.size(); i++) {
            String field = rs.getString(i);
            JTextField tb = fields.get(i - 1);
            if (!field.equals(tb.getText()))
                rs.updateString(i,tb.getText());
        }
        rs.updateRow();
    }
}
