package zambelz.dev.common.utilities;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class ZafKeyEvent {

	public static void keyEnterPressed(final EditText et, final ZafKeyEventListener listener) {
		et.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
				if(event.getAction() == KeyEvent.ACTION_DOWN) {
					if(keyCode == KeyEvent.KEYCODE_ENTER ) {
						listener.onKeyPressed();
					}
				}
				
				return false;
			}
			
		});
	}
	
	public interface ZafKeyEventListener {
		public void onKeyPressed();
	}
	
}