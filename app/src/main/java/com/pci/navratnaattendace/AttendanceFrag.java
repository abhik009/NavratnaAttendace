package com.pci.navratnaattendace;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pci.navratnaattendace.db.AppsDatabase;
import com.pci.navratnaattendace.db.Attendance;
import com.pci.navratnaattendace.db.Shg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import es.dmoral.toasty.Toasty;

public class AttendanceFrag extends Fragment {
    private static final int MENU_ITEM_ID = 3;
    private BottomNavigationView navbar;
    private Menu menu;
    private NavController navController;
    private AppCompatSpinner spinner;
    private ArrayAdapter shgAdapter, listAdapter;
    private TextView textView;
    private ListView listView;
    private Date date = new Date();
    private SimpleDateFormat dateFormat;
    private SparseBooleanArray checkedItems;
    private List shgNameList = new ArrayList<>();
    private List memberList = new ArrayList<>();
    private FirebaseUser user;
    private String atttendanceNames;
    private AppsDatabase database;

    public AttendanceFrag() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_from_top));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_attendance, container, false);
        database = AppsDatabase.getInstance(getContext());
        initView(view);
        new GetShgItems().execute();
        navbar.setOnNavigationItemSelectedListener(itemSelectedListener);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getSelectedItemPosition()>0){
                    new GetShgMemberNames().execute(spinner.getSelectedItem().toString());
                }else{
                    memberList.clear();
                    listAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                listAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                checkedItems = listView.getCheckedItemPositions();
                String values = "";
                int i = 0;
                while (i < checkedItems.size()) {
                    if (checkedItems.valueAt(i)) {
                        values += memberList.get(checkedItems.keyAt(i)) + ",";
                    }
                    i++;
                }
                values = values.replaceAll("(,)*$", "");
                atttendanceNames = values;
            }
        });

        return view;
    }

    public BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    navController.popBackStack();
                    navController.navigate(R.id.landingFrag);
                    return true;
                case R.id.nav_menu:
                    navController.popBackStack();
                    navController.navigate(R.id.menuFrag);
                    return true;
                case MENU_ITEM_ID:
                    if (ValidationChecked()) {
                        String[] items = atttendanceNames.split(",");
                        for (String memberItemName : items) {
                            new InsertTask().execute(new Attendance(0, user.getPhoneNumber(), spinner.getSelectedItem().toString(), dateFormat.format(date), memberItemName));
                        }
                    }
                    return true;
            }
            return false;
        }
    };

    private boolean ValidationChecked() {
        boolean check = false;
        if (spinner.getSelectedItemPosition() == 0) {
            ((TextView) spinner.getSelectedView()).setError("ब्लॉक का चुनाव करें");
            Toasty.error(getContext(),"समूह का चुनाव करें या चुनाव में सुधार करें",Toasty.LENGTH_LONG).show();
            spinner.requestFocus();
        } else if (listView.getCheckedItemCount() == 0) {
            Toasty.error(getContext(),"आज उपस्तिथ सदस्यों चुनाव करें या चुनाव में सुधार करें",Toasty.LENGTH_LONG).show();
            listView.requestFocus();
        } else
            check = true;
        return check;
    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.shgName);
        shgAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, shgNameList);
        shgAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(shgAdapter);
        textView = view.findViewById(R.id.attendaceDate);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        textView.setText("तारीख : " + dateFormat.format(date));
        listView = view.findViewById(R.id.listView);
        listAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, memberList);
        listView.setAdapter(listAdapter);
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navbar = getActivity().findViewById(R.id.nav_view);
        menu = navbar.getMenu();
        menu.add(Menu.NONE, MENU_ITEM_ID, 2, "SUBMIT").setIcon(R.drawable.ic_diamond).setChecked(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        menu.removeItem(MENU_ITEM_ID);
    }

    private class InsertTask extends AsyncTask<Attendance, Void, Void> {
        @Override
        protected Void doInBackground(Attendance... attendances) {
            long id = database.attendancedao().insert(attendances[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            navController.navigateUp();
        }
    }

    private class GetShgItems extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            shgNameList.clear();
            shgNameList.add("समूह के नाम");
            shgNameList.addAll(database.shgdao().getShgsNames());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            shgAdapter.notifyDataSetChanged();
        }
    }

    private class GetShgMemberNames extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            memberList.clear();
            memberList.addAll(database.memberdao().getMemberNames(strings[0]));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listAdapter.notifyDataSetChanged();
        }
    }
}
