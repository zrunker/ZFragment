package cc.ibooker.zfragment.classify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.ibooker.zfragment.R;

/**
 * 分类Fragment
 * Created by 邹峰立 on 2018/2/28.
 */
public class ClassifyFragmentOne extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_classify_one, container, false);
    }
}
