package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by pankajp on 20-04-2016.
 */
@Root(name="MpsXml")
public class SubMerchantResponse {

    @Element(name="Header")
    Header header;

    @Element(name="Request")
    Request request;

    @Element(name="Response")
    Response response;

    @Element(name="ResponseDetails")
    SubMerchantResponseDetails subMerchantResponseDetails;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public SubMerchantResponseDetails getSubMerchantResponseDetails() {
        return subMerchantResponseDetails;
    }

    public void setSubMerchantResponseDetails(SubMerchantResponseDetails subMerchantResponseDetails) {
        this.subMerchantResponseDetails = subMerchantResponseDetails;
    }




}
