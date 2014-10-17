package com.nanospark.cnc;

import android.app.Application;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.IOIOLooperProvider;
import ioio.lib.util.android.IOIOAndroidApplicationHelper;


public class CustomIOIO extends Application implements IOIOLooperProvider

{

	private final IOIOAndroidApplicationHelper helper_ = new IOIOAndroidApplicationHelper(
			this, this);

	protected void create()

	{

		helper_.create();

	}

	protected void destroy()

	{

		helper_.destroy();

	}

	protected void start()

	{

		helper_.start();

	}

	protected void stop()

	{

		helper_.stop();

	}

	protected void restart()

	{

		helper_.restart();

	}

	class Looper extends BaseIOIOLooper {

		@Override
		public void setup() throws ConnectionLostException {
			// setup DigitalOutputs, AnalogInputs etc here.
			

		}

		@Override
		public void loop() throws ConnectionLostException {

		}

	}

	@Override
	public IOIOLooper createIOIOLooper(String connectionType, Object extra) {
		return

		new Looper();
	}

}
