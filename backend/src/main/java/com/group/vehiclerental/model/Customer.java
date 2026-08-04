package com.group.vehiclerental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "customer")

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @NotBlank(message = "Full name is required")
    @Size(max = 100)
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

   
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid address")
    @Size(max = 120)
    @Column(name = "email", nullable = false, unique = true, length = 120)
    private String email;

   
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false, length = 100)
    private String password;

   
    @Size(max = 20)
    @Column(name = "nic", unique = true, length = 20)
    private String nic;

    @Size(max = 30)
    @Column(name = "driving_licence_no", unique = true, length = 30)
    private String drivingLicenceNo;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20)
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Size(max = 255)
    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "registered_date", nullable = false)
    private LocalDate registeredDate;

   
    @Size(max = 255)
    @Column(name = "image_path", length = 255)
    private String imagePath;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();

    public Customer() {
    }

    public Customer(String fullName, String email, String password, String phone,
                    String nic, String drivingLicenceNo, String address,
                    LocalDate registeredDate) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.nic = nic;
        this.drivingLicenceNo = drivingLicenceNo;
        this.address = address;
        this.registeredDate = registeredDate;
    }

   
    @PrePersist
    protected void onCreate() {
        if (registeredDate == null) {
            registeredDate = LocalDate.now();
        }
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getDrivingLicenceNo() {
        return drivingLicenceNo;
    }

    public void setDrivingLicenceNo(String drivingLicenceNo) {
        this.drivingLicenceNo = drivingLicenceNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", fullName='" + fullName + '\'' +
                ", nic='" + nic + '\'' +
                ", drivingLicenceNo='" + drivingLicenceNo + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", registeredDate=" + registeredDate +
                '}';
    }
}
