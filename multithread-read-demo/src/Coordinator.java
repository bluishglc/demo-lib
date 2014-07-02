
/**
 * The Class Coordinator.
 * @author laurence.geng
 */
public class Coordinator {
	
	public static final String ODD = "ODD";
	public static final String EVEN = "EVEN";
	
	private String indicator;
	
	private boolean closed;
	
	public Coordinator(){
		this(ODD);
	}
	
	public Coordinator(String initIndicator) {
		super();
		this.closed = false;
		this.indicator = initIndicator;
	}
	
	public synchronized void colse(){
		closed = true;
		this.notify();
	}
	
	public synchronized boolean isClosed(){
		return closed;
	}
	
	public synchronized String getIndicator() {
		return indicator;
	}

	public synchronized void shiftIndicator(){
		if(ODD.equals(indicator)){
			indicator = EVEN;
		}else{
			indicator = ODD;
		}
		this.notify();
	}
	
	
}
