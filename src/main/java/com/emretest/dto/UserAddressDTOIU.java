package com.emretest.dto;

import jakarta.validation.constraints.NotEmpty;


public class UserAddressDTOIU {
    @NotEmpty(message = "City field cannot be empty")
    private String city;

    @NotEmpty(message = "Country field cannot be empty")
    private String country;

    @NotEmpty(message = "Street field cannot be empty")
    private String street;

    @NotEmpty(message = "Zip field cannot be empty")
    private String zip;

    // Getter ve Setter metotları
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }
}
