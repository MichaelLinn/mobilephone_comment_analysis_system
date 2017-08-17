/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.awt.event.MouseEvent;
import mobile.Mobilephone;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import mobile.Mobile_category_info;
import mobile.Mobile_info;
import mobile.Page_data;

// MongoDB
import mobile.Mobile_brand_mongo;
import mobile.Mobile_mongo;
import mobile.Review_mongo;

/**
 *
 * @author michael
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private MenuItem mi1;
    @FXML
    private Tab t1;
    @FXML
    private Tab t2;
    @FXML
    private Tab t3;
    @FXML
    private Tab t4;
    @FXML
    private Tab t5;
    @FXML
    private Tab t6;
    @FXML
    private Tab t7;
    @FXML
    private Tab t8;
    @FXML
    private TabPane tp;
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button start_button;
    @FXML
    private Button mobile_button;
    @FXML
    private CategoryAxis cAxis;
    @FXML
    private NumberAxis num_axis;
    @FXML
    private StackedBarChart bc;
    @FXML
    private StackedBarChart mobile_sbc;
    @FXML
    private PieChart pc1;
    @FXML
    private PieChart pc2;
    @FXML
    private PieChart sub_pc1;
    @FXML
    private PieChart sub_pc11;

    @FXML
    private TableView tv;
    @FXML
    private TableView sub_tv1;
    @FXML
    private TableView sub_tv11;
    @FXML
    private TableView tv_all;
    @FXML
    private TableView category_tv;
    
    @FXML
    private Label summary;
    @FXML
    private BarChart barc;
    @FXML
    private BarChart barc1;
    /*@FXML
    private BarChart barc2;
    @FXML
    private BarChart barc3;
    @FXML
    private BarChart barc4;*/

    @FXML
    private BarChart soft_bc;
    @FXML
    private BarChart hard_bc;

    @FXML
    private ChoiceBox cbox;

    @FXML
    private ProgressBar pb;
    @FXML
    private ProgressIndicator pi;

    @FXML
    private ImageView image;
    
    @FXML
    private ChoiceBox cb_b1;
    @FXML
    private ChoiceBox cb_b2;
    
    @FXML
    private PieChart pc_br1;
    @FXML
    private PieChart pc_br2;
    @FXML 
    private ChoiceBox cb_type;
    @FXML
    private ComboBox cb_brand;
    @FXML
    private ComboBox cbr_brand;
    @FXML
    private ComboBox cbr_type;
    @FXML
    private ComboBox cb_fa;
    @FXML
    private NumberAxis na;
    @FXML
    private StackedBarChart sbc_fa;
    
    @FXML
    private void handleMenuAction(ActionEvent event) throws SQLException, ParseException {

        //PieChart
        Mobilephone mobile = new Mobilephone();
        Map product_map = mobile.stat_ZOL_forum();

        for (Object key : product_map.keySet()) {
            double temValue = Double.valueOf(product_map.get(key).toString());
            ObservableList<PieChart.Data> temPiechartData
                    = FXCollections.observableArrayList(new PieChart.Data(key.toString(), temValue));
            pc1.getData().addAll(temPiechartData);
            //sub_pc1.getData().addAll(temPiechartData);
        }

        // sub_piechart_zol
        for (Object key : product_map.keySet()) {
            double temValue = Double.valueOf(product_map.get(key).toString());
            ObservableList<PieChart.Data> temPiechartData
                    = FXCollections.observableArrayList(new PieChart.Data(key.toString(), temValue));
            sub_pc11.getData().addAll(temPiechartData);
            sub_pc11.setTitle("中关村论坛数据概况");
        }

        product_map = mobile.stat_lenovo_forum();
        for (Object key : product_map.keySet()) {
            double temValue = Double.valueOf(product_map.get(key).toString());
            ObservableList<PieChart.Data> temPiechartData
                    = FXCollections.observableArrayList(new PieChart.Data(key.toString(), temValue));
            pc2.getData().addAll(temPiechartData);
        }
        // sub_piechart_lenovo
        product_map = mobile.stat_lenovo_forum();
        for (Object key : product_map.keySet()) {
            double temValue = Double.valueOf(product_map.get(key).toString());
            ObservableList<PieChart.Data> temPiechartData
                    = FXCollections.observableArrayList(new PieChart.Data(key.toString(), temValue));
            sub_pc1.getData().addAll(temPiechartData);
            sub_pc1.setTitle("联想论坛数据概况");
        }

        //StackedBarChart
        XYChart.Series<String, Number> series1 = new XYChart.Series();
        XYChart.Series<String, Number> series2 = new XYChart.Series();

        mobile.stat_pos_neg_comments();
        ArrayList<String> product_name = mobile.getProduct_name();
        ArrayList<Integer> pos_product = mobile.getPos_product();
        ArrayList<Integer> neg_product = mobile.getNeg_product();
        for (int i = 0; i < product_name.size(); i++) {
            int pos_percent = (int) ((1.0 * pos_product.get(i) / (pos_product.get(i) + neg_product.get(i))) * 100);
            int neg_percent = 100 - pos_percent;
            series1.getData().addAll(new XYChart.Data<>(product_name.get(i), pos_percent));
            series2.getData().addAll(new XYChart.Data<>(product_name.get(i), neg_percent));
        }

        series1.setName("正面评价百分数");
        series2.setName("负面评价百分数");

        bc.getData().addAll(series2);
        bc.getData().addAll(series1);


        //TableView
        mobile.gen_comment_table_view();
        ObservableList<Page_data> list = FXCollections.observableArrayList();
        ArrayList<Page_data> p_datas = mobile.getP_datas();

        TableColumn col1 = new TableColumn("Profile_url");
        col1.setPrefWidth(90);
        TableColumn col2 = new TableColumn("Product_name");
        TableColumn col3 = new TableColumn("Nickname");
        TableColumn col4 = new TableColumn("Page_date");
        TableColumn col5 = new TableColumn("Page_content");

        col1.setCellValueFactory(new PropertyValueFactory("profile_url"));
        col2.setCellValueFactory(new PropertyValueFactory("product_name"));
        col3.setCellValueFactory(new PropertyValueFactory("nickname"));
        col4.setCellValueFactory(new PropertyValueFactory("page_date"));
        col5.setCellValueFactory(new PropertyValueFactory("page_body"));

        for (Page_data p : p_datas) {
            list.add(p);
        }
        tv.getColumns().addAll(col1, col2, col3, col4, col5);
        tv.setItems(list);

        // sub_tableView_lenovo
        ObservableList<Mobile_info> l = FXCollections.observableArrayList();
        ArrayList<Mobile_info> ms = mobile.getMobile_infos_lenovo();
        TableColumn c1 = new TableColumn("手机品牌");
        TableColumn c2 = new TableColumn("用户评论记录数");

        c1.setCellValueFactory(new PropertyValueFactory("product_name"));
        c2.setCellValueFactory(new PropertyValueFactory("record_number"));
        for (Mobile_info mi : ms) {
            l.add(mi);
        }
        sub_tv1.getColumns().addAll(c1, c2);
        sub_tv1.setItems(l);

        // sub_tableView_ZOL
        ObservableList<Mobile_info> l2 = FXCollections.observableArrayList();
        ArrayList<Mobile_info> mz = mobile.getMobile_infos_zol();
        TableColumn c11 = new TableColumn("手机品牌");
        TableColumn c21 = new TableColumn("用户评论记录数");

        c11.setCellValueFactory(new PropertyValueFactory("product_name"));
        c21.setCellValueFactory(new PropertyValueFactory("record_number"));
        for (Mobile_info mi : mz) {
            l2.add(mi);
        }
        sub_tv11.getColumns().addAll(c11, c21);
        sub_tv11.setItems(l2);

        //all_category_bar_chart
        Map category_map;
        category_map = mobile.stat_all_category();
        XYChart.Series category_series = new XYChart.Series();
        category_series.setName("记录数量");
        for (Object key : category_map.keySet()) {
            category_series.getData().add(new XYChart.Data(key.toString(), category_map.get(key)));
        }
        barc1.setTitle("手机功能数据统计");
        barc1.getData().add(category_series);
        
        //all_category_tableView
        ObservableList<Mobile_info> al = FXCollections.observableArrayList();
        ArrayList<Mobile_info> ma = mobile.getMobile_infos_all();
        TableColumn ca1 = new TableColumn("手机功能");
        TableColumn ca2 = new TableColumn("用户评论记录数");

        ca1.setCellValueFactory(new PropertyValueFactory("product_name"));
        ca2.setCellValueFactory(new PropertyValueFactory("record_number"));
        for (Mobile_info mi : ma) {
            al.add(mi);
        }
        tv_all.getColumns().addAll(ca1, ca2);
        tv_all.setItems(al);

        //photo bar_chart
        Map photo_map;
        photo_map = mobile.stat_photography();
        XYChart.Series photo_series = new XYChart.Series();
        photo_series.setName("记录数量");
        for (Object key : photo_map.keySet()) {
            photo_series.getData().add(new XYChart.Data(key.toString(), photo_map.get(key)));
        }

        //barc.setTitle("拍照功能数据统计");
        //barc.getData().add(photo_series);

        //software bar_chart
        Map software_map;
        software_map = mobile.stat_software();
        XYChart.Series software_series = new XYChart.Series();
        software_series.setName("记录数量");
        for (Object key : software_map.keySet()) {
            software_series.getData().add(new XYChart.Data(key.toString(), software_map.get(key)));
        }
       // barc2.setTitle("软件功能数据统计");
        //barc2.getData().add(software_series);

        //hardware bar_chart
        Map hardware_map;
        hardware_map = mobile.stat_hardware();
        XYChart.Series hardware_series = new XYChart.Series();
        hardware_series.setName("记录数量");
        for (Object key : hardware_map.keySet()) {
            hardware_series.getData().add(new XYChart.Data(key.toString(), hardware_map.get(key)));
        }
        //barc3.setTitle("硬件功能数据统计");
        //barc3.getData().add(hardware_series);

        //apperance bar_chart
        Map apperance_map;
        apperance_map = mobile.stat_apperance();
        XYChart.Series apperance_series = new XYChart.Series();
        apperance_series.setName("记录数量");
        for (Object key : apperance_map.keySet()) {
            apperance_series.getData().add(new XYChart.Data(key.toString(), apperance_map.get(key)));
        }
        //barc4.setTitle("手机外观数据统计");
        //barc4.getData().add(apperance_series);
        
        // show choice bar
        
        
        // MongoDB
        ArrayList<String> brands;
        brands = Mobile_brand_mongo.get_all_brand();
        ObservableList<String> brand_name = FXCollections.observableArrayList();
        for (String tem: brands){
            brand_name.add(tem);
        }
        
        cb_b1.setItems(brand_name);
        cb_b2.setItems(brand_name);
        cbr_brand.setItems(brand_name);
        cbr_brand.getSelectionModel().select(0);
        
        cb_fa.setItems(brand_name);
        cb_fa.getSelectionModel().select(0);
        
        cb_b1.getSelectionModel().select(0);
        cb_b2.getSelectionModel().select(1);
        
        cb_brand.setItems(brand_name);
        cb_brand.getSelectionModel().select(0);
        this.handleComboBoxAction(event);
        // pie_char shows the percentage of different mobile type
        // pie_char_1
        pc_br1.setTitle("手机型号分布情况");
        this.handleBrandButton1(event);
        //pi_char_2
        pc_br2.setTitle("手机型号分布情况");
        this.handleBrandButton2(event);
        
    }
   
    @FXML
    private void handleCommentShowButton(ActionEvent event) throws ParseException{
        
        String brand;
        String type;
        
        ObservableList<String> mobile_cat = cbr_brand.getItems();
        
        brand = cbr_brand.getSelectionModel().selectedItemProperty().getValue().toString();
        type = cbr_type.getSelectionModel().selectedItemProperty().getValue().toString();
        
        ObservableList<Review_mongo> list = FXCollections.observableArrayList();
        ArrayList<Review_mongo> reviews = null;
        
        Mobile_brand_mongo mbm = new Mobile_brand_mongo(brand);
        
        for (Mobile_mongo mobile: mbm.getMobiles()){
            if (mobile.getPhone_name().equals(type)){
                mobile.setReviews();
                reviews = mobile.getReviews();
            }
        }
        
        TableColumn col1 = new TableColumn("source");
        col1.setPrefWidth(90);
        
        TableColumn col2 = new TableColumn("comment_time");
        TableColumn col3 = new TableColumn("review");


        col1.setCellValueFactory(new PropertyValueFactory("source"));
        col2.setCellValueFactory(new PropertyValueFactory("comment_time"));
        col3.setCellValueFactory(new PropertyValueFactory("review"));
;

        for (Review_mongo rm : reviews) {
            list.add(rm);
        }
        tv.getItems().clear();
        tv.getColumns().clear();
        tv.getColumns().addAll(col1, col2, col3);
        tv.setItems(list);
        
    } 
    
    @FXML
    public void startButton(ActionEvent event) throws InterruptedException, SQLException, ParseException {
        Double[] values = new Double[100];
        double t = 0;
        for (int i = 0; i < 100; i++) {
            t += 0.01;
            values[i] = t;
        }
        pb.setProgress(1);
        pi.setProgress(1);
        start_button.setText("分析完成");
        start_button.setDisable(true);
        mi1.setDisable(true);
        
        handleMenuAction(event);
        

    }
    
    @FXML
    public void handleComboBoxAction(ActionEvent event) throws ParseException{
        
        int brand_index = 0;
        String brand_selected;
        ObservableList<String>mobile_cat = cb_brand.getItems();
        brand_index = cb_brand.getSelectionModel().selectedIndexProperty().getValue();
        brand_selected = mobile_cat.get(brand_index);
        
        ArrayList<String> types = new ArrayList<String>();
        Mobile_brand_mongo mbm = new Mobile_brand_mongo(brand_selected);
        for (Mobile_mongo m: mbm.getMobiles()){
            types.add(m.getPhone_name());
        }       
        
        ObservableList<String> type_name = FXCollections.observableArrayList();
        for (String tem: types){
            type_name.add(tem);
        }
        cb_type.setItems(type_name);
        cb_type.getSelectionModel().select(0); 
              
    }
    
        @FXML
    public void handleComboBoxAction2(ActionEvent event) throws ParseException{
        
        int brand_index = 0;
        String brand_selected;
        ObservableList<String>mobile_cat = cbr_brand.getItems();
        brand_index = cbr_brand.getSelectionModel().selectedIndexProperty().getValue();
        brand_selected = mobile_cat.get(brand_index);
        
        ArrayList<String> types = new ArrayList<String>();
        Mobile_brand_mongo mbm = new Mobile_brand_mongo(brand_selected);
        for (Mobile_mongo m: mbm.getMobiles()){
            types.add(m.getPhone_name());
        }       
        
        ObservableList<String> type_name = FXCollections.observableArrayList();
        for (String tem: types){
            type_name.add(tem);
        }
        cbr_type.setItems(type_name);
        cbr_type.getSelectionModel().select(0); 
    }
    
    @FXML
    private void handleFaverableRateButton(ActionEvent event) throws ParseException{
        String brand = cb_fa.getSelectionModel().selectedItemProperty().getValue().toString();
        System.out.print("***********"+ brand +"**********");
        Mobile_brand_mongo mbm = new Mobile_brand_mongo(brand);
               
        XYChart.Series<String, Number> pos = new XYChart.Series();
        XYChart.Series<String, Number> neg = new XYChart.Series();
        // XYChart.Series<String, Number> t = new XYChart.Series();
        
        for (Mobile_mongo mobile: mbm.getMobiles()){
            
            int pos_percentage = (int)(mobile.countTotalFaverableRate() * 100);
            int neg_percentage = 100 - pos_percentage;
           
            pos.getData().addAll(new XYChart.Data<>(mobile.getPhone_name(), pos_percentage));
            neg.getData().addAll(new XYChart.Data<>(mobile.getPhone_name(), neg_percentage));
        }
        
        pos.setName("正面评价百分数");
        neg.setName("负面评价百分数");
        
        na.setAutoRanging(false);
        na.setUpperBound(100);
        
        sbc_fa.getData().clear();
        sbc_fa.getData().addAll(pos);
        sbc_fa.getData().addAll(neg);
               
    }
    
    @FXML
    public void handleBrandButton1(ActionEvent event) throws ParseException{
        int brand_index = 0;
        String brand_selected;
        ObservableList<String>mobile_cat = cb_b1.getItems();
        brand_index = cb_b1.getSelectionModel().selectedIndexProperty().getValue();
        brand_selected = mobile_cat.get(brand_index);
        pc_br1.getData().clear();
        Mobile_brand_mongo mbm = new Mobile_brand_mongo(brand_selected);
        Map mobile_types = mbm.count_type_reviews();
        for (Object key : mobile_types.keySet()) {
            int temValue = Integer.valueOf(mobile_types.get(key).toString());
            String k = key.toString().concat("(" + mobile_types.get(key).toString()+ ")");
            ObservableList<PieChart.Data> temPiechartData
                    = FXCollections.observableArrayList(new PieChart.Data(k, temValue));
            pc_br1.getData().addAll(temPiechartData);
        }

    }
    
    @FXML
    public void handleBrandButton2(ActionEvent event) throws ParseException{
        int brand_index = 0;
        String brand_selected;
        ObservableList<String>mobile_cat = cb_b2.getItems();
        brand_index = cb_b2.getSelectionModel().selectedIndexProperty().getValue();
        brand_selected = mobile_cat.get(brand_index);
        pc_br2.getData().clear();
        Mobile_brand_mongo mbm = new Mobile_brand_mongo(brand_selected);
        Map mobile_types = mbm.count_type_reviews();
        for (Object key : mobile_types.keySet()) {
            int temValue = Integer.valueOf(mobile_types.get(key).toString());
            String k = key.toString().concat("(" + mobile_types.get(key).toString()+ ")");
            ObservableList<PieChart.Data> temPiechartData
                    = FXCollections.observableArrayList(new PieChart.Data(k, temValue));
            pc_br2.getData().addAll(temPiechartData);
        }

    }
    

    @FXML
    public void handleMobileAnalysisButton(ActionEvent event) throws SQLException, ParseException {
        
        Mobilephone mobile = new Mobilephone();
        
        int index = 0;
        String brand_selected = null;
        String type_selected = null;
        // get mobile brand
        ObservableList<String> items = cb_brand.getItems();
        index = cb_brand.getSelectionModel().selectedIndexProperty().getValue();
        brand_selected = items.get(index);
        // get mobile type
        items = cb_type.getItems();
        index = cb_brand.getSelectionModel().selectedIndexProperty().getValue();
        type_selected = items.get(index);
        System.out.println("--------------" + type_selected + "--------------");

        
        Mobile_brand_mongo mbm = new Mobile_brand_mongo(brand_selected);
        Mobile_mongo mobile_type = null;
        for (Mobile_mongo m:mbm.getMobiles()){
            if (m.getPhone_name().trim().equals(type_selected.trim())){
                mobile_type = m;
                break;
            }
        }
        
        Map<String, Integer> pos_map = mobile_type.getPositive();
        Map<String, Integer> neg_map = mobile_type.getNegative();
        
        XYChart.Series<String, Number> pos1 = new XYChart.Series();
        XYChart.Series<String, Number> neg1 = new XYChart.Series();
        XYChart.Series<String, Number> t = new XYChart.Series();
        
        ArrayList<String> category = new ArrayList<String>();
        ArrayList<Integer> pos = new ArrayList<Integer>();
        ArrayList<Integer> neg = new ArrayList<Integer>();
        
        for (String key: pos_map.keySet()){
            category.add(key);
            pos.add(pos_map.get(key));
            neg.add(neg_map.get(key));
        }
        
        ObservableList<Mobile_category_info> list = FXCollections.observableArrayList();
        ArrayList<Mobile_category_info> ms = new ArrayList<Mobile_category_info>();  
        
        String s = new String("      该手机品牌为");
        s = s.concat(type_selected+"，其中");
        
        int pos_sum = 0;
        int neg_sum = 0;
        for (int i = 0; i < category.size(); i++) {
            //Mobile_category_info mci = new Mobile_category_info();
            pos_sum += pos.get(i);
            neg_sum += neg.get(i);
            int pos_percent = (int) ((1.0 * pos.get(i) / (pos.get(i) + neg.get(i))) * 100);
            int neg_percent = 100 - pos_percent;
            s = s.concat(category.get(i) + "属性好评率为" + pos_percent + "%，" + "差评率为" + neg_percent + "%；");
            pos1.getData().addAll(new XYChart.Data<>(category.get(i), pos_percent));
            neg1.getData().addAll(new XYChart.Data<>(category.get(i), neg_percent));
            Mobile_category_info m = new Mobile_category_info();
            m.setCategory(category.get(i));
            m.setPos_num(pos.get(i));
            m.setNeg_num(neg.get(i));
            m.setPos_percent();
            ms.add(m);   
        }
        
      
        int pos_sum_percent = (int)(100.0 * pos_sum / (pos_sum + neg_sum));
        s = s.concat("该手机的总好评率为" + pos_sum_percent + "%，总差评率为" + (100 - pos_sum_percent) + "%。");
        
        for(Mobile_category_info a : ms){
            list.add(a);
        }
        pos1.setName("正面评价百分数");
        neg1.setName("负面评价百分数");
        
        num_axis.setAutoRanging(false);
        num_axis.setUpperBound(100);
        
        mobile_sbc.getData().clear();
        mobile_sbc.getData().addAll(pos1);
        mobile_sbc.getData().addAll(neg1);
        TableColumn c1 = new TableColumn("手机品牌");
        TableColumn c2 = new TableColumn("正面评论数");
        TableColumn c3 = new TableColumn("负面评论数");
        TableColumn c4 = new TableColumn("正面百分比");
        
        c1.setCellValueFactory(new PropertyValueFactory("category"));
        c2.setCellValueFactory(new PropertyValueFactory("pos_num"));
        c3.setCellValueFactory(new PropertyValueFactory("neg_num"));
        c4.setCellValueFactory(new PropertyValueFactory("pos_percent"));
        
        category_tv.getColumns().clear();
        category_tv.getColumns().addAll(c1,c2,c3,c4);
        category_tv.setItems(list);
        
        
        summary.setWrapText(true);
        summary.setText(s);        
        // summary.appendText("Hello World！");
        
    }
    
        @FXML
    // unsolved problem : show the percentage for the piechart
    private void showPieChartPercentage(MouseEvent event) {
        Label caption = new Label("");
        caption.setTranslateX(event.getX());
        caption.setTranslateY(event.getY());
        caption.setText(String.valueOf(pc1.getData().get(0)) + "%");
    }

    @FXML
    private void handleBAction2(ActionEvent event) {

        tp.getSelectionModel().select(t2);

    }

    @FXML
    private void handleBAction(ActionEvent event) {

        tp.getSelectionModel().select(t1);

    }

    @FXML
    public void handleBAction3(ActionEvent event) {

        tp.getSelectionModel().select(t3);

    }

    @FXML
    public void handleBAction4(ActionEvent event) {

        tp.getSelectionModel().select(t4);

    }

    @FXML
    public void handleBAction5(ActionEvent event) {

        tp.getSelectionModel().select(t5);

    }

    @FXML
    public void handleBAction6(ActionEvent event) {

        tp.getSelectionModel().select(t6);

    }

    @FXML
    public void handleBAction7(ActionEvent event) {

        tp.getSelectionModel().select(t7);

    }

    @FXML
    public void handleBAction8(ActionEvent event) {

        tp.getSelectionModel().select(t8);

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mobile_sbc.getData().removeAll(0, 1);
    }

}
