package ru.mastercond;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import ru.mastercond.ZAMETKI;

public class ZAMETKIdialogListAdapter extends BaseAdapter {
 
   private List<ZAMETKI> listData;
   private LayoutInflater layoutInflater;
   private Context context;
 
   public ZAMETKIdialogListAdapter(Context aContext,  List<ZAMETKI> listData) {
       this.context = aContext;
       this.listData = listData;
       layoutInflater = LayoutInflater.from(aContext);
   }
 
   @Override
   public int getCount() {
       return listData.size();
   }
 
   @Override
   public Object getItem(int position) {
       return listData.get(position);
   }
 
   @Override
   public long getItemId(int position) {
       return position;
   }
 
   public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
       if (convertView == null) {
           convertView = layoutInflater.inflate(R.layout.zametki_dialog_list_item_layout, null);
           holder = new ViewHolder();
           
           holder.zametkiNameView = (TextView)convertView.findViewById(R.id.textView_zametkiName);
           holder.zametkiDataView = (TextView)convertView.findViewById(R.id.textView_zametkiData);
           convertView.setTag(holder);
       } else {
           holder = (ViewHolder) convertView.getTag();
       }
 
       ZAMETKI zametka = this.listData.get(position);
       holder.zametkiNameView.setText(zametka.getzametkaName());
       holder.zametkiDataView.setText(zametka.getzametkaDATA());
 
 
       return convertView;
   }
 
 
   static class ViewHolder {
   
       TextView zametkiNameView;
       TextView zametkiDataView;
   }
 

}
