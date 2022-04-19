package com.example.springsocial.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;



@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User implements Serializable{
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    private String imageUrl;
    private String locationPref;
	private String genderPreference;
	private boolean inpersonRelationship;
	private String phoneNumber;

	private double longituide;
	private double lagituide;
	private double distance;
	private boolean verifiedphonenumber;
	private boolean findMatches;
	private boolean verified;
	private String object;
	private String city;
	private int cluster;
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getLong() {
		return this.longituide;
	}
	
	public double getLag() {
		return this.lagituide;
	}
	
	public void setLoc(double longituide, double lagituide) {
		this.longituide = longituide;
		this.lagituide = lagituide;
	}

    public String getGenderPreference() {
		return genderPreference;
	}

	public void setGenderPreference(String genderPreference) {
		this.genderPreference = genderPreference;
	}

	public boolean isInpersonRelationship() {
		return inpersonRelationship;
	}

	public void setInpersonRelationship(boolean inpersonRelationship) {
		this.inpersonRelationship = inpersonRelationship;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
    
    public void setLocationPref(String locationPref) {
    	this.locationPref = locationPref;
    }
    
    public String getLocationPref() {
    	return this.locationPref;
    }

}
