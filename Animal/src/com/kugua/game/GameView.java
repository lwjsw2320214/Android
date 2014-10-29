package com.kugua.game;

import java.util.ArrayList;

import com.kugua.canvas.CanvasBackground;
import com.kugua.spirit.GameSpirit;
import com.kugua.spirit.SpiritScribing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable,
		SurfaceHolder.Callback {

	private Thread gameThread;

	private boolean run_flag = false;

	private static int screen_height, screen_width;

	private Bitmap bitmapbg;

	private Bitmap bitmapbgtwo;

	private Canvas canvas;

	private SurfaceHolder holder;

	private Paint paint;

	private Bitmap readSpirit;

	private Bitmap clickSpirit;

	private Path path;

	private Point point = new Point();

	private ArrayList<GameSpirit> list = new ArrayList<GameSpirit>();

	private SpiritScribing spiritScribing;

	public GameView(Context context) {
		super(context);

		holder = this.getHolder();

		holder.addCallback(this);

		this.setKeepScreenOn(true);

		this.setFocusable(true);

		path = new Path();

		paint = new Paint();

		paint.setAntiAlias(true);

		paint.setColor(Color.RED);

		paint.setStrokeWidth(1);

		spiritScribing = new SpiritScribing();

		readSpirit = BitmapFactory.decodeResource(getResources(),
				R.drawable.readmonster);

		clickSpirit = BitmapFactory.decodeResource(getResources(),
				R.drawable.click);

		bitmapbg = BitmapFactory
				.decodeResource(getResources(), R.drawable.back);

		bitmapbgtwo = BitmapFactory.decodeResource(getResources(),
				R.drawable.bg2);
	}

	@Override
	public void run() {

		int count = 0;

		int bg = 0;

		for (int i = 0; i < 10; i++) {

			GameSpirit gameSpirit = new GameSpirit();

			list.add(gameSpirit);

		}

		// 动态画图
		DrawView(count, bg);

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

		screen_height = getHeight();

		screen_width = getWidth();

		run_flag = true;

		gameThread = new Thread(this);

		gameThread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

		run_flag = false;

	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			point.x = (int) event.getX();

			point.y = (int) event.getY();

			path.moveTo(point.x, point.y);

			spiritScribing.ClickSpirit(list, point);

			break;
		case MotionEvent.ACTION_UP:

			spiritScribing.ClickUpSpirit(list, point);

			path.reset();

			break;

		case MotionEvent.ACTION_MOVE:

			int movex = (int) event.getX();

			int movey = (int) event.getY();

			spiritScribing.DrawConnector(canvas, path, point, movex, movey);

			break;

		}
		return true;

	}

	private void DrawView(int count, int bg) {

		while (run_flag) {

			try {
				if (holder != null) {

					canvas = holder.lockCanvas();

					if (bg == 0) {

						CanvasBackground.CameraBackground(screen_width,
								screen_height, canvas, bitmapbg);
					} else {

						CanvasBackground.CameraBackground(screen_width,
								screen_height, canvas, bitmapbgtwo);
					}
					bg++;

					if (bg == 2) {
						bg = 0;
					}
					canvas.save();

					for (GameSpirit gs : list) {

						gs.CreatSpirit(readSpirit, canvas, count, screen_width,
								screen_height);
					}

					for (GameSpirit gs : spiritScribing.list) {

						spiritScribing
								.CreatClickSpirit(gs, canvas, clickSpirit);
					}
					canvas.restore();

				 Thread.sleep(200);

				}
			} catch (Exception ex) {
			} finally {
				if (canvas != null)
					holder.unlockCanvasAndPost(canvas);
			}

			count++;

			if (count > 6) {

				count = 0;
			}

		}
	}

}
