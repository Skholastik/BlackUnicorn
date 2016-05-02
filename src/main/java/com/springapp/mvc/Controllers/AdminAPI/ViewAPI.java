package com.springapp.mvc.Controllers.AdminAPI;

import com.springapp.mvc.Entities.View;
import com.springapp.mvc.Service.Interfaces.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/view")
public class ViewAPI {

    @Autowired
    private ViewService viewService;

    @RequestMapping(value = "/getViewList", method = RequestMethod.GET)
    public List<View> getViewList() {
        return viewService.getViewList();
    }
}
