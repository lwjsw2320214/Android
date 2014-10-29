package com.kugua.spirit;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

public class GameSpirit {

	private int spiritx, spirity, spirith, spiritw;

	private Paint paint;

	public int canvasx;

	public int canvasy;

	private int angle;

	public Matrix matrix;
	
	public Rect rect;
	
	private int bitlocation;

	//绘制动画
	public void CreatSpirit(Bitmap bitmap, Canvas canvas, int count,
			int randmx, int randmy) {

		RandmCoordinate(randmx, randmy);

		GameObjeTilt();

		//canvas.rotate(r);

		paint = new Paint();

		paint.setColor(Color.BLUE);

		spiritw = bitmap.getWidth() / 7;

		spirith = bitmap.getHeight();

		spiritx = spiritx + spiritw / 2;

		spirity = spirity + spirith / 2;

		ImgLocation(count);
		
		Bitmap dstbmp=Bitmap.createBitmap(bitmap,count * spiritw+3, bitlocation, spiritw-3, spirith,matrix,true);

		canvas.drawBitmap(dstbmp,canvasx,canvasy, null);
		
		rect=new Rect(canvasx,canvasy,canvasx+spiritw,canvasy+spirith);
		
		dstbmp=null;

	}
	
	//设置坐标
	private void RandmCoordinate(int randmx, int randmy) {

		if (canvasx == 0 && canvasy == 0) {
			
			Random random = new Random();

			canvasx = random.nextInt((randmx - 57));

			canvasy = random.nextInt(randmy - 87);

		}
	}

	//旋转角度
	private Matrix GameObjeTilt() {


		if (matrix == null) {

			matrix = new Matrix();

			Random random = new Random();

			angle = random.nextInt(365);

			matrix.postRotate(angle);
		}
		
		return matrix;

	}

	//游戏图片内容宽高
	private void ImgLocation(int count){
		
		switch (count) {
		case 0:
			bitlocation=0+28;
			spirith=spirith-28;
			break;
		case 1:
			bitlocation=0+28;
			spirith=spirith-28;
			break;
		case 2:
			bitlocation=0+28;
			spirith=spirith-28;
			break;
		case 3:
			bitlocation=0+42;
			spirith=spirith-42;
			break;
		case 4:
			bitlocation=0+6;
			spirith=spirith-6;
			break;
		case 5:
			spirith=spirith-18;
			break;
		case 6:
			spirith=spirith-36;
			break;
		}
	}
	
}
