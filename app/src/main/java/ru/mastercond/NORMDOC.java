package ru.mastercond;

import java.util.ArrayList;
import java.util.List;
 

public class NORMDOC {

   private String docName;
   private String opisanieName;
   private String fileName;
   private String idnumber;
 
  public NORMDOC(String docName, String opisanieName, String fileName, String idnumber) {
  this.docName=docName;
  this.opisanieName=opisanieName;
  this.fileName=fileName;
  this.idnumber=idnumber;
   }
 
   public String getdocName() {
       return docName;
   }
   
   public void setdocName(String docName) {
       this.docName = docName;
   }
   
   
   public String getopisanieName() {
       return opisanieName;
   }
   
   public void setopisanieName(String opisanieName) {
       this.opisanieName = opisanieName;
   }
   
   public String getfileName() {
       return fileName;
   }
   
   public void setfileName(String fileName) {
       this.fileName = fileName;
   }
   
   public String getidnumber() {
       return idnumber;
   }

}
