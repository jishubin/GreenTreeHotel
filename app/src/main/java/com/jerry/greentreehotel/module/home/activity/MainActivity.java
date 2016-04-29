package com.jerry.greentreehotel.module.home.activity;

import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jerry.greentreehotel.R;
import com.jerry.greentreehotel.base.BaseActivity;
import com.jerry.greentreehotel.db.dao.IBaseDao;
import com.jerry.greentreehotel.module.home.entity.LoreDetail;
import com.jerry.greentreehotel.module.home.fragment.HomePageFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity
{
    /*@ViewInject(R.id.btn_showMenu)*/
    private Button mBtnShowMenu;
    private FragmentManager manager;
    IBaseDao<LoreDetail> dao;
    SlidingMenu menu = null;
    @ViewInject(R.id.rg_button_search)
    private RadioGroup rgSearchandCollect;


   /* @Event(type = View.OnClickListener.class, value = {R.id.iv_showMenu})
    private void onClick(View view)
    {
        if (menu != null)
        {
            menu.toggle();
        }
    }*/

    /**
     * 初始化视图
     */
    @Override
    protected void initViews(){
        //dao = DaoFactory.getDao(getApplicationContext(), LoreDetail.class);
        //
        //dao.insert(new LoreDetail());
        //dao.update()
        menu = new SlidingMenu(this);
        // 设置左边侧边栏
        menu.setMode(SlidingMenu.LEFT);
        // 设置什么位置可以拉动侧边栏
        //menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        // 中间阴影部分宽度
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.sliding_shadow);
        // 侧边栏后面的布局的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //menu.setBehindOffset(DensityUtil.dip2px(120));
        menu.setFadeDegree(0.35f);
        // 把侧边栏附加到当前的Activity
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // 设置侧边栏的布局
        menu.setMenu(R.layout.main_left_menu);


        /*Button button = (Button) findViewById(R.id.btn_mainpage);*/
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fl_main_home, new HomePageFragment()).commit();
        /*button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                menu.toggle();
            }
        });

    }*/

    }
    /**
     * 加载数据
     */
    @Override
    protected void loadData()
    {
       /* HttpUtil util = HttpUtil.getInstance();

        Map<String, Object> param = new HashMap<>();
        param.put("pageindex", 3);

        util.sendPost(Url.HOTEL_LIST, param);*/
    }

    /**
     * 获取具体的结果
     *
     * @param result
     */
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onGetResult(String result)
    {
        Log.d("getResult", "onGetResult() returned: " + result);
    }
}
