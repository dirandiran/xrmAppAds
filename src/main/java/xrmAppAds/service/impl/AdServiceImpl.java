package xrmAppAds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xrmAppAds.model.Ads;
import xrmAppAds.repository.AdRepository;
import xrmAppAds.service.api.AdService;



/**
 * Created by diran on 08.12.2016.
 */
@Service
public class AdServiceImpl implements AdService {

    @Autowired
    AdRepository adRepository;

    public List<Ads> findAds(String text)
    {
        return adRepository.findAdsByAdTextLike(text);
    }

    public List<Ads> getAllAds()
    {

        List<Ads> ads = (List<Ads>)adRepository.findAll();

        return ads;
    }
   /* public Ads findById(Integer id)
    {
        return adRepository.findAdsByIdLike(id);
    }*/
}
