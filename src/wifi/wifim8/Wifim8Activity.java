package wifi.wifim8;

import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Wifim8Activity extends Activity implements OnClickListener {
	private static final String TAG = "WiFim8";
	WifiManager wifi;
	BroadcastReceiver receiver;
	boolean isRunning = false;

	TextView textStatus;
	Button buttonScan;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Setup UI
		textStatus = (TextView) findViewById(R.id.textView1);
		textStatus.setMovementMethod(new ScrollingMovementMethod());
		
		buttonScan = (Button) findViewById(R.id.button1);
		buttonScan.setOnClickListener(this);

		// Setup WiFi
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		// Register Broadcast Receiver
		if (receiver == null)
			receiver = new WiFiScanReceiver(this);		
		
	}

	@Override
	public void onStop() {
		unregisterReceiver(receiver);
	}

	public void onClick(View view) {
		boolean wifiStatus = (boolean) wifi.isWifiEnabled();
		if(wifiStatus){			
			Toast.makeText(this, "Scan Started",
					Toast.LENGTH_LONG).show();
			if (view.getId() == R.id.button1) {
				if(isRunning){
					isRunning = false;
					buttonScan.setText("Start");
				}else{
					isRunning = true;
					buttonScan.setText("Stop");
				}				
				startScan();								
			}
		}else{
			Toast.makeText(this, "Please enable Wifi in order to scan",
					Toast.LENGTH_LONG).show();		
		}		
	}	
	
	
	public void startScan(){
		Handler handlerTimer = new Handler();
		int interval = 10000;
		Log.d(TAG, "onClick() wifi.startScan()");
		wifi.startScan();
		registerReceiver(receiver, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		Log.d(TAG, "onCreate()");
		if(isRunning){
			handlerTimer.postDelayed(new Runnable(){
		        public void run() {
		        	startScan();
		      }}, interval);
		}
	}	

}