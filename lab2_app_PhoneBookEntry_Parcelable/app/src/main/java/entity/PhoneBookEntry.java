package entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by User on 07/02/2018.
 */

public class PhoneBookEntry implements Parcelable {
    private String name;
    private String phone;

    public PhoneBookEntry(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }



    protected PhoneBookEntry(Parcel in) {
        name = in.readString();
        phone = in.readString();
    }

    public static final Creator<PhoneBookEntry> CREATOR = new Creator<PhoneBookEntry>() {
        @Override
        public PhoneBookEntry createFromParcel(Parcel in) {
            return new PhoneBookEntry(in);
        }

        @Override
        public PhoneBookEntry[] newArray(int size) {
            return new PhoneBookEntry[size];
        }
    };

    @Override
    public String toString() {

        return(name+" "+phone+"\n");
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(phone);
    }
}
