/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package clix.crud.update;

import clix.components.combobox.ComboBoxMultiSelection;
import clix.view.home.Home;
import clix.manager.FormsManager;
import clix.model.ModelReceta;
import clix.util.db;
import raven.alerts.MessageAlerts;

import javax.swing.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author clint
 */
public class MainCrudUpdate2 extends JPanel {
        List<String> ingredientesG = new ArrayList<>();
        List<String> cantidadesG = new ArrayList<>();
        private ModelReceta receta;



        /**
         * Creates new form MainCrud2
         */
        public MainCrudUpdate2(ModelReceta receta) {
                this.receta = receta;
                initComponents();


                testData(comboBoxMultiSelection1);
                testData2(comboBoxMultiSelection2);

                // getData() modifica las listas ingredientesG y cantidadesG
                getDATA();

                // poner los datos en los campos
                comboBoxMultiSelection1.setSelectedItems(ingredientesG);
                comboBoxMultiSelection2.setSelectedItems(cantidadesG);

                // cambiar el color de los botones
                btnRegresar.setBackground(new java.awt.Color(253, 83, 83));
                btnRegresar.setForeground(new java.awt.Color(245, 245, 245));
                btnRegresar.setRippleColor(new java.awt.Color(255, 255, 255));
                btnRegresar.setShadowColor(new java.awt.Color(253, 83, 83));

                guardarReceta.setBackground(new java.awt.Color(30, 180, 114));
                guardarReceta.setForeground(new java.awt.Color(245, 245, 245));
                guardarReceta.setRippleColor(new java.awt.Color(255, 255, 255));
                guardarReceta.setShadowColor(new java.awt.Color(30, 180, 114));

        }


        public void  getDATA(){
                // sacar el comentario de la base de datos
                String sql = "SELECT nombre_autor, fecha, comentario FROM Comentarios WHERE id_receta = ?";
                try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
                        pst.setInt(1, receta.getId_receta());
                        try (ResultSet rs = pst.executeQuery()) {
                                if (rs.next()) {
                                        String nombre_autor = rs.getString(1);
                                        String fecha = rs.getString(2);
                                        String comentario = rs.getString(3);

                                        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                        LocalDate parsedDate = LocalDate.parse(fecha, inputFormatter);
                                        // lo cambia a 15/12/2023
                                        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                        fecha = outputFormatter.format(parsedDate);


                                        textAutor.setText(nombre_autor);
                                        txtDate.setText(fecha);
                                        textAreaComentario.setText(comentario);
                                }

                        }
                } catch (Exception ex) {
                        System.out.println(ex);
                }

