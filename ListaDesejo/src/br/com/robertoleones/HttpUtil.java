package br.com.robertoleones;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class HttpUtil {

	public static final String doGet(final String url) {
		String result = null;

		try {
			URI uri = new URI(url);
			final HttpGet httpGet = new HttpGet(uri);
			final HttpClient httpClient = new DefaultHttpClient();

			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				public String handleResponse(HttpResponse response)
						throws ClientProtocolException, IOException {
					HttpEntity entity = response.getEntity();
					return EntityUtils.toString(entity);
				}
			};

			result = httpClient.execute(httpGet, responseHandler);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static Bitmap getBitmap(String strURL) {
		Bitmap result = null;
		InputStream inputStream = null;

		try {
			URL url = new URL(strURL);
			URLConnection conn = url.openConnection();

			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setRequestMethod("GET");
			httpConn.connect();

			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				inputStream = httpConn.getInputStream();
			}

			BitmapFactory.Options bmOptions;
			bmOptions = new BitmapFactory.Options();
			bmOptions.inSampleSize = 1;

			result = BitmapFactory.decodeStream(inputStream, null, bmOptions);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

}
