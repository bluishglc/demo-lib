package net.sf.cxfdemo.server;

import javax.jws.WebService;

@WebService(targetNamespace="demo")
public class DemoServiceImpl implements DemoService{
	
	private BarDAO barDAO;

	public Bar execute(Foo foo) {
		
		System.out.println("foo is received! It's "+ foo.toString());
		
		Bar bar = barDAO.findBar();
		
		System.out.println("bar is created! It's "+ bar.toString());
		
		return bar;
	}

	public void setBarDAO(BarDAO barDAO) {
		this.barDAO = barDAO;
	}
	
}
