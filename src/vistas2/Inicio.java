/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas2;

import ControladorCliente.ClienteDao;
import ControladorCliente.consultaCombo;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import modelo.EntidadCliente;


/**
 *
 * @author moisesvelasco
 */
public class Inicio extends javax.swing.JFrame {

    ClienteDao dao = new ClienteDao();//instanciamos laclase que necesitaremos para guardar y para obtener el registro
    EntidadCliente ec = new EntidadCliente();
    DefaultTableModel modeloCliente = new DefaultTableModel();
    DefaultComboBoxModel modelosexo = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloBox = new DefaultComboBoxModel();
    consultaCombo consultaBox = new consultaCombo();
    
    int id;//variable que recoje id desde mouseClick en tabla
    public Inicio() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        listarCliente();
        this.setLocationRelativeTo(null);
        
        
        
    }
    
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
        catch(Exception e){ JOptionPane.showMessageDialog(this, "error en listar"+e.getMessage()); }
        
    }
    
    void AgregarCliente(){
        
        try{
            //obtencion de los datos
        String nombreCliente=txtNombreCliente.getText();
        String apellidoPat =txtApellidoPat.getText();
        String apellidoMat =txtApellidoMat.getText();
        String domicilio=txtDomicilio.getText();
        String telefono=txtTelefono.getText();
        Date fechaRegistro= txtFechaRegistro.getDate();
        Date fechaInicia=  txtFechaInicia.getDate();
        Date fechaTermina= txtFechaTermina.getDate();
        int edad = Integer.parseInt(txtEdad.getText());
        String sexo = genero.getSelectedItem().toString();
                
        
        if(txtNombreCliente.getText().equals("") ||txtApellidoPat.getText().equals("") ||txtApellidoMat.getText().equals("") || txtDomicilio.getText().equals("")|| txtTelefono.getText().equals("") || txtFechaRegistro.getDate().equals("")|| txtFechaInicia.getDate().equals("")|| txtFechaTermina.getDate().equals("")|| txtEdad.getText().equals("") ){
            JOptionPane.showMessageDialog(this,"Por Favor Llena todos los Campos que se le pide");
            txtNombreCliente.requestFocus();
           }
        else{            
           Object[] ob = new Object[11];
           ob[0]=nombreCliente;
           ob[1]=apellidoPat;
           ob[2]=apellidoMat;
           ob[3]=domicilio;
           ob[4]=telefono;
           ob[5]=fechaRegistro;
           ob[6]=fechaInicia;
           ob[7]=fechaTermina;
           ob[8]=edad;
           ob[9]=sexo;
           ob[10]=id;           
           dao.add(ob);//agrgamos los datos llamando el metodo de la clase instanciada 
       }

        }catch(Exception e){ JOptionPane.showMessageDialog(this, "error en agregar favor de checar campos."+e.getMessage());}
        
    }
    
    void Actualizar(){
        try{
            int fila = tablaCliente.getSelectedRow();
        if(fila==-1){JOptionPane.showMessageDialog(this, "SELECCIONE UNA FILA PARA ACTUALIZAR");}
        else{
        String nombreCliente=txtNombreCliente.getText();
        String apellidoPat =txtApellidoPat.getText();
        String apellidoMat =txtApellidoMat.getText();
        String domicilio=txtDomicilio.getText();
        String telefono=txtTelefono.getText();
        Date fechaRegistro= txtFechaRegistro.getDate();
        Date fechaInicia=  txtFechaInicia.getDate();
        Date fechaTermina= txtFechaTermina.getDate();
        int edad = Integer.parseInt(txtEdad.getText());
        String sexo = genero.getSelectedItem().toString();
        
        if(txtNombreCliente.getText().equals("") ||txtApellidoPat.getText().equals("") ||txtApellidoMat.getText().equals("") || txtDomicilio.getText().equals("")|| txtTelefono.getText().equals("") || txtFechaRegistro.getDate().equals("")|| txtFechaInicia.getDate().equals("")|| txtFechaTermina.getDate().equals("")|| txtEdad.getText().equals("") ){
            JOptionPane.showMessageDialog(this,"Por Favor Llena todos los Campos que se le pide");
            txtNombreCliente.requestFocus();
           }
        else{            
           Object[] ob = new Object[11];
           ob[0]=nombreCliente;
           ob[1]=apellidoPat;
           ob[2]=apellidoMat;
           ob[3]=domicilio;
           ob[4]=telefono;
           ob[5]=fechaRegistro;
           ob[6]=fechaInicia;
           ob[7]=fechaTermina;
           ob[8]=edad;
           ob[9]=sexo;
           ob[10]=id;         
           dao.actualizar(ob);
       }
        
        }
        }catch(Exception e){JOptionPane.showMessageDialog(this, "Error en actualizar favor de checar campos");}       
    }
    void eliminar(){
        int fila =tablaCliente.getSelectedRow();
        if(fila==-1){JOptionPane.showMessageDialog(this, "debe selecionar una fila");}
        else{
            int ids = Integer.parseInt(tablaCliente.getValueAt(fila,0).toString());
            dao.eliminar(ids);       
        }    
    }
    void nuevo(){        
        txtNombreCliente.setText("");
        txtApellidoPat.setText("");
        txtApellidoMat.setText("");
        txtDomicilio.setText("");
        txtTelefono.setText("");       
        txtEdad.setText("");
        txtFechaRegistro.setDate(new Date());
        txtFechaInicia.setDate(new Date());
        txtFechaTermina.setDate(new Date());
        genero.setSelectedIndex(0);
        
    }
    void limpiarTabla(){
        for(int i = 0;i<modeloCliente.getRowCount();i++ ){
        modeloCliente.removeRow(i);
        i=i-1;
        }
    }
    void centrarVentanas(JInternalFrame frame){
        escritorio.add(frame);
        Dimension dimension=escritorio.getSize();
        Dimension Dframe = frame.getSize();
        frame.setLocation((dimension.width -Dframe.height)/2,(dimension.height -Dframe.height)/2);
        frame.show();
    }
   /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TrataSiNo = new javax.swing.ButtonGroup();
        escritorio = new javax.swing.JDesktopPane();
        jPanel4 = new javax.swing.JPanel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtDomicilio = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtFechaRegistro = new com.toedter.calendar.JDateChooser();
        txtFechaInicia = new com.toedter.calendar.JDateChooser();
        txtFechaTermina = new com.toedter.calendar.JDateChooser();
        btnRegistrar = new javax.swing.JButton();
        genero = new javax.swing.JComboBox<>();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        txtApellidoPat = new javax.swing.JTextField();
        txtApellidoMat = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        inventario = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        clientes = new javax.swing.JMenuItem();
        padecimientos = new javax.swing.JMenuItem();
        tratamiento = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        dientes = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        salir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        escritorio.setLayout(null);

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        jLabel38.setText("NOMBRE DEL PACIENTE:");

        jLabel39.setText("EDAD:");

        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });

        jLabel40.setText("DOMICILIO:");

        txtDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDomicilioActionPerformed(evt);
            }
        });

        jLabel41.setText("CONTACTO (telefono, correo, etc)");

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });

        jLabel42.setText("SEXO:");

        jLabel43.setText("INICIA TRATAMIENTO");

        jLabel44.setText("FECHA");

        jLabel45.setText("TERMINA TRATAMIENTO");

        txtFechaRegistro.setDateFormatString("dd/MM/yyyy");

        txtFechaInicia.setDateFormatString("dd/MM/yyyy");

        txtFechaTermina.setDateFormatString("dd/MM/yyyy");

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        genero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "¨GENERO¨", "Femenino", "Masculino" }));

        btnEliminar.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(153, 204, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel46.setText("Apellido Paterno");

        jLabel47.setText("Apellido Materno");

        jButton2.setText("NUEVO");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jLabel48.setText("En numero");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel46)
                .addGap(48, 48, 48)
                .addComponent(jLabel47)
                .addGap(50, 50, 50))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtApellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtApellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(18, 18, 18)
                                .addComponent(txtFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel48))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFechaTermina, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(27, 27, 27)
                                .addComponent(txtFechaInicia, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(89, 89, 89))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel44)
                                .addComponent(txtFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel42)
                                .addComponent(genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel39)
                        .addComponent(jLabel48)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(txtFechaInicia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFechaTermina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnEliminar)
                                    .addComponent(btnActualizar)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        escritorio.add(jPanel4);
        jPanel4.setBounds(620, 10, 640, 323);

        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre del paciente", "APELLIDO P", "APELLIDO M", "Domicilio", "Telefono", "Fecha de registro", "inicia ", "termina", "Edad", "Sexo"
            }
        ));
        tablaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCliente);
        if (tablaCliente.getColumnModel().getColumnCount() > 0) {
            tablaCliente.getColumnModel().getColumn(0).setMaxWidth(60);
            tablaCliente.getColumnModel().getColumn(1).setMaxWidth(200);
            tablaCliente.getColumnModel().getColumn(2).setMaxWidth(100);
            tablaCliente.getColumnModel().getColumn(3).setMaxWidth(100);
            tablaCliente.getColumnModel().getColumn(4).setMaxWidth(400);
            tablaCliente.getColumnModel().getColumn(5).setMaxWidth(130);
            tablaCliente.getColumnModel().getColumn(6).setMaxWidth(170);
            tablaCliente.getColumnModel().getColumn(7).setMaxWidth(170);
            tablaCliente.getColumnModel().getColumn(8).setMaxWidth(170);
            tablaCliente.getColumnModel().getColumn(9).setMaxWidth(60);
            tablaCliente.getColumnModel().getColumn(10).setMaxWidth(90);
        }

        escritorio.add(jScrollPane1);
        jScrollPane1.setBounds(10, 340, 1250, 300);

        jLabel54.setFont(new java.awt.Font("Kokonor", 1, 20)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("OD. MIGUEL ANGEL SUAREZ RODRIGUEZ");
        escritorio.add(jLabel54);
        jLabel54.setBounds(100, 10, 440, 31);

        jButton1.setText("Actualizar tabla");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        escritorio.add(jButton1);
        jButton1.setBounds(240, 290, 140, 29);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/foto1.jpg"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        escritorio.add(jLabel1);
        jLabel1.setBounds(100, 50, 430, 230);

        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        jMenu1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/menu.png"))); // NOI18N
        jMenu1.setText("MENU");
        jMenu1.setFont(new java.awt.Font("Lucida Grande", 1, 20)); // NOI18N

        inventario.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/inventario.png"))); // NOI18N
        inventario.setText("INVENTARIO");
        inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventarioActionPerformed(evt);
            }
        });
        jMenu1.add(inventario);
        jMenu1.add(jSeparator1);

        clientes.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cliente.png"))); // NOI18N
        clientes.setText("CLIENTES");
        clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesActionPerformed(evt);
            }
        });
        jMenu1.add(clientes);

        padecimientos.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        padecimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pad.png"))); // NOI18N
        padecimientos.setText("PADECIMIENTOS");
        padecimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                padecimientosActionPerformed(evt);
            }
        });
        jMenu1.add(padecimientos);

        tratamiento.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        tratamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/trat.png"))); // NOI18N
        tratamiento.setText("TRATAMIENTO");
        tratamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tratamientoActionPerformed(evt);
            }
        });
        jMenu1.add(tratamiento);

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jMenu1.add(jSeparator2);

        dientes.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        dientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/dl.png"))); // NOI18N
        dientes.setText("DIENTES");
        dientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dientesActionPerformed(evt);
            }
        });
        jMenu1.add(dientes);

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jMenu1.add(jSeparator3);

        salir.setFont(new java.awt.Font("Malayalam MN", 1, 24)); // NOI18N
        salir.setText("SALIR  ");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jMenu1.add(salir);

        jMenuBar1.add(jMenu1);

        jMenu2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ayuda.png"))); // NOI18N
        jMenu2.setText("AYUDA");
        jMenu2.setFont(new java.awt.Font("Lucida Grande", 1, 19)); // NOI18N

        jMenuItem5.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jMenuItem5.setText("VER PDF");
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/soporte.png"))); // NOI18N
        jMenu3.setText("SOPORTE");
        jMenu3.setFont(new java.awt.Font("Lucida Grande", 1, 20)); // NOI18N
        jMenu3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jMenu3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 1272, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDomicilioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDomicilioActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        AgregarCliente();
        nuevo();
        limpiarTabla();
        listarCliente();

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        Actualizar();
        nuevo();
        limpiarTabla();
        listarCliente();

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tablaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClienteMouseClicked
        // TODO add your handling code here:
        int fila = tablaCliente.getSelectedRow();
        if(fila==-1){JOptionPane.showMessageDialog(this, "debe selecionar una fila"); }
        else{
            try {
                //seleccionamos el numero de columna y le asignamos un valo
                id = Integer.parseInt(tablaCliente.getValueAt(fila, 0).toString());
                String nombreCliente = tablaCliente.getValueAt(fila, 1).toString();
                String apellidoPat = tablaCliente.getValueAt(fila, 2).toString();
                String apellidoMat = tablaCliente.getValueAt(fila, 3).toString();
                String domicilio=tablaCliente.getValueAt(fila, 4).toString();
                String telefono =tablaCliente.getValueAt(fila, 5).toString();
                String fechaRegistro =tablaCliente.getValueAt(fila, 6).toString();
                String fechaInicia =tablaCliente.getValueAt(fila, 7).toString();
                String fechaTermina=tablaCliente.getValueAt(fila, 8).toString();
                String edad=tablaCliente.getValueAt(fila, 9).toString();
                String sexo=tablaCliente.getValueAt(fila, 10).toString();

                txtNombreCliente.setText(nombreCliente);
                txtApellidoPat.setText(apellidoPat);
                txtApellidoMat.setText(apellidoMat);
                txtDomicilio.setText(domicilio);
                txtTelefono.setText(telefono);
                txtFechaRegistro.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(fechaRegistro));
                txtFechaInicia.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicia));
                txtFechaTermina.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(fechaTermina));
                txtEdad.setText(edad);
                genero.setSelectedItem(sexo);
                
                //creamos un objeto de la ventana folio y llamamos sus componentes para asignarles valor
                Folio f = new Folio();               
                f.idF=id;
                f.nomCliente=nombreCliente+" "+apellidoPat+" "+apellidoMat;                
                centrarVentanas(f);
            } catch (Exception e) {
            }

        }

    }//GEN-LAST:event_tablaClienteMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
        nuevo();
        limpiarTabla();
        listarCliente();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void padecimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_padecimientosActionPerformed
        // TODO add your handling code here:
        
        Padecimientos pad = new Padecimientos();
        escritorio.add(pad);
        pad.setVisible(true);
    }//GEN-LAST:event_padecimientosActionPerformed

    private void tratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tratamientoActionPerformed
        Tratamiento trat = new Tratamiento();
        escritorio.add(trat);
        trat.setVisible(true);        
    }//GEN-LAST:event_tratamientoActionPerformed

    private void dientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dientesActionPerformed
        Dientes d = new Dientes();
        escritorio.add(d);
        d.setVisible(true);
    }//GEN-LAST:event_dientesActionPerformed

    private void inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioActionPerformed
        // TODO add your handling code here:
        Inventario inv = new Inventario();
        centrarVentanas(inv);
        
    }//GEN-LAST:event_inventarioActionPerformed

    private void clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesActionPerformed
        Clientes c =new Clientes();
        escritorio.add(c);
        c.setVisible(true);
    }//GEN-LAST:event_clientesActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        nuevo();
        limpiarTabla();
        listarCliente();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        nuevo();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        JOptionPane.showMessageDialog(this,"PUEDE VER EL PDF DEL MANUAL O COMINICARTE"+"\n"+ "CONTACTO: 919 123 5135"+"\n"+"CORREO: moisesvela176@gmail.com");
    }//GEN-LAST:event_jMenu3MouseClicked

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup TrataSiNo;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JMenuItem clientes;
    private javax.swing.JMenuItem dientes;
    public static javax.swing.JDesktopPane escritorio;
    private javax.swing.JComboBox<String> genero;
    private javax.swing.JMenuItem inventario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem padecimientos;
    private javax.swing.JMenuItem salir;
    private javax.swing.JTable tablaCliente;
    private javax.swing.JMenuItem tratamiento;
    private javax.swing.JTextField txtApellidoMat;
    private javax.swing.JTextField txtApellidoPat;
    private javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtEdad;
    private com.toedter.calendar.JDateChooser txtFechaInicia;
    private com.toedter.calendar.JDateChooser txtFechaRegistro;
    private com.toedter.calendar.JDateChooser txtFechaTermina;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}