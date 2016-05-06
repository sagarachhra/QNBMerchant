package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by pankajp on 5/3/2016.
 */
@Root(name="MpsXml")
public class StatementResponse {

    @Element(name="Header")
    Header header;

    @Element(name="Request")
    Request request;

    @Element(name="Response")
    Response response;

    @Element(name="ResponseDetails")
    StatementResponseDetails statementResponseDetails;

    public Header getHeader() {
        return header;
    }

    public Request getRequest() {
        return request;
    }

    public Response getResponse() {
        return response;
    }

    public StatementResponseDetails getStatementResponseDetails() {
        return statementResponseDetails;
    }
}
