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

import ru.mastercond.Sdelki;
import ru.mastercond.SdelkiListAdapter;

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


public class fragment_SDELKI extends Fragment {


  private SQLiteConnect DB;
  private ListView ListViewSdelki1;
  private ArrayList<Sdelki> list;

  public fragment_SDELKI() {}

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }



  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_sdelki, container, false);
    
    DB=new SQLiteConnect(getActivity());
    

//=================СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН=================

   final LinearLayout fragRoot = (LinearLayout)rootView.findViewById(R.id.LLContainer); 
   fragRoot.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
            
            MainActivity rootActivity = (MainActivity)getActivity(); 
              rootActivity.opencloseMenu(true);
             } 
           }   
   );
//=================КОНЕЦ СЕКЦИИ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН================= 

    
    
    
   //=================ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================
    
    try {
    SQLiteDatabase db=DB.getReadableDatabase();   
    Cursor cursor = db.rawQuery("SELECT * FROM SETTINGS WHERE ID = '1'", null); 
    cursor.moveToNext(); //без этого exception 
    String result=cursor.getString(2);

    if (result.equals("PHONE")) 
    { 
      
     LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(Math.round(getActivity().getResources().getDisplayMetrics().density*350), ViewGroup.LayoutParams.WRAP_CONTENT);
     fragRoot.setLayoutParams(param);
      
    }
    
     } 
     
     catch (CursorIndexOutOfBoundsException CursorException) {
     //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            }
            
    //=================КОНЕЦ СЕКЦИИ ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================
    
    
    Button DBaddSDELKA=(Button)rootView.findViewById(R.id.butonaddsdelka);
        DBaddSDELKA.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
            
            MainActivity rootActivity = (MainActivity)getActivity(); 
            rootActivity.addsdelka();
              
             } 
    }) ;  
    
    

    // =================СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД=================
    try {

      final ArrayList<Sdelki> list = new ArrayList<Sdelki>();

      SQLiteDatabase db = DB.getReadableDatabase();

      String[] dbcolumns = new String[] {"SDELKA_NAME", "K_SOKRNAME", "MY_SOKRNAME", "ID"};

      Cursor cursor =
          db.query(
              "SDELKI", dbcolumns, null, null, null, null, null); // запрос из базы реквизитов

      // cursor.moveToNext(); //без этого exception

      // String name1 = cursor.getString(cursor.getColumnIndex("FULLNAME")); // ноль в индексе
      // запроса cursor
      while (cursor.moveToNext()) {
        list.add(
            new Sdelki(
                cursor.getString(0),
                "Контрагент: " + cursor.getString(1),
                "Моя организация: " + cursor.getString(2),
                cursor.getString(3)));
        // Toast.makeText(getActivity(),"Контрагент: "+cursor.getString(0) + " БАНК:
        // "+cursor.getString(1)+" ИНН: "+cursor.getString(2)+" ID
        // "+cursor.getString(3),Toast.LENGTH_LONG).show();
      }
      // MyFullName.setText(cursor.getString(0));

      // list.add(new Sdelki("ООО МЕТЕОР", "ИНН: 7728000000000", "БАНК: Авангард", "12")) ;

      ListView ListViewSdelki1 = (ListView) rootView.findViewById(R.id.ListViewSdelki);

      ListViewSdelki1.setAdapter(new SdelkiListAdapter(getActivity(), list));

      ListViewSdelki1.setOnItemClickListener(
          new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
              //Toast.makeText(getActivity(), (list.get(position)).getidnumber(), Toast.LENGTH_SHORT).show();
              
              MainActivity rootActivity = (MainActivity)getActivity(); 
              rootActivity.editsdelka((list.get(position)).getidnumber());
              
              SdelkaID sdelkaid=new SdelkaID();
              sdelkaid.setSdelkaID((list.get(position)).getidnumber()); 
            }
          });

    } catch (CursorIndexOutOfBoundsException CursorException) {
      Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
    }

    return rootView;
  }
}
