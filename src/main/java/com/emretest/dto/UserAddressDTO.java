package com.emretest.dto;


public class UserAddressDTO {
    private Integer id;
    private String country;
    private String city;
    private String street;
    private String zip;

    // Getter ve Setter metotları
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }
}
