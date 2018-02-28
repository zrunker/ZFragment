package cc.ibooker.zfragment.me;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cc.ibooker.zfragment.R;

/**
 * 测试在Activity中添加多个Fragment，超过整个屏幕
 * （Android，iOS，Java，PHP，HTML，GO，C，C++）
 * create by 邹峰立 on 2016/9/22
 */
public class TestActivity extends FragmentActivity {
    private HorizontalScrollView mColumnHorizontalScrollView;
    private LinearLayout mColumnContent;
    private TextView indicateTV;//指示器
    private LinearLayout.LayoutParams indicateParams; // 指示器的布局参数，可设置控件在布局中的相关属性
    private ViewPager mViewPager;

    /**
     * 栏目部分类列表
     */
    private ArrayList<TitleData> mTitleDatas = new ArrayList<>();
    /**
     * fragment适配器
     */
    private FragmentPagerAdapter mAdapetr;
    /**
     * 当前选中的类别
     */
    private int columnSelectIndex = 0;
    /**
     * 屏幕宽度
     */
    private int mScreenWidth = 0;
    /**
     * Item宽度
     */
    private int mItemWidth = 0;
    /**
     * Fragment列表
     */
    private ArrayList<Fragment> fragments;

    // 移动指示器
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            initStartAnimation(msg.arg1, (Float) msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        //初始化列表,从共享参数中获取标题,如果不为空,则设置,如果为空,则从网络获取分类列表
        setChangelView(getData());
    }

    /**
     * 初始化layout控件
     */
    private void initView() {
        //初始化导航条
        mColumnHorizontalScrollView = findViewById(R.id.mColumnHorizontalScrollView_IntegralShop);
        mColumnContent = findViewById(R.id.mRadioGroup_content_IntegralShop);
        mViewPager = findViewById(R.id.mViewPager_IntegralShop);
        //初始化指示器
        indicateTV = findViewById(R.id.indicateId);
        indicateParams = (LinearLayout.LayoutParams) indicateTV.getLayoutParams();
    }

    /**
     * 当栏目项发生变化时候调用
     */
    private void setChangelView(ArrayList<TitleData> lists) {
        if (lists == null) {
            //没有数据就通过网络获取
            return;
        } else {
            initColumnData(lists);
        }
        initTabColumn();
        initFragment();
        //初始化指示器位置
        initIndicator();
    }

    /**
     * 获取Column栏目 数据
     */
    private void initColumnData(ArrayList<TitleData> lists) {
        mTitleDatas = lists;
    }

