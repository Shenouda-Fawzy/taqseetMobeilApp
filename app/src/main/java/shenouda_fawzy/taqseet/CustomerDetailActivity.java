package shenouda_fawzy.taqseet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerDetailActivity extends AppCompatActivity {

    CustomerDataSource dataSource;

    TextView customerNameTV;
    TextView customerPhoneTV;
    TextView totalCostTV;
    TextView restTV;

    EditText customerPayET;
    String customerPhone, customerName;
    float totalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);

        String custPhoneNumber = getIntent().getStringExtra(AllCustomerActivity.CUTOMER_PHONE);
        Log.i("LOG_TAG" , custPhoneNumber);


        dataSource = new CustomerDataSource(this);

        dataSource.openDataBase();
        Bundle bundle = dataSource.getCustomerDetails(custPhoneNumber);
        dataSource.closeDatabase();

        customerPhone = bundle.getString("CUSTOMER_PHONE");
        customerName = bundle.getString("CUSTOMER_NAME");
        totalCost = bundle.getFloat("TOTAL_COST");

        customerNameTV = (TextView) findViewById(R.id.customerNameTV);
        customerPhoneTV = (TextView) findViewById(R.id.customerPhoneTV);
        totalCostTV = (TextView) findViewById(R.id.totalCostTV);
        customerPayET = (EditText) findViewById(R.id.payET);

        customerNameTV.setText(customerName);
        customerPhoneTV.setText(customerPhone);
        totalCostTV.setText(String.valueOf(totalCost));

    }

    public void payNow(View view) {
        float payment = Float.parseFloat(customerPayET.getText().toString());
        dataSource.openDataBase();
        dataSource.addPayment(customerPhone, payment);
        dataSource.closeDatabase();
        customerPayET.setText(""); // clear.
    }

    public void allPayment(View view) {
        
    }
}
