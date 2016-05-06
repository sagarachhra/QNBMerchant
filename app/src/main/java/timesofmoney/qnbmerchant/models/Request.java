package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Element;

/**
 * Created by kunalk on 1/29/2016.
 */
public class Request {

    @Element
    String RequestType="Test",UserType="AG";

    public Request(){}
    public Request(String requestType)
    {
        this.RequestType=requestType;
    }
}
