package com.example.mahmoudmostafa.myapplication.model;

/**
 * Created by Mahmoud Mostafa on 9/3/2017.
 */

public class News {


    private String id;
    private String type;
    private String section_id;
    private String sectionName;
    private String Date;
    private String title;
    private String webUrl;


    private String author;

    public News() {

    }

    public News(String id, String type, String section_id, String sectionName,
                String date, String title, String webUrl, String author) {
        this.id = id;
        this.type = type;
        this.section_id = section_id;
        this.sectionName = sectionName;
        Date = date;
        this.title = title;
        this.webUrl = webUrl;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

}
