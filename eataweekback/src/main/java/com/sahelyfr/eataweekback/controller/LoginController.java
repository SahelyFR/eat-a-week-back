package com.sahelyfr.eataweekback.controller;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    
    @RolesAllowed("USER")
    @RequestMapping("/**")
    public String getUser(){
        return "Welcome User";
    }

    @RolesAllowed({"USER","ADMIN"})
    @RequestMapping("/admin")
    public String getAdmin(){
        return "Welcome Admin";
    }

    @GetMapping("/*")
    public String getUserInfo(Principal user){
        StringBuffer userInfo = new StringBuffer();
        if(user instanceof UsernamePasswordAuthenticationToken){
            userInfo.append(getUserNamePasswordLoginInfo(user));
        }/*else if(user instanceof OAuth2AuthenticationToken){
            userInfo.append(getOAuth2LoginInfo(user));
        }*/
        return userInfo.toString();
    }

    private StringBuffer getUserNamePasswordLoginInfo(Principal user){
        StringBuffer userNameInfo = new StringBuffer();

        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
        if(token.isAuthenticated()){
            User u = (User) token.getPrincipal();
            userNameInfo.append("Welcome, "+u.getUsername());
        }else{
            userNameInfo.append("NA");
        }
        return userNameInfo;
    }

    /*private StringBuffer getOAuth2LoginInfo(Principal user){
        StringBuffer protectedInfo = new StringBuffer();
        OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
        OAuth2AuthorizedClient authClient = this.authorizedClientService
            .loadAuthorizedClient(
                authToken.getAuthorizedClientRegistrationId(),
                authToken.getName()
        );

        OAuth2User principal = ((OAuth2AuthenticationToken) user).getPrincipal();
        if(authToken.isAuthenticated()){
            OidcIdToken idToken = getIdToken(principal);
            Map<String, Object> userAttributes = ((DefaultOAuth2User)authToken.getPrincipal()).getAttributes();
            
            String userToken = authClient.getAccessToken().getTokenValue();
            protectedInfo.append("Welcome, "+userAttributes.get("name")+"<br><br>");
            protectedInfo.append("email : "+getIdToken(principal).getEmail()+"<br><br>");
            protectedInfo.append("token : "+userToken+"<br><br>");

            if(idToken != null){
                Map<String, Object> claims = idToken.getClaims();

                for (String key : claims.keySet()) {
                    protectedInfo.append("  " + key + ": " + claims.get(key)+"<br>");
                }
            }
        }else{
            protectedInfo.append("NA");
        }
        return protectedInfo;
    }

    private OidcIdToken getIdToken(OAuth2User principal){
        if(principal instanceof DefaultOidcUser){
            DefaultOidcUser oidcUser = (DefaultOidcUser)principal;
            return oidcUser.getIdToken();
        }
        return null;
    }*/
}
