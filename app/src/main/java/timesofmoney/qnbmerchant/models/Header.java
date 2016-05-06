package timesofmoney.qnbmerchant.models;

import org.simpleframework.xml.Element;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by kunalk on 1/29/2016.
 */
public class Header {
    @Element(required = false)
    String ChannelId="App",Timestamp="TestTime",SessionId="Test",ServiceProvider="toml";

    public Header()
    {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");

        Timestamp=sdf.format(calendar.getTime());

        SessionId= "";

    }

    public String getSessionId() {
        return SessionId;
    }

    public static String getRawXML(String reqType,String reqDetails)
    {
        return "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<MpsXml>\n" +
                "\t<Header>\n" +
                "\t\t<ChannelId>APP</ChannelId>\n" +
                "\t\t<Timestamp>{DD-MM-YYYY HH24:MM:SS}</Timestamp>\n" +
                "\t\t<SessionId>{SID}</SessionId>\n" +
                "\t\t<ServiceProvider>{SPID}</ServiceProvider>\n" +
                "\t</Header>\n" +
                "\t<Request>\n" +
                "\t\t<RequestType>"+reqType+"</RequestType>\n" +
                "\t\t<UserType>CU</UserType>\n" +
                "\t</Request>\n" +
                "\t<RequestDetails>\n" +
                    reqDetails+
                "\t\t<ResponseURL>{ResponseURL}</ResponseURL>\n" +
                "\t\t<ResponseVar>{ResponseVar}</ResponseVar>\n" +
                "\t</RequestDetails>\n" +
                "</MpsXml>";
    }
}
