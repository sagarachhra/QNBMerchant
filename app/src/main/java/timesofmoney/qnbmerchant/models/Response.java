package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Element;

/**
 * Created by kunalk on 1/29/2016.
 */
public class Response {

    @Element
  String ResponseType;

    public String getResponseType() {
        return ResponseType;
    }
}
