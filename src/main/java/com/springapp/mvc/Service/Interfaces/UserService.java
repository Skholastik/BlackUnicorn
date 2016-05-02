package com.springapp.mvc.Service.Interfaces;

import com.springapp.mvc.Entities.UserEntity;

public interface UserService {

    UserEntity findById(int id);
    UserEntity findBySso(String sso);

}
