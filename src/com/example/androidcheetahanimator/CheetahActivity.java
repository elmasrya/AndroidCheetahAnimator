package com.example.androidcheetahanimator;

/*
 * Course: CS 2302
 * Section: 01
 * Name: Andrew El-Masry
 * Professor: Dr. Shaw
 * Assignment #: Lab 18
 */

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CheetahActivity extends Activity {
	
	private ImageView cheetahView;
	private Button slowerBtn;
	private Button fasterBtn;
	private Button reverseBtn;
	private Button pauseBtn;
	
	private int [] rightImages = { 
			  R.drawable.rcheetah1, R.drawable.rcheetah2,
              R.drawable.rcheetah3, R.drawable.rcheetah4,
              R.drawable.rcheetah5, R.drawable.rcheetah6,
              R.drawable.rcheetah7, R.drawable.rcheetah8 };
	
    private int [] leftImages = {
		      R.drawable.lcheetah1, R.drawable.lcheetah2,
              R.drawable.lcheetah3, R.drawable.lcheetah4,
              R.drawable.lcheetah5, R.drawable.lcheetah6,
              R.drawable.lcheetah7, R.drawable.lcheetah8 };
    
    private Timer myTimer;
    private TimerTask myTimerTask;
    
    private int cheetahNum = 0;
    private boolean isRight = true;
    private boolean isPaused = false;
    private int delay = 40;
   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheetah);
		
		cheetahView = (ImageView) findViewById(R.id.cheetahView);
		
		slowerBtn = (Button) findViewById(R.id.slowerBtn);
		slowerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	delay += 20;
                	myTimerTask.cancel();
                    myTimerTask = new TimerTask() {
                       @Override
                       public void run() {
                          TimerMethod();
                       }
                    };
                    myTimer.schedule(myTimerTask, 0, delay);
                }
        });

		fasterBtn = (Button) findViewById(R.id.fasterBtn);
		fasterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	if (delay > 20) {
                		delay -= 20;
                		myTimerTask.cancel();
                	    myTimerTask = new TimerTask() {
                	         @Override
                	         public void run() {
                	            TimerMethod();
                	         }
                	    };
                	    myTimer.schedule(myTimerTask, 0, delay);
                	}
                }
        });

		reverseBtn = (Button) findViewById(R.id.reverseBtn);
		reverseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	isRight = !isRight;
                }
        });

		pauseBtn = (Button) findViewById(R.id.pauseBtn);
		pauseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	isPaused = !isPaused;
                }
        });
		
		myTimer = new Timer(); 
	      myTimerTask = new TimerTask() {
	         @Override
	         public void run() {
	            TimerMethod();
	         }
	    };
	    myTimer.schedule(myTimerTask,0,delay);
		
	}
	
	private void TimerMethod() { 
        this.runOnUiThread(Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            // Do your timer tasks here
        	if (!isPaused)
        		++cheetahNum;
        	if (cheetahNum >= 8)
        		cheetahNum = 0;
        	if (isRight)
        	    cheetahView.setImageResource(rightImages[cheetahNum]);
        	else
        		cheetahView.setImageResource(leftImages[cheetahNum]);
        }
    };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cheetah, menu);
		return true;
	}
}