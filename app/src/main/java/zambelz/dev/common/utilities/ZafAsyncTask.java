package zambelz.dev.common.utilities;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public abstract class ZafAsyncTask extends AsyncTask<String, Integer, Void> {

	private Context context;
	private ZafOnAsyncTaskCompleted task;
	
	protected ZafAsyncTask(Context context, ZafOnAsyncTaskCompleted task) {
		this.context = context;
		this.task = task;
	}

	protected ZafOnAsyncTaskCompleted task() {
		return task;
	}
	
	protected ZafProgressDialog progressBar() {
		return new ZafProgressDialog(getContext());
	}
	
	protected ZafErrorHandler errorHandler() {
		return new ZafErrorHandler();
	}
	
	protected Context getContext() {
		return context;
	}
	
	protected void finishContext() {
		((Activity) getContext()).finish();
	}
	
	private void errorData(String log_msg, String toast_msg, String sys_log_tag) {
		errorHandler().logMsg(log_msg);
		errorHandler().toastMsg(toast_msg);
		
		if(sys_log_tag != null) {
			Log.e(sys_log_tag, log_msg);
		}
	}
	
	protected void errorValue(int type, String log_msg, String toast_msg, String sys_log_tag) {
		errorHandler().status(true);
		errorHandler().type(type);
		errorData(log_msg, toast_msg, sys_log_tag);
	}
	
	protected void errorValue(String type, String log_msg, String toast_msg, String sys_log_tag) {
		errorHandler().status(true);
		errorHandler().type(type);
		errorData(log_msg, toast_msg, sys_log_tag);
	}
	
}