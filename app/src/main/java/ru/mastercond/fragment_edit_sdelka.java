package ru.mastercond;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
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
import ru.mastercond.SdelkaID;
import ru.mastercond.Sdelki;
import ru.mastercond.SdelkiListAdapter;
import ru.mastercond.SetDynamicHeightListView;
import ru.mastercond.ZAMETKIdialogListAdapter;
import ru.mastercond.ZAMETKI;

public class fragment_edit_sdelka extends Fragment {

  private SQLiteConnect DB;
  private SetDynamicHeightListView SetDListView;

  public fragment_edit_sdelka() {}

  private ArrayList<Sdelki> kontragentlist;
  private ArrayList<Sdelki> myorglist;
  private ArrayList<Sdelki> uslovijalist;
  
  private ArrayList<ZAMETKI> listzametki;

  private ArrayList idArray1;
  private ArrayList idArray2;
  private ArrayList idArray3;

  AlertDialog.Builder dialogkontragent;
  AlertDialog.Builder dialogmyorg;
  AlertDialog.Builder sdelkauslovija;
  AlertDialog.Builder addel;
  AlertDialog.Builder addzametkabuilder;
  AlertDialog.Builder editzametkabuilder;
  AlertDialog.Builder selectkontragentbuilder;
  AlertDialog.Builder selectmyorgbuilder;
  

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }


  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_edit_sdelka, container, false);

    DB = new SQLiteConnect(getActivity());
    SetDListView=new SetDynamicHeightListView();
   
    final MainActivity rootActivity = (MainActivity)getActivity();
    final String ID = rootActivity.getsdelkaid();
   
   
    final ArrayList<ZAMETKI> listzametki = new ArrayList<ZAMETKI>();
    final ListView ListViewZAMETKI = (ListView)rootView.findViewById(R.id.ListViewZAMETKI);
    final ZAMETKIdialogListAdapter Zadapter = new ZAMETKIdialogListAdapter(getActivity(), listzametki);
    final ArrayList idArrayZametki = new ArrayList();
    
    final EditText ETSdelkaName = (EditText) rootView.findViewById(R.id.sdelka_name);

    final EditText KontragentFullName = (EditText) rootView.findViewById(R.id.kontragent_myfullname);
    final EditText KontragentSokrName = (EditText) rootView.findViewById(R.id.kontragent_mysokrname);
    final EditText KontragentINN = (EditText) rootView.findViewById(R.id.kontragent_INN);
    final EditText KontragentKPP = (EditText) rootView.findViewById(R.id.kontragent_KPP);
    final EditText KontragentOGRN = (EditText) rootView.findViewById(R.id.kontragent_OGRN);
    final EditText KontragentBankName = (EditText) rootView.findViewById(R.id.kontragent_bankname);
    final EditText KontragentBankBIK = (EditText) rootView.findViewById(R.id.kontragent_bankbik);
    final EditText KontragentBankKS = (EditText) rootView.findViewById(R.id.kontragent_bankks);
    final EditText KontragentBankRS = (EditText) rootView.findViewById(R.id.kontragent_RS);
    final EditText KontragentRukDolzhn = (EditText) rootView.findViewById(R.id.kontragent_rukdolzhnost);
    final EditText KontragentVlice = (EditText) rootView.findViewById(R.id.kontragent_vlice);
    final EditText KontragentFIOruk = (EditText) rootView.findViewById(R.id.kontragent_FIOruksokr);
    final EditText KontragentUrAddr = (EditText) rootView.findViewById(R.id.kontragent_URaddr);
    final EditText KontragentFaktAddr = (EditText) rootView.findViewById(R.id.kontragent_FACTaddr);
    final EditText KontragentPostAddr = (EditText) rootView.findViewById(R.id.kontragent_POSTaddr);
    final EditText KontragentPhone = (EditText) rootView.findViewById(R.id.kontragent_Phone);
    final EditText KontragentMobile = (EditText) rootView.findViewById(R.id.kontragent_MobPhone);
    final EditText KontragentEmail = (EditText) rootView.findViewById(R.id.kontragent_Email);
    final EditText KontragentSite = (EditText) rootView.findViewById(R.id.kontragent_www);
    final EditText KontragentOtvetstvennij = (EditText) rootView.findViewById(R.id.kontragent_otvetstvennij);

    final EditText MyFullName = (EditText) rootView.findViewById(R.id.my_fullname);
    final EditText MySokrName = (EditText) rootView.findViewById(R.id.my_sokrname);
    final EditText MyINN = (EditText) rootView.findViewById(R.id.my_INN);
    final EditText MyKPP = (EditText) rootView.findViewById(R.id.my_KPP);
    final EditText MyOGRN = (EditText) rootView.findViewById(R.id.my_OGRN);
    final EditText MyBankName = (EditText) rootView.findViewById(R.id.my_bankname);
    final EditText MyBankBIK = (EditText) rootView.findViewById(R.id.my_bankbik);
    final EditText MyBankKS = (EditText) rootView.findViewById(R.id.my_bankks);
    final EditText MyBankRS = (EditText) rootView.findViewById(R.id.my_RS);
    final EditText MyRukDolzhn = (EditText) rootView.findViewById(R.id.my_rukdolzhnost);
    final EditText MyVlice = (EditText) rootView.findViewById(R.id.my_vlice);
    final EditText MyFIOruk = (EditText) rootView.findViewById(R.id.my_FIOruksokr);
    final EditText MyUrAddr = (EditText) rootView.findViewById(R.id.my_URaddr);
    final EditText MyFaktAddr = (EditText) rootView.findViewById(R.id.my_FACTaddr);
    final EditText MyPostAddr = (EditText) rootView.findViewById(R.id.my_POSTaddr);
    final EditText MyPhone = (EditText) rootView.findViewById(R.id.my_Phone);
    final EditText MyMobile = (EditText) rootView.findViewById(R.id.my_MobPhone);
    final EditText MyEmail = (EditText) rootView.findViewById(R.id.my_Email);
    final EditText MySite = (EditText) rootView.findViewById(R.id.my_www);
    final EditText MyOtvetstvennij = (EditText) rootView.findViewById(R.id.my_otvetstvennij);

    final EditText ETDataDogovora = (EditText) rootView.findViewById(R.id.datadogovora);
    final EditText ETNomerDogovora = (EditText) rootView.findViewById(R.id.nomerdogovora);
    final EditText ETSrokiPostavkiTovarov = (EditText) rootView.findViewById(R.id.srokipostavkitovarov);
    final EditText ETSrokiOkazanijaUslug = (EditText) rootView.findViewById(R.id.srokiokazanijauslug);
    final EditText ETUslovijaOplatiTovarov = (EditText) rootView.findViewById(R.id.uslovijaoplatitovarov);
    final EditText ETUslovijaOplatiUslug = (EditText) rootView.findViewById(R.id.uslovijaoplatiuslug);
    final EditText ETUslovijaPriemkiTovarov = (EditText) rootView.findViewById(R.id.uslovijaprijemkitovarov);
    final EditText ETUslovijaPriemkiUslug = (EditText) rootView.findViewById(R.id.uslovijaprijemkiuslug);
    final EditText ETUslovijaGarantii = (EditText) rootView.findViewById(R.id.uslovijagarantii);
    final EditText ETOsobijeUslovija = (EditText) rootView.findViewById(R.id.osobieuslovija);
    final EditText ETSud = (EditText) rootView.findViewById(R.id.arbitrsud);

    
    
    
    

    //=================СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН=================

    final LinearLayout fragRoot = (LinearLayout) rootView.findViewById(R.id.LLContainer);
    fragRoot.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            MainActivity rootActivity = (MainActivity) getActivity();
            rootActivity.opencloseMenu(true);
          }
        });
    // =================КОНЕЦ СЕКЦИИ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН=================
    

    // =================ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================

    try {
      SQLiteDatabase db = DB.getReadableDatabase();
      Cursor cursor = db.rawQuery("SELECT * FROM SETTINGS WHERE ID = '1'", null);
      cursor.moveToNext(); // без этого exception
      String result = cursor.getString(2);

      if (result.equals("PHONE")) {

        LinearLayout.LayoutParams param =
            new LinearLayout.LayoutParams(
                Math.round(getActivity().getResources().getDisplayMetrics().density * 350),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        fragRoot.setLayoutParams(param);
      }
      
      db.close();

    } catch (CursorIndexOutOfBoundsException CursorException) {
      // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
    }

    // =================КОНЕЦ СЕКЦИИ ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================

    final LinearLayout LinearLayoutKontragent = (LinearLayout) rootView.findViewById(R.id.sectionkontragent);
    final LinearLayout LinearLayoutMyOrg = (LinearLayout) rootView.findViewById(R.id.sectionmyorg);
    final LinearLayout LinearLayoutDocuments = (LinearLayout) rootView.findViewById(R.id.sectiondocuments);
    final LinearLayout LinearLayoutUslovijaSdelki = (LinearLayout) rootView.findViewById(R.id.sectionuslovijasdelki);
    final LinearLayout LinearLayoutTovariUslugi = (LinearLayout) rootView.findViewById(R.id.sectiontovariuslugi);
    final LinearLayout LinearLayoutZametki = (LinearLayout) rootView.findViewById(R.id.sectionzametki);
    

    LinearLayoutKontragent.setVisibility(View.GONE);
    LinearLayoutMyOrg.setVisibility(View.GONE);
    LinearLayoutDocuments.setVisibility(View.GONE);
    LinearLayoutUslovijaSdelki.setVisibility(View.GONE);
    LinearLayoutTovariUslugi.setVisibility(View.GONE);
    LinearLayoutZametki.setVisibility(View.GONE);
    
    

    //=================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В КОНТРАГЕНТАХ=================

    final Button ButtonShowKontragent = (Button) rootView.findViewById(R.id.buttonshowkontragent);
    ButtonShowKontragent.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            if (LinearLayoutKontragent.getVisibility() == View.VISIBLE) {

              LinearLayoutKontragent.setVisibility(View.GONE);
              ButtonShowKontragent.setText(">");

            } else {
              LinearLayoutKontragent.setVisibility(View.VISIBLE);
              ButtonShowKontragent.setText("^");
            }
          }
        });

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В КОНТРАГЕНТАХ=================
    
    

    // =================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В МОИХ ОРГАНИЗАЦИЯХ=================

    final Button ButtonShowMyOrg = (Button) rootView.findViewById(R.id.buttonshowmyorg);
    ButtonShowMyOrg.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            if (LinearLayoutMyOrg.getVisibility() == View.VISIBLE) {

              LinearLayoutMyOrg.setVisibility(View.GONE);
              ButtonShowMyOrg.setText(">");
            } else {
              LinearLayoutMyOrg.setVisibility(View.VISIBLE);
              ButtonShowMyOrg.setText("^");
            }
          }
        });

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В МОИХ ОРГАНИЗАЦИЯХ=================
    
    

    // =================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В УСЛОВИЯХ СДЕЛКИ=================

    final Button ButtonShowUslovijaSdelki =
        (Button) rootView.findViewById(R.id.buttonshowuslovijasdelki);
    ButtonShowUslovijaSdelki.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            if (LinearLayoutUslovijaSdelki.getVisibility() == View.VISIBLE) {

              LinearLayoutUslovijaSdelki.setVisibility(View.GONE);
              ButtonShowUslovijaSdelki.setText(">");
            } else {
              LinearLayoutUslovijaSdelki.setVisibility(View.VISIBLE);
              ButtonShowUslovijaSdelki.setText("^");
            }
          }
        });

    //=================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В УСЛОВИЯХ СДЕЛКИ=================
    
    

    //=================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ=================

    final Button ButtonShowDocuments = (Button)rootView.findViewById(R.id.buttonshowdocuments);
    ButtonShowDocuments.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            if (LinearLayoutDocuments.getVisibility() == View.VISIBLE) {

              LinearLayoutDocuments.setVisibility(View.GONE);
              ButtonShowDocuments.setText(">");
            } else {
              LinearLayoutDocuments.setVisibility(View.VISIBLE);
              ButtonShowDocuments.setText("^");
            }
          }
        });

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ=================
    
    

    // =================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->КОММЕРЧЕСКИЕ ПРЕДЛОЖЕНИЯ=================

    final Button ButtonShowDocumentsKP = (Button) rootView.findViewById(R.id.buttonshowkommpredl);
    ButtonShowDocumentsKP.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            if (LinearLayoutDocuments.getVisibility() == View.VISIBLE) {

              LinearLayoutDocuments.setVisibility(View.GONE);
              ButtonShowDocuments.setText(">");
            } else {
              LinearLayoutDocuments.setVisibility(View.VISIBLE);
              ButtonShowDocuments.setText("^");
            }
          }
        });

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->КОММЕРЧЕСКИЕ ПРЕДЛОЖЕНИЯ=================
    
    

    // =================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->ТЕХЗАКЛЮЧЕНИЯ=================

    final Button ButtonShowDocumentsTZ = (Button) rootView.findViewById(R.id.buttonshowtehzakl);
    ButtonShowDocumentsTZ.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            if (LinearLayoutDocuments.getVisibility() == View.VISIBLE) {

              LinearLayoutDocuments.setVisibility(View.GONE);
              ButtonShowDocuments.setText(">");
            } else {
              LinearLayoutDocuments.setVisibility(View.VISIBLE);
              ButtonShowDocuments.setText("^");
            }
          }
        });

    //=================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->ТЕХЗАКЛЮЧЕНИЯ=================
    
    

    //=================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->СЧЕТА=================

    final Button ButtonShowDocumentsScheta = (Button) rootView.findViewById(R.id.buttonshowscheta);
    ButtonShowDocumentsScheta.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            if (LinearLayoutDocuments.getVisibility() == View.VISIBLE) {

              LinearLayoutDocuments.setVisibility(View.GONE);
              ButtonShowDocuments.setText(">");
            } else {
              LinearLayoutDocuments.setVisibility(View.VISIBLE);
              ButtonShowDocuments.setText("^");
            }
          }
        });

    //=================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->СЧЕТА=================
    
    

    //=================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->ДОГОВОР=================

    final Button ButtonShowDocumentsDogovor =
        (Button) rootView.findViewById(R.id.buttonshowdogovor);
    ButtonShowDocumentsDogovor.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            if (LinearLayoutDocuments.getVisibility() == View.VISIBLE) {

              LinearLayoutDocuments.setVisibility(View.GONE);
              ButtonShowDocuments.setText(">");
            } else {
              LinearLayoutDocuments.setVisibility(View.VISIBLE);
              ButtonShowDocuments.setText("^");
            }
          }
        });

    //=================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->ДОГОВОР=================
    
    

    //=================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->АКТЫ=================

    final Button ButtonShowDocumentsAkt = (Button) rootView.findViewById(R.id.buttonshowakt);
    ButtonShowDocumentsAkt.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            if (LinearLayoutDocuments.getVisibility() == View.VISIBLE) {

              LinearLayoutDocuments.setVisibility(View.GONE);
              ButtonShowDocuments.setText(">");
            } else {
              LinearLayoutDocuments.setVisibility(View.VISIBLE);
              ButtonShowDocuments.setText("^");
            }
          }
        });

    //=================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->АКТЫ=================
    
    

    //=================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->НАКЛАДНЫЕ=================

    final Button ButtonShowDocumentsNakl = (Button) rootView.findViewById(R.id.buttonshownakl);
    ButtonShowDocumentsNakl.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            if (LinearLayoutDocuments.getVisibility() == View.VISIBLE) {

              LinearLayoutDocuments.setVisibility(View.GONE);
              ButtonShowDocuments.setText(">");
            } else {
              LinearLayoutDocuments.setVisibility(View.VISIBLE);
              ButtonShowDocuments.setText("^");
            }
          }
        });

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->НАКЛАДНЫЕ=================
    
    

    // =================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->ПРОЧИЕ ДОКУМЕНТЫ=================

    final Button ButtonShowDocumentsProch = (Button) rootView.findViewById(R.id.buttonshowdrdoc);
    ButtonShowDocumentsProch.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            if (LinearLayoutDocuments.getVisibility() == View.VISIBLE) {

              LinearLayoutDocuments.setVisibility(View.GONE);
              ButtonShowDocuments.setText(">");
            } else {
              LinearLayoutDocuments.setVisibility(View.VISIBLE);
              ButtonShowDocuments.setText("^");
            }
          }
        });

    //=================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->ПРОЧИЕ ДОКУМЕНТЫ=================
    
    
    
    //=================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ТОВАРЫ И УСЛУГИ=================

    final Button ButtonShowTOVUSL = (Button)rootView.findViewById(R.id.buttonshowtovariuslugi);
    ButtonShowTOVUSL.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            if (LinearLayoutTovariUslugi.getVisibility() == View.VISIBLE) {

              LinearLayoutTovariUslugi.setVisibility(View.GONE);
              ButtonShowTOVUSL.setText(">");
            } else {
              LinearLayoutTovariUslugi.setVisibility(View.VISIBLE);
              ButtonShowTOVUSL.setText("^");
            }
          }
        });

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ТОВАРЫ И УСЛУГИ=================
    
    
    
    
     //=================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ЗАМЕТКИ=================

    final Button ButtonShowZAMETKI = (Button)rootView.findViewById(R.id.buttonshowzametki);
    ButtonShowZAMETKI.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            if (LinearLayoutZametki.getVisibility() == View.VISIBLE) {

              LinearLayoutZametki.setVisibility(View.GONE);
              ButtonShowZAMETKI.setText(">");
            } else {
              LinearLayoutZametki.setVisibility(View.VISIBLE);
              ButtonShowZAMETKI.setText("^");
            }
          }
        });

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ЗАМЕТКИ=================
    
    
    
    
    //=================СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД=================
    try {
    SQLiteDatabase db=DB.getReadableDatabase();   
    Cursor cursor = db.rawQuery("SELECT * FROM SDELKI WHERE ID = " + ID, null); 
    cursor.moveToNext(); //без этого exception 
    
    ETSdelkaName.setText(cursor.getString(1));
    
    KontragentFullName.setText(cursor.getString(2));
    KontragentSokrName.setText(cursor.getString(3));
    KontragentINN.setText(cursor.getString(4));
    KontragentKPP.setText(cursor.getString(5));
    KontragentOGRN.setText(cursor.getString(6));
    KontragentBankName.setText(cursor.getString(7));
    KontragentBankBIK.setText(cursor.getString(8));
    KontragentBankKS.setText(cursor.getString(9));
    KontragentBankRS.setText(cursor.getString(10));
    KontragentRukDolzhn.setText(cursor.getString(11));
    KontragentVlice.setText(cursor.getString(12));
    KontragentFIOruk.setText(cursor.getString(13));
    KontragentUrAddr.setText(cursor.getString(14));
    KontragentFaktAddr.setText(cursor.getString(15));
    KontragentPostAddr.setText(cursor.getString(16));
    KontragentPhone.setText(cursor.getString(17));
    KontragentMobile.setText(cursor.getString(18));
    KontragentEmail.setText(cursor.getString(19));
    KontragentSite.setText(cursor.getString(20));
    KontragentOtvetstvennij.setText(cursor.getString(21));
    
    MyFullName.setText(cursor.getString(22));
    MySokrName.setText(cursor.getString(23));
    MyINN.setText(cursor.getString(24));
    MyKPP.setText(cursor.getString(25));
    MyOGRN.setText(cursor.getString(26));
    MyBankName.setText(cursor.getString(27));
    MyBankBIK.setText(cursor.getString(28));
    MyBankKS.setText(cursor.getString(29));
    MyBankRS.setText(cursor.getString(30));
    MyRukDolzhn.setText(cursor.getString(31));
    MyVlice.setText(cursor.getString(32));
    MyFIOruk.setText(cursor.getString(33));
    MyUrAddr.setText(cursor.getString(34));
    MyFaktAddr.setText(cursor.getString(35));
    MyPostAddr.setText(cursor.getString(36));
    MyPhone.setText(cursor.getString(37));
    MyMobile.setText(cursor.getString(38));
    MyEmail.setText(cursor.getString(39));
    MySite.setText(cursor.getString(40));
    MyOtvetstvennij.setText(cursor.getString(41));
    
    ETNomerDogovora.setText(cursor.getString(45));
    ETDataDogovora.setText(cursor.getString(46));
    ETSrokiPostavkiTovarov.setText(cursor.getString(47));
    ETSrokiOkazanijaUslug.setText(cursor.getString(48));
    ETUslovijaOplatiTovarov.setText(cursor.getString(49));
    ETUslovijaOplatiUslug.setText(cursor.getString(50));
    ETUslovijaPriemkiTovarov.setText(cursor.getString(51));
    ETUslovijaPriemkiUslug.setText(cursor.getString(52));
    ETUslovijaGarantii.setText(cursor.getString(53));
    ETOsobijeUslovija.setText(cursor.getString(54));
    ETSud.setText(cursor.getString(55));
    
    db.close();
    
     } 
     
     catch (CursorIndexOutOfBoundsException CursorException) {
     Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
            }
            
    //=================КОНЕЦ СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД=================
    
    
    
    
    // =================СЕКЦИЯ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (РАЗДЕЛ ЗАМЕТКИ)=================
    try {

      idArrayZametki.clear();
      SQLiteDatabase db = DB.getReadableDatabase();
      Cursor cursor = db.rawQuery("SELECT * FROM ZAMETKI WHERE SDELKAIDD = " + ID, null); 
      
      while (cursor.moveToNext()) {
        listzametki.add(new ZAMETKI(cursor.getString(1),"","","Дата: "+cursor.getString(3), "", "")); 
        idArrayZametki.add(cursor.getString(0));
      }
      
      //final ListView ListViewZAMETKI = (ListView)rootView.findViewById(R.id.ListViewZAMETKI);
      //final ZAMETKIdialogListAdapter Zadapter = new ZAMETKIdialogListAdapter(getActivity(), listzametki);
      ListViewZAMETKI.setAdapter(Zadapter);
      SetDListView.SetDynamicHeight(ListViewZAMETKI);
      
      Zadapter.notifyDataSetChanged();

      ListViewZAMETKI.setOnItemClickListener(
          new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                
                final String selectedIDzametka = idArrayZametki.get(position).toString();
              
                AlertDialog.Builder editzametkabuilder = new AlertDialog.Builder(container. getContext());

                View dialogView = inflater.inflate(R.layout.alertdialog_edit_zametka, null); //важно - inflater определен в начале кода фрагмента

                editzametkabuilder.setCancelable(false);

                // Привязка xml-разметки окна диалогов
                editzametkabuilder.setView(dialogView);
                
                
                Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                Button btn_del = (Button) dialogView.findViewById(R.id.dialog_del_btn);
                Button btn_del_go = (Button) dialogView.findViewById(R.id.dialog_del_btn_go);
                final EditText DialogEditZametkaName = (EditText)dialogView.findViewById(R.id.dialogadd_zametka_name);
                final EditText DialogEditZametkaOpisanie = (EditText)dialogView.findViewById(R.id.dialogadd_zametka_opisanie);
                final EditText DialogEditZametkaData = (EditText)dialogView.findViewById(R.id.dialogadd_zametka_data);
                
                SQLiteDatabase db=DB.getReadableDatabase();   
                Cursor cursor = db.rawQuery("SELECT * FROM ZAMETKI WHERE ID = " + selectedIDzametka, null); 
                cursor.moveToNext(); //без этого exception 
    
                DialogEditZametkaName.setText(cursor.getString(1));
                DialogEditZametkaOpisanie.setText(cursor.getString(2));
                DialogEditZametkaData.setText(cursor.getString(3));

                // Создание диалога
                final AlertDialog editzametkadialog = editzametkabuilder.create();

               
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss the alert dialog
                        
                        
                        try {
             
                String Zname=DialogEditZametkaName.getText().toString();
                String Zopisanie= DialogEditZametkaOpisanie.getText().toString();
                String Zdata=DialogEditZametkaData.getText().toString();
                
                DB.ChangeZAMETKA(selectedIDzametka, Zname,Zopisanie,ID,Zdata);
                
                
             
             listzametki.clear();
             idArrayZametki.clear();
             
             Toast.makeText(getActivity(),"Заметка успешно изменена",Toast.LENGTH_LONG).show();
             
             SQLiteDatabase db = DB.getReadableDatabase();
             Cursor cursor = db.rawQuery("SELECT * FROM ZAMETKI WHERE SDELKAIDD = " + ID, null); 
      
             while (cursor.moveToNext()) {
             listzametki.add(new ZAMETKI(cursor.getString(1),"","","Дата: "+cursor.getString(3), "", "")); 
             idArrayZametki.add(cursor.getString(0));
             
             Zadapter.notifyDataSetChanged();
             SetDListView.SetDynamicHeight(ListViewZAMETKI);
             
             }
      
             
      
     
             
             } 
            catch (SQLException mSQLException) {
            Toast.makeText(getActivity(),mSQLException.toString(),Toast.LENGTH_LONG).show();
            }
           
                        editzametkadialog.cancel();
                       
                    }
                });

               
                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss/cancel the alert dialog
                        //dialog.cancel();
                        editzametkadialog.dismiss();
                        //Toast.makeText(getContext(),"No button clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                
                
                
                btn_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss/cancel the alert dialog
                        //dialog.cancel();
                        
                        btn_del_go.setVisibility(View.VISIBLE);
                        
                        btn_del_go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    
                    DB.DelZAMETKA(selectedIDzametka);
                    
                    listzametki.clear();
                    idArrayZametki.clear();
             
             Toast.makeText(getActivity(),"Заметка успешно удалена",Toast.LENGTH_LONG).show();
             
             SQLiteDatabase db = DB.getReadableDatabase();
             Cursor cursor = db.rawQuery("SELECT * FROM ZAMETKI WHERE SDELKAIDD = " + ID, null); 
      
             
             while (cursor.moveToNext()) {
             listzametki.add(new ZAMETKI(cursor.getString(1),"","","Дата: "+cursor.getString(3), "", "")); 
             idArrayZametki.add(cursor.getString(0));} 
             
             Zadapter.notifyDataSetChanged();
             SetDListView.SetDynamicHeight(ListViewZAMETKI);
             btn_del_go.setVisibility(View.GONE);
             editzametkadialog.dismiss();
                         }
                         });
                        
                        
                        
                        
                        
                       
                    }
                });

                editzametkadialog.show();
            }
        });
              
              
      db.close();
      

    } catch (CursorIndexOutOfBoundsException CursorException) {
      Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
    }
    
    
    // =================КОНЕЦ СЕКЦИИ ЗАПОЛНЕНИЯ СТРАНИЦЫ ДАННЫМИ ИЗ БД (РАЗДЕЛ ЗАМЕТКИ)=================
    
   

    
    Button DBsaveSdelka = (Button) rootView.findViewById(R.id.buttonaddsdelka);
    DBsaveSdelka.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            try {

              String SdelkaName = ETSdelkaName.getText().toString();

              String SKontragentFullName = KontragentFullName.getText().toString();
              String SKontragentSokrName = KontragentSokrName.getText().toString();
              String SKontragentINN = KontragentINN.getText().toString();
              String SKontragentKPP = KontragentKPP.getText().toString();
              String SKontragentOGRN = KontragentOGRN.getText().toString();
              String SKontragentBankName = KontragentBankName.getText().toString();
              String SKontragentBankBIK = KontragentBankBIK.getText().toString();
              String SKontragentBankKS = KontragentBankKS.getText().toString();
              String SKontragentBankRS = KontragentBankRS.getText().toString();
              String SKontragentRukDolzhn = KontragentRukDolzhn.getText().toString();
              String SKontragentVlice = KontragentVlice.getText().toString();
              String SKontragentFIOruk = KontragentFIOruk.getText().toString();
              String SKontragentUrAddr = KontragentUrAddr.getText().toString();
              String SKontragentFaktAddr = KontragentFaktAddr.getText().toString();
              String SKontragentPostAddr = KontragentPostAddr.getText().toString();
              String SKontragentPhone = KontragentPhone.getText().toString();
              String SKontragentMobile = KontragentMobile.getText().toString();
              String SKontragentEmail = KontragentEmail.getText().toString();
              String SKontragentSite = KontragentSite.getText().toString();
              String SKontragentOtvetstvennij = KontragentOtvetstvennij.getText().toString();

              String SMyFullName = MyFullName.getText().toString();
              String SMySokrName = MySokrName.getText().toString();
              String SMyINN = MyINN.getText().toString();
              String SMyKPP = MyKPP.getText().toString();
              String SMyOGRN = MyOGRN.getText().toString();
              String SMyBankName = MyBankName.getText().toString();
              String SMyBankBIK = MyBankBIK.getText().toString();
              String SMyBankKS = MyBankKS.getText().toString();
              String SMyBankRS = MyBankRS.getText().toString();
              String SMyRukDolzhn = MyRukDolzhn.getText().toString();
              String SMyVlice = MyVlice.getText().toString();
              String SMyFIOruk = MyFIOruk.getText().toString();
              String SMyUrAddr = MyUrAddr.getText().toString();
              String SMyFaktAddr = MyFaktAddr.getText().toString();
              String SMyPostAddr = MyPostAddr.getText().toString();
              String SMyPhone = MyPhone.getText().toString();
              String SMyMobile = MyMobile.getText().toString();
              String SMyEmail = MyEmail.getText().toString();
              String SMySite = MySite.getText().toString();
              String SMyOtvetstvennij = MyOtvetstvennij.getText().toString();

              String DataDogovora = ETDataDogovora.getText().toString();
              String NomerDogovora = ETNomerDogovora.getText().toString();
              String SrokiPostavkiTovarov = ETSrokiPostavkiTovarov.getText().toString();
              String SrokiOkazanijaUslug = ETSrokiOkazanijaUslug.getText().toString();
              String UslovijaOplatiTovarov = ETUslovijaOplatiTovarov.getText().toString();
              String UslovijaOplatiUslug = ETUslovijaOplatiUslug.getText().toString();
              String UslovijaPriemkiTovarov = ETUslovijaPriemkiTovarov.getText().toString();
              String UslovijaPriemkiUslug = ETUslovijaPriemkiUslug.getText().toString();
              String UslovijaGarantii = ETUslovijaGarantii.getText().toString();
              String OsobijeUslovija = ETOsobijeUslovija.getText().toString();
              String Sud = ETSud.getText().toString();

              // public void AddSdelka (String SdelkaName,String SKontragentFullName,String
              // SKontragentSokrName,String SKontragentINN,String SKontragentKPP, String
              // SKontragentOGRN, String SKontragentBankName,String SKontragentBankBIK,String
              // SKontragentBankKS,String SKontragentBankRS,String SKontragentRukDolzhn, String
              // SKontragentVlice,String SKontragentFIOruk,String SKontragentUrAddr,String
              // SKontragentFaktAddr,String SKontragentPostAddr,String SKontragentPhone, String
              // SKontragentMobile, String SKontragentEmail,String SKontragentSite,String
              // SKontragentOtvetstvennij,String SMyFullName,String SMySokrName,String SMyINN,
              // String SMyKPP, String SMyOGRN,String SMyBankName, String SMyBankBIK, String
              // SMyBankKS, String SMyBankRS, String SMyRukDolzhn, String SMyVlice, String
              // SMyFIOruk, String SMyUrAddr, String SMyFaktAddr, String  SMyPostAddr, String
              // SMyPhone, String SMyMobile, String SMyEmail, String SMySite, String
              // SMyOtvetstvennij, String NomerSdelki, String DataSdelki, String
              // SrokiPostavkiTovarov, String SrokiOkazanijaUslug,String
              // UslovijaOplatiTovarov,String UslovijaOplatiUslug,String UslovijaPriemkiTovarov,
              // String UslovijaPriemkiUslug, String UslovijaGarantii, String OsobijeUslovija,
              // String Sud)

              DB.ChangeSdelka(ID, 
                  SdelkaName,
                  SKontragentFullName,
                  SKontragentSokrName,
                  SKontragentINN,
                  SKontragentKPP,
                  SKontragentOGRN,
                  SKontragentBankName,
                  SKontragentBankBIK,
                  SKontragentBankKS,
                  SKontragentBankRS,
                  SKontragentRukDolzhn,
                  SKontragentVlice,
                  SKontragentFIOruk,
                  SKontragentUrAddr,
                  SKontragentFaktAddr,
                  SKontragentPostAddr,
                  SKontragentPhone,
                  SKontragentMobile,
                  SKontragentEmail,
                  SKontragentSite,
                  SKontragentOtvetstvennij,
                  SMyFullName,
                  SMySokrName,
                  SMyINN,
                  SMyKPP,
                  SMyOGRN,
                  SMyBankName,
                  SMyBankBIK,
                  SMyBankKS,
                  SMyBankRS,
                  SMyRukDolzhn,
                  SMyVlice,
                  SMyFIOruk,
                  SMyUrAddr,
                  SMyFaktAddr,
                  SMyPostAddr,
                  SMyPhone,
                  SMyMobile,
                  SMyEmail,
                  SMySite,
                  SMyOtvetstvennij,
                  NomerDogovora,
                  DataDogovora,
                  SrokiPostavkiTovarov,
                  SrokiOkazanijaUslug,
                  UslovijaOplatiTovarov,
                  UslovijaOplatiUslug,
                  UslovijaPriemkiTovarov,
                  UslovijaPriemkiUslug,
                  UslovijaGarantii,
                  OsobijeUslovija,
                  Sud);

              Toast.makeText(getActivity(), "Изменения в сделке успешно сохранены в базе", Toast.LENGTH_LONG)
                  .show();

              MainActivity rootActivity = (MainActivity)getActivity();
              rootActivity.sdelkiclose();

            } catch (SQLException mSQLException) {
              Toast.makeText(getActivity(), mSQLException.toString(), Toast.LENGTH_LONG).show();
            }
          }
        });
        
        
        

    // =================СЕКЦИИ ДИАЛОГОВ ВЫБОРА================

    Button SelectKontragent = (Button) rootView.findViewById(R.id.buttonselectkontragent);
    SelectKontragent.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            try {

            final ArrayList<Sdelki> listkontragenti = new ArrayList<Sdelki>();
   
            
            AlertDialog.Builder selectkontragentbuilder = new AlertDialog.Builder(container. getContext());
            selectkontragentbuilder.setCancelable(false);
            View dialogView = inflater.inflate(R.layout.alertdialog_select_sdelka, null); //важно - inflater определен в начале кода фрагмента
            // Привязка xml-разметки окна диалогов
            selectkontragentbuilder.setView(dialogView);
            
            final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
            final Button btn_neutral = (Button) dialogView.findViewById(R.id.dialog_neutral_btn);
            final ListView ListViewKontragenti = (ListView)dialogView.findViewById(R.id.ListViewSDELKI);
            TextView Zagolovok=(TextView) dialogView.findViewById(R.id.Zagolovok);
            Zagolovok.setText("Выберите контрагента:");
            btn_neutral.setText("Без контрагента");

         //   final ArrayList idArray1 = new ArrayList();
            SQLiteDatabase db = DB.getReadableDatabase();
            String[] dbcolumns = new String[] {"SOKRNAME", "BANKNAME", "INN", "ID"};

            Cursor cursor = db.query("KONTRAGENTI", dbcolumns, null, null, null, null, null); // запрос из базы реквизитов

     
            while (cursor.moveToNext()) {
            listkontragenti.add(new Sdelki(
                cursor.getString(0),
                "БАНК: " + cursor.getString(1),
                "ИНН: " + cursor.getString(2),
                cursor.getString(3)));
       
      }
      
            final SdelkiListAdapter arrayAdapter = new SdelkiListAdapter(getActivity(), listkontragenti); 
 
            ListViewKontragenti.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
            final AlertDialog selectkontragentdialog = selectkontragentbuilder.create();
            SetDListView.SetDynamicHeight(ListViewKontragenti);
      
            selectkontragentdialog.show();
            
      
      
            ListViewKontragenti.setOnItemClickListener(
          new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
            
            
            final String selectedID1 = listkontragenti.get(position).getidnumber().toString();
            
              // =================СЕКЦИЯ ЗАПОЛНЕНИЯ РАЗДЕЛА КОНТРАГЕНТЫ ДАННЫМИ ИЗ БД=================
                              try {
                              
                                SQLiteDatabase db1 = DB.getReadableDatabase();
                                Cursor cursor = db1.rawQuery("SELECT * FROM KONTRAGENTI WHERE ID = " + selectedID1, null);
                                cursor.moveToNext(); // без этого exception

                                KontragentFullName.setText(cursor.getString(1));
                                KontragentSokrName.setText(cursor.getString(2));
                                KontragentINN.setText(cursor.getString(3));
                                KontragentKPP.setText(cursor.getString(4));
                                KontragentOGRN.setText(cursor.getString(5));
                                KontragentBankName.setText(cursor.getString(6));
                                KontragentBankBIK.setText(cursor.getString(7));
                                KontragentBankKS.setText(cursor.getString(8));
                                KontragentBankRS.setText(cursor.getString(9));
                                KontragentRukDolzhn.setText(cursor.getString(10));
                                KontragentVlice.setText(cursor.getString(11));
                                KontragentFIOruk.setText(cursor.getString(12));
                                KontragentUrAddr.setText(cursor.getString(13));
                                KontragentFaktAddr.setText(cursor.getString(14));
                                KontragentPostAddr.setText(cursor.getString(15));
                                KontragentPhone.setText(cursor.getString(16));
                                KontragentMobile.setText(cursor.getString(17));
                                KontragentEmail.setText(cursor.getString(18));
                                KontragentSite.setText(cursor.getString(19));
                                KontragentOtvetstvennij.setText(cursor.getString(20));
                                
                                db1.close();

                              } catch (CursorIndexOutOfBoundsException CursorException) {
                                Toast.makeText(
                                        getActivity(),
                                        CursorException.toString(),
                                        Toast.LENGTH_LONG)
                                    .show();
                              }

                              // =================КОНЕЦ СЕКЦИЯ ЗАПОЛНЕНИЯ РАЗДЕЛА КОНТРАГЕНТЫ ДАННЫМИ ИЗ БД=================
                              
             selectkontragentdialog.dismiss();
           } 
          });
          
          
          btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                        selectkontragentdialog.dismiss();
                       
                    }
                });
                
                btn_neutral.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        KontragentFullName.setText("");
                                KontragentSokrName.setText("");
                                KontragentINN.setText("");
                                KontragentKPP.setText("");
                                KontragentOGRN.setText("");
                                KontragentBankName.setText("");
                                KontragentBankBIK.setText("");
                                KontragentBankKS.setText("");
                                KontragentBankRS.setText("");
                                KontragentRukDolzhn.setText("");
                                KontragentVlice.setText("");
                                KontragentFIOruk.setText("");
                                KontragentUrAddr.setText("");
                                KontragentFaktAddr.setText("");
                                KontragentPostAddr.setText("");
                                KontragentPhone.setText("");
                                KontragentMobile.setText("");
                                KontragentEmail.setText("");
                                KontragentSite.setText("");
                                KontragentOtvetstvennij.setText("");
                        selectkontragentdialog.dismiss();
                    }
                });
          
          

    } 
    
      catch (CursorIndexOutOfBoundsException CursorException) {
      Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
    }
    
    
    
}});

              
              
              
            
        
        

    Button SelectMyOrg = (Button) rootView.findViewById(R.id.buttonselectmyorg);
    SelectMyOrg.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          try {

            final ArrayList<Sdelki> listmyorg = new ArrayList<Sdelki>();
   
            
            AlertDialog.Builder selectmyorgbuilder = new AlertDialog.Builder(container. getContext());
            selectmyorgbuilder.setCancelable(false);
            View dialogView = inflater.inflate(R.layout.alertdialog_select_sdelka, null); //важно - inflater определен в начале кода фрагмента
            // Привязка xml-разметки окна диалогов
            selectmyorgbuilder.setView(dialogView);
            
            final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
            final Button btn_neutral = (Button) dialogView.findViewById(R.id.dialog_neutral_btn);
            final ListView ListViewKontragenti = (ListView)dialogView.findViewById(R.id.ListViewSDELKI);
            TextView Zagolovok=(TextView) dialogView.findViewById(R.id.Zagolovok);
            Zagolovok.setText("Выберите свою организацию:");
            btn_neutral.setText("Без своей организации");

         //   final ArrayList idArray1 = new ArrayList();
            SQLiteDatabase db = DB.getReadableDatabase();
            String[] dbcolumns = new String[] {"SOKRNAME", "BANKNAME", "INN", "ID"};

            Cursor cursor = db.query("MYFIRMREKVIZITI", dbcolumns, null, null, null, null, null); // запрос из базы реквизитов

     
            while (cursor.moveToNext()) {
            listmyorg.add(new Sdelki(
                cursor.getString(0),
                "БАНК: " + cursor.getString(1),
                "ИНН: " + cursor.getString(2),
                cursor.getString(3)));
       
      }
      
            final SdelkiListAdapter arrayAdapter = new SdelkiListAdapter(getActivity(), listmyorg); 
 
            ListViewKontragenti.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
            final AlertDialog selectmyorgdialog = selectmyorgbuilder.create();
            SetDListView.SetDynamicHeight(ListViewKontragenti);
      
            selectmyorgdialog.show();
            
      
      
            ListViewKontragenti.setOnItemClickListener(
          new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
            
            
            final String selectedID2 = listmyorg.get(position).getidnumber().toString();
            
              // =================СЕКЦИЯ ЗАПОЛНЕНИЯ РАЗДЕЛА КОНТРАГЕНТЫ ДАННЫМИ ИЗ БД=================
                              try {
                              
                                SQLiteDatabase db1 = DB.getReadableDatabase();
                                Cursor cursor = db1.rawQuery("SELECT * FROM MYFIRMREKVIZITI WHERE ID = " + selectedID2, null);
                                cursor.moveToNext(); // без этого exception

                                MyFullName.setText(cursor.getString(1));
                                MySokrName.setText(cursor.getString(2));
                                MyINN.setText(cursor.getString(3));
                                MyKPP.setText(cursor.getString(4));
                                MyOGRN.setText(cursor.getString(5));
                                MyBankName.setText(cursor.getString(6));
                                MyBankBIK.setText(cursor.getString(7));
                                MyBankKS.setText(cursor.getString(8));
                                MyBankRS.setText(cursor.getString(9));
                                MyRukDolzhn.setText(cursor.getString(10));
                                MyVlice.setText(cursor.getString(11));
                                MyFIOruk.setText(cursor.getString(12));
                                MyUrAddr.setText(cursor.getString(13));
                                MyFaktAddr.setText(cursor.getString(14));
                                MyPostAddr.setText(cursor.getString(15));
                                MyPhone.setText(cursor.getString(16));
                                MyMobile.setText(cursor.getString(17));
                                MyEmail.setText(cursor.getString(18));
                                MySite.setText(cursor.getString(19));
                                
                                db1.close();

                              } catch (CursorIndexOutOfBoundsException CursorException) {
                                Toast.makeText(
                                        getActivity(),
                                        CursorException.toString(),
                                        Toast.LENGTH_LONG)
                                    .show();
                              }

                              // =================КОНЕЦ СЕКЦИЯ ЗАПОЛНЕНИЯ РАЗДЕЛА КОНТРАГЕНТЫ ДАННЫМИ ИЗ БД=================
                              
             selectmyorgdialog.dismiss();
           } 
          });
          
          
          btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                        selectmyorgdialog.dismiss();
                       
                    }
                });
                
                btn_neutral.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                                MyFullName.setText("");
                                MySokrName.setText("");
                                MyINN.setText("");
                                MyKPP.setText("");
                                MyOGRN.setText("");
                                MyBankName.setText("");
                                MyBankBIK.setText("");
                                MyBankKS.setText("");
                                MyBankRS.setText("");
                                MyRukDolzhn.setText("");
                                MyVlice.setText("");
                                MyFIOruk.setText("");
                                MyUrAddr.setText("");
                                MyFaktAddr.setText("");
                                MyPostAddr.setText("");
                                MyPhone.setText("");
                                MyMobile.setText("");
                                MyEmail.setText("");
                                MySite.setText("");
                                
                        selectmyorgdialog.dismiss();
                    }
                });
          
          db.close();

    } 
    
      catch (CursorIndexOutOfBoundsException CursorException) {
      Toast.makeText(getActivity(), CursorException.toString(), Toast.LENGTH_LONG).show();
    }
    
    
    
}});



    //=================КОНЕЦ СЕКЦИИ ДИАЛОГОВ ВЫБОРА================
    
    
    
    
    Button DBdelSdelka=(Button)rootView.findViewById(R.id.butondelsdelka);
            
            DBdelSdelka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
           
            
            addel = new AlertDialog.Builder(container. getContext());
            addel.setCancelable(true);
            View dialogView = inflater.inflate(R.layout.alertdialog_delete, null); //важно - inflater определен в начале кода фрагмента
            // Привязка xml-разметки окна диалогов
            addel.setView(dialogView);
            final AlertDialog deldialog = addel.create();
             
            final Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
            final Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
            TextView Zagolovok=(TextView) dialogView.findViewById(R.id.Zagolovok);
            Zagolovok.setText("Удалить сделку и все связанные документы из базы?");
            
            btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                        deldialog.cancel();
                       
                    }
                });
                
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                     DB.DelSdelka(ID);
              
                     Toast.makeText(getActivity(),"Сделка и все связанные документы успешно удалены из базы",Toast.LENGTH_LONG).show();
                     deldialog.cancel();
                     MainActivity rootActivity = (MainActivity)getActivity(); 
                     rootActivity.sdelkiclose();
                       
                    }
                });
                    
             
             deldialog.show();
            
            } 
            } );
            
            
            
            Button ButtonAddZametka = (Button)rootView.findViewById(R.id.buttonaddzametka);
            
            ButtonAddZametka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
                AlertDialog.Builder addzametkabuilder = new AlertDialog.Builder(container. getContext());

                View dialogView = inflater.inflate(R.layout.alertdialog_add_zametka, null); //важно - inflater определен в начале кода фрагмента

                addzametkabuilder.setCancelable(false);

                // Привязка xml-разметки окна диалогов
                addzametkabuilder.setView(dialogView);
                
                
                Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                Button btn_negative = (Button) dialogView.findViewById(R.id.dialog_negative_btn);
                final EditText DialogAddZametkaName = (EditText)dialogView.findViewById(R.id.dialogadd_zametka_name);
                final EditText DialogAddZametkaOpisanie = (EditText)dialogView.findViewById(R.id.dialogadd_zametka_opisanie);
                final EditText DialogAddZametkaData = (EditText)dialogView.findViewById(R.id.dialogadd_zametka_data);

                // Создание диалога
                final AlertDialog addzametkadialog = addzametkabuilder.create();

               
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss the alert dialog
                        
                        
                        try {
             
                String Zname=DialogAddZametkaName.getText().toString();
                String Zopisanie= DialogAddZametkaOpisanie.getText().toString();
                String Zdata=DialogAddZametkaData.getText().toString();
                
                DB.AddZAMETKA(Zname,Zopisanie,ID,Zdata);
                
                
             
             listzametki.clear();
             idArrayZametki.clear();
             
             Toast.makeText(getActivity(),"Заметка успешно добавлена в базу",Toast.LENGTH_LONG).show();
             
             SQLiteDatabase db = DB.getReadableDatabase();

             Cursor cursor = db.rawQuery("SELECT * FROM ZAMETKI WHERE SDELKAIDD = " + ID, null); 
      
             while (cursor.moveToNext()) {
             listzametki.add(new ZAMETKI(cursor.getString(1),"","","Дата: "+cursor.getString(3), "", "")); 
             idArrayZametki.add(cursor.getString(0));} 
                
                Zadapter.notifyDataSetChanged();
                
       SetDListView.SetDynamicHeight(ListViewZAMETKI);
       
      
     db.close();
             
             } 
            catch (SQLException mSQLException) {
            Toast.makeText(getActivity(),mSQLException.toString(),Toast.LENGTH_LONG).show();
            }
           
                        addzametkadialog.cancel();
                       
                    }
                });

               
                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss/cancel the alert dialog
                        //dialog.cancel();
                        addzametkadialog.dismiss();
                        //Toast.makeText(getContext(),"No button clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                addzametkadialog.show();
            }
        });
            
            
            
            
            

    return rootView;
  }
}
