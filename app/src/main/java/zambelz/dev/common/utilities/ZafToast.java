package zambelz.dev.common.utilities;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class ZafToast {

	public static void showShort(Activity activity, int messages) {
		Toast.makeText(activity, messages, Toast.LENGTH_SHORT).show();
	}
	
	public static void showShort(Context context, int messages) {
		Toast.makeText(context, messages, Toast.LENGTH_SHORT).show();
	}
	
	public static void showLong(Activity activity, int messages) {
		Toast.makeText(activity, messages, Toast.LENGTH_LONG).show();
	}
	
	public static void showLong(Context context, int messages) {
		Toast.makeText(context, messages, Toast.LENGTH_LONG).show();
	}
	
	public static void showShort(Activity activity, String messages) {
		Toast.makeText(activity, messages, Toast.LENGTH_SHORT).show();
	}
	
	public static void showShort(Context context, String messages) {
		Toast.makeText(context, messages, Toast.LENGTH_SHORT).show();
	}
	
	public static void showLong(Activity activity, String messages) {
		Toast.makeText(activity, messages, Toast.LENGTH_LONG).show();
	}
	
	public static void showLong(Context context, String messages) {
		Toast.makeText(context, messages, Toast.LENGTH_LONG).show();
	}
	
}