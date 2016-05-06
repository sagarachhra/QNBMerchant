package timesofmoney.qnbmerchant.utilities;

import android.nfc.Tag;
import android.util.Log;

import timesofmoney.qnbmerchant.QNBMerchantApp;
import timesofmoney.qnbmerchant.models.MerchantProfile;

/**
 * Created by pankajp on 4/26/2016.
 */
public class QRCodeEncode {


    public static MerchantProfile getMerchant(){
      MerchantProfile mp=QNBMerchantApp.getMerchantProfile();
        return mp;
    }

    public boolean isAlphanumeric(String str){
        String temp = str;
        String s=temp.replaceAll("\\s+", "");
        if(s.matches("^[a-zA-Z0-9]*$")){
            return true;
        }

        return false;
    }

    public boolean isNumeric(String str){
        if(str.matches("[0-9]*")){
            return true;
        }

        return false;
    }

    public String getAscciValue(int length){
        switch(length){
            case 0: return "0";
            case 1: return "1";
            case 2: return "2";
            case 3: return "3";
            case 4: return "4";
            case 5: return "5";
            case 6: return "6";
            case 7: return "7";
            case 8: return "8";
            case 9: return "9";
            case 10: return "A";
            case 11: return "B";
            case 12: return "C";
            case 13: return "D";
            case 14: return "E";
            case 15: return "F";
            case 16: return "G";
            case 17: return "H";
            case 18: return "I";
            case 19: return "J";
            case 20: return "K";
            case 21: return "L";
            case 22: return "M";
            case 23: return "N";
            case 24: return "O";
            case 25: return "P";
            case 26: return "Q";
            case 27: return "R";
            case 28: return "S";
            case 29: return "T";
            case 30: return "U";
            case 31: return "V";
            case 32: return "W";
            case 33: return "X";
            case 34: return "Y";
            case 35: return "Z";
            default: return null;
        }

    }

    public boolean isAlpha(String str){
        if(str.matches("[a-zA-Z]*")){
            return true;
        }
        return false;
    }

    public boolean merchantIdCheck(){
        String merchantId = QNBMerchantApp.getMerchantProfile().getMerchantId();
        int merchantIdLength = merchantId.length();

        if(merchantIdLength>=8 && merchantIdLength<=16 && isNumeric(merchantId)){
          return true;
        }else {
            return false;
        }
    }

    public boolean merchantNameCheck(){
        String merchantName = QNBMerchantApp.getMerchantProfile().getMerchantName();
        int merchantNameLength = merchantName.length();

        if(isAlphanumeric(merchantName) && merchantNameLength<=25){
            return true;
        }

        return false;
    }

    public boolean merchantCCCheck(){
        String merchantCC = QNBMerchantApp.getMerchantProfile().getMCC();
        int merchantCCLength = merchantCC.length();

        if(isNumeric(merchantCC) && merchantCCLength==4){
            return true;
        }

        return false;
    }

    public boolean cityNameCheck(){
        String cityName = QNBMerchantApp.getMerchantProfile().getMerchantCategory();
        int cityNameLength = cityName.length();

        if(isAlphanumeric(cityName) && cityNameLength<=13){
            return true;
        }

        return false;
    }

    public boolean countryCodeCheck(){
        String countryCode = QNBMerchantApp.getMerchantProfile().getCountryCode();
        int countryCodeLength = countryCode.length();

        if(isAlpha(countryCode) && countryCodeLength==2){
            return true;
        }

        return false;
    }

   public String getMerchantQR(){

       String merchantQR = "";
       if(merchantIdCheck()){
           String merchantid = QRCodeEncode.getMerchant().getMerchantId();
           merchantQR="0"+getAscciValue(merchantid.length())+merchantid;
           Log.v("id",merchantid);
       }else
           return null;

       if(merchantNameCheck()){
           String merchantName = QRCodeEncode.getMerchant().getMerchantName();
           merchantQR = merchantQR+"1"+getAscciValue(merchantName.length())+merchantName;
           Log.v("name",merchantName);
       }else
           return null;

       if(merchantCCCheck()){
           String merchantCC = QRCodeEncode.getMerchant().getMCC();
           merchantQR = merchantQR +"2"+getAscciValue(merchantCC.length())+merchantCC;
       }else
           return null;

       if(cityNameCheck()){
           String cityName = QRCodeEncode.getMerchant().getCity();
           merchantQR = merchantQR+"3"+getAscciValue(cityName.length())+cityName;
       }else
        return null;

       if(countryCodeCheck()){
           String countryCode = QRCodeEncode.getMerchant().getCountryCode();
           merchantQR = merchantQR+"4"+getAscciValue(countryCode.length())+countryCode;
       }else
           return null;


       merchantQR= merchantQR+"5"+"3"+"111";

       return merchantQR;
   }

    public  String dynamicQR(String transactionAmount){

        String merchantQR = "";
        if(merchantIdCheck()){
            String merchantid = QRCodeEncode.getMerchant().getMerchantId();
            merchantQR="0"+getAscciValue(merchantid.length())+merchantid;
            Log.v("id",merchantid);
        }else
            return null;

        if(merchantNameCheck()){
            String merchantName = QRCodeEncode.getMerchant().getMerchantName();
            merchantQR = merchantQR+"1"+getAscciValue(merchantName.length())+merchantName;
            Log.v("name",merchantName);
        }else
            return null;

        if(merchantCCCheck()){
            String merchantCC = QRCodeEncode.getMerchant().getMCC();
            merchantQR = merchantQR +"2"+getAscciValue(merchantCC.length())+merchantCC;
        }else
            return null;

        if(cityNameCheck()){
            String cityName = QRCodeEncode.getMerchant().getCity();
            merchantQR = merchantQR+"3"+getAscciValue(cityName.length())+cityName;
        }else
            return null;

        if(countryCodeCheck()){
            String countryCode = QRCodeEncode.getMerchant().getCountryCode();
            merchantQR = merchantQR+"4"+getAscciValue(countryCode.length())+countryCode;
        }else
            return null;

        merchantQR= merchantQR+"5"+"3"+"111";

        if(isNumeric(transactionAmount) && transactionAmount.length()<=12){
            merchantQR= merchantQR+"6"+getAscciValue(transactionAmount.length())+transactionAmount;
        }

        return merchantQR;


    }





    }


