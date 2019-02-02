package ru.mastercond;


import java.util.ArrayList;
import java.util.List;


//SDELKI (ID Integer Primary Key Autoincrement NOT NULL, K_FULLNAME Varchar(255),K_SOKRNAME Varchar(255),K_INN Varchar(255),K_KPP Varchar(255),K_OGRN Varchar(255),K_BANKNAME Varchar(255),K_BANKBIK Varchar(255),K_BANKKS Varchar(255),K_BANKRS Varchar(255),K_RUKDOLZHN Varchar(255),K_VLICE Varchar(255),K_FIORUK Varchar(255),K_URADDR Varchar(255),K_FACTADDR Varchar(255),K_POSTADDR Varchar(255),K_PHONE Varchar(255),K_MOBILE Varchar(255),K_EMAIL Varchar(255),K_SITE Varchar(255), K_OTVETSTVENNIJ Varchar(255),MY_FULLNAME Varchar(255),MY_SOKRNAME Varchar(255),MY_INN Varchar(255),MY_KPP Varchar(255),MY_OGRN Varchar(255),MY_BANKNAME Varchar(255),MY_BANKBIK Varchar(255),MY_BANKKS Varchar(255),MY_BANKRS Varchar(255),MY_RUKDOLZHN Varchar(255),MY_VLICE Varchar(255),MY_FIORUK Varchar(255),MY_URADDR Varchar(255),MY_FACTADDR Varchar(255),MY_POSTADDR Varchar(255),MY_PHONE Varchar(255),MY_MOBILE Varchar(255),MY_EMAIL Varchar(255),MY_SITE Varchar(255), MY_USLUGIPO Varchar(10000), MY_SLOGAN Varchar(1000), MY_LOGOIMG Varchar(2000), MY_PECHATIMG Varchar(2000), NOMERSDELKI Varchar(255), KP_DATA Varchar(255),KP_SPECUSLOVIJA Varchar(10000), SCHET_DATA Varchar(255), SCHET_USLOVIJA Varchar(10000), DOGOVOR_DATA, DOGOVOR_GOROD Varchar(255), DOGOVOR_SROKIISPOLNENIJA Varchar(10000), DOGOVOR_SROKIOPLATI Varchar(10000), DOGOVOR_GARANTIJA Varchar(10000), DOGOVOR_OSOBIJEUSLOVIJA Varchar(10000), DOGOVOR_ARBITRSUD Varchar(1000), NAK1_DATA Varchar(255), NAK2_DATA Varchar(255), AKT1_DATA Varchar(255), AKT2_DATA Varchar(255), SDELKA_ENDGOD Varchar(255), SDELKA_STATUS Varchar(255), TZAKL_DATA Varchar(255), TZAKL_TEKST Varchar(10000))

 
public class Sdelki {
 
   private String kontragentName;
   private String innName;
   private String bankName;
   private String idnumber;
 
  public Sdelki(String kontragentName, String innName, String bankName, String idnumber) {
  this.kontragentName=kontragentName;
  this.innName=innName;
  this.bankName=bankName;
  this.idnumber=idnumber;
   }
 
   public String getkontragentName() {
       return kontragentName;
   }
   
   public void setkontragentName(String kontragentName) {
       this.kontragentName = kontragentName;
   }
   
   
   public String getinnName() {
       return innName;
   }
   
   public void setinnName(String innName) {
       this.innName = innName;
   }
   
   public String getbankName() {
       return bankName;
   }
   
   public void setbankName(String bankName) {
       this.bankName = bankName;
   }
   
   public String getidnumber() {
       return idnumber;
   }
   
}