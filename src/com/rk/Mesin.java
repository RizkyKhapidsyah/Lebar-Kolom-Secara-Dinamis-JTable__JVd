package com.rk;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class Mesin extends JFrame {

    JTable table;
    JScrollPane scrollTable;

    public Mesin() {
        super("Lebar Kolom Secara Dinamis");
        Inisialisasi_Komponen();
    }

    private void Inisialisasi_Komponen() {
        aturTabel();
        scrollTable.setPreferredSize(new Dimension(400, 200));
        getContentPane().add(scrollTable, BorderLayout.NORTH);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void aturTabel() {
        String data[][] = {
                {"Rizky Khapidsyah", "Manajamen Informatika", "Sering Titip Absen"},
                {"Yurnais Silaban", "Filsafat", "Jenius"},
                {"Mesem", "Manajemen Pembangunan", "Superior"}
        };
        String column[] = {"Nama", "Jurusan", "Keterangan"};
        DefaultTableModel model = new DefaultTableModel(data, column);

        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        aturSemuaLebarKolom();
        scrollTable = new JScrollPane(table);
    }

    private void aturSemuaLebarKolom() {
        for (int i = 0; i < table.getColumnCount(); i++) {
            aturLebarKolom(i);
        }
    }

    private void aturLebarKolom(int IndexKolom) {
        DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn tableColumn = columnModel.getColumn(IndexKolom);
        int width = 0;
        int margin = 20;
        TableCellRenderer renderer = tableColumn.getHeaderRenderer();

        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }

        Component comp = renderer.getTableCellRendererComponent(table, tableColumn.getHeaderValue(), false, false, 0, 0);

        width = comp.getPreferredSize().width;

        for (int i = 0; i < table.getRowCount(); i++) {
            renderer = table.getCellRenderer(i, IndexKolom);
            comp = renderer.getTableCellRendererComponent(table, table.getValueAt(i, IndexKolom), false, false, i, IndexKolom);

            int widthColumn = comp.getPreferredSize().width;
            width = Math.max(width, widthColumn);
        }

        width += margin;
        tableColumn.setPreferredWidth(width);
    }
}
