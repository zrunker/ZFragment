package cc.ibooker.zfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cc.ibooker.zfragment.classify.ClassifyFragment;
import cc.ibooker.zfragment.main.MainFragment;
import cc.ibooker.zfragment.me.MeFragment;
import cc.ibooker.zfragment.message.MessageFragment;

/**
 * android.support.v4.app.Fragment应用
 * <p>
 * author 邹峰立
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mainTv, classifyTv, messageTv, meTv;

    private FragmentManager fragmentManager;
    private Fragment mainFragment, classifyFragment, messageFragment, meFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        // 创建Fragment管理对象
        fragmentManager = getSupportFragmentManager();
        // 默认显示第一项
        setTabSelection(0);
    }

    // 初始化控件
    private void initView() {
        mainTv = findViewById(R.id.tv_main);
        mainTv.setOnClickListener(this);
        classifyTv = findViewById(R.id.tv_classify);
        classifyTv.setOnClickListener(this);
        messageTv = findViewById(R.id.tv_message);
        messageTv.setOnClickListener(this);
        meTv = findViewById(R.id.tv_me);
        meTv.setOnClickListener(this);
    }

    // 点击事件监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_main:// 首页
                setTabSelection(0);
                break;
            case R.id.tv_classify:// 分类
                setTabSelection(1);
                break;
            case R.id.tv_message:// 消息
                setTabSelection(2);
                break;
            case R.id.tv_me:// 我的
                setTabSelection(3);
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。 每个tab页对应的下标。0表示首页，1表示分类，2表示消息，3表示我的
     */
    private void setTabSelection(int index) {
        clearSelection();
        // 开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // 先隐藏所有Fragment
        hideFragments(fragmentTransaction);
        switch (index) {
            case 0:// 首页
                selectedSelection(0);
                if (mainFragment == null) {
                    // 如果Fragment为空，则创建一个并添加到界面上
                    mainFragment = new MainFragment();
                    fragmentTransaction.add(R.id.content, mainFragment);
                } else {
                    // 如果Fragment不为空，则直接将它显示出来
                    fragmentTransaction.show(mainFragment);
                }
                break;
            case 1:// 分类
                selectedSelection(1);
                if (classifyFragment == null) {
                    classifyFragment = new ClassifyFragment();
                    fragmentTransaction.add(R.id.content, classifyFragment);
                } else {
                    fragmentTransaction.show(classifyFragment);
                }
                break;
            case 2:// 消息
                selectedSelection(2);
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.content, messageFragment);
                } else {
                    fragmentTransaction.show(messageFragment);
                }
                break;
            case 3:// 我的
                selectedSelection(3);
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    fragmentTransaction.add(R.id.content, meFragment);
                } else {
                    fragmentTransaction.show(meFragment);
                }
                break;
        }
        // 提交
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 清除掉所有的选中状态
     */
    private void clearSelection() {
        mainTv.setTextColor(getResources().getColor(R.color.colorDefault));
        classifyTv.setTextColor(getResources().getColor(R.color.colorDefault));
        messageTv.setTextColor(getResources().getColor(R.color.colorDefault));
        meTv.setTextColor(getResources().getColor(R.color.colorDefault));
    }

    /**
     * 设置选中项样式
     */
    private void selectedSelection(int index) {
        switch (index) {
            case 0:
                mainTv.setTextColor(getResources().getColor(R.color.colorTitle));
                break;
            case 1:
                classifyTv.setTextColor(getResources().getColor(R.color.colorTitle));
                break;
            case 2:
                messageTv.setTextColor(getResources().getColor(R.color.colorTitle));
                break;
            case 3:
                meTv.setTextColor(getResources().getColor(R.color.colorTitle));
                break;
        }
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mainFragment != null) {
            transaction.hide(mainFragment);
        }
        if (classifyFragment != null) {
            transaction.hide(classifyFragment);
        }
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);
        }
    }
}
