package com.kochasoft.opendoor.userservice.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.firebase.auth.FirebaseAuth;
import com.kochasoft.opendoor.userservice.domain.Status;
import com.kochasoft.opendoor.userservice.domain.User;
import com.kochasoft.opendoor.userservice.domain.User.Device;
import com.kochasoft.opendoor.userservice.dto.ResponseDTO;
import com.kochasoft.opendoor.userservice.dto.TokenDTO;
import com.kochasoft.opendoor.userservice.dto.ResponseDTO.ResponseStatusCode;
import com.kochasoft.opendoor.userservice.dto.UserDTO;
import com.kochasoft.opendoor.userservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping("/v1")
@CrossOrigin
public class UserController {

	private static final String ERR_EXCP = "please see log for further.";

	@Autowired
	UserService userService;

	@Value("${firebase-api-key}")
	String firebaseWebAPIKey;
	
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
						.body(ResponseDTO.failed(ResponseStatusCode.FAIL,"Mobile Number","Invalid Mobile Number"));
			}
			
			User user=new User();
			user.setUuid(userDTO.getUuid());
			user.setName(userDTO.getName());
			user.setContactEmail(userDTO.getContactEmail());
			user.setCountry(userDTO.getCountry());
			user.setEmail(userDTO.getEmail());
			user.setMobileCountryCode(userDTO.getMobileCountryCode());
			user.setMobileNumber(userDTO.getMobileNumber());
			user.setMobileCountryCode(userDTO.getMobileCountryCode());
			user.setStatus(Status.ACTIVE);
			
			long epochMilli = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
			user.setCreatedAt(epochMilli);
			user.setCreatedBy("APP");
			
			userService.createUser(user);
			return ResponseEntity.ok(ResponseDTO.success());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDTO.failed());
		}
	}

    @GetMapping("/users/mobile/{mobileNumber}/countryCode/{countryCode}")
    public ResponseEntity<ResponseDTO> checkRegisteredUser(@PathVariable(name = "mobileNumber",required = true) String mobileNumber,
	@PathVariable(name = "countryCode",required = true) String countryCode){
       try {
			if(!countryCode.contains("+")){
				countryCode="+"+countryCode;
			}
			User user = userService.findUserByMobileNumber(mobileNumber,countryCode);
			boolean isRegisterd=true;
			Map<String, Object> resMap=null;
			if(user==null){
					isRegisterd=false;
			}else{
				resMap=new HashMap<>();
				resMap.put("name", user.getName());
			}
		   	return ResponseEntity.ok(ResponseDTO.sendStatus(isRegisterd?ResponseStatusCode.YES:ResponseStatusCode.NO,resMap));
	   } catch (Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDTO.failed());
	   }
    }

	@GetMapping("/users/id/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable(name = "id",required = true) String id){
		try {
			
			User user = userService.findById(id);
			if(user==null){
				return ResponseEntity.ok(ResponseDTO.failed(ResponseStatusCode.FAIL, "No active User","user not found"));
			}
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setAvatar(user.getAvatar());
			userDTO.setCountry(user.getCountry());
			userDTO.setContactEmail(user.getContactEmail());
			userDTO.setEmail(user.getEmail());
			userDTO.setMobileCountryCode(user.getMobileCountryCode());
			userDTO.setMobileNumber(user.getMobileNumber());
			userDTO.setUuid(user.getUuid());
			
		   	return ResponseEntity.ok(ResponseDTO.success(userDTO));
	   } catch (Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDTO.failed());
	   }
    }

	@GetMapping("/users/uid/{uid}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable(name = "uid",required = true) String uid){
		try {
			
			User user = userService.findByUuid(uid,Status.ACTIVE);
			if(user==null){
				return ResponseEntity.ok(ResponseDTO.failed(ResponseStatusCode.FAIL, "No active User","user not found"));
			}
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			
		   	return ResponseEntity.ok(ResponseDTO.success(userDTO));
	   } catch (Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDTO.failed());
	   }
    }

	@GetMapping("/user/{uid}/token")
	public String getNewToken(@PathVariable String uid) {
		Map<String, Object> params = new HashMap<>();
		String customtoken;
		try {
			customtoken = FirebaseAuth.getInstance().createCustomToken(uid);

			params.put("token", customtoken);
			params.put("returnSecureToken", true);

			LinkedHashMap<String, Object> postForObject = new RestTemplate().postForObject(
					"https://www.googleapis.com/identitytoolkit/v3/relyingparty/verifyCustomToken?key="
							+ firebaseWebAPIKey,
					params, LinkedHashMap.class);

			return postForObject.get("idToken").toString();

		} catch (Exception e) {
			
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@PutMapping("/users/token")
	public ResponseEntity<ResponseDTO> updateDeviceToken(@RequestAttribute("user") User user, @RequestBody TokenDTO tokenDTO){
		try {

			User searchedUser = userService.findById(user.getId());
			List<Device> devices=searchedUser.getDevices()==null?new ArrayList<>():searchedUser.getDevices();

			Status status=tokenDTO.getLogin()?Status.ACTIVE:Status.LOGOUT;
			devices.forEach(a->{
				if(a.getToken().equals(tokenDTO.getToken())){
					a.setStatus(status);
				}else{
					Device device = new Device();
					device.setToken(tokenDTO.getToken());
					device.setStatus(status);
					devices.add(device);
				}
			});

			searchedUser.setDevices(devices);
			userService.createUser(searchedUser);
			
			return ResponseEntity.ok().body(ResponseDTO.success());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ResponseDTO.failed(ResponseStatusCode.FAIL, null, ERR_EXCP));
		}
	}

}
