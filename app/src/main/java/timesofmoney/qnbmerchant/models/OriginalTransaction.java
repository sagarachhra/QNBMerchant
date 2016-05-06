package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Element;

/**
 * Created by pankajp on 5/3/2016.
 */
public class OriginalTransaction {
    @Element(required = false)
    private String Id,Amount,TxnDate,CardNumber;

    public String getId() {
        return Id;
    }

    public String getAmount() {
        return Amount;
    }

    public String getTxnDate() {
        return TxnDate;
    }

    public String getCardNumber() {
        return CardNumber;
    }
}
