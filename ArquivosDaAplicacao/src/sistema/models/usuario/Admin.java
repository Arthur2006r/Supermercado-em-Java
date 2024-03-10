package sistema.models.usuario;

import java.util.Date;

public class Admin extends Usuario {
	public Date ultimoLogin;

	// CONSTRUTOR DA CLASSE FILHA
	private Admin(int codigo, String nome, String login, String senha) {
		super(codigo, nome, login, senha);
	}

	// CONSTRUTOR DE CÓPIA
	public Admin(Admin admin) {
		super(admin);
		this.ultimoLogin = admin.ultimoLogin;
	}

	// MÉTODO FÁBRICA
	public static Admin getInstance(int codigo, String nome, String login, String senha) {
		if (codigo > 0 && nome != null && login != null && senha != null) {
			return new Admin(codigo, nome, login, senha);
		}

		return null;
	}

	public Date getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(Date ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	public void logar() {
		Date dataAtual = new Date();
		setUltimoLogin(dataAtual);
	}
}
