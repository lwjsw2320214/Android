package com.kugua.spirit;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class SpiritScribing {

	public ArrayList<GameSpirit> list = new ArrayList<GameSpirit>();
	
	public ArrayList<GameSpirit> clickUpList = new ArrayList<GameSpirit>();

	//点击的时候使用
	public void ClickSpirit(ArrayList<GameSpirit> gsList, Point point) {

		// 查找出被点击的图片
		for (GameSpirit gs : gsList) {

			if (gs.rect.contains(point.x, point.y)) {

				list.add(gs);
			}
		}

		// 移除被点击的图片
		for (GameSpirit gs : list) {

			gsList.remove(gs);

		}

	}

	//点击无效的时候 使用
	public void ClickUpSpirit(ArrayList<GameSpirit> gsList,Point point){
		
		// 查找出被没有被连接起来的点击的图片
				for (GameSpirit gs : list) {

					if (gs.rect.contains(point.x, point.y)) {

						gsList.add(gs);
					}
				}
				
				// 移除被原有动画
				for (GameSpirit gs : list) {

					list.remove(gs);

				}
		
	}
	
	public void DrawConnector(Canvas canvas,Path path,Point point,int x, int y){

		 Paint paint=new Paint();
		 
		 paint.setColor(Color.BLUE);
		 
		 paint.setAntiAlias(true);

		 
		 canvas.drawLine(point.x, point.y, x, y, paint);
		
	}
	
	public void CreatClickSpirit(GameSpirit gs, Canvas canvas,Bitmap bitmap) {

		Bitmap dstbmp=Bitmap.createBitmap(bitmap,0, 0, bitmap.getWidth(), bitmap.getHeight(),gs.matrix,true);
		
		canvas.drawBitmap(dstbmp,gs.canvasx,gs.canvasy, null);
		
	}
}
