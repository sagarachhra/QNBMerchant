package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Element;

/**
 * Created by kunalk on 1/29/2016.
 */
public class LoginResponseDetails {

    @Element(required = false)
    String ErrorCode;
    @Element(required = false)
    String Reason;

    @Element(required = false)
    String ChangeMpin;

    @Element(required = false)
    MerchantProfile Profile;


    @Element(required = false)
    String ChangeTpin;

    @Element(required = false)
    String LastLogin;


    @Element(required = false,name="Wallet")
    Wallet wallet;

    public String getChangeTpin() {
        return ChangeTpin;
    }

    public String getReason() {
        return Reason;
    }



    public String getChangeMpin() {
        return ChangeMpin;
    }

    public MerchantProfile getProfile() {
        return Profile;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public Wallet getWallet() {
        return wallet;
    }


    public String getLastLogin() {
        return LastLogin;
    }




}
