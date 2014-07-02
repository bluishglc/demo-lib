package net.sf.cxfdemo.server;

public class Bar {
	private Long id;
	private Double num;
	
	public Bar(){}

	public Bar(Long id, Double num) {
		super();
		this.id = id;
		this.num = num;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public String toString() {
		return "Bar [id=" + id + ", num=" + num + "]";
	}

}
