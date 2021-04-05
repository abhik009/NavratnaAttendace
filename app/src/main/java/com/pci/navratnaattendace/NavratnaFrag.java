package com.pci.navratnaattendace;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.*;

import com.pci.navratnaattendace.models.NavratnaViewModel;

public class NavratnaFrag extends Fragment {
    private NavratnaViewModel mViewModel;

    public static NavratnaFrag newInstance() {
        return new NavratnaFrag();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NavratnaViewModel.class);
        // TODO: Use the ViewModel


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navratna_frag, container, false);




        return view;
    }
}