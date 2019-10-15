package br.senai.sp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sp.banco.conexaoMysql;
import br.senai.sp.model.Usuario;

public class UsuarioDao {
	
	private Connection con = conexaoMysql.getConexao();

	public void gravar(Usuario usuario) {
		String sql;
		sql = "insert into tbl_user" 
		+ "(usuario, senha, avatar, questao, resposta)" 
		+ "values (?, ?, ?, ?, ?);";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getSenha());
			stm.setString(3, usuario.getAvatar());
			stm.setInt(4, usuario.getQuestao());
			stm.setString(5, usuario.getResposta().toUpperCase());
			
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um problema ao salvar.\nEntre em contato com o administrador do sistema!", "Erro.",
						JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Registro efetuado com Sucesso.", "Sucesso!",
						JOptionPane.INFORMATION_MESSAGE);
			}
			conexaoMysql.fecharconexão();
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}
	
	public void alterarSenha(Usuario usuario) {
		String sql;
		sql = "update tbl_user"
		+ " set senha = ? WHERE usuario = ?";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, usuario.getSenha());
			stm.setString(2, usuario.getNome());
			
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um problema ao alterar a senha.\nEntre em contato com o administrador do sistema!", "Erro.",
						JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Senha alterada com Sucesso.", "Sucesso!",
						JOptionPane.INFORMATION_MESSAGE);
			}
			conexaoMysql.fecharconexão();
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}
	
	public ArrayList<Usuario> verificarUsuario() {
		ArrayList<Usuario> user = new ArrayList<>();
			String sql;
			sql = "select * from tbl_user";
			
			try {
				PreparedStatement stm = con.prepareStatement(sql);
				ResultSet rs = stm.executeQuery();
				
				while(rs.next()) {
					Usuario usuario = new Usuario();
					
					usuario.setNome(rs.getString("usuario"));
					
					user.add(usuario);
				}
				conexaoMysql.fecharconexão();
				
			} catch (SQLException error) {
				error.printStackTrace();
			}
		return user;
	}
	
	public Usuario getUserByNome(String nome) {
		Usuario pass = new Usuario();
		
		String sql = "select * from tbl_user WHERE usuario = ?";
		
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, nome);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				pass.setNome(rs.getString("usuario"));
				pass.setSenha(rs.getString("senha"));
				pass.setAvatar(rs.getString("avatar"));
				pass.setQuestao(rs.getInt("questao"));
				pass.setResposta(rs.getString("resposta"));
			}
			conexaoMysql.fecharconexão();
			
		} catch (SQLException error) {
			error.printStackTrace();
		}
		return pass;
	}

	
}