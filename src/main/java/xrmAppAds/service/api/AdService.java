package xrmAppAds.service.api;

import xrmAppAds.model.Ads;

import java.util.List;

/**
 * Created by diran on 08.12.2016.
 */
public interface AdService {
    List<Ads> findAds(String text);
    List<Ads> getAllAds();
}
