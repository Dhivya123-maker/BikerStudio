package com.example.bikerstudio;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.bikerstudio.utils.PreferenceUtils;

import pl.droidsonroids.gif.GifImageView;

public class SplashActivity extends AppCompatActivity {



    private final int SPLASH_TIME_OUT = 3350;
    Animation animation,bottomanim;
  ImageView imageView;
    LottieAnimationView lottieAnimationView;
    GifImageView gifImageView;
    VideoView videoView;
    PreferenceUtils preferenceUtils;
    MediaPlayer mySong;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This method is used so that your splash activity
        //can cover the entire screen.

        setContentView(R.layout.activity_splash);


        mySong=MediaPlayer.create(SplashActivity.this,R.raw.bike_trim);
        mySong.start();

        animation= AnimationUtils.loadAnimation(this,R.anim.animation);
        bottomanim=AnimationUtils.loadAnimation(this,R.anim.animation_2);
       imageView=findViewById(R.id.bike);


        gifImageView = findViewById(R.id.cycle);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(imageView,"translationX",-1000,0),
                ObjectAnimator.ofFloat(imageView,"alpha",0,1)

        );

        animatorSet.setDuration(2000);
        animatorSet.addListener(new AnimatorListenerAdapter(){
            @Override public void onAnimationEnd(Animator animation) {

                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playTogether(
                        ObjectAnimator.ofFloat(imageView,"scaleX", 1f, 1f,1f),
                        ObjectAnimator.ofFloat(imageView,"scaleY", 1f, 1f, 1f)
                );
                animatorSet2.setInterpolator(new AccelerateInterpolator());
                animatorSet2.setDuration(1000);
                animatorSet2.start();

            }
        });
        animatorSet.start();




        new Handler().postDelayed(new Runnable() {
            @Override

            public void run() {


                if(PreferenceUtils.getTokan(SplashActivity.this) == null){
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(SplashActivity.this,home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);

                }
//                Intent i = new Intent(SplashActivity.this,MainActivity.class);
//                startActivity(i);
//
//                finish();
                //the current activity will get finished.
            }
        }, SPLASH_TIME_OUT);





    }

}

