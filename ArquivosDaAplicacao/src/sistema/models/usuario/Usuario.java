package sistema.models.usuario;

public abstract class Usuario {
	protected int codigo;
	protected String nome;
	protected String login;
	protected String senha;

	// CONSTRUTOR DA SUPER CLASSE (CLASSE MÃE)
	protected Usuario(int codigo, String nome, String login, String senha) {
		this.codigo = codigo;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public Usuario(Usuario usuario) {
		this.codigo = usuario.codigo;
		this.nome = usuario.nome;
		this.login = usuario.login;
		this.senha = usuario.senha;
	}

	// MÉTODO FÁBRICA
	public static Usuario getInstance(int codigo, String nome, String login, String senha) {
		if (codigo > 0 && nome != null && login != null && senha != null) {
			// Cria uma instância de UsuarioConcreto e copia os dados
			return new UsuarioConcreto(codigo, nome, login, senha);
		}

		return null;
	}

	// GETTERS E SETTERS
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getCodigo() {
		return codigo;
	}
}
