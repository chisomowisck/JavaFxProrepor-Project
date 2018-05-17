/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prorepor.addstudent;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author SLyT
 */
public class AddStudent {

    private final StringProperty sid;
    private final StringProperty fname;
    private final StringProperty lname;
    private final StringProperty gender;
    private final StringProperty form;
    private final StringProperty clas;

//Default Constructor
    public AddStudent(String sid, String fname, String lname, String gender, String form, String clas) {
        this.sid = new SimpleStringProperty(sid);
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
        this.gender = new SimpleStringProperty(gender);
        this.form = new SimpleStringProperty(form);
        this.clas = new SimpleStringProperty(clas);
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

    public String getgender() {
        return gender.get();
    }

    public String getform() {
        return form.get();
    }

    public String getclas() {
        return clas.get();
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

    public void setgender(String value) {
        gender.set(value);
    }

    public void setform(String value) {
        form.set(value);
    }

    public void setclas(String value) {
        clas.set(value);
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

    public StringProperty genderProperty() {
        return gender;
    }

    public StringProperty formProperty() {
        return form;
    }

    public StringProperty clasProperty() {
        return clas;
    }

}
