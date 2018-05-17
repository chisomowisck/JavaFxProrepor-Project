package prorepor.home;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewStudentDetails {

    private final StringProperty sid;
    private final StringProperty fname;
    private final StringProperty lname;


//Default Constructor
public ViewStudentDetails(String sid,String fname,String lname){
this.sid =new SimpleStringProperty(sid);
this.fname =new SimpleStringProperty(fname);
this.lname =new SimpleStringProperty(lname);
}
//Getters
public String getSid(){
return sid.get();
}
public String getfname(){
return fname.get();
}
public String getlname(){
return lname.get();
}

//Stters
public void setSid(String value){
sid.set(value);
}
public void setfname(String value){
fname.set(value);
}
public void setlname(String value){
lname.set(value);
}

//PropertyValues
public StringProperty sidProperty(){
return sid;
}
public StringProperty fnameProperty(){
return fname;
}
public StringProperty lnameProperty(){
return lname;
}
}