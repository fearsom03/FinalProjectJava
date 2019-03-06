package com.spring.controllers;

import com.spring.beans.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CampusHelperController {
    @Autowired
    UserBean dbbean;

    @RequestMapping(value = "/teachersPage", method = RequestMethod.GET)
    public ModelAndView teachersPage() {
        ModelAndView mw = new ModelAndView("CampusHelper/teachers_firstPage");
        return mw;
    }

    @RequestMapping(value = "/teachers_studentListPage", method = RequestMethod.GET)
    public ModelAndView teachersStudents() {
        ModelAndView mw = new ModelAndView("CampusHelper/teachers_studentlistPage");
        return mw;
    }
    @RequestMapping(value = "/SomePage", method = RequestMethod.GET)
    public ModelAndView somePage() {
        ModelAndView mw = new ModelAndView("CampusHelper/SomePage");
        return mw;
    }


}
