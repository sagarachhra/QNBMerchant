package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Element;

/**
 * Created by pankajp on 5/3/2016.
 */
public class Bank {

    @Element(required = false)
    String BankName,AccountNumber;

    public String getBankName() {
        return BankName;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }
}
