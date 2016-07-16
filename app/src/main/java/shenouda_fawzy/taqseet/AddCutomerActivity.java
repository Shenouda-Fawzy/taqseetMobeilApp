package shenouda_fawzy.taqseet;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddCutomerActivity extends AppCompatActivity {

    CustomerDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cutomer);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // To disable rotate to landscape mode

        //Toast.makeText(this,"Hello from second activity" , Toast.LENGTH_LONG).show();

        if(getSupportActionBar() != null) // for enabling  back <- button .
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataSource = new CustomerDataSource(this);
        dataSource.openDataBase();
        //Customer customer = new Customer("Shenoud Fawzy" , "01270262976");
        //dataSource.insertCustomer(customer , 500);
        dataSource.closeDatabase();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == android.R.id.home) // This to enable navigation to the parent activity.
            finish();


        return super.onOptionsItemSelected(item);
    }

    public void addNewCutomer(View view) {
        Customer customer = new Customer();
        EditText userName = (EditText) findViewById(R.id.userFullName);
        EditText userPhon = (EditText) findViewById(R.id.userPhoneNumber);
        EditText firsPay = (EditText) findViewById(R.id.firstPay);
        EditText userCost = (EditText) findViewById(R.id.totaCost);
        EditText itemName = (EditText) findViewById(R.id.itemName);

        String custName = userName.getText().toString();
        String custPhone = userPhon.getText().toString();
        String itemNa = itemName.getText().toString();

        float totalCost = Float.parseFloat(userCost.getText().toString());
        float firstPaymen = Float.parseFloat(firsPay.getText().toString());

        customer.setPhonNumber(custPhone);
        customer.setCustomerName(custName);
        customer.setItemName(itemNa);

        dataSource.openDataBase();
        dataSource.insertCustomer(customer , totalCost, firstPaymen);
        dataSource.closeDatabase();

        clearView(userCost , userName, userPhon , firsPay, itemName);
    }

    public static void clearView(View ... views){
        for(int i = 0 ; i < views.length ; i++){
            EditText et = (EditText)views[i];
            et.setText("");
        }
    }
}
