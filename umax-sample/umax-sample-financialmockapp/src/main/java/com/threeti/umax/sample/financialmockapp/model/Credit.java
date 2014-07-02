package com.threeti.umax.sample.financialmockapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "credit")
@XmlRootElement
public class Credit implements Serializable {

	private static final long serialVersionUID = 7225513965621655467L;
	
	private Long id;
	private Customer customer;
	private Double creditLine;
	private Double debt;
	private Integer version;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

    public void setId(Long id) {
		this.id = id;
	}


    @ManyToOne
    @JoinColumn(name="customer_id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name="credit_line")
	public Double getCreditLine() {
		return creditLine;
	}

	public void setCreditLine(Double creditLine) {
		this.creditLine = creditLine;
	}

	@Column
	public Double getDebt() {
		return debt;
	}

	public void setDebt(Double debt) {
		this.debt = debt;
	}
	
    @Version
    public Integer getVersion() {
        return version;
    }

	public void setVersion(Integer version) {
		this.version = version;
	}

}
