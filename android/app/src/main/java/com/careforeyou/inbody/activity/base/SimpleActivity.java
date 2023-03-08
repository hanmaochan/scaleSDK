package com.careforeyou.inbody.activity.base;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.careforeyou.inbody.R;


/**
 * Created by xulj on 2016/7/19.
 */
public class SimpleActivity extends FragmentActivity {
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    public void onFinish(View view) {
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }
    public void showToast(String text) {
       Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    public void showToast(int resId) {
        Toast.makeText(this,this.getString(resId),Toast.LENGTH_SHORT).show();
    }
}
