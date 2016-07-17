package shenouda_fawzy.taqseet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerDetailActivity extends AppCompatActivity {

    CustomerDataSource dataSource;

    TextView customerNameTV;
    TextView customerPhoneTV;
    TextView totalCostTV;
    TextView itemNameTV;
    TextView restTV;

    EditText customerPayET;
    String customerPhone, customerName,customerItemName;
    float totalCost;
    float rest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // To disable rotate to landscape mode

        String custPhoneNumber = getIntent().getStringExtra(AllCustomerActivity.CUTOMER_PHONE);
        String custItemName = getIntent().getStringExtra(AllCustomerActivity.CUST_ITEM_NAME);
        //Log.i("LOG_TAG" , custPhoneNumber);


        dataSource = new CustomerDataSource(this);

        dataSource.openDataBase();
        Bundle bundle = dataSource.getCustomerDetails(custPhoneNumber , custItemName);
        rest = dataSource.getTotalPaid(custPhoneNumber,custItemName);
        dataSource.closeDatabase();

        customerPhone = bundle.getString("CUSTOMER_PHONE");
        customerName = bundle.getString("CUSTOMER_NAME");
        customerItemName = bundle.getString("CUST_ITEM_NAME");
        totalCost = bundle.getFloat("TOTAL_COST");

        customerNameTV = (TextView) findViewById(R.id.customerNameTV);
        customerPhoneTV = (TextView) findViewById(R.id.customerPhoneTV);
        totalCostTV = (TextView) findViewById(R.id.totalCostTV);
        itemNameTV = (TextView) findViewById(R.id.custItemName);
        restTV = (TextView) findViewById(R.id.restTV);
        customerPayET = (EditText) findViewById(R.id.payET);

        customerNameTV.setText(customerName);
        customerPhoneTV.setText(customerPhone);
        itemNameTV.setText(getResources().getText(R.string.itemName).toString()+ ": " + customerItemName);

        totalCostTV.setText(getResources().getText(R.string.totalCost).toString() + ": " + totalCost);
        restTV.setText(getResources().getText(R.string.totalPaid).toString() + ": " + rest);
    }

    public void payNow(View view) {
        String pa = customerPayET.getText().toString();

        if(!pa.isEmpty()) {
            float payment = Float.parseFloat(pa);
            dataSource.openDataBase();
            dataSource.addPayment(customerPhone, payment, customerItemName);
            rest = dataSource.getTotalPaid(customerPhone, customerItemName);
            restTV.setText(getResources().getText(R.string.totalPaid).toString() + ": " + rest);
            dataSource.closeDatabase();
            customerPayET.setText(""); // clear.
        }else
            Toast.makeText(this , "Plz Fill the required data", Toast.LENGTH_LONG).show();
    }

    public void allPayment(View view) {
        Intent intent = new Intent(this , AllPaymentActivity.class);
        intent.putExtra("CUSTOMER_PHONE", customerPhone);
        intent.putExtra("CUST_ITEM_NAME" , customerItemName);
        startActivity(intent);

    }
}
