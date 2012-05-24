/*
 * Copyright (c) 2012 Jared González
 * 
 * Permission is hereby granted, free of charge, to any
 * person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice
 * shall be included in all copies or substantial portions of
 * the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
 * KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.java.ayatana;

public class Launcher {
	private static Launcher launcher = null;
	
	public static boolean initialize() {
		if (launcher == null) {
			if (!AyatanaLibrary.load())
				return false;
			GMainLoop.run();
			launcher = new Launcher(DesktopFile
					.getInstance().getLocalFile().getAbsolutePath());
		}
		return true;
	}
	public static Launcher getInstance() {
		if (launcher == null) {
			if (!initialize())
				throw new IllegalAccessError("Launcher can't initialized");
		}
		return launcher;
	}
	
	native private void initialize(String desktopfile);
	native private void setNativeCount(long count);
	native private void setNativeCountVisible(boolean visible);
	native private void setNativeProgress(double progress);
	native private void setNativeProgressVisible(boolean visible);
	native private void setNativeUrgent(boolean urgent);
	
	Launcher(String desktopFile) {
		initialize(desktopFile);
	}
	
	public synchronized void setCount(final long count) {
		setNativeCount(count);
	}
	native public long getCount();
	
	public synchronized void setCountVisible(final boolean visible) {
		setNativeCountVisible(visible);
	};
	native public boolean isCountVisible();
	
	public synchronized void setProgress(final double progress) {
		setNativeProgress(progress);
	}
	native public double getProgress();
	
	public synchronized void setProgressVisible(final boolean visible) {
		setNativeProgressVisible(visible);
	}
	native public boolean isProgressVisible();
	
	public synchronized void setUrgent(final boolean urgent) {
		setNativeUrgent(urgent);
	}
	native public boolean isUrgent();
}