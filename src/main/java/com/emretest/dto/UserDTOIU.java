package com.emretest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
public class UserDTOIU {
    @NotEmpty(message = "User Name field cannot be empty")
    private String username;

    @NotNull(message = "Email field cannot be empty")
    @Email(message = "Email format is incorrect")
    private String email;

    @NotEmpty(message = "Password field cannot be empty")
    private String password;

    @NotNull(message = "User Address field cannot be empty")
    private UserAddressDTOIU address;

    // Getter ve Setter metotları
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UserAddressDTOIU getAddress() { return address; }
    public void setAddress(UserAddressDTOIU address) { this.address = address; }
}
