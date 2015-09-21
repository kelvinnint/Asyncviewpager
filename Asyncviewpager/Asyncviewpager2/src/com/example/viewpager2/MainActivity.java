package com.example.viewpager2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.viewpagerindicator.CirclePageIndicator;

public class MainActivity extends Activity {
	String url1="http://p0.so.qhimg.com/bdr/_240_/t01ad3641a09574b2f5.jpg";
	String url2="http://p0.so.qhimg.com/bdr/_240_/t01a3f05c605b07d5d5.jpg";
	String url3="http://p2.so.qhimg.com/bdr/_240_/t0126b04b6e0a3fccb7.jpg";
	String url4="http://p1.so.qhimg.com/bdr/_240_/t01baab02b231be535b.jpg";
	String url5="http://p0.so.qhimg.com/bdr/_240_/t01c5ad4f3a5cf97185.jpg";
	String url6="http://p0.so.qhimg.com/bdr/_240_/t0141df88740d0e417f.jpg";
	String url[]={url1,url2,url3,url4,url5,url6};
    private ViewPager viewpager;
    Mypageradapter mypageradapter;
    CirclePageIndicator mIndicator;
    Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			int currentItem = viewpager.getCurrentItem();
			if(currentItem<mypageradapter.getCount()-1){
				currentItem++;
			}else{
				currentItem=0;
			}
			viewpager.setCurrentItem(currentItem);
			handler.sendEmptyMessageDelayed(0, 3000);
			
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		ImageLoaderConfiguration configuration=ImageLoaderConfiguration.createDefault(MainActivity.this);
		ImageLoader.getInstance().init(configuration);
		viewpager=(ViewPager) findViewById(R.id.viewpager);
		mypageradapter=new Mypageradapter();
		viewpager.setAdapter(mypageradapter);
		mIndicator=(CirclePageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(viewpager);
		handler.sendEmptyMessageDelayed(0, 3000);
		
		
	}

	
	class Mypageradapter extends PagerAdapter{
       
		public Mypageradapter() {
			
		}
		
		@Override
		public int getCount() {
			
			return url.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			
			return view==obj;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView=new ImageView(MainActivity.this);
			container.addView(imageView);
			DisplayImageOptions options=new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).build();
			ImageLoader.getInstance().displayImage(url[position], imageView,options);
			return imageView;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			container.removeView((View) object);
		}
		
		
		
	}
	

}
