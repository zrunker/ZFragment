package cc.ibooker.zfragment.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cc.ibooker.zfragment.me.FragmentPagerAdapter;
import cc.ibooker.zfragment.R;

/**
 * 消息Fragment
 * Created by 邹峰立 on 2018/2/28.
 */
public class MessageFragment extends Fragment implements View.OnClickListener {
    private View view;
    private ViewPager viewPager;
    private ArrayList<Fragment> mDatas;

    private TextView noticeTv, findTv, linkmanTv;

    private ImageView cursor;
    private int bmpw = 0, mCurrentIndex = 0, mScreen1_5, fixLeftMargin;// bmpw游标宽度,mCurrentIndex表示当前所在页面
    private LinearLayout.LayoutParams params;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);

        initView();
        initImg();

        return view;
    }

    // 初始化imageview
    private void initImg() {
        Display disPlay = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        disPlay.getMetrics(outMetrics);
        mScreen1_5 = outMetrics.widthPixels / 5;
        bmpw = outMetrics.widthPixels / 3;
        fixLeftMargin = (bmpw - mScreen1_5) / 2;
        ViewGroup.LayoutParams layoutParams = cursor.getLayoutParams();
        layoutParams.width = mScreen1_5;
        cursor.setLayoutParams(layoutParams);
        /**
         * 设置左侧固定距离
         */
        params = (android.widget.LinearLayout.LayoutParams) cursor.getLayoutParams();
        params.leftMargin = fixLeftMargin;
        cursor.setLayoutParams(params);
    }

    // 改变游动条
    private void changeTextView(int position) {
        noticeTv.setTextColor(getResources().getColor(R.color.gray));
        findTv.setTextColor(getResources().getColor(R.color.gray));
        linkmanTv.setTextColor(getResources().getColor(R.color.gray));
        switch (position) {
            case 0:// 通知
                noticeTv.setTextColor(getResources().getColor(R.color.colorTitle));
                break;
            case 1:// 发现
                findTv.setTextColor(getResources().getColor(R.color.colorTitle));
                break;
            case 2:// 联系人
                linkmanTv.setTextColor(getResources().getColor(R.color.colorTitle));
                break;
        }
        mCurrentIndex = position;
    }

    // 初始化控件
    private void initView() {
        LinearLayout noticeLayout = view.findViewById(R.id.layout_notice);
        noticeLayout.setOnClickListener(this);
        LinearLayout findLayout = view.findViewById(R.id.layout_find);
        findLayout.setOnClickListener(this);
        LinearLayout linkmanLayout = view.findViewById(R.id.layout_linkman);
        linkmanLayout.setOnClickListener(this);

        noticeTv = view.findViewById(R.id.tv_notice);
        findTv = view.findViewById(R.id.tv_find);
        linkmanTv = view.findViewById(R.id.tv_linkman);

        cursor = view.findViewById(R.id.cursor);
        viewPager = view.findViewById(R.id.viewpager);

        mDatas = new ArrayList<>();
        NoticeFragment tab01 = NoticeFragment.newInstance("标题A");
        NoticeFragment tab02 = NoticeFragment.newInstance("标题B");
        NoticeFragment tab03 = NoticeFragment.newInstance("标题C");

        mDatas.add(tab01);
        mDatas.add(tab02);
        mDatas.add(tab03);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return mDatas.size();
            }

            @Override
            public Fragment getItem(int positon) {
                return mDatas.get(positon);
            }
        };

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int positon) {
                // 滑动结束
                changeTextView(positon);
            }

            @Override
            public void onPageScrolled(int positon, float positonOffset, int positonOffsetPx) {
                // 滑动过程
                if (mCurrentIndex == 0 && positon == 0) {// 0-->1
                    params.leftMargin = (int) (mCurrentIndex * bmpw + positonOffset
                            * bmpw)
                            + fixLeftMargin;
                } else if (mCurrentIndex == 1 && positon == 0) {// 1-->0
                    params.leftMargin = (int) (mCurrentIndex * bmpw + (positonOffset - 1)
                            * bmpw)
                            + fixLeftMargin;
                } else if (mCurrentIndex == 1 && positon == 1) {// 1-->1
                    params.leftMargin = (int) (mCurrentIndex * bmpw + positonOffset
                            * bmpw)
                            + fixLeftMargin;
                } else if (mCurrentIndex == 2 && positon == 1) {// 2-->1
                    params.leftMargin = (int) (mCurrentIndex * bmpw + (positonOffset - 1)
                            * bmpw)
                            + fixLeftMargin;
                } else if (mCurrentIndex == 2 && positon == 2) {// 2-->2
                    params.leftMargin = (int) (mCurrentIndex * bmpw + positonOffset
                            * bmpw)
                            + fixLeftMargin;
                }
                cursor.setLayoutParams(params);
            }

            @Override
            public void onPageScrollStateChanged(int positon) {
                // TODO Auto-generated method stub

            }
        });
        viewPager.setOffscreenPageLimit(mDatas.size());// 缓存
    }

    // 控件点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_notice:// 通知
                viewPager.setCurrentItem(0);
                break;
            case R.id.layout_find:// 发现
                viewPager.setCurrentItem(1);
                break;
            case R.id.layout_linkman:// 联系人
                viewPager.setCurrentItem(2);
                break;
        }
    }

}
