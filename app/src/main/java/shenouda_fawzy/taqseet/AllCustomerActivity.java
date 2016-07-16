package shenouda_fawzy.taqseet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllCustomerActivity extends AppCompatActivity {
    CustomerDataSource dataSource;
    ListView lv;
    ArrayList<Customer> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customer);

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
                Log.i("LOG_TAG",arrayList.get(position).getCustomerName());
            }
        });

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
