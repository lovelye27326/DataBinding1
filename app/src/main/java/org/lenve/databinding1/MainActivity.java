package org.lenve.databinding1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.ImageView;

import org.lenve.databinding1.data.UserEntity;
import org.lenve.databinding1.databinding.ActivityMainBinding;
import org.lenve.databinding1.ui.Base2Activity;

public class MainActivity extends Base2Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ImageView titleimgLeft = (ImageView) findViewById(R.id.title_left);
        titleimgLeft.setImageResource(R.mipmap.top_back);
        UserEntity user = new UserEntity();
        user.setAge(34);
        user.setUsername("zhangsan");
        user.setNickname("张三");
        activityMainBinding.setUser(user);
    }
}
