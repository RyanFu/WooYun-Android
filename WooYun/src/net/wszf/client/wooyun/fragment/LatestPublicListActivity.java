package net.wszf.client.wooyun.fragment;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import net.wszf.client.wooyun.BuildConfig;
import net.wszf.client.wooyun.R;
import net.wszf.client.wooyun.adapter.CommonAdapter;
import net.wszf.client.wooyun.domain.BugInfoDomain;
import net.wszf.client.wooyun.utils.ApiUtils;
import net.wszf.client.wooyun.utils.CommonUtils;
import net.wszf.client.wooyun.utils.FiledMark;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * 最新公开
 * @author pxs
 * @project WooYun
 * @date 2013-1-24下午10:22:40
 * @version 1.0
 */
public class LatestPublicListActivity extends Fragment
	{
		private ListView listView;
		private View view;
		public static LatestPublicListActivity latestSubmitListActivity=null;
		private CommonAdapter adapter;
		private ProgressDialog dialog;
		private Thread thread;
		private List<BugInfoDomain> list=new ArrayList<BugInfoDomain>();
		private Runnable infoRunnable=new Runnable()
			{
				@Override
				public void run()
					{
						try
							{
								ApiUtils.getSubmitList(FiledMark.PUBLIC_URL, list);
								handler.sendEmptyMessage(1);
							} catch (ClientProtocolException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (JSONException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (URISyntaxException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					}
			};
		private Handler handler=new Handler(new Callback()
			{
				@Override
				public boolean handleMessage(Message msg)
					{
						listView.setVisibility(View.GONE);
						adapter.notifyDataSetChanged();
						listView.setVisibility(View.VISIBLE);
						dialog.cancel();
						return false;
					}
			});
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
			{
				// TODO Auto-generated method stub
				view = inflater.inflate(R.layout.activity_main, container,false);
//				view = inflater.inflate(R.layout.activity_main, null);
				fillView();
				return view;
			}

		private void fillView()
			{
				dialog=CommonUtils.getLoading(getActivity(), dialog);
				listView=(ListView) view.findViewById(R.main.listView1);
				adapter=new CommonAdapter(list, getActivity());
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener()
					{

						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id)
							{
								 Intent intent= new Intent();        
					                intent.setAction("android.intent.action.VIEW");    
					                Uri content_url = Uri.parse(list.get(position).getLink());   
					                intent.setData(content_url);           
					                //intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");   
					                startActivity(intent);
								
							}
					});
				dialog.show();
				if(thread == null){
					thread = new Thread(infoRunnable);
				}
				if(!thread.isAlive()){
					thread.start();
				}
				//new Thread(infoRunnable).start();
			}
			public static LatestPublicListActivity getInstance()
				{
					if(latestSubmitListActivity==null)
						{
						latestSubmitListActivity=new LatestPublicListActivity();	
						}
					return latestSubmitListActivity;
					
				}
			@Override
			public void onPause() {
				// TODO Auto-generated method stub
				super.onPause();
				stopRecThread();
				if(BuildConfig.DEBUG)
				Log.d("meng", "onPause2");
			}
			public  void stopRecThread(){
				if(thread!=null){
					thread.interrupt();
					thread = null;
				}
			}
			@Override
			public void onStop()
				{
					// TODO Auto-generated method stub
					super.onStop();
					stopRecThread();
					if(BuildConfig.DEBUG)
					Log.d("meng", "onstop");
				}
	}
