package br.com.flaviodev.study.websocket.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OutputOrder extends Order {

	private String dateTime;

	public OutputOrder() {
	}

	public OutputOrder(Order order, LocalDateTime dateTime) {
		setNumber(order.getNumber());
		setSalor(order.getSalor());
		setCostumer(order.getCostumer());
		setTotalAmount(order.getTotalAmount());
		setStatus(order.getStatus());
		this.dateTime = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm a").format(dateTime);
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}

	
}
