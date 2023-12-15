
package clix.home;
import clix.components.Item;
import clix.crud.create.MainCrud;
import clix.crud.update.MainCrudUpdate;
import clix.event.EventItem;
import clix.manager.FormsManager;
import clix.manager.SessionManager;
import clix.model.Categoria;
import clix.model.Comentarios;
import clix.model.ModelReceta;
import clix.util.db;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author clint
 */
public class Home extends javax.swing.JPanel {
    private  Animator animator;
    private Point animatePoint;
    private ModelReceta itemSelected;



    // declarando variables para capturar la imagen---------------------------------------------------------------------


    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    }

    public Icon getImageOld() {
        return imageOld;
    }

    public void setImageOld(Icon imageOld) {
        this.imageOld = imageOld;
    }

    public Point getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(Point imageLocation) {
        this.imageLocation = imageLocation;
        repaint();
    }

    public Dimension getImageSize() {
        return imageSize;
    }

    public void setImageSize(Dimension imageSize) {
        this.imageSize = imageSize;
    }

    public Point getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(Point targetLocation) {
        this.targetLocation = targetLocation;
    }

    public Dimension getTargetSize() {
        return targetSize;
    }

    public void setTargetSize(Dimension targetSize) {
        this.targetSize = targetSize;
    }


    private Icon image;
    private Icon imageOld;
    private Point imageLocation;
    private Dimension imageSize;
    private Point targetLocation = new Point(10, 75);
    // llegada
    private Dimension targetSize = new Dimension(140, 160);


    // ------------------------------------------------------------------------------------------------------------------

    // metodos para capturar la imagen-----------------------------------------------------------------------------------

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        if (imageOld != null) {
            Rectangle size = getAutoSize(imageOld, targetSize);
            g2.drawImage(toImage(imageOld), targetLocation.x, targetLocation.y, size.getSize().width, size.getSize().height, null);
        }
        if (image != null) {
            Rectangle size = getAutoSize(image, imageSize);
            g2.drawImage(toImage(image), imageLocation.x, imageLocation.y, size.getSize().width, size.getSize().height, null);
        }
        g2.dispose();
    }



    private Rectangle getAutoSize(Icon image, Dimension size) {
        int w = size.width;
        int h = size.height;
        if (w > image.getIconWidth()) {
            w = image.getIconWidth();
        }
        if (h > image.getIconHeight()) {
            h = image.getIconHeight();
        }
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.max(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        int x = getWidth() / 2 - (width / 2);
        int y = getHeight() / 2 - (height / 2);
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }
    // ------------------------------------------------------------------------------------------------------------------

    public void setEventItem(EventItem eventItem) {
        this.eventItem = eventItem;
    }

    private EventItem eventItem;

    public void addItem(ModelReceta data){


        Item item = new Item();

        item.setData(data);

        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    eventItem.itemClick(item, data);

                }
            }
        });





        panelItem.add(item);
        panelItem.revalidate();
        panelItem.repaint();
    }

    public void testData(){

        setEventItem((com, item) -> {
            if (itemSelected != null){
                setImageOld(itemSelected.getImagen());
            }

                    itemSelected = item;
                    animatePoint = new Point(getPanelItemLocation().x + com.getLocation().x, getPanelItemLocation().y + 60  + com.getLocation().y);
                    setImage(item.getImagen());
                    setImageLocation(animatePoint);
                    setImageSize(new Dimension(115, 115));
                    repaint();
                    setSelected(com);
                    showItem(item);
                    animator.start();
        });


        List<ModelReceta> recetas = getRecetasFromDatabase();
        for (ModelReceta receta : recetas) {
            addItem(receta);
        }

        panelItem.revalidate();
        panelItem.repaint();



        /*
        int ID = 1;
        for (int i = 0; i <=1 ; i++) {
            addItem(new ModelReceta(ID++,"Caldo de gallina", "El Caldo de Gallina es una sopa clara con trozos de gallina o pollo, vegetales y a veces arroz. Tiene un aspecto transparente y aromático.", 30, "Instruccion de preparacion", 3, 2,new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/caldo_de_gallina.jpg")))) );
            addItem(new ModelReceta(ID++,"Arroz con huevo","El Arroz con Huevo es una mezcla de arroz cocido con huevos revueltos. Se presenta como una combinación de granos de arroz mezclados con huevo cocido, a veces con algunos trozos de vegetales o condimentos.", 30, "Instruccion de preparacion", 1, 1,new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/arroz_con_huevo.jpg")))) );
            addItem(new ModelReceta(ID++,"Pollito a la brasa","Pollo asado con piel crujiente, jugoso y característico de Perú.", 30, "Instruccion de preparacion", 2, 3,new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/pollo-a-la-braza.jpg")))) );
            addItem(new ModelReceta(ID++,"Cevice de pescado","El Ceviche de Pescado muestra trozos de pescado blanco crudo marinados en jugo cítrico, con ají y cebolla. Su aspecto es fresco y traslúcido por la marinada de limón.", 30, "Instruccion de preparacion", 2, 1,new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/ceviche.jpg")))) );
            addItem(new ModelReceta(ID++, "Chaufa de pollo","El Chaufa de Pollo es un plato peruano que mezcla arroz frito con trozos de pollo, salteados con verduras, huevo y salsa de soja. Es una fusión sabrosa de la cocina china con el estilo peruano.", 30, "Instruccion de preparacion", 3, 1,new ImageIcon(Objects.requireNonNull(getClass().getResource("/img/chaufa.jpg")))) );
        }
        */

    }

    public List<ModelReceta> getRecetasFromDatabase() {
        List<ModelReceta> recetas = new ArrayList<>();
        try {
            Statement st = db.getConnection().createStatement();
            String sql = "SELECT * FROM Receta";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id_receta");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double tiempo_de_preparacion = rs.getDouble("tiempo_de_preparacion");
                String instruccion_de_preparacion = rs.getString("instruccion_de_preparacion");
                int dificultad = rs.getInt("dificultad");
                int id_categoria = rs.getInt("id_categoria");
                byte[] imagenBytes = rs.getBytes("imagen");
                ImageIcon imagen = null;
                if (imagenBytes != null && imagenBytes.length > 0) {
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(imagenBytes));
                    if (img != null) {
                        imagen = new ImageIcon(img);
                    }
                }
                ModelReceta receta = new ModelReceta(id, nombre, descripcion, tiempo_de_preparacion, instruccion_de_preparacion, dificultad, id_categoria, imagen);
                recetas.add(receta);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return recetas;
    }




    public  void setSelected(Component item){
        for (Component component : panelItem.getComponents()) {
            Item item1 = (Item) component;
            if(item1.isSelected()){
                item1.setSelected(false);
            }
        }
        ((Item)item).setSelected(true);
    }
    public Home() {
        initComponents();
        testData();
        // scroll.setVerticalScrollBar(new ScrollBar());
        animator = PropertySetter.createAnimator(500, this, "imageLocation", animatePoint, targetLocation);
        animator.addTarget(new PropertySetter(this, "imageSize", new Dimension(115, 115), getTargetSize()));
        animator.addTarget(new TimingTargetAdapter(){
            @Override
            public void end() {
                setImageOld(null);
            }
        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);

        txtDescription.setOpaque(false);



    }

    public void showItem(ModelReceta data){
        lblItemName.setText(data.getNombre());
        txtDescription.setText(data.getDescripcion());
        lbDificultd.setText(data.getDificultad() == 1 ? "Facil" : data.getDificultad() == 2 ? "Moderado" : "Dificil");
        lbCatg.setText(data.getId_categoria() == 1 ? "Desayuno" : data.getId_categoria() == 2 ? "Almuerzo" : data.getId_categoria() == 3 ? "Cena" : "Postre");

        //lbTiempo.setText(data.getTiempo_de_preparacion() + " minutos");
        DecimalFormat df = new DecimalFormat("#.00");
        lbTiempo.setText(df.format(data.getTiempo_de_preparacion()) + " minutos");

    }

    public void cargarPasos(ModelReceta data){

        // global
        ModelReceta receta = new ModelReceta();
        Comentarios comentarios = new Comentarios();
        Categoria categoria = new Categoria();

        ArrayList<String> ingredientesG = new ArrayList<>();
        ArrayList<String> cantidadesG = new ArrayList<>();

        boolean fav = false;


        // sacamos la receta de la base de datos y la mostramos en un joptionpane
        String sql = "SELECT * FROM Receta WHERE id_receta = ?";
        try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
            pst.setInt(1, data.getId_receta());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double tiempo_de_preparacion = rs.getDouble("tiempo_de_preparacion");
                String instruccion_de_preparacion = rs.getString("instruccion_de_preparacion");
                int dificultad = rs.getInt("dificultad");
                int id_categoria = rs.getInt("id_categoria");
                byte[] imagenBytes = rs.getBytes("imagen");
                ImageIcon imagen = null;
                if (imagenBytes != null && imagenBytes.length > 0) {
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(imagenBytes));
                    if (img != null) {
                        imagen = new ImageIcon(img);
                    }
                }
                //
                receta.setNombre(nombre);
                receta.setDescripcion(descripcion);
                receta.setTiempo_de_preparacion(tiempo_de_preparacion);
                receta.setInstruccion_de_preparacion(instruccion_de_preparacion);
                receta.setDificultad(dificultad);
                receta.setId_categoria(id_categoria);
                receta.setImagen(imagen);




            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        // sacamos comentarios de la base
        String sqlCom = "SELECT nombre_autor, fecha, comentario FROM Comentarios WHERE id_receta = ?";
        try (PreparedStatement pst = db.getConnection().prepareStatement(sqlCom)) {
            pst.setInt(1, data.getId_receta());
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String nombre_autor = rs.getString(1);
                    String fecha = rs.getString(2);

                    SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = inputFormat.parse(fecha);
                    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String fechaDate = outputFormat.format(date);
                    Date date2 = outputFormat.parse(fechaDate);


                    String comentario = rs.getString(3);

                    comentarios.setNombre_autor(nombre_autor);
                    comentarios.setFecha(date2);
                    comentarios.setComentario(comentario);


                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }


        // sacar categorias de la base
        String sqlCat = "SELECT * FROM Categoria WHERE id_categoria = ?";
        try (PreparedStatement pst = db.getConnection().prepareStatement(sqlCat)) {
            pst.setInt(1, data.getId_categoria());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int id_categoria = rs.getInt("id_categoria");
                String nombre = rs.getString("nombre");

                categoria.setId_categoria(id_categoria);
                categoria.setNombre(nombre);

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        // sacamos ingredientes de la base
        String sqlIngCoM = "SELECT Ingrediente.nombre, Ingrediente.medida FROM Ingrediente INNER JOIN Ingrediente_receta ON Ingrediente.id_ingrediente = Ingrediente_receta.id_ingrediente WHERE Ingrediente_receta.id_receta = ?";
        try (PreparedStatement pst = db.getConnection().prepareStatement(sqlIngCoM)) {
            pst.setInt(1, data.getId_receta());
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String nombre = rs.getString(1);
                    String medida = rs.getString(2);

                    // creamos un arraylist para guardar los ingredientes y cantidades

                    ingredientesG.add(nombre);
                    cantidadesG.add(medida);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        // sacamos si es favorito o no
        String sqlFav = "SELECT * FROM Favorito WHERE id_receta = ?";
        try (PreparedStatement pst = db.getConnection().prepareStatement(sqlFav)) {
            pst.setInt(1, data.getId_receta());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                fav = true;
            }
            System.out.println(fav);
        } catch (Exception ex) {
            System.out.println(ex);
        }



        String path = "recetas.pdf";

        // usaremos  IText 7 Core » 8.0.2

        try {
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);

            // dar titulo al pdf

            // receta
            Paragraph titulo = new Paragraph(receta.getNombre());
            titulo.setTextAlignment(TextAlignment.CENTER);
            titulo.setFontColor(ColorConstants.RED);
            titulo.setBold();
            titulo.setFontSize(20);
            document.add(titulo);


            // descripcion:

            Paragraph desc = new Paragraph("Descripcion:");
            desc.setBold();
            document.add(desc);

            Paragraph descripcion = new Paragraph(receta.getDescripcion());
            descripcion.setTextAlignment(TextAlignment.LEFT);
            descripcion.setMarginBottom(20);
            document.add(descripcion);

            // tiempo de preparacion, dificultad y categoria
            Table table1 = new Table(2);
            table1.setPadding(15);
            table1.addCell(new Cell().add(new Paragraph("Tiempo de preparacion:").setBold()));
            table1.addCell(new Cell().add(new Paragraph(receta.getTiempo_de_preparacion() + " minutos")));
            table1.addCell(new Cell().add(new Paragraph("Dificultad:").setBold()));
            table1.addCell(new Cell().add(new Paragraph(receta.getDificultad() == 1 ? "Facil" : receta.getDificultad() == 2 ? "Moderado" : "Dificil")));
            table1.addCell(new Cell().add(new Paragraph("Categoria:").setBold()));
            table1.addCell(new Cell().add(new Paragraph(receta.getId_categoria() == 1 ? "Desayuno" : receta.getId_categoria() == 2 ? "Almuerzo" : receta.getId_categoria() == 3 ? "Cena" : "Postre")));
            table1.setMarginBottom(20);
            document.add(table1);

            // ingredientes
            // hace una trabala dinamica con la cantidad de ingredientes que tenga la receta pero solo muestra 2 columnas
            Table table2 = new Table(2);
            table2.setPadding(15);
            table2.addCell(new Cell().add(new Paragraph("Ingredientes:").setBold()));
            table2.addCell(new Cell().add(new Paragraph("Cantidad:").setBold()));
            for (int i = 0; i < ingredientesG.size(); i++) {
                table2.addCell(new Cell().add(new Paragraph(ingredientesG.get(i))));
                table2.addCell(new Cell().add(new Paragraph(cantidadesG.get(i))));
            }

            table2.setMarginBottom(20);
            document.add(table2);



            // instruccion de preparacion
            Paragraph instruccion = new Paragraph("Instruccion de preparacion:");
            instruccion.setBold();
            document.add(instruccion);
            String instruccion_de_preparacion = receta.getInstruccion_de_preparacion();
            instruccion_de_preparacion = instruccion_de_preparacion.replace("-", "\n").replace("_", "\n");
            document.add(new Paragraph(instruccion_de_preparacion));


            // comentarios
            Paragraph comen = new Paragraph("Comentarios:");
            comen.setBold();
            document.add(comen);

            // si no hay comentarios
            if (comentarios.getNombre_autor() == null){
                document.add(new Paragraph("No hay comentarios"));
            } else {
                // sacar la fecha solo como año-mes-dia
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                String fechaDate = outputFormat.format(comentarios.getFecha());
                System.out.println(fechaDate);

                document.add(new Paragraph(comentarios.getNombre_autor() + " - " + fechaDate));

                if (fav){
                    document.add(new Paragraph("Favorito"));
                } else {
                    document.add(new Paragraph("No es favorito"));
                }



                document.add(new Paragraph(comentarios.getComentario()));
            }

            // cerrar el documento




            document.close();


            // preguntar si desea abrir el pdf y la otra opcion es que el mismo guardara el pdf en la carpeta de su ordenador
            int resp = JOptionPane.showConfirmDialog(null, "¿Desea abrir el pdf?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                // abrir el pdf
                try {
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + path);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al abrir el pdf");
                }
            } else {
                // guardar el pdf
                JOptionPane.showMessageDialog(null, "El pdf se ha guardado en la carpeta de su ordenador");
            }







        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    // metodo para obtener la posicion del panelItem
    public Point getPanelItemLocation(){
        Point p = scroll.getLocation();
        return new Point(p.x, p.y - scroll.getViewport().getViewPosition().y);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        panelItem = new clix.swing.PanelItem();
        jPanel1 = new javax.swing.JPanel();
        lblItemName = new javax.swing.JLabel();
        lbCatg = new javax.swing.JLabel();
        lbDificultd = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtDescription = new javax.swing.JTextPane();
        lbTiempo = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnSeeMore = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nuestras Recetas");

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setEnabled(false);
        scroll.setViewportView(panelItem);

        jPanel1.setOpaque(false);

        lblItemName.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblItemName.setForeground(new java.awt.Color(76, 76, 76));
        lblItemName.setText("Nombre Receta");

        lbCatg.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCatg.setForeground(new java.awt.Color(76, 76, 76));
        lbCatg.setText("Categoria");

        lbDificultd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbDificultd.setForeground(new java.awt.Color(76, 76, 76));
        lbDificultd.setText("Dificultad");
        lbDificultd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtDescription.setBorder(null);
        txtDescription.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDescription.setForeground(new java.awt.Color(178, 178, 178));
        txtDescription.setFocusable(false);

        lbTiempo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTiempo.setForeground(new java.awt.Color(76, 76, 76));
        lbTiempo.setText("tiempo");
        lbTiempo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnDelete.setText("Eliminar");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnSeeMore.setText("Ver pasos");
        btnSeeMore.addActionListener(this::BtnSeeMoreActionPerformed);

        btnEdit.setText("Editar");
        btnEdit.addActionListener(this::btnEditActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbTiempo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblItemName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(47, 47, 47))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDificultd)
                            .addComponent(lbCatg))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDelete)
                        .addGap(9, 9, 9)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSeeMore))
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(342, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblItemName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDificultd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCatg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTiempo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnSeeMore)
                    .addComponent(btnEdit))
                .addGap(105, 105, 105))
        );

        jButton1.setText("Crear receta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("change theme");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(36, 36, 36)
                .addComponent(jButton2)
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int id = itemSelected.getId_receta();

        // mostrar mensaje de pregunta y pone el foco en el boton si o no
        int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar la receta?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                // Primero, elimina las filas en Ingrediente_receta que hacen referencia a la receta
                String sql = "DELETE FROM Ingrediente_receta WHERE id_receta = ?";
                try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
                    pst.setInt(1, id);
                    pst.executeUpdate();
                }

                // Luego, elimina las filas en Favorito que hacen referencia a la receta
                sql = "DELETE FROM Favorito WHERE id_receta = ?";
                try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
                    pst.setInt(1, id);
                    pst.executeUpdate();
                }

                // elimina los comentarios de la receta
                String sqlC = "DELETE FROM Comentarios WHERE id_receta = ?";
                try (PreparedStatement pst = db.getConnection().prepareStatement(sqlC)) {
                    pst.setInt(1, id);
                    pst.executeUpdate();
                }

                // Finalmente, elimina la receta
                sql = "DELETE FROM Receta WHERE id_receta = ?";
                try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
                    pst.setInt(1, id);
                    pst.executeUpdate();
                }

                JOptionPane.showMessageDialog(null, "La receta ha sido eliminada");

                FormsManager.getInstance().showForm(new Home());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La receta no ha sido eliminada");
        }





    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FormsManager.getInstance().showForm(new MainCrud());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BtnSeeMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cargarPasos(itemSelected);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerfverormed

        // itemSelected.getId_receta();
       // FormsManager.getInstance().showForm(new MainCrudUpdate());
        FormsManager.getInstance().showForm(new MainCrudUpdate(itemSelected));



    }//GEN-LAST:event_btnEditActionPerformed






    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSeeMore;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbCatg;
    private javax.swing.JLabel lbDificultd;
    private javax.swing.JLabel lbTiempo;
    private javax.swing.JLabel lblItemName;
    private clix.swing.PanelItem panelItem;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextPane txtDescription;
    // End of variables declaration//GEN-END:variables



}
