package com.example.listycitylab3;

import java.io.Serializable;

public class City implements Serializable {
    private String cityName;
    private String province;

    public City(String cityName, String province) {
        this.cityName = cityName;
        this.province = province;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return cityName + ", " + province;
    }

}
