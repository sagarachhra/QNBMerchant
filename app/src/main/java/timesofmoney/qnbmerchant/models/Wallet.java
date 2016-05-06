package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Text;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kunalk on 4/14/2016.
 */
public class Wallet {

    @ElementList(entry = "WalletId", inline = true)
    List<WalletId> Wallet;

    public List<WalletId> getWallets() {
        return Wallet;
    }


    public static class WalletId implements Serializable {
        @Attribute
        public String AccountType, isPrimary, balance;//token,cardNumber,cardHolderName,WalletId;

        public String getValue() {
            return value;
        }

        @Text
        private String value;
       /* public String getToken() {
            return token;
        }*/

       /* public String getWalletId() {
            return WalletId;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public String getCardHolderName() {
            return cardHolderName;
        }*/


        public void setBalance(String balance) {
            this.balance = balance;
        }

        public void setIsPrimary(String isPrimary) {
            this.isPrimary = isPrimary;
        }

        public String getAccountType() {
            return AccountType;
        }


        public String getIsPrimary() {
            return isPrimary;
        }

        public String getBalance() {
            return balance;
        }
    }
}
