package com.pci.navratnaattendace;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.pci.navratnaattendace.db.AppsDatabase;
import com.pci.navratnaattendace.db.User;
import com.pci.navratnaattendace.models.UsersViewModel;


public class MenuFrag extends Fragment {

    private NavController navController;
    private CardView card_one, card_two, card_three, card_four;
    private AppsDatabase database;
    private User user;

    public MenuFrag() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_left_enter));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        database = AppsDatabase.getInstance(getContext());
        View view = inflater.inflate(R.layout.frag_menu, container, false);
        user = new User();
        new GetUserDetail().execute();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.parseColor("#263238"));
        }
        card_one = view.findViewById(R.id.item1);
        card_two = view.findViewById(R.id.item2);
        card_three = view.findViewById(R.id.item3);
        card_four = view.findViewById(R.id.item4);
        card_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                navController.popBackStack();
                navController.navigate(R.id.attendanceFrag);
            }
        });
        card_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.navratnaFrag);
            }
        });
        card_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.shgRegisterFrag);
            }
        });
        card_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.shgdetailFrag);
            }
        });
        return view;
    }

    private class GetUserDetail extends AsyncTask<Void, Void, User> {
        @Override
        protected User doInBackground(Void... voids) {
            user = database.userdao().getUserTable();
            return user;
        }

        @Override
        protected void onPostExecute(User users) {
            super.onPostExecute(users);
            if (users != null) {
                ViewModelProviders.of(getActivity()).get(UsersViewModel.class).toFrag(users);
            }
        }
    }
}
