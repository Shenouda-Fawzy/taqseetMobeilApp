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
 * Created by Amazing on 7/16/2016.
 */
public class PaymentArrayAdapter extends ArrayAdapter<Payment> {

    Context context;
    List<Payment> data;

    public PaymentArrayAdapter(Context context, int resource, List<Payment> objects) {
        super(context, resource, objects);
        this.context = context;
        this.data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Payment payment = data.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.payment_item , null);

        TextView date = (TextView) view.findViewById(R.id.paymentDateTVItem);
        TextView value = (TextView) view.findViewById(R.id.paymentValueTVItem);

        date.setText(payment.getDate());
        value.setText(payment.getPaid());

        return view;
    }
}
