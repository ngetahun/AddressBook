package submission;

import java.io.Serializable;
import java.lang.String;

/**
 * Person class
 * @author Taha Zia
 * @author Natnael Getahun
 * */

public class Person implements Serializable, Comparable<Person> {
    private String name;
    private String address;
    private String email;
    private long phonenumber;
    private String website;
    private int age;

    /**
     * Constructor
     */
    public Person(String n, String a, String e, long p, String w, int ag) {
        name = n;
        address = a;
        email = e;
        phonenumber = p;
        website = w;
        age = ag;
    }

    /**
     * Constructor that takes name only
     */
    public Person(String n) {
        name = n;
        address = "";
        email = "";
        phonenumber = 0;
        website = "";
        age = 0;
    }

    public void setName(String n) {
        name = n;
    }

    public void setAddress(String a) {
        address = a;
    }

    public void setEmail(String e) {
        email = e;
    }

    public void setPhoneNumber(long pn) {
        phonenumber = pn;
    }

    public void setWebsite(String web) {
        website = web;
    }

    public void setAge(int ag) {
        age = ag;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public long getPhoneNumber() {
        return phonenumber;
    }

    public int getAge() {
        return age;
    }

    public long getPhonenumber() {
        return phonenumber;
    }

    /**
     * Updates a person entries
     * @param aName
     * @param pAddress
     * @param pEmail
     * @param phone
     * @param web
     * @param pAge
     *
     * @return
     * */
    public void update(String aName,
                       String pAddress,
                       String pEmail,
                       long phone,
                       String web,
                       int pAge)
    {
        // updates only valid inputs
        if (!aName.isEmpty()){
            this.setName(aName);
        }

        if (!pAddress.isEmpty()){
            this.setAddress(pAddress);
        }

        if (!pEmail.isEmpty()) {
            this.setEmail(pEmail);
        }

        if (phone > 0){
            this.setPhoneNumber(phone);
        }

        if (web.isEmpty()){
            this.setWebsite(web);
        }

        if (pAge > 0 && pAge > age){
            this.setAge(pAge);
        }
    }

    /**
     * Computes hash value based on the djb2
     * Taken from http://www.cse.yorku.ca/~oz/hash.html
     * @param h
     * @return hash value
     * */
    public static long hashString(String h){
        long hash = 5381;
        h = h.toLowerCase();

        for (int i = 0; i < h.length(); i++){
            int c = (int)h.charAt(i);
            hash = ((hash << 5) + hash) + c;
        }

        return hash;
    }

    @Override
    public String toString() {
        String addressEntry = "Person: " + getName() +
                              "\nPhone Number: " + getPhoneNumber() +
                              "\nAddress: " + getAddress() +
                              "\nEmail: " + getEmail();
        return addressEntry;
    }

    @Override
    public int compareTo(Person o) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (this == o) return EQUAL;

        int r = this.name.compareTo(o.name);

        if (this.age > o.age) return AFTER;
        if (this.age < o.age) return BEFORE;
        if (this.age == o.age) return EQUAL;

        return r;
    }
}
