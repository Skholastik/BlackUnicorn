package com.springapp.mvc.Config.CustomSecure;
import java.util.ArrayList;
import java.util.List;


import com.springapp.mvc.Entities.UserEntity;
import com.springapp.mvc.Entities.UserRoles;

import com.springapp.mvc.Service.Interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService service;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UserEntity user = service.findBySso(username);
        System.out.println(user);
        if (user == null)
            throw new UsernameNotFoundException("user name not found");
        return buildUserFromUserEntity(user);
    }

    private User buildUserFromUserEntity(UserEntity user) {

        String username = user.getSsoId();
        String password = user.getPassword();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        UserRoles role=user.getRole();
        List<UserRoles> roles = new ArrayList<UserRoles>();
        System.out.println(role);
        roles.add(role);

        User springUser = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,roles );
        return springUser;
    }

    public UserService getService() {
        return service;
    }

    public void setService(UserService service) {
        this.service = service;
    }
}
