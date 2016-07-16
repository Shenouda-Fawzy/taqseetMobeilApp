package shenouda_fawzy.taqseet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllCustomerActivity extends AppCompatActivity {
    public static final String CUTOMER_PHONE = "CUTOMER_PHONE";
    public static final String CUST_ITEM_NAME = "CUST_ITEM_NAME";
    CustomerDataSource dataSource;
    ListView lv;
    ArrayList<Customer> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // To disable rotate to landscape mode
        if(getSupportActionBar() != null) // for enabling  back <- button .
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // This list will be fill from the database(user table).
        dataSource = new CustomerDataSource(this);
        lv = (ListView) findViewById(android.R.id.list);
        dataSource.openDataBase();

        arrayList = dataSource.getAllCustomer(); // retrive all customer form the database.
        ArrayAdapter adapter = new CustomerArrayAdapter(this, 0 , arrayList);
        lv.setAdapter(adapter);
        dataSource.closeDatabase();

        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.i("LOG_TAG",arrayList.get(position).getCustomerName());
                Intent intent = new Intent(AllCustomerActivity.this,CustomerDetailActivity.class);
                intent.putExtra(CUTOMER_PHONE, arrayList.get(position).getPhonNumber());
                intent.putExtra(CUST_ITEM_NAME, arrayList.get(position).getItemName());

                startActivity(intent);
            }
        });

        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu , v , menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_item , menu);

    }

// This context menue is used for deletion purpose.
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //return super.onContextItemSelected(item);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int id = item.getItemId();
        if(id == R.id.deleteCust) {
            Customer customer = arrayList.get(info.position);
            dataSource.openDataBase();
            dataSource.deleteCustomer(customer.getPhonNumber(), customer.getItemName());
            arrayList = dataSource.getAllCustomer();
            ArrayAdapter adapter = new CustomerArrayAdapter(this, 0 , arrayList);
            lv.setAdapter(adapter);
            dataSource.closeDatabase();
            //Log.i("LOG_TAG", "CUST phone is" + customer.getPhonNumber());
        }
        return true;
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

}
