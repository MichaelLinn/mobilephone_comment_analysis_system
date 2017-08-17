/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile;



import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mobile.Mobile_brand_mongo;
import org.bson.Document;


/**
 *
 * @author michael
 */

public class Review_mongo {
    
    private Date comment_time = new Date();
    private String source = new String();
    private String review = new String();
    
    public Review_mongo(){
        
    }
    
    public Review_mongo(String commet_time, String source, String review) throws ParseException{
        this.source = source;
        this.review = review;
        this.setComment_time(commet_time);
    }
    
    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) throws ParseException {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DateFormat fmt1 = new SimpleDateFormat("于 yyyy年MM月dd日");
        Date date =null;
        try {
            date = fmt.parse(comment_time);
            
        } catch (ParseException ex) {
            Logger.getLogger(Review_mongo.class.getName()).log(Level.SEVERE, null, ex);
            date = fmt1.parse(comment_time);
        }
        this.comment_time = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
    
    /*
    public static void main(String args[]){
        MongoDatabase md = Mobile_brand_mongo.connect_to_DB();
        MongoCollection mc = md.getCollection("HUAWEI");
        FindIterable<Document> findIteration = mc.find();
        MongoCursor<Document> cursor = findIteration.iterator();
        Document doc = cursor.next();
        //Mobile_mongo mobile = new Mobile_mongo(doc);
        //System.out.println(mobile.getReviews());
        ArrayList<Document> reviews = doc.get("reviews", ArrayList.class);
        //System.out.println(doc.get("reviews"));
        
        String commment_time = reviews.get(0).getString("comment_time");
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = fmt.parse(commment_time);
        } catch (ParseException ex) {
            Logger.getLogger(Review_mongo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(d);
    }
    */
}
