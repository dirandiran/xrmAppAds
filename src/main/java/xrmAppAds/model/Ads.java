package xrmAppAds.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by diran on 08.12.2016.
 */
@Entity
@Table(name = "ads")
public class Ads implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "adText")
    private String adText;

    @Column(name = "price")
    private String price;

    @Column(name = "contacts")
    private String contacts;


    public Integer getId() {
        return id;
    }

    public String getAdText() {
        return adText;
    }

    public void setAdText(String adText) {
        this.adText = adText;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
