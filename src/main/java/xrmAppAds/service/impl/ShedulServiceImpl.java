package xrmAppAds.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xrmAppAds.controller.MainController;
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
        Logger log = Logger.getLogger(MainController.class.getName());
        try {
            List<Ads> oldDataInDB = adService.getAllAds();
            Integer totalAds = 0;
            Integer pageCol = 0;
            if(oldDataInDB.size() == 0)
            {
                List<String> urlArr = new ArrayList<>();
                String endLine = pageCol + "/";
                String url = "http://monetki.su/component/option,com_adsmanager/page,show_all/text_search,/order,0/expand,0/Itemid,39/limit,20/limitstart," + endLine;
                Document doc = Jsoup.connect(url).get();

                while (pageCol<=totalAds) {
                    Elements ads = doc.getElementsByClass("adsmanager_table_description");
                    Elements div_pars = ads.select("div");
                    Elements href_pars = div_pars.select("a[href]");

                    int pos = 0;
                    for (Element element : href_pars) {
                        urlArr.add(element.attr("href"));
                        Document document = Jsoup.connect(urlArr.get(pos)).get();
                        pos++;
                        Elements ad = document.getElementsByClass("adsmanager_ads_title");
                        Elements price = document.getElementsByClass("adsmanager_ads_price");
                        Elements name = document.getElementsByClass("adsmanager_ads_contact");
                        Ads adsEntity = new Ads();
                        adsEntity.setPrice(price.text());
                        adsEntity.setAdText(ad.text());
                        adsEntity.setContacts(name.text().substring(10));
                        adRepository.save(adsEntity);
                        totalAds++;
                    }
                    pageCol += 20;
                    endLine = pageCol + "/";
                    url = "http://monetki.su/component/option,com_adsmanager/page,show_all/text_search,/order,0/expand,0/Itemid,39/limit,20/limitstart," + endLine;
                    doc = Jsoup.connect(url).get();
                }
            }
            else
            {
                totalAds = oldDataInDB.size();
                pageCol = totalAds/20;
                Integer urlPage = pageCol*20;
                List<String> urlArr = new ArrayList<>();
                String endLine = urlPage + "/";
                String url = "http://monetki.su/component/option,com_adsmanager/page,show_all/text_search,/order,0/expand,0/Itemid,39/limit,20/limitstart," + endLine;
                Document doc = Jsoup.connect(url).get();
                while (urlPage<=totalAds) {
                    Elements ads = doc.getElementsByClass("adsmanager_table_description");
                    Elements div_pars = ads.select("div");
                    Elements href_pars = div_pars.select("a[href]");

                    int pos = totalAds - urlPage;
                    for (Element element : href_pars) {
                        urlArr.add(element.attr("href"));
                        Document document = Jsoup.connect(urlArr.get(pos)).get();
                        pos++;
                        Elements ad = document.getElementsByClass("adsmanager_ads_title");
                        Elements price = document.getElementsByClass("adsmanager_ads_price");
                        Elements name = document.getElementsByClass("adsmanager_ads_contact");
                        Ads adsEntity = new Ads();
                        adsEntity.setPrice(price.text());
                        adsEntity.setAdText(ad.text());
                        adsEntity.setContacts(name.text().substring(10));
                        adRepository.save(adsEntity);
                        totalAds++;
                    }
                    urlPage += 20;
                    endLine = urlPage + "/";
                    url = "http://monetki.su/component/option,com_adsmanager/page,show_all/text_search,/order,0/expand,0/Itemid,39/limit,20/limitstart," + endLine;
                    doc = Jsoup.connect(url).get();
                }
            }

        }
        catch (IOException ex) {
            log.info("Can't establish a connection! Tru again later, or make sure that the Internet is OK");
        }
    }
}
