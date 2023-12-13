
package clix.components;

import clix.model.ModelReceta;

import java.awt.*;

/**
 *
 * @author clint
 */
public class Item extends javax.swing.JPanel {
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public ModelReceta getData() {
        return data;
    }

    private ModelReceta data;
    public  void setData(ModelReceta data){
        this.data = data;
        pic.setImage(data.getImagen());
        lblItemName.setText(data.getNombre());
        lbDescription.setText(data.getDescripcion());
        switch (data.getId_categoria()) {
            case 1 -> lbCatg.setText("Desayuno");
            case 2 -> lbCatg.setText("Almuerzo");
            case 3 -> lbCatg.setText("Cena");
            case 4 -> lbCatg.setText("Postre");
        }
        switch (data.getDificultad()) {
            case 1 -> lbDificultd.setText("Facil");
            case 2 -> lbDificultd.setText("Moderado");
            case 3 -> lbDificultd.setText("Dificil");
        }



        // tiempo de preparacion
        /*
        * DecimalFormat df = new DecimalFormat("#.00");
        * df.format(data.getTiempo_de_preparacion());
        * */


        // instruccion de preparacion



    }
    public Item() {
        initComponents();
        // arreglar con el color de fondo
        setOpaque(false);

        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // no causa efecto ----------------------------------------------------------------------------------------------
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);


        if(selected){
            g2.setColor(new Color(94,156,255));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
        }
        g2.dispose();
        super.paint(grphcs);
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblItemName = new javax.swing.JLabel();
        lbDescription = new javax.swing.JLabel();
        pic = new clix.swing.PictureBox();
        lbDificultd = new javax.swing.JLabel();
        lbCatg = new javax.swing.JLabel();
        txtcorazon = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        lblItemName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblItemName.setForeground(new java.awt.Color(76, 76, 76));
        lblItemName.setText("Item Name");

        lbDescription.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDescription.setForeground(new java.awt.Color(178, 178, 178));
        lbDescription.setText("description");

        pic.setImage(new javax.swing.ImageIcon(getClass().getResource("/img/arroz_con_huevo.jpg"))); // NOI18N

        lbDificultd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbDificultd.setForeground(new java.awt.Color(76, 76, 76));
        lbDificultd.setText("Moderado");
        lbDificultd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbCatg.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCatg.setForeground(new java.awt.Color(76, 76, 76));
        lbCatg.setText("Categoria");

        txtcorazon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconsImg/corazon.png"))); // NOI18N
        txtcorazon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorazonActionPerformed(evt);
            }
        });

        jLabel1.setText("fecha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblItemName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDescription, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 30, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbCatg)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbDificultd))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtcorazon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblItemName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDescription)
                .addGap(18, 18, 18)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCatg)
                    .addComponent(lbDificultd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtcorazon, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtcorazonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorazonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorazonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbCatg;
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbDificultd;
    private javax.swing.JLabel lblItemName;
    private clix.swing.PictureBox pic;
    private javax.swing.JButton txtcorazon;
    // End of variables declaration//GEN-END:variables
}
