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

import ru.mastercond.NORMDOC;
import ru.mastercond.NORMDOCListAdapter;
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


public class fragment_NORMDOC extends Fragment {


  private SQLiteConnect DB;
  private ListView ListViewNORMDOC;
  private ArrayList<NORMDOC> list;

  public fragment_NORMDOC() {}

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }



  @Override
  public View onCreateView(
      LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_normdoc, container, false);

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
     
     ListView LVN =(ListView)rootView.findViewById(R.id.ListViewNORMDOC);
     LinearLayout.LayoutParams paramHeight = new LinearLayout.LayoutParams(Math.round(getActivity().getResources().getDisplayMetrics().density*350), Math.round(getActivity().getResources().getDisplayMetrics().density*400)); //LayoutParams(width, height) в px
     LVN.setLayoutParams(paramHeight);
      
    }
    
     } 
     
     catch (CursorIndexOutOfBoundsException CursorException) {
     //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            }
            
    //=================КОНЕЦ СЕКЦИИ ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================






    // =================СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД=================
    try {

      final ArrayList<NORMDOC> list = new ArrayList<NORMDOC>();

      SQLiteDatabase db = DB.getReadableDatabase();

      String[] dbcolumns = new String[] {"NAME", "OPISANIE", "FILENAME", "ID"};

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
                "Файл: " + cursor.getString(2),
                cursor.getString(3)));
        // Toast.makeText(getActivity(),"Контрагент: "+cursor.getString(0) + " БАНК:
        // "+cursor.getString(1)+" ИНН: "+cursor.getString(2)+" ID
        // "+cursor.getString(3),Toast.LENGTH_LONG).show();
      }
      // MyFullName.setText(cursor.getString(0));

      // list.add(new Sdelki("ООО МЕТЕОР", "ИНН: 7728000000000", "БАНК: Авангард", "12")) ;

      ListView ListViewNORMDOC = (ListView)rootView.findViewById(R.id.ListViewNORMDOC);

      ListViewNORMDOC.setAdapter(new NORMDOCListAdapter(getActivity(), list));

      ListViewNORMDOC.setOnItemClickListener(
          new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
              
              //Toast.makeText(getActivity(), (list.get(position)).getidnumber(), Toast.LENGTH_SHORT).show();
              
              MainActivity rootActivity = (MainActivity)getActivity(); 
              rootActivity.editnormdoc((list.get(position)).getidnumber());
              
              SdelkaID sdelkaid=new SdelkaID();
              sdelkaid.setSdelkaID((list.get(position)).getidnumber()); 
            }
          });

    } catch (CursorIndexOutOfBoundsException CursorException) {
      Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
    }
    
    
    Button DBaddNORMDOC=(Button)rootView.findViewById(R.id.butonaddnormdoc);
        DBaddNORMDOC.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
            
            MainActivity rootActivity = (MainActivity)getActivity(); 
            rootActivity.addnormdoc();
              
             } 
    }) ;

    return rootView;
  }
}
