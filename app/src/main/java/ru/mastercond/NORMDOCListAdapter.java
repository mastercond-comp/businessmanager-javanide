package ru.mastercond;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import ru.mastercond.NORMDOC;

public class NORMDOCListAdapter extends BaseAdapter {
 
   private List<NORMDOC> listData;
   private LayoutInflater layoutInflater;
   private Context context;
 
   public NORMDOCListAdapter(Context aContext,  List<NORMDOC> listData) {
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
           convertView = layoutInflater.inflate(R.layout.normdoc_list_item_layout, null);
           holder = new ViewHolder();
           
           holder.docNameView = (TextView)convertView.findViewById(R.id.textView_docName);
           holder.opisanieNameView = (TextView)convertView.findViewById(R.id.textView_opisanieName);
           holder.fileNameView = (TextView)convertView.findViewById(R.id.textView_fileName);
           convertView.setTag(holder);
       } else {
           holder = (ViewHolder) convertView.getTag();
       }
 
       NORMDOC client = this.listData.get(position);
       holder.docNameView.setText(client.getdocName());
       holder.opisanieNameView.setText(client.getopisanieName());
       holder.fileNameView.setText(client. getfileName());
 
 
       return convertView;
   }
 
 
   static class ViewHolder {
   
       TextView docNameView;
       TextView opisanieNameView;
       TextView fileNameView;
   }
 

}
