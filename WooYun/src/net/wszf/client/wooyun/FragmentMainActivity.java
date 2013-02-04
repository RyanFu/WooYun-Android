package net.wszf.client.wooyun;

import net.wszf.client.wooyun.fragment.BaseFragmentActivity;
import net.wszf.client.wooyun.fragment.FragmentAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TitlePageIndicator;

public class FragmentMainActivity extends BaseFragmentActivity
	{
		Intent intent;

		@Override
		protected void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.simple_titles);

				mAdapter = new FragmentAdapter(getSupportFragmentManager());

				mPager = (ViewPager) findViewById(R.id.pager);
				mPager.setAdapter(mAdapter);

				mIndicator = (TitlePageIndicator) findViewById(R.id.indicator);
				mIndicator.setViewPager(mPager);
			}
	}
