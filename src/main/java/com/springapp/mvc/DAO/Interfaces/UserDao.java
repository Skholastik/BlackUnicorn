package com.springapp.mvc.DAO.Interfaces;

import com.springapp.mvc.Entities.UserEntity;

public interface UserDao {

    UserEntity findById(int id);
    UserEntity findBySSO(String sso);

}