package wifi.wifim8;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Wifim8Activity extends Activity {
    

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button startButton = (Button) findViewById(R.id.button1);
        
        startButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {				
				startButton.setText(new String("Started"));
			}
		});
    }
}