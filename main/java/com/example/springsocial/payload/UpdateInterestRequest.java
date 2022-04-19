package com.example.springsocial.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UpdateInterestRequest {
	//general
	@NotBlank
    @Email
    private String email;

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	private boolean movie;	
	private boolean videoGame;	
	private boolean music;
	private boolean sport;
	private String name;
	private String personalityType;
	
	public String getPersonalityType() {
		return personalityType;
	}
	public void setPersonalityType(String personalityType) {
		this.personalityType = personalityType;
	}
	public boolean isMovie() {
		return movie;
	}
	public void setMovie(boolean movie) {
		this.movie = movie;
	}
	public boolean isVideoGame() {
		return videoGame;
	}
	public void setVideoGame(boolean videoGame) {
		this.videoGame = videoGame;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	
	//fitness
	private boolean gym;
	private boolean calisthenics;
	private boolean cycling;
	private boolean hiking;
	private boolean rockClimbing;
	private boolean weightLifting;
	private boolean yoga;
	private boolean horseRiding;
	private boolean dancing;
	public boolean isCalisthenics() {
		return calisthenics;
	}
	public void setCalisthenics(boolean calisthenics) {
		this.calisthenics = calisthenics;
	}
	public boolean isCycling() {
		return cycling;
	}
	public void setCycling(boolean cycling) {
		this.cycling = cycling;
	}
	public boolean isHiking() {
		return hiking;
	}
	public void setHiking(boolean hiking) {
		this.hiking = hiking;
	}
	public boolean isRockClimbing() {
		return rockClimbing;
	}
	public void setRockClimbing(boolean rockClimbing) {
		this.rockClimbing = rockClimbing;
	}
	public boolean isWeightLifting() {
		return weightLifting;
	}
	public void setWeightLifting(boolean weightLifting) {
		this.weightLifting = weightLifting;
	}
	public boolean isYoga() {
		return yoga;
	}
	public void setYoga(boolean yoga) {
		this.yoga = yoga;
	}
	public boolean isHorseRiding() {
		return horseRiding;
	}
	public void setHorseRiding(boolean horseRiding) {
		this.horseRiding = horseRiding;
	}
	public boolean isDancing() {
		return dancing;
	}
	public void setDancing(boolean dancing) {
		this.dancing = dancing;
	}
	public boolean isGym() {
		return gym;
	}
	public void setGym(boolean gym) {
		this.gym = gym;
	}
	
	//religion
	private boolean polytheism;//multiple gods
	private boolean monotheism;//single god
	private boolean atheism;
	private boolean animism;//animals, plant, natural 
	private boolean totemism;//human-natural being connection


	public boolean isPolytheism() {
		return polytheism;
	}
	public void setPolytheism(boolean polytheism) {
		this.polytheism = polytheism;
	}
	public boolean isMonotheism() {
		return monotheism;
	}
	public void setMonotheism(boolean monotheism) {
		this.monotheism = monotheism;
	}
	public boolean isAtheism() {
		return atheism;
	}
	public void setAtheism(boolean atheism) {
		this.atheism = atheism;
	}
	public boolean isAnimism() {
		return animism;
	}
	public void setAnimism(boolean animism) {
		this.animism = animism;
	}
	public boolean isTotemism() {
		return totemism;
	}
	public void setTotemism(boolean totemism) {
		this.totemism = totemism;
	}
	
	//leisure
	private boolean social;//socializing with family and friends
	private boolean cognitive;//mentally stimulating activities
	private boolean physical;//exercise and sports
	
	public boolean isSocial() {
		return social;
	}
	public void setSocial(boolean social) {
		this.social = social;
	}
	public boolean isCognitive() {
		return cognitive;
	}
	public void setCognitive(boolean cognitive) {
		this.cognitive = cognitive;
	}
	public boolean isPhysical() {
		return physical;
	}
	public void setPhysical(boolean physical) {
		this.physical = physical;
	}

	//family
	
		
	private boolean married;
	private boolean single;
	private boolean divorced;
	private boolean dating;
	private boolean widow;
	private boolean complicated;
	
	public boolean isMarried() {
		return married;
	}
	public void setMarried(boolean married) {
		this.married = married;
	}
	public boolean isSingle() {
		return single;
	}
	public void setSingle(boolean single) {
		this.single = single;
	}
	public boolean isDivorced() {
		return divorced;
	}
	public void setDivorced(boolean divorced) {
		this.divorced = divorced;
	}
	public boolean isDating() {
		return dating;
	}
	public void setDating(boolean dating) {
		this.dating = dating;
	}
	public boolean isWidow() {
		return widow;
	}
	public void setWidow(boolean widow) {
		this.widow = widow;
	}
	public boolean isComplicated() {
		return complicated;
	}
	public void setComplicated(boolean complicated) {
		this.complicated = complicated;
	}

	
	//education	
	private boolean socialScience;
	private boolean science;
	private boolean engineering;
	private boolean fineArtsMusic;
	public boolean isSocialScience() {
		return socialScience;
	}

	public boolean isScience() {
		return science;
	}

	public boolean isEngineering() {
		return engineering;
	}

	public boolean isFineArtsMusic() {
		return fineArtsMusic;
	}

	
	



	
}
