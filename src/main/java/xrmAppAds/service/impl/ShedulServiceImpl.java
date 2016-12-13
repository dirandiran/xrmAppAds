package xrmAppAds.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xrmAppAds.model.Ads;
import xrmAppAds.repository.AdRepository;
import xrmAppAds.service.api.AdService;
import xrmAppAds.service.api.ShedulService;


/**
 * Created by diran on 09.12.2016.
 */
@Component
public class ShedulServiceImpl implements ShedulService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private AdService adService;

    @Scheduled(fixedRate=1000*60*60)
    @Override
    public void actualizeInfo() {
        try {
            List<Ads> oldDatainDB = adService.getAllAds();
            Integer totalAds = 0;
            if(oldDatainDB.size() == 0)
            {
                Integer pageCol = 0;
                List<String> urlArr = new ArrayList<>();
                Ads adsEntity = new Ads();
                String endLine = pageCol + "/";
                String url = "http://monetki.su/component/option,com_adsmanager/page,show_all/text_search,/order,0/expand,0/Itemid,39/limit,20/limitstart," + endLine;
                Document doc = Jsoup.connect(url).get();
                
                Integer listLengthOld = 0;
                Integer listLengthNew = 1;

                while (oldDatainDB.size()<=totalAds) {
                    Elements ads = doc.getElementsByClass("adsmanager_table_description");
                    Elements div_pars = ads.select("div");
                    Elements href_pars = div_pars.select("a[href]");

                    int i = 0;
                    for (Element element : href_pars) {
                        urlArr.add(element.attr("href"));
                        Document document = Jsoup.connect(urlArr.get(i)).get();
                        i++;
                        Elements ad = document.getElementsByClass("adsmanager_ads_title");
                        Elements price = document.getElementsByClass("adsmanager_ads_price");
                        Elements name = document.getElementsByClass("adsmanager_ads_contact");
                        adsEntity.setPrice(price.text());
                        adsEntity.setAdText(ad.text());
                        adsEntity.setContacts(name.text().substring(10));
                        adRepository.save(adsEntity);
                    }
                    pageCol += 20;
                    endLine = pageCol + "/";
                    url = "http://monetki.su/component/option,com_adsmanager/page,show_all/text_search,/order,0/expand,0/Itemid,39/limit,20/limitstart," + endLine;
                    doc = Jsoup.connect(url).get();
                }
            }

        }
        catch (IOException ex) {
            System.out.println("Что то не так");
        }
    }
}
