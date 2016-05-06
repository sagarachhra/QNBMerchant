package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by pankajp on 20-04-2016.
 */
public class SubMerchantResponseDetails {


    public SubMerchant getSubMerchant() {
        return subMerchant;
    }

    public void setSubMerchant(SubMerchant subMerchant) {
        this.subMerchant = subMerchant;
    }

    @Element(name = "SubUsers", required = false)
    SubMerchant subMerchant;


    @Element(required = false)
    String ErrorCode;
    @Element(required = false)
    String Reason;


    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }
}
