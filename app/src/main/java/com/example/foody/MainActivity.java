package com.example.foody;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView listView;
    Context context;
    ConstraintLayout rela;
    ArrayList<CountriesModel> countriesData;
    CustomAdapter customAdapter;
    CountriesModel countriesModel;
    TextView edtamnhac;
    int vitri = -1;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        //getviews
        listView = findViewById(R.id.listView);
        rela = (ConstraintLayout) findViewById(R.id.rela);
//        edtamnhac=(TextView) findViewById(R.id.edittext1);
        countriesData = new ArrayList<>();

        //add Countries Data
        populateCountriesData();

        customAdapter = new CustomAdapter(context, countriesData);
        listView.setAdapter(customAdapter);
        //registerForContextMenu(listView);
        listView.setOnItemLongClickListener(new ItemLongClickRemove());
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                edtamnhac.setText(countriesData.get(i).getName());
//                vitri=i;
//            }
//        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(context, countriesData.get(position).getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,GridItemActivity.class);
                intent.putExtra("id",countriesData.get(position).getId());
                intent.putExtra("name",countriesData.get(position).getName());
                intent.putExtra("image",countriesData.get(position).getImage());
                intent.putExtra("gia",countriesData.get(position).getPopulation());
                intent.putExtra("danhgia",countriesData.get(position).getArea());
                intent.putExtra("giamgia",countriesData.get(position).getArea1());

                startActivity(intent);
            }
        });

    }
    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Bán có muốn xóa sản phẩm này!");
            alertDialogBuilder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // xóa sp đang nhấn giữ
                    countriesData.remove(position);
                    Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    //cập nhật lại listview


                }
            });
            alertDialogBuilder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //không làm gì
                }
            });
            alertDialogBuilder.show();
            return true;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
//        MenuItem menuItem=menu.findItem(R.id.search);
//        SearchView searchView=(SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Search Here!");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                return false;
//            }
//        });
        return true;
    }//super.onCreateOptionsMenu(menu)
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Cập nhật");
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.example_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.them:
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sua:
                Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.xoa:
                Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void populateCountriesData() {
        //music1
        countriesModel = new CountriesModel();
        countriesModel.setId(1);
        countriesModel.setName("Ốc Quỳnh");
        countriesModel.setImage(R.drawable.oc);
        countriesModel.setArea("140");
        countriesModel.setArea1("-23");
        countriesModel.setPopulation("200.000d");
        countriesData.add(countriesModel);

        //music2
        countriesModel = new CountriesModel();
        countriesModel.setId(2);
        countriesModel.setName("Cơm Gà Kim Ngân");
        countriesModel.setImage(R.drawable.ga);
        countriesModel.setPopulation("50.000d");
        countriesModel.setArea("1112");
        countriesModel.setArea1("-21");
        countriesData.add(countriesModel);

        //music3
        countriesModel = new CountriesModel();
        countriesModel.setId(3);
        countriesModel.setName("Trà Sữa Gemn ");
        countriesModel.setImage(R.drawable.tra);
        countriesModel.setPopulation("45.000d");
        countriesModel.setArea("195");
        countriesModel.setArea1("-23");
        countriesData.add(countriesModel);

        //music4
        countriesModel = new CountriesModel();
        countriesModel.setId(4);
        countriesModel.setName("Bánh Chuối Chiên");
        countriesModel.setImage(R.drawable.chuoi);
        countriesModel.setPopulation("10.000đ");
        countriesModel.setArea("69");
        countriesModel.setArea1("-20");
        countriesData.add(countriesModel);

        //music5
        countriesModel = new CountriesModel();
        countriesModel.setId(5);
        countriesModel.setName("Cơm Tấm Quê Hương");
        countriesModel.setImage(R.drawable.tam);
        countriesModel.setPopulation("60.000d");
        countriesModel.setArea("452");
        countriesModel.setArea1("-25");
        countriesData.add(countriesModel);

        //music6
        countriesModel = new CountriesModel();
        countriesModel.setId(6);
        countriesModel.setName("Bánh Xèo Bé Uyên ");
        countriesModel.setImage(R.drawable.xeo);
        countriesModel.setPopulation("30.000d");
        countriesModel.setArea("1236");
        countriesModel.setArea1("-26");
        countriesData.add(countriesModel);

        //music7
        countriesModel = new CountriesModel();
        countriesModel.setId(7);
        countriesModel.setName("Bánh Kẹp ");
        countriesModel.setImage(R.drawable.bo);
        countriesModel.setPopulation("80.000");
        countriesModel.setArea("32");
        countriesModel.setArea1("-25");
        countriesData.add(countriesModel);

        //music8
        countriesModel = new CountriesModel();
        countriesModel.setId(8);
        countriesModel.setName("Phở Bò");
        countriesModel.setImage(R.drawable.bo);
        countriesModel.setPopulation("45.000d");
        countriesModel.setArea("234");
        countriesModel.setArea1("-15");
        countriesData.add(countriesModel);

    }
}