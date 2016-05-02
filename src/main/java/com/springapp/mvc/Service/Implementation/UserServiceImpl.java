package com.springapp.mvc.Service.Implementation;



import com.springapp.mvc.DAO.Interfaces.UserDao;
import com.springapp.mvc.Entities.UserEntity;
import com.springapp.mvc.Service.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserEntity findById(int id) {
        return userDao.findById(id);
    }

    public UserEntity findBySso(String sso) {
        return userDao.findBySSO(sso);
    }

    public UserDao getDao() {
        return userDao;
    }

    public void setDao(UserDao dao) {
        this.userDao = dao;
    }
}
