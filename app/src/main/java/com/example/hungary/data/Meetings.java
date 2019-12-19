package com.example.hungary.data;

public class Meetings {
String name,subtitle,tumbnail,external_link;
String id , created;

    public Meetings(String name, String subtitle, String tumbnail, String external_link) {
        this.name = name;
        this.subtitle = subtitle;
        this.tumbnail = tumbnail;
        this.external_link = external_link;
    }

    public Meetings(String name, String subtitle, String tumbnail, String external_link, String id, String created) {
        this.name = name;
        this.subtitle = subtitle;
        this.tumbnail = tumbnail;
        this.external_link = external_link;
        this.id = id;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTumbnail() {
        return tumbnail;
    }

    public String getExternal_link() {
        return external_link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setTumbnail(String tumbnail) {
        this.tumbnail = tumbnail;
    }

    public void setExternal_link(String external_link) {
        this.external_link = external_link;
    }
}
