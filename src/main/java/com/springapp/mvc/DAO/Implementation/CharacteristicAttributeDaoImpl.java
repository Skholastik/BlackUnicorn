package com.springapp.mvc.DAO.Implementation;

import com.springapp.mvc.DAO.Interfaces.CharacteristicAttributeDao;
import com.springapp.mvc.Entities.CharacteristicAttribute;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("characteristicAttributeDaoImpl")
public class CharacteristicAttributeDaoImpl implements CharacteristicAttributeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CharacteristicAttribute> getAttributeList() {
        return entityManager.createQuery("FROM CharacteristicAttribute", CharacteristicAttribute.class)
                .getResultList();
    }

    @Override
    public CharacteristicAttribute getAttribute(int id) {
        return entityManager.createQuery("FROM CharacteristicAttribute c WHERE c.id=:id", CharacteristicAttribute.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public CharacteristicAttribute addAttribute(CharacteristicAttribute attribute) {
        entityManager.persist(attribute);
        entityManager.flush();
        return attribute;
    }


}
