package wifi.wifim8;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

public class WiFiScanReceiver extends BroadcastReceiver {
  private static final String TAG = "WiFiScanReceiver";
  Wifim8Activity wifiDemo;

  public WiFiScanReceiver(Wifim8Activity wifiDemo) {
    super();
    this.wifiDemo = wifiDemo;
  }

  @Override
  public void onReceive(Context c, Intent intent) {
    List<ScanResult> results = wifiDemo.wifi.getScanResults();
    ScanResult bestSignal = null;
    wifiDemo.textStatus.setText("");
    int i=0;
    for (ScanResult result : results) {
    	wifiDemo.textStatus.append("\n\n" + i + ". " + result.toString());
    	i++;
      if (bestSignal == null
          || WifiManager.compareSignalLevel(bestSignal.level, result.level) < 0)
        bestSignal = result;
    }

    String message = String.format("%s networks found.",
        results.size());
    Toast.makeText(wifiDemo, message, Toast.LENGTH_LONG).show();

    Log.d(TAG, "onReceive() message: " + message);
  }

}
