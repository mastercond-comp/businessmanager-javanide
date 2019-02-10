package ru.mastercond;

import android.app.Activity;
import android.icu.util.Measure;
import android.os.Bundle;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class SetDynamicHeightListView {


public void SetDynamicHeight (ListView listView) {
ListAdapter listAdapter = listView.getAdapter();
    if (listAdapter == null) {
    return;
    }
int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED); //UNSPECIFIED ВАЖНО!!!! 
for (int i = 0; i < listAdapter.getCount(); i++) {
    View listItem = listAdapter.getView(i, null, listView);

    if(listItem != null){
        listItem.setLayoutParams(new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED); 
        totalHeight += listItem.getMeasuredHeight();
    //Log.d("ru.mastercond", "ЭЛЕМЕНТ LISTVIEW измеренная высота: "+String.valueOf(listItem.getMeasuredHeight())) ; 
    }
}
ViewGroup.LayoutParams params = listView.getLayoutParams();
//params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
params.height = Math.round(totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1))) ;
//Log.d("ru.mastercond", " ЗНАЧЕНИЯ LISTVIEW ДЛЯ ОТЛАДКИ - TotalHeight: " + String.valueOf(totalHeight) + ", Density: " + String.valueOf(context.getResources().getDisplayMetrics().density) + ", ParamsHeight: "+String.valueOf(params. height));
listView.setLayoutParams(params);
//listView.requestLayout();
}

}
