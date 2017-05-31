package com.example.bikramkoju.navigationbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by Bikramkoju on 4/3/2017.
 */

public class PlayerActivity extends YouTubeBaseActivity{
    YouTubePlayerView youTubePlayerView;
    String api_key="AIzaSyBB9y9L5_2G5edLz9FGV3i2OKLB3daqelE";
    String id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerlayout);

        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.playerview);
        Bundle b1=getIntent().getExtras();
        id=b1.getString("videoid");
        youTubePlayerView.initialize(api_key, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(id);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}
