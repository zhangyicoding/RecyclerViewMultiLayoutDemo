package com.estyle.recyclerviewmultilayoutdemo.bean;

public class ItemBean {

    private String title;
    private int iconId;

    public ItemBean() {
    }

    public ItemBean(String title, int iconId) {
        this.title = title;
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
