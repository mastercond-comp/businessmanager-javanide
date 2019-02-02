package ru.mastercond;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import ru.mastercond.MenuOpened;
import ru.mastercond.R;
import ru.mastercond.SQLiteConnect;
import ru.mastercond.SdelkaID;
import ru.mastercond.fragment_NORMDOC;
import ru.mastercond.fragment_ZAMETKI;
import ru.mastercond.fragment_SETTINGS;
import ru.mastercond.fragment_KONTRAGENTI;
import ru.mastercond.fragment_MYFIRMS;
import ru.mastercond.fragment_add_kontragent;
import ru.mastercond.fragment_add_myfirm;
import ru.mastercond.fragment_add_normdoc;
import ru.mastercond.fragment_add_normdoc;
import ru.mastercond.fragment_add_zametka;
import ru.mastercond.fragment_add_zametka_home;
import ru.mastercond.fragment_add_sdelka;
import ru.mastercond.fragment_edit_kontragent;
import ru.mastercond.fragment_edit_kontragent_home;
import ru.mastercond.fragment_edit_myfirm;
import ru.mastercond.fragment_edit_myfirm_home;
import ru.mastercond.fragment_edit_normdoc;
import ru.mastercond.fragment_edit_normdoc_home;
import ru.mastercond.fragment_edit_zametka;
import ru.mastercond.fragment_edit_zametka_home;
import ru.mastercond.fragment_edit_sdelka;
import ru.mastercond.fragment_mainpage;
import ru.mastercond.fragment_MYFIRMS;
import ru.mastercond.fragment_SDELKI;



public class MainActivity extends Activity {
  
  SQLiteConnect DB;
  
  FragmentTransaction fTrans;
  
  
  fragment_add_kontragent fragaddkontragent;
  fragment_add_myfirm fragaddmyfirm;
  fragment_add_normdoc fraddnormdoc;
  fragment_add_zametka fraddzametka;
  fragment_add_zametka_home fraddzametkahome;
  fragment_add_sdelka fragaddsdelka;
  
  fragment_edit_kontragent frageditkontragent;
  fragment_edit_myfirm frageditmyfirm;
  fragment_edit_myfirm_home frageditmyfirmhome;
  fragment_edit_normdoc frageditnormdoc;
  fragment_edit_normdoc_home frageditnormdochome;
  fragment_edit_zametka frageditzametka;
  fragment_edit_zametka_home frageditzametkahome;
  fragment_edit_kontragent_home frageditkontragenthome;
  fragment_edit_sdelka fragsdelka;
  fragment_edit_sdelka frsd;
  
  
  fragment_ZAMETKI fragZAMETKI;
  fragment_NORMDOC fragNORMDOC;
  fragment_SDELKI frag1;
  fragment_SDELKI fragsdelki;
  fragment_mainpage fragmain;
  fragment_mainpage fragmain1;
  fragment_SETTINGS fragsettings;
  fragment_KONTRAGENTI fragKONTRAGENTI;
  fragment_MYFIRMS fragMYFIRMS;
  
  SdelkaID sdelkaid;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    
    DB=new SQLiteConnect(this);
    
    
    final ScrollView fragArea=(ScrollView)findViewById(R.id.FragmentArea);
    final ImageButton menuButton = (ImageButton)findViewById(R.id.MenuButton);
    final ScrollView menuLayout=(ScrollView)findViewById(R.id.MenuLayout);
    final MenuOpened iMO=new MenuOpened();
    iMO.setMenuOpened(false);
    menuLayout.setVisibility(View.GONE);
    
    
    
    menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
              boolean isMenuOpened=iMO.getMenuOpened();
      
               if (isMenuOpened==false) 
                {
                 opencloseMenu(false);
                 iMO.setMenuOpened(true);
                } 
                
