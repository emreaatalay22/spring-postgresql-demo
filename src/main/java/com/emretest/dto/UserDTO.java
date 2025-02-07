package com.emretest.dto;

public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private UserAddressDTO userAddress;

    // Getter ve Setter metotları
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public UserAddressDTO getUserAddress() { return userAddress; }
    public void setUserAddress(UserAddressDTO userAddress) { this.userAddress = userAddress; }
}
