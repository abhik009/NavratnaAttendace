package com.pci.navratnaattendace;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.*;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LandingFrag extends Fragment {

    private NavController navController;
    private BottomNavigationView bottomNav;

    public LandingFrag() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_landing, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.WHITE);
        }
        bottomNav = getActivity().findViewById(R.id.nav_view);
        bottomNav.setVisibility(View.VISIBLE);
        return view;
    }
}
