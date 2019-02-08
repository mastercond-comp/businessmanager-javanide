package ru.mastercond;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class SQLiteConnect extends SQLiteOpenHelper
{
  
    private static final int DATABASE_VERSION = 6;
  
    public static final String DATABASE_NAME="dbbusinessmanagement.db"; //имя базы данных


    public SQLiteConnect (Context context)
    {
        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db=this.getWritableDatabase();
        onUpgrade(db, 1, DATABASE_VERSION); //обновляем БД с версии 1 до новой версии
        onUpgrade(db, 3, DATABASE_VERSION);
        onUpgrade(db, 4, DATABASE_VERSION);
        onUpgrade(db, 5, DATABASE_VERSION);
        onUpgrade(db, 6, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS MYFIRMREKVIZITI (ID Integer Primary Key Autoincrement NOT NULL, FULLNAME Varchar(255),SOKRNAME Varchar(255),INN Varchar(255),KPP Varchar(255),OGRN Varchar(255),BANKNAME Varchar(255),BANKBIK Varchar(255),BANKKS Varchar(255),BANKRS Varchar(255),RUKDOLZHN Varchar(255),VLICE Varchar(255),FIORUK Varchar(255),URADDR Varchar(255),FACTADDR Varchar(255),POSTADDR Varchar(255),PHONE Varchar(255),MOBILE Varchar(255),EMAIL Varchar(255),SITE Varchar(255));"); //SQL-запрос на создание БД
        
        db.execSQL("CREATE TABLE IF NOT EXISTS KONTRAGENTI (ID Integer Primary Key Autoincrement NOT NULL, FULLNAME Varchar(255),SOKRNAME Varchar(255),INN Varchar(255),KPP Varchar(255),OGRN Varchar(255),BANKNAME Varchar(255),BANKBIK Varchar(255),BANKKS Varchar(255),BANKRS Varchar(255),RUKDOLZHN Varchar(255),VLICE Varchar(255),FIORUK Varchar(255),URADDR Varchar(255),FACTADDR Varchar(255),POSTADDR Varchar(255),PHONE Varchar(255),MOBILE Varchar(255),EMAIL Varchar(255),SITE Varchar(255), OTVETSTVENNIJ Varchar(255));"); //SQL-запрос на создание БД
        
        db.execSQL("CREATE TABLE IF NOT EXISTS TOVARI (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255),CENA Varchar(255),KOLVO Varchar(255),EDIZM Varchar(255));"); //SQL-запрос на создание БД
         
         db.execSQL("CREATE TABLE IF NOT EXISTS USLUGI (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255),CENA Varchar(255),KOLVO Varchar(255),EDIZM Varchar(255));"); //SQL-запрос на создание БД
         
         db.execSQL("CREATE TABLE IF NOT EXISTS SDELKI (ID Integer Primary Key Autoincrement NOT NULL, SDELKA_NAME Varchar(2000), K_FULLNAME Varchar(1000),K_SOKRNAME Varchar(255), K_INN Varchar(255), K_KPP Varchar(255), K_OGRN Varchar(255), K_BANKNAME Varchar(255), K_BANKBIK Varchar(255), K_BANKKS Varchar(255), K_BANKRS Varchar(255), K_RUKDOLZHN Varchar(255), K_VLICE Varchar(255), K_FIORUK Varchar(255), K_URADDR Varchar(1000), K_FACTADDR Varchar(1000), K_POSTADDR Varchar(1000), K_PHONE Varchar(255), K_MOBILE Varchar(255), K_EMAIL Varchar(255), K_SITE Varchar(255), K_OTVETSTVENNIJ Varchar(255), MY_FULLNAME Varchar(1000), MY_SOKRNAME Varchar(255), MY_INN Varchar(255), MY_KPP Varchar(255), MY_OGRN Varchar(255), MY_BANKNAME Varchar(255), MY_BANKBIK Varchar(255), MY_BANKKS Varchar(255), MY_BANKRS Varchar(255), MY_RUKDOLZHN Varchar(255), MY_VLICE Varchar(255), MY_FIORUK Varchar(255), MY_URADDR Varchar(255), MY_FACTADDR Varchar(1000), MY_POSTADDR Varchar(1000), MY_PHONE Varchar(255), MY_MOBILE Varchar(255), MY_EMAIL Varchar(255), MY_SITE Varchar(255), MY_OTVETSTVENNIJ Varchar(255), MY_SLOGAN Varchar(1000), MY_LOGOIMG Varchar(2000), MY_PECHATIMG Varchar(2000), SDELKA_NOMER Varchar(255), DOGOVOR_DATA Varchar(2000), TOVAR_SROKIPOSTAVKI Varchar (2000), USLUGI_SROKIOKAZANIJA Varchar(2000), TOVAR_USLOVIJAOPLATI Varchar(2000),USLUGI_USLOVIJAOPLATI Varchar(2000), TOVAR_USLOVIJAPRIEMKI Varchar(2000), USLUGI_USLOVIJAPRIEMKI Varchar(2000), GARANTIJA_USLOVIJA Varchar(2000), OSOBIJE_USLOVIJA Varchar(2000), SUD Varchar(1000));"); //SQL-запрос на создание БД
         
        db.execSQL("CREATE TABLE IF NOT EXISTS TOVARI_SDELKI (ID Integer Primary Key Autoincrement NOT NULL, IDD Varchar(255), NAME Varchar(255),CENA Varchar(255),KOLVO Varchar(255),EDIZM Varchar(255), NAKLADNAJA_NOMER Varchar(255) );"); //SQL-запрос на создание БД
         
         db.execSQL("CREATE TABLE IF NOT EXISTS USLUGI_SDELKI (ID Integer Primary Key Autoincrement NOT NULL, IDD Varchar(255),NAME Varchar(255),CENA Varchar(255),KOLVO Varchar(255),EDIZM Varchar(255), AKT_NOMER Varchar(255));"); //SQL-запрос на создание БД 
         
         db.execSQL("CREATE TABLE IF NOT EXISTS NORMDOC (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255), OPISANIE Varchar(1000), PRIMECHANIE Varchar(1000),FILENAME Varchar(1000));"); //SQL-запрос на создание БД
         
         db.execSQL("CREATE TABLE IF NOT EXISTS ZAMETKI (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255), OPISANIE Varchar(1000), DATA Varchar(1000),SDELKAIDD Varchar(1000), IZBR Varchar(1000));"); //SQL-запрос на создание БД
         
         db.execSQL("CREATE TABLE IF NOT EXISTS SETTINGS (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255), ZVALUE Varchar(1000));"); //SQL-запрос на создание БД
         
         //db.execSQL("INSERT OR REPLACE INTO SETTINGS ([ID], [NAME],[ZVALUE]) VALUES ('1','VIEWTYPE','TABLET'); ");
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) //действия при обновлении базы данных
    {
       if (oldVersion < 3) {
       
         db.execSQL("CREATE TABLE IF NOT EXISTS KONTRAGENTI (ID Integer Primary Key Autoincrement NOT NULL, FULLNAME Varchar(255),SOKRNAME Varchar(255),INN Varchar(255),KPP Varchar(255),OGRN Varchar(255),BANKNAME Varchar(255),BANKBIK Varchar(255),BANKKS Varchar(255),BANKRS Varchar(255),RUKDOLZHN Varchar(255),VLICE Varchar(255),FIORUK Varchar(255),URADDR Varchar(255),FACTADDR Varchar(255),POSTADDR Varchar(255),PHONE Varchar(255),MOBILE Varchar(255),EMAIL Varchar(255),SITE Varchar(255), OTVETSTVENNIJ Varchar(255));"); //SQL-запрос на создание БД
       }
       
       
       if (oldVersion < 4) {
         db.execSQL("CREATE TABLE IF NOT EXISTS TOVARI (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255),CENA Varchar(255),KOLVO Varchar(255),EDIZM Varchar(255));"); //SQL-запрос на создание БД
         
         db.execSQL("CREATE TABLE IF NOT EXISTS USLUGI (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255),CENA Varchar(255),KOLVO Varchar(255),EDIZM Varchar(255));"); //SQL-запрос на создание БД
         
       }
        
        if (oldVersion <= 3) {
         db.execSQL("CREATE TABLE IF NOT EXISTS SDELKI (ID Integer Primary Key Autoincrement NOT NULL, SDELKA_NAME Varchar(2000), K_FULLNAME Varchar(1000),K_SOKRNAME Varchar(255),K_INN Varchar(255),K_KPP Varchar(255),K_OGRN Varchar(255),K_BANKNAME Varchar(255),K_BANKBIK Varchar(255),K_BANKKS Varchar(255),K_BANKRS Varchar(255),K_RUKDOLZHN Varchar(255),K_VLICE Varchar(255),K_FIORUK Varchar(255),K_URADDR Varchar(1000),K_FACTADDR Varchar(1000),K_POSTADDR Varchar(1000),K_PHONE Varchar(255),K_MOBILE Varchar(255),K_EMAIL Varchar(255),K_SITE Varchar(255), K_OTVETSTVENNIJ Varchar(255),MY_FULLNAME Varchar(1000),MY_SOKRNAME Varchar(255),MY_INN Varchar(255),MY_KPP Varchar(255),MY_OGRN Varchar(255),MY_BANKNAME Varchar(255),MY_BANKBIK Varchar(255),MY_BANKKS Varchar(255),MY_BANKRS Varchar(255),MY_RUKDOLZHN Varchar(255),MY_VLICE Varchar(255),MY_FIORUK Varchar(255),MY_URADDR Varchar(255),MY_FACTADDR Varchar(1000),MY_POSTADDR Varchar(1000),MY_PHONE Varchar(255),MY_MOBILE Varchar(255),MY_EMAIL Varchar(255),MY_SITE Varchar(255),MY_OTVETSTVENNIJ Varchar(255), MY_SLOGAN Varchar(1000), MY_LOGOIMG Varchar(2000), MY_PECHATIMG Varchar(2000), SDELKA_NOMER Varchar(255), DOGOVOR_DATA Varchar(2000), TOVAR_SROKIPOSTAVKI Varchar (2000), USLUGI_SROKIOKAZANIJA Varchar(2000), TOVAR_USLOVIJAOPLATI Varchar(2000),USLUGI_USLOVIJAOPLATI Varchar(2000), TOVAR_USLOVIJAPRIEMKI Varchar(2000), USLUGI_USLOVIJAPRIEMKI Varchar(2000), GARANTIJA_USLOVIJA Varchar(2000), OSOBIJE_USLOVIJA Varchar(2000), SUD Varchar(1000));"); //SQL-запрос на создание БД
         
         db.execSQL("CREATE TABLE IF NOT EXISTS TOVARI_SDELKI (ID Integer Primary Key Autoincrement NOT NULL, IDD Varchar(255), NAME Varchar(255),CENA Varchar(255),KOLVO Varchar(255),EDIZM Varchar(255), NAKLADNAJA_NOMER Varchar(255) );"); //SQL-запрос на создание БД
         
         db.execSQL("CREATE TABLE IF NOT EXISTS USLUGI_SDELKI (ID Integer Primary Key Autoincrement NOT NULL, IDD Varchar(255),NAME Varchar(255),CENA Varchar(255),KOLVO Varchar(255),EDIZM Varchar(255), AKT_NOMER Varchar(255));"); //SQL-запрос на создание БД
       }
        
        if (oldVersion <= 4) {
         db.execSQL("CREATE TABLE IF NOT EXISTS NORMDOC (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255), OPISANIE Varchar(1000), PRIMECHANIE Varchar(1000),FILENAME Varchar(1000));"); //SQL-запрос на создание БД
        }
        
        if (oldVersion <= 5) {
         db.execSQL("CREATE TABLE IF NOT EXISTS ZAMETKI (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255), OPISANIE Varchar(1000), DATA Varchar(1000),SDELKAIDD Varchar(1000), IZBR Varchar(1000));"); //SQL-запрос на создание БД
        }
        
        if (oldVersion <= 6) {
        //db.execSQL("DROP TABLE SDELKI;");
        
         db.execSQL("CREATE TABLE IF NOT EXISTS SETTINGS (ID Integer Primary Key Autoincrement NOT NULL, NAME Varchar(255), ZVALUE Varchar(1000));"); //SQL-запрос на создание БД
         
        // db.execSQL("INSERT OR REPLACE INTO SETTINGS ([ID], [NAME],[ZVALUE]) VALUES ('1','VIEWTYPE','TABLET'); ");
        
        db.execSQL("CREATE TABLE IF NOT EXISTS SDELKI (ID Integer Primary Key Autoincrement NOT NULL, SDELKA_NAME Varchar(2000), K_FULLNAME Varchar(1000),K_SOKRNAME Varchar(255),K_INN Varchar(255),K_KPP Varchar(255),K_OGRN Varchar(255),K_BANKNAME Varchar(255),K_BANKBIK Varchar(255),K_BANKKS Varchar(255),K_BANKRS Varchar(255),K_RUKDOLZHN Varchar(255),K_VLICE Varchar(255),K_FIORUK Varchar(255),K_URADDR Varchar(1000),K_FACTADDR Varchar(1000),K_POSTADDR Varchar(1000),K_PHONE Varchar(255),K_MOBILE Varchar(255),K_EMAIL Varchar(255),K_SITE Varchar(255), K_OTVETSTVENNIJ Varchar(255),MY_FULLNAME Varchar(1000),MY_SOKRNAME Varchar(255),MY_INN Varchar(255),MY_KPP Varchar(255),MY_OGRN Varchar(255),MY_BANKNAME Varchar(255),MY_BANKBIK Varchar(255),MY_BANKKS Varchar(255),MY_BANKRS Varchar(255),MY_RUKDOLZHN Varchar(255),MY_VLICE Varchar(255),MY_FIORUK Varchar(255),MY_URADDR Varchar(255),MY_FACTADDR Varchar(1000),MY_POSTADDR Varchar(1000),MY_PHONE Varchar(255),MY_MOBILE Varchar(255),MY_EMAIL Varchar(255),MY_SITE Varchar(255),MY_OTVETSTVENNIJ Varchar(255), MY_SLOGAN Varchar(1000), MY_LOGOIMG Varchar(2000), MY_PECHATIMG Varchar(2000), SDELKA_NOMER Varchar(255), DOGOVOR_DATA Varchar(2000), TOVAR_SROKIPOSTAVKI Varchar (2000), USLUGI_SROKIOKAZANIJA Varchar(2000), TOVAR_USLOVIJAOPLATI Varchar(2000),USLUGI_USLOVIJAOPLATI Varchar(2000), TOVAR_USLOVIJAPRIEMKI Varchar(2000), USLUGI_USLOVIJAPRIEMKI Varchar(2000), GARANTIJA_USLOVIJA Varchar(2000), OSOBIJE_USLOVIJA Varchar(2000), SUD Varchar(1000));"); //SQL-запрос на создание БД
        
        
        }
    }






   //====================МОИ ОРГАНИЗАЦИИ====================

    public void AddMyFirm(String MyFullName , String MySokrName, String MyINN, String MyKPP, String MyOGRN, String MyBankName, String MyBankBIK, String MyBankKS, String MyBankRS, String MyRukDolzhn, String MyVlice, String MyFIOruk, String MyUrAddr, String MyFaktAddr, String MyPostAddr, String MyPhone, String MyMobile, String MyEmail, String MySite)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String id="1";
        db.execSQL("INSERT OR REPLACE INTO MYFIRMREKVIZITI ([ID],[FULLNAME],[SOKRNAME],[INN],[KPP],[OGRN],[BANKNAME],[BANKBIK],[BANKKS],[BANKRS],[RUKDOLZHN],[VLICE],[FIORUK],[URADDR],[FACTADDR],[POSTADDR],[PHONE],[MOBILE],[EMAIL],[SITE]) VALUES ('"+id+"' , '"+ MyFullName+"' , '"+ MySokrName+"', '"+MyINN+"', '"+MyKPP+"', '"+MyOGRN+"', '"+MyBankName+"', '"+MyBankBIK+"', '"+MyBankKS+"', '"+MyBankRS+"','"+MyRukDolzhn+"', '"+MyVlice+"', '"+MyFIOruk+"', '"+MyUrAddr+"', '"+MyFaktAddr+"', '"+MyPostAddr+"', '"+MyPhone+"', '"+MyMobile+"','"+MyEmail+"','"+MySite+"'); ");
    }
    
    public void AddMyFirmDB(String MyFullName , String MySokrName, String MyINN, String MyKPP, String MyOGRN, String MyBankName, String MyBankBIK, String MyBankKS, String MyBankRS, String MyRukDolzhn, String MyVlice, String MyFIOruk, String MyUrAddr, String MyFaktAddr, String MyPostAddr, String MyPhone, String MyMobile, String MyEmail, String MySite)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.execSQL("INSERT OR REPLACE INTO MYFIRMREKVIZITI ([FULLNAME],[SOKRNAME],[INN],[KPP],[OGRN],[BANKNAME],[BANKBIK],[BANKKS],[BANKRS],[RUKDOLZHN],[VLICE],[FIORUK],[URADDR],[FACTADDR],[POSTADDR],[PHONE],[MOBILE],[EMAIL],[SITE]) VALUES ('"+MyFullName+"' , '"+ MySokrName+"', '"+MyINN+"', '"+MyKPP+"', '"+MyOGRN+"', '"+MyBankName+"', '"+MyBankBIK+"', '"+MyBankKS+"', '"+MyBankRS+"','"+MyRukDolzhn+"', '"+MyVlice+"', '"+MyFIOruk+"', '"+MyUrAddr+"', '"+MyFaktAddr+"', '"+MyPostAddr+"', '"+MyPhone+"', '"+MyMobile+"','"+MyEmail+"','"+MySite+"'); ");
    }
    
    
    public void ChangeMyFirm(String ID, String MyFullName , String MySokrName, String MyINN, String MyKPP, String MyOGRN, String MyBankName, String MyBankBIK, String MyBankKS, String MyBankRS, String MyRukDolzhn, String MyVlice, String MyFIOruk, String MyUrAddr, String MyFaktAddr, String MyPostAddr, String MyPhone, String MyMobile, String MyEmail, String MySite)
    {
        SQLiteDatabase db = this.getWritableDatabase();
       
        db.execSQL("INSERT OR REPLACE INTO MYFIRMREKVIZITI ([ID],[FULLNAME],[SOKRNAME],[INN],[KPP],[OGRN],[BANKNAME],[BANKBIK],[BANKKS],[BANKRS],[RUKDOLZHN],[VLICE],[FIORUK],[URADDR],[FACTADDR],[POSTADDR],[PHONE],[MOBILE],[EMAIL],[SITE]) VALUES ('"+ID+"' , '"+ MyFullName+"' , '"+ MySokrName+"', '"+MyINN+"', '"+MyKPP+"', '"+MyOGRN+"', '"+MyBankName+"', '"+MyBankBIK+"', '"+MyBankKS+"', '"+MyBankRS+"','"+MyRukDolzhn+"', '"+MyVlice+"', '"+MyFIOruk+"', '"+MyUrAddr+"', '"+MyFaktAddr+"', '"+MyPostAddr+"', '"+MyPhone+"', '"+MyMobile+"','"+MyEmail+"','"+MySite+"'); ");
    }
    
    public void DelMyFirm(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.execSQL("DELETE FROM MYFIRMREKVIZITI WHERE ID=" + ID+";");
    }
   
   
   
   
   
   
