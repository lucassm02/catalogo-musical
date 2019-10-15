package br.senai.sp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.SystemColor;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import br.senai.sp.dao.UsuarioDao;
import br.senai.sp.model.Artista;
import br.senai.sp.model.Usuario;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField txtPesquisa;
	private Color cor;
	private JMenu mnUser;

	private String perfil = FrmLogin.getNomeUser();

	/**
	 * Create the frame.
	 */
	public Home() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/br/senai/sp/img/Janela_icone.png")));
		setBackground(SystemColor.scrollbar);
		setTitle("Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 270);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mnUser.setSelected(false);
				mnUser.setPopupMenuVisible(false);
			}
		});
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setLocation(0, -73);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panelRegistrar = new JPanel();
		panelRegistrar.setBackground(new Color(220, 220, 220));
		panelRegistrar.setBounds(0, 122, 404, 119);
		contentPane.add(panelRegistrar);
		panelRegistrar.setLayout(null);

		JLabel icoArtista = new JLabel("");
		icoArtista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmArtista artista = new FrmArtista();
				artista.setVisible(true);
				dispose();
			}
		});
		icoArtista.setToolTipText("Artistas");
		icoArtista.setIcon(new ImageIcon(Home.class.getResource("/br/senai/sp/img/Icone_Artista.png")));
		icoArtista.setBounds(45, 22, 102, 97);
		panelRegistrar.add(icoArtista);

		JLabel lblMusica = new JLabel("");
		lblMusica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrmMusica musica = new FrmMusica();
				musica.setVisible(true);
				dispose();
			}
		});
		lblMusica.setToolTipText("Musicas");
		lblMusica.setIcon(new ImageIcon(Home.class.getResource("/br/senai/sp/img/Icone_Musica.png")));
		lblMusica.setBounds(260, 20, 102, 96);
		panelRegistrar.add(lblMusica);

		JSeparator sprArtisEMusic = new JSeparator();
		sprArtisEMusic.setOrientation(SwingConstants.VERTICAL);
		sprArtisEMusic.setForeground(Color.GRAY);
		sprArtisEMusic.setBackground(Color.LIGHT_GRAY);
		sprArtisEMusic.setBounds(201, 5, 2, 111);
		panelRegistrar.add(sprArtisEMusic);

		JLabel lblArtistas = new JLabel("Artistas");
		lblArtistas.setBackground(new Color(255, 255, 255));
		lblArtistas.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblArtistas.setForeground(new Color(128, 128, 128));
		lblArtistas.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtistas.setBounds(45, 5, 102, 24);
		panelRegistrar.add(lblArtistas);

		JLabel lblMusicas = new JLabel("Musicas");
		lblMusicas.setBackground(new Color(255, 255, 255));
		lblMusicas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMusicas.setForeground(new Color(128, 128, 128));
		lblMusicas.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMusicas.setBounds(260, 5, 102, 24);
		panelRegistrar.add(lblMusicas);

		JPanel panelUser = new JPanel();
		panelUser.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(211, 211, 211)));
		panelUser.setBackground(new Color(245, 245, 245));
		panelUser.setBounds(0, 0, 404, 53);
		contentPane.add(panelUser);

		JLabel IconUsuario = new JLabel("");
		IconUsuario.setBounds(9, 1, 50, 50);
		IconUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mnUser.doClick();
				mnUser.setSelected(true);
				mnUser.setPopupMenuVisible(true);

			}
		});

		Usuario user = new Usuario();
		UsuarioDao usuarioDao = new UsuarioDao();
		user = usuarioDao.getUserByNome(perfil);

		if (user.getAvatar().equals("Avatar 1")) {
			IconUsuario.setIcon(new ImageIcon(Home.class.getResource("/br/senai/sp/img/icone1_low.png")));
		} else if (user.getAvatar().equals("Avatar 2")) {
			IconUsuario.setIcon(new ImageIcon(Home.class.getResource("/br/senai/sp/img/icone2_low.png")));
		} else if (user.getAvatar().equals("Avatar 3")) {
			IconUsuario.setIcon(new ImageIcon(Home.class.getResource("/br/senai/sp/img/icone3_low.png")));
		} else if (user.getAvatar().equals("Avatar 4")) {
			IconUsuario.setIcon(new ImageIcon(Home.class.getResource("/br/senai/sp/img/icone4_low.png")));
		} else if (user.getAvatar().equals("Avatar 5")) {
			IconUsuario.setIcon(new ImageIcon(Home.class.getResource("/br/senai/sp/img/icone5_low.png")));
		} else if (user.getAvatar().equals("Avatar 6")) {
			IconUsuario.setIcon(new ImageIcon(Home.class.getResource("/br/senai/sp/img/icone6_low.png")));
		} else if (user.getAvatar().equals("Avatar 7")) {
			IconUsuario.setIcon(new ImageIcon(Home.class.getResource("/br/senai/sp/img/icone7_low.png")));
		} else if (user.getAvatar().equals("Avatar 8")) {
			IconUsuario.setIcon(new ImageIcon(Home.class.getResource("/br/senai/sp/img/icone8_low.png")));
		}
		panelUser.setLayout(null);
		panelUser.add(IconUsuario);

		JPanel panelPesquisa = new JPanel();
		panelPesquisa.setBounds(120, 10, 278, 35);
		panelUser.add(panelPesquisa);
		panelPesquisa.setBackground(new Color(245, 245, 245));
		panelPesquisa.setLayout(null);

		txtPesquisa = new JTextField();
		txtPesquisa.setBackground(new Color(245, 245, 245));
		txtPesquisa.setForeground(Color.GRAY);
		txtPesquisa.setBorder(null);
		TextPrompt tpUsuario = new TextPrompt("Pesquisar", txtPesquisa);
		tpUsuario.setForeground(new Color(128, 128, 128));
		txtPesquisa.setColumns(10);
		txtPesquisa.setBounds(0, 5, 178, 23);
		panelPesquisa.add(txtPesquisa);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBorder(null);
		btnPesquisar.setBackground(Color.LIGHT_GRAY);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPesquisar.setBounds(188, 2, 85, 28);
		panelPesquisa.add(btnPesquisar);

		JSeparator sprPesquisa = new JSeparator();
		sprPesquisa.setForeground(Color.GRAY);
		sprPesquisa.setBackground(Color.LIGHT_GRAY);
		sprPesquisa.setBounds(0, 28, 178, 2);
		panelPesquisa.add(sprPesquisa);

		JPanel panelMenuB = new JPanel();
		panelMenuB.setBounds(56, 0, 348, 52);
		panelMenuB.setBackground(new Color(245, 245, 245));
		panelUser.add(panelMenuB);
		panelMenuB.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.setBounds(0, 3, 280, 43);
		panelMenuB.add(menuBar);
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setUI(null);

		mnUser = new JMenu(perfil);
		mnUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mnUser.doClick();
				mnUser.setSelected(true);
				mnUser.setPopupMenuVisible(true);

			}
		});
		mnUser.setForeground(Color.DARK_GRAY);
		mnUser.setHorizontalAlignment(SwingConstants.CENTER);
		mnUser.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuBar.add(mnUser);

		JMenuItem mntmMeuPerfil = new JMenuItem("Meu Perfil");
		mnUser.add(mntmMeuPerfil);

		JSeparator separator = new JSeparator();
		mnUser.add(separator);

		JMenuItem mntmLogout = new JMenuItem("logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmLogin login = new FrmLogin();
				login.setVisible(true);
				dispose();
			}
		});
		mnUser.add(mntmLogout);

		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(10, 64, 384, 45);
		contentPane.add(panelLogo);
		panelLogo.setBackground(new Color(245, 245, 245));
		panelLogo.setLayout(null);

		JLabel lbl_Home = new JLabel("Gestor Musical");
		lbl_Home.setForeground(Color.GRAY);
		lbl_Home.setFont(new Font("Verdana", Font.BOLD, 18));
		lbl_Home.setBounds(127, 11, 155, 32);
		panelLogo.add(lbl_Home);

		JLabel lbl_IconeHome = new JLabel("");
		lbl_IconeHome.setIcon(new ImageIcon(Home.class.getResource("/br/senai/sp/img/icone_home.png")));
		lbl_IconeHome.setBounds(94, 12, 37, 32);
		panelLogo.add(lbl_IconeHome);
	}
}
