package br.senai.sp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.senai.sp.dao.ArtistaDao;
import br.senai.sp.dao.MusicaDao;
import br.senai.sp.dao.UsuarioDao;
import br.senai.sp.model.Artista;
import br.senai.sp.model.Musica;
import br.senai.sp.model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.MatteBorder;
import javax.swing.JMenuBar;
import javax.swing.ListSelectionModel;

public class FrmMusica extends JFrame {

	private JPanel contentPane;
	private JPanel panelTabela;
	private JTable tblMusica;
	private JPanel panelBotoes;
	private JTextField txtNome;
	private JScrollPane scrollPane;
	private DefaultTableModel tabModel;
	private JComboBox cbIdioma;
	private JComboBox cbEstilo;
	private JLabel lblStatus;
	private MaskFormatter mascara;
	private MaskFormatter mascaraDuracao;
	private JFormattedTextField txtData;
	private JFormattedTextField txtDuracao;

	private int indice;
	private int key;
	private int tratamentoTxt = 0;
	private JTextField txtAutor;
	private JTextField txtAlbum;
	private JTextField txtPesquisa;
	private JMenu mnUser;
	private JSeparator sprPesquisa;
	
	private String perfil = FrmLogin.getNomeUser();

	/*
	 * Create the frame.
	 */

	public FrmMusica() {
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(FrmMusica.class.getResource("/br/senai/sp/img/Janela_icone.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 405);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mnUser.setSelected(false);
				mnUser.setPopupMenuVisible(false);
			}
		});
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panelLogo = new JPanel();
		panelLogo.setBackground(new Color(245, 245, 245));
		panelLogo.setBounds(10, 56, 524, 58);
		contentPane.add(panelLogo);
		panelLogo.setLayout(null);

		JLabel lblMusicas = new JLabel("Musicas");
		lblMusicas.setForeground(SystemColor.controlDkShadow);
		lblMusicas.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblMusicas.setBounds(258, 13, 116, 30);
		panelLogo.add(lblMusicas);

		JLabel lbl_logo = new JLabel("");
		lbl_logo.setIcon(new ImageIcon(FrmMusica.class.getResource("/br/senai/sp/img/icone_music.png")));
		lbl_logo.setBounds(190, 0, 71, 58);
		panelLogo.add(lbl_logo);
		
