package net.sf.cxfdemo.server;

public class BarDAOImpl implements BarDAO{

	public Bar findBar() {
		Bar bar = new Bar(new Long(1L),new Double(1.5D));
		return bar;
	}

}
