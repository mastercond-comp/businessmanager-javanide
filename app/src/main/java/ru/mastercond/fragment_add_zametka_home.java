package ru.mastercond;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.content.Context;
import android.content.ContentValues;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import ru.mastercond.SQLiteConnect;
import ru.mastercond.R;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.content.Context;



public class fragment_add_zametka_home extends Fragment {
  
        private SQLiteConnect DB;
        
        private EditText ZAMETKAname;
        private EditText ZAMETKAopisanie;
        private EditText ZAMETKAsdelka;
        private EditText ZAMETKAdata;
        
        public fragment_add_zametka_home() {}
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState); 
    }    
        
          
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
   
   View rootView = inflater.inflate(R.layout.fragment_add_zametka, container, false);
   
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
    
    
    
    


    
        final EditText ZAMETKAname=(EditText)rootView.findViewById(R.id.zametka_name);
        final EditText ZAMETKAopisanie=(EditText)rootView.findViewById(R.id.zametka_opisanie);
        final EditText ZAMETKAsdelka=(EditText)rootView.findViewById(R.id.zametka_sdelka);
        final EditText ZAMETKAdata=(EditText)rootView.findViewById(R.id.zametka_data);
        
        
        
        
        
        Button DBaddZAMETKA=(Button)rootView.findViewById(R.id.butonaddzametka);
        DBaddZAMETKA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              
                
                
           
           try {
             
                String Zname=ZAMETKAname.getText().toString();
                String Zopisanie=ZAMETKAopisanie.getText().toString();
                String Zsdelka=ZAMETKAsdelka.getText().toString();
                String Zdata=ZAMETKAdata.getText().toString();
                
                DB.AddZAMETKA(Zname,Zopisanie,Zsdelka,Zdata);
             
             
             
             
             Toast.makeText(getActivity(),"Заметка успешно добалена в базу",Toast.LENGTH_LONG).show();
             
             MainActivity rootActivity = (MainActivity)getActivity(); 
             rootActivity.closezametkahome();
     
             
             } 
            catch (SQLException mSQLException) {
            Toast.makeText(getActivity(),mSQLException.toString(),Toast.LENGTH_LONG).show();
            }
           } 
                
                
                
                
   
  }) ;
   
    return rootView;
}


}
