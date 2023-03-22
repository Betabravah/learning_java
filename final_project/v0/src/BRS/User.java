package BRS;

public abstract class User {
    public static final String CUSTOMER = "CUSTOMER";
    public static final String ADMIN = "ADMIN";

    protected String id;
    protected String firstname;
    protected String middlename;
    protected String lastname;
    protected Address address;
    protected String phoneNumber;

    public User(String id, String firstname, String middlename, String lastname, Address address, String phoneNumber) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return String.format("%s %s %s", firstname, middlename, lastname);
    }

    public void setName(String fname, String mname, String lname) {
        firstname = fname;
        middlename = mname;
        lastname = lname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
