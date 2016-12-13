package xrmAppAds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xrmAppAds.service.api.AdService;
import java.util.logging.Logger;

/**
 * Created by diran on 08.12.2016.
 */
@Controller
@RequestMapping(value = "/")
public class MainController  {

    @Autowired
    private AdService adService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView actionGet() {

        Logger log = Logger.getLogger(MainController.class.getName());
        return new ModelAndView("index");
    }

}
