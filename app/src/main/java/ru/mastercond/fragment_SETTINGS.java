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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.AdapterView.OnItemClickListener;

import ru.mastercond.SQLiteConnect;
import ru.mastercond.MainActivity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;

import java.lang.ClassCastException;


public class fragment_SETTINGS extends Fragment {


  private SQLiteConnect DB;
  private SQLiteConnect DB1;
  

  public fragment_SETTINGS() {}

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }



  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
    
    DB = new SQLiteConnect(getActivity());

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
    
    
   
  
  final RadioButton ButtonPlanshet=(RadioButton)rootView.findViewById(R.id.radio_planshet); 
  final RadioButton ButtonPhone=(RadioButton)rootView.findViewById(R.id.radio_smartphone); 
  
  
    //ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД
    try {
    SQLiteDatabase db=DB.getReadableDatabase();   
    Cursor cursor = db.rawQuery("SELECT * FROM SETTINGS WHERE ID = '1'", null); 
    cursor.moveToNext(); //без этого exception 
    String result=cursor.getString(2);
    //Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
    if (result.equals("TABLET")) { ButtonPlanshet.setChecked(true);}
    if (result.equals("PHONE")) { ButtonPhone.setChecked(true);}
    
     } 
     
     catch (CursorIndexOutOfBoundsException CursorException) {
     //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            }
            
    //КОНЕЦ СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД
  
  
    RadioGroup radioGroup = (RadioGroup)rootView.findViewById(R.id.radiogroupDevice);

    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                
                case R.id.radio_planshet:
                    DB = new SQLiteConnect(getActivity());
                    SQLiteDatabase db=DB.getReadableDatabase();   
                    db.execSQL("INSERT OR REPLACE INTO SETTINGS ([ID],[NAME],[ZVALUE]) VALUES ('1','VIEWTYPE','TABLET');"); 
                    //Toast.makeText(getActivity(),"РЕЖИМ ПЛАНШЕТА",Toast.LENGTH_LONG).show();
                    break;
                case R.id.radio_smartphone:
                    DB1 = new SQLiteConnect(getActivity());
                    SQLiteDatabase db1=DB1.getReadableDatabase();   
                    db1.execSQL("INSERT OR REPLACE INTO SETTINGS ([ID],[NAME],[ZVALUE]) VALUES ('1','VIEWTYPE','PHONE');"); 
                    //Toast.makeText(getActivity(),"РЕЖИМ ТЕЛЕФОНА",Toast.LENGTH_LONG).show();
                    break;
                

                default:
                    break;
            }
        }
    });
  
  
  

    Button DBexport = (Button)rootView.findViewById(R.id.buttonexportbd);
        DBexport.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
            
            MainActivity rootActivity = (MainActivity)getActivity(); 
            rootActivity.exportbd();
            
            Toast.makeText(getActivity(), "Экспорт базы данных в папку приложения выполнен", Toast.LENGTH_LONG).show();
             } 
    }) ;
    
    
    Button DBimport = (Button)rootView.findViewById(R.id.buttonimportbd);
        DBimport.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {
            
            MainActivity rootActivity = (MainActivity)getActivity(); 
            rootActivity.importbd();
            
            Toast.makeText(getActivity(), "Импорт базы данных в приложение выполнен", Toast.LENGTH_LONG).show();
             } 
    }) ;
   

    return rootView;
  }
}
