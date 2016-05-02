package com.springapp.mvc.DAO.Implementation;


import com.springapp.mvc.DAO.Interfaces.UnitOfMeasurementDao;
import com.springapp.mvc.Entities.UnitOfMeasurement;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("unitOfMeasurementDaoImpl")
public class UnitOfMeasurementDaoImpl implements UnitOfMeasurementDao {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public UnitOfMeasurement getUnitOfMeasurement(String name) {
        return entityManager.createQuery("FROM UnitOfMeasurement u WHERE u.name=:name", UnitOfMeasurement.class)
                .setParameter("name", name).getSingleResult();
    }

    @Override
    public UnitOfMeasurement addUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        entityManager.persist(unitOfMeasurement);
        entityManager.flush();
        return unitOfMeasurement;
    }

    @Override
    public boolean checkUnitOfMeasurementExists(String name) {
        return entityManager.createQuery("FROM UnitOfMeasurement u WHERE u.name=:name", UnitOfMeasurement.class)
                .setParameter("name", name).getResultList().size() == 1;

    }
}
