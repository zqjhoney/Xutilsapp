package com.bwie.xutilsapp.bean;

/**
 * Created by 张乔君 on 2017/9/4.
 */

public class TopBean {
    public String name;
    public String id;
    public boolean state;

    public TopBean(String id, String name, boolean state) {
        this.name = name;
        this.id = id;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
