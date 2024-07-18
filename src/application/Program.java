package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import model.entities.Contract;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		ContractService service = new ContractService(new PaypalService());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Entre com os dados do contrato: ");
		System.out.print("Numero: ");
		Integer numero = sc.nextInt();
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.next(), dtf);
		System.out.println("Valor do contrato: ");
		Double valorContrato = sc.nextDouble();
		System.out.print("Entre com o numero de parcelas: ");
		Integer n = sc.nextInt();
		Contract contract = new Contract(n, date, valorContrato);
		service.processContract(contract, n);
		System.out.println("PARCELAS:");
		System.out.println(contract);
	}

}
