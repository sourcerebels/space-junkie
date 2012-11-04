package com.sourcerebels.spacejunkie;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class Sprite {

	private Bitmap b;
	
	private Bitmap normalBitmap;
	private Bitmap acceleratingBitmap;
	
	

	private int x;
	private int y;
	
	private float xRate;
	private float yRate;

	public Sprite(Bitmap bitmap, Bitmap acceleratingBitmap) {
		b = bitmap;
		normalBitmap = bitmap;
		this.acceleratingBitmap = acceleratingBitmap;
		xRate = b.getWidth() / 2;
		yRate = b.getHeight() / 2;
	}

	public void onDraw(Canvas c) {
		c.drawBitmap(b, x - xRate, y - yRate, null);
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void accelerate(float accelerationX, float accelerationY, int maxX, int maxY) {
		
		if (accelerationY < 0) {
			b = acceleratingBitmap;
		} else {
			b = normalBitmap;
		}
		
		int nextX = (int) (x - accelerationX);
		int nextY = (int) (y + accelerationY);

		Log.d("EDU","nextX " + nextX + " xRate " + xRate + " maxX " + maxX);

		if (nextX > xRate && nextX < maxX) {
			x = nextX;
		}
		if(nextY > yRate && nextY < maxY) {
			y = nextY;
		}
	}

	public int getWidth() {
		return b.getWidth();
	}
	
	public int getHeight() {
		return b.getHeight();
	}
}
