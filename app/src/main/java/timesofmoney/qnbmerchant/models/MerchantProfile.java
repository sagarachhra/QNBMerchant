package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * Created by pankajp on 19-04-2016.
 */

public class MerchantProfile implements Serializable{


    @Element(required = false)
    String FirstName,LastName,MerchantId,MerchantName,CompanyName,BusinessDescription,MerchantType,CountryCode;
    @Element(required = false)
    String MerchantCategory,Address,City,AlternateAddress,Pincode,EmailId,MobileNumber,Fax,TaxIdentificationNumber,TradingLicenceNumber,MCC,TipFlag;


    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getMerchantId() {
        return MerchantId;
    }

    public String getMerchantName() {
        return MerchantName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getBusinessDescription() {
        return BusinessDescription;
    }

    public String getMerchantType() {
        return MerchantType;
    }

    public String getMerchantCategory() {
        return MerchantCategory;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getAlternateAddress() {
        return AlternateAddress;
    }

    public String getPincode() {
        return Pincode;
    }

    public String getEmailId() {
        return EmailId;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public String getFax() {
        return Fax;
    }

    public String getTaxIdentificationNumber() {
        return TaxIdentificationNumber;
    }

    public String getTradingLicenceNumber() {
        return TradingLicenceNumber;
    }

    public String getMCC() {
        return MCC;
    }

    public String getTipFlag() {
        return TipFlag;
    }

    public String getCountryCode() {
        return CountryCode;
    }
}
