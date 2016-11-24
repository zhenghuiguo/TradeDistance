package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpUrlConnectionUtil {
	public static void sendHttpRequest(final String addres,final HttpCallbackListener listener){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpURLConnection  con = null;
					try {
						URL url = new URL(addres);
						con = (HttpURLConnection)url.openConnection();
						con.setRequestMethod("GET");
						con.setConnectTimeout(8000);
						con.setReadTimeout(8000);
						con.setDoInput(true);
						con.setDoOutput(true);
						//con.setRequestProperty(field, newValue)
						InputStream in =(InputStream)con.getInputStream();
						BufferedReader bfr = new BufferedReader(new InputStreamReader(in));
						StringBuilder response = new StringBuilder();
						String line = null;
						while((line=bfr.readLine())!=null ){
							response.append(line);
						}
						if(listener!=null){
							con.getResponseCode();
							con.getContent();
							con.getContentLength();
							con.getContentEncoding();
							con.getContentType();
							listener.OnFinish(response.toString());
						}
					} catch (IOException e) {
						e.printStackTrace();
						if(listener!=null){
							listener.OnError(e);
						}
					}finally{
						if(con!=null)
							con.disconnect();
					}
			}
		}).start();
		
	}
}