                // sacar el favorito de la base de datos
                sql = "SELECT favorito FROM Favorito WHERE id_receta = ?";
                try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
                        pst.setInt(1, receta.getId_receta());
                        try (ResultSet rs = pst.executeQuery()) {
                                if (rs.next()) {
                                        boolean favorito = rs.getBoolean(1);
                                        if (favorito) {
                                                jComboBox1.setSelectedIndex(0);
                                        } else {
                                                jComboBox1.setSelectedIndex(1);
                                        }
                                }
                        }
                } catch (Exception ex) {
                        System.out.println(ex);
                }



                // sacar los ingredientes de la base de datos
                sql = "SELECT Ingrediente.nombre, Ingrediente.medida FROM Ingrediente INNER JOIN Ingrediente_receta ON Ingrediente.id_ingrediente = Ingrediente_receta.id_ingrediente WHERE Ingrediente_receta.id_receta = ?";
                try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
                        pst.setInt(1, receta.getId_receta());
                        try (ResultSet rs = pst.executeQuery()) {
                                while (rs.next()) {
                                        String nombre = rs.getString(1);
                                        String medida = rs.getString(2);
                                        ingredientesG.add(nombre);
                                        cantidadesG.add(medida);
                                }
                        }
                } catch (Exception ex) {
                        System.out.println(ex);
                }



        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        // @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                date = new clix.components.datechooser.DateChooser();
                jLabel1 = new JLabel();
                lblIngredieentes = new JLabel();
                jLabel3 = new JLabel();
                jLabel4 = new JLabel();
                lblComentario = new JLabel();
                jLabel6 = new JLabel();
                jScrollPane1 = new JScrollPane();
                textAreaComentario = new JTextArea();
                textAutor = new JTextField();
                comboBoxMultiSelection1 = new ComboBoxMultiSelection<>();
                guardarReceta = new clix.components.btn.Button();
                btnRegresar = new clix.components.btn.Button();
                txtDate = new JTextField();
                btnCalendar = new JButton();
                btnAhora = new JButton();
                lblComentario1 = new JLabel();
                jComboBox1 = new JComboBox<>();
                lblIngredieentes1 = new JLabel();
                comboBoxMultiSelection2 = new ComboBoxMultiSelection<>();
                jLabel2 = new JLabel();
                jLabel5 = new JLabel();
                jLabel7 = new JLabel();

                date.setForeground(new java.awt.Color(93, 142, 204));
                date.setTextRefernce(txtDate);

                jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
                jLabel1.setText("Termina de Actualizar tu receta");

                lblIngredieentes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                lblIngredieentes.setText("Ingredientes");

                jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jLabel3.setText("Nombre Autor");

                jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                jLabel4.setText("Comenta a tu receta");

                lblComentario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                lblComentario.setText("Comentario");

                jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jLabel6.setText("Fecha");

                textAreaComentario.setColumns(20);
                textAreaComentario.setRows(5);
                jScrollPane1.setViewportView(textAreaComentario);

                guardarReceta.setIcon(new ImageIcon(getClass().getResource("/iconsImg/minicohete.png"))); // NOI18N
                guardarReceta.setText("Actualizar");
                guardarReceta.addActionListener(this::guardarRecetaActionPerformed);

                btnRegresar.setIcon(
                                new ImageIcon(getClass().getResource("/iconsImg/flecha-izquierda.png"))); // NOI18N
                btnRegresar.setText("regresar");
                btnRegresar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnRegresarActionPerformed(evt);
                        }
                });

                txtDate.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtDateActionPerformed(evt);
                        }
                });

                btnCalendar.setIcon(new ImageIcon(getClass().getResource("/iconsImg/calen.png"))); // NOI18N
                btnCalendar.setText("calendario");
                btnCalendar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCalendarActionPerformed(evt);
                        }
                });

                btnAhora.setIcon(new ImageIcon(getClass().getResource("/iconsImg/today.png"))); // NOI18N
                btnAhora.setText("hoy");
                btnAhora.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAhoraActionPerformed(evt);
                        }
                });

                lblComentario1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                lblComentario1.setText("¿Es tu favorito?");

                jComboBox1.setModel(new DefaultComboBoxModel<>(new String[] { "si", "no" }));

                lblIngredieentes1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                lblIngredieentes1.setText("cantidad");

                jLabel2.setIcon(new ImageIcon(getClass().getResource("/iconsImg/cohete.png"))); // NOI18N

                jLabel5.setText("\"rellenar secuencialmente: ingrediente y cantidad \"");

                jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
                jLabel7.setText("\"Estamos preparándonos para despegar 🚀\"");

                GroupLayout layout = new GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(53, 53, 53)
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.TRAILING)
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addComponent(lblIngredieentes1)
                                                                                                                                .addGap(56, 56, 56)
                                                                                                                                .addComponent(comboBoxMultiSelection2,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                565,
                                                                                                                                                Short.MAX_VALUE))
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addComponent(lblIngredieentes)
                                                                                                                                .addGap(31, 31, 31)
                                                                                                                                .addComponent(comboBoxMultiSelection1,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE))))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(167, 167, 167)
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.TRAILING)
                                                                                                                .addComponent(jLabel5,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                330,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel2,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                455,
                                                                                                                                GroupLayout.PREFERRED_SIZE))))
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addGap(181, 181,
                                                                                                                                                181)
                                                                                                                                .addComponent(btnAhora)
                                                                                                                                .addGap(22, 22, 22)
                                                                                                                                .addComponent(txtDate,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                111,
                                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                                                layout.createSequentialGroup()
                                                                                                                                                .addGap(144, 144,
                                                                                                                                                                144)
                                                                                                                                                .addComponent(jLabel4)
                                                                                                                                                .addGap(70, 70, 70)))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                                                layout
                                                                                                                                                .createSequentialGroup()
                                                                                                                                                .addGap(71, 71, 71)
                                                                                                                                                .addGroup(layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                                                                .addComponent(lblComentario)
                                                                                                                                                                .addComponent(jScrollPane1,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                315,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addComponent(lblComentario1)))
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addGap(70, 70, 70)
                                                                                                                                .addGroup(layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                                                false)
                                                                                                                                                .addComponent(jLabel3)
                                                                                                                                                .addComponent(textAutor,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                316,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(jLabel6)
                                                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                                                .addComponent(btnCalendar)
                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                .addComponent(jComboBox1,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                140,
                                                                                                                                                                                GroupLayout.PREFERRED_SIZE))))))
                                                                .addGap(133, 133, 133))
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(240, Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                layout.createSequentialGroup()
                                                                                                                .addComponent(jLabel1)
                                                                                                                .addGap(363, 363,
                                                                                                                                363))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(jLabel7,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                235,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(btnRegresar,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                108,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(58, 58, 58)
                                                                                                                .addComponent(guardarReceta,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                163,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(429, 429,
                                                                                                                                429)))));
                layout.setVerticalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(21, 21, 21)
                                                                .addComponent(jLabel1)
                                                                .addGap(70, 70, 70)
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(jLabel4)
                                                                                                .addGap(24, 24, 24)
                                                                                                .addComponent(jLabel3)
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(textAutor,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(28, 28, 28)
                                                                                                .addComponent(jLabel6)
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(txtDate,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(btnCalendar)
                                                                                                                .addComponent(btnAhora))
                                                                                                .addGap(41, 41, 41)
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(lblComentario1)
                                                                                                                .addComponent(jComboBox1,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(36, 36, 36)
                                                                                                .addComponent(lblComentario)
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jScrollPane1,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                61,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(guardarReceta,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(btnRegresar,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(16, 16, 16))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(lblIngredieentes)
                                                                                                                .addComponent(comboBoxMultiSelection1,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                30,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(lblIngredieentes1)
                                                                                                                .addComponent(comboBoxMultiSelection2,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                30,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(4, 4, 4)
                                                                                                .addComponent(jLabel5)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jLabel2,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                268,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jLabel7)
                                                                                                .addContainerGap(
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)))));
        }// </editor-fold>//GEN-END:initComponents

        private void testData(JComboBox<String> combo) {
                combo.setModel(new DefaultComboBoxModel<>(new String[] {
                                "Ajo",
                                "Cebolla",
                                "Tomate",
                                "Zanahoria",
                                "Pimiento",
                                "Pepino",
                                "Lechuga",
                                "Espinaca",
                                "Papa",
                                "Batata",
                                "Calabaza",
                                "Brócoli",
                                "Coliflor",
                                "Apio",
                                "Espárragos",
                                "Champiñones",
                                "Maíz",
                                "Guisantes",
                                "Frijoles",
                                "Lentejas",
                                "Arroz",
                                "Quinoa",
                                "Trigo",
                                "Harina",
                                "Maicena",
                                "Azúcar",
                                "Sal",
                                "Pimienta",
                                "Vinagre",
                                "Aceite",
                                "Mantequilla",
                                "Leche",
                                "Huevos",
                                "Harina de trigo",
                                "Levadura",
                                "Canela",
                                "Nuez Moscada",
                                "Jengibre",
                                "Cúrcuma",
                                "Comino",
                                "Orégano",
                                "Perejil",
                                "Albahaca",
                                "Cilantro",
                                "Miel",
                                "Mostaza",
                                "Salsa de soja",
                                "Salsa Worcestershire",
                                "Salsa de tomate",
                                "Salsa de chile",
                                "Caldo de pollo",
                                "Caldo de verduras"
                }));

        }

        private void testData2(JComboBox<String> combo) {
                combo.setModel(new DefaultComboBoxModel<>(new String[] {
                                "100 gramos",
                                "200 gramos",
                                "300 gramos",
                                "400 gramos",
                                "500 gramos",
                                "1 kilo",
                                "2 kilos",
                                "3 kilos",
                                "4 kilos",
                                "5 kilos",
                                "1 litro",
                                "2 litros",
                                "3 litros",
                                "4 litros",
                                "5 litros",
                                "10 mililitros",
                                "20 mililitros",
                                "30 mililitros",
                                "40 mililitros",
                                "50 mililitros",
                                "1 cucharada",
                                "2 cucharadas",
                                "3 cucharadas",
                                "4 cucharadas",
                                "5 cucharadas",
                                "1 cucharadita",
                                "2 cucharaditas",
                                "3 cucharaditas",
                                "1 taza",
                                "2 tazas",
                                "3 tazas",
                                "4 tazas",
                                "5 tazas",
                                "1 unidad",
                                "2 unidades",
                                "3 unidades",
                                "4 unidades",
                                "5 unidades",
                                "6 unidades",
                }));

        }

        private void guardarRecetaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_guardarRecetaActionPerformed

                List<String> ingredientes = new ArrayList<>(comboBoxMultiSelection1.getSelectedItems());
                List<String> cantidades = new ArrayList<>(comboBoxMultiSelection2.getSelectedItems());



                String autor = textAutor.getText();
                String fechatxt = txtDate.getText();

                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate parsedDate = LocalDate.parse(fechatxt, inputFormatter);
                Date fecha = java.sql.Date.valueOf(parsedDate);

                String comentario = textAreaComentario.getText();
                // hace que favorito sea un booleano
                String favoritoText = Objects.requireNonNull(jComboBox1.getSelectedItem()).toString();
                // tru si es si y false si es no y enviar como booleano
                boolean favorito = favoritoText.equals("si");


                if (ingredientes.isEmpty() || cantidades.isEmpty() || autor.equals("") || fechatxt.equals("") || comentario.equals("")) {
                        MessageAlerts.getInstance().showMessage("Error", "Por favor, rellene todos los campos", MessageAlerts.MessageType.WARNING);

                } else {
                        try {
                                // Primero, elimina todas las relaciones de ingredientes existentes para la receta
                                String sqlDelete = "DELETE FROM Ingrediente_receta WHERE id_receta = ?";
                                try (PreparedStatement pst = db.getConnection().prepareStatement(sqlDelete)) {
                                        pst.setInt(1, receta.getId_receta());
                                        pst.executeUpdate();
                                } catch (Exception ex) {
                                        System.out.println(ex);
                                }

                                // Luego, para cada ingrediente y cantidad en las listas
                                for (int i = 0; i < ingredientes.size(); i++) {
                                        String ingrediente = ingredientes.get(i);
                                        String cantidad = cantidades.get(i);

                                        // Inserta el nuevo ingrediente en la tabla Ingrediente y obtén su id_ingrediente
                                        String sqlInsertIngrediente = "INSERT INTO Ingrediente (nombre, medida) VALUES (?, ?) RETURNING id_ingrediente";
                                        try (PreparedStatement pst = db.getConnection().prepareStatement(sqlInsertIngrediente)) {
                                                pst.setString(1, ingrediente);
                                                pst.setString(2, cantidad);
                                                try (ResultSet rs = pst.executeQuery()) {
                                                        if (rs.next()) {
                                                                int id_ingrediente = rs.getInt(1);

                                                                // Inserta la nueva relación en la tabla Ingrediente_receta
                                                                String sqlInsertRelacion = "INSERT INTO Ingrediente_receta (id_receta, id_ingrediente) VALUES (?, ?)";
                                                                try (PreparedStatement pst2 = db.getConnection().prepareStatement(sqlInsertRelacion)) {
                                                                        pst2.setInt(1, receta.getId_receta());
                                                                        pst2.setInt(2, id_ingrediente);
                                                                        pst2.executeUpdate();
                                                                }
                                                        }
                                                }
                                        } catch (Exception ex) {
                                                System.out.println(ex);
                                        }
                                }


                                // actualiza el comentario de la receta
                                String sqlB = "UPDATE Comentarios SET nombre_autor = ?, fecha = ?, comentario = ? WHERE id_receta = ?";
                                try (PreparedStatement pst = db.getConnection().prepareStatement(sqlB)) {
                                        pst.setString(1, autor);
                                        pst.setDate(2, fecha);
                                        pst.setString(3, comentario);
                                        pst.setInt(4, receta.getId_receta());
                                        pst.executeUpdate();
                                } catch (Exception ex) {
                                        System.out.println("Comentarios: " +ex);
                                }


                                // actualiza el favorito
                                String sqlC = "UPDATE Favorito SET favorito = ? WHERE id_receta = ?";
                                try (PreparedStatement pst = db.getConnection().prepareStatement(sqlC)) {
                                        pst.setBoolean(1, favorito);
                                        pst.setInt(2, receta.getId_receta());
                                        pst.executeUpdate();
                                } catch (Exception ex) {
                                        System.out.println("Favorito: " + ex);
                                }

                                MessageAlerts.getInstance().showMessage("Exito", "Receta actualizada correctamente", MessageAlerts.MessageType.SUCCESS);

                                FormsManager.getInstance().showForm(new Home());
                        } catch (Exception ex) {
                                System.out.println("Error: " + ex);
                        }
                }


        }// GEN-LAST:event_guardarRecetaActionPerformed

        private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRegresarActionPerformed
                FormsManager.getInstance().showForm(new MainCrudUpdate(receta));
        }// GEN-LAST:event_btnRegresarActionPerformed

        private void btnAhoraActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAhoraActionPerformed
                date.toDay();
        }// GEN-LAST:event_btnAhoraActionPerformed

        private void btnCalendarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCalendarActionPerformed
                date.showPopup();
        }// GEN-LAST:event_btnCalendarActionPerformed

        private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtDateActionPerformed

        }// GEN-LAST:event_txtDateActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private JButton btnAhora;
        private JButton btnCalendar;

        private ComboBoxMultiSelection comboBoxMultiSelection1;
        private ComboBoxMultiSelection comboBoxMultiSelection2;

        private clix.components.btn.Button btnRegresar;
        private clix.components.datechooser.DateChooser date;
        private clix.components.btn.Button guardarReceta;
        private JComboBox<String> jComboBox1;
        private JLabel jLabel1;
        private JLabel jLabel2;
        private JLabel jLabel3;
        private JLabel jLabel4;
        private JLabel jLabel5;
        private JLabel jLabel6;
        private JLabel jLabel7;
        private JScrollPane jScrollPane1;
        private JLabel lblComentario;
        private JLabel lblComentario1;
        private JLabel lblIngredieentes;
        private JLabel lblIngredieentes1;
        private JTextArea textAreaComentario;
        private JTextField textAutor;
        private JTextField txtDate;
        // End of variables declaration//GEN-END:variables

}
