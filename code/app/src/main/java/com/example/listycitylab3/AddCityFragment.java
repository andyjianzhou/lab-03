package com.example.listycitylab3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    public interface AddCityListener {
        void onCityAdded(City city);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater()
                .inflate(R.layout.fragment_add_city, null);

        EditText cityNameInput = view.findViewById(R.id.city_name_input);
        EditText provinceInput = view.findViewById(R.id.province_input);

        return new AlertDialog.Builder(requireContext())
                .setTitle("Add City")
                .setView(view)
                .setPositiveButton("Add", (dialog, which) -> {
                    String cityName = cityNameInput.getText().toString();
                    String province = provinceInput.getText().toString();
                    City city = new City(cityName, province);

                    if (getActivity() instanceof AddCityListener) {
                        ((AddCityListener) getActivity()).onCityAdded(city);
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .create();
    }
}
