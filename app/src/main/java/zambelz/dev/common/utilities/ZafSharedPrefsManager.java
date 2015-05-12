package zambelz.dev.common.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class ZafSharedPrefsManager {

	private SharedPreferences prefs;
	
	public ZafSharedPrefsManager(Context context) {
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public void editor(String key, String value) {
		Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public void editor(String key, boolean value) {
		Editor editor = prefs.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	public void editor(String key, float value) {
		Editor editor = prefs.edit();
		editor.putFloat(key, value);
		editor.commit();
	}
	
	public void editor(String key, int value) {
		Editor editor = prefs.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	public void editor(String key, long value) {
		Editor editor = prefs.edit();
		editor.putLong(key, value);
		editor.commit();
	}
	
	public String getValue(String key, String defaultValue) {
		String default_value = "";
		
		if(defaultValue == null) {
			default_value = "";
		} else {
			default_value = defaultValue;
		}
		
		return prefs.getString(key,	default_value);
	}
	
	public static void loadDefaultValues(Context context, int[] xml) {
		for(int i = 0; i < xml.length; i++) {
			PreferenceManager.setDefaultValues(context, xml[i], true);
		}
	}
	
	public static void loadDefaultValues(Context context, int xml) {
		PreferenceManager.setDefaultValues(context, xml, true);
	}
	
}
