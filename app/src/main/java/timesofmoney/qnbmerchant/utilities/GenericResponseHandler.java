package timesofmoney.qnbmerchant.utilities;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kunalk on 4/18/2016.
 */
public class GenericResponseHandler {

    public static Map<String, String> handleResponse(String xml) {
        Map<String, String> responseMap = new HashMap<>();

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xml));
            int eventType = xpp.getEventType();
            String text = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {

                String tagname = xpp.getName();

                if (eventType == XmlPullParser.TEXT) {
                    text = xpp.getText();

                } else if (eventType == XmlPullParser.END_TAG) {
                    if (tagname.equalsIgnoreCase("ResponseType")) {
                        responseMap.put("ResponseType", text);
                    }
                    if (tagname.equalsIgnoreCase("Message"))
                        responseMap.put("Message", text);
                    if (tagname.equalsIgnoreCase("Reason"))
                        responseMap.put("Reason", text);
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return responseMap;
    }


}