package br.senai.sp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument.LeafElement;

import br.senai.sp.banco.conexaoMysql;
import br.senai.sp.model.Artista;

public class ArtistaDao {

	private Connection con = conexaoMysql.getConexao();

	public void gravar(Artista artista) {

		String sql = "insert into tbl_banda_cantor" + 
				" (nome,cidade_natal,nascimento,estilo,usuario,id)"
				+ " values (?,?,?,?,?,?)";
		
		int contLista = 0;
		int cont = 0;
		
		ArrayList<Artista> artist = new ArrayList<>();
		
		artist = exibirArtista();
		
		for ( Artista quant : artist) {
			
			contLista++;
				
			}
		
		
		if (contLista != 0) {
			
			artist.get(artist.size() - 1);
			
					
			cont = contLista + 1;
				
		}
		
		artista.setId(cont);

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			
			stm.setString(1, artista.getNome());
			stm.setString(2, artista.getCidade());
			stm.setString(3, artista.getNascimento());
			stm.setString(4, artista.getEstilo());
			stm.setString(5, artista.getUsuario());
			stm.setInt(6, artista.getId());

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

		String sql = "delete from tbl_banda_cantor" + " WHERE id = ?";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, key);

			if (stm.execute()) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao deletar o artista selecionado", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Artista deletado com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}

			conexaoMysql.fecharconexão();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void atualizar(Artista artista) {

		String sql = "update tbl_banda_cantor"
				+ " set nome = ?, cidade_natal = ?, nascimento = ?, estilo = ?"
				+ " WHERE id = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, artista.getNome());
			stm.setString(2, artista.getCidade());
			stm.setString(3, artista.getNascimento());
			stm.setString(4, artista.getEstilo());
			stm.setInt(5, artista.getId());
			

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


	public ArrayList<Artista> exibirArtista() {

		ArrayList<Artista> artista = new ArrayList<>();
		
		Artista artis = new Artista();
		
		String sql = "select * from tbl_banda_cantor order by nome desc";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				
				artis.setNome(rs.getString("nome"));
				artis.setCidade(rs.getString("cidade_natal"));
				artis.setNascimento(rs.getString("nascimento"));
				artis.setEstilo(rs.getString("estilo"));
				artis.setId(rs.getInt("id"));

				artista.add(artis);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return artista;
	}

	public Artista getArtistaByKey(int key) {
		Artista artista = new Artista();

		String sql = "select * from tbl_banda_cantor" + " WHERE id = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, key);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {

				artista.setNome(rs.getString("nome"));
				artista.setCidade(rs.getString("cidade_natal"));
				artista.setNascimento(rs.getString("nascimento"));
				artista.setEstilo(rs.getString("estilo"));
				artista.setId(rs.getInt("id"));
				

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return artista;
	}

}
