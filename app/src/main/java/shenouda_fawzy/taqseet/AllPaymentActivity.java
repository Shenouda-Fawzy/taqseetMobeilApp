package shenouda_fawzy.taqseet;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllPaymentActivity extends AppCompatActivity {

    CustomerDataSource dataSource;
    ListView lv;
    ArrayList<Payment> arrayList;
    String userPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_payment);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // To disable rotate to landscape mode

        dataSource = new CustomerDataSource(this);
        userPhone = getIntent().getStringExtra("CUSTOMER_PHONE");

        dataSource.openDataBase();
        arrayList = dataSource.getAllPayment(userPhone);
        dataSource.closeDatabase();

        lv = (ListView) findViewById(android.R.id.list);

        ArrayAdapter adapter = new PaymentArrayAdapter(this,0 , arrayList);
        lv.setAdapter(adapter);
    }
}
