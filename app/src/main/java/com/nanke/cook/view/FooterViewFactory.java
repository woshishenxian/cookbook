package com.nanke.cook.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nanke.cook.R;
import com.nanke.cook.ui.main.domain.Food;

import java.util.List;

import app.dinus.com.loadingdrawable.LoadingView;

/**
 * Created by vince on 16/10/26.
 */

public class FooterViewFactory implements View.OnClickListener{

    View view;
    TextView btn_more;
    LoadingView loadingView;
    OnMoreRefreshListener mOnMoreRefreshListener;
    boolean isRefreshing = false;


    public FooterViewFactory(Context context) {
        initView(context);
    }
    public FooterViewFactory(Context context, OnMoreRefreshListener mOnMoreRefreshListener) {
        initView(context);
        this.mOnMoreRefreshListener = mOnMoreRefreshListener;
    }


    private void initView(Context context){
        view = LayoutInflater.from(context).inflate(R.layout.recycler_view_footer,null);
        btn_more = (TextView) view.findViewById(R.id.btn_more);
        loadingView = (LoadingView) view.findViewById(R.id.loadingView);
        view.setOnClickListener(this);
    }

    public View getFooterView(){
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return view;
    }


    @Override
    public void onClick(View v) {
        btn_more.setVisibility(View.INVISIBLE);
        loadingView.setVisibility(View.VISIBLE);
        isRefreshing = true;
        mOnMoreRefreshListener.onLoad();

    }

    public void setmOnMoreRefreshListener(OnMoreRefreshListener mOnMoreRefreshListener){
        this.mOnMoreRefreshListener = mOnMoreRefreshListener;
    }

    public interface OnMoreRefreshListener{
        void onLoad();
    }

    public void setRefreshing(boolean isRefresh){
        isRefreshing = isRefresh;
        if(isRefreshing){
            btn_more.setVisibility(View.INVISIBLE);
            loadingView.setVisibility(View.VISIBLE);
        }else{
            btn_more.setVisibility(View.VISIBLE);
            loadingView.setVisibility(View.INVISIBLE);
        }
    }


}
