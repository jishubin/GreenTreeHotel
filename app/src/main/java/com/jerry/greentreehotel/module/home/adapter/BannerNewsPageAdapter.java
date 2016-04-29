package com.jerry.greentreehotel.module.home.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jerry.greentreehotel.R;
import com.jerry.greentreehotel.base.BaseApplication;
import com.jerry.greentreehotel.module.home.entity.NewsDetail;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

/**
 * Created by Administrator on 2016/4/28 0028.
 */
public class BannerNewsPageAdapter extends PagerAdapter{
    private List<NewsDetail.ResponseDataBean.BannerListBean> list;

    public List<NewsDetail.ResponseDataBean.BannerListBean> getList(){
        return list;
    }

    private Context context;
    private ImageLoader loader = null;
    private  DisplayImageOptions options = null;

    public BannerNewsPageAdapter(Context context, List<NewsDetail.ResponseDataBean.BannerListBean> list){
        this.context = context;
        this.list = list;
        loader = ((BaseApplication)(context.getApplicationContext())).getImageLoader();

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(false)  // default 设置图片在加载前是否重置、复位
                .delayBeforeLoading(1000)  // 下载前的延迟时间
                .cacheInMemory(true) // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // default  设置下载的图片是否缓存在SD卡中
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT) // default 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default 设置图片的解码类型
                .build();
    }
    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        ImageView imageView = new ImageView(context);
        /*View view = View.inflate(context, R.layout.banner_news,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_banner);*/
        Log.d("index", "=======================" +(position % list.size()));
        loader.displayImage(
                list.get(position).getBannerUrl(),
                imageView,
                options);

        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((View) object);

    }
}
