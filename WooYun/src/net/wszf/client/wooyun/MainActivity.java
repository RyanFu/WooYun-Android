package net.wszf.client.wooyun;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import net.wszf.client.wooyun.adapter.CommonAdapter;
import net.wszf.client.wooyun.domain.BugInfoDomain;
import net.wszf.client.wooyun.utils.ApiUtils;
import net.wszf.client.wooyun.utils.CommonUtils;
import net.wszf.client.wooyun.utils.FiledMark;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity
	{
		private ListView listView;
//		public static LatestSubmitListActivity latestSubmitListActivity=null;
		private CommonAdapter adapter;
		private List<BugInfoDomain> list=new ArrayList<BugInfoDomain>();
		private ProgressDialog dialog;
		private Thread thread;
		private String url=FiledMark.SUBMIT_URL;
		private Runnable infoRunnable=new Runnable()
			{
				@Override
				public void run()
					{
						try
							{
								ApiUtils.getSubmitList(url, list);
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
						listView.setAdapter(adapter);
						listView.setVisibility(View.VISIBLE);
						dialog.cancel();
						return false;
					}
			});
	

		private void fillView()
			{
				dialog=CommonUtils.getLoading(this, dialog);
				listView=(ListView) findViewById(R.main.listView);
				adapter=new CommonAdapter(list,this);
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
//			public static LatestSubmitListActivity getInstance()
//				{
//					if(latestSubmitListActivity==null)
//						{
//						latestSubmitListActivity=new LatestSubmitListActivity();	
//						}
//					return latestSubmitListActivity;
//				}
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

		@Override
		protected void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_main);
				fillView();
			}

		@Override
		public boolean onCreateOptionsMenu(Menu menu)
			{
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.activity_main, menu);
				return true;
			}
		public boolean onOptionsItemSelected(MenuItem item)
			{
				switch (item.getItemId())
					{
					case R.menu.about:
						new AlertDialog.Builder(this)
						.setTitle("关于")
						.setMessage("感谢WooYun开放API.\n Version:1.1")
						.setNegativeButton("确定", null)
						.create().show();
						return true;
					case R.menu.new_submit:
						url=FiledMark.SUBMIT_URL;
						CommonAdapter.mark=0;
						list.clear();
						dialog.show();
						new Thread(infoRunnable).start();
						break;
					case R.menu.new_confirm:
						url=FiledMark.CONFIRM_URL;
						CommonAdapter.mark=1;
						list.clear();
						dialog.show();
						new Thread(infoRunnable).start();
						break;
					case R.menu.new_public:
						url=FiledMark.PUBLIC_URL;
						CommonAdapter.mark=1;
						list.clear();
						dialog.show();
						new Thread(infoRunnable).start();
						break;
					case R.menu.new_unclaim:
						url=FiledMark.UNCLAIM_URL;
						CommonAdapter.mark=0;
						list.clear();
						dialog.show();
						new Thread(infoRunnable).start();
						break;
					
					}
				return super.onOptionsItemSelected(item);
			}
	}
