package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import timesofmoney.qnbmerchant.models.adaptermodels.Item;

/**
 * Created by pankajp on 5/3/2016.
 */
public class Transaction implements Item {

    @Attribute(required = false)
    String id,txnType,amount,type,txnDate,remark;

    @Element(name = "OtherDetails",required = false)
    OtherDetail otherDetail;

    public String getId() {
        return id;
    }

    public String getTxnType() {
        return txnType;
    }

    public String getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getTxnDate() {
        return txnDate;
    }

    public String getRemark() {
        return remark;
    }

    public OtherDetail getOtherDetail() {
        return otherDetail;
    }

    @Override
    public boolean isSectioned() {
        return false;
    }

    public static class OtherDetail
    {
        @Element(name = "Customer",required = false)
        public Customer customer;
        @Element(name = "Bank",required = false)
        public Bank bank;

        @Element(name = "OriginalTransaction",required = false)
        public OriginalTransaction originalTransaction;

        public Customer getCustomer() {
            return customer;
        }

        public Bank getBank() {
            return bank;
        }

        public OriginalTransaction getOriginalTransaction() {
            return originalTransaction;
        }
    }

}
