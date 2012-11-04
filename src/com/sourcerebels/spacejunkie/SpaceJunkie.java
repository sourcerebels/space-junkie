package com.sourcerebels.spacejunkie;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SpaceJunkie extends Activity implements SensorEventListener {

	private SpaceJunkieView v;
	private SensorManager sensorManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		v = new SpaceJunkieView(this);
		setContentView(v);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onPause() {
		super.onPause();
		v.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		v.resume();
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

			float accelerationX = event.values[0];
			float accelerationY = event.values[1];
			// float accelerationZ = event.values[0];
			v.accelerate(accelerationX, accelerationY);
		}
	}

	public class SpaceJunkieView extends SurfaceView implements Runnable {

		private Thread t;
		private SurfaceHolder holder;
		private boolean running = false;
		Sprite shuttle;
		StarsField stars;

		public SpaceJunkieView(Context context) {
			super(context);
			holder = getHolder();
			shuttle = new Sprite(BitmapFactory.decodeResource(getResources(),
					R.drawable.shuttle), BitmapFactory.decodeResource(
					getResources(), R.drawable.shuttle_accel));
			shuttle.setPosition(100, 500);
			stars = new StarsField();
		}

		public void accelerate(float accelerationX, float accelerationY) {
			shuttle.accelerate(accelerationX, accelerationY, getWidth() - shuttle.getWidth() / 2, getHeight() - shuttle.getHeight() / 2);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawARGB(255, 0, 0, 0);
			stars.ondDraw(canvas);
			shuttle.onDraw(canvas);
		}

		public void run() {
			while (running) {
				if (holder.getSurface().isValid()) {
					Canvas c = holder.lockCanvas();
					onDraw(c);
					holder.unlockCanvasAndPost(c);
				}
			}
		}

		public void pause() {
			running = false;
			while (true) {
				try {
					t.join();
				} catch (InterruptedException e) {
				}
				break;
			}
		}

		public void resume() {
			running = true;
			t = new Thread(this);
			t.start();
		}
	}
}
