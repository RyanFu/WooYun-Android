package net.wszf.client.wooyun.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import net.wszf.client.wooyun.BuildConfig;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

/**
 * json获取数据
 * 
 * @author pxs
 * @project GB_Hui
 * @date 2012-10-12上午10:38:49
 * @version 1.0
 */
public class JsonUtils
	{
		private static final String TAG = "JsonUtils";

		

		/**
		 * get方式获取
		 * 
		 * @param url
		 * @param params
		 * @param method
		 * @return
		 * @throws JSONException
		 * @throws ClientProtocolException
		 * @throws IOException
		 * @throws URISyntaxException 
		 */
		public static JSONArray getGetResult(String url,String method) throws JSONException, ClientProtocolException, IOException, URISyntaxException
			{
				HttpClient client = new DefaultHttpClient();
				String result = null;
				int statusCode = 200;
				
				HttpGet getMethod = new HttpGet(new URI(url));
				
				if(BuildConfig.DEBUG)
					{
						Log.d(TAG, "do the getRequest,url=" + url + method + "");
					}
				try
					{
						// getMethod.setHeader("User-Agent", USER_AGENT);
						HttpResponse httpResponse = client.execute(getMethod);
						if (statusCode == httpResponse.getStatusLine().getStatusCode())// 判断是否正常
							{
								if(BuildConfig.DEBUG)
								Log.d(TAG, "statuscode = " + statusCode);
								// 处理返回的httpResponse信息
								result=EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
							}

					} finally
					{
						getMethod.abort();

					}
				return new JSONArray(result);
			}
	}
