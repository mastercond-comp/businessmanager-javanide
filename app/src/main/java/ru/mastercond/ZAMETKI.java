package ru.mastercond;

import java.util.ArrayList;
import java.util.List;
 

public class ZAMETKI {

   private String zametkaName;
   private String opisanieName;
   private String sdelkaIDD;
   private String zametkaDATA;
   private String zametkaIZBR;
   private String idnumber;
 
  public ZAMETKI(String zametkaName, String opisanieName, String sdelkaIDD, String zametkaDATA, String zametkaIZBR, String idnumber) {
  this.zametkaName=zametkaName;
  this.opisanieName=opisanieName;
  this.sdelkaIDD=sdelkaIDD;
  this.zametkaDATA=zametkaDATA;
  this.zametkaIZBR=zametkaIZBR;
  this.idnumber=idnumber;
   }
 
   public String getzametkaName() {
       return zametkaName;
   }
   
   public void setzametkaName(String zametkaName) {
       this.zametkaName = zametkaName;
   }
   
   
   public String getopisanieName() {
       return opisanieName;
   }
   
   public void setopisanieName(String opisanieName) {
       this.opisanieName = opisanieName;
   }
   
   public String getsdelkaIDD() {
   //вставить sql-конвертер 
       return sdelkaIDD;
   }
   
   public void setsdelkaIDD(String sdelkaIDD) {
       this.sdelkaIDD = sdelkaIDD;
   }
   
   public String getzametkaDATA() {
       return zametkaDATA;
   }
   
   public void setzametkaDATA(String zametkaDATA) {
       this.zametkaDATA = zametkaDATA;
   }
   
   public String getzametkaIZBR() {
       return zametkaIZBR;
   }
   
   public void setzametkaIZBR(String zametkaIZBR) {
       this.zametkaIZBR = zametkaIZBR;
   }
   
   public String getidnumber() {
       return idnumber;
   }

}
