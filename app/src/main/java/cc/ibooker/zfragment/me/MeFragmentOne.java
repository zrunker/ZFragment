package cc.ibooker.zfragment.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.ibooker.zfragment.R;

/**
 * 个人中心Fragment
 * Created by 邹峰立 on 2018/2/28.
 */
public class MeFragmentOne extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me_one, container, false);
    }
}
