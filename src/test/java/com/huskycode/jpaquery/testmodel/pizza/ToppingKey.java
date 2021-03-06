package com.huskycode.jpaquery.testmodel.pizza;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ToppingKey implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	@Column private long orderId;
	@Column private int pizzaSequenceNumber;
	@Column private int toppingSequenceNumber;
}
