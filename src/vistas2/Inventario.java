/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.EntidadInventario;
import modelo.inventarioDao;

/**
 *
 * @author moisesvelasco
 */
public class Inventario extends javax.swing.JInternalFrame {

    inventarioDao dao = new inventarioDao();
    EntidadInventario EI = new EntidadInventario();
    DefaultTableModel modelo = new DefaultTableModel();
    int id;
    public Inventario() {
        initComponents();        
        listar();
    }
    void listar(){
        List<EntidadInventario> lista =dao.listar();
        modelo =(DefaultTableModel)tablaProductos.getModel();
        Object[]ob = new Object[5];
        for(int i = 0;i<lista.size();i++){
            ob[0]=lista.get(i).getidProducto();
            ob[1]=lista.get(i).getNombreProducto();
            ob[2]=lista.get(i).getCodigo();
            ob[3]=lista.get(i).getExistencia();
            ob[4]=lista.get(i).getFechaCaducidad();
            modelo.addRow(ob);
        }
        tablaProductos.setModel(modelo);
    }
    
    void Agregar(){
        try {
            String nombreProducto=txtNombreProducto.getText();
        String codigo=txtCodigo.getText();        
        int existencia=Integer.parseInt(txtExistencia.getText());
        Date fechaCaducidad=FechaCaducidad.getDate();
        
        if(txtNombreProducto.getText().equals("") || txtCodigo.getText().equals("")|| txtExistencia.getText().equals("") || FechaCaducidad.getDate().equals("")){
            JOptionPane.showMessageDialog(this,"Por Favor Llena todos los Campos que se le pide");
            txtNombreProducto.requestFocus();
           }
        else{   
           Object[] ob = new Object[4];
           ob[0]=nombreProducto;
           ob[1]=codigo;
           ob[2]=existencia;
           ob[3]=fechaCaducidad;
           dao.add(ob); 
            nuevo();
        }
        } catch (Exception e) {JOptionPane.showMessageDialog(this, "ERROR AL AGREGAR... FAVOR DE CHECAR CAMPOS");
        }
        
    }
    
    void Actualizar(){
        int fila = tablaProductos.getSelectedRow();
        if(fila==-1){JOptionPane.showMessageDialog(this, "debe selecionar una fila");}
        else{
        String nombreProducto=txtNombreProducto.getText();
        String codigo=txtCodigo.getText();      
        String existencia=txtExistencia.getText();
        Date fechaCaducidad=FechaCaducidad.getDate();
        Object[] obj = new Object[5];
        obj[0]=nombreProducto;
        obj[1]=codigo;
        obj[2]=existencia;
        obj[3]=fechaCaducidad;
        obj[4]=id;
        
        dao.actualizar(obj);
        }
    }
    void eliminar(){
        int fila =tablaProductos.getSelectedRow(); 
        if(fila==-1){JOptionPane .showMessageDialog(this, "debe selecionar una fila");}
        else{
            int ids = Integer.parseInt(tablaProductos.getValueAt(fila,0).toString());            
           dao.eliminar(ids);
            
           
        }
    
    }
    void nuevo(){
        txtNombreProducto.setText("");
        txtCodigo.setText("");
        txtExistencia.setText("");  
        FechaCaducidad.setDate(new Date());        
    }
    void limpiarTabla(){
        for(int i = 0;i<modelo.getRowCount();i++ ){
        modelo.removeRow(i);
        i=i-1;
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtExistencia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        FechaCaducidad = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("INVENTARIO ");

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PRODUCTO", "CODIGO", "EXISTENCIA", "FECHA DE CADUCIDAD"
            }
        ));
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProductos);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/inv.png"))); // NOI18N

        jLabel2.setText("Nombre del producto");

        jLabel3.setText("Codigo");

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel4.setText("Existencia");

        jLabel5.setText("FECHA DE CADUCIDAD");

        jButton5.setText("ACTUALIZAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregar)
                            .addComponent(jButton5)
                            .addComponent(btnEliminar)))
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnAgregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(btnEliminar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        Agregar();
        limpiarTabla();
        listar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
        limpiarTabla();
        listar();
        nuevo();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Actualizar();
        limpiarTabla();
        listar();
        nuevo();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        int fila = tablaProductos.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(this, "debe selecionar una fila");
        }
        else{
            try {

            //checar video su dni si es entero
            id = Integer.parseInt(tablaProductos.getValueAt(fila,0).toString());
            String nombreProducto= tablaProductos.getValueAt(fila,1).toString();
            String codigo=tablaProductos.getValueAt(fila,2).toString();
            String existencia =tablaProductos.getValueAt(fila,3).toString();
            String fechaCaducidad=tablaProductos.getValueAt(fila,4).toString();

            txtNombreProducto.setText(nombreProducto);
            txtCodigo.setText(codigo);
            txtExistencia.setText(existencia);
                            
                FechaCaducidad.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(fechaCaducidad));
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "error en tabla seleccionar"+e.getMessage());
            }
        }

    }//GEN-LAST:event_tablaProductosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser FechaCaducidad;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtExistencia;
    private javax.swing.JTextField txtNombreProducto;
    // End of variables declaration//GEN-END:variables
}
