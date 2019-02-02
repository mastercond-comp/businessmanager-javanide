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
import ru.mastercond.SdelkaID;


import android.app.AlertDialog;
import android.content.DialogInterface;



public class fragment_edit_normdoc extends Fragment {


  private SQLiteConnect DB;
  private SdelkaID sdelkaid;
  
  private EditText NORMDOCname;
  private EditText NORMDOCopisanie;
  private EditText NORMDOCprimechanie;
  private EditText NORMDOCfilename;
  
  AlertDialog.Builder ad;
  
        
  public fragment_edit_normdoc() {}

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
  

 
 @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
  {

    View rootView = inflater.inflate(R.layout.fragment_edit_normdoc, container, false);
    
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
    
    
    
   
    
    final EditText NORMDOCname=(EditText)rootView.findViewById(R.id.normdoc_name);
    final EditText NORMDOCopisanie=(EditText)rootView.findViewById(R.id.normdoc_opisanie);
    final EditText NORMDOCprimechanie=(EditText)rootView.findViewById(R.id.normdoc_primechanie);
    final EditText NORMDOCfilename=(EditText)rootView.findViewById(R.id.normdoc_filename);
    
    MainActivity rootActivity = (MainActivity)getActivity(); 
    String ID = rootActivity.getsdelkaid();
   
   
    
    //СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД
    try {
    SQLiteDatabase db=DB.getReadableDatabase();   
    Cursor cursor = db.rawQuery("SELECT * FROM NORMDOC WHERE ID = " + ID, null); 
    cursor.moveToNext(); //без этого exception 
    
    NORMDOCname.setText(cursor.getString(1));
    NORMDOCopisanie.setText(cursor.getString(2));
    NORMDOCprimechanie.setText(cursor.getString(3));
    NORMDOCfilename.setText(cursor.getString(4));
     } 
     
     catch (CursorIndexOutOfBoundsException CursorException) {
     Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            }
            
    //КОНЕЦ СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД
    
    
    
    Button DBchangenormdoc=(Button)rootView.findViewById(R.id.butonchangenormdoc);
        DBchangenormdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
            String ID = rootActivity.getsdelkaid();
            
            String Name = NORMDOCname.getText().toString();
            String Opisanie = NORMDOCopisanie.getText().toString();
            String Primechanie = NORMDOCprimechanie.getText().toString();
            String FileName=NORMDOCfilename.getText().toString();
            
            
            DB.ChangeNORMDOC(ID,Name,Opisanie,Primechanie, FileName);
             
             
            Toast.makeText(getActivity(),"Данные нормативного документа успешно изменены и записаны в базу",Toast.LENGTH_LONG).show();
            
            MainActivity rootActivity = (MainActivity)getActivity(); 
            rootActivity.closenormdoc();
              
            
            } 
            } );
            
            
            Button DBdelnormdoc=(Button)rootView.findViewById(R.id.butondelnormdoc);
            
            DBdelnormdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
           
            ad = new AlertDialog.Builder(container. getContext());
            ad.setTitle("Удалить нормативный документ?");
            ad.setCancelable(true);
            ad.setNegativeButton("Не удалять",
                 new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                     dialog.cancel();
                     }
                    }); 
                     
                     ad.setPositiveButton("Удалить",  
                 new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                     String ID = rootActivity.getsdelkaid();
                     DB. DelNORMDOC(ID);
              
                     Toast.makeText(getActivity(),"Данные нормативного документа удалены из базы",Toast.LENGTH_LONG).show();
            
                     MainActivity rootActivity = (MainActivity)getActivity(); 
                     rootActivity.closenormdoc();
                     }
                     }) ;
                     
             
             ad.show();
            
            
            } 
            } );
    
    return rootView;
  }
  
 @Override
    public void onStart() {
    super.onStart();
        
    
    }

   

}