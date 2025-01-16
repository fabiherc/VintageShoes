package src.main.java.org.example.Vintage;

import src.main.java.org.example.SchuhAuswahl;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.*;

 class VintageShoes {

    // GUI-Komponenten
    private JFrame frmSchuhShop;
    private JTextField tfPreis, tfmaxPreis;
    private JComboBox<String> cbArt, cbMarke, cbFarbe, cbGroesse;
    private JCheckBox cbLeder;
    private JTextArea taAusgabe;
    private ArrayList<SchuhAuswahl> schuhListe = new ArrayList<>();

    // main-Methode für das Ausführen
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
               VintageShoes window = new VintageShoes();
                window.frmSchuhShop.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Konstruktor
    public VintageShoes() {
        initialize();  // Initialisierung der GUI
        initObjekte(); // Initialisierung von Beispiel-Schuhe
    }

    // Methode zur Initialisierung der GUI
    private void initialize() {
        // Fenster-Einstellungen
        frmSchuhShop = new JFrame();
        frmSchuhShop.getContentPane().setBackground(new Color(176, 224, 230));
        frmSchuhShop.setTitle("Vintage Shoes");
        frmSchuhShop.setBounds(100, 100, 800, 600);
        frmSchuhShop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSchuhShop.getContentPane().setLayout(null);
        frmSchuhShop.setLocationRelativeTo(null);

        //  Labels, Textfelder, Buttons und ComboBoxen
        JLabel lblMarke = new JLabel("Marke:");
        lblMarke.setBounds(20, 20, 80, 25);
        frmSchuhShop.getContentPane().add(lblMarke);
        cbMarke = new JComboBox<>(new String[]{"", "Adidas", "Nike", "Puma", "Uggs", "NewBalance", "Onitsuka Tiger", "Birkenstock", " Dr. Martins", "Uggs"});
        cbMarke.setBounds(100, 20, 150, 25);
        frmSchuhShop.getContentPane().add(cbMarke);

        JLabel lblArt = new JLabel("Art:");
        lblArt.setBounds(20, 60, 80, 25);
        frmSchuhShop.getContentPane().add(lblArt);
        cbArt = new JComboBox<>(new String[]{"", "Sneaker", "Boots", "Sandalen"});
        cbArt.setBounds(100, 60, 150, 25);
        frmSchuhShop.getContentPane().add(cbArt);

        JLabel lblFarbe = new JLabel("Farbe:");
        lblFarbe.setBounds(20, 100, 80, 25);
        frmSchuhShop.getContentPane().add(lblFarbe);
        cbFarbe = new JComboBox<>(new String[]{"", "weiss", "braun", "schwarz", "blau", "grau", "blau", "grün", "pink", "rot", "gelb"});
        cbFarbe.setBounds(100, 100, 150, 25);
        frmSchuhShop.getContentPane().add(cbFarbe);

        JLabel lblGroesse = new JLabel("Größe:");
        lblGroesse.setBounds(20, 140, 80, 25);
        frmSchuhShop.getContentPane().add(lblGroesse);
        cbGroesse = new JComboBox<>(new String[]{"", "36", "36.5", "37", "37.5", "38", "38.5", "39", "39.5", "40", "40.5", "41", "41.5", "42", "42.5", "43"});
        cbGroesse.setBounds(100, 140, 150, 25);
        frmSchuhShop.getContentPane().add(cbGroesse);

        JLabel lblPreis = new JLabel("Preis:");
        lblPreis.setBounds(20, 180, 80, 25);
        frmSchuhShop.getContentPane().add(lblPreis);
        tfPreis = new JTextField();
        tfPreis.setBounds(100, 180, 150, 25);
        frmSchuhShop.getContentPane().add(tfPreis);

        cbLeder = new JCheckBox("Leder");
        cbLeder.setBounds(100, 220, 100, 25);
        frmSchuhShop.getContentPane().add(cbLeder);


        JButton btnHinzufuegen = new JButton("Hinzufügen");
        btnHinzufuegen.addActionListener(e -> schuhHinzufuegen());
        btnHinzufuegen.setBounds(20, 260, 120, 30);
        frmSchuhShop.getContentPane().add(btnHinzufuegen);

        JButton btnListeanzeigen = new JButton("Liste anzeigen");
        btnListeanzeigen.addActionListener(e -> ListeAnzeigen());
        btnListeanzeigen.setBounds(160, 260, 120, 30);
        frmSchuhShop.getContentPane().add(btnListeanzeigen);

        JLabel lblmaxPreis = new JLabel("max. Preis");
        lblmaxPreis.setBounds(20, 300, 80, 25);
        frmSchuhShop.getContentPane().add(lblmaxPreis);
        tfmaxPreis = new JTextField();
        tfmaxPreis.setBounds(30, 330, 150, 25);
        frmSchuhShop.getContentPane().add(tfmaxPreis);

        JButton btnAusgeben = new JButton("Ausgeben");
        btnAusgeben.addActionListener(e -> schuhAusgeben());
        btnAusgeben.setBounds(160, 400, 120, 30);
        frmSchuhShop.getContentPane().add(btnAusgeben);

        taAusgabe = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(taAusgabe);
        scrollPane.setBounds(300, 20, 460, 520);
        frmSchuhShop.getContentPane().add(scrollPane);

        JButton btnLoeschen = new JButton("Löschen");
        btnLoeschen.addActionListener(e -> btnLoeschen());
        btnLoeschen.setBounds(160, 440, 120, 30);
        frmSchuhShop.getContentPane().add(btnLoeschen);

        // Markenerkennung für die "Art" ComboBox
        cbMarke.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateArt();
                }
            }
        });
    }

    // Schuhe hinzufügen
    private void schuhHinzufuegen() {
        try {
            // Auslesen der Werte aus der GUI
            String art = cbArt.getSelectedItem().toString();
            String marke = cbMarke.getSelectedItem().toString();
            String farbe = cbFarbe.getSelectedItem().toString();
            double groesse = Double.parseDouble(cbGroesse.getSelectedItem().toString());
            double preis = Double.parseDouble(tfPreis.getText());
            boolean leder = cbLeder.isSelected();

            // Fragen ob Eingabefelder  ausgefüllt sind
            if (art.isEmpty() || marke.isEmpty() || farbe.isEmpty()) {
                throw new Exception("Bitte fülle alle Felder aus.");
            }

            // Schuhobjekt erstellen und zur Liste hinzufügen
            SchuhAuswahl neuerSchuh = new SchuhAuswahl(art, marke, farbe, groesse, leder, preis);
            schuhListe.add(neuerSchuh);
            JOptionPane.showMessageDialog(frmSchuhShop, "Schuh hinzugefügt!", "", JOptionPane.INFORMATION_MESSAGE);

            zuruecksetzen();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frmSchuhShop, "Bitte gebe gültige Zahlen für den Preis ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmSchuhShop, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Zurücksetzen der Eingabefelder
    private void zuruecksetzen() {
        cbFarbe.setSelectedIndex(0);
        cbGroesse.setSelectedIndex(0);
        tfPreis.setText("");
        cbArt.setSelectedIndex(0);
        cbMarke.setSelectedIndex(0);
        cbLeder.setSelected(false);
    }

    // Anzeigen der Schuhliste
    private void ListeAnzeigen() {
        taAusgabe.setText(""); // TextArea leeren bevor Liste angezeigt wird
        for (SchuhAuswahl schuh : schuhListe) {
            taAusgabe.append("Art: " + schuh.getArt() + "\n");
            taAusgabe.append("Marke: " + schuh.getMarke() + "\n");
            taAusgabe.append("Größe: " + schuh.getGroeße() + "\n");
            taAusgabe.append("Farbe: " + schuh.getFarbe() + "\n");
            taAusgabe.append("Preis: " + schuh.getPreis() + "\n");
            taAusgabe.append("Leder: " + (schuh.isLeder() ? "Ja" : "Nein") + "\n");
            taAusgabe.append("\n");
        }
        if (taAusgabe.getText().isEmpty()) {
            taAusgabe.append("Keine Schuhe in der Liste.");
        }
    }
    // Filter und Ausgabe von Schuhen anhand des maximalen Preises
    private void schuhAusgeben() {
        taAusgabe.setText(""); // Leeren der Text Area
        try {
            double maxPreis = Double.parseDouble(tfmaxPreis.getText());

            for (SchuhAuswahl schuh : schuhListe) {
                // Nur Schuhe anzeigen die unter dem max. Preis liegen
                if (schuh.getPreis() <= maxPreis) {
                    taAusgabe.append("Art: " + schuh.getArt() + "\n");
                    taAusgabe.append("Marke: " + schuh.getMarke() + "\n");
                    taAusgabe.append("Größe: " + schuh.getGroeße() + "\n");
                    taAusgabe.append("Farbe: " + schuh.getFarbe() + "\n");
                    taAusgabe.append("Preis: " + schuh.getPreis() + "\n");
                    taAusgabe.append("Leder: " + (schuh.isLeder() ? "Ja" : "Nein") + "\n");
                    taAusgabe.append("\n");
                }
            }
            if (taAusgabe.getText().isEmpty()) {
                taAusgabe.append("Keine Schuhe gefunden, die den Kriterien entsprechen.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frmSchuhShop, "Bitte geben Sie einen gültigen Preis ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }
    //Zurücksetzen des Filters
    private void btnLoeschen() {
        taAusgabe.setText(""); // Leeren der TextArea
        tfmaxPreis.setText(""); // Leeren des maximalen Preis-Feldes
    }
    // Abhängigkeit bei der Marke erstellen
    private void updateArt() {
        cbArt.removeAllItems();
        String selectedItem = (String) cbMarke.getSelectedItem();

        if ("Adidas".equals(selectedItem)) {
            cbArt.addItem(" ");
            cbArt.addItem("Sneaker");
            cbArt.addItem("Sandalen");
        } else if ("Dr. Martens".equals(selectedItem)) {
            cbArt.addItem(" ");
            cbArt.addItem("Stiefel");
            cbArt.addItem("Sandalen");
        } else if ("Nike".equals(selectedItem)) {
            cbArt.addItem(" ");
            cbArt.addItem("Sneaker");
            cbArt.addItem("Sandalen");
        } else if ("Puma".equals(selectedItem)) {
            cbArt.addItem(" ");
            cbArt.addItem("Sneaker");
            cbArt.addItem("Boots");
        } else if ("Uggs".equals(selectedItem)) {
            cbArt.addItem(" ");
            cbArt.addItem("Boots");
        } else if ("New Balance".equals(selectedItem)) {
            cbArt.addItem(" ");
            cbArt.addItem("Sneaker");
            cbArt.addItem("Boots");
        } else if ("Birkenstock".equals(selectedItem)) {
            cbArt.addItem(" ");
            cbArt.addItem("Boots");
            cbArt.addItem("Sandalen");
        }
    }
    // 3 Beispiel-Schuhobjekte anlegen
    public void initObjekte() {
        SchuhAuswahl schuh1 = new SchuhAuswahl("Sneaker", "Adidas", "blau", 40, false, 110);
        SchuhAuswahl schuh2 = new SchuhAuswahl("Boots", "Uggs", "braun", 39, true, 150);
        SchuhAuswahl schuh3 = new SchuhAuswahl("Sandalen", "Birkenstock", "schwarz", 38.5, false, 80);
        schuhListe.add(schuh1);
        schuhListe.add(schuh2);
        schuhListe.add(schuh3);
    }
}

