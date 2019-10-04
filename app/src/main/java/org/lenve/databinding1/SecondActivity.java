package org.lenve.databinding1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import org.lenve.databinding1.data.User;
import org.lenve.databinding1.databinding.ActivitySecondBinding;
import org.lenve.databinding1.ui.Base2Activity;
import org.lenve.databinding1.util.ActManager;

public class SecondActivity extends Base2Activity {
    private long firstBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySecondBinding activitySecondBinding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        findViewById(R.id.title_left_ll).setVisibility(View.GONE);
        activitySecondBinding.setUser(new User("http://img2.cache.netease.com/auto/2016/7/28/201607282215432cd8a.jpg", "张三"));
        activitySecondBinding.setTestClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.btn_test:
                        startActivity(new Intent(SecondActivity.this, MainActivity.class));
                        break;
                    case R.id.btn_test1:
                        startActivity(new Intent(SecondActivity.this, DayNewsActivity.class));
                        break;
                }
//                Toast.makeText(SecondActivity.this, "testClick", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondBack = System.currentTimeMillis();
            if (secondBack - firstBack > 2000) {
                Toast.makeText(this, "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                firstBack = secondBack;
                return true;
            } else {
                ActManager.getAppManager().AppExit(this);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
