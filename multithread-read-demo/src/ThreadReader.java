import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The Class MultiThreadReader.
 * 
 * @author laurence.geng
 */
public class ThreadReader extends Thread {

	private String indicator;

	private BufferedReader bufferedReader;

	private Coordinator coordinator;

	public ThreadReader(String indicator, BufferedReader bufferedReader,
			Coordinator coordinator) {
		super();
		this.indicator = indicator;
		this.bufferedReader = bufferedReader;
		this.coordinator = coordinator;
	}

	@Override
	public void run() {
		try {
			String threadName = Thread.currentThread().getName();
			synchronized (coordinator) {
				while (!coordinator.isClosed()) {
					if (indicator.equals(coordinator.getIndicator())) {
						String line = bufferedReader.readLine();
						if (line != null) {
							System.out.println(threadName + ":" + line);
							coordinator.shiftIndicator();
						} else {
							coordinator.colse();
						}
					} else {
						coordinator.wait();
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(ThreadReader.class
						.getResourceAsStream("inputfile.txt")));
		Coordinator coordinator = new Coordinator();
		ThreadReader reader1 = new ThreadReader(Coordinator.ODD,
				bufferedReader, coordinator);
		ThreadReader reader2 = new ThreadReader(Coordinator.EVEN,
				bufferedReader, coordinator);
		System.out.println("Started.");
		try {
			reader1.start();
			reader2.start();
			reader1.join();
			reader2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished.");
	}

}
