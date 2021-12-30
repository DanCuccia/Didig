package com.didig.main;

import android.app.Activity;
import android.os.Bundle;

/**
 * Application Entry Point
 * @author Dan Cuccia
 */
public class DidigActivity extends Activity 
{
	GLView 			mDidigView;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	
    	mDidigView = new GLView(this);
    	
        setContentView(mDidigView);
    }
}