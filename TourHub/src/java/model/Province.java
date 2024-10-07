/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoang
 */
public class Province {

    private int province_id;
    private String province_name;
    private int visit_count;
    private String image_url;

    public Province() {
    }

    public Province(int province_id, String province_name, int visit_count, String image_url) {
        this.province_id = province_id;
        this.province_name = province_name;
        this.visit_count = visit_count;
        this.image_url = image_url;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public int getVisit_count() {
        return visit_count;
    }

    public void setVisit_count(int visit_count) {
        this.visit_count = visit_count;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "Province{" + "province_id=" + province_id + ", province_name=" + province_name + ", visit_count=" + visit_count + ", image_url=" + image_url + '}';
    }

}
