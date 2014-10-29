package com.kugua.canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class CanvasBackground {

	
	public static void CameraBackground(int width,int height,Canvas canvas,Bitmap bitmap){
		
	            //canvas.drawColor(Color.WHITE);
		
		Rect srcRect=new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
		
		Rect drRect=new Rect(0,0,width,height);
		
		//canvas.drawBitmap(bitmap, 0, 0, null);
		 
		 canvas.drawBitmap(bitmap,srcRect,drRect, null);
	            
	    }
}
