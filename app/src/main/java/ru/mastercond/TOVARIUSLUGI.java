package ru.mastercond;

import java.util.ArrayList;
import java.util.List;
 

public class TOVARIUSLUGI {

   private String Name;
   private String Type;
   private String Price;
   private String Valuta;
   private String idnumber;
 
  public TOVARIUSLUGI(String Name, String Type, String Price, String Valuta, String idnumber) {
  this.Name=Name;
  this.Type=Type;
  this.Price=Price;
  this.Valuta=Valuta;
  this.idnumber=idnumber;
   }
 
   public String getName() {
       return Name;
   }
   
   public void setName(String Name) {
       this.Name = Name;
   }
   
   
   public String getType() {
       return Type;
   }
   
   public void setType(String Type) {
       this.Type = Type;
   }
   
   public String getPrice() {
       return Price;
   }
   
   public void setPrice(String Price) {
       this.Price = Price;
   }
   
   public String getValuta() {
       return Valuta;
   }
   
   public void setValuta(String Valuta) {
       this.Valuta = Valuta;
   }
   
   public String getidnumber() {
       return idnumber;
   }

}
