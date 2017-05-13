package com.shangshow.showlive.common.utils;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.shangshow.showlive.R;

public class PlayVideoCommint extends AppCompatActivity {
    private CustomVideoView play_video_commint;
    private String videourl;
    private String urls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video_commint);
        Intent intent = getIntent();
        videourl = intent.getStringExtra("videourl");
        initView();
    }
    private void initView() {
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAI5amNhekmnXc7", "kjUf0WVCLBbpbIvKX3JYwhKuescvKq");
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);
        try {
            urls = oss.presignConstrainedObjectURL("video-showlive", "mp4-hd/3bb9d3bc5b724399827a0615cac64a9f/IMG_1440.mp4", 30 * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        play_video_commint = (CustomVideoView) findViewById(R.id.play_video_commint);
        //设置播放加载路径
        //网络视频
       String videoUrl2 =  urls;
       Uri uri = Uri.parse( videoUrl2 );
        play_video_commint.setVideoURI(uri);
        //播放
        play_video_commint.start();
        //循环播放
        play_video_commint.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                play_video_commint.start();
            }
        });

    }
}
