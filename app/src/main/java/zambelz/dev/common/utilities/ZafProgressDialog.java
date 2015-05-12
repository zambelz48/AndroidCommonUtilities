package zambelz.dev.common.utilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

public class ZafProgressDialog {

	private Activity activity;
	private Context context;
	private static ProgressDialog progressBar;

	public ZafProgressDialog(Activity activity) {
		this.activity = activity;
	}

	public ZafProgressDialog(Context context) {
		this.context = context;
	}

	private void setContextType() {
		if(this.activity == null) {
			progressBar = new ProgressDialog(this.context);
		} else {
			progressBar = new ProgressDialog(this.activity);
		}
	}

	public void setProgressDialog(String message) {
		setContextType();
		progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressBar.setCancelable(false);
		progressBar.setMessage(message);
	}

	public void setProgressDialog(boolean isCancelable, String message) {
		setContextType();
		progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressBar.setCancelable(isCancelable);
		progressBar.setMessage(message);
	}

	public void setProgressDialog(int setMax, boolean isCancelable, String message) {
		setContextType();
		progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressBar.setMax(setMax);
		progressBar.setCancelable(isCancelable);
		progressBar.setMessage(message);
	}
	
	public void showProgress(int progress) {
		progressBar.setProgress(progress);
	}

	public void show() {
		progressBar.show();
	}

	public void hide() {
		progressBar.hide();
	}

	public void dismiss() {
		progressBar.dismiss();
	}
	
}