package entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 11/02/2018.
 */

public class ExParcelable implements Parcelable {
    private int mData;

    /* everything below here is for implementing Parcelable */

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<ExParcelable> CREATOR = new Parcelable.Creator<ExParcelable>() {
        public ExParcelable createFromParcel(Parcel in) {
            return new ExParcelable(in);
        }

        public ExParcelable[] newArray(int size) {
            return new ExParcelable[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private ExParcelable(Parcel in) {
        mData = in.readInt();
    }
}
