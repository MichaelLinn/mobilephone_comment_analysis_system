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
public class Mobile_category_info {

    private String category;
    private int neg_num;
    private int pos_num;
    private String pos_percent;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNeg_num() {
        return neg_num;
    }

    public void setNeg_num(int neg_num) {
        this.neg_num = neg_num;
    }

    public int getPos_num() {
        return pos_num;
    }

    public void setPos_num(int pos_num) {
        this.pos_num = pos_num;
    }

    public String getPos_percent() {
        return pos_percent;
    }

    public void setPos_percent() {
        int percent = (int) ( this.pos_num / (this.neg_num + this.pos_num *1.0) * 100);
        String p = String.valueOf(percent) + "%";
        this.pos_percent = p;
    }

}
