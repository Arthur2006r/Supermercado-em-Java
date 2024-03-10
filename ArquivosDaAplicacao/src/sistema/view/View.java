package sistema.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class View {
	private Scanner inputNumber;

	public View() {
		this.inputNumber = new Scanner(System.in);
	}

	public void print(String mensagem) {
		System.out.println(mensagem);
	}

	// Lê a largura que o usuário deseja para sua tabela
	public int lerLarguraDasColunas() {
		System.out.print("\nDigite a largura das colunas da tabela (maior que 10): ");
		int largura = inputNumber.nextInt();
		while (largura < 10) {
			System.out.print("Digite um valor maior ou igual a zero!: ");
			largura = inputNumber.nextInt();
		}

		return largura;

	}

	public Date lerData() {
		System.out.println("Atenção! Não digite uma data inválida.");
		System.out.println("Lendo a data...");
		System.out.println("Formato: dd/MM/yyyy");

		System.out.print("Digite o dia: ");
		int dia = inputNumber.nextInt();

		System.out.print("Digite o mês: ");
		int mes = inputNumber.nextInt();

		System.out.print("Digite o ano: ");
		int ano = inputNumber.nextInt();

		if (validarData(dia, mes, ano)) {
			String dataString = dia + "/" + mes + "/" + ano;

			DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

			return formatoData.parse(dataString, new java.text.ParsePosition(0));
		} else {
			return null;
		}

	}

	public boolean validarData(int dia, int mes, int ano) {
		if (ano >= 0 && mes >= 1 && mes <= 12 && dia >= 1) {
			// o vetor de cada mês com sua respectiva quantidade de dias
			int[] diasPorMes = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

			if (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0)) {
				diasPorMes[1] = 29;
			}

			if (dia <= diasPorMes[mes - 1]) {
				return true;
			}
		}

		return false;
	}
}
