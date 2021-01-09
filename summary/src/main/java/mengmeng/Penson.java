package mengmeng;

import lombok.Data;

/**
 * @Author:peimengmeng
 * @Date: 2020/12/5 10:47
 */
//@Data
public class Penson implements Cloneable{
    private String name;
    private Address address;

    public Penson() {
    }

    public Penson(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Penson peyson = null;
        try{
            peyson = (Penson) super.clone();
            peyson.setAddress((Address) peyson.getAddress().clone());
        } catch (Exception e){
            e.printStackTrace();
        }
        return peyson;
    }
}
