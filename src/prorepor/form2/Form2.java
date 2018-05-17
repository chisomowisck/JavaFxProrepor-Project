/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prorepor.form2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SLyT
 */
public class Form2 {
     private final StringProperty sid;
    private final StringProperty fname;
    private final StringProperty lname;
    private final StringProperty subject;
    private final StringProperty score;
    private final StringProperty grade;
    private final StringProperty comment;

//Default Constructor
    public Form2(String sid, String fname, String lname, String subject, String score, String grade,String comment) {
        this.sid = new SimpleStringProperty(sid);
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
        this.subject = new SimpleStringProperty(subject);
        this.score = new SimpleStringProperty(score);
        this.grade = new SimpleStringProperty(grade);
        this.comment = new SimpleStringProperty(comment);
    }
//Getters

    public String getSid() {
        return sid.get();
    }

    public String getfname() {
        return fname.get();
    }

    public String getlname() {
        return lname.get();
    }

    public String getsubject() {
        return subject.get();
    }

    public String getscore() {
        return score.get();
    }

    public String getgrade() {
        return grade.get();
    }
    public String getcomment() {
        return comment.get();
    }

//Stters
    public void setSid(String value) {
        sid.set(value);
    }

    public void setfname(String value) {
        fname.set(value);
    }

    public void setlname(String value) {
        lname.set(value);
    }

    public void setsubject(String value) {
        subject.set(value);
    }

    public void score(String value) {
        score.set(value);
    }

    public void setgrade(String value) {
        grade.set(value);
    }
    public void setcomment(String value) {
        comment.set(value);
    }

//PropertyValues
    public StringProperty sidProperty() {
        return sid;
    }

    public StringProperty fnameProperty() {
        return fname;
    }

    public StringProperty lnameProperty() {
        return lname;
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public StringProperty scoreProperty() {
        return score;
    }

    public StringProperty gradeProperty() {
        return grade;
    }
    
     public StringProperty commentProperty() {
        return comment;
    }
}
