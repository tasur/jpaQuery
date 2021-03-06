package com.huskycode.jpaquery.testmodel.pizza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Model taken from the design here
 * 
 * http://www.databaseanswers.org/data_models/pizza_deliveries/index.htm
 * 
 * @author Varokas Panusuwan
 *
 */
@Entity
@IdClass(ToppingKey.class)
public class Topping {
	@Id @Column private long orderId;
	@Column private int pizzaSequenceNumber;
	@Column private int toppingSequenceNumber;
	
	//private int toppingCode;
	
	private String toppingName;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getPizzaSequenceNumber() {
		return pizzaSequenceNumber;
	}

	public void setPizzaSequenceNumber(int pizzaSequenceNumber) {
		this.pizzaSequenceNumber = pizzaSequenceNumber;
	}

	public int getToppingSequenceNumber() {
		return toppingSequenceNumber;
	}

	public void setToppingSequenceNumber(int toppingSequenceNumber) {
		this.toppingSequenceNumber = toppingSequenceNumber;
	}

	public String getToppingName() {
		return toppingName;
	}

	public void setToppingName(String toppingName) {
		this.toppingName = toppingName;
	}
	
	
}
