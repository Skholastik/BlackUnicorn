package com.springapp.mvc.DAO.Interfaces;

import com.springapp.mvc.Entities.View;

import java.util.List;

public interface ViewDao {

    View getView(String type);

    List<View> getViewList();
}
