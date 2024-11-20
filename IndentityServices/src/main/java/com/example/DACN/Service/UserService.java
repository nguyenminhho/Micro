package com.example.DACN.Service;

import com.example.DACN.Dto.Request.AddPasswordRequest;
import com.example.DACN.Dto.Request.UserRequest;

import com.example.DACN.Dto.Response.UserResponse;
import com.example.DACN.Entity.Roles;
import com.example.DACN.Entity.User;

import com.example.DACN.Mapper.CompanyMapper;
import com.example.DACN.Mapper.ProfileMapper;
import com.example.DACN.Mapper.UserMapper;
import com.example.DACN.Repository.RolesRepository;
import com.example.DACN.Repository.UserRepository;

//import com.example.DACN.Repository.feign.CompanyRepository;
import com.example.DACN.Repository.feign.CompanyRepository;
import com.example.DACN.Repository.feign.ProfileRepository;
import com.example.event.dto.NotificationEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private RedisTemplate<String,String > redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Gson gson;


    @Autowired
    private ProfileRepository profileRepository;
    @Transactional
    public UserResponse addUser(UserRequest userRequest) throws Exception{
        User user = userMapper.toUser(userRequest);
        if( (userRepository.findByUserName(user.getUsername())) != null){
            throw  new Exception("User exits");
        }
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        HashSet<Roles> roles = new HashSet<>();
//Type = 0 role = user, con lai la company
        if(userRequest.getType() == 0){
            Roles rolesUSER = rolesRepository.findById("USER").get();
            roles.add(rolesUSER);
        }else {
            Roles rolesCOMPANY = rolesRepository.findById("COMPANY").get();
            roles.add(rolesCOMPANY);
        }
        user.setRoles(roles);

        var saveUser = userRepository.save(user);


//Type = 0 thi la profile(nguoi tim viec), con lai la company(nha tuyen dung)
        if(userRequest.getType() == 0){
            var profile = profileMapper.toProfileRequest(userRequest);
            profile.setUserId(saveUser.getId());
            profileRepository.addProfile(profile);
        }
        if(userRequest.getType() == 1){
            var company = companyMapper.toCompanyRequest(userRequest);
            company.setUserId(saveUser.getId());
            companyRepository.addCompany(company);
        }

        NotificationEvent notificationEvent = NotificationEvent.builder()
                .channel("Email")
                .recipient(user.getEmail())
                .subject("Welcome to MicroService")
                .body("Hello"+user.getUsername())
                .build();

//            kafkaTemplate.send("notification-success",notificationEvent);

        return userMapper.toUserResponse(saveUser);
    }

    public void addPassword(AddPasswordRequest addPasswordRequest) throws Exception{
        var userCurrent = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(userCurrent.getName());
        if( user == null || (StringUtils.hasText(user.getPassword()))){
            throw  new Exception("User isn't exits or User had password");
        }
        user.setPassword(passwordEncoder.encode(addPasswordRequest.getPassword()));
       userRepository.save(user);

    }

//    //Co su dung redis
//    public List<UserResponse> getAllUser() throws JsonProcessingException {
//        List<User> users = new ArrayList<>();
//        String redisAllUser = redisTemplate.opsForValue().get("User");
//
//        if (redisAllUser == null) {
//            users = userRepository.findAll();
//            // Chuyển `users` thành chuỗi JSON
//            var userJson =  objectMapper.writeValueAsString(users);
//            redisTemplate.opsForValue().set("User",userJson);
//        } else {
//            // Chuyển chuỗi JSON thành List<User>
//            users = objectMapper.readValue(redisAllUser, new TypeReference<List<User>>() {});
//        }
//
//
//        return users.stream().map(userMapper::toUserResponse).toList();
//    }


    public List<UserResponse> getAllUser() throws JsonProcessingException {
            var users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponse).toList();
    }
    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id).get());
    }

    public UserResponse getMyInfo(){
        var currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        return userMapper.toUserResponse(userRepository.findByUserName(username));
    }

    ///con chua sua role khi update co role thay doi
    public UserResponse updateUser(String id, UserRequest userRequest) throws Exception {
        User user = userRepository.findById(id).get();

        user.setId( userRequest.getId() );
        user.setPassword( userRequest.getPassword() );

//        user.setAge( userRequest.getAge() );
//        if (userRequest.getRoles() != null) {
//            if (user.getRoles() != null) {
//                user.getRoles().clear();
//                user.getRoles().addAll(userRequest.getRoles());
//            } else {
//                user.setRoles(new LinkedHashSet<>(userRequest.getRoles()));
//            }
//        }
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }




    public Boolean removeUser(String id){
        userRepository.deleteById(id);

        return true;
    }


}
