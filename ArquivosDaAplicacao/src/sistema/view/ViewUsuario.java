package sistema.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import sistema.dados.RepositorioDeUsuario;
import sistema.models.usuario.Admin;
import sistema.models.usuario.Atendente;
import sistema.models.usuario.Usuario;

public class ViewUsuario {
	private Scanner inputNumber;
	private Scanner inputNext;
	private Scanner inputNextLine;

	public ViewUsuario() {
		this.inputNumber = new Scanner(System.in);
		this.inputNext = new Scanner(System.in);
		this.inputNextLine = new Scanner(System.in);
	}

	public void listarUsuarios(List<Usuario> usuarios, int largura) {
		System.out.println("Usuários:\n");
		System.out.printf(
				"%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "s%-" + largura + "s\n", "TIPO",
				"CÓDIGO", "NOME", "LOGIN", "EXTRA");

		for (Usuario usuario : usuarios) {
			if (usuario instanceof Atendente) {
				Atendente atd = (Atendente) usuario;
				System.out.printf(
						"%-" + largura + "s%-" + largura + "d%-" + largura + "s%-" + largura + "s%-" + largura + "d\n",
						"ATENDENTE", atd.getCodigo(), atd.getNome(), atd.getLogin(), atd.getTotalVendas());
			} else if (usuario instanceof Admin) {
				Admin admin = (Admin) usuario;
				String dataFormatada = "Não houve login";

				if (admin.getUltimoLogin() != null) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					dataFormatada = dateFormat.format(admin.getUltimoLogin());
				}

				System.out.printf(
						"%-" + largura + "s%-" + largura + "d%-" + largura + "s%-" + largura + "s%-" + largura + "s\n",
						"ADMIN", admin.getCodigo(), admin.getNome(), admin.getLogin(), dataFormatada);
			}
		}
	}

	public String leituraLogin() {
		System.out.print("Digite o login: ");
		return inputNext.next();
	}

	public String leituraSenha() {
		System.out.print("Digite a senha: ");
		return inputNext.next();
	}

	public int lerCodigo() {
		System.out.println("Digite o codigo do usuários para haver a remoção: ");
		return inputNumber.nextInt();
	}

	public Usuario lerUsuario(RepositorioDeUsuario repositorioDeUsuario) {
		System.out.println("> CADASTRO DE USUÁRIO");
		System.out.println("Antes de iniciarmos, selecione o tipo de usuário que você deseja cadastrar:");
		System.out.println("1 --> Atendente");
		System.out.println("2 --> Admin");
		int tipo = inputNumber.nextInt();

		int codigo = repositorioDeUsuario.gerarCodigoDoUsuario();

		System.out.print("\nDigite o nome do usuário: ");
		String nome = inputNext.next();

		System.out.print("\nDigite o login: ");
		String login = inputNext.next();

		System.out.print("\nDigite a senha: ");
		String senha = inputNext.next();

		Usuario usuario = Usuario.getInstance(codigo, nome, login, senha);

		if (tipo == 1) {
			return Atendente.getInstance(codigo, nome, login, senha);
		} else if (tipo == 2) {
			return Admin.getInstance(codigo, nome, login, senha);
		}

		return null;
	}
}
