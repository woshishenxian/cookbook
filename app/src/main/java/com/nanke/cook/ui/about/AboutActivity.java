package com.nanke.cook.ui.about;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.nanke.cook.base.BasePresenter;
import com.nanke.cook.BuildConfig;
import com.nanke.cook.R;
import com.nanke.cook.ui.BaseActivity;
import com.nanke.cook.view.ShareBottomDialog;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by vince on 16/11/10.
 */

public class AboutActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.version_text)
    TextView version_text;

    @Override
    public int getLayoutView() {
        return R.layout.activity_about;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initToolbar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.menu_about);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showShareDialog();
                return true;
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_down_up_anim, R.anim.activity_exit_anim);
        version_text.setText("v"+BuildConfig.VERSION_NAME);
    }


    @OnClick(R.id.btn_blog)
    public void onBlogClick(){
        startViewAction(BuildConfig.BLOG_URL);
    }

    @OnClick(R.id.btn_project_home)
    public void onProHomeClck(){
        startViewAction(BuildConfig.PROJECT_URL);
    }

    private void startViewAction(String uriStr){
        try {
            Uri uri = Uri.parse(uriStr);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showShareDialog(){
        ShareBottomDialog shareBottomDialog = new ShareBottomDialog(this);
        shareBottomDialog.showAnim(new BounceTopEnter()).show();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_exit_anim, R.anim.activity_up_down_anim);
    }
}
