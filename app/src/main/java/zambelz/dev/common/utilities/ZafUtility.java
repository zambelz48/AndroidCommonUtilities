package zambelz.dev.common.utilities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ZafUtility {
	
	private final static String TAG = "ZambelzUtility";

	public static void Fullscreen(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	public static void NoTitle(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	public static Date GetToday() {
		return Calendar.getInstance().getTime();
	}

	@SuppressLint("InlinedApi")
	public static void killAutomaticKeyboardShow(Activity activity) {
		activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}

	@SuppressLint("SimpleDateFormat")
	public static String DateToString(Date dt, String Format) {
		SimpleDateFormat df = new SimpleDateFormat(Format);
		return df.format(dt);
	}

	@SuppressLint("SimpleDateFormat")
	@SuppressWarnings("deprecation")
	public static Date StringToDate(String sDate, String dateFormat) {
		Date date = null;
		
		try {
			DateFormat formatter = new SimpleDateFormat(dateFormat);
			date = formatter.parse(sDate);
		} catch (Exception e) {
			date = new Date(1900, 1, 1);
		}
		
		return date;
	}

	@SuppressLint("SimpleDateFormat")
	public static String convertFormatDate(String date, String from_format, String to_format) {
		SimpleDateFormat inputFormat = null;
		SimpleDateFormat outputFormat = null;
		Date formattedDate = null;
		
		try {
			inputFormat 	= new SimpleDateFormat(from_format);
			outputFormat	= new SimpleDateFormat(to_format);
			formattedDate 	= inputFormat.parse(date);
		} catch(Exception e) {
			Log.e(TAG, e.getMessage());
		}
		
		return outputFormat.format(formattedDate);
	}

	public static int removeIntegerComma(String value) {
		return Integer.parseInt(value.replaceAll(",", ""));
	}

	public static double removeDoubleComma(String value) {
		return Double.parseDouble(value.replaceAll(",", ""));
	}

	@SuppressLint("InlinedApi")
	public static void showKeyboard(EditText editText, final Activity activity) {
		editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
		    @Override
			public void onFocusChange(View v, boolean hasFocus) {
		        if (hasFocus) {
		        	activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		        }
		    }
		});
		
		editText.requestFocus();
	}

	@SuppressLint("InlinedApi")
	public static void showKeyboardOnDialog(EditText editText, final Dialog dialog) {
		editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
		    @Override
			public void onFocusChange(View v, boolean hasFocus) {
		        if (hasFocus) {
		        	dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		        }
		    }
		});
		
		editText.requestFocus();
	}

	public static void datePicker(final EditText et, Context context) {
		Calendar mcurrentDate = Calendar.getInstance();

		int Year = mcurrentDate.get(Calendar.YEAR);
		int Month = mcurrentDate.get(Calendar.MONTH);
		int Day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog mDatePicker = new DatePickerDialog(context, new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
				et.setText(Integer.toString(selectedday) +"/"+ Integer.toString(selectedmonth + 1) + "/"
						  + Integer.toString(selectedyear));
			}
		}, Year, Month, Day);

		mDatePicker.setTitle("Pilih tanggal");
		mDatePicker.show();
	}

	public static void decodeFile(File file) {
		Bitmap bitmap = null;
		
		try {
			// Decode image size
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;

			FileInputStream fileInputStream = new FileInputStream(file);
			BitmapFactory.decodeStream(fileInputStream, null, options);
			fileInputStream.close();

			int scale = 1;
			
			if (options.outHeight > 500 || options.outWidth > 500) {
				scale = (int) Math.pow(2, (int) Math.round(Math.log(500 / (double) Math.max(
								options.outHeight, options.outWidth)) / Math.log(0.5)));
			}

			// Decode with inSampleSize
			BitmapFactory.Options options2 = new BitmapFactory.Options();
			options2.inSampleSize = scale;
			
			fileInputStream = new FileInputStream(file);
			bitmap = BitmapFactory.decodeStream(fileInputStream, null, options2);
			fileInputStream.close();
			
			FileOutputStream output = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, output);
			
			output.flush();
			output.close();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	@SuppressLint("InlinedApi")
	public static String uploadFile(String path) {
		String sourceFileUri = path;
		File file = new File(sourceFileUri);
		ByteArrayOutputStream objByteArrayOS = null;
		FileInputStream objFileIS = null;
		boolean isSuccess = false;
		String strAttachmentCoded = null;

		if (!file.isFile()) {
			Log.w(TAG, "Source File not exist :" + sourceFileUri);
		} else {
			try {
				objFileIS = new FileInputStream(file);
				objByteArrayOS = new ByteArrayOutputStream();
				byte[] byteBufferString = new byte[(int) file.length()];
				objFileIS.read(byteBufferString);

				byte[] byteBinaryData = Base64.encode(byteBufferString, Base64.DEFAULT);
				strAttachmentCoded = new String(byteBinaryData);
				
				isSuccess = true;
				
				//return strAttachmentCoded;
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("Upload file to server", "error: " + e.getMessage(), e);
			} finally {
				try {
					objByteArrayOS.close();
					objFileIS.close();
				} catch (IOException e) {	
					Log.e(TAG, "Error : "+ e.getMessage());
				}
			}
		}
		
		if(isSuccess) {
			return strAttachmentCoded;
		} else {
			return null;
		}
	}

	public static String removeCharAt(String s, int pos) {
		return s.substring(0, pos) + s.substring(pos + 1);
	}

	public static boolean backupDB(Context context, String backupFolder, String db_name) {
		boolean result = false;
		
		try {
			String current_date = DateToString(GetToday() , "dd-MM-yyyy");

			File data 	= Environment.getDataDirectory();
			File sdcard	= new File(Environment.getExternalStorageDirectory(), backupFolder +"/");
			sdcard.mkdirs();
			
			if (sdcard.canWrite()) {
				String currentDBPath	= "//data//" + context.getPackageName() + "//databases//" + db_name + "";
				String backupDBPath 	= "backup_" + db_name +"_"+ current_date + ".db";

				File currentDB	= new File(data, currentDBPath);
				File backupDB 	= new File(sdcard, backupDBPath);

				if(currentDB.exists()) {
					InputStream input 	= new FileInputStream(currentDB);
					OutputStream output	= new FileOutputStream(backupDB);

					byte[] buffer = new byte[1024];
					int length;

					while ((length = input.read(buffer)) > 0) {
						output.write(buffer, 0, length);
					}

					output.flush();
					output.close();
					input.close();
					
					result = true;
				} 
			} 
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		
		return result;
	}

	public static String formatNumber(double value, int min_fraction_digit, int max_fraction_digit) {
		NumberFormat nf;
		nf = NumberFormat.getInstance();
		
		if(min_fraction_digit != 0) {
			nf.setMinimumFractionDigits(min_fraction_digit);
		}
		
		if(max_fraction_digit != 0) {
			nf.setMaximumFractionDigits(max_fraction_digit);
		}
		
		return nf.format(value);
	}

	public static String ID_DayName() {
		Calendar cal 		= Calendar.getInstance();
		int dayOfWeek 		= cal.get(Calendar.DAY_OF_WEEK);
		String weekDayName 	= "";
		
		if (Calendar.MONDAY == dayOfWeek) {
			weekDayName = "Senin";
		} else if (Calendar.TUESDAY == dayOfWeek) {
			weekDayName = "Selasa";
		} else if (Calendar.WEDNESDAY == dayOfWeek) {
			weekDayName = "Rabu";
		} else if (Calendar.THURSDAY == dayOfWeek) {
			weekDayName = "Kamis";
		} else if (Calendar.FRIDAY == dayOfWeek) {
			weekDayName = "Jumat";
		} else if (Calendar.SATURDAY == dayOfWeek) {
			weekDayName = "Sabtu";
		} else if (Calendar.SUNDAY == dayOfWeek) {
			weekDayName = "Minggu";
		}
		
		return weekDayName;
	}

	public static String expToDecimal(String value) {
		return BigDecimal.valueOf(Double.valueOf(value)).toPlainString();
	}

	public static String expToDecimal(double value) {
		return BigDecimal.valueOf(value).toPlainString();
	}

	public static String getSelectedRadioText(Activity activity, RadioGroup rg) {
		int selectedRadioProtocol = rg.getCheckedRadioButtonId();
		RadioButton rbTextSelected = (RadioButton) activity.findViewById(selectedRadioProtocol);
		
		return rbTextSelected.getText().toString();
	}
	
	public static String getSelectedRadioText(Dialog dialog, RadioGroup rg) {
		int selectedRadioProtocol = rg.getCheckedRadioButtonId();
		RadioButton rbTextSelected = (RadioButton) dialog.findViewById(selectedRadioProtocol);
		
		return rbTextSelected.getText().toString();
	}

	public static void setSelectedRadioButton(String args, String[] val, RadioButton[] rb) {
		try {
			if(val.length == rb.length) {
				for(int i=0; i<val.length; i++) {
					if(args.equalsIgnoreCase(val[i])) {
						rb[i].setChecked(true);
					}
				}
			}
		} catch(Exception e) {
			Log.e("ZambelzUtility", "setSelectedRadioButton : "+ e.getMessage());
		}
	}
	
	public static String getIMEI(Context context) {
		return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
	}
	
	public static String getIMEI(Activity activity) {
		return ((TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
	}

	public static void setTimerReceiver(Context context, int repeat_time, Class<?> class_name) {
		AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intentReceiver = new Intent(context, class_name);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentReceiver, PendingIntent.FLAG_CANCEL_CURRENT);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, repeat_time/1000);
		
		// InexactRepeating allows Android to optimize the energy consumption
		alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), repeat_time, pendingIntent);
	}
	
	@SuppressWarnings("deprecation")
	public static int getScreenWidth(Activity activity) {
		int Measuredwidth = 0;
		Point size = new Point();
		WindowManager wm = activity.getWindowManager();
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			wm.getDefaultDisplay().getSize(size);
			Measuredwidth = size.x;
		} else {
			Display d = wm.getDefaultDisplay();
			Measuredwidth = d.getWidth();
		}
		
		return Measuredwidth;
	}
	
	@SuppressWarnings("deprecation")
	public static int getScreenHeight(Activity activity) {
		int Measuredheight = 0;
		Point size = new Point();
		WindowManager wm = activity.getWindowManager();
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			wm.getDefaultDisplay().getSize(size);
			Measuredheight = size.y;
		} else {
			Display d = wm.getDefaultDisplay();
			Measuredheight = d.getWidth();
		}
		
		return Measuredheight;
	}
}
