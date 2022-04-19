package com.example.springsocial.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.AgeRange;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.Attribute;
import com.amazonaws.services.rekognition.model.DetectFacesRequest;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.DetectTextRequest;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.textract.AmazonTextract;
import com.example.springsocial.model.AmazonRekConfig;
import com.example.springsocial.model.User;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@RestController
public class PhotoVideoController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private AmazonRekognition amazonRekognition;
	
	@Autowired
	private AmazonTextract amazTextract; 
	
	@Autowired
	private AmazonS3Client amazonS3Client;
	
    @PostMapping("user/me/uploadProfilePhoto")
    public String uploadP(@RequestParam(value = "file") MultipartFile multipartFile, Long id) throws IOException {
    	File modifiedFile = new File(multipartFile.getOriginalFilename());
    	FileOutputStream os = new FileOutputStream(modifiedFile);
    	os.write(multipartFile.getBytes());
    	String photoURL = id + "Photo";
    	amazonS3.putObject("custom-labels-console-us-east-1-bbe9954b52", photoURL, modifiedFile);

    	String photo  = id + "Photo";
    	String bucket = "custom-labels-console-us-east-1-bbe9954b52";
    	DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image().withS3Object(new S3Object().withName(photo).withBucket(bucket)))
                .withMaxLabels(10).withMinConfidence(75F);
    	
    	Label face = new Label();
    	Label human = new Label();
        face.setName("Human");
        human.setName("Face");
        
        DetectLabelsResult result = amazonRekognition.detectLabels(request);
        List<Label> labels = result.getLabels();
        boolean isface = false;
        boolean ishuman = false;
        
        for(Label c : labels) {
        	if(c.getName().equals(face.getName())) {
        		isface = true;
        	}
        	if(c.getName().equals(human.getName())) {
        		ishuman = true;
        	}
        }
      
        if(isface && ishuman) {
        	amazonS3.putObject("icebreakeraws/IceBreakerPhotos", photoURL, modifiedFile);
        	os.close();
         	modifiedFile.delete();
        	return "Worked";
        }else {
        	os.close();
         	modifiedFile.delete();
        	amazonS3.deleteObject("custom-labels-console-us-east-1-bbe9954b52", id + "Photo");
        	return "Failed";
        }
    }
    
    @PostMapping("user/me/uploadIntroVideo")
    @PreAuthorize("hasRole('USER')")
    public String uploadPhoto(@CurrentUser MultipartFile multipartFile, Long id) throws IOException {
    	File modifiedFile = new File(multipartFile.getOriginalFilename());
    	FileOutputStream os = new FileOutputStream(modifiedFile);
    	os.write(multipartFile.getBytes());
    	String introVideoURL = id + "Video";
    	amazonS3.putObject("icebreakeraws/IceBreakerVideos", introVideoURL, modifiedFile);
    	os.close();
    	modifiedFile.delete();
    	return "Worked";
    }
    
    
    @PostMapping("user/me/requestVerification")
    public String rqVerify(Long id) {
    	List<String> list = new ArrayList<>();
    	list.add("Fork");
    	list.add("Spoon");
    	String object = list.get(new Random().nextInt(list.size()));
    	User current = userRepository.getById(id);
    	if(current.isVerified()) {
    		return "User Already Verified";
    	}
    	current.setObject(object);
    	userRepository.save(current);
    	return object;
    }
    
    
    @PostMapping("user/me/uploadVerifyPhoto")
    public String verifyPhoto(@RequestParam(value = "file") MultipartFile multipartFile, Long id) throws IOException {
    	File modifiedFile = new File(multipartFile.getOriginalFilename());
    	FileOutputStream os = new FileOutputStream(modifiedFile);
    	os.write(multipartFile.getBytes());
    	
    	String photoURL = id + "VerifyPhoto";
    	amazonS3.putObject("custom-labels-console-us-east-1-bbe9954b52", photoURL, modifiedFile);

    	String photo  = id + "VerifyPhoto";
    	String bucket = "custom-labels-console-us-east-1-bbe9954b52";
    	DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image().withS3Object(new S3Object().withName(photo).withBucket(bucket)))
                .withMaxLabels(10).withMinConfidence(75F);
    	
    	
    	User current = userRepository.getById(id);
    	
    	Label face = new Label();
    	Label human = new Label();
    	Label object = new Label();
    	object.setName(current.getObject());
        face.setName("Human");
        human.setName("Face");
    
        
        DetectLabelsResult result = amazonRekognition.detectLabels(request);
        List<Label> labels = result.getLabels();
        
       // boolean isface = false;
        //boolean ishuman = false;
        boolean hasObject = false;
        
        for(Label c : labels) {
        	if(c.getName().equals(face.getName())) {
        		//isface = true;
        	}
        	if(c.getName().equals(human.getName())) {
        		//ishuman = true;
        	}
        	if(c.getName().equals(object.getName())) {
        		hasObject = true;
        	}
        }
      
        if(hasObject) {
        	current.setVerified(true);
        	userRepository.save(current);
        	os.close();
         	modifiedFile.delete();
        	return "User Is Verified";
        }else {
        	os.close();
         	modifiedFile.delete();
        	amazonS3.deleteObject("custom-labels-console-us-east-1-bbe9954b52", id + "VerifyPhoto");
        	return "User is not Verified";
        }
    }
}
