package entity;

import java.io.Serializable;

/**
 * Created by User on 07/02/2018.
 */

public class PhoneBookEntry implements Serializable {
    private String name;
    private String phone;

    public PhoneBookEntry(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

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
}
