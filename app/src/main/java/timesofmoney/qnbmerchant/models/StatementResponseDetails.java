package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by pankajp on 5/3/2016.
 */
public class StatementResponseDetails {

    @Element(required = false)
    String ErrorCode,Reason;
    @Element(required = false)
    String TotalRecords,Balance;


    @Element(name="Transactions",required = false)
    Transactions transactions;


    public String getErrorCode() {
        return ErrorCode;
    }

    public String getReason() {
        return Reason;
    }

    public String getTotalRecords() {
        return TotalRecords;
    }

    public String getBalance() {
        return Balance;
    }


    public Transactions getTransactions() {
        return transactions;
    }

    public static class Transactions
    {
        @ElementList(entry="Transaction", inline = true,required = false)
        List<Transaction> transactions;

        public List<Transaction> getTransactions() {
            return transactions;
        }
    }


}
