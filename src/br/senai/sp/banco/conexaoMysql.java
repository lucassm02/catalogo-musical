package br.senai.sp.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class conexaoMysql {
	
	private static Connection con = null;
	
	public static Connection getConexao() {
		
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/gestao_musical?useSSL=false", "root", "");
			System.out.println("Conex�o aberta com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	public  static void fecharconex�o(){
		
		try {
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Ocorreu um erro ao fechar o banco de dados.","Erro",JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
	
}