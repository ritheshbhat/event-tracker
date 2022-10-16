package com.sample.rest.Security;

import com.sample.rest.Models.Role;
import com.sample.rest.Models.User;
import com.sample.rest.Repo.UserRepo;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    public CustomDetailsService(UserRepo userRepo){
        this.userRepo=userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String utaIdOrEmail) throws UsernameNotFoundException {
      User user=  userRepo.findByUtaIdOrEmail(utaIdOrEmail,utaIdOrEmail)
                .orElseThrow(()->new UsernameNotFoundException("User not found with username or email"+utaIdOrEmail));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
