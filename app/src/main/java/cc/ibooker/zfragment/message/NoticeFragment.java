package cc.ibooker.zfragment.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cc.ibooker.zfragment.R;

/**
 * 通知Fragment
 * Created by 邹峰立 on 2018/2/28.
 */
public class NoticeFragment extends Fragment {

    // 可用于传值
    public static NoticeFragment newInstance(String title) {

        Bundle bundle = new Bundle();

        bundle.putString("title", title);

        NoticeFragment fragment = new NoticeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_one, container, false);
        TextView textView = view.findViewById(R.id.text);

        textView.setText(getArguments().getString("title", "标题"));

        return view;
    }
}
