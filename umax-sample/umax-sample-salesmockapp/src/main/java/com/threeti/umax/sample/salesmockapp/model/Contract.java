package com.threeti.umax.sample.salesmockapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "contract")
@XmlRootElement
public class Contract implements Serializable {

	private static final long serialVersionUID = 7225513965621655467L;
	
	public static final String STATE_NEW = "新建";
	public static final String STATE_EXAMINING = "审核中";
	public static final String STATE_REJECTED = "审核驳回";
	public static final String STATE_AVAILABLE = "审核通过";
	
	private Long id;
	private Customer firstParty;
	private String secondParty;
	private Date date;
	private String productName;
	private Double price;
	private Integer quantity;
	private Double amount;
	private String state;
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
    @JoinColumn(name="first_party_id")
	public Customer getFirstParty() {
		return firstParty;
	}

	public void setFirstParty(Customer firstParty) {
		this.firstParty = firstParty;
	}

    @Column(name = "second_party", nullable = false, length = 255)
	public String getSecondParty() {
		return secondParty;
	}

	public void setSecondParty(String secondParty) {
		this.secondParty = secondParty;
	}

	@Column
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "product_name", nullable = false, length = 255)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
    @Version
    public Integer getVersion() {
        return version;
    }

	public void setVersion(Integer version) {
		this.version = version;
	}

}
