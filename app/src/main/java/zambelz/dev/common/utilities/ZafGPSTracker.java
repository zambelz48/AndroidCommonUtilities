package zambelz.dev.common.utilities;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class ZafGPSTracker extends Service implements LocationListener {

	private String TAG = "GPSTracker";
	private boolean isGPSEnabled 		= false;
	private boolean isNetworkEnabled	= false;
	private boolean canGetLocation 		= false;
	private Context context;
	private Location location;
	private double latitude;
	private double longitude;
	
	private static final long MIN_DISTANCES_CHANGE_FOR_UPDATES = 10;
	private static final long MIN_TIME_BW_UPDATES = 1000 * 10 * 1;
	
	private LocationManager locationManager;
	
	public ZafGPSTracker(Context context) {
		this.context = context;
		getLocation();
	}
	
	public Location getLocation() {
		try {
			locationManager 	= (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			isGPSEnabled 		= locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isNetworkEnabled 	= locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			
			if(isGPSEnabled) {
				canGetLocation = true;
				
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, 
						MIN_DISTANCES_CHANGE_FOR_UPDATES, this);
				
				if (locationManager != null) {
					location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

					if (location != null) {
						latitude = location.getLatitude();
						longitude = location.getLongitude();
					}
				}
			}
			
			if(isNetworkEnabled) {
				canGetLocation = true;
				
				locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, 
						MIN_DISTANCES_CHANGE_FOR_UPDATES, this);
				
				if (locationManager != null) {
					location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

					if (location != null) {
						latitude = location.getLatitude();
						longitude = location.getLongitude();
					}
				}
			}
		} catch(Exception e) {
			Log.e(TAG, "Error : "+e.getMessage());
		}
		
		return location;
	}
	
	public void stopUsingGPS() {
		if(locationManager != null) {
			locationManager.removeUpdates(ZafGPSTracker.this);
		}
	}
	
	public double getLatitude() {
		if(location != null) {
			latitude = location.getLatitude(); 
		}
		
		return latitude;
	}
	
	public double getLongitude() {
		if(location != null) {
			longitude = location.getLongitude();
		}
		
		return longitude;
	}
	
	public boolean canGetLocation() {
		return canGetLocation;
	}
	
	public void showSettingsAlert() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setTitle("Caution !");
		alertDialog.setMessage("GPS is not enabled. do you want to enable it on the setting ?");
		alertDialog.setCancelable(false);
		alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
				
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				context.startActivity(intent);
			}
		});
		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		alertDialog.show();
	}
	
	@Override
	public void onLocationChanged(Location arg0) {

	}

	@Override
	public void onProviderDisabled(String arg0) {
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
}
