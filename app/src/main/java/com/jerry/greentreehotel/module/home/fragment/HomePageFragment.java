package com.jerry.greentreehotel.module.home.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.jerry.greentreehotel.R;
import com.jerry.greentreehotel.base.BaseFragment;
import com.jerry.greentreehotel.base.EventMessage;
import com.jerry.greentreehotel.constant.Url;
import com.jerry.greentreehotel.module.home.adapter.BannerNewsPageAdapter;
import com.jerry.greentreehotel.module.home.entity.NewsDetail;
import com.jerry.greentreehotel.utils.HttpUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by Administrator on 2016/4/27 0027.
 */
@ContentView(R.layout.mainmenu_activity)
public class HomePageFragment extends BaseFragment{

    @ViewInject(R.id.rg_button_search)
    private RadioGroup rgSearchandCollect;
    private FragmentManager manager;
    @ViewInject(R.id.vp_consider)
    private ViewPager viewPager;

    private List<NewsDetail.ResponseDataBean.BannerListBean> list;
    @ViewInject(R.id.lay_dot)
    private RadioGroup rgDots;
    private BannerNewsPageAdapter adapter;

    @Override
    protected void initViews(){
        list = new ArrayList<>();
        EventBus.getDefault().register(this);
        manager = getChildFragmentManager();
        manager.beginTransaction().add(R.id.fl_searchAndcollect, new SeacherFragment()).commit();
        rgSearchandCollect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                switch(checkedId){
                    case R.id.RB_reserchhotle:
                        manager.beginTransaction().add(R.id.fl_searchAndcollect, new SeacherFragment()).commit();
                        break;
                    case R.id.RB_collect:
                        manager.beginTransaction().replace(R.id.fl_searchAndcollect, new CollectFragment()).addToBackStack(null).commit();
                        break;
                }
            }
        });

    }


    @Override
    protected void loadData(){
        HttpUtil.getInstance().sendPost(Url.AD_URL,null);
        adapter = new BannerNewsPageAdapter(getContext(),list);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
                View rb = rgDots.getChildAt(position);
                rgDots.check(rb.getId());


            }

            @Override
            public void onPageSelected(int position){
            }

            @Override
            public void onPageScrollStateChanged(int state){

                if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1) {
                    viewPager.setCurrentItem(0);
                }
                // 当前为第一张，此时从左向右滑，则切换到最后一张
                else if (viewPager.getCurrentItem() == 0) {
                    viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                }
            }
        });

        initGetDots();
    }
    private void initGetDots(){
        rgDots.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                int count = group.getChildCount();
                for(int i = 0; i < count; i++){
                    if(group.getChildAt(i).getId() == checkedId){
                        viewPager.setCurrentItem(i);
                        break;
                    }
                }
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onGetResult(EventMessage message){
        if(message.type.equals(Url.AD_URL)){
            Gson gson = new Gson();
            NewsDetail newsDetail = gson.fromJson(message.result, NewsDetail.class);
            adapter.getList().addAll(newsDetail.getResponseData().getBannerList());
            adapter.notifyDataSetChanged();
        }

    }

}