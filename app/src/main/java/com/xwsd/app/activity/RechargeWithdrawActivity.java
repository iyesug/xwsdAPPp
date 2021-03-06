package com.xwsd.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xwsd.app.R;
import com.xwsd.app.base.BaseActivity;
import com.xwsd.app.fragment.RechargeRecordFragment;
import com.xwsd.app.fragment.WithdrawRecordFragment;
import com.xwsd.app.view.NavbarManage;

import butterknife.Bind;

/**
 * Created by Gx on 2016/8/29.
 * 充值提现记录
 */
public class RechargeWithdrawActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    @Bind(R.id.view_pager)
    ViewPager view_pager;

    /**
     * 左边TAB
     */
    @Bind(R.id.tab_project_left)
    TextView tab_project_left;

    /**
     * 右边TAB
     */
    @Bind(R.id.tab_project_right)
    TextView tab_project_right;

    /**
     * 左边指示器
     */
    @Bind(R.id.indicator_left)
    View indicator_left;

    /**
     * 右边指示器
     */
    @Bind(R.id.indicator_right)
    View indicator_right;

    RechargeRecordFragment rechargeRecordFragment;

    WithdrawRecordFragment withdrawRecordFragment;

    /**
     * 导航栏
     */
    private NavbarManage navbarManage;

    @Override
    protected void onBeforeSetContentLayout() {
        setContentView(R.layout.activity_invest_manage);
        navbarManage = new NavbarManage(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //设置导航栏
        navbarManage.setCentreStr(getString(R.string.recharge_withdraw));
        navbarManage.showLeft(true);
        navbarManage.showRight(false);
        navbarManage.setBackground(R.color.navbar_bg);
        navbarManage.setLeftImg(R.mipmap.ic_back_b);
        navbarManage.setOnLeftClickListener(new NavbarManage.OnLeftClickListener() {
            @Override
            public void onLeftClick() {
                onBackPressed();
            }
        });

        //        设置选项卡
        tab_project_left.setOnClickListener(this);
        tab_project_right.setOnClickListener(this);
        tab_project_left.setText(getString(R.string.recharge_record));
        tab_project_right.setText(getString(R.string.withdraw_record));

        rechargeRecordFragment = new RechargeRecordFragment();
        withdrawRecordFragment = new WithdrawRecordFragment();
        view_pager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager()));
        view_pager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_project_left:
                if (view_pager.getCurrentItem() == 0) {
                    return;
                }
                updateIndicator(R.id.indicator_left);
                view_pager.setCurrentItem(0);
                break;
            case R.id.tab_project_right:
                if (view_pager.getCurrentItem() == 1) {
                    return;
                }
                updateIndicator(R.id.indicator_right);
                view_pager.setCurrentItem(1);
                break;
        }
    }

    /**
     * 根据ID 更新指示器的状态
     *
     * @param id
     */
    private void updateIndicator(int id) {

        switch (id) {
            case R.id.indicator_left:
                indicator_left.setVisibility(View.VISIBLE);
                indicator_right.setVisibility(View.INVISIBLE);
                break;
            case R.id.indicator_right:
                indicator_left.setVisibility(View.INVISIBLE);
                indicator_right.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
        updateIndicator(position == 0 ? R.id.indicator_left : R.id.indicator_right);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * Fragment的适配器
     */
    private class TabFragmentPagerAdapter extends FragmentPagerAdapter {

        public TabFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return position == 0 ? rechargeRecordFragment : withdrawRecordFragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            Object obj = super.instantiateItem(container, position);
            return obj;
        }
    }
}
