package com.sonnyjack.roundprogressview;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sonnyjack.widget.progress.RoundProgressView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RoundProgressView mRoundProgressView;
    private TextView mTvProgress;
    private int mTime = 5 * 1000;

    private Handler mMainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainHandler = new Handler(Looper.getMainLooper());

        setContentView(R.layout.activity_main);

        initProgressView();
    }

    private void initProgressView() {
        mRoundProgressView = findViewById(R.id.rpv_widget_progress);
        mRoundProgressView.setOnClickListener(this);
        mTvProgress = findViewById(R.id.tv_widget_progress);
        mRoundProgressView.setIRoundProgressListener(new RoundProgressView.IRoundProgressListener() {
            @Override
            public void progress(float progress, float total) {
                if (progress >= total) {
                    mTvProgress.setText("完成");
                } else {
                    int value = (int) (progress / total * 100);
                    mTvProgress.setText(value + "%");
                }
            }
        });
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //mRoundProgressView.setImageBitmap(bitmap);
        //mRoundProgressView.setImageScale(0.4f);
        mRoundProgressView.setText("你好");
        //int mode = RoundProgressView.MODE_AUTO;
        int mode = RoundProgressView.MODE_UPDATE;
        mRoundProgressView.setMode(mode);
        if (mode == RoundProgressView.MODE_AUTO) {
            //mRoundProgressView.start();
            mRoundProgressView.start(mTime);
        } else {
            post(0);
        }
    }

    private void post(final float progress) {
        if (progress > 100) {
            return;
        }
        mRoundProgressView.updateProgress(progress);
        mMainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                post(progress + 100.0f / 100);
            }
        }, mTime / 100);
    }

    @Override
    public void onClick(View v) {

    }
}
