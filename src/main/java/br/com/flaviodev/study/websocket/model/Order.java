package br.com.flaviodev.study.websocket.model;

import java.math.BigDecimal;

public class Order {

	private String number;
	private String salor;
	private String costumer;
	private BigDecimal totalAmount;
	private String status = "SENT";

	public Order() {
	}

	public Order(String number, String salor, String costumer, BigDecimal totalAmount, String status) {
		super();
		this.number = number;
		this.salor = salor;
		this.costumer = costumer;
		this.totalAmount = totalAmount;
		this.status = status;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSalor() {
		return salor;
	}

	public void setSalor(String salor) {
		this.salor = salor;
	}

	public String getCostumer() {
		return costumer;
	}

	public void setCostumer(String costumer) {
		this.costumer = costumer;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [number=");
		builder.append(number);
		builder.append(", salor=");
		builder.append(salor);
		builder.append(", costumer=");
		builder.append(costumer);
		builder.append(", totalAmount=");
		builder.append(totalAmount);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}


	
}