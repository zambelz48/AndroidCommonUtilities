package zambelz.dev.common.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.text.format.DateFormat;

@SuppressLint("WorldReadableFiles")
public class ZafLog {
	private String TAG;
	private String log_dir;
	private String current_date;
	private String time_created;
	private String file_name;
	private File logDir;
	
	public ZafLog(String TAG, String log_dir) {
		this.TAG 		= TAG;
		this.log_dir	= log_dir;
		
		current_date 	= String.valueOf(DateFormat.format("dd-MM-yyyy", Calendar.getInstance().getTime()));
		time_created 	= String.valueOf(DateFormat.format("hh:mm:ss", Calendar.getInstance().getTime()));
		file_name 		= "Log_"+ current_date +".log";
		
		logDir = new File(Environment.getExternalStorageDirectory(), this.log_dir);
		
		if(!logDir.exists()) {
			logDir.mkdirs();
		}
 	}
	
	public void writeLog(String log_text) {
		try {
			File logFile = new File(logDir, file_name);
			FileWriter writer = new FileWriter(logFile, true);
			writer.append("\n" + time_created + " | " + TAG + " => " + log_text);
	
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public final static void deleteLog(String file_name) {
		
	}
	
	public final static void clearLog() {
		
	}
}