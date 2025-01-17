package com.sahelyfr.eataweekback.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sahelyfr.eataweekback.infrastructure.UserRepository;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;

@Configuration
public class ApplicationConfiguration {

  private final UserRepository userRepository;

  public ApplicationConfiguration(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Bean
  UserDetailsService userDetailsService() {
    return username -> userRepository.findByEmail(username)
        .orElseThrow(
            () -> new UsernameNotFoundException(String.format("User not found with email %s", username)));
  }

  @Bean
  BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Bean
  ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
    return new ObservedAspect(observationRegistry);
  }
}
