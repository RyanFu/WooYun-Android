package net.wszf.client.wooyun.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class FragmentAdapter extends FragmentStatePagerAdapter
	{
		protected static final String[] CONTENT = new String[] { "最新提交", "最新公开", "最新确认","等待认领"};

		public FragmentAdapter(FragmentManager fm)
			{
				super(fm);
				// TODO Auto-generated constructor stub
			}

		@Override
		public Fragment getItem(int position)
			{
				//System.out.println("getItem");
				// TODO Auto-generated method stub
				if (position == 0)
					{
						return LatestSubmitListActivity.getInstance();
					} else if (position == 1)
					{
						return LatestPublicListActivity.getInstance();
					} else if(position==2)
					{
						return LatestConfirmListActivity.getInstance();
					}
					else{
						return LatestUnclaimListActivity.getInstance();
					}
				
			}

		@Override
		public int getCount()
			{
				// TODO Auto-generated method stub
				return CONTENT.length;
			}

		@Override
		public CharSequence getPageTitle(int position)
			{
				// TODO Auto-generated method stub
				return CONTENT[position % CONTENT.length];
			}

		@Override
		public Object instantiateItem(ViewGroup container, int position)
			{
				// TODO Auto-generated method stub
			//	System.out.println("instantiateItem");
				return super.instantiateItem(container, position);
				
			}




	}
