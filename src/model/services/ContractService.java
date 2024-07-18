package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService ops;
	
	public ContractService() {
		
	}

	public ContractService(OnlinePaymentService onlinepaymentservice) {
		this.ops = onlinepaymentservice;
	} 
	
	public void processContract(Contract contract, Integer months) {
		Double valorParcela = contract.getTotalValue() / contract.getNumber();
		for (int i = 1; i <= months; i++ ) {
			LocalDate dueDate = contract.getDate().plusMonths(i);
			Double amount = valorParcela + ops.interest(valorParcela, i);
			amount += ops.paymentFee(amount);
			contract.getInstallment().add(new Installment(dueDate, amount));
		}
	}
	
	
}
