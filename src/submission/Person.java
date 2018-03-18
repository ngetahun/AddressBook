package submission;

import java.lang.String;

public class Person implements Serializable, Comparable {
	// TODO: add private access
    String name;
	String address;
	String email;
	long phonenumber;
	String website;
	int age;

	public Person(n, a, e, p, w, a){
	    name = n;
	    address = a;
	    email = e;
	    phonenumber = p;
	    website = w;
	    age = a;
    }

    public Person(n){
	    name = n;
	    address = "";
	    email = "";
	    phonenumber = 0;
	    website = "";
	    age = 0;
    }

	void setName(String n) {
		name = n;
	}
	
	void setAddress(String a) {
		address = a;
	}
	
	void setEmail(String e) {
		email = e;
	}
	
	void setPhoneNumber(long pn) {
		phonenumber = pn;
	}
	
	void setWebsite(String web) {
		website = web;
	}
	
	void setAge(int ag) {
		age = ag;
	}
	
	String getName() {
		return name;
	}
	
	String getAddress() {
		return address;
	}
	
	String getEmail() {
		return email;
	}
	
	String getWebsite() {
		return website;
	}
	
	long getPhoneNumber() {
		return phonenumber;
	}
	
	int getAge() {
		return age;
	}

    public long getPhonenumber() {
        return phonenumber;
    }

    @Override
    public String toString(){
	    String addressEntry = "Person: " + getName() + "\n" +
                           "Phone Number: " + getPhoneNumber() +
                           "Address: " + getAddress() +
                           "Email: " + getEmail();
	    return addressEntry;
    }
}
