package cc.ibooker.zfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * ViewPager的Adapter
 * create by 邹峰立 on 2016/9/22
 */
public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private FragmentManager fm;

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    public FragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     * 从新设置fragment集合
     *
     * @param fragments
     */
    public void setFragments(ArrayList<Fragment> fragments) {
        if (this.fragments != null) {
            FragmentTransaction ft = fm.beginTransaction();
            for (Fragment f : this.fragments) {
                ft.remove(f);
            }
            ft.commit();
            fm.executePendingTransactions();
        }
        if (fragments != null)
            this.fragments = fragments;
        notifyDataSetChanged();
    }

    public void clearFragment() {
        setFragments(null);
    }

}
