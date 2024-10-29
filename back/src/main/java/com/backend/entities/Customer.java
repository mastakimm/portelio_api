package com.backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String displayName;

    @BooleanFlag
    private Boolean isVerified = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastConnectionDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date jwtInvalidationDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CustomerRole> roles = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Token> tokens;

    public Customer() {

    }

    public Customer(String email, String password, String displayName) {
        this.email = email;
        this.password = password;
        this.displayName = displayName;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }
}
