package com.springapp.mvc.DAO.Implementation;


import com.springapp.mvc.DAO.Interfaces.UserDao;
import com.springapp.mvc.Entities.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity findById(int id) {
        return entityManager.find(UserEntity.class, id);
    }

    public UserEntity findBySSO(String sso) {
        UserEntity userEntity = null;
        List<UserEntity> list = entityManager.createQuery("FROM UserEntity u WHERE u.ssoId=:sso",UserEntity.class)
                .setParameter("sso", sso)
                .getResultList();
        Iterator iterator = list.iterator();
        if (iterator.hasNext()) {
            userEntity = (UserEntity) iterator.next();

        }

        System.out.println(userEntity);
        return userEntity;

    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
