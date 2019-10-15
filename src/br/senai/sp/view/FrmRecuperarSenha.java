package br.senai.sp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.senai.sp.dao.UsuarioDao;
import br.senai.sp.model.Usuario;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FrmRecuperarSenha extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private Color cor;
	
	private static String user;
	
	/**
	 * Create the frame.
	 */
	public FrmRecuperarSenha() {
		setResizable(false);
		setTitle("Recuperar");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FrmRecuperarSenha.class.getResource("/br/senai/sp/img/Janela_icone.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 140);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUsuario = new JTextField();
		txtUsuario.setForeground(Color.GRAY);
		txtUsuario.setColumns(10);
		txtUsuario.setBorder(null);
		txtUsuario.setBackground(new Color(245, 245, 245));
		txtUsuario.setBounds(65, 15, 257, 20);
		TextPrompt tpUsuario = new TextPrompt("Usuario", txtUsuario);
		tpUsuario.setForeground(new Color(128, 128, 128));
		contentPane.add(txtUsuario);

		JSeparator sprUsuario = new JSeparator();
		sprUsuario.setForeground(Color.GRAY);
		sprUsuario.setBackground(Color.LIGHT_GRAY);
		sprUsuario.setBounds(65, 36, 257, 7);
		contentPane.add(sprUsuario);

		JLabel icoUsuario = new JLabel("");
		icoUsuario.setIcon(new ImageIcon(FrmRecuperarSenha.class.getResource("/br/senai/sp/img/usuario.png")));
		icoUsuario.setBounds(37, 11, 30, 32);
		contentPane.add(icoUsuario);

		JButton btnVerificar = new JButton("Vefificar");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtUsuario.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Campo Obrigatorio.", "Aviso",
							JOptionPane.WARNING_MESSAGE);

				} else {

					ArrayList<Usuario> usuarios = new ArrayList<>();
					UsuarioDao usuarioDAO = new UsuarioDao();
					usuarios = usuarioDAO.verificarUsuario();

					int verifUser = 0;
					user = txtUsuario.getText();

					for (Usuario listaUser : usuarios) {

						if (listaUser.getNome().equals(user)) {
							verifUser = 1;
						}
					}

					if (verifUser == 0) {
						JOptionPane.showMessageDialog(null, "Usuario Incorreto", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (verifUser == 1) {

						FrmRecuperarSeConf confirmar = new FrmRecuperarSeConf();
						confirmar.setVisible(true);
						dispose();

					}
				}
			}
		});
		btnVerificar.setForeground(Color.DARK_GRAY);
		btnVerificar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVerificar.setBorder(null);
		btnVerificar.setBackground(Color.GRAY);
		btnVerificar.setBounds(65, 56, 257, 37);
		contentPane.add(btnVerificar);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmLogin login = new FrmLogin();
				login.setVisible(true);
				dispose();
			}
		});
		label.setIcon(new ImageIcon(FrmRecuperarSenha.class.getResource("/br/senai/sp/img/voltar.png")));
		label.setToolTipText("Voltar");
		label.setBounds(10, 14, 22, 25);
		contentPane.add(label);
	}
	
	public static String getNomeUser() {
		return user;
	}
}
