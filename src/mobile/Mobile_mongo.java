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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private Map<String, Double> feature_faRate;
    private Document mobile;
    private String faverable_rate;
    private int review_count = 0;

    public Map<String, Double> getFeature_faRate() {
        return feature_faRate;
    }

    public void setFeature_faRate() {
        Map<String, Double> rate = new HashMap();  
        
        for (String key: this.positive.keySet()){
            int pos_n = this.positive.get(key);
            int neg_n = 0;
            try{
                neg_n = this.negative.get(key);
            }catch(Exception e){
                neg_n = 0;
            }
            double fa_rate = 1.0 * pos_n / (pos_n + neg_n);
            rate.put(key, fa_rate);
        }
        this.feature_faRate = rate;
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count() {
        this.review_count = this.mobile.getInteger("review_count");
    }
    
    public Mobile_mongo(Document doc) throws ParseException{
        this.mobile = doc;
        this.setPhone_name();
        this.setPositive();
        this.setNegative(); 
        this.setReview_count();
        this.setFeature_faRate();
    }

    public String getPhone_name() {
        return phone_name;
    }

    public void setPhone_name() {
        this.phone_name = mobile.getString("phone_name");
    }

    public ArrayList<Review_mongo> getReviews() {
        return this.reviews;
    }

    public void setReviews() throws ParseException {
        ArrayList<Document> rs = this.mobile.get("reviews", ArrayList.class);
        for (Document doc: rs){
            String comment_time = doc.getString("comment_time");
            String rev = doc.getString("review");
            String source = doc.getString("source");
            Review_mongo re = new Review_mongo(comment_time, source, rev);
            this.reviews.add(re);
        }
    }
    
    public double countTotalFaverableRate(){
        
        int faverable_count = 0;
        int difference_count = 0;
        for (String key: this.positive.keySet()){
            faverable_count += this.positive.get(key);
            try{
                difference_count += this.negative.get(key);
            }catch(Exception e){
                difference_count += 0;                
            }
     
        }     
        DecimalFormat df = new DecimalFormat("0.00");
        double fa_rate = 1.0 * faverable_count / ( faverable_count + difference_count) ;
        // System.out.println("%%%%%%%%%%%" + fa_rate + "%%%%%%%%%%%");
        String rate = df.format((fa_rate * 100)) + "%";
        this.setFaverable_rate(rate);
        
        return fa_rate;
    }

    public String getFaverable_rate() {
        return faverable_rate;
    }

    public void setFaverable_rate(String faverable_rate) {
        this.faverable_rate = faverable_rate;
    }

    public Map<String, Integer> getPositive() {
        return this.positive;
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
        return this.negative;
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
