package com.springapp.mvc.DAO.Interfaces;

import com.springapp.mvc.Entities.UnitOfMeasurement;


public interface UnitOfMeasurementDao {

    UnitOfMeasurement getUnitOfMeasurement(String name);
    UnitOfMeasurement addUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement);
    boolean checkUnitOfMeasurementExists(String name);
}
