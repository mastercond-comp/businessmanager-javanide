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


public class fragment_KONTRAGENTI extends Fragment {


  private SQLiteConnect DB;
  private ListView ListViewSdelki;
  private ArrayList<Sdelki> list;

  public fragment_KONTRAGENTI() {}

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }



  @Override
  public View onCreateView(
      LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_kontragenti, container, false);

//=================СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН=================

   LinearLayout fragRoot = (LinearLayout)rootView.findViewById(R.id.LLContainer); 
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
      
     LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(Math.round(getActivity().getResources().getDisplayMetrics().density*350), ViewGroup.LayoutParams.WRAP_CONTENT);
     fragRoot.setLayoutParams(param);
     
     
     ListView LVK = (ListView)rootView.findViewById(R.id.ListViewKONTRAGENTI);
     LinearLayout.LayoutParams paramHeight = new LinearLayout.LayoutParams(Math.round(getActivity().getResources().getDisplayMetrics().density*350), Math.round(getActivity().getResources().getDisplayMetrics().density*400)); //LayoutParams(width, height) в px
     LVK.setLayoutParams(paramHeight);
      
    }
    
     } 
     
     catch (CursorIndexOutOfBoundsException CursorException) {
     //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            }
            
    //=================КОНЕЦ СЕКЦИИ ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================






   //=================СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД=================
    try {

      final ArrayList<Sdelki> list = new ArrayList<Sdelki>(); //один список для контрагентов, моих организаций и сделок

      SQLiteDatabase db = DB.getReadableDatabase();

      String[] dbcolumns = new String[] {"SOKRNAME", "BANKNAME", "INN", "ID"};

      Cursor cursor = db.query("KONTRAGENTI", dbcolumns, null, null, null, null, null); // запрос из базы реквизитов

     
      while (cursor.moveToNext()) {
        list.add(
            new Sdelki(
                cursor.getString(0),
                "БАНК: " + cursor.getString(1),
                "ИНН: " + cursor.getString(2),
                cursor.getString(3)));
       
      }
      


      ListView ListViewSdelki = (ListView)rootView.findViewById(R.id.ListViewKONTRAGENTI);

      ListViewSdelki.setAdapter(new SdelkiListAdapter(getActivity(), list));

      ListViewSdelki.setOnItemClickListener(
          new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
              //Toast.makeText(getActivity(), (list.get(position)).getidnumber(), Toast.LENGTH_SHORT).show();
              
              MainActivity rootActivity = (MainActivity)getActivity(); 
              rootActivity.editkontragent((list.get(position)).getidnumber());
              
              SdelkaID sdelkaid=new SdelkaID();
              sdelkaid.setSdelkaID((list.get(position)).getidnumber()); 
            }
          });

    } catch (CursorIndexOutOfBoundsException CursorException) {
      Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
    }
    
    
    
    Button DBaddKONTRAGENT=(Button)rootView.findViewById(R.id.butonaddkontragent);
        DBaddKONTRAGENT.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
            
            MainActivity rootActivity = (MainActivity)getActivity(); 
            rootActivity.addkontragent();
              
             } 
    }) ;
    
    

    return rootView;
  }
}
