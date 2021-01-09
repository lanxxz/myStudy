package mengmeng;

import lombok.Data;

/**
 * @Author:peimengmeng
 * @Date: 2020/12/5 10:48
 */
//@Data
public class Address implements Cloneable{
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
