package com.kochasoft.opendoor.userservice.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kochasoft.opendoor.userservice.domain.User;
import com.kochasoft.opendoor.userservice.dto.ResponseDTO;
import com.kochasoft.opendoor.userservice.dto.UserDTO;
import com.kochasoft.opendoor.userservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/v1")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/users")
	@CrossOrigin
	public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
		try {
			
			//Validation
			
			String mobileNumber = userDTO.getMobileNumber();
			Pattern pattern = Pattern.compile("^\\d{5,15}$");
			Matcher mobileNumberMatcher= pattern.matcher(mobileNumber);
			if(!mobileNumberMatcher.matches()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(ResponseDTO.FAILED(1,"Invalid Mobile Number"));
			}
			
			User user=new User();
			user.setUuid(userDTO.getUuid());
			user.setName(userDTO.getName());
			user.setContactEmail(userDTO.getContactEmail());
			user.setCountry(userDTO.getCountry());
			user.setEmail(userDTO.getEmail());
			user.setMobileCountryCode(userDTO.getMobileCountryCode());
			user.setMobileNumber(user.getMobileNumber());
			user.setMobileCountryCode(user.getMobileCountryCode());
			user.setRedeemCode(user.getRedeemCode());
			
			long epochMilli = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
			user.setCreatedAt(epochMilli);
			user.setCreatedBy("APP");
			
			userService.createUser(user);
			return ResponseEntity.ok(ResponseDTO.SUCCESS());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDTO.FAILED());
		}
	}

    @GetMapping("/users/mobile/{mobileNumber}/countryCode/{countryCode}")
    public ResponseEntity<ResponseDTO> checkRegisteredUser(@PathVariable(name = "mobile",required = true) String mobileNumber,
	@PathVariable(name = "countryCode",required = true) String countryCode){
       try {
			User user = userService.findUserByMobileNumber(mobileNumber,countryCode);
			boolean isRegisterd=true;
			if(user==null){
					isRegisterd=false;
			}
		   	return ResponseEntity.ok(ResponseDTO.sendStatus(isRegisterd?"YES":"NO"));
	   } catch (Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDTO.FAILED());
	   }
    }
}