		JLabel IconVoltar = new JLabel("");
		IconVoltar.setToolTipText("Voltar");
		IconVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Home inicio = new Home();
				inicio.setVisible(true);
				dispose();
			}
		});
		IconVoltar.setIcon(new ImageIcon(FrmArtista.class.getResource("/br/senai/sp/img/voltar.png")));
		IconVoltar.setBounds(10, 5, 26, 20);
		panelLogo.add(IconVoltar);

		JPanel panelConteudo = new JPanel();
		panelConteudo.setBackground(new Color(245, 245, 245));
		panelConteudo.setBounds(10, 115, 524, 261);
		contentPane.add(panelConteudo);
		panelConteudo.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(10, 0, 504, 260);
		panelConteudo.add(tabbedPane);

		panelTabela = new JPanel();
		panelTabela.setBackground(new Color(245, 245, 245));
		tabbedPane.addTab("Musicas", null, panelTabela, null);
		panelTabela.setLayout(null);

		panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(245, 245, 245));
		panelBotoes.setLayout(null);
		panelBotoes.setBounds(0, 173, 159, 51);
		panelTabela.add(panelBotoes);

		montarTabela();
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				limpar();

				lblStatus.setText("Inserir");
				tabbedPane.setEnabledAt(1, true);
				tabbedPane.setSelectedIndex(1);
			}
		});
		label.setIcon(new ImageIcon(FrmMusica.class.getResource("/br/senai/sp/img/icone_adicionarMusica.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 7, 38, 38);
		panelBotoes.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				indice = tblMusica.getSelectedRow();

				if (indice == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, selecione uma musica para excluir.", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				} else {

					key = Integer.parseInt(tabModel.getValueAt(indice, 4).toString());

					MusicaDao musicaDao = new MusicaDao();

					int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir musica ?", "Excluir",
							JOptionPane.YES_NO_OPTION);

					if (resposta == 0) {

						musicaDao.excluir(key);

					}

				}

				panelTabela.removeAll();
				tabbedPane.setSelectedIndex(0);
				montarTabela();
			}
		});
		label_1.setIcon(new ImageIcon(FrmMusica.class.getResource("/br/senai/sp/img/icone_deletarMusica.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(58, 7, 38, 38);
		panelBotoes.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				indice = tblMusica.getSelectedRow();

				if (indice == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, selecione uma musica para atualizar.", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				} else {

					key = Integer.parseInt(tabModel.getValueAt(indice, 4).toString());

					lblStatus.setText("Atualizar");

					limpar();

					preencherCampo();

					tabbedPane.setEnabledAt(1, true);
					tabbedPane.setSelectedIndex(1);
				}
			}
		});
		label_2.setIcon(new ImageIcon(FrmMusica.class.getResource("/br/senai/sp/img/icone_AtualizarMusica.png")));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(106, 7, 38, 38);
		panelBotoes.add(label_2);

		JPanel panelDetalhes = new JPanel();
		panelDetalhes.setBackground(new Color(245, 245, 245));
		tabbedPane.addTab("Detalhes", null, panelDetalhes, null);
		tabbedPane.setEnabledAt(1, false);
		panelDetalhes.setLayout(null);

		JPanel panelDados = new JPanel();
		panelDados.setForeground(new Color(128, 128, 128));
		panelDados.setBackground(new Color(245, 245, 245));
		panelDados.setBorder(new TitledBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(128, 128, 128)), "Dados da Musica", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		panelDados.setBounds(10, 11, 469, 154);
		panelDetalhes.add(panelDados);
		panelDados.setLayout(null);

		txtNome = new JTextField();
		txtNome.setForeground(Color.GRAY);
		txtNome.setColumns(10);
		txtNome.setBorder(null);
		txtNome.setBackground(new Color(245, 245, 245));
		txtNome.setBounds(10, 42, 143, 25);
		TextPrompt tpNome = new TextPrompt("Nome", txtNome);
		panelDados.add(txtNome);

		String[] estilos = { "Rock", "Pop", "Eletronica", "Reggae" };

		cbEstilo = new JComboBox(estilos);
		cbEstilo.setForeground(new Color(128, 128, 128));
		cbEstilo.setBounds(263, 95, 99, 25);
		panelDados.add(cbEstilo);

		JLabel lblEstilo = new JLabel("Estilo:");
		lblEstilo.setForeground(new Color(128, 128, 128));
		lblEstilo.setBounds(263, 78, 46, 14);
		panelDados.add(lblEstilo);

		try {
			mascara = new MaskFormatter("##/##/##");
			mascara.setPlaceholderCharacter('_');

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		txtAutor = new JTextField();
		txtAutor.setForeground(Color.GRAY);
		txtAutor.setColumns(10);
		txtAutor.setBorder(null);
		txtAutor.setBackground(new Color(245, 245, 245));
		txtAutor.setBounds(163, 42, 136, 25);
		TextPrompt tpAutor = new TextPrompt("Autor", txtAutor);
		panelDados.add(txtAutor);
		
		txtData = new JFormattedTextField(mascara);
		txtData.setForeground(new Color(128, 128, 128));
		txtData.setBorder(null);
		txtData.setBackground(new Color(245, 245, 245));
		txtData.setBounds(10, 96, 69, 22);
		panelDados.add(txtData);
		
		txtAlbum = new JTextField();
		txtAlbum.setForeground(Color.GRAY);
		txtAlbum.setColumns(10);
		txtAlbum.setBorder(null);
		txtAlbum.setBackground(new Color(245, 245, 245));
		txtAlbum.setBounds(309, 42, 136, 25);
		TextPrompt tpAlbum = new TextPrompt("Album", txtAlbum);
		panelDados.add(txtAlbum);

		String[] idiomas = { "Portugês", "Inglês", "Espanhol", "Japonês", "Chines", "Coreano", "Alemão", "Arabe" };

		cbIdioma = new JComboBox(idiomas);
		cbIdioma.setForeground(new Color(128, 128, 128));
		cbIdioma.setBounds(153, 95, 99, 25);
		panelDados.add(cbIdioma);

		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setForeground(new Color(128, 128, 128));
		lblIdioma.setBounds(153, 78, 46, 14);
		panelDados.add(lblIdioma);

		try {
			mascaraDuracao = new MaskFormatter("##:##");
			mascaraDuracao.setPlaceholderCharacter('_');

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblDuracao = new JLabel("Dura\u00E7\u00E3o:");
		lblDuracao.setForeground(new Color(128, 128, 128));
		lblDuracao.setBounds(80, 78, 53, 14);
		panelDados.add(lblDuracao);

		
		txtDuracao = new JFormattedTextField(mascaraDuracao);
		txtDuracao.setForeground(new Color(128, 128, 128));
		txtDuracao.setBorder(null);
		txtDuracao.setBackground(new Color(245, 245, 245));
		txtDuracao.setBounds(89, 96, 47, 22);
		panelDados.add(txtDuracao);
		
		JSeparator sprNome = new JSeparator();
		sprNome.setForeground(Color.GRAY);
		sprNome.setBackground(Color.LIGHT_GRAY);
		sprNome.setBounds(10, 67, 120, 2);
		panelDados.add(sprNome);
		
		JSeparator sprAutor = new JSeparator();
		sprAutor.setForeground(Color.GRAY);
		sprAutor.setBackground(Color.LIGHT_GRAY);
		sprAutor.setBounds(163, 67, 136, 2);
		panelDados.add(sprAutor);
		
		JSeparator sprAlbum = new JSeparator();
		sprAlbum.setForeground(Color.GRAY);
		sprAlbum.setBackground(Color.LIGHT_GRAY);
		sprAlbum.setBounds(309, 67, 136, 2);
		panelDados.add(sprAlbum);
		
		JSeparator sprData = new JSeparator();
		sprData.setForeground(Color.GRAY);
		sprData.setBackground(Color.LIGHT_GRAY);
		sprData.setBounds(10, 118, 49, 2);
		panelDados.add(sprData);
		
		JSeparator sprDuracao = new JSeparator();
		sprDuracao.setForeground(Color.GRAY);
		sprDuracao.setBackground(Color.LIGHT_GRAY);
		sprDuracao.setBounds(88, 118, 35, 2);
		panelDados.add(sprDuracao);

		JPanel panelSalvar = new JPanel();
		panelSalvar.setBackground(new Color(245, 245, 245));
		panelSalvar.setBounds(10, 170, 466, 51);
		panelDetalhes.add(panelSalvar);
		panelSalvar.setLayout(null);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(Color.LIGHT_GRAY);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Musica musica = new Musica();

				musica.setNome(txtNome.getText());
				musica.setAutor(txtAutor.getText());
				musica.setAlbum(txtAlbum.getText());
				musica.setAno(txtData.getText());
				musica.setDuracao(txtDuracao.getText());
				musica.setIdioma(cbIdioma.getSelectedItem().toString());
				musica.setEstilo(cbEstilo.getSelectedItem().toString());
				musica.setUsuario(perfil);
				musica.setId(key);

				MusicaDao musicaDao = new MusicaDao();

				if (txtNome.getText().equals("") || txtAutor.getText().equals("") || txtAlbum.getText().equals("")
						|| txtData.getText().equals("__/__/__") || txtDuracao.getText().equals("__:__")) {

					JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Aviso",
							JOptionPane.WARNING_MESSAGE);

				} else {

					if (lblStatus.getText().equals("Inserir")) {

						musicaDao.gravar(musica);

					} else if (lblStatus.getText().equals("Atualizar")) {

						musicaDao.atualizar(musica);

					} else if (lblStatus.getText().equals("")) {

						musicaDao.gravar(musica);

					}

					limpar();

					montarTabela();

					tabbedPane.setEnabledAt(1, false);
					tabbedPane.setSelectedIndex(0);

				}
			}
		});
		btnSalvar.setBounds(272, 11, 87, 28);
		panelSalvar.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				limpar();
				tabbedPane.setEnabledAt(1, false);
				tabbedPane.setSelectedIndex(0);

			}
		});
		btnCancelar.setBounds(369, 11, 87, 28);
		panelSalvar.add(btnCancelar);

		lblStatus = new JLabel("");
		lblStatus.setForeground(new Color(105, 105, 105));
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStatus.setBounds(10, 11, 76, 28);
		panelSalvar.add(lblStatus);
		
		JPanel panelUser = new JPanel();
		panelUser.setLayout(null);
		panelUser.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(211, 211, 211)));
		panelUser.setBackground(new Color(245, 245, 245));
		panelUser.setBounds(0, 0, 544, 53);
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
		panelPesquisa.setLayout(null);
		panelPesquisa.setBackground(new Color(245, 245, 245));
		panelPesquisa.setBounds(120, 10, 414, 35);
		panelUser.add(panelPesquisa);
		
		txtPesquisa = new JTextField();
		txtPesquisa.setBackground(new Color(245, 245, 245));
		txtPesquisa.setForeground(Color.GRAY);
		txtPesquisa.setBorder(null);
		TextPrompt tpUsuario = new TextPrompt("Pesquisar", txtPesquisa);
		txtPesquisa.setColumns(10);
		txtPesquisa.setBounds(0, 5, 309, 23);
		panelPesquisa.add(txtPesquisa);
		
		JButton btnPesquisa = new JButton("Pesquisar");
		btnPesquisa.setBorder(null);
		btnPesquisa.setBackground(Color.LIGHT_GRAY);
		btnPesquisa.setBounds(319, 2, 85, 28);
		panelPesquisa.add(btnPesquisa);
		
		JSeparator sprMenu;
		sprPesquisa = new JSeparator();
		sprPesquisa.setForeground(Color.GRAY);
		sprPesquisa.setBackground(Color.LIGHT_GRAY);
		sprPesquisa.setBounds(0, 28, 309, 2);
		panelPesquisa.add(sprPesquisa);
		
		JPanel panelMenuB = new JPanel();
		panelMenuB.setLayout(null);
		panelMenuB.setBackground(new Color(245, 245, 245));
		panelMenuB.setBounds(56, 0, 348, 52);
		panelUser.add(panelMenuB);
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

		JMenuItem mntmLogout = new JMenuItem("logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmLogin login = new FrmLogin();
				login.setVisible(true);
				dispose();
			}
		});
		
		JSeparator sprMen = new JSeparator();
		mnUser.add(sprMen);
		mnUser.add(mntmLogout);
	}

	public void montarTabela() {

		panelTabela.removeAll();
		panelTabela.add(panelBotoes);

		ArrayList<Musica> music = new ArrayList<>();
		MusicaDao musicaDao = new MusicaDao();
		music = musicaDao.exibirMusica();

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 499, 162);
		panelTabela.add(scrollPane);

		tabModel = new DefaultTableModel();
		tblMusica = new JTable(tabModel);
		tblMusica.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblMusica.setShowHorizontalLines(false);
		tblMusica.setBackground((new Color(255, 255, 255)));

		tabModel.addColumn("Nome");
		tabModel.addColumn("Autor");
		tabModel.addColumn("Album");
		tabModel.addColumn("Estilo");
		tabModel.addColumn("ID");

		tblMusica.getColumnModel().getColumn(4).setMinWidth(0);
		tblMusica.getColumnModel().getColumn(4).setPreferredWidth(0);
		tblMusica.getColumnModel().getColumn(4).setMaxWidth(0);

		for (Musica listaMusica : music) {

			tabModel.addRow(new Object[] { listaMusica.getNome(), listaMusica.getAutor(), listaMusica.getAlbum(),
					listaMusica.getEstilo(), listaMusica.getId() });

		}

		scrollPane.setViewportView(tblMusica);

	}

	public void preencherCampo() {

		Musica musica = new Musica();
		MusicaDao musicaDao = new MusicaDao();

		musica = musicaDao.getMusicaByKey(key);

		txtNome.setText(musica.getNome());
		txtAutor.setText(musica.getAutor());
		txtAlbum.setText(musica.getAlbum());
		txtData.setText(musica.getAno());
		txtDuracao.setText(musica.getDuracao());
		cbIdioma.setSelectedItem(musica.getIdioma());
		cbEstilo.setSelectedItem(musica.getEstilo());
		musica.setId(key);

	}

	public void limpar() {

		txtNome.setText("");
		txtAutor.setText("");
		txtAlbum.setText("");
		txtData.setText("");
		txtDuracao.setText("");
		cbIdioma.setSelectedItem(0);
		cbEstilo.setSelectedItem(0);

	}
}
