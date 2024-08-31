package com.sahelyfr.eataweekback.controller;

import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Base64;

import jakarta.annotation.security.RolesAllowed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sahelyfr.eataweekback.model.Authorities;
import com.sahelyfr.eataweekback.model.User;
import com.sahelyfr.eataweekback.repository.AuthoritiesRepository;
import com.sahelyfr.eataweekback.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthoritiesRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RolesAllowed("USER")
    @RequestMapping("/**")
    public String getUser() {
        return "Welcome User";
    }

    @RolesAllowed({ "USER", "ADMIN" })
    @RequestMapping("/admin")
    public String getAdmin() {
        return "Welcome Admin";
    }

    @GetMapping("/user")
    public String getUserInfo(Principal user) {
        StringBuffer userInfo = new StringBuffer();
        if (user instanceof UsernamePasswordAuthenticationToken) {
            userInfo.append(getUserNamePasswordLoginInfo(user));
        } /*
           * else if(user instanceof OAuth2AuthenticationToken){
           * userInfo.append(getOAuth2LoginInfo(user));
           * }
           */
        return userInfo.toString();
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User registerUser(@RequestBody String payload) throws JsonMappingException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(payload, User.class);

        System.out.printf("Registering user: %s", user.getUsername());

        if (user.getUsername() != null && user.getPassword() != null) {
            String password = new String(
                    Base64.getDecoder().decode(
                            user.getPassword().getBytes(StandardCharsets.UTF_8)));
            user.setPassword(passwordEncoder.encode(password));
            User u = userRepo.save(user);
            Authorities r = new Authorities(u.getUsername(), "USER");
            roleRepo.save(r);
            System.out.printf("Registering for user: %s is OK\\n", user.getUsername());
            return u;
        } else {
            System.out.printf("Registering for user: %s is KO\\n", user.getUsername());
            return null;
        }
    }

    private StringBuffer getUserNamePasswordLoginInfo(Principal user) {
        StringBuffer userNameInfo = new StringBuffer();

        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
        if (token.isAuthenticated()) {
            User u = (User) token.getPrincipal();
            userNameInfo.append("Welcome, " + u.getUsername());
        } else {
            userNameInfo.append("NA");
        }
        return userNameInfo;
    }

    /*
     * private StringBuffer getOAuth2LoginInfo(Principal user){
     * StringBuffer protectedInfo = new StringBuffer();
     * OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
     * OAuth2AuthorizedClient authClient = this.authorizedClientService
     * .loadAuthorizedClient(
     * authToken.getAuthorizedClientRegistrationId(),
     * authToken.getName()
     * );
     * 
     * OAuth2User principal = ((OAuth2AuthenticationToken) user).getPrincipal();
     * if(authToken.isAuthenticated()){
     * OidcIdToken idToken = getIdToken(principal);
     * Map<String, Object> userAttributes =
     * ((DefaultOAuth2User)authToken.getPrincipal()).getAttributes();
     * 
     * String userToken = authClient.getAccessToken().getTokenValue();
     * protectedInfo.append("Welcome, "+userAttributes.get("name")+"<br><br>");
     * protectedInfo.append("email : "+getIdToken(principal).getEmail()+"<br><br>");
     * protectedInfo.append("token : "+userToken+"<br><br>");
     * 
     * if(idToken != null){
     * Map<String, Object> claims = idToken.getClaims();
     * 
     * for (String key : claims.keySet()) {
     * protectedInfo.append("  " + key + ": " + claims.get(key)+"<br>");
     * }
     * }
     * }else{
     * protectedInfo.append("NA");
     * }
     * return protectedInfo;
     * }
     * 
     * private OidcIdToken getIdToken(OAuth2User principal){
     * if(principal instanceof DefaultOidcUser){
     * DefaultOidcUser oidcUser = (DefaultOidcUser)principal;
     * return oidcUser.getIdToken();
     * }
     * return null;
     * }
     */
}
