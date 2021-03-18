/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas2;

import ControladorCliente.ClienteDao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.EntidadCliente;
import static vistas2.Inicio.tablaCliente;


/**
 *
 * @author moisesvelasco
 */
public class actualizar extends javax.swing.JInternalFrame {

    ClienteDao dao = new ClienteDao();
    EntidadCliente ec = new EntidadCliente();
    DefaultTableModel modeloCliente = new DefaultTableModel();
    int Aid,Aedad;
    String AfRegistro,Agenero,Anombre,AapellidoPat,AapellidoMat,Adomicilio,Acontacto,AfInicia,AfTermina;
    public actualizar() {
        initComponents();
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
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setTitle("¿DESEA ACTUALIZAR CAMPOS?");

        jLabel1.setFont(new java.awt.Font("Malayalam MN", 1, 18)); // NOI18N
        jLabel1.setText("SE DETECTARON CAMBIOS ");

        jLabel2.setFont(new java.awt.Font("Malayalam MN", 1, 18)); // NOI18N
        jLabel2.setText("¿DESEA ACTUALIZAR LOS DATOS?");

        jButton1.setBackground(new java.awt.Color(102, 102, 255));
        jButton1.setFont(new java.awt.Font("Malayalam MN", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SI");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Malayalam Sangam MN", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("NO");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(39, 39, 39)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void listarCliente(){
        try{
        List<EntidadCliente> lista =dao.listar();//metodo tipo list para traer todos los registros
        modeloCliente =(DefaultTableModel)tablaCliente.getModel();        
        Object[]ob = new Object[11];
        for(int i = 0;i<lista.size();i++){
            ob[0]=lista.get(i).getIdCliente();
            ob[1]=lista.get(i).getNombreCliente();
            ob[2]=lista.get(i).getApellidoPat();
            ob[3]=lista.get(i).getApellidoMat();
            ob[4]=lista.get(i).getDomicilio();
            ob[5]=lista.get(i).getContacto();
            ob[6]=lista.get(i).getFechaRegistro();
            ob[7]=lista.get(i).getFechaInicia();
            ob[8]=lista.get(i).getFechaTermina();
            ob[9]=lista.get(i).getEdad();
            ob[10]=lista.get(i).getSexo();
            
            modeloCliente.addRow(ob);
        }
        tablaCliente.setModel(modeloCliente);
        }
        catch(Exception e){ JOptionPane.showMessageDialog(this, "error en listar clientes"+e.getMessage()); }        
    }
    void limpiarTabla(){
        
        for(int i = 0;i<modeloCliente.getRowCount();i++ ){
        modeloCliente.removeRow(i);
        i=i-1;
        }
    }
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
     dispose();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
           Object[] ob = new Object[11];
           ob[0]=Anombre;
           ob[1]=AapellidoPat;
           ob[2]=AapellidoMat;
           ob[3]=Adomicilio;
           ob[4]=Acontacto;
           ob[5]=AfRegistro;
           ob[6]=AfInicia;
           ob[7]=AfTermina;
           ob[8]=Aedad;
           ob[9]=Agenero;
           ob[10]=Aid;         
           dao.actualizar(ob);           
           
           listarCliente();
           limpiarTabla();
           listarCliente();
           JOptionPane.showMessageDialog(this,"SE ACTUALIZO CORRECTAMENTE.");
           dispose();
    }//GEN-LAST:event_jButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}