    /**
     * 初始化Column栏目项
     */
    private void initTabColumn() {
        mScreenWidth = getWindowsWidth(this);
        mItemWidth = mScreenWidth / 5; // 一个Item宽度为屏幕的1/5
        mColumnContent.removeAllViews();
        int count = mTitleDatas.size();
        sortList(); //给集合排序
        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, LayoutParams.WRAP_CONTENT);
            TextView localTextView = new TextView(this);
            if (Build.VERSION.SDK_INT < 23) {
                localTextView.setTextAppearance(this, R.style.top_category_scroll_view_item_text);
            } else {
                localTextView.setTextAppearance(R.style.top_category_scroll_view_item_text);
            }
            localTextView.setGravity(Gravity.CENTER);
            localTextView.setText(mTitleDatas.get(i).getArname());
            if (Build.VERSION.SDK_INT >= 23) {
                localTextView.setTextColor(getResources().getColorStateList(R.color.gray, this.getTheme()));
            } else {
                localTextView.setTextColor(getResources().getColorStateList(R.color.gray));
            }
            if (columnSelectIndex == i) {//设置第一个被选中,颜色变化
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    localTextView.setTextColor(getResources().getColorStateList(R.color.colorTitle, this.getTheme()));
                } else {
                    localTextView.setTextColor(getResources().getColorStateList(R.color.colorTitle));
                }
            }
            mColumnContent.addView(localTextView, i, params);
        }
        // 重新设置宽度
        LinearLayout.LayoutParams columuParams = (LinearLayout.LayoutParams) mColumnContent.getLayoutParams();
        columuParams.width = mItemWidth * count;
        mColumnContent.setLayoutParams(columuParams);
        //设置标题类型选择点击事件
        setModelClick();
    }

    //给集合从新排序
    private void sortList() {
        Collections.sort(mTitleDatas, new Comparator<TitleData>() {
            @Override
            public int compare(TitleData lhs, TitleData rhs) {
                int a = lhs.getId() - rhs.getId();
                if (a == 0)
                    return 0;
                if (a > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        int size = mTitleDatas.size();

        MeFragmentOne tab01 = new MeFragmentOne();
        MeFragmentOne tab02 = new MeFragmentOne();
        MeFragmentOne tab03 = new MeFragmentOne();
        MeFragmentOne tab04 = new MeFragmentOne();
        MeFragmentOne tab05 = new MeFragmentOne();
        MeFragmentOne tab06 = new MeFragmentOne();
        MeFragmentOne tab07 = new MeFragmentOne();
        MeFragmentOne tab08 = new MeFragmentOne();

        fragments.add(tab01);
        fragments.add(tab02);
        fragments.add(tab03);
        fragments.add(tab04);
        fragments.add(tab05);
        fragments.add(tab06);
        fragments.add(tab07);
        fragments.add(tab08);

        mViewPager.removeAllViews();
        mViewPager.setOffscreenPageLimit(size);
        if (mAdapetr != null)
            mAdapetr.clearFragment();
        mAdapetr = new FragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void initIndicator() {
        //重置选择器位置
        selectMode(0);
        //初始化指示器并设置指示器位置
        indicateParams.width = mItemWidth;//设置指示器宽度
        indicateParams.setMargins(0, 0, 0, 0);
        indicateTV.setLayoutParams(indicateParams);//设置指示器宽度
    }

    /**
     * ViewPager切换监听方法
     */
    class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int position, float offset, int offsetPixes) {
            Message msg = Message.obtain();
            msg.arg1 = position;
            msg.obj = offset;
            handler.sendMessage(msg);
        }

        @Override
        public void onPageSelected(int position) {
            selectMode(position);
        }
    }

    private void initStartAnimation(int position, float offset) {
        if (fragments.isEmpty()) {
            indicateTV.setVisibility(View.GONE);
        } else {
            indicateTV.setVisibility(View.VISIBLE);
        }
        if (offset == 0) { // 停止滚动
            indicateParams.setMargins(indicateParams.width * position, 0, 0, 0);
        } else {
            indicateParams.setMargins((int) (indicateParams.width * (position + offset)), 0, 0, 0);
        }
        indicateTV.setLayoutParams(indicateParams);
    }

    /**
     * 设置HorizontalScrollView将被选中项移动到中间位置,并且设置被选中项字体变化
     */
    private void selectMode(int position) {
        if (fragments.isEmpty()) return;
        TextView selTextView = null;

        // 获取所有model,清除之前选择的状态
        for (int i = 0; i < mColumnContent.getChildCount(); i++) {
            TextView textView = (TextView) mColumnContent.getChildAt(i);
            if (Build.VERSION.SDK_INT >= 23) {
                textView.setTextColor(getResources().getColor(R.color.gray, this.getTheme()));
            } else {
                textView.setTextColor(getResources().getColor(R.color.gray));
            }
            if (i == position) {
                selTextView = textView;
                //设置被选中项字体颜色变化为被选中颜色
                if (Build.VERSION.SDK_INT >= 23) {
                    selTextView.setTextColor(getResources().getColor(R.color.colorTitle, this.getTheme()));
                } else {
                    selTextView.setTextColor(getResources().getColor(R.color.colorTitle));
                }
            }
        }
        if (selTextView != null) {
            int left = selTextView.getLeft();
            int right = selTextView.getRight();
            int sw = getResources().getDisplayMetrics().widthPixels;
            // 将控件滚动到屏幕的中间位置
            mColumnHorizontalScrollView.scrollTo(left - sw / 2 + (right - left) / 2, 0);
        }
    }

    // 设置每一个模块(标题)的点击事件
    private void setModelClick() {
        // 获取所有model,清除之前选择的状态
        for (int i = 0; i < mColumnContent.getChildCount(); i++) {
            TextView textView = (TextView) mColumnContent.getChildAt(i);
            textView.setTag(i);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = (Integer) v.getTag();
                    mViewPager.setCurrentItem(index);
                }
            });
        }
    }

    /**
     * 获取屏幕宽度
     */
    private int getWindowsWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    // （Android，iOS，Java，PHP，HTML，GO，C，C++）
    private ArrayList<TitleData> getData() {
        ArrayList<TitleData> newsClassify = new ArrayList<>();
        TitleData classify = new TitleData();
        classify.setId(0);
        classify.setArname("Android");
        newsClassify.add(classify);

        classify = new TitleData();
        classify.setId(1);
        classify.setArname("iOS");
        newsClassify.add(classify);

        classify = new TitleData();
        classify.setId(2);
        classify.setArname("Java");
        newsClassify.add(classify);

        classify = new TitleData();
        classify.setId(3);
        classify.setArname("PHP");
        newsClassify.add(classify);

        classify = new TitleData();
        classify.setId(4);
        classify.setArname("HTML");
        newsClassify.add(classify);

        classify = new TitleData();
        classify.setId(5);
        classify.setArname("GO");
        newsClassify.add(classify);

        classify = new TitleData();
        classify.setId(6);
        classify.setArname("C");
        newsClassify.add(classify);

        classify = new TitleData();
        classify.setId(7);
        classify.setArname("C++");
        newsClassify.add(classify);

        return newsClassify;
    }

}