package com.jerry.greentreehotel.module.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.jerry.greentreehotel.R;
import com.jerry.greentreehotel.base.BaseViewHolder;
import com.jerry.greentreehotel.base.MyBaseAdapter;
import com.jerry.greentreehotel.module.home.entity.LoreDetail;

import java.util.List;

/**
 * Created by mac on 27/4/16.
 */
public class LoreDetailAdapter extends MyBaseAdapter<LoreDetail>
{
    private Context context;
    public LoreDetailAdapter(Context context, List<LoreDetail> list)
    {
        super(context,list);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.activity_main;
    }

    @Override
    public BaseViewHolder getViewHolder()
    {
        return new ViewHodler();
    }

    @Override
    public void bindViews(View view,BaseViewHolder viewHolder)
    {
        // 绑定内容到控件
        LoreDetail detail = getItem(viewHolder.position);

        ViewHodler myViewHolder = (ViewHodler) viewHolder;

        myViewHolder.tv1.setText(detail.getTitle());
    }

    public static class ViewHodler extends BaseViewHolder
    {
        /*@ViewInject(R.id.btn_showMenu)*/
        Button tv1;
    }


}
