package net.wszf.client.wooyun.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.wszf.client.wooyun.BuildConfig;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.style.BulletSpan;
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
				StringBuffer result = null;
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
								int length = (int) httpResponse.getEntity().getContentLength();
								// the number of bytes of the content, or a negative number if unknown. If the content length is
								// known but
								// exceeds
								// Long.MAX_VALUE, a negative number is returned.
								// length==-1，下面这句报错，println needs a message
								if(BuildConfig.DEBUG)
									{
										System.out.println("length:"+length+"dfdf:"+httpResponse.getEntity().getContentLength());
									}
								if (length < 0)
									{
										length = 10000;
									}
								
								result = new StringBuffer();
								InputStreamReader inputStreamReader = new InputStreamReader(httpResponse.getEntity().getContent(), HTTP.UTF_8);
								char buffer[] = new char[length];
								int count;
								while ((count = inputStreamReader.read(buffer, 0, length - 1)) > 0)
									{
										result.append(buffer, 0, count);
									}
								inputStreamReader.close();
							}

					} finally
					{
						getMethod.abort();

					}
				return new JSONArray(result.toString());
			}
	}
