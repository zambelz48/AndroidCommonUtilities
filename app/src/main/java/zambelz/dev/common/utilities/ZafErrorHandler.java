package zambelz.dev.common.utilities;

public class ZafErrorHandler {

	private boolean error_status 		= false;
	private int num_error_type			= 0;
	private String string_error_type	= "";
	private String error_log_msg 		= "";
	private String error_toast_msg		= "";
	
	public void type(String error_type) {
		this.string_error_type = error_type;
	}
	
	public void type(int error_type) {
		this.num_error_type = error_type;
	}
	
	public void logMsg(String error_log_msg) {
		this.error_log_msg = error_log_msg;
	}
	
	public void toastMsg(String error_toast_msg) {
		this.error_toast_msg = error_toast_msg;
	}
	
	public void status(boolean error_status) {
		this.error_status = error_status;
	}
	
	public int getNumType() {
		return num_error_type;
	}
	
	public String getStringType() {
		return string_error_type;
	}
	
	public String getLogMsg() {
		return error_log_msg;
	}
	
	public String getToastMsg() {
		return error_toast_msg;
	}
	
	public boolean isError() {
		return error_status;
	}
	
}