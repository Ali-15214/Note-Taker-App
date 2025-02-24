package com.model;



import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(length = 5000)
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;
    private String password;
    private String email;



    public Note() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }


    public Note(String content, String title, Date addedDate,String password,String email){

        this.title = title;
        this.content = content;
        this.addedDate = addedDate;
        this.password=password;
        this.email=email;
    }
}
