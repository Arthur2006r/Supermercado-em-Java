package sistema.view;

import java.util.Scanner;

public class ViewMenu {
	private static Scanner inputNumber;
	private static Scanner inputNext;
	private static Scanner inputNextLine;

	public ViewMenu() {
		this.inputNumber = new Scanner(System.in);
		this.inputNext = new Scanner(System.in);
		this.inputNextLine = new Scanner(System.in);
	}

	public int menuLogin() {
		// Menu de opção inicial - escolher entre atendente ou administrador
		System.out.println("-------------------------------------------------------");
		System.out.println("                      Menu de login");
		System.out.println("-------------------------------------------------------");
		System.out.println("Você deseja logar em que módulo, digite uma das opções:");
		System.out.println("1 --> Administrador");
		System.out.println("2 --> Atendente");
		System.out.println("3 --> Fechar programa");
		System.out.println("-------------------------------------------------------");
		return inputNumber.nextInt();
	}

	public int menuAdministrador() {
		System.out.print("\n* MODO ADMIN *\n");
		// Menu para o usuário escolher a opção
		System.out.println("-------------------------------------------------------");
		System.out.println("> Menu do Administrador");
		System.out.println("-------------------------------------------------------");
		System.out.println("Digite uma opção:");
		System.out.println("1  --> Inserir Produtos");
		System.out.println("2  --> Excluir Produto");
		System.out.println("3  --> Alterar dados de um produto");
		System.out.println("4  --> Exibir todos os produtos (em ordem de cadastro)");
		System.out.println("5  --> Exibir todos os produtos (em ordem alfabética)");
		System.out.println("6  --> Exibir todas as vendas realizadas");
		System.out.println("7  --> Exibir todas as vendas de um determinado dia");
		System.out.println("8  --> Exibir venda pelo código");
		System.out.println("9 --> Cadastrar Usuário");
		System.out.println("10 --> Remover um usuário");
		System.out.println("11 --> Exibir usuários cadastrados");
		System.out.println("12 --> Deslogar");
		System.out.println("-------------------------------------------------------");
		return inputNumber.nextInt();
	}

	public int menuAtendente(String nomeAtendente) {
		System.out.println("\n* MODO ATENDENTE *\n");

		// Menu para o usuário escolher a opção
		System.out.println("> Menu do Atendente");
		System.out.println("...\nATENDENTE: " + nomeAtendente + "\n...");
		System.out.println("Digite uma opção:");
		System.out.println("1 --> Abrir um novo carrinho");
		System.out.println("2 --> Deslogar");
		return inputNumber.nextInt();
	}

	public int menuCarrinho(String nomeAtendente) {
		System.out.println("\n> Menu carrinho");
		System.out.println("...\nATENDENTE: " + nomeAtendente + "\n...");
		System.out.println("Digite uma opção:");
		System.out.println("1 --> Inserir item no carrinho");
		System.out.println("2 --> Visualizar carrinho");
		System.out.println("3 --> Finalizar venda");
		System.out.println("4 --> Cancelar venda");
		return inputNumber.nextInt();
	}
}
