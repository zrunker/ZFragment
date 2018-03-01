package cc.ibooker.zfragment.classify;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import cc.ibooker.zfragment.R;

/**
 * （关注，推荐，热点，视频，新时代，娱乐，科技，问答，财经）
 * create by 邹峰立 on 2016/9/22
 */
public class ClassifyFragment extends Fragment {
    private View view;

    private HorizontalScrollView mColumnHorizontalScrollView;
    private LinearLayout mColumnContent;
    private TextView indicateTV;// 指示器
    private LinearLayout.LayoutParams indicateParams; // 指示器的布局参数，可设置控件在布局中的相关属性
    private ViewPager mViewPager;

    /**
     * 栏目部分类列表
     */
    private ArrayList<ClassifyTitleData> mTitleDatas = new ArrayList<>();
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

    /**
     * 移动指示器 - 通过handler执行主线程
     */
    private MyHandler myHandler = new MyHandler(this);

    static class MyHandler extends Handler {
        // 定义一个对象用来引用Activity中的方法
        private final WeakReference<Fragment> mFragment;

        MyHandler(Fragment fragment) {
            mFragment = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ((ClassifyFragment) mFragment.get()).initStartAnimation(msg.arg1, (Float) msg.obj);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classify, container, false);

        initView();
        // 初始化列表,从共享参数中获取标题,如果不为空,则设置,如果为空,则从网络获取分类列表
        setChangelView(getData());

        return view;
    }

    /**
     * 初始化layout控件
     */
    private void initView() {
        // 初始化导航条
        mColumnHorizontalScrollView = view.findViewById(R.id.mColumnHorizontalScrollView_IntegralShop);
        mColumnContent = view.findViewById(R.id.mRadioGroup_content_IntegralShop);
        mViewPager = view.findViewById(R.id.mViewPager_IntegralShop);

        // 初始化指示器
        indicateTV = view.findViewById(R.id.indicateId);
        indicateParams = (LinearLayout.LayoutParams) indicateTV.getLayoutParams();
    }

    /**
     * 当栏目项发生变化时候调用
     */
    private void setChangelView(ArrayList<ClassifyTitleData> lists) {
        if (lists == null) {
            // 没有数据就通过网络获取
            return;
        } else {
            initColumnData(lists);
        }

        initTabColumn();
        initFragment();
        // 初始化指示器位置
        initIndicator();
    }

    /**
     * 获取Column栏目 数据
     */
    private void initColumnData(ArrayList<ClassifyTitleData> lists) {
        mTitleDatas = lists;
    }

    /**
     * 初始化Column栏目项
     */
    private void initTabColumn() {
        mScreenWidth = getWindowsWidth();
        mItemWidth = mScreenWidth / 5; // 一个Item宽度为屏幕的1/5
        mColumnContent.removeAllViews();

        int count = mTitleDatas.size();

        for (int i = 0; i < count; i++) { // 自定义TextView
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, LayoutParams.WRAP_CONTENT);
            TextView localTextView = new TextView(getContext());
            localTextView.setTextAppearance(getContext(), R.style.top_category_scroll_view_item_text);
            localTextView.setGravity(Gravity.CENTER);
            localTextView.setText(mTitleDatas.get(i).getArname());
            localTextView.setTextColor(getResources().getColorStateList(R.color.gray));

            if (columnSelectIndex == i) {// 设置第一个被选中,颜色变化
                localTextView.setTextColor(getResources().getColorStateList(R.color.colorTitle));
            }
            mColumnContent.addView(localTextView, i, params);
        }

        // 重新设置宽度
        LinearLayout.LayoutParams columuParams = (LinearLayout.LayoutParams) mColumnContent.getLayoutParams();
        columuParams.width = mItemWidth * count;
        mColumnContent.setLayoutParams(columuParams);

        // 设置标题类型选择点击事件
        setModelClick();
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fragments = new ArrayList<>();

        ClassifyFragmentOne tab01 = new ClassifyFragmentOne();
        ClassifyFragmentOne tab02 = new ClassifyFragmentOne();
        ClassifyFragmentOne tab03 = new ClassifyFragmentOne();
        ClassifyFragmentOne tab04 = new ClassifyFragmentOne();
        ClassifyFragmentOne tab05 = new ClassifyFragmentOne();
        ClassifyFragmentOne tab06 = new ClassifyFragmentOne();
        ClassifyFragmentOne tab07 = new ClassifyFragmentOne();
        ClassifyFragmentOne tab08 = new ClassifyFragmentOne();
        ClassifyFragmentOne tab09 = new ClassifyFragmentOne();
        fragments.add(tab01);
        fragments.add(tab02);
        fragments.add(tab03);
        fragments.add(tab04);
        fragments.add(tab05);
        fragments.add(tab06);
        fragments.add(tab07);
        fragments.add(tab08);
        fragments.add(tab09);

        mViewPager.removeAllViews();
        int size = mTitleDatas.size();

        mViewPager.setOffscreenPageLimit(size);
        if (mAdapetr != null)
            mAdapetr.clearFragment();
        mAdapetr = new FragmentPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void initIndicator() {
        // 重置选择器位置
        selectMode(0);

        // 初始化指示器并设置指示器位置
        indicateParams.width = mItemWidth;// 设置指示器宽度
        indicateParams.setMargins(0, 0, 0, 0);
        indicateTV.setLayoutParams(indicateParams);// 设置指示器宽度
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
            myHandler.sendMessage(msg);
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
        if (offset == 0) {// 停止滚动
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
            textView.setTextColor(getResources().getColor(R.color.gray));
            if (i == position) {
                selTextView = textView;
                // 设置被选中项字体颜色变化为被选中颜色
                selTextView.setTextColor(getResources().getColor(R.color.colorTitle));
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
    private int getWindowsWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    // 关注，推荐，热点，视频，新时代，娱乐，科技，问答，财经
    private ArrayList<ClassifyTitleData> getData() {
        ArrayList<ClassifyTitleData> newsClassify = new ArrayList<>();
        ClassifyTitleData classify = new ClassifyTitleData();
        classify.setClassid(0);
        classify.setArname("关注");
        newsClassify.add(classify);

        classify = new ClassifyTitleData();
        classify.setClassid(1);
        classify.setArname("推荐");
        newsClassify.add(classify);

        classify = new ClassifyTitleData();
        classify.setClassid(2);
        classify.setArname("热点");
        newsClassify.add(classify);

        classify = new ClassifyTitleData();
        classify.setClassid(3);
        classify.setArname("视频");
        newsClassify.add(classify);

        classify = new ClassifyTitleData();
        classify.setClassid(4);
        classify.setArname("新时代");
        newsClassify.add(classify);

        classify = new ClassifyTitleData();
        classify.setClassid(5);
        classify.setArname("娱乐");
        newsClassify.add(classify);

        classify = new ClassifyTitleData();
        classify.setClassid(6);
        classify.setArname("科技");
        newsClassify.add(classify);

        classify = new ClassifyTitleData();
        classify.setClassid(7);
        classify.setArname("问答");
        newsClassify.add(classify);

        classify = new ClassifyTitleData();
        classify.setClassid(8);
        classify.setArname("财经");
        newsClassify.add(classify);

        return newsClassify;
    }

}