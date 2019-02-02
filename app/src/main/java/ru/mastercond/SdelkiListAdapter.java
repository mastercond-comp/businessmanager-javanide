package ru.mastercond;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import ru.mastercond.Sdelki;
 
public class SdelkiListAdapter extends BaseAdapter {
 
   private List<Sdelki> listData;
   private LayoutInflater layoutInflater;
   private Context context;
 
   public SdelkiListAdapter(Context aContext,  List<Sdelki> listData) {
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
           convertView = layoutInflater.inflate(R.layout.sdelki_list_item_layout, null);
           holder = new ViewHolder();
           
           holder.kontragentNameView = (TextView)convertView.findViewById(R.id.textView_kontragentName);
           holder.innNameView = (TextView)convertView.findViewById(R.id.textView_INN);
           holder.bankNameView = (TextView)convertView.findViewById(R.id.textView_BANK);
           convertView.setTag(holder);
       } else {
           holder = (ViewHolder) convertView.getTag();
       }
 
       Sdelki client = this.listData.get(position);
       holder.kontragentNameView.setText(client. getkontragentName());
       holder.innNameView.setText(client. getinnName());
       holder.bankNameView.setText(client. getbankName());
 
 
       return convertView;
   }
 
 
   static class ViewHolder {
   
       TextView kontragentNameView;
       TextView innNameView;
       TextView bankNameView;
   }
 
}