                if (isMenuOpened==true) 
                {
                 opencloseMenu(true);
                 iMO.setMenuOpened(false);
                } 
              
  }
 }) ;
 
 
 //закрыть меню при нажатии на FragmentArea
 
 
 


 
 
 

    final Button FragmentButtonWorkTable = (Button)findViewById(R.id.MenuButton0);
    final Button FragmentButtonSdelki = (Button)findViewById(R.id.MenuButton1);
    final Button FragmentButtonKontragenti = (Button)findViewById(R.id.MenuButton2);
    final Button FragmentButtonMyOrgs = (Button)findViewById(R.id.MenuButton3);
    final Button FragmentButtonTovariUslugi = (Button)findViewById(R.id.MenuButton4);
    final Button FragmentButtonSotrudniki = (Button)findViewById(R.id.MenuButton5);
    final Button FragmentButtonMiniSklad = (Button)findViewById(R.id.MenuButton6);
    final Button FragmentButtonFinance = (Button)findViewById(R.id.MenuButton7);
    final Button FragmentButtonNORMDOC = (Button)findViewById(R.id.MenuButton8);
    final Button FragmentButtonSajti = (Button)findViewById(R.id.MenuButton9);
    final Button FragmentButtonSync = (Button)findViewById(R.id.MenuButton10);
    final Button FragmentButtonSettings = (Button)findViewById(R.id.MenuButton11);
    final Button FragmentButtonZametki = (Button)findViewById(R.id.MenuButton12);
    
    final Button FragmentButtonWorkTableUP = (Button)findViewById(R.id.UPMenuButton0);
    final Button FragmentButtonAddSdelka = (Button)findViewById(R.id.UPMenuButton1);
    final Button FragmentButtonAddKontragent = (Button)findViewById(R.id.UPMenuButton2);
    final Button FragmentButtonAddMyOrg = (Button)findViewById(R.id.UPMenuButton3);
    final Button FragmentButtonAddTovarUsluga = (Button)findViewById(R.id.UPMenuButton4);
    final Button FragmentButtonAddDelo = (Button)findViewById(R.id.UPMenuButton5);
    final Button FragmentButtonAddZametka = (Button)findViewById(R.id.UPMenuButton6);
    final Button FragmentButtonAddSotrudnik = (Button)findViewById(R.id.UPMenuButton7);
    
    
    
    
    fragmain=new fragment_mainpage();
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.add(R.id.FragmentArea, fragmain);
    fTrans.commit();
    
    
    FragmentButtonWorkTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
     
    fragmain1=new fragment_mainpage();
    
            
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragmain1);
    fTrans.addToBackStack(null);
    fTrans.commit();             
    }
   });
   
   
   
   FragmentButtonMyOrgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {     
    
    fragment_MYFIRMS fragMYFIRMS=new fragment_MYFIRMS();  
          
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragMYFIRMS);
    fTrans.addToBackStack(null);
    fTrans.commit();             
    }
   });
   
   FragmentButtonKontragenti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {     
    
    fragment_KONTRAGENTI fragKONTRAGENTI=new fragment_KONTRAGENTI();  
          
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragKONTRAGENTI);
    fTrans.addToBackStack(null);
    fTrans.commit();             
    }
   });
   
   
   FragmentButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {     
    
    fragment_SETTINGS fragsettings=new fragment_SETTINGS();  
          
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragsettings);
    fTrans.addToBackStack(null);
    fTrans.commit();             
    }
   });
   
    
   
   FragmentButtonSdelki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {     
    
    fragsdelki=new fragment_SDELKI();  
          
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragsdelki);
    fTrans.addToBackStack(null);
    fTrans.commit();             
    }
   });
   
   
   
   
   FragmentButtonAddMyOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
     
    fragaddmyfirm=new fragment_add_myfirm();   
            
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddmyfirm);
    fTrans.addToBackStack(null);
    fTrans.commit();             
    }
   });
   
   
   FragmentButtonAddSdelka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
     
    fragaddsdelka=new fragment_add_sdelka();   
            
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddsdelka);
    fTrans.addToBackStack(null);
    fTrans.commit();             
    }
   });
   
   
   
   
   
   FragmentButtonWorkTableUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragmain);
    fTrans.addToBackStack(null);
    fTrans.commit();             
    }
   });
   
   
   
   FragmentButtonAddKontragent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
    fragment_add_kontragent fragaddkontragent =new fragment_add_kontragent();        
            
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddkontragent);
    fTrans.addToBackStack(null);
    fTrans.commit();             
    }
   });
   
   
   FragmentButtonAddZametka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
    fragment_add_zametka_home fragaddzametkahome =new fragment_add_zametka_home();        
            
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddzametkahome);
    fTrans.addToBackStack(null);
    fTrans.commit();             
    }
   });
       
     
    
    FragmentButtonMyOrgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
    fragment_MYFIRMS fragMYFIRMS = new fragment_MYFIRMS();        
            
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragMYFIRMS);
    fTrans.addToBackStack(null);
    fTrans.commit();             
    }
   });
   
   
   
   FragmentButtonNORMDOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
    fragment_NORMDOC fragNORMDOC =new fragment_NORMDOC();        
            
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragNORMDOC);
    fTrans.addToBackStack(null);
    fTrans.commit();             
    }
   });
   
   
   FragmentButtonZametki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
    fragment_ZAMETKI fragZAMETKI =new fragment_ZAMETKI();        
            
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragZAMETKI);
    fTrans.addToBackStack(null);
    fTrans.commit();             
    }
   });
       
         
          
       
 } 


