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
import ru.mastercond.SdelkaID;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class fragment_add_sdelka extends Fragment {

  private SQLiteConnect DB;

  public fragment_add_sdelka() {}

  private ArrayList<Sdelki> kontragentlist;
  private ArrayList<Sdelki> myorglist;
  private ArrayList<Sdelki> uslovijalist;

  private ArrayList idArray1;
  private ArrayList idArray2;
  private ArrayList idArray3;

  AlertDialog.Builder dialogkontragent;
  AlertDialog.Builder dialogmyorg;
  AlertDialog.Builder sdelkauslovija;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_add_sdelka, container, false);

    DB = new SQLiteConnect(getActivity());

    // =================СЕКЦИЯ ЗАКРЫТИЯ МЕНЮ ПРИ НАЖАТИИ НА ЭКРАН=================

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

    } catch (CursorIndexOutOfBoundsException CursorException) {
      // Toast.makeText(getActivity(),CursorException.toString(),Toast.LENGTH_LONG).show();
    }

    // =================КОНЕЦ СЕКЦИИ ВЫСТАВЛЕНИЕ РЕЖИМА ОТОБРАЖЕНИЯ ТЕЛЕФОН-ПЛАНШЕТ=================

    final LinearLayout LinearLayoutKontragent =
        (LinearLayout) rootView.findViewById(R.id.sectionkontragent);
    final LinearLayout LinearLayoutMyOrg = (LinearLayout) rootView.findViewById(R.id.sectionmyorg);
    final LinearLayout LinearLayoutDocuments =
        (LinearLayout) rootView.findViewById(R.id.sectiondocuments);
    final LinearLayout LinearLayoutUslovijaSdelki =
        (LinearLayout) rootView.findViewById(R.id.sectionuslovijasdelki);

    LinearLayoutKontragent.setVisibility(View.GONE);
    LinearLayoutMyOrg.setVisibility(View.GONE);
    LinearLayoutDocuments.setVisibility(View.GONE);
    LinearLayoutUslovijaSdelki.setVisibility(View.GONE);

    // =================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В КОНТРАГЕНТАХ=================

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

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ ВВОДА В УСЛОВИЯХ СДЕЛКИ=================

    // =================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ=================

    final Button ButtonShowDocuments = (Button) rootView.findViewById(R.id.buttonshowdocuments);
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

    // =================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->КОММЕРЧЕСКИЕ
    // ПРЕДЛОЖЕНИЯ=================

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

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->КОММЕРЧЕСКИЕ
    // ПРЕДЛОЖЕНИЯ=================

    // =================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ
    // ДОКУМЕНТЫ-->ТЕХЗАКЛЮЧЕНИЯ=================

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

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ
    // ДОКУМЕНТЫ-->ТЕХЗАКЛЮЧЕНИЯ=================

    // =================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->СЧЕТА=================

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

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ
    // ДОКУМЕНТЫ-->СЧЕТА=================

    // =================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->ДОГОВОР=================

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

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ
    // ДОКУМЕНТЫ-->ДОГОВОР=================

    // =================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->АКТЫ=================

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

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ
    // ДОКУМЕНТЫ-->АКТЫ=================

    // =================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->НАКЛАДНЫЕ=================

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

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ
    // ДОКУМЕНТЫ-->НАКЛАДНЫЕ=================

    // =================СЕКЦИЯ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->ПРОЧИЕ
    // ДОКУМЕНТЫ=================

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

    // =================КОНЕЦ СЕКЦИИ ПОКАЗА-СКРЫТИЯ ПОЛЕЙ В РАЗДЕЛЕ ДОКУМЕНТЫ-->ПРОЧИЕ
    // ДОКУМЕНТЫ=================

    final EditText ETSdelkaName = (EditText) rootView.findViewById(R.id.sdelka_name);

    final EditText KontragentFullName =
        (EditText) rootView.findViewById(R.id.kontragent_myfullname);
    final EditText KontragentSokrName =
        (EditText) rootView.findViewById(R.id.kontragent_mysokrname);
    final EditText KontragentINN = (EditText) rootView.findViewById(R.id.kontragent_INN);
    final EditText KontragentKPP = (EditText) rootView.findViewById(R.id.kontragent_KPP);
    final EditText KontragentOGRN = (EditText) rootView.findViewById(R.id.kontragent_OGRN);
    final EditText KontragentBankName = (EditText) rootView.findViewById(R.id.kontragent_bankname);
    final EditText KontragentBankBIK = (EditText) rootView.findViewById(R.id.kontragent_bankbik);
    final EditText KontragentBankKS = (EditText) rootView.findViewById(R.id.kontragent_bankks);
    final EditText KontragentBankRS = (EditText) rootView.findViewById(R.id.kontragent_RS);
    final EditText KontragentRukDolzhn =
        (EditText) rootView.findViewById(R.id.kontragent_rukdolzhnost);
    final EditText KontragentVlice = (EditText) rootView.findViewById(R.id.kontragent_vlice);
    final EditText KontragentFIOruk = (EditText) rootView.findViewById(R.id.kontragent_FIOruksokr);
    final EditText KontragentUrAddr = (EditText) rootView.findViewById(R.id.kontragent_URaddr);
    final EditText KontragentFaktAddr = (EditText) rootView.findViewById(R.id.kontragent_FACTaddr);
    final EditText KontragentPostAddr = (EditText) rootView.findViewById(R.id.kontragent_POSTaddr);
    final EditText KontragentPhone = (EditText) rootView.findViewById(R.id.kontragent_Phone);
    final EditText KontragentMobile = (EditText) rootView.findViewById(R.id.kontragent_MobPhone);
    final EditText KontragentEmail = (EditText) rootView.findViewById(R.id.kontragent_Email);
    final EditText KontragentSite = (EditText) rootView.findViewById(R.id.kontragent_www);
    final EditText KontragentOtvetstvennij =
        (EditText) rootView.findViewById(R.id.kontragent_otvetstvennij);

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
    final EditText ETSrokiPostavkiTovarov =
        (EditText) rootView.findViewById(R.id.srokipostavkitovarov);
    final EditText ETSrokiOkazanijaUslug =
        (EditText) rootView.findViewById(R.id.srokiokazanijauslug);
    final EditText ETUslovijaOplatiTovarov =
        (EditText) rootView.findViewById(R.id.uslovijaoplatitovarov);
    final EditText ETUslovijaOplatiUslug =
        (EditText) rootView.findViewById(R.id.uslovijaoplatiuslug);
    final EditText ETUslovijaPriemkiTovarov =
        (EditText) rootView.findViewById(R.id.uslovijaprijemkitovarov);
    final EditText ETUslovijaPriemkiUslug =
        (EditText) rootView.findViewById(R.id.uslovijaprijemkiuslug);
    final EditText ETUslovijaGarantii = (EditText) rootView.findViewById(R.id.uslovijagarantii);
    final EditText ETOsobijeUslovija = (EditText) rootView.findViewById(R.id.osobieuslovija);
    final EditText ETSud = (EditText) rootView.findViewById(R.id.arbitrsud);

    Button DBaddSdelka = (Button) rootView.findViewById(R.id.buttonaddsdelka);
    DBaddSdelka.setOnClickListener(
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

              DB.AddSdelka(
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

              Toast.makeText(getActivity(), "Сделка успешно добавлена в базу", Toast.LENGTH_LONG)
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

              AlertDialog.Builder builderSingle = new AlertDialog.Builder(container.getContext());
              // builderSingle.setIcon(R.drawable.ic_launcher);
              builderSingle.setTitle("Выбрать контрагента:");
              final ArrayAdapter<String> arrayAdapter =
                  new ArrayAdapter<String>(
                      container.getContext(), android.R.layout.select_dialog_singlechoice);
              final ArrayList idArray1 = new ArrayList();
              SQLiteDatabase db = DB.getReadableDatabase();
              String[] dbcolumns = new String[] {"SOKRNAME", "INN", "BANKNAME", "ID"};
              Cursor cursor = db.query("KONTRAGENTI", dbcolumns,null,null,null,null,null); // запрос из базы реквизитов

              while (cursor.moveToNext()) {
                arrayAdapter.add(
                    cursor.getString(0)+ " (ИНН "+ cursor.getString(1)+ ", БАНК "+ cursor.getString(2) + ")");
                idArray1.add(cursor.getString(3)); // добавить в отдельный массив ID
              }

              builderSingle.setNegativeButton(
                  "Отмена",
                  new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      dialog.dismiss();
                    }
                  });

              builderSingle.setAdapter(
                  arrayAdapter,
                  new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      String strName = arrayAdapter.getItem(which);
                      final String selectedID1 = idArray1.get(which).toString();
                      AlertDialog.Builder builderInner = new AlertDialog.Builder(container.getContext());
                      builderInner.setMessage(strName);
                      builderInner.setTitle("Выбран элемент:");
                      builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                              dialog.dismiss();

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

                              } catch (CursorIndexOutOfBoundsException CursorException) {
                                Toast.makeText(
                                        getActivity(),
                                        CursorException.toString(),
                                        Toast.LENGTH_LONG)
                                    .show();
                              }

                              // =================КОНЕЦ СЕКЦИЯ ЗАПОЛНЕНИЯ РАЗДЕЛА КОНТРАГЕНТЫ ДАННЫМИ ИЗ БД=================

                            }
                          });
                      builderInner.show();
                    }
                  });
              builderSingle.create();
              builderSingle.show();

            } catch (SQLException mSQLException) {
              Toast.makeText(getActivity(), mSQLException.toString(), Toast.LENGTH_LONG).show();
            }
          }
        });

    Button SelectMyOrg = (Button) rootView.findViewById(R.id.buttonselectmyorg);
    SelectMyOrg.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            try {

              AlertDialog.Builder builderSingle = new AlertDialog.Builder(container.getContext());
              // builderSingle.setIcon(R.drawable.ic_launcher);
              builderSingle.setTitle("Выбрать свою организацию:");
              final ArrayAdapter<String> arrayAdapter =
                  new ArrayAdapter<String>(
                      container.getContext(), android.R.layout.select_dialog_singlechoice);
              final ArrayList idArray2 = new ArrayList();
              SQLiteDatabase db = DB.getReadableDatabase();
              String[] dbcolumns = new String[] {"SOKRNAME", "INN", "BANKNAME", "ID"};
              Cursor cursor = db.query("MYFIRMREKVIZITI", dbcolumns,null,null,null,null,null); // запрос из базы реквизитов

              while (cursor.moveToNext()) {
                arrayAdapter.add(
                    cursor.getString(0)+ " (ИНН "+ cursor.getString(1)+ ", БАНК "+ cursor.getString(2) + ")");
                idArray2.add(cursor.getString(3)); // добавить в отдельный массив ID
              }

              builderSingle.setNegativeButton(
                  "Отмена",
                  new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      dialog.dismiss();
                    }
                  });

              builderSingle.setAdapter(
                  arrayAdapter,
                  new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      String strName = arrayAdapter.getItem(which);
                      final String selectedID2 = idArray2.get(which).toString();
                      AlertDialog.Builder builderInner = new AlertDialog.Builder(container.getContext());
                      builderInner.setMessage(strName);
                      builderInner.setTitle("Выбран элемент:");
                      builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                              dialog.dismiss();

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
                                //MyOtvetstvennij.setText(cursor.getString(20));

                              } catch (CursorIndexOutOfBoundsException CursorException) {
                                Toast.makeText(
                                        getActivity(),
                                        CursorException.toString(),
                                        Toast.LENGTH_LONG)
                                    .show();
                              }

                              // =================КОНЕЦ СЕКЦИЯ ЗАПОЛНЕНИЯ РАЗДЕЛА КОНТРАГЕНТЫ ДАННЫМИ ИЗ БД=================

                            }
                          });
                      builderInner.show();
                    }
                  });
              builderSingle.create();
              builderSingle.show();

            } catch (SQLException mSQLException) {
              Toast.makeText(getActivity(), mSQLException.toString(), Toast.LENGTH_LONG).show();
            }
          }
        });

    // =================КОНЕЦ СЕКЦИИ ДИАЛОГОВ ВЫБОРА================

    return rootView;
  }
}
