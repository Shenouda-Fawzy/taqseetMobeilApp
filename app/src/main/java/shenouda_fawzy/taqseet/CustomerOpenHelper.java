package shenouda_fawzy.taqseet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * Created by Amazing on 7/14/2016.
 */

// this class responsible for creating the database table structure.
public class CustomerOpenHelper extends SQLiteOpenHelper {

    public static final int VERSION_NUMBER = 1; // database version
    public static final String DATABSE_NAME = "TaqseetDB.db"; // database version
    public static final String LOG_TAG = "LOG_TAG";


// **************************  User table *************************************
    public static final String USER_TABLE = "user_t";
    public static final String COLUMN_USER_PHONE = "userPhone";
    public static final String COLUMN_USER_NAME = "userName";
    public static final String COLUMN_TOTAL_COST = "totalCost";

    public static final String TABLE_USER_CREATE =
            "CREATE TABLE IF NOT EXISTS " + USER_TABLE + " ( "
                +COLUMN_USER_PHONE + " VARCHAR(20) NOT NULL PRIMARY KEY, "
                +COLUMN_USER_NAME + " VARCHAR(256), "
                +COLUMN_TOTAL_COST + " FLOAT "
            + ");";

// ******************************  user_payment table ****************************
    public static final String USER_PAYMENT_TABLE = "userPayment";
    public static final String COLUMN_USER_PK= "pk";
    public static final String COLUMN_USER_PAID = "paid";
    public static final String COLUMN_USER_PAY_DATE = "payDate";

    public static final String TABLE_USER_PAYMENT_CREATE =
            "CREATE TABLE IF NOT EXISTS " + USER_PAYMENT_TABLE + "( "
                +COLUMN_USER_PK + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                +COLUMN_USER_PHONE + " varchar(20) NOT NULL, "
                +COLUMN_USER_PAID + " FLOAT NOT NULL, "
                +COLUMN_USER_PAY_DATE + " DATE, "
            +" FOREIGN KEY ( " + COLUMN_USER_PHONE+ " ) REFERENCES " + USER_TABLE + "( " + COLUMN_USER_PHONE + " ) ON UPDATE CASCADE ON DELETE CASCADE "
            + ");";

    public CustomerOpenHelper(Context context) { // creating database.
        super(context, DATABSE_NAME, null, VERSION_NUMBER);
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER_CREATE);
        //Log.i(LOG_TAG , "User table created");
        db.execSQL(TABLE_USER_PAYMENT_CREATE);
       //Log.i(LOG_TAG , "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS  " + TABLE_USER_PAYMENT_CREATE);
        //db.execSQL("DROP TABLE IF EXISTS  " + TABLE_USER_CREATE);
        //onCreate(db);
    }
}