private void copyFile(String inputPath, String inputFile, String outputFile, String outputPath) {

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
            out = new FileOutputStream(outputPath + outputFile);

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
            Toast.makeText(getApplicationContext(), fnfe1.toString(), Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }
    
public void gohome()
{
    fragment_mainpage frhome=new fragment_mainpage();
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea,frhome);
    fTrans.commit(); 
    
       
}

public void addsdelka()
{
    
    fragment_add_sdelka fragaddsdelka =new fragment_add_sdelka();
    
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, fragaddsdelka);
    fTrans.addToBackStack(null);
    fTrans.commit();     
    
    
}


public void editsdelka(String S)
{
    sdelkaid=new SdelkaID();
    sdelkaid.setSdelkaID(S); 
    
    fragment_edit_sdelka frsd =new fragment_edit_sdelka();
    
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frsd);
    fTrans.addToBackStack(null);
    fTrans.commit();  
    //Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();    
    
    
}


public void sdelkiclose()
{
    fragment_SDELKI fragsdelki=new fragment_SDELKI();
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea,fragsdelki);
    fTrans.addToBackStack(null);
    fTrans.commit(); 
    
       
}

public void addkontragent()
{
    fragment_add_kontragent fragaddkontragent=new fragment_add_kontragent();
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea,fragaddkontragent);
    fTrans.addToBackStack(null);
    fTrans.commit(); 
    
       
}


public void editkontragent(String S)
{
    sdelkaid=new SdelkaID();
    sdelkaid.setSdelkaID(S); 
    
    fragment_edit_kontragent frageditkontragent = new fragment_edit_kontragent();
    
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frageditkontragent);
    fTrans.addToBackStack(null);
    fTrans.commit();  
   // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();    
    
    
}


public void editkontragenthome(String S)
{
    sdelkaid=new SdelkaID();
    sdelkaid.setSdelkaID(S); 
    
    fragment_edit_kontragent_home frageditkontragenthome = new fragment_edit_kontragent_home();
    
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frageditkontragenthome);
    fTrans.addToBackStack(null);
    fTrans.commit();  
   // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();    
    
    
}

public void closekontragent()
{
    fragment_KONTRAGENTI fragKONTRAGENTI=new fragment_KONTRAGENTI();
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea,fragKONTRAGENTI);
    fTrans.addToBackStack(null);
    fTrans.commit(); 
    
       
}

public void closekontragenthome()
{
    fragment_mainpage fragmain=new fragment_mainpage();
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea,fragmain);
    fTrans.addToBackStack(null);
    fTrans.commit(); 
    
       
}



public void myfirmdetail(String S)
{
    sdelkaid=new SdelkaID();
    sdelkaid.setSdelkaID(S); 
    
    fragment_edit_sdelka frsd =new fragment_edit_sdelka();
    
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frsd);
    fTrans.addToBackStack(null);
    fTrans.commit();  
    Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();    
    
    
}


public void addmyfirm()
{
    fragment_add_myfirm fragaddmyfirm=new fragment_add_myfirm();
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea,fragaddmyfirm);
    fTrans.addToBackStack(null);
    fTrans.commit(); 
    
       
}


public void editmyfirm(String S)
{
    sdelkaid=new SdelkaID();
    sdelkaid.setSdelkaID(S); 
    
    fragment_edit_myfirm frageditmyfirm = new fragment_edit_myfirm();
    
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frageditmyfirm);
    fTrans.addToBackStack(null);
    fTrans.commit();  
   // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();    
    
    
}


public void editmyfirmhome(String S)
{
    sdelkaid=new SdelkaID();
    sdelkaid.setSdelkaID(S); 
    
    fragment_edit_myfirm_home frageditmyfirmhome = new fragment_edit_myfirm_home();
    
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frageditmyfirmhome);
    fTrans.addToBackStack(null);
    fTrans.commit();  
   // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();    
    
    
}

public void closemyfirm()
{
    fragment_MYFIRMS fragMYFIRMS=new fragment_MYFIRMS();
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea,fragMYFIRMS);
    fTrans.addToBackStack(null);
    fTrans.commit(); 
    
       
}

public void closemyfirmhome()
{
    fragment_mainpage fragmain=new fragment_mainpage();
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea,fragmain);
    fTrans.addToBackStack(null);
    fTrans.commit(); 
    
       
}




public void addnormdoc()
{
    fragment_add_normdoc fraddnormdoc=new fragment_add_normdoc();
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea,fraddnormdoc);
    fTrans.addToBackStack(null);
    fTrans.commit(); 
    
       
}


