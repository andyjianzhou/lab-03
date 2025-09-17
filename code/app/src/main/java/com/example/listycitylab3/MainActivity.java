package com.example.listycitylab3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityListener, EditCityFragment.EditCityListener {

    private ArrayList<City> cityDataList;
    private CityProvinceList cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView cityList = findViewById(R.id.city_list);

        cityDataList = new ArrayList<>();
        cityDataList.add(new City("Edmonton", "AB"));
        cityDataList.add(new City("Vancouver", "BC"));
        cityDataList.add(new City("Toronto", "ON"));
        cityDataList.add(new City("Hamilton", "ON"));
        cityDataList.add(new City("Denver", "CO"));
        cityDataList.add(new City("Los Angeles", "CA"));

        cityAdapter = new CityProvinceList(this, cityDataList);
        cityList.setAdapter(cityAdapter);

        FloatingActionButton fab = findViewById(R.id.fab_add_city);
        fab.setOnClickListener(v ->
                new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY")
        );

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City city = cityDataList.get(position);
            EditCityFragment.newInstance(city, position)
                    .show(getSupportFragmentManager(), "EDIT_CITY");
        });
    }

    @Override
    public void onCityAdded(City city) {
        cityDataList.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCityEdited(int position, City updatedCity) {
        cityDataList.set(position, updatedCity);
        cityAdapter.notifyDataSetChanged();
    }
}
