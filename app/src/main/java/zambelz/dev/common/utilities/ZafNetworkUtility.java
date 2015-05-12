package zambelz.dev.common.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ZafNetworkUtility {

	private Context context;
	
	public ZafNetworkUtility(Context context) {
		this.context = context;
	}
	
	public boolean isConnected() {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		if(connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			
			if(info != null) {
				for(int i = 0; i < info.length; i++) {
					if(info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public void notConnectToastMsg(String msg, int duration) {
		Toast.makeText(context, msg, duration).show();
	}
	
	public void notConnectDialogMsg() {
		
	}
}
