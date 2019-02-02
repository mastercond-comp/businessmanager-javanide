package ru.mastercond;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.content.Intent;
import android.view.WindowManager;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.app.Activity;
import android.os.Bundle;
import ru.mastercond.MenuOpened;
import ru.mastercond.SQLiteConnect;
import ru.mastercond.R;

import android.content.Context;
import android.content.ContentValues;
import android.util.Log;
import android.util.DisplayMetrics;
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

import ru.mastercond.NORMDOC;
import ru.mastercond.NORMDOCListAdapter;
import ru.mastercond.ZAMETKI;
import ru.mastercond.ZAMETKIListAdapter;
import ru.mastercond.Sdelki;
import ru.mastercond.SdelkiListAdapter;
import ru.mastercond.fragment_KONTRAGENTI;
import ru.mastercond.fragment_MYFIRMS;

import ru.mastercond.SdelkaID;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;

import ru.mastercond.SQLiteConnect;
import ru.mastercond.MainActivity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;

import java.lang.ClassCastException;


public class fragment_mainpage extends Fragment {

  private SQLiteConnect DB;
  private ListView ListViewNORMDOC;
  private ArrayList<NORMDOC> list;
  
  private ListView ListViewSdelki;
  private ArrayList<Sdelki> listkontragenti;
  
  private ListView ListViewMYFIRMS;
  private ArrayList<Sdelki> listkontragenti1;
  
  
  public fragment_mainpage() {}
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
   
   View rootView = inflater.inflate(R.layout.fragment_mainpage, container, false);
   
   

//=================СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН=================

   LinearLayout fragRoot = (LinearLayout)rootView.findViewById(R.id.FragmentRoot); 
   fragRoot.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
            
            MainActivity rootActivity = (MainActivity)getActivity(); 
              rootActivity.opencloseMenu(true);
             } 
           }   
   );