public void editnormdoc(String S)
{
    sdelkaid=new SdelkaID();
    sdelkaid.setSdelkaID(S); 
    
    fragment_edit_normdoc freditnormdoc = new fragment_edit_normdoc();
    
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, freditnormdoc);
    fTrans.addToBackStack(null);
    fTrans.commit();  
   // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();    
    
    
}

public void editnormdochome(String S)
{
    sdelkaid=new SdelkaID();
    sdelkaid.setSdelkaID(S); 
    
    fragment_edit_normdoc_home freditnormdoc = new fragment_edit_normdoc_home();
    
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, freditnormdoc);
    fTrans.addToBackStack(null);
    fTrans.commit();  
   // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();    
    
    
}

public void closenormdoc()
{
    fragment_NORMDOC frnormdoc=new fragment_NORMDOC();
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea,frnormdoc);
    fTrans.addToBackStack(null);
    fTrans.commit(); 
    
       
}

public void addzametka()
{
    fragment_add_zametka fraddzametka=new fragment_add_zametka();
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea,fraddzametka);
    fTrans.addToBackStack(null);
    fTrans.commit(); 
    
       
}


public void editzametka(String S)
{
    sdelkaid=new SdelkaID();
    sdelkaid.setSdelkaID(S); 
    
    fragment_edit_zametka frageditzametka = new fragment_edit_zametka();
    
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frageditzametka);
    fTrans.addToBackStack(null);
    fTrans.commit();  
   // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();    
    
    
}

public void editzametkahome(String S)
{
    sdelkaid=new SdelkaID();
    sdelkaid.setSdelkaID(S); 
    
    fragment_edit_zametka_home frageditzametkahome = new fragment_edit_zametka_home();
    
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea, frageditzametkahome);
    fTrans.addToBackStack(null);
    fTrans.commit();  
   // Toast.makeText(getApplicationContext(), S, Toast.LENGTH_LONG).show();    
    
    
}


public void closezametka()
{
    fragment_ZAMETKI frzametki=new fragment_ZAMETKI();
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea,frzametki);
    fTrans.addToBackStack(null);
    fTrans.commit(); 
    
       
}

public void closezametkahome()
{
    fragment_mainpage fragmain=new fragment_mainpage();
    
    fTrans = getFragmentManager().beginTransaction();
    fTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.FragmentArea,fragmain);
    fTrans.addToBackStack(null);
    fTrans.commit(); 
    
       
}

public void exportbd()
{
    DB=new SQLiteConnect(this);
    
     //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В КОРНЕВУЮ ПАПКУ SDCARD0=================
        Context CN = getApplicationContext();
        String dbfilePath= CN.getDatabasePath("db").getPath().toString(); //получить путь до dbbusinessmanagement1.db
        String dbfileOUTPath=Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/Управление.бизнесом/"; //получить путь до /storage/sdcard0 + НУЖНО android.permission.WRITE_EXTERNAL_STORAGE в MANIFEST!!!

        copyFile(dbfilePath,"businessmanagement.db", "businessmanagement.db",dbfileOUTPath); //скопировать файл базы данных для отладки
        
      //  Toast. makeText(CN, dbfilePath, Toast.LENGTH_LONG). show();

     //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В КОРНЕВУЮ ПАПКУ SDCARD0=================
    
       
}


public void importbd()
{
    DB=new SQLiteConnect(this);
    
     //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В ПРИЛОЖЕНИЕ=================
        Context CN = getApplicationContext();
        
        String dbfilePath= CN.getDatabasePath("db").getPath().toString(); //получить путь до dbbusinessmanagement.db
        String dbfileINPath=Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/Управление.бизнесом/"; 
        copyFile(dbfileINPath,"businessmanagement.db", "businessmanagement.db",dbfilePath); //импортировать файл базы данных

     //=================СЕКЦИЯ КОПИРОВАНИЯ ФАЙЛА БАЗЫ ДАННЫХ В ПРИЛОЖЕНИЕ=================
    
       
}


public String getsdelkaid() {
        return sdelkaid.getSdelkaID();
    }
    
    
    public void opencloseMenu(Boolean isMenuOpened)
    {
      
      MenuOpened iM1=new MenuOpened();
      
      final ScrollView menuLayout=(ScrollView)findViewById(R.id.MenuLayout);
               
      
               if (isMenuOpened==false) 
                {
                 menuLayout.setVisibility(View.VISIBLE);
                 iM1.setMenuOpened(true);
                } 
                
                if (isMenuOpened==true) 
                {
                 menuLayout.setVisibility(View.GONE);
                 iM1.setMenuOpened(false);
                } 
    
}


} 
