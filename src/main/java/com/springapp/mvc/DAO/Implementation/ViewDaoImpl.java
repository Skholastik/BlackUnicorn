package com.springapp.mvc.DAO.Implementation;


import com.springapp.mvc.DAO.Interfaces.ViewDao;
import com.springapp.mvc.Entities.View;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("viewDaoImpl")
public class ViewDaoImpl implements ViewDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public View getView(String type) {
        return entityManager.createQuery("FROM View v WHERE v.type=:type", View.class)
                .setParameter("type", type).getSingleResult();
    }

    @Override
    public List<View> getViewList() {
        return entityManager.createQuery("From View",View.class).getResultList();
    }
}
