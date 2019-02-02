package ru.mastercond;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import ru.mastercond.TOVARIUSLUGI;

public class TOVARIUSLUGIListAdapter extends BaseAdapter {
 
   private List<TOVARIUSLUGI> listData;
   private LayoutInflater layoutInflater;
   private Context context;
 
   public TOVARIUSLUGIListAdapter(Context aContext,  List<TOVARIUSLUGI> listData) {
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
           convertView = layoutInflater.inflate(R.layout.tovariuslugi_list_item_layout, null);
           holder = new ViewHolder();
           
           holder.NameView = (TextView)convertView.findViewById(R.id.textView_tovaruslugaName);
           holder.TypeView = (TextView)convertView.findViewById(R.id.textView_tovaruslugaType);
           holder.PriceView = (TextView)convertView.findViewById(R.id.textView_tovaruslugaPrice);
           holder.ValutaView = (TextView)convertView.findViewById(R.id.textView_tovaruslugaValuta);
           convertView.setTag(holder);
       } else {
           holder = (ViewHolder) convertView.getTag();
       }
 
       TOVARIUSLUGI element = this.listData.get(position);
       holder.NameView.setText(element.getName());
       holder.TypeView.setText(element.getType());
       holder.PriceView.setText(element.getPrice());
       holder.ValutaView.setText(element.getValuta());
 
 
       return convertView;
   }
 
 
   static class ViewHolder {
   
       TextView NameView;
       TextView TypeView;
       TextView PriceView;
       TextView ValutaView;
   }
 

}
