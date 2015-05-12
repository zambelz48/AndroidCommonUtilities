package zambelz.dev.common.utilities;

import android.content.Context;

public abstract class ZafRESTAsyncTask extends ZafAsyncTask {

	protected ZafRESTUtility rest;
	
	protected ZafRESTAsyncTask(Context context, ZafOnAsyncTaskCompleted task) {
		super(context, task);
		
		rest = new ZafRESTUtility();
	}

}
