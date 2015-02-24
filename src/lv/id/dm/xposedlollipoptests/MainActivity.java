package lv.id.dm.xposedlollipoptests;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private boolean method1() {
		Log.i("XposedLTests", "Original method 1 called");
		return false;
	}

	private boolean method2() {
		// Bug1
		// This method will get inlined by ART

		// Bug2 (hard to reproduce)
		/* App crashes with something like:
			I/ActivityManager( 2446): Process lv.id.dm.xposedlollipoptests (pid 11033) has died
			I/Zygote  ( 1951): Process 11033 exited due to signal (11)
			W/ActivityManager( 2446): Force removing ActivityRecord{35967343 u0 lv.id.dm.xposedlollipoptests/.MainActivity t1002}: app died, no saved state
			W/WindowManager( 2446): Failed looking up window
			W/WindowManager( 2446): java.lang.IllegalArgumentException: Requested window android.view.ViewRootImpl$W@3e2ab333 does not exist
			W/WindowManager( 2446):         at com.android.server.wm.WindowManagerService.windowForClientLocked(WindowManagerService.java:8504)
			W/WindowManager( 2446):         at com.android.server.wm.WindowManagerService.windowForClientLocked(WindowManagerService.java:8495)
			W/WindowManager( 2446):         at com.android.server.wm.WindowManagerService.removeWindow(WindowManagerService.java:2604)
			W/WindowManager( 2446):         at com.android.server.wm.Session.remove(Session.java:186)
			W/WindowManager( 2446):         at android.view.ViewRootImpl.dispatchDetachedFromWindow(ViewRootImpl.java:2927)
			W/WindowManager( 2446):         at android.view.ViewRootImpl.doDie(ViewRootImpl.java:5397)
			W/WindowManager( 2446):         at android.view.ViewRootImpl$ViewRootHandler.handleMessage(ViewRootImpl.java:3230)
			W/WindowManager( 2446):         at android.os.Handler.dispatchMessage(Handler.java:102)
			W/WindowManager( 2446):         at android.os.Looper.loop(Looper.java:135)
			W/WindowManager( 2446):         at android.os.HandlerThread.run(HandlerThread.java:61)
			W/WindowManager( 2446):         at com.android.server.ServiceThread.run(ServiceThread.java:46)
		To reproduce: run the app, uncomment/comment code line below and run the app again (without reboot) to crash it
					  repeat the above several times
		Worst thing - the app this way does not always crash
		After reboot app will usually work
		*/
		// Log.i("XposedLTests", "Original method 2 called");
		return false;
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("XposedLTests", "Calling methods");
		((TextView)this.findViewById(R.id.hello_world)).setText(String.format("method1: %b\nmethod2: %b", method1(), method2()));
		Log.i("XposedLTests", "Called");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
