package net.sf.sharding.demo;

public interface OrderDao {

	public Order get(Long id);
	
	public void save(Order order);
	
}
