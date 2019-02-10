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

import ru.mastercond.ZAMETKI;
import ru.mastercond.ZAMETKIListAdapter;

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



public class fragment_edit_zametka extends Fragment {


  private SQLiteConnect DB;
  private SdelkaID sdelkaid;
  
  private EditText ZAMETKAname;
  private EditText ZAMETKAopisanie;
  private EditText ZAMETKAsdelka;
  private EditText ZAMETKAdata;
        
  
  AlertDialog.Builder ad;
  
        
  public fragment_edit_zametka() {}

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
  

 
 @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
  {

    View rootView = inflater.inflate(R.layout.fragment_edit_zametka, container, false);
    
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
    db.close();
     } 
     
     catch (CursorIndexOutOfBoundsException CursorException) {
     //Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            }
            
    //=================КОНЕЦ СЕКЦИИ ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================
    
    
        final EditText ZAMETKAname=(EditText)rootView.findViewById(R.id.zametka_name);
        final EditText ZAMETKAopisanie=(EditText)rootView.findViewById(R.id.zametka_opisanie);
        final EditText ZAMETKAsdelka=(EditText)rootView.findViewById(R.id.zametka_sdelka);
        final EditText ZAMETKAdata=(EditText)rootView.findViewById(R.id.zametka_data);
        
    
    MainActivity rootActivity = (MainActivity)getActivity(); 
    String ID = rootActivity.getsdelkaid();
   
   
    
    //СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД
    try {
    SQLiteDatabase db=DB.getReadableDatabase();   
    Cursor cursor = db.rawQuery("SELECT * FROM ZAMETKI WHERE ID = " + ID, null); 
    cursor.moveToNext(); //без этого exception 
    
    ZAMETKAname.setText(cursor.getString(1));
    ZAMETKAopisanie.setText(cursor.getString(2));
    ZAMETKAsdelka.setText(cursor.getString(4));
    ZAMETKAdata.setText(cursor.getString(3));
    
    db.close();
     } 
     
     catch (CursorIndexOutOfBoundsException CursorException) {
     Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            }
            
    //КОНЕЦ СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД
    
    
    
    Button DBchangezametka=(Button)rootView.findViewById(R.id.butonchangezametka);
        DBchangezametka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
            String ID = rootActivity.getsdelkaid();
            
            String Zname=ZAMETKAname.getText().toString();
            String Zopisanie=ZAMETKAopisanie.getText().toString();
            String Zsdelka=ZAMETKAsdelka.getText().toString();
            String Zdata=ZAMETKAdata.getText().toString();
                
            
            
            DB.ChangeZAMETKA(ID,Zname,Zopisanie,Zsdelka, Zdata);
             
             
            Toast.makeText(getActivity(),"Заметка успешно изменена и записана в базу",Toast.LENGTH_LONG).show();
            
            MainActivity rootActivity = (MainActivity)getActivity(); 
            rootActivity.closezametka();
              
            
            } 
            } );
            
            
            Button DBdelzametka=(Button)rootView.findViewById(R.id.butondelzametka);
            
            DBdelzametka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
           
            ad = new AlertDialog.Builder(container. getContext());
            ad.setCancelable(true);
            View dialogView = inflater.inflate(R.layout.alertdialog_delete, null); //важно - inflater определен в начале кода фрагмента
            // Привязка xml-разметки окна диалогов
            ad.setView(dialogView);
            final AlertDialog deldialog = ad.create();
             
            final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
            final Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
            final TextView Zagolovok=(TextView) dialogView.findViewById(R.id.Zagolovok);
            Zagolovok.setText("Удалить заметку из базы?");
            
            btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                        deldialog.cancel();
                       
                    }
                });
                
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                     String ID = rootActivity.getsdelkaid();
                     DB.DelZAMETKA(ID);
              
                     Toast.makeText(getActivity(),"Заметка удалена из базы",Toast.LENGTH_LONG).show();
                     deldialog.cancel();
                     MainActivity rootActivity = (MainActivity)getActivity(); 
                     rootActivity.closezametka();
                       
                    }
                });
                    
             
             deldialog.show();
              
            } 
            } );
    
    return rootView;
  }
  
 @Override
    public void onStart() {
    super.onStart();
        
    
    }

   

}