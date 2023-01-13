package com.example.sampe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    EditText name, age, mobile, addr;
    DataBase dataBase;
    ArrayList<String> list1;
    ArrayList<String> list2;
    ArrayList<String> list3;
    ArrayList<String> list4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dataBase = new DataBase(this);

        name = findViewById(R.id.txtName);
        age = findViewById(R.id.txtAge);
        mobile = findViewById(R.id.txtMobile);
        addr = findViewById(R.id.txtAddr);

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();
        fetch();
    }

    public void fetch() {

        Cursor res2 = dataBase.fetchData();

        String extractedname, extractedphone, extratctedage, extractedaddr;
        /*while (res2.moveToNext())
        {
            extractedname=res2.getString(0);
            extratctedage=res2.getString(1);
            extractedphone=res2.getString(2);
            extractedaddr=res2.getString(3);
        }*/


        /*name.setText(extractedname);
        age.setText(extratctedage);
        mobile.setText(extractedphone);
        addr.setText(extractedaddr);


*/
/*
        ArrayList<String> list
        List<bean> beans =res2.;*/


        /*final AlertDialog.Builder dlg = new AlertDialog.Builder(this);

        LayoutInflater inf = LayoutInflater.from(this);
        View vw = inf.inflate(R.layout.order_detail_dialog, null);

        dlg.setView(vw);
        dlg.setCancelable(true);
*/
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();

        while (res2.moveToNext()) {
            list1.add(res2.getString(0));
            list2.add(res2.getString(1));
            list3.add(res2.getString(2));
            list4.add(res2.getString(3));
        }
        /*ListView names = (ListView) vw.findViewById(R.id.listname);
        ListView ages = (ListView) vw.findViewById(R.id.listAge);
        ListView phones = (ListView)vw.findViewById(R.id.listMobile);
        ListView addrs = (ListView) vw.findViewById(R.id.listAddr);
        */
        final ListView names = (ListView) findViewById(R.id.listname);
        ListView ages = (ListView) findViewById(R.id.listAge);
        ListView phones = (ListView) findViewById(R.id.listMobile);
        ListView addrs = (ListView) findViewById(R.id.listAddr);

        /*extractedname = res2.getString(0);
        extratctedage = res2.getString(1);
        extractedphone = res2.getString(2);
        extractedaddr = res2.getString(3);*/
        /*String[] nameArr = extractedname.split(",");
        String[] ageArr = extratctedage.split(",");
        String[] phoneArr = extractedphone.split(",");
        String[] addrArr = extractedaddr.split(",");*/
        ArrayAdapter<String> adapterBrand = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list1);
        adapterBrand.notifyDataSetChanged();
        names.setAdapter(adapterBrand);
        ArrayAdapter<String> adaptersize = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2);
        adaptersize.notifyDataSetChanged();
        ages.setAdapter(adaptersize);
        ArrayAdapter<String> adapterqty = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list3);
        adapterqty.notifyDataSetChanged();
        phones.setAdapter(adapterqty);
        ArrayAdapter<String> adapteraddr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list4);
        adapterqty.notifyDataSetChanged();
        addrs.setAdapter(adapteraddr);

        /*dlg.create();
        dlg.show();*/

        ages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alerts(parent.getPositionForView(view));

            }
        });
        phones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alerts(parent.getPositionForView(view));

            }
        });
        addrs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alerts(parent.getPositionForView(view));

            }
        });
        names.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alerts(parent.getPositionForView(view));

            }
        });





    }
    public void alerts(int postion){
        final AlertDialog.Builder dlg = new AlertDialog.Builder(this);

        LayoutInflater inf = LayoutInflater.from(this);
        View vw = inf.inflate(R.layout.detail_dialog, null);

        dlg.setView(vw);
        dlg.setCancelable(true);
        TextView names = (TextView) vw.findViewById(R.id.listname);
        TextView ages = (TextView) vw.findViewById(R.id.listAge);
        TextView phones = (TextView)vw.findViewById(R.id.listMobile);
        TextView addrs = (TextView) vw.findViewById(R.id.listAddr);

        names.setText(list1.get(postion));
        ages.setText(list2.get(postion));
        phones.setText(list3.get(postion));
        addrs.setText(list4.get(postion));
        dlg.create();
        dlg.show();
    }
}