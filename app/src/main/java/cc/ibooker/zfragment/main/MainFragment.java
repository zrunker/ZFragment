package cc.ibooker.zfragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import cc.ibooker.zfragment.R;

/**
 * 首页Fragment
 * <p>
 * Created by 邹峰立 on 2018/2/28.
 */
public class MainFragment extends Fragment {

    // 当Fragment被添加到Activity时候调用
    @Override
    public void onAttach(Context context) {
        Log.d("MainFragment", "onAttach");
        if (textView != null)
            textView.setText("onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("MainFragment", "onCreate");
        if (textView != null)
            textView.setText("onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d("MainFragment", "onStart");
        if (textView != null)
            textView.setText("onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.d("MainFragment", "onStop");
        if (textView != null)
            textView.setText("onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d("MainFragment", "onDestroy");
        if (textView != null)
            textView.setText("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onPause() {
        Log.d("MainFragment", "onPause");
        if (textView != null)
            textView.setText("onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.d("MainFragment", "onResume");
        if (textView != null)
            textView.setText("onResume");
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("MainFragment", "onActivityCreated");
        if (textView != null)
            textView.setText("onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public boolean getUserVisibleHint() {
        Log.d("MainFragment", "getUserVisibleHint");
        if (textView != null)
            textView.setText("getUserVisibleHint");
        return super.getUserVisibleHint();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d("MainFragment", "setUserVisibleHint");
        if (textView != null)
            textView.setText("setUserVisibleHint");
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDetach() {
        Log.d("MainFragment", "onDetach");
        if (textView != null)
            textView.setText("onDetach");
        super.onDetach();
    }

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MainFragment", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        textView = view.findViewById(R.id.textview);

        return view;
    }

    @Override
    public void onDestroyView() {
        Log.d("MainFragment", "onDestroyView");
        if (textView != null)
            textView.setText("onDestroyView");
        super.onDestroyView();
    }

    // 设置Fragment进入和退出动画
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

}