//=================КОНЕЦ СЕКЦИИ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН=================   



    DB = new SQLiteConnect(getActivity());
    
    
     //=================ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================
    
    try {
    SQLiteDatabase db=DB.getReadableDatabase();   
    Cursor cursor = db.rawQuery("SELECT * FROM SETTINGS WHERE ID = '1'", null); 
    cursor.moveToNext(); //без этого exception 
    String result=cursor.getString(2);

    if (result.equals("PHONE")) 
    { 
     LinearLayout Line1=(LinearLayout)rootView.findViewById(R.id.LLLine1);
     LinearLayout Line2=(LinearLayout)rootView.findViewById(R.id.LLLine2);
     LinearLayout Line3=(LinearLayout)rootView.findViewById(R.id.LLLine3);
     LinearLayout Line4=(LinearLayout)rootView.findViewById(R.id.LLLine4);
     LinearLayout Line5=(LinearLayout)rootView.findViewById(R.id.LLLine5); 
    
    
      
     
      
    }
    
     } 
     
     catch (CursorIndexOutOfBoundsException CursorException) {
     //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            }
            
    //=================КОНЕЦ СЕКЦИИ ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================
    
    
    
    
    
    

    // =================СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (НОРМАТИВНЫЕ ДОКУМЕНТЫ)=================
    try {

      final ArrayList<NORMDOC> list = new ArrayList<NORMDOC>();

      SQLiteDatabase db = DB.getReadableDatabase();

      String[] dbcolumns = new String[] {"NAME", "OPISANIE", "FILENAME","ID"};

      Cursor cursor =
          db.query(
              "NORMDOC", dbcolumns, null, null, null, null, null); // запрос из базы документов

      // cursor.moveToNext(); //без этого exception

      // String name1 = cursor.getString(cursor.getColumnIndex("FULLNAME")); // ноль в индексе
      // запроса cursor
      while (cursor.moveToNext()) {
        list.add(
            new NORMDOC(
                cursor.getString(0),
                "Описание: " + cursor.getString(1),
                "Файл: "+ cursor.getString(2),
                cursor.getString(3)));
        // Toast.makeText(getActivity(),"Контрагент: "+cursor.getString(0) + " БАНК:
        // "+cursor.getString(1)+" ИНН: "+cursor.getString(2)+" ID
        // "+cursor.getString(3),Toast.LENGTH_LONG).show();
      }
      // MyFullName.setText(cursor.getString(0));

      // list.add(new Sdelki("ООО МЕТЕОР", "ИНН: 7728000000000", "БАНК: Авангард", "12")) ;

      final ListView ListViewNORMDOC = (ListView)rootView.findViewById(R.id.ListViewNORMDOC);

      ListViewNORMDOC.setAdapter(new NORMDOCListAdapter(getActivity(), list));

      ListViewNORMDOC.setOnItemClickListener(
          new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
              
              //Toast.makeText(getActivity(), (list.get(position)).getidnumber(), Toast.LENGTH_SHORT).show();
              
              MainActivity rootActivity = (MainActivity)getActivity(); 
              rootActivity.editnormdochome((list.get(position)).getidnumber());
              
              SdelkaID sdelkaid=new SdelkaID();
              sdelkaid.setSdelkaID((list.get(position)).getidnumber()); 
            }
          });

    } catch (CursorIndexOutOfBoundsException CursorException) {
      Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
    }
    
    
    
    
      // =================СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (ЗАМЕТКИ)=================
    try {

      final ArrayList<ZAMETKI> list = new ArrayList<ZAMETKI>();

      SQLiteDatabase db = DB.getReadableDatabase();

      String[] dbcolumns = new String[] {"NAME", "OPISANIE", "SDELKAIDD", "DATA", "IZBR", "ID"};

      Cursor cursor =
          db.query(
              "ZAMETKI", dbcolumns, null, null, null, null, null); // запрос из базы документов

      // cursor.moveToNext(); //без этого exception

      // String name1 = cursor.getString(cursor.getColumnIndex("FULLNAME")); // ноль в индексе
      // запроса cursor
      while (cursor.moveToNext()) {
        list.add(
            new ZAMETKI(
                cursor.getString(0),
                cursor.getString(1),
                "Сделка: " + cursor.getString(2),
                "Дата: " +cursor.getString(3), 
                cursor.getString(4), 
                cursor.getString(5)));
                
        // Toast.makeText(getActivity(),"Контрагент: "+cursor.getString(0) + " БАНК:
        // "+cursor.getString(1)+" ИНН: "+cursor.getString(2)+" ID
        // "+cursor.getString(3),Toast.LENGTH_LONG).show();
      }
      // MyFullName.setText(cursor.getString(0));

      // list.add(new Sdelki("ООО МЕТЕОР", "ИНН: 7728000000000", "БАНК: Авангард", "12")) ;

      ListView ListViewZAMETKI = (ListView)rootView.findViewById(R.id.ListViewZAMETKI);

      ListViewZAMETKI.setAdapter(new ZAMETKIListAdapter(getActivity(), list));

      ListViewZAMETKI.setOnItemClickListener(
          new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
              
              //Toast.makeText(getActivity(), (list.get(position)).getidnumber(), Toast.LENGTH_SHORT).show();
              SdelkaID sdelkaid=new SdelkaID();
              sdelkaid.setSdelkaID((list.get(position)).getidnumber()); 
              
              
              MainActivity rootActivity = (MainActivity)getActivity(); 
              rootActivity.editzametkahome((list.get(position)).getidnumber());
              
              
            }
          });

    } catch (CursorIndexOutOfBoundsException CursorException) {
      Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
    }
    
    
    
    //=================СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (КОНТРАГЕНТЫ)=================
    try {

      final ArrayList<Sdelki> listsdelki = new ArrayList<Sdelki>(); //один список для контрагентов, моих организаций и сделок

      SQLiteDatabase db = DB.getReadableDatabase();

      String[] dbcolumns = new String[] {"SOKRNAME", "BANKNAME", "INN", "ID"};

      Cursor cursor = db.query("KONTRAGENTI", dbcolumns, null, null, null, null, null); // запрос из базы реквизитов

     
      while (cursor.moveToNext()) {
        listsdelki.add(
            new Sdelki(
                cursor.getString(0),
                "БАНК: " + cursor.getString(1),
                "ИНН: " + cursor.getString(2),
                cursor.getString(3)));
       
      }
      


      ListView ListViewKONTRAGENTI = (ListView)rootView.findViewById(R.id.ListViewKONTRAGENTI);

      ListViewKONTRAGENTI.setAdapter(new SdelkiListAdapter(getActivity(), listsdelki));

      ListViewKONTRAGENTI.setOnItemClickListener(
          new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
              //Toast.makeText(getActivity(), (list.get(position)).getidnumber(), Toast.LENGTH_SHORT).show();
              
              MainActivity rootActivity = (MainActivity)getActivity(); 
              rootActivity.editkontragenthome((listsdelki.get(position)).getidnumber());
              
              SdelkaID sdelkaid=new SdelkaID();
              sdelkaid.setSdelkaID((listsdelki.get(position)).getidnumber()); 
            }
          });

    } catch (CursorIndexOutOfBoundsException CursorException) {
      Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
    }
    
    
    
    
     //=================СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (МОИ ОРГАНИЗАЦИИ)=================
    try {

      final ArrayList<Sdelki> listsdelki1 = new ArrayList<Sdelki>(); //один список для контрагентов, моих организаций и сделок

      SQLiteDatabase db = DB.getReadableDatabase();

      String[] dbcolumns = new String[] {"SOKRNAME", "BANKNAME", "INN", "ID"};

      Cursor cursor = db.query("MYFIRMREKVIZITI", dbcolumns, null, null, null, null, null); // запрос из базы реквизитов

     
      while (cursor.moveToNext()) {
        listsdelki1.add(
            new Sdelki(
                cursor.getString(0),
                "БАНК: " + cursor.getString(1),
                "ИНН: " + cursor.getString(2),
                cursor.getString(3)));
       
      }
      

      ListView ListViewMYFIRMS = (ListView)rootView.findViewById(R.id.ListViewMYFIRMS);

      ListViewMYFIRMS.setAdapter(new SdelkiListAdapter(getActivity(), listsdelki1));

      ListViewMYFIRMS.setOnItemClickListener(
          new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
              //Toast.makeText(getActivity(), (list.get(position)).getidnumber(), Toast.LENGTH_SHORT).show();
              
              MainActivity rootActivity = (MainActivity)getActivity(); 
              rootActivity.editmyfirmhome((listsdelki1.get(position)).getidnumber());
              
              SdelkaID sdelkaid=new SdelkaID();
              sdelkaid.setSdelkaID((listsdelki1.get(position)).getidnumber()); 
            }
          });

    } catch (CursorIndexOutOfBoundsException CursorException) {
      Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
    }
    
    
    
    
    
    
    Button NORMDOClistfull=(Button)rootView.findViewById(R.id.butonnormdoclist_full);
    NORMDOClistfull.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
       
         MainActivity rootActivity = (MainActivity)getActivity(); 
         rootActivity.closenormdoc(); 
      
      
} 
}); 

         Button ZAMETKAlistfull=(Button)rootView.findViewById(R.id.butonzametkilist_full);
         ZAMETKAlistfull.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
       
         MainActivity rootActivity = (MainActivity)getActivity(); 
         rootActivity.closezametka(); 
         
         
         
      
} 
}); 
      
      Button KONTRAGENTIlistfull=(Button)rootView.findViewById(R.id.buttonkontragentilist_full);
         KONTRAGENTIlistfull.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
       
         MainActivity rootActivity = (MainActivity)getActivity(); 
         rootActivity.closekontragent(); 
      
      
} 
}); 



Button MYFIRMSlistfull=(Button)rootView.findViewById(R.id.buttonmyfirmslist_full);
         MYFIRMSlistfull.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
       
         MainActivity rootActivity = (MainActivity)getActivity(); 
         rootActivity.closemyfirm(); 
      
      
} 
}); 
   
   
   
   
   return rootView;
}
  
}
