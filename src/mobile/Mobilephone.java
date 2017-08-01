/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author michael
 */
public class Mobilephone {

    public static final String url = "jdbc:mysql://192.168.200.206:3306/lenovoforum2?useUnicode=true&characterEncoding=UTF-8";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "123456";

    private ArrayList<String> product_name = new ArrayList<String>();
    private ArrayList<String> category_name = new ArrayList<String>();
    private ArrayList<Integer> pos_product = new ArrayList<Integer>();
    private ArrayList<Integer> neg_product = new ArrayList<Integer>();
    private ArrayList<Page_data> p_datas = new ArrayList<Page_data>();
    private ArrayList<Mobile_info> mobile_infos_lenovo;
    private ArrayList<Mobile_info> mobile_infos_zol;
    private ArrayList<Mobile_info> mobile_infos_all;

    public ArrayList<Mobile_info> getMobile_infos_zol() {
        return mobile_infos_zol;
    }

    public ArrayList<Mobile_info> getMobile_infos_all() {
        return mobile_infos_all;
    }

    public Mobilephone() {

    }

    public ArrayList<String> getProduct_name() {
        return this.product_name;
    }

    public ArrayList<Page_data> getP_datas() {
        return p_datas;
    }

    public ArrayList<Mobile_info> getMobile_infos_lenovo() {
        return mobile_infos_lenovo;
    }

    public ArrayList<Integer> getPos_product() {
        return this.pos_product;
    }

