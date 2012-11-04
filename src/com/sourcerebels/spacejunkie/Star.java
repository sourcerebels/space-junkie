package com.sourcerebels.spacejunkie;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Star {
	
	private int x;
	private int y;
	private Paint paint;
	public boolean valid = true;
	
	public Star(int canvasWidth) {
		y = 0;
		x = new Random().nextInt(canvasWidth);
		paint = new Paint();
		paint.setColor(Color.WHITE);
	}
	
	public void onDraw(Canvas canvas) {
		y++;
		canvas.drawPoint(x, y, paint);
		canvas.drawPoint(x+1, y, paint);
		canvas.drawPoint(x-1, y, paint);
		canvas.drawPoint(x, y-1, paint);
		canvas.drawPoint(x, y+1, paint);
		
		if (y > canvas.getHeight()) {
			valid = false;
		}
	}
	
	public boolean isValid() {
		return valid;
	}
}
