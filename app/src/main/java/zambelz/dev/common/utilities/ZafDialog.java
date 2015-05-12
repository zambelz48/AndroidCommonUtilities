package zambelz.dev.common.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class ZafDialog {
	
	public Runnable ans_true 	= null;
	public Runnable ans_false	= null;
	
	public ZafDialog() {}
	
	public boolean Confirm(	Activity act, String Title, String ConfirmText,
							String CancelBtn, String OkBtn, 
							Runnable aProcedure, Runnable bProcedure ) {
		
		ans_true 	= aProcedure;
		ans_false	= bProcedure;
		
		AlertDialog dialog = new AlertDialog.Builder(act).create();
		
		dialog.setTitle(Title);
		dialog.setMessage(ConfirmText);
		dialog.setCancelable(false);
		
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, OkBtn, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int buttonId) {
				ans_true.run();
			}
		});
		
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, CancelBtn, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int buttonId) {
				ans_false.run();
			}
		});
		
		dialog.setIcon(android.R.drawable.ic_dialog_alert);
		
		dialog.show();
		
		return true;
	}
	
}
