package com.sourcerebels.spacejunkie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Canvas;

public class StarsField {

	private List<Star> stars = new ArrayList<Star>();
	
	public void ondDraw(Canvas canvas) {

		boolean newStar = new Random().nextInt(20) < 2;
		if (newStar) {
			
			stars.add(new Star(canvas.getWidth()));
			
		}
		
		for (Star star : stars) {
			star.onDraw(canvas);
		}

		for (int i = 0; i < stars.size(); i++) {
			if (!stars.get(i).isValid()) {
				stars.remove(i);
			}
		}
	}
}
