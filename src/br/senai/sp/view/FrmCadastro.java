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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.color.CMMException;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Toolkit;

public class FrmCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JPasswordField txtReSenha;
	private JComboBox cbQuestao;
	private JTextField txtResposta;
	private Color cor;
	private String repetirSenha;
	private String senha;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JLabel iconAvatar1;
	private JLabel iconAvatar2;
	private JLabel iconAvatar3;
	private JLabel iconAvatar4;
	private JLabel iconAvatar5;
	private JLabel iconAvatar6;
	private JLabel iconAvatar7;
	private JLabel iconAvatar8;
	
	private JRadioButton rdbtnAvatar1;
	private JRadioButton rdbtnAvatar2;
	private JRadioButton rdbtnAvatar3;
	private JRadioButton rdbtnAvatar4;
	private JRadioButton rdbtnAvatar5;
	private JRadioButton rdbtnAvatar6;
	private JRadioButton rdbtnAvatar7;
	private JRadioButton rdbtnAvatar8;
	
	
	/**
	 * Create the frame.
	 */
	public FrmCadastro() {
		setResizable(false);
		setTitle("Cadastrar");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCadastro.class.getResource("/br/senai/sp/img/Janela_icone.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 635);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setForeground(Color.GRAY);
		txtUsuario.setColumns(10);
		txtUsuario.setBorder(null);
		txtUsuario.setBackground(new Color(245, 245, 245));
		txtUsuario.setBounds(63, 15, 257, 20);
		TextPrompt tpUsuario = new TextPrompt("Usuario", txtUsuario);
		tpUsuario.setForeground(new Color(128, 128, 128));
		contentPane.add(txtUsuario);
		
		txtSenha = new JPasswordField();
		txtSenha.setForeground(Color.GRAY);
		txtSenha.setEchoChar('●');
		txtSenha.setBorder(null);
		txtSenha.setBackground(new Color(245, 245, 245));
		txtSenha.setBounds(63, 57, 257, 20);
		TextPrompt tpSenha = new TextPrompt("Senha", txtSenha);
		tpSenha.setForeground(new Color(128, 128, 128));
		contentPane.add(txtSenha);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuario = new Usuario();
				UsuarioDao usuarioDoa = new UsuarioDao();
				
				senha =  new String (txtSenha.getPassword());
				repetirSenha = new String (txtReSenha.getPassword());
				
				String radioAvatar = "";
				
				if (rdbtnAvatar1.isSelected()) {
					radioAvatar = "Avatar 1";
				}else if (rdbtnAvatar2.isSelected()) {
					radioAvatar = "Avatar 2";
				}else if (rdbtnAvatar3.isSelected()) {
					radioAvatar = "Avatar 3";
				}else if (rdbtnAvatar4.isSelected()) {
					radioAvatar = "Avatar 4";
				}else if (rdbtnAvatar5.isSelected()) {
					radioAvatar = "Avatar 5";
				}else if (rdbtnAvatar6.isSelected()) {
					radioAvatar = "Avatar 6";
				}else if (rdbtnAvatar7.isSelected()) {
					radioAvatar = "Avatar 7";
				}else if (rdbtnAvatar8.isSelected()) {
					radioAvatar = "Avatar 8";
				}
				
				usuario.setNome(txtUsuario.getText());
				usuario.setSenha(senha);
				usuario.setQuestao(cbQuestao.getSelectedIndex());
				usuario.setResposta(txtResposta.getText());
				usuario.setAvatar(radioAvatar);
				
				
				if (txtUsuario.getText().equals("") || txtSenha.getText().equals("") || txtReSenha.getText().equals("")
						|| txtResposta.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos.", "Aviso",
							JOptionPane.WARNING_MESSAGE);

				}else if(radioAvatar.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor selecione um avatar.", "Aviso",
							JOptionPane.WARNING_MESSAGE);
					
				}else if (!repetirSenha.equalsIgnoreCase(senha)) {
					JOptionPane.showMessageDialog(null, "As senhas não conhecidem.", "Aviso",
							JOptionPane.WARNING_MESSAGE);
					System.out.println(repetirSenha);
					System.out.println(senha);
					
				} else {
					usuarioDoa.gravar(usuario);	
					dispose();
					
					FrmLogin login = new FrmLogin();
					login.setVisible(true);
				}
				
			}
		});
		btnRegistrar.setForeground(Color.DARK_GRAY);
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegistrar.setBorder(null);
		btnRegistrar.setBackground(Color.GRAY);
		btnRegistrar.setBounds(58, 555, 257, 37);
		contentPane.add(btnRegistrar);
		
		JSeparator sprSenha = new JSeparator();
		sprSenha.setForeground(Color.GRAY);
		sprSenha.setBackground(Color.LIGHT_GRAY);
		sprSenha.setBounds(63, 78, 257, 10);
		contentPane.add(sprSenha);
		
		JSeparator sprUsuario = new JSeparator();
		sprUsuario.setForeground(Color.GRAY);
		sprUsuario.setBackground(Color.LIGHT_GRAY);
		sprUsuario.setBounds(63, 36, 257, 10);
		contentPane.add(sprUsuario);
		
		JLabel IcoUsuario = new JLabel("");
		IcoUsuario.setIcon(new ImageIcon(FrmCadastro.class.getResource("/br/senai/sp/img/usuario.png")));
		IcoUsuario.setBounds(35, 11, 30, 32);
		contentPane.add(IcoUsuario);
		
		JLabel icoSenha = new JLabel("");
		icoSenha.setIcon(new ImageIcon(FrmCadastro.class.getResource("/br/senai/sp/img/senha.png")));
		icoSenha.setBounds(35, 49, 30, 39);
		contentPane.add(icoSenha);
		
		txtReSenha = new JPasswordField();
		txtReSenha.setForeground(Color.GRAY);
		txtReSenha.setEchoChar('●');
		txtReSenha.setBorder(null);
		txtReSenha.setBackground(new Color(245, 245, 245));
		txtReSenha.setBounds(63, 107, 257, 20);
		TextPrompt tpReSenha = new TextPrompt("Repetir Senha", txtReSenha);
		tpReSenha.setForeground(new Color(128, 128, 128));
		contentPane.add(txtReSenha);
		
		JSeparator sprRepSenha = new JSeparator();
		sprRepSenha.setForeground(Color.GRAY);
		sprRepSenha.setBackground(Color.LIGHT_GRAY);
		sprRepSenha.setBounds(63, 128, 257, 10);
		contentPane.add(sprRepSenha);
		
		JLabel icoSenhaR = new JLabel("");
		icoSenhaR.setIcon(new ImageIcon(FrmCadastro.class.getResource("/br/senai/sp/img/senha.png")));
		icoSenhaR.setBounds(35, 97, 30, 39);
		contentPane.add(icoSenhaR);
		
		JSeparator sprResposta = new JSeparator();
		sprResposta.setForeground(Color.GRAY);
		sprResposta.setBackground(Color.LIGHT_GRAY);
		sprResposta.setBounds(63, 229, 257, 10);
		contentPane.add(sprResposta);
		
		JLabel icoQuestao = new JLabel("");
		icoQuestao.setIcon(new ImageIcon(FrmCadastro.class.getResource("/br/senai/sp/img/quest\u00E3o.png")));
		icoQuestao.setBounds(38, 204, 22, 35);
		contentPane.add(icoQuestao);
		
		String questao [] = {"Qual o nome da sua mãe ?","Quantos irmõs(a) você tem ?", "Qual o nome do seu cachorro(a) ?", "Qual a data de aniversario do seu pai ?"};
		
		cbQuestao = new JComboBox(questao);
		cbQuestao.setBounds(39, 168, 285, 25);
		contentPane.add(cbQuestao);
		
		JLabel lblQuestoDeSegurana = new JLabel("Quest\u00E3o de Seguran\u00E7a:");
		lblQuestoDeSegurana.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuestoDeSegurana.setBounds(41, 151, 140, 14);
		contentPane.add(lblQuestoDeSegurana);
		
		txtResposta = new JTextField();
		
		txtResposta.setForeground(Color.GRAY);
		txtResposta.setColumns(10);
		txtResposta.setBorder(null);
		txtResposta.setBackground(new Color(245, 245, 245));
		txtResposta.setBounds(63, 209, 257, 20);
		TextPrompt tpResposta = new TextPrompt("Resposta", txtResposta);
		tpResposta.setForeground(new Color(128, 128, 128));
		System.out.println(txtResposta.getText());
		contentPane.add(txtResposta);
		
		JLabel label = new JLabel("Selecionar Avatar:");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(35, 253, 130, 24);
		contentPane.add(label);
		
		iconAvatar1 = new JLabel("");
		iconAvatar1.setIcon(new ImageIcon(FrmCadastro.class.getResource("/br/senai/sp/img/icone1.png")));
		iconAvatar1.setBounds(58, 288, 64, 64);
		contentPane.add(iconAvatar1);
		
		iconAvatar2 = new JLabel("");
		iconAvatar2.setIcon(new ImageIcon(FrmCadastro.class.getResource("/br/senai/sp/img/icone2.png")));
		iconAvatar2.setBounds(137, 288, 64, 64);
		contentPane.add(iconAvatar2);
		
		iconAvatar3 = new JLabel("");
		iconAvatar3.setIcon(new ImageIcon(FrmCadastro.class.getResource("/br/senai/sp/img/icone3.png")));
		iconAvatar3.setBounds(216, 288, 64, 64);
		contentPane.add(iconAvatar3);
		
		iconAvatar4 = new JLabel("");
		iconAvatar4.setIcon(new ImageIcon(FrmCadastro.class.getResource("/br/senai/sp/img/icone4.png")));
		iconAvatar4.setBounds(58, 375, 64, 64);
		contentPane.add(iconAvatar4);
		
		iconAvatar5 = new JLabel("");
		iconAvatar5.setIcon(new ImageIcon(FrmCadastro.class.getResource("/br/senai/sp/img/icone5.png")));
		iconAvatar5.setBounds(137, 375, 64, 64);
		contentPane.add(iconAvatar5);
		
		iconAvatar6 = new JLabel("");
		iconAvatar6.setIcon(new ImageIcon(FrmCadastro.class.getResource("/br/senai/sp/img/icone6.png")));
		iconAvatar6.setBounds(216, 375, 64, 64);
		contentPane.add(iconAvatar6);
		
		iconAvatar7 = new JLabel("");
		iconAvatar7.setIcon(new ImageIcon(FrmCadastro.class.getResource("/br/senai/sp/img/icone7.png")));
		iconAvatar7.setBounds(100, 455, 64, 64);
		contentPane.add(iconAvatar7);
		
		iconAvatar8 = new JLabel("");
		iconAvatar8.setIcon(new ImageIcon(FrmCadastro.class.getResource("/br/senai/sp/img/icone8.png")));
		iconAvatar8.setBounds(183, 455, 64, 64);
		contentPane.add(iconAvatar8);
		
		rdbtnAvatar1 = new JRadioButton("Avatar 1");
		buttonGroup.add(rdbtnAvatar1);
		rdbtnAvatar1.setBounds(53, 350, 73, 23);
		contentPane.add(rdbtnAvatar1);
		
		rdbtnAvatar2 = new JRadioButton("Avatar 2");
		buttonGroup.add(rdbtnAvatar2);
		rdbtnAvatar2.setBounds(135, 350, 73, 23);
		contentPane.add(rdbtnAvatar2);
		
		rdbtnAvatar3 = new JRadioButton("Avatar 3");
		buttonGroup.add(rdbtnAvatar3);
		rdbtnAvatar3.setBounds(217, 350, 73, 23);
		contentPane.add(rdbtnAvatar3);
		
		rdbtnAvatar4 = new JRadioButton("Avatar 4");
		buttonGroup.add(rdbtnAvatar4);
		rdbtnAvatar4.setBounds(54, 435, 73, 23);
		contentPane.add(rdbtnAvatar4);
		
		rdbtnAvatar5 = new JRadioButton("Avatar 5");
		buttonGroup.add(rdbtnAvatar5);
		rdbtnAvatar5.setBounds(135, 435, 73, 23);
		contentPane.add(rdbtnAvatar5);
		
		rdbtnAvatar6 = new JRadioButton("Avatar 6");
		buttonGroup.add(rdbtnAvatar6);
		rdbtnAvatar6.setBounds(217, 435, 73, 23);
		contentPane.add(rdbtnAvatar6);
		
		rdbtnAvatar8 = new JRadioButton("Avatar 8");
		buttonGroup.add(rdbtnAvatar8);
		rdbtnAvatar8.setBounds(182, 515, 73, 23);
		contentPane.add(rdbtnAvatar8);
		
		rdbtnAvatar7 = new JRadioButton("Avatar 7");
		buttonGroup.add(rdbtnAvatar7);
		rdbtnAvatar7.setBounds(97, 515, 73, 23);
		contentPane.add(rdbtnAvatar7);
		
		JLabel icoVoltar = new JLabel("");
		icoVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FrmLogin login = new FrmLogin();
				login.setVisible(true);
				dispose();
			}
		});
		icoVoltar.setToolTipText("Voltar");
		icoVoltar.setIcon(new ImageIcon(FrmCadastro.class.getResource("/br/senai/sp/img/voltar.png")));
		icoVoltar.setBounds(10, 15, 22, 25);
		contentPane.add(icoVoltar);
	}
}
