package net.sf.cxfdemo.server;

import javax.jws.WebService;

@WebService(targetNamespace="demo")
public interface DemoService {
	
	public Bar execute(Foo foo);
	
}
