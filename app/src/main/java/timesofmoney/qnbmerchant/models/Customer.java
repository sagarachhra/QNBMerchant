package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Element;

/**
 * Created by pankajp on 5/3/2016.
 */
public class Customer {

    @Element(required = false)
    String FirstName,LastName,MobileNumber,CardNumber;

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public String getCardNumber() {
        return CardNumber;
    }
}
