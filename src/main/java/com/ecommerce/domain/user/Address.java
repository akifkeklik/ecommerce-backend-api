package com.ecommerce.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Embeddable value object representing a physical address.
 */
@Embeddable
public class Address {

    @NotBlank
    @Size(max = 255)
    @Column(name = "street_address")
    private String streetAddress;

    @Size(max = 255)
    @Column(name = "address_line_2")
    private String addressLine2;

    @NotBlank
    @Size(max = 100)
    @Column(name = "city")
    private String city;

    @NotBlank
    @Size(max = 100)
    @Column(name = "state")
    private String state;

    @NotBlank
    @Size(max = 20)
    @Column(name = "postal_code")
    private String postalCode;

    @NotBlank
    @Size(max = 100)
    @Column(name = "country")
    private String country;

    @Size(max = 20)
    @Column(name = "phone_number")
    private String phoneNumber;

    // ================== Constructors ==================

    public Address() {
    }

    public Address(String streetAddress, String addressLine2, String city, String state,
            String postalCode, String country, String phoneNumber) {
        this.streetAddress = streetAddress;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    // ================== Getters and Setters ==================

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // ================== Business Methods ==================

    /**
     * Returns a formatted address string.
     */
    public String getFormattedAddress() {
        StringBuilder sb = new StringBuilder();
        sb.append(streetAddress);
        if (addressLine2 != null && !addressLine2.isBlank()) {
            sb.append(", ").append(addressLine2);
        }
        sb.append(", ").append(city)
                .append(", ").append(state)
                .append(" ").append(postalCode)
                .append(", ").append(country);
        return sb.toString();
    }
}
