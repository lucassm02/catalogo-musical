package br.senai.sp.app;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import br.senai.sp.view.FrmAlterarSenha;
import br.senai.sp.view.FrmCadastro;
import br.senai.sp.view.FrmLogin;
import br.senai.sp.view.FrmRecuperarSeConf;
import br.senai.sp.view.FrmRecuperarSenha;
import br.senai.sp.view.Home;

public class Aplicativo {

	public static void main(String[] args) {
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
		    // handle exception
		} catch (ClassNotFoundException e) {
		    // handle exception
		} catch (InstantiationException e) {
		    // handle exception
		} catch (IllegalAccessException e) {
		    // handle exception
		}
		
		
		/*Home home = new Home();
		home.setVisible(true);
		*/
		
		FrmLogin login = new FrmLogin();
		login.setVisible(true);
		
		/*
		FrmAlterarSenha alterar = new FrmAlterarSenha();
		alterar.setVisible(true);
		*/
		
		/*
		FrmCadastro cadastro = new FrmCadastro();
		cadastro.setVisible(true);
		*/
		
		/*
		FrmRecuperarSenha recuperar = new FrmRecuperarSenha();
		recuperar.setVisible(true);
		*/
		
	}

}
