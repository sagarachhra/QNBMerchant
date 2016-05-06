package timesofmoney.qnbmerchant.models;

/**
 * Created by pankajp on 4/28/2016.
 */
public class QNBNotofication {

    private String message,timestamp;


    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
