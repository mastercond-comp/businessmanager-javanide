package ru.mastercond;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Bundle;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.ClassCastException;
import java.util.ArrayList;
import java.util.List;
import ru.mastercond.MainActivity;
import ru.mastercond.MenuOpened;
import ru.mastercond.R;
import ru.mastercond.SQLiteConnect;
import ru.mastercond.SQLiteConnect;
import ru.mastercond.SdelkaID;
import ru.mastercond.ZAMETKI;
import ru.mastercond.ZAMETKIListAdapter;


public class fragment_ZAMETKI extends Fragment {


  private SQLiteConnect DB;
  private ListView ListViewZAMETKI;
  private ArrayList<ZAMETKI> list;

  public fragment_ZAMETKI() {}

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }



  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_zametki, container, false);

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
     
     ListView LVZ = (ListView)rootView.findViewById(R.id.ListViewZAMETKI);
     LinearLayout.LayoutParams paramHeight = new LinearLayout.LayoutParams(Math.round(getActivity().getResources().getDisplayMetrics().density*350), Math.round(getActivity().getResources().getDisplayMetrics().density*400)); //LayoutParams(width, height) в px
     LVZ.setLayoutParams(paramHeight);
      
    }
    
     } 
     
     catch (CursorIndexOutOfBoundsException CursorException) {
     //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            }
            
    //=================КОНЕЦ СЕКЦИИ ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================
    
    

    // =================СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД=================
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
              rootActivity.editzametka((list.get(position)).getidnumber());
              
              
            }
          });

    } catch (CursorIndexOutOfBoundsException CursorException) {
      Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
    }
    
    
     
    Button DBaddZAMETKA=(Button)rootView.findViewById(R.id.butonaddzametka);
        DBaddZAMETKA.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
            
            MainActivity rootActivity = (MainActivity)getActivity(); 
            rootActivity.addzametka();
              
             } 
    }) ;

    return rootView;
  }
   
   
}
