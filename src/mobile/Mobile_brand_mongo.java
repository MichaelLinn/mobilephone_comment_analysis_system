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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import mobile.Mobile_mongo;
import static mobile.Review_mongo.mongodb_address;
import org.bson.Document;
/**
 *
 * @author michael
 */
public class Mobile_brand_mongo {
    
    public static final String mongodb_address = new String("192.168.200.47");
    private ArrayList<Mobile_mongo> mobiles = new ArrayList<Mobile_mongo>();
    private String mobile_brand = new String();
    
    
    public Mobile_brand_mongo(String mobile_brand){
        this.setMobile_brand(mobile_brand);
        this.setMobiles();
    }
    
    public Mobile_brand_mongo(){
        
    }
    
    public Map count_type_reviews(){
        Map mobile_type = new HashMap();
        for (Mobile_mongo type: this.mobiles){
            mobile_type.put(type.getPhone_name(), type.getReview_count());
        }
        return mobile_type;
    }
    
    public ArrayList<Mobile_mongo> getMobiles() {
        return mobiles;
    }

    public void setMobiles() {
        Document doc;
        MongoDatabase DB = Mobile_brand_mongo.connect_to_DB();
        MongoCollection collection = DB.getCollection(this.mobile_brand);
        FindIterable<Document> findIteration = collection.find();
        MongoCursor<Document> cursor = findIteration.iterator();
        while(cursor.hasNext()){
            doc = cursor.next();
            Mobile_mongo mobile = new Mobile_mongo(doc);
            this.mobiles.add(mobile);
        }
    }

    public String getMobile_brand() {
        return mobile_brand;
    }

    public void setMobile_brand(String mobile_brand) {
        this.mobile_brand = mobile_brand;
    }
    
    public static MongoDatabase connect_to_DB(){
        MongoDatabase mongoDB = null;
        try{
            // Connect to mongodb
            MongoClient mongoClient = new MongoClient(mongodb_address, 27017);
            // Connect to database
            mongoDB = mongoClient.getDatabase("spider");
            System.out.println("Connect to database successfully!");
            
        }catch(Exception e){
            System.err.println(e.getClass() + ": " + e.getMessage());
        }
        return mongoDB;
    }
    public static ArrayList<String> get_all_brand(){
        ArrayList<String> brands = new ArrayList<String>();
        MongoDatabase db = Mobile_brand_mongo.connect_to_DB();
        MongoCollection mc = db.getCollection("mobile_brand");
        FindIterable<Document> findIteration = mc.find();
        MongoCursor<Document> cursor = findIteration.iterator();
        while(cursor.hasNext()){
            brands.add(cursor.next().getString("brand_name"));
        } 
        
        return brands;
    }
    
    
    public static void main(String args[]){
        Mobile_brand_mongo mbm = new Mobile_brand_mongo("APPLE");
            for (Mobile_mongo mobile: mbm.mobiles){
                System.out.println(mobile.getPhone_name());
            }        
    }
}
