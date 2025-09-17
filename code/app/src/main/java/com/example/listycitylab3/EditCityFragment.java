package com.example.listycitylab3;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {

    public interface EditCityListener {
        void onCityEdited(int position, City updatedCity);
    }

    private static final String ARG_CITY = "city";
    private static final String ARG_POSITION = "position";

    public static EditCityFragment newInstance(City city, int position) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CITY, city);
        args.putInt(ARG_POSITION, position);

        EditCityFragment fragment = new EditCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private City city;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city = (City) getArguments().getSerializable(ARG_CITY);
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_add_city, null);

        EditText cityNameInput = view.findViewById(R.id.city_name_input);
        EditText provinceInput = view.findViewById(R.id.province_input);

        // Pre-fill with data from the Bundle
        if (city != null) {
            cityNameInput.setText(city.getCityName());
            provinceInput.setText(city.getProvince());
        }

        return new AlertDialog.Builder(requireContext())
                .setTitle("Edit City")
                .setView(view)
                .setPositiveButton("Save", (dialog, which) -> {
                    String newName = cityNameInput.getText().toString();
                    String newProvince = provinceInput.getText().toString();
                    City updatedCity = new City(newName, newProvince);

                    if (getActivity() instanceof EditCityListener) {
                        ((EditCityListener) getActivity()).onCityEdited(position, updatedCity);
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .create();
    }
}
