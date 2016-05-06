package timesofmoney.qnbmerchant.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by pankajp on 21-04-2016.
 */
public class SubUser implements Parcelable
{

    public SubUser(){

    }

    @Attribute(name = "id" ,required = false)
    public String id="";

    @Element(required = false)
    String FirstName, LastName, EmailID, MobileNumber;

    protected SubUser(Parcel in) {
        id = in.readString();
        FirstName = in.readString();
        LastName = in.readString();
        EmailID = in.readString();
        MobileNumber = in.readString();
    }

    public static final Creator<SubUser> CREATOR = new Creator<SubUser>() {
        @Override
        public SubUser createFromParcel(Parcel in) {
            return new SubUser(in);
        }

        @Override
        public SubUser[] newArray(int size) {
            return new SubUser[size];
        }
    };

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(FirstName);
        dest.writeString(LastName);
        dest.writeString(EmailID);
        dest.writeString(MobileNumber);
    }
}
