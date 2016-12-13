package xrmAppAds.repository;

import org.springframework.data.repository.CrudRepository;
import xrmAppAds.model.Ads;

import java.util.List;

/**
 * Created by diran on 08.12.2016.
 */
public interface AdRepository extends CrudRepository<Ads, Integer> {

    List<Ads> findAdByAdTextLike(String adText);

}