    public ArrayList<Integer> getNeg_product() {
        return this.neg_product;
    }

    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Mobilephone test = new Mobilephone();
        test.stat_product_name();
        test.analyse_mobile(test.product_name.get(0));
    }

    public static Connection connect_to_DB() {
        Connection conn = null;
        try {
            Class.forName(name);  // MySQL Driver
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successed!");

        } catch (Exception e) {
            System.out.println("MySQL Error:" + e.getMessage());
        }
        return conn;
    }

    public void setP_datas(ArrayList<Page_data> p_datas) {
        this.p_datas = p_datas;
    }

    public Map stat_ZOL_forum() throws SQLException {
        // show the number of the mobile phone records collected from the Lenovo Online Forum
        Map product_map = new HashMap();
        ArrayList<Mobile_info> ms = new ArrayList<Mobile_info>();
        Connection conn = Mobilephone.connect_to_DB();
        PreparedStatement pst = null;
        String sql = "select  product_name, count(product_name) as count from "
                + "lf_target JOIN lf_page on lf_target.target_id = lf_page.target_id GROUP BY product_name ";
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            product_map.put(rs.getString("product_name"), rs.getInt("count"));
            Mobile_info m = new Mobile_info();
            m.setProduct_name(rs.getString("product_name"));
            m.setRecord_number(rs.getInt("count"));
            ms.add(m);
        }
        /*for (Object key : product_map.keySet()) {
            System.out.println(key.toString() + " : " + product_map.get(key).toString());
        }*/
        this.mobile_infos_zol = ms;
        return product_map;
    }

    public Map stat_lenovo_forum() throws SQLException {
        // show the number of the mobile phone records collected from the Lenovo Online Forum
        ArrayList<Mobile_info> ms = new ArrayList<Mobile_info>();
 
        Map product_map = new HashMap();
        Connection conn = Mobilephone.connect_to_DB();
        PreparedStatement pst = null;
        String sql = "select  product_name, count(product_name) as count from lf_target JOIN sum_results "
                + "on lf_target.target_id = sum_results.target_id GROUP BY product_name";
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Mobile_info m = new Mobile_info();
            m.setProduct_name(rs.getString("product_name"));
            m.setRecord_number(rs.getInt("count"));
            ms.add(m);
            product_map.put(rs.getString("product_name"), rs.getInt("count"));
        }
        this.mobile_infos_lenovo = ms;
        /*for (Object key : product_map.keySet()) {
            System.out.println(key.toString() + " : " + product_map.get(key).toString());
        }*/
        return product_map;
    }

    public void stat_pos_neg_comments() throws SQLException {
        // calculate the number the negative and postitive comments
        Connection conn = Mobilephone.connect_to_DB();
        PreparedStatement pst = null;
        String sql = "select product_name,SUM(pos_num) as pos,SUM(neg_num) as neg from lf_target "
                + "join sum_results on lf_target.target_id = sum_results.target_id GROUP BY product_name;";
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            this.product_name.add(rs.getString("product_name"));
            this.pos_product.add(rs.getInt("pos"));
            this.neg_product.add(rs.getInt("neg"));
        }
        /*for (int i = 0; i < this.product_name.size(); i++) {
            System.out.println(this.product_name.get(i) + " " + this.pos_product.get(i).toString() + " " + this.neg_product.get(i).toString());
        }*/
    }

    public void gen_comment_table_view() throws SQLException {
        // generate the table for showing the collected comments from the page
        Connection conn = Mobilephone.connect_to_DB();
        PreparedStatement pst = null;
        ArrayList<Page_data> p_datas = new ArrayList<Page_data>();
        String sql = "select profile_url,product_name,nickname ,page_date,page_body from lf_page join lf_target"
                + " join lf_author join lf_site on lf_author.author_id = lf_page.author_id and lf_page.target_id = lf_target.target_id and lf_site.site_id = lf_author.site_id";
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Page_data p_data = new Page_data();
            p_data.setProfile_url(rs.getString("profile_url"));
            p_data.setProduct_name(rs.getString("product_name"));
            p_data.setNickname(rs.getString("nickname"));
            p_data.setPage_date(rs.getString("page_date"));
            p_data.setPage_body(rs.getString("page_body").trim());
            p_datas.add(p_data);
        }
        this.p_datas = p_datas;
        
    }
    
    public Map stat_photography() throws SQLException{
        Connection conn = Mobilephone.connect_to_DB();
        Map photo_map = new HashMap();
        PreparedStatement pst = null;
        String sql = "SELECT child_category,sum(num) as sum from lf_target as a join sum_results as b on a.target_id = b.target_id WHERE category = \"拍照\" GROUP BY child_category";
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            photo_map.put(rs.getString("child_category"), rs.getInt("sum"));
        }
        for (Object key : photo_map.keySet()) {
            System.out.println(key.toString() + " : " + photo_map.get(key).toString());
        }
        return photo_map;
    }
    
    public Map stat_software() throws SQLException{
        Connection conn = Mobilephone.connect_to_DB();
        Map software_map = new HashMap();
        PreparedStatement pst = null;
        String sql = "SELECT child_category,sum(num) as sum from lf_target as a join sum_results as b on a.target_id = b.target_id WHERE category = \"软件\" GROUP BY child_category";
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            software_map.put(rs.getString("child_category"), rs.getInt("sum"));
        }
        for (Object key : software_map.keySet()) {
            System.out.println(key.toString() + " : " + software_map.get(key).toString());
        }
        return software_map;
    }
    
    public Map stat_hardware() throws SQLException{
        Connection conn = Mobilephone.connect_to_DB();
        Map hardware_map = new HashMap();
        PreparedStatement pst = null;
        String sql = "SELECT child_category,sum(num) as sum from lf_target as a join sum_results as b on a.target_id = b.target_id WHERE category = \"硬件\" GROUP BY child_category";
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            hardware_map.put(rs.getString("child_category"), rs.getInt("sum"));
        }
        for (Object key : hardware_map.keySet()) {
            System.out.println(key.toString() + " : " + hardware_map.get(key).toString());
        }
        return hardware_map;
    }
    
    public Map stat_apperance() throws SQLException{
        Connection conn = Mobilephone.connect_to_DB();
        Map apperance_map = new HashMap();
        PreparedStatement pst = null;
        String sql = "SELECT child_category,sum(num) as sum from lf_target as a join sum_results as b on a.target_id = b.target_id WHERE category = \"外观\" GROUP BY child_category";
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            apperance_map.put(rs.getString("child_category"), rs.getInt("sum"));
        }
        for (Object key : apperance_map.keySet()) {
            System.out.println(key.toString() + " : " + apperance_map.get(key).toString());
        }
        return apperance_map;
    }
    
    public Map stat_all_category() throws SQLException{
        Connection conn = Mobilephone.connect_to_DB();
        ArrayList<Mobile_info> ms = new ArrayList<Mobile_info>();
        Map category_map = new HashMap();
        PreparedStatement pst = null;
        String sql = "SELECT category, sum(num) as sum from sum_results GROUP BY category";
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            category_map.put(rs.getString("category"), rs.getInt("sum"));
            Mobile_info m = new Mobile_info();
            m.setProduct_name(rs.getString("category"));
            m.setRecord_number(rs.getInt("sum"));
            ms.add(m);
        }
        this.mobile_infos_all = ms;
        /*for (Object key : category_map.keySet()) {
            System.out.println(key.toString() + " : " + category_map.get(key).toString());
        }*/
        return category_map;
    }
    
    public void analyse_mobile(String mobile_category) throws SQLException{
        Connection conn = Mobilephone.connect_to_DB();
        PreparedStatement pst = null;
        String sql = "SELECT category,SUM(neg_num) as n,SUM(pos_num) as p from lf_target as a join sum_results as b on a.target_id = b.target_id where product_name =\"";
        sql += mobile_category;
        sql += "\" GROUP BY category";
        System.out.println(sql);
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            this.category_name.add(rs.getString("category"));
            this.pos_product.add(rs.getInt("p"));
            this.neg_product.add(rs.getInt("n"));
            System.out.println(rs.getString("category") + " " + rs.getInt("p") +" " + rs.getInt("n"));
        }    
        
    }

    public ArrayList<String> getCategory_name() {
        return category_name;
    }
    
    public void stat_product_name() throws SQLException{
        Connection conn = Mobilephone.connect_to_DB();
        ArrayList <String> category_name = new ArrayList<String>();
        ArrayList<Integer> num_neg = new ArrayList<Integer>();
        ArrayList<Integer> num_pos = new ArrayList<Integer>();
        PreparedStatement pst = null;   
        String sql = "SELECT DISTINCT(product_name) FROM lf_target as a JOIN sum_results as b on a.target_id = b.target_id";
        ArrayList<String> product_name = new ArrayList<String>();
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            product_name.add(rs.getString("product_name"));
            System.out.println(rs.getString("product_name"));
        }
        this.product_name = product_name;
    }
}
