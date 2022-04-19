package com.example.springsocial.controller;


import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.DetectFacesRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.springsocial.exception.BadRequestException;
import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.AuthProvider;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.AuthProvider;
import com.example.springsocial.model.Education;
import com.example.springsocial.model.Family;

import com.example.springsocial.model.Fitness;
import com.example.springsocial.model.Interest;
import com.example.springsocial.model.Leisure;
import com.example.springsocial.model.Religion;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.ApiResponse;
import com.example.springsocial.payload.SignUpRequest;
import com.example.springsocial.payload.UpdateInterestRequest;
import com.example.springsocial.payload.UpdateLocationRequest;
import com.example.springsocial.payload.UpdateProfileRequest;

import com.example.springsocial.repository.EducationRepository;
import com.example.springsocial.repository.FamilyRepository;

import com.example.springsocial.repository.FitnessRepository;
import com.example.springsocial.repository.InterestRepository;
import com.example.springsocial.repository.LeisureRepository;
import com.example.springsocial.repository.ReligionRepository;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.springsocial.webscrap.GameScrap;
import com.example.springsocial.webscrap.MovieReleaseScrap;
import com.example.springsocial.webscrap.MusicEventScrap;
import com.example.springsocial.webscrap.SportScrap;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class UserController {
	@Autowired
	private InterestRepository interestRepository;
	
	@Autowired
	private FitnessRepository fitnessRepository;
	    
	@Autowired
	private ReligionRepository religionRepository;
	    
	@Autowired
	private LeisureRepository leisureRepository;
	    
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private AmazonRekognition amazonRekognition;
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private FamilyRepository familyRepository;
    
    @Autowired
    private EducationRepository educationRepository;
    
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    } 
    
    @PostMapping("/user/me/updateprofile")
    //@PreAuthorize("hasRole('USER')")
    public String registerUser(@CurrentUser @Valid @RequestBody UpdateProfileRequest updateProfileRequest){
    	String key = updateProfileRequest.getEmail();
    	User user = userRepository.getUserDetails(key);
    	user.setPhoneNumber(updateProfileRequest.getPhoneNumber());
    	user.setGenderPreference(updateProfileRequest.getGenderPreference());
    	user.setLocationPref(updateProfileRequest.getLocationPref());
    	user.setInpersonRelationship(updateProfileRequest.isInpersonRelationship());
    	userRepository.save(user);

    	return user.getLocationPref();
    }
    
 

    @PostMapping("/user/me/createInterest")
    //@PreAuthorize("hasRole('USER')")
    public Interest updateInterest(@CurrentUser @Valid @RequestBody UpdateInterestRequest updateInterestRequest){
    	String key = updateInterestRequest.getEmail();

    	//User user = userRepository.getUserDetails(key);
    	Interest interest = new Interest(updateInterestRequest.getEmail()) ;
    	interest.setMovie(updateInterestRequest.isMovie());
    	interest.setMusic(updateInterestRequest.isMusic());
    	interest.setName(updateInterestRequest.getName());
    	interest.setVideogame(updateInterestRequest.isVideoGame());
    	interest.setPersonalType(updateInterestRequest.getPersonalityType());
    	interest.setSport(updateInterestRequest.isSport());   	
    	interestRepository.save(interest);
    	
    	//fitness
    	Fitness fitness = new Fitness();
    	fitness.setEmail(updateInterestRequest.getEmail());
    	fitness.setGym(updateInterestRequest.isGym());
    	fitness.setCalisthenics(updateInterestRequest.isCalisthenics());
    	fitness.setCycling(updateInterestRequest.isCycling());
    	fitness.setDancing(updateInterestRequest.isDancing());
    	fitness.setHiking(updateInterestRequest.isHiking());
    	fitness.setHorseRiding(updateInterestRequest.isHorseRiding());
    	fitness.setRockClimbing(updateInterestRequest.isRockClimbing());
    	fitness.setWeightLifting(updateInterestRequest.isWeightLifting());
    	fitness.setYoga(updateInterestRequest.isYoga());
    	fitnessRepository.save(fitness);
    	
    	//religion
    	Religion religion = new Religion();
    	religion.setEmail(updateInterestRequest.getEmail());
    	religion.setAnimism(updateInterestRequest.isAnimism());
    	religion.setAtheism(updateInterestRequest.isAtheism());
    	religion.setMonotheism(updateInterestRequest.isMonotheism());
    	religion.setPolytheism(updateInterestRequest.isPolytheism());
    	religion.setTotemism(updateInterestRequest.isTotemism());
    	religionRepository.save(religion);
    	
    	//leisure
    	Leisure leisure = new Leisure();
    	leisure.setEmail(updateInterestRequest.getEmail());
    	leisure.setCognitive(updateInterestRequest.isCognitive());
    	leisure.setPhysical(updateInterestRequest.isPhysical());
    	leisure.setSocial(updateInterestRequest.isSocial());
    	leisureRepository.save(leisure);
    	return interest;

  
    }
    @PostMapping("/user/me/updateLocation")
    //@PreAuthorize("hasRole('USER')")
    public double updateLocation(@CurrentUser @Valid @RequestBody UpdateLocationRequest updateLocationRequest) {
    	User current = userRepository.getById(updateLocationRequest.getId());
    	current.setLoc(updateLocationRequest.getlongitude(), updateLocationRequest.getlatitude());
    	
    	userRepository.save(current);
    	return updateLocationRequest.getlatitude();
    }
    
    
    //Not needed
    @GetMapping("user/me/downloadVideo")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam(value = "id") Long id) throws IOException{
    	String fileName = id + "video";
    	S3Object s3obj = amazonS3.getObject("icebreakeraws/IceBreakerVideos", fileName);
    	S3ObjectInputStream objectcontent = s3obj.getObjectContent();
    	byte[] byteArray = IOUtils.toByteArray(objectcontent);
    	
    	ByteArrayResource resource = new ByteArrayResource(byteArray);
    	
    	return ResponseEntity.ok()
    			.contentLength(byteArray.length)
    			.header("content-type", "application/octet-stream")
    			.header("content-disposition", "attachment;filename=\"" + fileName + "\"")
    			.body(resource);
    	
    }

    	//family
    
  
 
    @PostMapping("/user/me/changeInterest")
    //@PreAuthorize("hasRole('USER')")
    // @valid removed from parameter
    public Interest changeInterest(@CurrentUser  @RequestBody UpdateInterestRequest updateInterestRequest){
    	//general interest
    	if (!fitnessRepository.existsById(updateInterestRequest.getEmail())) {
    		
    		System.out.print("Creating new profile");
    		Interest interest = new Interest(updateInterestRequest.getEmail()) ;
        	interest.setMovie(updateInterestRequest.isMovie());
        	interest.setMusic(updateInterestRequest.isMusic());
        	interest.setName(updateInterestRequest.getName());
        	interest.setVideogame(updateInterestRequest.isVideoGame());
        	interest.setPersonalType(updateInterestRequest.getPersonalityType());
        	interest.setSport(updateInterestRequest.isSport());   	
        	interestRepository.save(interest);
        	
        	//fitness
        	Fitness fitness = new Fitness();
        	fitness.setEmail(updateInterestRequest.getEmail());
        	fitness.setGym(updateInterestRequest.isGym());
        	fitness.setCalisthenics(updateInterestRequest.isCalisthenics());
        	fitness.setCycling(updateInterestRequest.isCycling());
        	fitness.setDancing(updateInterestRequest.isDancing());
        	fitness.setHiking(updateInterestRequest.isHiking());
        	fitness.setHorseRiding(updateInterestRequest.isHorseRiding());
        	fitness.setRockClimbing(updateInterestRequest.isRockClimbing());
        	fitness.setWeightLifting(updateInterestRequest.isWeightLifting());
        	fitness.setYoga(updateInterestRequest.isYoga());
        	fitnessRepository.save(fitness);
        	
        	//religion
        	Religion religion = new Religion();
        	religion.setEmail(updateInterestRequest.getEmail());
        	religion.setAnimism(updateInterestRequest.isAnimism());
        	religion.setAtheism(updateInterestRequest.isAtheism());
        	religion.setMonotheism(updateInterestRequest.isMonotheism());
        	religion.setPolytheism(updateInterestRequest.isPolytheism());
        	religion.setTotemism(updateInterestRequest.isTotemism());
        	religionRepository.save(religion);
        	
        	//leisure
        	Leisure leisure = new Leisure();
        	leisure.setEmail(updateInterestRequest.getEmail());
        	leisure.setCognitive(updateInterestRequest.isCognitive());
        	leisure.setPhysical(updateInterestRequest.isPhysical());
        	leisure.setSocial(updateInterestRequest.isSocial());
        	leisureRepository.save(leisure);
        	//family
        	Family family = new Family();
        	family.setEmail(updateInterestRequest.getEmail());
        	family.setDating(updateInterestRequest.isDating());
        	family.setDivorced(updateInterestRequest.isDivorced());
        	family.setSingle(updateInterestRequest.isSingle());
        	family.setWidow(updateInterestRequest.isWidow());
        	family.setComplicated(updateInterestRequest.isComplicated());
        	familyRepository.save(family);
        	
        	//education
        	Education education = new Education();
        	education.setEmail(updateInterestRequest.getEmail());
        	education.setEngineering(updateInterestRequest.isEngineering());
        	education.setFineArtsMusic(updateInterestRequest.isFineArtsMusic());
        	education.setScience(updateInterestRequest.isScience());
        	education.setSocialScience(updateInterestRequest.isSocialScience());
        	educationRepository.save(education);
        	
        	
        	return interest;
        	
        	

  
    		
    	}
    	else {
    		String email = updateInterestRequest.getEmail();
    	Interest interest = interestRepository.getByEmail(email);
    	interest.setMovie(updateInterestRequest.isMovie());
    	interest.setMusic(updateInterestRequest.isMusic());
    	interest.setName(updateInterestRequest.getName());
    	interest.setVideogame(updateInterestRequest.isVideoGame());
    	interest.setPersonalType(updateInterestRequest.getPersonalityType());
    	interest.setSport(updateInterestRequest.isSport());   	
    	interestRepository.save(interest);
  
    	//fitness
    	Fitness fitness = fitnessRepository.findByEmail(email);
    	fitness.setGym(updateInterestRequest.isGym());
       	fitness.setCalisthenics(updateInterestRequest.isCalisthenics());
    	fitness.setCycling(updateInterestRequest.isCycling());
    	fitness.setDancing(updateInterestRequest.isDancing());
    	fitness.setHiking(updateInterestRequest.isHiking());
    	fitness.setHorseRiding(updateInterestRequest.isHorseRiding());
    	fitness.setRockClimbing(updateInterestRequest.isRockClimbing());
    	fitness.setWeightLifting(updateInterestRequest.isWeightLifting());
    	fitness.setYoga(updateInterestRequest.isYoga());
    	fitnessRepository.save(fitness);
    	
    	//religion
    	Religion religion = religionRepository.getByEmail(email);
    	religion.setAnimism(updateInterestRequest.isAnimism());
    	religion.setAtheism(updateInterestRequest.isAtheism());
    	religion.setMonotheism(updateInterestRequest.isMonotheism());
    	religion.setPolytheism(updateInterestRequest.isPolytheism());
    	religion.setTotemism(updateInterestRequest.isTotemism());
    	religionRepository.save(religion);
    	
    	//leisure
    	Leisure leisure = leisureRepository.getByEmail(email);
    	leisure.setCognitive(updateInterestRequest.isCognitive());
    	leisure.setPhysical(updateInterestRequest.isPhysical());
    	leisure.setSocial(updateInterestRequest.isSocial());
    	leisureRepository.save(leisure);
    	
    	//family
		
		  Family family = familyRepository.getByEmail(email);
		  family.setEmail(updateInterestRequest.getEmail());
		  
		  family.setDating(updateInterestRequest.isDating());
		  family.setDivorced(updateInterestRequest.isDivorced());
		  family.setSingle(updateInterestRequest.isSingle());
		  family.setWidow(updateInterestRequest.isWidow());
		  family.setComplicated(updateInterestRequest.isComplicated());
		  familyRepository.save(family);
		 
    	
    	//education
    	Education education = educationRepository.getByEmail(email);
    	education.setEmail(updateInterestRequest.getEmail());
    	education.setEngineering(updateInterestRequest.isEngineering());
    	education.setFineArtsMusic(updateInterestRequest.isFineArtsMusic());
    	education.setScience(updateInterestRequest.isScience());
    	education.setSocialScience(updateInterestRequest.isSocialScience());
    	educationRepository.save(education);
    	
    	System.out.print("Updating interest profile");
    	return interest;
    	}
    	

 
    	
    }
   
    
    @GetMapping("/user/me/getProfile/{email}")
    public User getCurrentUserInfo(@CurrentUser @Valid  @PathVariable String email) {
    	//To run postman use text and type string: zhanglala0208@gmail.com 
    	User user = userRepository.getUserDetails(email);
    	return user;
//    	return user.getName();
    	
    }
    
    @GetMapping("/user/me/getMusicEvent/{id}")
    public String getMusicEventScrap(@CurrentUser @Valid  @PathVariable String id) throws IOException {
    	//id can not be 0
    	MusicEventScrap scrap = new MusicEventScrap();
    	//scrap.getMusicEventScrap();
    	int index= Integer.valueOf(id);
    	return scrap.getMusicEventScrap(index);

    	
    }
    
    @GetMapping("/user/me/getMovieRelease/{id}")
    public String getMovieRelease(@CurrentUser @Valid  @PathVariable String id) throws IOException {
    	//id can not be 0
    	MovieReleaseScrap scrap = new MovieReleaseScrap();
    	int index= Integer.valueOf(id);
    	return scrap.getMovieReleaseScrap(index);

    	
    }
    @GetMapping("/user/me/getGame")
    public String getGame() throws IOException {
    	//id can not be 0
    	GameScrap gameScrap = new GameScrap();
    	gameScrap.gameScrap();
    	return gameScrap.getGameScrap();
    	
    }
    @GetMapping("/user/me/getSport")
    public String getSport() throws IOException {
    	//id can not be 0
    	SportScrap sportScrap = new SportScrap();
    	sportScrap.sportScrap();
    	return sportScrap.getSportScrap();
    	
    }
  

}
