package shenouda_fawzy.taqseet;

/*
 * Created by Amazing on 2/5/2016.
 */
public class Customer {
    String customerName;
    String phonNumber;

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhonNumber(String phonNumber) {
        this.phonNumber = phonNumber;
    }


    public Customer(){

    }

    public Customer(String customerName,  String phonNumber) {
        this.customerName = customerName;
        this.phonNumber = phonNumber;

    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhonNumber() {
        return phonNumber;
    }



    @Override
    public String toString() {
        return this.customerName;
    }
}
