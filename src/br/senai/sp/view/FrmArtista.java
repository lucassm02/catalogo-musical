package br.senai.sp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument.Content;
import javax.swing.text.MaskFormatter;
import javax.swing.text.Position;

import br.senai.sp.dao.ArtistaDao;
import br.senai.sp.dao.UsuarioDao;
import br.senai.sp.model.Artista;
import br.senai.sp.model.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.server.UID;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Panel;
import javax.swing.JDesktopPane;
import java.awt.Button;
import javax.swing.event.MenuDragMouseListener;
import javax.swing.event.MenuDragMouseEvent;
import javax.swing.UIManager;
import javax.swing.event.MenuListener;import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.MenuEvent;
import javax.swing.border.MatteBorder;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

public class FrmArtista extends JFrame {

	private JPanel contentPane;
	private JPanel panelTabela;
	private JTable tblArtista;
	private JPanel panelBotoes;
	private JTextField txtNome;
	private JTextField txtCidade;
	private JScrollPane scrollPane;
	private DefaultTableModel tabModel;
	private JComboBox cbEstilo;
	private JLabel lblStatus;
	private JFormattedTextField txtData;
	private MaskFormatter mascara;

	private int indice;
	private int key;
	private int tratamentoTxt = 0;
	private JTable tbl_Detalhes;
	private Color Color;
	private JTextField txtPesquisa;
	private JMenu mnUser;
	private JSeparator sprPesquisa;
	private JLabel lblNomeDetalhes;
	private JLabel lblEstiloM; 
	private JLabel lblDataN; 
	private JLabel lblCidadeN;
	private JComboBox cbAlbunsDetalhes;
	
	private String perfil = FrmLogin.getNomeUser();
	

	/*
	 * Create the frame.
	 */

	public FrmArtista() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FrmArtista.class.getResource("/br/senai/sp/img/Janela_icone.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 405);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
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
		panelLogo.setBounds(10, 55, 524, 58);
		contentPane.add(panelLogo);
		panelLogo.setLayout(null);

		JLabel lblArtistas = new JLabel("Artistas");
		lblArtistas.setBounds(247, 15, 116, 30);
		lblArtistas.setForeground(SystemColor.controlDkShadow);
		lblArtistas.setFont(new Font("Tahoma", Font.BOLD, 23));
		panelLogo.add(lblArtistas);

		JLabel lbl_logo = new JLabel("");
		lbl_logo.setBounds(185, 0, 71, 58);
		lbl_logo.setIcon(new ImageIcon(FrmArtista.class.getResource("/br/senai/sp/img/icone_musico.png")));
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
		panelConteudo.setBounds(10, 114, 524, 261);
		contentPane.add(panelConteudo);
		panelConteudo.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBackground(new Color(245, 245, 245));
		tabbedPane.setBounds(10, 0, 504, 260);
		panelConteudo.add(tabbedPane);

		panelTabela = new JPanel();
		panelTabela.setBackground(new Color(245, 245, 245));
		tabbedPane.addTab("Artistas", null, panelTabela, null);
		panelTabela.setLayout(null);

		panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(245, 245, 245));
		panelBotoes.setLayout(null);
		panelBotoes.setBounds(0, 170, 160, 51);
		panelTabela.add(panelBotoes);

		
		montarTabela();
		
