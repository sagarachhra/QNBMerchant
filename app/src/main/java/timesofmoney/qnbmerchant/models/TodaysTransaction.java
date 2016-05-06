package timesofmoney.qnbmerchant.models;

/**
 * Created by pankajp on 11-04-2016.
 */
public class TodaysTransaction {

    protected String strName;
    protected String strCardNo;
    protected String strTranactionAmount;

    public String getName() {
        return strName;
    }

    public void setName(String strName) {
        this.strName = strName;
    }

    public String getCardNo() {
        return strCardNo;
    }

    public void setCardNo(String strCardNo) {
        this.strCardNo = strCardNo;
    }

    public String getTranactionAmount() {
        return strTranactionAmount;
    }

    public void setTranactionAmount(String strTranactionAmount) {
        this.strTranactionAmount = strTranactionAmount;
    }
}
