package net.wszf.client.wooyun.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.wszf.client.wooyun.domain.BugInfoDomain;

public class ApiUtils
	{
		public static List<BugInfoDomain> getSubmitList(String url,List<BugInfoDomain> list) throws ClientProtocolException, JSONException, IOException, URISyntaxException
			{
//				JSONObject jsonObject=JsonUtils.getGetResult(url, "");
				list.clear();
				JSONArray jsonArray=JsonUtils.getGetResult(url, "");
				for(int i=0;i<jsonArray.length();i++)
					{
						JSONObject info=jsonArray.getJSONObject(i);
						list.add(new BugInfoDomain(info.getString("title"), CommonUtils.getTime(Long.valueOf(info.getString("timestamp"))), info.getString("status"), info.getString("link")));
					}
				return list;
			}
	}
