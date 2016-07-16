package shenouda_fawzy.taqseet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * Created by Amazing on 7/15/2016.
 */
public class CustomerDataSource {

    SQLiteOpenHelper dbHelper; // This is the connection object.
    SQLiteDatabase database; // This object responsible for database operations(INSERT , UPDATE , DELETE ... etc).


    public CustomerDataSource(Context context){
        dbHelper = new CustomerOpenHelper(context);
    }

    public void openDataBase(){
        database = dbHelper.getWritableDatabase();
    }

    public void closeDatabase(){
        database.close();
    }


    public void insertCustomer(Customer customer, float totalCost , float firsPay){
        String custName = customer.getCustomerName();
        String custPhone = customer.getPhonNumber();

        ContentValues values = new ContentValues(); // used for database insertion.
        values.put(CustomerOpenHelper.COLUMN_USER_NAME , custName);
        values.put(CustomerOpenHelper.COLUMN_USER_PHONE , custPhone);
        values.put(CustomerOpenHelper.COLUMN_TOTAL_COST , totalCost);

        database.insert(CustomerOpenHelper.USER_TABLE , null , values);
        addPayment(custPhone, firsPay);
    }

    public ArrayList<Customer> getAllCustomer(){
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        final String [] ALL_COLUMNS = {
                CustomerOpenHelper.COLUMN_USER_PHONE,
                CustomerOpenHelper.COLUMN_USER_NAME
        };

        Cursor resltQuery = database.query(CustomerOpenHelper.USER_TABLE,ALL_COLUMNS , null , null , null , null , null);

        if(resltQuery.getCount() > 0){
            while(resltQuery.moveToNext()){
                Customer customer = new Customer();
                String customerName = resltQuery.getString(resltQuery.getColumnIndex(CustomerOpenHelper.COLUMN_USER_NAME));
                String customerPhon = resltQuery.getString(resltQuery.getColumnIndex(CustomerOpenHelper.COLUMN_USER_PHONE));
                customer.setCustomerName(customerName);
                customer.setPhonNumber(customerPhon);
                customerArrayList.add(customer);
            }
        }
        return customerArrayList;
    }
    public Bundle getCustomerDetails(String custPhon){
        Bundle bundle = new Bundle();
        final String [] CUSTOMER_ALL = {
                CustomerOpenHelper.COLUMN_USER_NAME,
                CustomerOpenHelper.COLUMN_USER_PHONE,
                CustomerOpenHelper.COLUMN_TOTAL_COST
        };
        final String [] USER_PHONE = {custPhon};
        Cursor resultQuerey = database.query(CustomerOpenHelper.USER_TABLE,CUSTOMER_ALL,"userPhone = ?",USER_PHONE,null,null,null);

        if(resultQuerey.getCount() > 0){
            if(resultQuerey.moveToNext()){
                String customerName = resultQuerey.getString(resultQuerey.getColumnIndex(CustomerOpenHelper.COLUMN_USER_NAME));
                String customerPhon = resultQuerey.getString(resultQuerey.getColumnIndex(CustomerOpenHelper.COLUMN_USER_PHONE));
                float totalCost = resultQuerey.getFloat(resultQuerey.getColumnIndex(CustomerOpenHelper.COLUMN_TOTAL_COST));
                bundle.putString("CUSTOMER_NAME" , customerName);
                bundle.putString("CUSTOMER_PHONE" , customerPhon);
                bundle.putFloat("TOTAL_COST",totalCost);
            }
        }

        return bundle;
    }

    public void addPayment(String custPhone, float payment){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        ContentValues values = new ContentValues();
        values.put(CustomerOpenHelper.COLUMN_USER_PAY_DATE , date);
        values.put(CustomerOpenHelper.COLUMN_USER_PHONE , custPhone );
        values.put(CustomerOpenHelper.COLUMN_USER_PAID , payment );

        long id = database.insert(CustomerOpenHelper.USER_PAYMENT_TABLE , null, values);
        //Log.i("LOG_TAG" , "Row id: " + id);
    }

    public ArrayList<Payment> getAllPayment(String userPhone){
        ArrayList<Payment> paymentArrayList = new ArrayList<>();

        final String [] ALL_COLUMNS = {
                CustomerOpenHelper.COLUMN_USER_PAY_DATE,
                CustomerOpenHelper.COLUMN_USER_PAID
        };

        final String [] USER_PHONE = {userPhone};

        Cursor resltQuery = database.query(CustomerOpenHelper.USER_PAYMENT_TABLE,ALL_COLUMNS , "userPhone = ?" , USER_PHONE , null , null , null);
        if(resltQuery.getCount() > 0){
            while(resltQuery.moveToNext()){
                Payment payment = new Payment();
                String payDate = resltQuery.getString(resltQuery.getColumnIndex(CustomerOpenHelper.COLUMN_USER_PAY_DATE));
                String payValue = resltQuery.getString(resltQuery.getColumnIndex(CustomerOpenHelper.COLUMN_USER_PAID));
                payment.setDate(payDate);
                payment.setPaid(payValue);
                paymentArrayList.add(payment);
            }
        }

        return paymentArrayList;
    }

    public float getTotalPaid(String userPhone){

        float sum = 0.0f;

        final String [] SUMMITION = {
                "sum(paid)"
        };

        final String [] USER_PHONE = {userPhone};

        Cursor resltQuery = database.query(CustomerOpenHelper.USER_PAYMENT_TABLE,SUMMITION , "userPhone = ?" , USER_PHONE , null , null , null);
        if(resltQuery.getCount() > 0)
            if(resltQuery.moveToNext()){
                sum = resltQuery.getFloat(resltQuery.getColumnIndex("sum(paid)"));
            }

        return sum;
    }

    public void deleteCustomer(String phoneNumber){
        final String [] PHONE_NUMBER = {phoneNumber};
        database.delete(CustomerOpenHelper.USER_TABLE , "userPhone = ?" , PHONE_NUMBER);
    }
}
