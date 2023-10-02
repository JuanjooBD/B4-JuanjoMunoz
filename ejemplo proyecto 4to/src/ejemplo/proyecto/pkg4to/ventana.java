package ejemplo.proyecto.pkg4to;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ventana extends JFrame {

    JPanel panelInicioSesion, panelNewUser, panelClientes;
    JTextField txtUsuario;
    JPasswordField txtContra;
    usuario misUsuarios[] = new usuario[10];

    public ventana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        misUsuarios[0] = new usuario("Admin", "123");
        misUsuarios[1] = new usuario("Otro", "321");
    }

    public void iniciarComponentes() {
        //Panel
        panelInicioSesion = new JPanel();
        panelInicioSesion.setLayout(null);
        this.getContentPane().add(panelInicioSesion);
        componentesInicioSesion();
        //2do panel
        panelNewUser = new JPanel();
        panelNewUser.setLayout(null);
        this.getContentPane().add(panelNewUser);
        panelNewUser.setVisible(false);
        //3er panel
        panelClientes = new JPanel();
        panelClientes.setLayout(null);
        this.getContentPane().add(panelClientes);
        panelClientes.setVisible(false);
    }

    public void componentesInicioSesion() {
        this.setTitle("Inicio Sesion");        
//ETIQUETAS
        JLabel lblUsuario = new JLabel("Ingrese su usuario");
        lblUsuario.setBounds(50, 50, 150, 20);
        panelInicioSesion.add(lblUsuario);

        JLabel lblContra = new JLabel("Ingrese su Password");
        lblContra.setBounds(50, 80, 150, 20);
        panelInicioSesion.add(lblContra);

        //CUADROS DE TEXTO
        txtUsuario = new JTextField();
        txtUsuario.setBounds(180, 50, 150, 20);
        panelInicioSesion.add(txtUsuario);

        txtContra = new JPasswordField();
        txtContra.setBounds(180, 80, 150, 20);
        panelInicioSesion.add(txtContra);

        JButton btnIniciar = new JButton("Log in");
        btnIniciar.setBounds(50, 110, 150, 20);
        panelInicioSesion.add(btnIniciar);

        ActionListener InicioSesion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contra = txtContra.getText();
                BuscarUser(usuario, contra);
            }
        };
        btnIniciar.addActionListener(InicioSesion);
        //boton nuevo usuario
        JButton btnNewUser = new JButton("Register");
        btnNewUser.setBounds(50, 140, 150, 20);
        panelInicioSesion.add(btnNewUser);

        ActionListener newuser = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesNewUser();
                panelInicioSesion.setVisible(false);
                panelNewUser.setVisible(true);
            }
        };
        btnNewUser.addActionListener(newuser);

        panelInicioSesion.repaint();
    }

    public void BuscarUser(String usuario, String contra) {
        boolean encontrado = false;
        for (int i = 0; i <= misUsuarios.length - 1; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getName().equals(usuario) && misUsuarios[i].getPass().equals(contra)) {
                    encontrado = true;
                    break;
                }
            }
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Bienvenido");
        } else {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
    }

    public void componentesNewUser() {
        this.setTitle("New User");
        JLabel NewNombre = new JLabel("Ingrese nombre de usuario");
        NewNombre.setBounds(50, 50, 250, 20);
        panelNewUser.add(NewNombre);
        JLabel NewContra = new JLabel("Ingrese su contraseña de usuario");
        NewContra.setBounds(50, 80, 250, 20);
        panelNewUser.add(NewContra);
        JLabel ConfNewContra = new JLabel("Confirme su contraseña de usuario");
        ConfNewContra.setBounds(50, 110, 250, 20);
        panelNewUser.add(ConfNewContra);

        //CUADROS DE TEXTO
        JTextField txtUsuarioo = new JTextField();
        txtUsuarioo.setBounds(250, 50, 150, 20);
        panelNewUser.add(txtUsuarioo);

        JTextField txtContraa = new JPasswordField();
        txtContraa.setBounds(250, 80, 150, 20);
        panelNewUser.add(txtContraa);
        //BOTONES
        JTextField txtConfContra = new JPasswordField();
        txtConfContra.setBounds(250, 110, 150, 20);
        panelNewUser.add(txtConfContra);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 150, 150, 20);
        panelNewUser.add(btnGuardar);

        ActionListener guardar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtUsuarioo.getText();
                String contra = txtContraa.getText();
                SaveUser(nombre, contra);
                txtUsuarioo.setText("");
                txtContraa.setText("");
                txtConfContra.setText("");
            }
        };
        btnGuardar.addActionListener(guardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(50, 180, 150, 20);
        panelNewUser.add(btnVolver);

        ActionListener volver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesInicioSesion();
                panelInicioSesion.setVisible(true);
                panelNewUser.setVisible(false);
            }
        };
        btnVolver.addActionListener(volver);
    }

    public void SaveUser(String nombre, String contra) {

        if (nombre.equals("") || contra.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los  campos");
        } else {
            boolean vacio = false;
            int posicion = -1;
            for (int i = 0; i <= misUsuarios.length - 1; i++) {
                if (misUsuarios[i] != null) {
                    vacio = true;
                    posicion = i;
                    break;
                }
            }
            if (vacio) {
                misUsuarios[posicion] = new usuario(nombre, contra);
                JOptionPane.showMessageDialog(null, "Usuario guardado correctamente");
            } else {
                JOptionPane.showConfirmDialog(null, "El almacenamiento esta lleno");
            }
        }
    }

    public boolean comprobarDuplicadoUser(String nombre) {
        boolean duplicado = false;
        for (int i = 0; i <= 9; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getName().equals(nombre)) {
                    duplicado = true;
                    break;
                }
            }
        }
        return duplicado;
    }
    
    //------------------------------componentes para mostrar clientes
    
}
