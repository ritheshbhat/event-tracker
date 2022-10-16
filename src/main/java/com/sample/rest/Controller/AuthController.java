package com.sample.rest.Controller;

import com.sample.rest.Models.Role;
import com.sample.rest.Models.User;
import com.sample.rest.Payload.LoginDto;
import com.sample.rest.Payload.SignupDto;
import com.sample.rest.Repo.RoleRepo;
import com.sample.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/tracker/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<String> authentication(@RequestBody LoginDto loginDto){

         Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUtaIdOrEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully", HttpStatus.OK);
    }

    @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody SignupDto signupDto){

        if(userRepo.existsByUtaId(signupDto.getUtaId())){
            return new ResponseEntity<>("UtaId is already taken",HttpStatus.BAD_REQUEST);
        }
        if(userRepo.existsByEmail(signupDto.getEmail())){
            return new ResponseEntity<>("Email is already taken",HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(signupDto.getEmail());
        user.setAddress(signupDto.getAddress());
        user.setZipcode(signupDto.getZipcode());
        user.setPhoneNumber(signupDto.getPhoneNumber());
        user.setState(signupDto.getState());
        user.setCountry(signupDto.getCountry());
        user.setUtaId(signupDto.getUtaId());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        Role roles=roleRepo.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        userRepo.save(user);
        return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
  }


}
