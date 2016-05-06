package timesofmoney.qnbmerchant.models;

import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by pankajp on 06-04-2016.
 */

public class SubMerchant {

    @ElementList(entry = "SubUser", inline = true,required = false)
     List<SubUser> listSubUsers;


    public List<SubUser> getListSubUsers() {
        return listSubUsers;
    }



}
