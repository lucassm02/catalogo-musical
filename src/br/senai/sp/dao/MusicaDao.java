package br.senai.sp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sp.banco.conexaoMysql;
import br.senai.sp.model.Artista;
import br.senai.sp.model.Musica;

public class MusicaDao {
	
	private Connection con = conexaoMysql.getConexao();

	public void gravar(Musica musica) {

		String sql = "insert into tbl_musica" + 
				" (titulo,cantor,album,data,idioma,duracao,estilo,usuario,id)"
				+ " values (?,?,?,?,?,?,?,?,?)";
		
		int contLista = 0;
		int cont = 0;
		
		ArrayList<Musica> music = new ArrayList<>();
		
		music = exibirMusica();
		
		for ( Musica quant : music) {
			
			contLista++;
				
			}
		
		
		if (contLista != 0) {
			
			music.get(music.size() -1);
			
			for ( Musica quant : music) {
					
			cont = quant.getId() + 1;
				
			}
		}
		
		musica.setId(cont);

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			
			stm.setString(1, musica.getNome());
			stm.setString(2, musica.getAutor());
			stm.setString(3, musica.getAlbum());
			stm.setString(4, musica.getAno());
			stm.setString(5, musica.getIdioma());
			stm.setString(6, musica.getDuracao());
			stm.setString(7, musica.getEstilo());
			stm.setString(8, musica.getUsuario());
			stm.setInt(9, musica.getId());;

			if (stm.execute()) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gravar os dados no banco de dados", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}

			conexaoMysql.fecharconexão();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public void excluir(int key) {

		String sql = "delete from tbl_musica" + " WHERE id = ?";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, key);

			if (stm.execute()) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao deletar a musica selecionada", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Musica deletado com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}

			conexaoMysql.fecharconexão();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void atualizar(Musica musica) {

		String sql = "update tbl_musica"
				+ " set titulo = ?, cantor = ?, album = ?, data = ?, idioma = ?, duracao = ?, estilo = ?"
				+ " WHERE id = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, musica.getNome());
			stm.setString(2, musica.getAutor());
			stm.setString(3, musica.getAlbum());
			stm.setString(4, musica.getAno());
			stm.setString(5, musica.getIdioma());
			stm.setString(6, musica.getDuracao());
			stm.setString(7, musica.getEstilo());
			stm.setInt(8, musica.getId());
			

			if (stm.execute()) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar os dados no banco de dados", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}

			conexaoMysql.fecharconexão();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public ArrayList<Musica> exibirMusica() {

		ArrayList<Musica> music = new ArrayList<>();

		String sql = "select * from tbl_musica order by titulo desc";

		try {
			PreparedStatement stm = con.prepareStatement(sql);

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Musica musica = new Musica();

				musica.setNome(rs.getString("titulo"));
				musica.setAutor(rs.getString("cantor"));
				musica.setAlbum(rs.getString("album"));
				musica.setAno(rs.getString("data"));
				musica.setIdioma(rs.getString("idioma"));
				musica.setDuracao(rs.getString("duracao"));
				musica.setEstilo(rs.getString("estilo"));
				musica.setId(rs.getInt("id"));

				music.add(musica);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return music;
	}

	public Musica getMusicaByKey(int key) {
		Musica musica = new Musica();

		String sql = "select * from tbl_musica" + " WHERE id = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, key);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {

				musica.setNome(rs.getString("titulo"));
				musica.setAutor(rs.getString("cantor"));
				musica.setAlbum(rs.getString("album"));
				musica.setAno(rs.getString("data"));
				musica.setIdioma(rs.getString("idioma"));
				musica.setDuracao(rs.getString("duracao"));
				musica.setEstilo(rs.getString("estilo"));
				musica.setId(rs.getInt("id"));
				

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return musica;
	}

}

