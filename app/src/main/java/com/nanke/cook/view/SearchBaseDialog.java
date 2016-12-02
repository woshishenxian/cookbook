package com.nanke.cook.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceRightEnter;
import com.flyco.dialog.widget.base.BaseDialog;
import com.nanke.cook.R;
import com.nanke.cook.utils.SnackbarUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by vince on 16/12/1.
 */

public class SearchBaseDialog extends BaseDialog<SearchBaseDialog> {

    @InjectView(R.id.searchView)
    SearchView mSearch;

    public SearchBaseDialog(Context context) {
        super(context);
    }

    public SearchBaseDialog(Context context, Callback callback) {
        super(context);
        this.callback = callback;
    }

    @Override
    public View onCreateView() {
        widthScale(0.7f);

        showAnim(new BounceRightEnter());
//        setCanceledOnTouchOutside(false);

        View inflate = View.inflate(mContext, R.layout.panel_search_input, null);
        ButterKnife.inject(this, inflate);

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        int id = mSearch.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        //获取到TextView的控件
        TextView textView = (TextView) mSearch.findViewById(id);
        //设置字体大小为14sp
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);//14sp
        //设置字体颜色
        textView.setTextColor(ActivityCompat.getColor(getContext(), R.color.grey_1));
        //设置提示文字颜色
        textView.setHintTextColor(ActivityCompat.getColor(getContext(), R.color.grey));

        mSearch.onActionViewExpanded();

        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (callback != null) {
                    callback.query(mSearch.getQuery()+"");
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

    }


    @OnClick(R.id.tv_cancel)
    public void onCancelClick() {
        dismiss();
    }


    @OnClick(R.id.tv_enter)
    public void startWeatherActivity() {
        if (callback != null) {
            callback.query(mSearch.getQuery()+"");
        }
    }

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {

        void query(String string);
    }
}
