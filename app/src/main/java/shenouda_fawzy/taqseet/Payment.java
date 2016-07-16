package shenouda_fawzy.taqseet;

/**
 * Created by Amazing on 7/16/2016.
 */
public class Payment {
    String date;
    String paid;

    public Payment(){}

    public Payment(String date, String paid) {
        this.date = date;
        this.paid = paid;
    }

    public String getDate() {
        return date;
    }

    public String getPaid() {
        return paid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
}
