/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile;

/**
 *
 * @author michael
 */
public class Page_data {
    
    private String product_name ;
    private String profile_url;
    private String nickname;
    private String page_date;

    public String getPage_date() {
        return page_date;
    }

    public void setPage_date(String page_date) {
        this.page_date = page_date;
    }
    private String page_body;
    
    private int count;

    @Override
    public String toString() {
        return "Page_data{" + "product_name=" + product_name + ", profile_url=" + profile_url + ", nickname=" + nickname + ", page_date=" + page_date + ", page_body=" + page_body + ", count=" + count + '}';
    }


    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPage_body() {
        return page_body;
    }

    public void setPage_body(String page_body) {
        this.page_body = page_body;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
}
