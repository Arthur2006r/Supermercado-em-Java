package sistema.models.usuario;

public class Atendente extends Usuario {
	private int totalVendas;

	// CONSTRUTOR DA CLASSE FILHA
	private Atendente(int codigo, String nome, String login, String senha) {
		super(codigo, nome, login, senha);
		this.totalVendas = 0;
	}

	// CONSTRUTOR DE CÓPIA
	public Atendente(Atendente atd) {
		super(atd);
		this.totalVendas = atd.totalVendas;
	}

	// MÉTODO FÁBRICA
	public static Atendente getInstance(int codigo, String nome, String login, String senha) {
		if (codigo > 0 && nome != null && login != null && senha != null) {
			return new Atendente(codigo, nome, login, senha);
		}

		return null;
	}

	// GETTER
	public int getTotalVendas() {
		return totalVendas;
	}

	public void adicionarVenda() {
		this.totalVendas++;
	}
}
