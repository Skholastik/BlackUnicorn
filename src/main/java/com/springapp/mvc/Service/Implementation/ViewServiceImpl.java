package com.springapp.mvc.Service.Implementation;


import com.springapp.mvc.DAO.Interfaces.ViewDao;
import com.springapp.mvc.Entities.View;
import com.springapp.mvc.Service.Interfaces.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ViewServiceImpl implements ViewService {

    @Autowired
    ViewDao viewDao;

    @Override
    public List<View> getViewList() {
        return viewDao.getViewList();
    }
}
