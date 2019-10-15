package br.senai.sp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.senai.sp.dao.UsuarioDao;
import br.senai.sp.model.Usuario;
import br.senai.sp.view.TextPrompt;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmAlterarSenha extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtSenha;
	private JPasswordField txtReSenha;
	private String repetirSenha;
	private String senha;
	
	private String perfil = FrmRecuperarSenha.getNomeUser();;
	
	/**
	 * Create the frame.
	 */
	public FrmAlterarSenha() {
		setTitle("Nova Senha");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmAlterarSenha.class.getResource("/br/senai/sp/img/Janela_icone.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 193);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel icoSenha = new JLabel("");
		icoSenha.setIcon(new ImageIcon(FrmAlterarSenha.class.getResource("/br/senai/sp/img/senha.png")));
		icoSenha.setBounds(40, 11, 30, 39);
		contentPane.add(icoSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setForeground(Color.GRAY);
		txtSenha.setEchoChar('●');
		txtSenha.setBorder(null);
		txtSenha.setBackground(new Color(245, 245, 245));
		txtSenha.setBounds(68, 19, 257, 20);
		TextPrompt tp1 = new TextPrompt("Senha", txtSenha);
		tp1.setForeground(new Color(128, 128, 128));
		contentPane.add(txtSenha);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(68, 44, 257, 10);
		contentPane.add(separator);
		
		JLabel icoReSenha = new JLabel("");
		icoReSenha.setIcon(new ImageIcon(FrmAlterarSenha.class.getResource("/br/senai/sp/img/senha.png")));
		icoReSenha.setBounds(40, 52, 30, 39);
		contentPane.add(icoReSenha);
		
		txtReSenha = new JPasswordField();
		txtReSenha.setForeground(Color.GRAY);
		txtReSenha.setEchoChar('●');
		txtReSenha.setBorder(null);
		txtReSenha.setBackground(new Color(245, 245, 245));
		txtReSenha.setBounds(68, 62, 257, 20);
		TextPrompt tp2 = new TextPrompt("Repetir Senha", txtReSenha);
		tp2.setForeground(new Color(128, 128, 128));
		contentPane.add(txtReSenha);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setBounds(68, 85, 257, 10);
		contentPane.add(separator_1);
		
		JButton btnAlterar = new JButton();
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
				UsuarioDao usuarioDoa = new UsuarioDao();
				
				senha =  new String (txtSenha.getPassword());
				repetirSenha = new String (txtReSenha.getPassword());
				
				usuario.setSenha(senha);
				usuario.setNome(perfil);
				
				if (txtSenha.getText().equals("") || txtReSenha.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos.", "Aviso",
							JOptionPane.WARNING_MESSAGE);

				}else if (!repetirSenha.equalsIgnoreCase(senha)) {
					JOptionPane.showMessageDialog(null, "As senhas não conhecidem.", "Aviso",
							JOptionPane.WARNING_MESSAGE);
					System.out.println(repetirSenha);
					System.out.println(senha);
					
				} else {
					usuarioDoa.alterarSenha(usuario);	
					dispose();
					
					FrmLogin login = new FrmLogin();
					login.setVisible(true);
				}
			}
		});
		btnAlterar.setText("Alterar");
		btnAlterar.setForeground(Color.DARK_GRAY);
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAlterar.setBorder(null);
		btnAlterar.setBackground(Color.GRAY);
		btnAlterar.setBounds(68, 102, 257, 37);
		contentPane.add(btnAlterar);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmRecuperarSeConf alterar = new FrmRecuperarSeConf();
				alterar.setVisible(true);
				dispose();
			}
		});
		label.setIcon(new ImageIcon(FrmAlterarSenha.class.getResource("/br/senai/sp/img/voltar.png")));
		label.setToolTipText("Voltar");
		label.setBounds(10, 19, 22, 25);
		contentPane.add(label);
	}
}
