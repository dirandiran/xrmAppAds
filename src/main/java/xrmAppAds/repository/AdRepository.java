package xrmAppAds.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import xrmAppAds.model.Ads;



/**
 * Created by diran on 08.12.2016.
 */
public interface AdRepository extends CrudRepository<Ads, Integer> {

    List<Ads> findAdsByAdTextLike(String adText);
    //Ads findAdsByIdLike (Integer id);
}
