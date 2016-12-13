package xrmAppAds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xrmAppAds.model.Ads;
import xrmAppAds.repository.AdRepository;
import xrmAppAds.service.api.AdService;

import java.io.PrintWriter;
import java.util.List;

/**
 * Created by diran on 08.12.2016.
 */
@Service
public class AdServiceImpl implements AdService {

    @Autowired
    AdRepository adRepository;

    public List<Ads> findAds(String text)
    {
        return adRepository.findAdByAdTextLike(text); // разобраться!
    }

    public List<Ads> getAllAds()
    {

        List<Ads> ads = (List<Ads>)adRepository.findAll();

        return ads;
    }
}
