package com.example.springsocial.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "interests", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class Interest {

	public Interest() {}
	public Interest(@Email String email) {
		super();
		this.email = email;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    @Email
    @Column(nullable = false)
    private String email;

    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private boolean movie;
    private boolean videogame;
    private boolean music;
    private boolean sport;
    private String personalityType;
	public String getPersonalType() {
		return personalityType;
	}
	public void setPersonalType(String personalType) {
		this.personalityType = personalType;
	}
	public boolean isMovie() {
		return movie;
	}
	public void setMovie(boolean movie) {
		this.movie = movie;
	}
	public boolean isVideogame() {
		return videogame;
	}
	public void setVideogame(boolean videogame) {
		this.videogame = videogame;
	}
	public boolean isMusic() {
		return music;
	}
	public void setMusic(boolean music) {
		this.music = music;
	}
	public boolean isSport() {
		return sport;
	}
	public void setSport(boolean sport) {
		this.sport = sport;
	}
}

