/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile;



import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.util.Date;



/**
 *
 * @author michael
 */


public class Review_mongo {
    
    public static final String mongodb_address = new String("192.168.200.47");
        
    private Date comment_time = new Date();
    private String source = new String();
    private String review = new String();

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
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
        
}
