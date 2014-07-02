package net.sf.httpclientdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

/**
 * The Client will hit my blog on CSDN by HttpClient.
 */
public class Client implements Runnable{
	public static void main(String[] args) throws Exception, IOException {		
		multihit(10);
	}
	
	public static void multihit(int threads){
		for (int i = 0; i < threads; i++) {
			new Thread(new Client()).start();
		}		
	}
	

	public  void hit() throws Exception, IOException {

		// In multi-thread environment, we should set ThreadSafeClientConnManager for client.
		HttpClient httpclient = new DefaultHttpClient(new ThreadSafeClientConnManager());			
		HttpGet httpget = new HttpGet("http://blog.csdn.net/bluishglc");
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		
		// Print page content.
		// if (entity != null) {
		// InputStream instream = entity.getContent();
		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(instream,"UTF-8"));
		//
		// int l;
		// int cnt=0;
		// char[] tmp = new char[2048];
		// while ((l = br.read(tmp)) != -1) {
		//
		// System.out.println(cnt+"***************************************************************************");
		// System.out.println(tmp);
		// cnt=cnt+l;
		// }
		// }
		
		if (entity != null) {
			InputStream instream = entity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(instream, "UTF-8"));
			br.skip(24000); // Locates to "access number" element, print and monitor. 
			System.out.println(br.readLine());
		}

		
	}

	public void run() {
		while (true) {
			try {
				hit();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
}
