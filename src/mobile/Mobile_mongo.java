/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import mobile.Review_mongo;
import org.bson.Document;

/**
 *
 * @author michael
 */
public class Mobile_mongo {
     
    private String phone_name = new String();
    private ArrayList<Review_mongo> reviews = new ArrayList<Review_mongo>();
    private Map<String, Integer> positive;
    private Map<String, Integer> negative;
    private Document mobile;
    private int review_count = 0;

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count() {
        this.review_count = this.mobile.getInteger("review_count");
    }
    
    public Mobile_mongo(Document doc){
        this.mobile = doc;
        this.setPhone_name();
        this.setPositive();
        this.setNegative(); 
        this.setReview_count();
    }
    

    public String getPhone_name() {
        return phone_name;
    }

    public void setPhone_name() {
        this.phone_name = mobile.getString("phone_name");
    }

    public ArrayList<Review_mongo> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review_mongo> reviews) {
        this.reviews = reviews;
    }

    public Map<String, Integer> getPositive() {
        return positive;
    }

    public void setPositive() {
        Map<String, Integer> pos = this.mobile.get("positive", Map.class);
        /*
        for (Map.Entry<String, Integer> p : pos.entrySet()){
           System.out.print(p.getKey() + ":" + p.getValue() + "\n");
        }
        */
        this.positive = pos;
    }

    public Map<String, Integer> getNegative() {
        return negative;
    }

    public void setNegative() {
        Map<String, Integer> neg = this.mobile.get("negative", Map.class);
        /*
        for (Map.Entry<String, Integer> n : neg.entrySet()){
           System.out.print(n.getKey() + ":" + n.getValue() + "\n");
        }
        */
        this.negative = neg;
    }
 
}
