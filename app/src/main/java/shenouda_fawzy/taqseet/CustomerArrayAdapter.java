package shenouda_fawzy.taqseet;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Amazing on 7/15/2016.
 */
public class CustomerArrayAdapter extends ArrayAdapter<Customer> {

    Context context;
    List<Customer> data;

    public CustomerArrayAdapter(Context context, int resource, List<Customer> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects; // this is the data to be putted in the list.
    }


// This method is called for every item in the list.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Customer customer = data.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view  = inflater.inflate(R.layout.cutomer_item , null);

        TextView custName = (TextView) view.findViewById(R.id.customerNameItem);
        TextView custPhone = (TextView) view.findViewById(R.id.customerPhoneItem);
        TextView custItem = (TextView) view.findViewById(R.id.itemNameTV);
        custName.setText(customer.getCustomerName());
        custPhone.setText(customer.getPhonNumber());
        custItem.setText(customer.getItemName());

        return view;
    }
}
