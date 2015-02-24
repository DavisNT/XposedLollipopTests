package lv.id.dm.xposedlollipoptests;

import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class XposedModule implements IXposedHookLoadPackage{
	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        if("lv.id.dm.xposedlollipoptests".equals(lpparam.packageName)) {
            XposedHelpers.findAndHookMethod("lv.id.dm.xposedlollipoptests.MainActivity", lpparam.classLoader, "method1", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param)
                        throws Throwable {
            		Log.i("XposedLTests", "Hooked method1 called");
                    return true;
                }
            });
            XposedHelpers.findAndHookMethod("lv.id.dm.xposedlollipoptests.MainActivity", lpparam.classLoader, "method2", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param)
                        throws Throwable {
            		Log.i("XposedLTests", "Hooked method2 called");
                    return true;
                }
            });
        }		
	}
}
