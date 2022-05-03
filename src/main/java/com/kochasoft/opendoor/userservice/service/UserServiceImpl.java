package com.kochasoft.opendoor.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.kochasoft.opendoor.userservice.domain.Status;
import com.kochasoft.opendoor.userservice.domain.User;
import com.kochasoft.opendoor.userservice.dto.CardDTO;
import com.kochasoft.opendoor.userservice.dto.Local;
import com.kochasoft.opendoor.userservice.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repository;

	@Autowired
	CardService cardService;
	
	@Value("${firebase-api-key}")
	String firebaseWebAPIKey;

	
	@Value("${ksft.opendr.card.avatar.url}")
	String cardAvatarPath;

	@Value("${ksft.opendr.bucket}")
    String bucketName;

	@Value("${ksft.opendr.user.avatar.location}")
	String userAvatarLocation;

    @Value("${spring.cloud.gcp.project-id}")
    String projectId;

	@Override
	@Transactional
	public User createUser(User user,boolean createCard) throws Exception{
		User savedUser=repository.save(user).block();


		if(!createCard){
			return savedUser;
		}
		if(savedUser==null){
			return null;
		}

		//creating token
		String token = generateNewToken(savedUser.getUuid());

		CardDTO cardDto=new CardDTO();

		Local userDisplayNameLocal= new Local();
		userDisplayNameLocal.setEn(user.getName());

		Local subTitle= new Local();
		subTitle.setEn("Personal");

		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		String uri=userAvatarLocation+"/default-card-avatar.png";
		byte[] fileBytes = storage.readAllBytes(bucketName, uri);
		String avatarBase64 = "data:image/png;base64,"+Base64.getEncoder().encodeToString(fileBytes);
		
		cardDto.setUserDisplayName(userDisplayNameLocal);
		cardDto.setRestrictionType("No restriction");
		cardDto.setUserId(savedUser.getId());
		cardDto.setSubTitle(subTitle);
		cardDto.setExpiration(0);
		cardDto.setAvatar(avatarBase64);
		cardService.createCard(cardDto,token);

		return savedUser;
	}

	@Override
	public User findById(String id) {
		return repository.findById(id).block();
	}

	@Override
	public List<User> findAllUsers() {
		return repository.findAll().collectList().block();

	}

	@Override
	public void deleteUserById(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findUserByMobileNumber(String mobileNumber,String mobileCountryCode) {
		return repository.findByMobileNumberAndMobileCountryCode(mobileNumber,mobileCountryCode).block();
		
	}

	@Override
	public User findByUuid(String uuid, Status status){

		return repository.findByUuidAndStatus(uuid, status).block();
	}


	@Override
	public String generateNewToken(String uid) throws Exception{

		Map<String, Object> params = new HashMap<>();
		String customtoken = FirebaseAuth.getInstance().createCustomToken(uid);

		params.put("token", customtoken);
		params.put("returnSecureToken", true);

		LinkedHashMap<String, Object> postForObject = new RestTemplate().postForObject(
				"https://www.googleapis.com/identitytoolkit/v3/relyingparty/verifyCustomToken?key="
						+ firebaseWebAPIKey,
				params, LinkedHashMap.class);

		return postForObject==null?"":postForObject.get("idToken").toString();

		
	}

}