//====================НОРМАТИВНЫЕ ДОКУМЕНТЫ====================
    
   public void AddNORMDOC(String Name, String Opisanie, String Primechanie, String FileName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.execSQL("INSERT OR REPLACE INTO NORMDOC ([NAME],[OPISANIE],[PRIMECHANIE],[FILENAME]) VALUES ('"+Name+"' , '"+ Opisanie+"', '"+Primechanie+"', '"+FileName+"'); ");
    }
    
    
    public void ChangeNORMDOC(String ID, String Name, String Opisanie, String Primechanie, String FileName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
       
        db.execSQL("INSERT OR REPLACE INTO NORMDOC ([ID], [NAME],[OPISANIE],[PRIMECHANIE],[FILENAME]) VALUES ('"+ID+"' , '"+Name+"' , '"+ Opisanie+"', '"+Primechanie+"', '"+FileName+"'); ");
    }
    
    public void DelNORMDOC(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.execSQL("DELETE FROM NORMDOC WHERE ID=" + ID+";");
    }
    
    
    
    
    
    
    
       //====================ЗАМЕТКИ====================
    
    public void AddZAMETKA(String Name, String Opisanie, String Sdelka, String Data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.execSQL("INSERT OR REPLACE INTO ZAMETKI ([NAME],[OPISANIE],[SDELKAIDD],[DATA]) VALUES ('"+Name+"' , '"+ Opisanie+"', '"+Sdelka+"', '"+Data+"'); ");
    }
    
    
    public void ChangeZAMETKA(String ID, String Name, String Opisanie, String Sdelka, String Data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
       
        db.execSQL("INSERT OR REPLACE INTO ZAMETKI ([ID], [NAME],[OPISANIE],[SDELKAIDD],[DATA]) VALUES ('"+ID+"' , '"+Name+"' , '"+ Opisanie+"', '"+Sdelka+"', '"+Data+"'); ");
    }
    
    public void DelZAMETKA(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.execSQL("DELETE FROM ZAMETKI WHERE ID=" + ID+";");
    }
     
     
     
     
     
     
       //====================КОНТРАГЕНТЫ====================
    
    
    public void AddKontragent(String FullName , String SokrName, String INN, String KPP, String OGRN, String BankName, String BankBIK, String BankKS, String BankRS, String RukDolzhn, String Vlice, String FIOruk, String UrAddr, String FaktAddr, String PostAddr, String Phone, String Mobile, String Email, String Site, String Otvetstvennij)
    {

       SQLiteDatabase db =  this.getWritableDatabase();
        
        db.execSQL("INSERT OR REPLACE INTO KONTRAGENTI ([FULLNAME],[SOKRNAME],[INN],[KPP],[OGRN],[BANKNAME],[BANKBIK],[BANKKS],[BANKRS],[RUKDOLZHN],[VLICE],[FIORUK],[URADDR],[FACTADDR],[POSTADDR],[PHONE],[MOBILE],[EMAIL],[SITE], [OTVETSTVENNIJ]) VALUES ('"+FullName+"' , '"+ SokrName+"', '"+INN+"', '"+KPP+"', '"+OGRN+"', '"+BankName+"', '"+BankBIK+"', '"+BankKS+"', '"+BankRS+"','"+RukDolzhn+"', '"+Vlice+"', '"+FIOruk+"', '"+UrAddr+"', '"+FaktAddr+"', '"+PostAddr+"', '"+Phone+"', '"+Mobile+"','"+Email+"','"+Site+"','"+Otvetstvennij+"'); ");
    }
    
    public void ChangeKontragent(String ID, String FullName , String SokrName, String INN, String KPP, String OGRN, String BankName, String BankBIK, String BankKS, String BankRS, String RukDolzhn, String Vlice, String FIOruk, String UrAddr, String FaktAddr, String PostAddr, String Phone, String Mobile, String Email, String Site, String Otvetstvennij)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.execSQL("INSERT OR REPLACE INTO KONTRAGENTI ([ID],[FULLNAME],[SOKRNAME],[INN],[KPP],[OGRN],[BANKNAME],[BANKBIK],[BANKKS],[BANKRS],[RUKDOLZHN],[VLICE],[FIORUK],[URADDR],[FACTADDR],[POSTADDR],[PHONE],[MOBILE],[EMAIL],[SITE], [OTVETSTVENNIJ]) VALUES ('"+ID+"' , '"+ FullName+"' , '"+ SokrName+"', '"+INN+"', '"+KPP+"', '"+OGRN+"', '"+BankName+"', '"+BankBIK+"', '"+BankKS+"', '"+BankRS+"','"+RukDolzhn+"', '"+Vlice+"', '"+FIOruk+"', '"+UrAddr+"', '"+FaktAddr+"', '"+PostAddr+"', '"+Phone+"', '"+Mobile+"','"+Email+"','"+Site+"','"+Otvetstvennij+"'); ");
    }
    
    public void DelKontragent(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.execSQL("DELETE FROM KONTRAGENTI WHERE ID=" + ID+";");
    }
    
      
       //====================КОНЕЦ КОНТРАГЕНТЫ====================
      
      
      
      
        //====================СДЕЛКИ====================
        
      public void AddSdelka (String SdelkaName,String SKontragentFullName,String SKontragentSokrName,String SKontragentINN,String SKontragentKPP, String SKontragentOGRN, String SKontragentBankName,String SKontragentBankBIK,String SKontragentBankKS,String SKontragentBankRS,String SKontragentRukDolzhn, String SKontragentVlice,String SKontragentFIOruk,String SKontragentUrAddr,String SKontragentFaktAddr,String SKontragentPostAddr,String SKontragentPhone, String SKontragentMobile, String SKontragentEmail,String SKontragentSite,String SKontragentOtvetstvennij,String SMyFullName,String SMySokrName,String SMyINN, String SMyKPP, String SMyOGRN,String SMyBankName, String SMyBankBIK, String SMyBankKS, String SMyBankRS, String SMyRukDolzhn, String SMyVlice, String SMyFIOruk, String SMyUrAddr, String SMyFaktAddr, String  SMyPostAddr, String SMyPhone, String SMyMobile, String SMyEmail, String SMySite, String SMyOtvetstvennij, String NomerSdelki, String DataSdelki, String SrokiPostavkiTovarov, String SrokiOkazanijaUslug,String UslovijaOplatiTovarov,String UslovijaOplatiUslug,String UslovijaPriemkiTovarov, String UslovijaPriemkiUslug, String UslovijaGarantii, String OsobijeUslovija, String Sud)
    {

       SQLiteDatabase db =  this.getWritableDatabase();
        
     
       db.execSQL("INSERT OR REPLACE INTO SDELKI ([SDELKA_NAME], [K_FULLNAME], [K_SOKRNAME], [K_INN], [K_KPP], [K_OGRN], [K_BANKNAME], [K_BANKBIK], [K_BANKKS], [K_BANKRS], [K_RUKDOLZHN], [K_VLICE], [K_FIORUK], [K_URADDR], [K_FACTADDR], [K_POSTADDR], [K_PHONE], [K_MOBILE], [K_EMAIL], [K_SITE], [K_OTVETSTVENNIJ], [MY_FULLNAME], [MY_SOKRNAME], [MY_INN], [MY_KPP], [MY_OGRN], [MY_BANKNAME], [MY_BANKBIK], [MY_BANKKS], [MY_BANKRS], [MY_RUKDOLZHN], [MY_VLICE], [MY_FIORUK], [MY_URADDR], [MY_FACTADDR], [MY_POSTADDR], [MY_PHONE], [MY_MOBILE], [MY_EMAIL], [MY_SITE], [MY_OTVETSTVENNIJ], [SDELKA_NOMER], [DOGOVOR_DATA], [TOVAR_SROKIPOSTAVKI], [USLUGI_SROKIOKAZANIJA], [TOVAR_USLOVIJAOPLATI], [USLUGI_USLOVIJAOPLATI], [TOVAR_USLOVIJAPRIEMKI], [USLUGI_USLOVIJAPRIEMKI], [GARANTIJA_USLOVIJA], [OSOBIJE_USLOVIJA], [SUD]) VALUES ('"+SdelkaName+"' ,'"+SKontragentFullName+"' , '"+SKontragentSokrName+"' , '"+ SKontragentINN+"' , '"+ SKontragentKPP+"' , '"+ SKontragentOGRN+"' , '"+SKontragentBankName+"' , '"+SKontragentBankBIK+"' , '"+ SKontragentBankKS+"' , '"+ SKontragentBankRS+"' , '"+ SKontragentRukDolzhn+"' , '"+SKontragentVlice+"' , '"+ SKontragentFIOruk+"' , '"+ SKontragentUrAddr+"' , '"+ SKontragentFaktAddr+"' , '"+ SKontragentPostAddr+"' , '"+SKontragentPhone+"' , '"+ SKontragentMobile+"' , '"+ SKontragentEmail+"' , '"+ SKontragentSite+"' , '"+ SKontragentOtvetstvennij+"' , '"+SMyFullName+"' , '"+ SMySokrName+"' , '"+ SMyINN+"' , '"+ SMyKPP+"' , '"+ SMyOGRN+"' , '"+ SMyBankName+"' , '"+ SMyBankBIK+"' , '"+ SMyBankKS+"' , '"+SMyBankRS+"' , '"+ SMyRukDolzhn+"' , '"+ SMyVlice+"' , '"+ SMyFIOruk+"' , '"+ SMyUrAddr+"' , '"+ SMyFaktAddr+"' , '"+ SMyPostAddr+"' , '"+ SMyPhone+"' , '"+ SMyMobile+"' , '"+ SMyEmail+"' , '"+ SMySite+"' , '"+ SMyOtvetstvennij+"' , '"+NomerSdelki+"' , '"+ DataSdelki+"' , '"+ SrokiPostavkiTovarov+"' , '"+ SrokiOkazanijaUslug+"' , '"+ UslovijaOplatiTovarov+"' , '"+UslovijaOplatiUslug+"' , '"+ UslovijaPriemkiTovarov+"' , '"+ UslovijaPriemkiUslug+"' , '"+ UslovijaGarantii+"' , '"+ OsobijeUslovija+"' , '"+Sud+"'); "); //SQL-запрос на создание БД
    }
    
       public void ChangeSdelka (String ID, String SdelkaName,String SKontragentFullName,String SKontragentSokrName,String SKontragentINN,String SKontragentKPP, String SKontragentOGRN, String SKontragentBankName,String SKontragentBankBIK,String SKontragentBankKS,String SKontragentBankRS,String SKontragentRukDolzhn, String SKontragentVlice,String SKontragentFIOruk,String SKontragentUrAddr,String SKontragentFaktAddr,String SKontragentPostAddr,String SKontragentPhone, String SKontragentMobile, String SKontragentEmail,String SKontragentSite,String SKontragentOtvetstvennij,String SMyFullName,String SMySokrName,String SMyINN, String SMyKPP, String SMyOGRN,String SMyBankName, String SMyBankBIK, String SMyBankKS, String SMyBankRS, String SMyRukDolzhn, String SMyVlice, String SMyFIOruk, String SMyUrAddr, String SMyFaktAddr, String  SMyPostAddr, String SMyPhone, String SMyMobile, String SMyEmail, String SMySite, String SMyOtvetstvennij, String NomerSdelki, String DataSdelki, String SrokiPostavkiTovarov, String SrokiOkazanijaUslug,String UslovijaOplatiTovarov,String UslovijaOplatiUslug,String UslovijaPriemkiTovarov, String UslovijaPriemkiUslug, String UslovijaGarantii, String OsobijeUslovija, String Sud)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.execSQL("INSERT OR REPLACE INTO SDELKI ([ID], [SDELKA_NAME], [K_FULLNAME], [K_SOKRNAME],[K_INN],[K_KPP], [K_OGRN], [K_BANKNAME], [K_BANKBIK], [K_BANKKS], [K_BANKRS], [K_RUKDOLZHN], [K_VLICE], [K_FIORUK], [K_URADDR], [K_FACTADDR], [K_POSTADDR], [K_PHONE], [K_MOBILE], [K_EMAIL], [K_SITE], [K_OTVETSTVENNIJ], [MY_FULLNAME], [MY_SOKRNAME], [MY_INN], [MY_KPP], [MY_OGRN], [MY_BANKNAME], [MY_BANKBIK], [MY_BANKKS], [MY_BANKRS], [MY_RUKDOLZHN], [MY_VLICE], [MY_FIORUK], [MY_URADDR], [MY_FACTADDR], [MY_POSTADDR], [MY_PHONE], [MY_MOBILE], [MY_EMAIL], [MY_SITE], [MY_OTVETSTVENNIJ], [SDELKA_NOMER], [DOGOVOR_DATA], [TOVAR_SROKIPOSTAVKI], [USLUGI_SROKIOKAZANIJA], [TOVAR_USLOVIJAOPLATI], [USLUGI_USLOVIJAOPLATI], [TOVAR_USLOVIJAPRIEMKI], [USLUGI_USLOVIJAPRIEMKI], [GARANTIJA_USLOVIJA], [OSOBIJE_USLOVIJA], [SUD]) VALUES ('"+ID+"' ,'"+SdelkaName+"' ,'"+SKontragentFullName+"' , '"+SKontragentSokrName+"' , '"+ SKontragentINN+"' , '"+ SKontragentKPP+"' , '"+ SKontragentOGRN+"' , '"+SKontragentBankName+"' , '"+ SKontragentBankBIK+"' , '"+ SKontragentBankKS+"' , '"+ SKontragentBankRS+"' , '"+ SKontragentRukDolzhn+"' , '"+SKontragentVlice+"' , '"+ SKontragentFIOruk+"' , '"+ SKontragentUrAddr+"' , '"+ SKontragentFaktAddr+"' , '"+ SKontragentPostAddr+"' , '"+SKontragentPhone+"' , '"+ SKontragentMobile+"' , '"+ SKontragentEmail+"' , '"+ SKontragentSite+"' , '"+ SKontragentOtvetstvennij+"' , '"+SMyFullName+"' , '"+ SMySokrName+"' , '"+ SMyINN+"' , '"+ SMyKPP+"' , '"+ SMyOGRN+"' , '"+ SMyBankName+"' , '"+ SMyBankBIK+"' , '"+ SMyBankKS+"' , '"+SMyBankRS+"' , '"+ SMyRukDolzhn+"' , '"+ SMyVlice+"' , '"+ SMyFIOruk+"' , '"+ SMyUrAddr+"' , '"+ SMyFaktAddr+"' , '"+ SMyPostAddr+"' , '"+ SMyPhone+"' , '"+ SMyMobile+"' , '"+ SMyEmail+"' , '"+ SMySite+"' , '"+ SMyOtvetstvennij+"' , '"+ NomerSdelki+"' , '"+ DataSdelki+"' , '"+ SrokiPostavkiTovarov+"' , '"+ SrokiOkazanijaUslug+"' , '"+ UslovijaOplatiTovarov+"' , '"+UslovijaOplatiUslug+"' , '"+ UslovijaPriemkiTovarov+"' , '"+ UslovijaPriemkiUslug+"' , '"+ UslovijaGarantii+"' , '"+ OsobijeUslovija+"' , '"+Sud+"'); "); //SQL-запрос на создание БД
        
    }
    
    public void DelSdelka(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        
        db.execSQL("DELETE FROM SDELKI WHERE ID=" + ID+";");
    }
    
        
        //====================КОНЕЦ СДЕЛКИ====================
    
    
    
    public void ExportDB()
    {
      String inputPath="";
      String inputFile="";
      String outputPath="";
      copyFile(inputPath,inputFile,outputPath);
    }
    
    private void copyFile(String inputPath, String inputFile, String outputPath) {

        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File(outputPath);
            if (!dir.exists())
            {
                dir.mkdirs();
            }


            in = new FileInputStream(inputPath + inputFile);
            out = new FileOutputStream(outputPath + inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file (You have now copied the file)
            out.flush();
            out.close();
            out = null;

        }  catch (FileNotFoundException fnfe1) {
            Log.e("copyerrortag", fnfe1.getMessage());
        }
        catch (Exception e) {
            Log.e("copyerrortag", e.getMessage());
        }

    }
    
    
}
