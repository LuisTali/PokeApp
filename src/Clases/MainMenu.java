package Clases;

import Clases.PokeApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

public class MainMenu extends JFrame {
    private JPanel MainMenu;
    private JTextField nameField;
    private JButton BUSCARButton;
    private JTable tableInfo;
    private JScrollPane infoScrollPane;
    private JComboBox comboBox1;
    private JLabel pokeLabel;

    PokeApp pkApp = new PokeApp();

    public MainMenu() {
        super("PikaPika");
        setContentPane(MainMenu);
        setSize(900,500);
        //setMinimumSize(new Dimension(850, 500));
        setLocationRelativeTo(null);
        styleTable();
        setComboBoxConfig();
        setVisible(true);



        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().toLowerCase();

                infoTable((Pokemon) pkApp.getInfoPokemonByName(name));

            }
        });

        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                typesTable();
            }
        });
    }

    public void setComboBoxConfig() {
        comboBox1.removeAllItems();
        Object[] arr = pkApp.typesToArray();
        for (Object o : arr) {
            comboBox1.addItem(o);
        }
    }

    public void styleTable() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{
                "Name", "Type", "Move1", "Move2", "Move3","HP","Attack","Defense","Speed"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableInfo.setModel(model);
        tableInfo.getColumnModel().getColumn(5).setPreferredWidth(40);
        tableInfo.getColumnModel().getColumn(6).setPreferredWidth(40);
        tableInfo.getColumnModel().getColumn(7).setPreferredWidth(40);
        tableInfo.getColumnModel().getColumn(8).setPreferredWidth(40);
        tableInfo.getTableHeader().setReorderingAllowed(false); //Hace imposible mover las columnas de lugar.
    }

    public void typesTable(){
        String type = (String) comboBox1.getSelectedItem();

        DefaultTableModel model = new DefaultTableModel(new Object[]{
                "Name", "Type", "Move1", "Move2", "Move3","HP","Attack","Defense","Speed"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Iterator entries = pkApp.pokemonsByTypeIterator(type);
        while (entries.hasNext()){
            Pokemon e = (Pokemon) entries.next();
            model.addRow(new Object[]{e.getName(),e.getType()
                    , e.getMove1(),e.getMove2(),e.getMove3(),e.getHp()
                    ,e.getAttack(),e.getDefense(),e.getSpeed()});
        }
        tableInfo.setModel(model);
        tableInfo.getColumnModel().getColumn(5).setPreferredWidth(40);
        tableInfo.getColumnModel().getColumn(6).setPreferredWidth(40);
        tableInfo.getColumnModel().getColumn(7).setPreferredWidth(40);
        tableInfo.getColumnModel().getColumn(8).setPreferredWidth(40);
        tableInfo.getTableHeader().setReorderingAllowed(false); //Hace imposible mover las columnas de lugar.
    }

    public void infoTable(Pokemon e) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{
                "Name", "Type", "Move1", "Move2", "Move3","HP","Attack","Defense","Speed"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        if(e!=null) {

            try {
                URL url = new URL(e.getUrlImage());
                Image image = ImageIO.read(url);
                pokeLabel.setIcon(new ImageIcon(image));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            model.addRow(new Object[]{e.getName(),e.getType()
                , e.getMove1(),e.getMove2(),e.getMove3(),e.getHp()
                ,e.getAttack(),e.getDefense(),e.getSpeed()});
        }



        tableInfo.setModel(model);
        tableInfo.getColumnModel().getColumn(5).setPreferredWidth(40);
        tableInfo.getColumnModel().getColumn(6).setPreferredWidth(40);
        tableInfo.getColumnModel().getColumn(7).setPreferredWidth(40);
        tableInfo.getColumnModel().getColumn(8).setPreferredWidth(40);
        tableInfo.getTableHeader().setReorderingAllowed(false); //Hace imposible mover las columnas de lugar.
    }

    class MyTableCellRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return (Component) value;
        }
    }
}

