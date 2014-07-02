package net.sf.sharding.demo;

import java.io.Serializable;

public class Order implements Serializable{

	private static final long serialVersionUID = 324584497558495996L;

	private Long id;
	
	private String name;
	
	public Order(){}
	
	public Order(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
