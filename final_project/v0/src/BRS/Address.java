package BRS;

public class Address {
    private String town;
    private String city;
    private String country;
    private String zipcode;

    public Address(String compactAddress) {
        String[] splited = compactAddress.split(",( )*");
        town = splited[0];
        city = splited[1];
        country = splited[2];
        zipcode = splited[3];
    }

    public Address(String town, String city, String country, String zipcode) {
        this.town = town;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }


    public String toString() {
        return String.format("%s, %s, %s, %s", town, city, country, zipcode);
    }
}
