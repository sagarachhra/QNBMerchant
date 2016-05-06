package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by kunalk on 1/29/2016.
 */
@Root(name="MpsXml")
public class LoginResponse {

    @Element(name="Header")
    Header header;

    @Element(name="Request")
    Request request;

    @Element(name="Response")
    Response response;

    @Element(name="ResponseDetails")
    LoginResponseDetails responseDetails;

    public Response getResponse() {
        return response;
    }

    public LoginResponseDetails getResponseDetails() {
        return responseDetails;
    }

    public Header getHeader() {
        return header;
    }
}
