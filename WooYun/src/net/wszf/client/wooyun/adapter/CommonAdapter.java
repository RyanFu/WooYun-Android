package net.wszf.client.wooyun.adapter;

import java.util.List;

import net.wszf.client.wooyun.R;
import net.wszf.client.wooyun.domain.BugInfoDomain;
import net.wszf.client.wooyun.utils.CommonUtils;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 通用adapter
 * @author pxs
 * @project WooYun
 * @date 2013-1-25下午1:30:08
 * @version 1.0
 */
public class CommonAdapter extends BaseAdapter
	{
		private List<BugInfoDomain> list;
		private Context context;
		private LayoutInflater layoutInflater;
		public CommonAdapter(List<BugInfoDomain> list, Context context)
			{
				super();
				this.list = list;
				this.context = context;
				layoutInflater=LayoutInflater.from(context);
			}

		@Override
		public int getCount()
			{
				// TODO Auto-generated method stub
				return list.size();
			}

		@Override
		public Object getItem(int position)
			{
				// TODO Auto-generated method stub
				return list.get(position);
			}

		@Override
		public long getItemId(int position)
			{
				// TODO Auto-generated method stub
				return 0;
			}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
			{
				WrapperView wrapperView;
				if(list.size()>0)
					{
						BugInfoDomain info=list.get(position);
						if(convertView==null)
							{
							wrapperView=new WrapperView();
							convertView=layoutInflater.inflate(R.layout.item, null);
							wrapperView.title=(TextView) convertView.findViewById(R.item.title);
							wrapperView.date=(TextView) convertView.findViewById(R.item.date);
							wrapperView.link=(TextView) convertView.findViewById(R.item.link);
							convertView.setTag(wrapperView);
							}
						else{
							wrapperView=(WrapperView) convertView.getTag();
						}
						wrapperView.title.setText(CommonUtils.getStringFormat(context, R.string.item_title, info.getTitle()));
						wrapperView.date.setText(CommonUtils.getStringFormat(context, R.string.item_date, info.getDate()));
						wrapperView.link.setText(CommonUtils.getStringFormat(context, R.string.item_link, info.getLink()));
					}
				return convertView;
			}
		class WrapperView{
			TextView title,date,link;
		}
	}