		JLabel IconAdicionar = new JLabel("");
		IconAdicionar.setToolTipText("Adicionar");
		IconAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblStatus.setText("Inserir");
				tabbedPane.setEnabledAt(2, true);
				tabbedPane.setSelectedIndex(2);
			}
		});
		IconAdicionar.setHorizontalAlignment(SwingConstants.CENTER);
		IconAdicionar.setIcon(new ImageIcon(FrmArtista.class.getResource("/br/senai/sp/img/icone_adicionarArtista.png.png")));
		IconAdicionar.setBounds(10, 7, 38, 38);
		panelBotoes.add(IconAdicionar);
		
		JLabel IconDeletar = new JLabel("");
		IconDeletar.setToolTipText("Remover");
		IconDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				indice = tblArtista.getSelectedRow();

				if (indice == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, selecione um artista para excluir.", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				} else {

					key = Integer.parseInt(tabModel.getValueAt(indice, 3).toString());

					ArtistaDao artistaDao = new ArtistaDao();

					int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir artista ?", "Excluir",
							JOptionPane.YES_NO_OPTION);

					if (resposta == 0) {

						artistaDao.excluir(key);

					}

					panelTabela.removeAll();
					tabbedPane.setSelectedIndex(0);
					montarTabela();

				}
			}
		});
		IconDeletar.setIcon(new ImageIcon(FrmArtista.class.getResource("/br/senai/sp/img/icone_deletarArtista.png.png")));
		IconDeletar.setHorizontalAlignment(SwingConstants.CENTER);
		IconDeletar.setBounds(58, 7, 38, 38);
		panelBotoes.add(IconDeletar);
		
		JLabel IconAtualizar = new JLabel("");
		IconAtualizar.setToolTipText("Atualizar");
		IconAtualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				indice = tblArtista.getSelectedRow();

				if (indice == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, selecione um artista para atualizar.", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				} else {

					key = Integer.parseInt(tabModel.getValueAt(indice, 3).toString());

					lblStatus.setText("Atualizar");

					limpar();

					preencherCampo();

					tabbedPane.setEnabledAt(2, true);
					tabbedPane.setSelectedIndex(2);

				}
			}
		});
		IconAtualizar.setIcon(new ImageIcon(FrmArtista.class.getResource("/br/senai/sp/img/icone_AtualizarArtista.png.png")));
		IconAtualizar.setHorizontalAlignment(SwingConstants.CENTER);
		IconAtualizar.setBounds(106, 7, 38, 38);
		panelBotoes.add(IconAtualizar);

		JPanel panelDetalhes = new JPanel();
		tabbedPane.addTab("Detalhes", null, panelDetalhes, null);
		panelDetalhes.setLayout(null);

		JPanel panelDadosArtista = new JPanel();
		panelDadosArtista.setBackground(new Color(245, 245, 245));
		panelDadosArtista.setLayout(null);
		panelDadosArtista.setBounds(0, 0, 489, 232);
		panelDetalhes.add(panelDadosArtista);

		lblNomeDetalhes = new JLabel("Nome musico");
		lblNomeDetalhes.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNomeDetalhes.setBounds(20, 11, 198, 34);
		panelDadosArtista.add(lblNomeDetalhes);

		JLabel lblEstiloDetalhes = new JLabel("Estilo musical:");
		lblEstiloDetalhes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEstiloDetalhes.setBounds(20, 46, 103, 14);
		panelDadosArtista.add(lblEstiloDetalhes);

		lblEstiloM = new JLabel("New label");
		lblEstiloM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstiloM.setBounds(119, 46, 72, 14);
		panelDadosArtista.add(lblEstiloM);

		JLabel lblDataDetalhes = new JLabel("Data nascimento:");
		lblDataDetalhes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDataDetalhes.setBounds(20, 66, 131, 14);
		panelDadosArtista.add(lblDataDetalhes);

		lblDataN = new JLabel("New label");
		lblDataN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataN.setBounds(143, 66, 72, 14);
		panelDadosArtista.add(lblDataN);

		lblCidadeN = new JLabel("New label");
		lblCidadeN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCidadeN.setBounds(113, 85, 72, 14);
		panelDadosArtista.add(lblCidadeN);

		JLabel lblCidadeDetalhes = new JLabel("Cidade natal:");
		lblCidadeDetalhes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCidadeDetalhes.setBounds(20, 85, 131, 14);
		panelDadosArtista.add(lblCidadeDetalhes);

		cbAlbunsDetalhes = new JComboBox();
		cbAlbunsDetalhes.setBounds(20, 122, 103, 26);
		panelDadosArtista.add(cbAlbunsDetalhes);

		JLabel lblAlbunsDetalhes = new JLabel("Albuns:");
		lblAlbunsDetalhes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAlbunsDetalhes.setBounds(25, 107, 72, 14);
		panelDadosArtista.add(lblAlbunsDetalhes);

		JScrollPane scrollPaneDt = new JScrollPane();
		scrollPaneDt.setBounds(228, 11, 251, 210);
		panelDadosArtista.add(scrollPaneDt);

		tbl_Detalhes = new JTable();
		tbl_Detalhes.setShowHorizontalLines(false);
		tbl_Detalhes.setEnabled(false);
		tbl_Detalhes.setBackground(new Color(255, 255, 255));
		tbl_Detalhes.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, { null, null }, { null, null }, { null, null }, { null, null }, },
				new String[] { "New column", "New column" }));
		scrollPaneDt.setViewportView(tbl_Detalhes);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(129, 200, -134, -46);
		panelDadosArtista.add(desktopPane);

		JPanel panelEditarSalvar = new JPanel();
		panelEditarSalvar.setBackground(new Color(245, 245, 245));
		tabbedPane.addTab("Salvar / Editar", null, panelEditarSalvar, null);
		tabbedPane.setEnabledAt(2, false);
		panelEditarSalvar.setLayout(null);

		JPanel panelDados = new JPanel();
		panelDados.setBackground(new Color(245, 245, 245));
		panelDados.setBorder(
				new TitledBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(105, 105, 105)), "Dados do artista", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		panelDados.setBounds(10, 11, 469, 132);
		panelEditarSalvar.add(panelDados);
		panelDados.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setForeground(Color.GRAY);
		txtNome.setColumns(10);
		txtNome.setBorder(null);
		txtNome.setBackground(new Color(245, 245, 245));
		txtNome.setBounds(10, 42, 274, 25);
		TextPrompt tpNome = new TextPrompt("Nome", txtNome);
		tpNome.setForeground(Color.DARK_GRAY);
		panelDados.add(txtNome);

		txtCidade = new JTextField();
		txtCidade.setForeground(Color.GRAY);
		txtCidade.setColumns(10);
		txtCidade.setBorder(null);
		txtCidade.setBackground(new Color(245, 245, 245));
		txtCidade.setBounds(10, 89, 274, 25);
		TextPrompt tpCidade = new TextPrompt("Cidade", txtCidade);
		tpCidade.setForeground(Color.DARK_GRAY);
		panelDados.add(txtCidade);

		String[] estilos = { "Rock", "Pop", "Eletronica", "Reggae" };

		cbEstilo = new JComboBox(estilos);
		cbEstilo.setForeground(Color.DARK_GRAY);
		cbEstilo.setBounds(295, 89, 99, 25);
		panelDados.add(cbEstilo);

		JLabel lblEstilo = new JLabel("Estilo:");
		lblEstilo.setForeground(Color.DARK_GRAY);
		lblEstilo.setBounds(296, 73, 46, 14);
		panelDados.add(lblEstilo);

		try {
			mascara = new MaskFormatter("##/##/##");
			mascara.setPlaceholderCharacter('_');

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		txtData = new JFormattedTextField(mascara);
		txtData.setForeground(Color.DARK_GRAY);
		txtData.setBorder(null);
		txtData.setBackground(new Color(245, 245, 245));
		txtData.setBounds(294, 50, 69, 17);
		panelDados.add(txtData);
		
		JSeparator sprNome = new JSeparator();
		sprNome.setForeground(Color.GRAY);
		sprNome.setBackground(Color.LIGHT_GRAY);
		sprNome.setBounds(10, 67, 274, 2);
		panelDados.add(sprNome);
		
		JSeparator sprCidade = new JSeparator();
		sprCidade.setForeground(Color.GRAY);
		sprCidade.setBackground(Color.LIGHT_GRAY);
		sprCidade.setBounds(10, 114, 274, 2);
		panelDados.add(sprCidade);
		
		JSeparator sprData = new JSeparator();
		sprData.setForeground(Color.GRAY);
		sprData.setBackground(Color.LIGHT_GRAY);
		sprData.setBounds(294, 67, 55, 2);
		panelDados.add(sprData);

		JPanel panelSalvar = new JPanel();
		panelSalvar.setBackground(new Color(245, 245, 245));
		panelSalvar.setBounds(10, 170, 466, 51);
		panelEditarSalvar.add(panelSalvar);
		panelSalvar.setLayout(null);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Artista artista = new Artista();

				artista.setNome(txtNome.getText());
				artista.setCidade(txtCidade.getText());
				artista.setNascimento(txtData.getText());
				artista.setEstilo(cbEstilo.getSelectedItem().toString());
				artista.setUsuario(perfil);
				artista.setId(key);

				ArtistaDao artistaDao = new ArtistaDao();

				if (txtNome.getText().equals("") || txtCidade.getText().equals("")
						|| txtData.getText().equals("__/__/__")) {

					JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Aviso",
							JOptionPane.WARNING_MESSAGE);

				} else {

					if (lblStatus.getText().equals("Inserir")) {

						artistaDao.gravar(artista);

					} else if (lblStatus.getText().equals("Atualizar")) {

						artistaDao.atualizar(artista);

					} else if (lblStatus.getText().equals("")) {

						artistaDao.gravar(artista);

					}

					limpar();

					montarTabela();

					tabbedPane.setEnabledAt(2, false);
					tabbedPane.setSelectedIndex(0);

				}
			}
		});
		btnSalvar.setBounds(272, 11, 87, 28);
		panelSalvar.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				limpar();
				tabbedPane.setEnabledAt(2, false);
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
		TextPrompt tpPesquisa = new TextPrompt("Pesquisar", txtPesquisa);
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

		ArrayList<Artista> artista = new ArrayList<>();
		ArtistaDao artistaDao = new ArtistaDao();
		artista = artistaDao.exibirArtista();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 499, 162);
		panelTabela.add(scrollPane);

		tabModel = new DefaultTableModel();
		tblArtista = new JTable(tabModel);
		tblArtista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				indice = tblArtista.getSelectedRow();

				key = Integer.parseInt(tabModel.getValueAt(indice, 3).toString());
				
				preencherDt();
			}
		});
		
		tblArtista.setSurrendersFocusOnKeystroke(true);
		tblArtista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblArtista.setShowHorizontalLines(false);
		tblArtista.setBackground(new Color(255, 255, 255));

		tabModel.addColumn("Nome");
		tabModel.addColumn("Cidade");
		tabModel.addColumn("Estilo");
		tabModel.addColumn("ID");

		tblArtista.getColumnModel().getColumn(3).setMinWidth(0);
		tblArtista.getColumnModel().getColumn(3).setPreferredWidth(0);
		tblArtista.getColumnModel().getColumn(3).setMaxWidth(0);

		for (Artista listaArtista : artista) {

			tabModel.addRow(new Object[] { listaArtista.getNome(), listaArtista.getCidade(), listaArtista.getEstilo(),
					listaArtista.getId() });

		}

		scrollPane.setViewportView(tblArtista);

	}

	public void preencherCampo() {

		Artista artista = new Artista();
		ArtistaDao artistaDao = new ArtistaDao();

		artista = artistaDao.getArtistaByKey(key);

		txtNome.setText(artista.getNome());
		txtCidade.setText(artista.getCidade());
		txtData.setText(artista.getNascimento());
		cbEstilo.setSelectedItem(artista.getEstilo());
		artista.setId(key);

	}
	
	public void preencherDt() {
		
		Artista artista = new Artista();
		ArtistaDao artistaDao = new ArtistaDao();

		artista = artistaDao.getArtistaByKey(key);

		lblNomeDetalhes.setText(artista.getNome());
		lblCidadeN.setText(artista.getCidade());
		lblDataN.setText(artista.getNascimento());
		lblEstiloM.setText(artista.getEstilo());
		
	}

	public void limpar() {

		Artista arttista = new Artista();

		txtNome.setText("");
		txtCidade.setText("");
		txtData.setText("");
		cbEstilo.setSelectedIndex(0);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
