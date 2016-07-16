package shenouda_fawzy.taqseet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // To disable rotate to landscabe mode

        TelephonyManager mngr = (TelephonyManager) this.getSystemService(this.TELEPHONY_SERVICE);
        String imei = mngr.getDeviceId();
        if(!imei.equals("353118073744461")) // Mariam Fawzy
            finish();
        //Log.i("LOG_TAG" , "IMEI: "+imei);

    }

    public void displayAllCustomer(View view) {
        Intent intent = new Intent(this, AllCustomerActivity.class);
        startActivity(intent);
    }

    public void navigateAddCutomer(View view) {
        Intent intent = new Intent(this,AddCutomerActivity.class);
        startActivity(intent);
    }
}
