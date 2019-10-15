package br.senai.sp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

import br.senai.sp.dao.UsuarioDao;
import br.senai.sp.model.Usuario;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private Color cor;
	private String senha;
	
	private static String perfil;
	

	/**
	 * Create the frame.
	 */

	public FrmLogin() {
		setResizable(false);
		setTitle("Login");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(FrmLogin.class.getResource("/br/senai/sp/img/Janela_icone.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 223);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelConteudo = new JPanel();
		panelConteudo.setBounds(0, 0, 380, 206);
		contentPane.add(panelConteudo);
		panelConteudo.setLayout(null);

		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(new Color(245, 245, 245));
		panelLogin.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelLogin.setBounds(0, 0, 380, 206);
		panelConteudo.add(panelLogin);
		panelLogin.setLayout(null);

		txtUsuario = new JTextField();
		txtUsuario.setForeground(Color.GRAY);
		txtUsuario.setColumns(10);
		txtUsuario.setBorder(null);
		txtUsuario.setBackground(new Color(245, 245, 245));
		txtUsuario.setBounds(64, 28, 257, 20);
		TextPrompt tpUsuario = new TextPrompt("Usuario", txtUsuario);
		tpUsuario.setForeground(new Color(128, 128, 128));
		panelLogin.add(txtUsuario);

		txtSenha = new JPasswordField();
		txtSenha.setForeground(Color.GRAY);
		txtSenha.setEchoChar('●');
		txtSenha.setBorder(null);
		txtSenha.setBackground(new Color(245, 245, 245));
		txtSenha.setBounds(64, 69, 257, 20);
		TextPrompt tpSenha = new TextPrompt("Senha", txtSenha);
		tpSenha.setForeground(new Color(128, 128, 128));
		panelLogin.add(txtSenha);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtUsuario.getText().equals("Usuario") || txtSenha.getText().equals("Senha")) {

					JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos.", "Aviso",
							JOptionPane.WARNING_MESSAGE);

				} else {

					ArrayList<Usuario> usuarios = new ArrayList<>();
					UsuarioDao usuarioDAO = new UsuarioDao();
					usuarios = usuarioDAO.verificarUsuario();

					int verifUser = 0;
					
					perfil = txtUsuario.getText();

					for (Usuario listaUser : usuarios) {

						if (listaUser.getNome().equals(perfil)) {
							verifUser = 1;
						}
					}

					if (verifUser == 0) {
						JOptionPane.showMessageDialog(null, "Usuario Incorreto", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (verifUser == 1) {

						Usuario user = new Usuario();
						UsuarioDao pass = new UsuarioDao();

						user = pass.getUserByNome(perfil);

						String senha = new String(txtSenha.getPassword());

						if (user.getSenha().equals(senha)) {
							FrmMusica login = new FrmMusica();
							dispose();

							Home entrar = new Home();
							entrar.setVisible(true);
							entrar.setLocationRelativeTo(null);

						} else {
							JOptionPane.showMessageDialog(null, "Senha incorreta.", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
						}

					}

				}
			}
		});
		btnLogin.setForeground(Color.DARK_GRAY);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBorder(null);
		btnLogin.setBackground(new Color(128, 128, 128));
		btnLogin.setBounds(64, 110, 257, 37);
		panelLogin.add(btnLogin);

		JLabel lblRecuperar = new JLabel("Recuperar senha");
		lblRecuperar.setForeground(Color.DARK_GRAY);
		lblRecuperar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				FrmRecuperarSenha recuperar = new FrmRecuperarSenha();
				recuperar.setVisible(true);
				dispose();
			}
		});
		lblRecuperar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRecuperar.setBounds(90, 158, 98, 14);
		panelLogin.add(lblRecuperar);

		JLabel lblRegistrar = new JLabel("Registrar-se");
		lblRegistrar.setForeground(Color.DARK_GRAY);
		lblRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				FrmCadastro cadastro = new FrmCadastro();
				cadastro.setVisible(true);
				dispose();

			}
		});
		lblRegistrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRegistrar.setBounds(210, 158, 72, 14);
		panelLogin.add(lblRegistrar);

		JSeparator sprSenha = new JSeparator();
		sprSenha.setForeground(Color.GRAY);
		sprSenha.setBackground(Color.LIGHT_GRAY);
		sprSenha.setBounds(64, 90, 257, 10);
		panelLogin.add(sprSenha);

		JSeparator sprUsuario = new JSeparator();
		sprUsuario.setForeground(Color.GRAY);
		sprUsuario.setBackground(Color.LIGHT_GRAY);
		sprUsuario.setBounds(64, 49, 257, 10);
		panelLogin.add(sprUsuario);

		JLabel icoUsuario = new JLabel("");
		icoUsuario.setIcon(new ImageIcon(FrmLogin.class.getResource("/br/senai/sp/img/usuario.png")));
		icoUsuario.setBounds(36, 23, 30, 32);
		panelLogin.add(icoUsuario);

		JLabel icoSenha = new JLabel("");
		icoSenha.setIcon(new ImageIcon(FrmLogin.class.getResource("/br/senai/sp/img/senha.png")));
		icoSenha.setBounds(36, 64, 30, 39);
		panelLogin.add(icoSenha);

		JSeparator SprRecuperar = new JSeparator();
		SprRecuperar.setForeground(Color.GRAY);
		SprRecuperar.setBackground(Color.LIGHT_GRAY);
		SprRecuperar.setBounds(90, 172, 98, 10);
		panelLogin.add(SprRecuperar);

		JSeparator sprRegistrar = new JSeparator();
		sprRegistrar.setForeground(Color.GRAY);
		sprRegistrar.setBackground(Color.LIGHT_GRAY);
		sprRegistrar.setBounds(210, 172, 72, 10);
		panelLogin.add(sprRegistrar);
	}
	
	public static String getNomeUser() {
		return perfil;
	}

}
