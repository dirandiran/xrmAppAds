package xrmAppAds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xrmAppAds.model.Ads;
import xrmAppAds.service.api.AdService;

import java.util.List;

/**
 * Created by diran on 08.12.2016.
 */
@RequestMapping("/index")
@RestController
public class RestContr {

  @Autowired
  private AdService adService;

    @ResponseBody
    @RequestMapping(value = "/ajaxAll", method = RequestMethod.POST)
    public List<Ads> qetAllAds()
    {
        return adService.getAllAds();
    }

   @ResponseBody
   @RequestMapping(value = "/ajaxAd", method = RequestMethod.POST)
   public List<Ads> qetAd(@RequestBody Ads adText)
   {

       System.out.println(adText.getAdText());
       return adService.findAds("%"+adText.getAdText()+"%");
   }
}
