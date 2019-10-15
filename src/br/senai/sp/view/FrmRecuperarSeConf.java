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

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmRecuperarSeConf extends JFrame {

	private JPanel contentPane;
	private JTextField txtResposta;
	private Color cor;
	private String usuario = FrmRecuperarSenha.getNomeUser();;

	/**
	 * Create the frame.
	 */
	public FrmRecuperarSeConf() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FrmRecuperarSeConf.class.getResource("/br/senai/sp/img/Janela_icone.png")));
		setTitle("Confirmar Identidade");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 160);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtResposta = new JTextField();
		txtResposta.setForeground(Color.GRAY);
		txtResposta.setBorder(null);
		txtResposta.setBackground(new Color(245, 245, 245));
		txtResposta.setBounds(63, 36, 257, 20);
		TextPrompt tpResposta = new TextPrompt("Resposta", txtResposta);
		tpResposta.setForeground(new Color(128, 128, 128));
		contentPane.add(txtResposta);

		JSeparator sprResposta = new JSeparator();
		sprResposta.setForeground(Color.GRAY);
		sprResposta.setBackground(Color.LIGHT_GRAY);
		sprResposta.setBounds(63, 57, 257, 10);
		contentPane.add(sprResposta);

		JLabel icoResposta = new JLabel("");
		icoResposta.setIcon(new ImageIcon(FrmRecuperarSeConf.class.getResource("/br/senai/sp/img/quest\u00E3o.png")));
		icoResposta.setBounds(38, 31, 22, 35);
		contentPane.add(icoResposta);
		
		JLabel lblQuestao = new JLabel("Quest\u00E3o:");
		
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario user = new Usuario();
		
		user = usuarioDao.getUserByNome(usuario);
		
		if(user.getQuestao() == 0) {
			lblQuestao.setText("Qual o nome da sua mãe ?");
		}else if(user.getQuestao() == 1) {
			lblQuestao.setText("Quantos irmõs(a) você tem ?");
		}else if(user.getQuestao() == 2) {
			lblQuestao.setText("Qual o nome do seu cachorro(a) ?");
		}else if(user.getQuestao() == 3) {
			lblQuestao.setText("Qual a data de aniversario do seu pai ?");
		}
		
		lblQuestao.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuestao.setBounds(63, 11, 265, 14);
		contentPane.add(lblQuestao);

		JButton btnVerificar = new JButton("Vefificar");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtResposta.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Campo Obrigatorio.", "Aviso", JOptionPane.WARNING_MESSAGE);

				} else {
					
					Usuario user = new Usuario();
					UsuarioDao pass = new UsuarioDao();
					

					user = pass.getUserByNome(usuario);


					if (user.getResposta().equals(txtResposta.getText().toUpperCase())) {
						
						dispose();

						FrmAlterarSenha alterar = new FrmAlterarSenha();
						
						alterar.setVisible(true);
						
					}else {
						JOptionPane.showMessageDialog(null, "Resposta Incorreta", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnVerificar.setForeground(Color.DARK_GRAY);
		btnVerificar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVerificar.setBorder(null);
		btnVerificar.setBackground(Color.GRAY);
		btnVerificar.setBounds(63, 77, 257, 37);
		contentPane.add(btnVerificar);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmRecuperarSenha alterar = new FrmRecuperarSenha();
				alterar.setVisible(true);
				dispose();
			}
		});
		label.setIcon(new ImageIcon(FrmRecuperarSeConf.class.getResource("/br/senai/sp/img/voltar.png")));
		label.setToolTipText("Voltar");
		label.setBounds(10, 5, 22, 25);
		contentPane.add(label);
		
	}
}
