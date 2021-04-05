package com.pci.navratnaattendace;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialog;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pci.navratnaattendace.db.*;
import java.util.*;

import es.dmoral.toasty.Toasty;

public class ShgRegisterFrag extends Fragment {

    private static final int MENU_ITEM_ID = 4;
    private AppCompatSpinner shgName, shgVillage, shgQ10, shgQ14, shgQ17;
    private AppCompatButton btnDatePicker;
    private int month, year;
    private int QS1, QS2, QS3, QS4, QS5, QS6, QS7, QS8, QS9, QS10, QS11, QS12, QS13, QS14, QS15;
    private CheckBox S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, S14, S15;
    private TextInputEditText shgQ6, shgQ8A, shgQ8B, shgQ9A, shgQ9B, shgQ9C, shgQ11, shgQ12, shgQ13, shgQ15, shgQ16;
    private ArrayAdapter aShg, aVillage;
    private List shgNameList = new ArrayList<>();
    private List villageNameList = new ArrayList<>();
    private MonthYearPickerDialogFragment monthYear;
    private FirebaseUser user;
    private BottomNavigationView navbar;
    private Menu menu;
    private AppsDatabase database;
    private NavController navController;
    private Animation viewAnim;
    private ProgressDialog mDialog;
    private insertShgTask insertdata;

    public ShgRegisterFrag() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        navbar.setOnNavigationItemSelectedListener(itemSelectedListener);
        viewAnim= AnimationUtils.loadAnimation(getContext(),R.anim.slide_right_enter);
        view.setAnimation(viewAnim);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_shg_register, container, false);
        initView(view);
        insertdata = new insertShgTask(getContext());
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                String dateTitle = "रिपोर्टिंग महीने और वर्ष का चुनाव करें";
                monthYear = MonthYearPickerDialogFragment.getInstance(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR), dateTitle);
                monthYear.show(getFragmentManager(), null);
                monthYear.setOnDateSetListener(new MonthYearPickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(int y, int m) {
                        switch (m) {
                            case 0:
                                btnDatePicker.setText("जनवरी - " + y);
                                break;
                            case 1:
                                btnDatePicker.setText("फ़रवरी - " + y);
                                break;
                            case 2:
                                btnDatePicker.setText("मार्च - " + y);
                                break;
                            case 3:
                                btnDatePicker.setText("अप्रैल - " + y);
                                break;
                            case 4:
                                btnDatePicker.setText("मई - " + y);
                                break;
                            case 5:
                                btnDatePicker.setText("जून - " + y);
                                break;
                            case 6:
                                btnDatePicker.setText("जुलाई - " + y);
                                break;
                            case 7:
                                btnDatePicker.setText("अगस्त - " + y);
                                break;
                            case 8:
                                btnDatePicker.setText("सितम्बर - " + y);
                                break;
                            case 9:
                                btnDatePicker.setText("अक्टूबर - " + y);
                                break;
                            case 10:
                                btnDatePicker.setText("नवम्बर - " + y);
                                break;
                            case 11:
                                btnDatePicker.setText("दिसम्बर - " + y);
                                break;
                        }
                        month = m;
                        year = y;
                    }
                });
            }
        });
        return view;
    }

    public BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    navController.popBackStack();
                    navController.navigate(R.id.landingFrag);
                    return true;
                case R.id.nav_menu:
                    navController.popBackStack();
                    navController.navigate(R.id.menuFrag);
                    return true;
                case MENU_ITEM_ID:
                    if (validationCheck()) {
//                        Insert Data
                        insertdata.execute(new ShgRegister(0,
                                shgName.getSelectedItem().toString(),
                                shgVillage.getSelectedItem().toString(),month,year,
                                Integer.parseInt(shgQ6.getText().toString()),
                                QS1, QS2, QS3, QS4, QS5, QS6, QS7, QS8, QS9, QS10, QS11, QS12, QS13, QS14, QS15,
                                Integer.parseInt(shgQ8A.getText().toString()),
                                Integer.parseInt(shgQ8B.getText().toString()),
                                Integer.parseInt(shgQ9A.getText().toString()),
                                Integer.parseInt(shgQ9B.getText().toString()),
                                Integer.parseInt(shgQ9C.getText().toString()),
                                shgQ10.getSelectedItemPosition(),
                                Integer.parseInt(shgQ11.getText().toString()),
                                Integer.parseInt(shgQ12.getText().toString()),
                                Integer.parseInt(shgQ13.getText().toString()),
                                shgQ14.getSelectedItemPosition(),
                                Integer.parseInt(shgQ15.getText().toString()),
                                Integer.parseInt(shgQ16.getText().toString()),
                                shgQ17.getSelectedItemPosition()));
                    }
                    return true;
            }
            return false;
        }
    };

    private boolean validationCheck() {
        boolean check = false;
        if (shgName.getSelectedItemPosition() == 0) {
            ((TextView) shgName.getSelectedView()).setError("समूह के नाम का चुनाव करें");
            Toasty.error(getContext(),"समूह का चुनाव करें या चुनाव में सुधार करें",Toasty.LENGTH_LONG).show();
            shgName.requestFocus();
        } else if (shgVillage.getSelectedItemPosition() == 0) {
            ((TextView) shgVillage.getSelectedView()).setError("गाँव के नाम का चुनाव करें");
            Toasty.error(getContext(),"गाँव के नाम का चुनाव करें या चुनाव में सुधार करें",Toasty.LENGTH_LONG).show();
        } else if (shgQ6.getText().toString().isEmpty()) {
            shgQ6.setError("समूह के सदस्यों की संख्या दर्ज करें"); // need to ask can i fetch it from members table total members
        } else if (!Q7()) {
            Toasty.error(getContext(),"कृपया इस महीने रोल आउट हुए मोडुल का चयन करें",Toasty.LENGTH_LONG).show();
        } else if (shgQ8A.getText().toString().trim().isEmpty()) {
            shgQ8A.setError(" सेशन रोल आउट के दौरान उपस्तिथ समूह के सदस्यों की संख्या दर्ज करें");
        } else if (shgQ8B.getText().toString().isEmpty()) {
            shgQ8B.setError(" सेशन रोल आउट के दौरान उपस्तिथ अन्य सदस्यों की संख्या दर्ज करें");
        } else if (shgQ9A.getText().toString().trim().isEmpty()) {
            shgQ9A.setError("कृपया समूह के घरों की संख्या दर्ज करें जहाँ गर्भवती महिलाऐं हैं");
        } else if (shgQ9B.getText().toString().trim().isEmpty()) {
            shgQ9B.setError("कृपया समूह के वैसे घरों की संख्या दर्ज करें जिनमें धात्री माताओं के बच्चे की उम्र 0-5 माह हैं");
        } else if (shgQ9C.getText().toString().trim().isEmpty()) {
            shgQ9C.setError("कृपया समूह के वैसे घरों की संख्या दर्ज करें जिनमें धात्री माताओं के बच्चे की उम्र 6-11 माह हैं");
        } else if (shgQ10.getSelectedItemPosition() == 0) {
            ((TextView) shgQ10.getSelectedView()).setError("Q10 में एक उत्तर का चुनाव करें");
            Toasty.error(getContext(),"कृपया Q10 के उत्तर का चुनाव या उत्तर में सुधार करें",Toasty.LENGTH_LONG).show();
        } else if (shgQ10.getSelectedItemPosition() == 1 && shgQ11.getText().toString().trim().isEmpty()) {
            shgQ11.setError("कृपया Q11 के उत्तर का चुनाव या उत्तर में सुधार करें");
        } else if (shgQ12.getText().toString().trim().isEmpty()) {
            shgQ12.setError("कृपया Q12 के उत्तर का चुनाव या उत्तर में सुधार करें");
        } else if (shgQ13.getText().toString().trim().isEmpty()) {
            shgQ13.setError("कृपया Q13 के उत्तर का चुनाव या उत्तर में सुधार करें");
        } else if (shgQ14.getSelectedItemPosition() == 0) {
            ((TextView) shgQ14.getSelectedView()).setError("Q14 में एक उत्तर का चुनाव करें");
            Toasty.error(getContext(),"कृपया Q14 के उत्तर का चुनाव या उत्तर में सुधार करें",Toasty.LENGTH_LONG).show();
        } else if (shgQ14.getSelectedItemPosition() == 1 && shgQ15.getText().toString().trim().isEmpty()) {
            shgQ15.setError("कृपया Q15 के उत्तर में सुधार करें");
        } else if (shgQ14.getSelectedItemPosition() == 1 && shgQ16.getText().toString().trim().isEmpty()) {
            shgQ16.setError("कृपया Q16 के उत्तर में सुधार करें");
        } else if (shgQ17.getSelectedItemPosition() == 0) {
            ((TextView) shgQ17.getSelectedView()).setError("ERROR");
            Toasty.error(getContext(),"कृपया Q17 के उत्तर का चुनाव या उत्तर में सुधार करें",Toasty.LENGTH_LONG).show();
        } else
            check = true;

        return check;
    }

    private boolean Q7() {
        boolean checked = false;
        QS1 = QS2 = QS3 = QS4 = QS5 = QS6 = QS7 = QS8 = QS9 = QS10 = QS11 = QS12 = QS13 = QS14 = QS15 = 0;
        if (S1.isChecked()) {
            QS1 = 1;
            checked = true;
        }
        if (S2.isChecked()) {
            QS2 = 1;
            checked = true;
        }
        if (S3.isChecked()) {
            QS3 = 1;
            checked = true;
        }
        if (S4.isChecked()) {
            QS4 = 1;
            checked = true;
        }
        if (S5.isChecked()) {
            QS5 = 1;
            checked = true;
        }
        if (S6.isChecked()) {
            QS6 = 1;
            checked = true;
        }
        if (S7.isChecked()) {
            QS7 = 1;
            checked = true;
        }
        if (S8.isChecked()) {
            QS8 = 1;
            checked = true;
        }
        if (S9.isChecked()) {
            QS9 = 1;
            checked = true;
        }
        if (S10.isChecked()) {
            QS10 = 1;
            checked = true;
        }
        if (S11.isChecked()) {
            QS11 = 1;
            checked = true;
        }
        if (S12.isChecked()) {
            QS12 = 1;
            checked = true;
        }
        if (S13.isChecked()) {
            QS13 = 1;
            checked = true;
        }
        if (S14.isChecked()) {
            QS14 = 1;
            checked = true;
        }
        if (S15.isChecked()) {
            QS15 = 1;
            checked = true;
        }
        return checked;
    }

    private void initView(View view) {
        user = FirebaseAuth.getInstance().getCurrentUser();

        shgNameList.add("समूह का नाम");
        shgNameList.add("रेखा समूह");
        shgNameList.add("शबनम समूह");
        shgNameList.add("देवी समूह");
        shgNameList.add("जानकी समूह");
        shgName = view.findViewById(R.id.regShgName);
        aShg = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, shgNameList);
        aShg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shgName.setAdapter(aShg);

        villageNameList.add("गाँव का नाम");
        villageNameList.add("रेखा");
        villageNameList.add("शबनम");
        villageNameList.add("देवी");
        villageNameList.add("जानकी");
        shgVillage = view.findViewById(R.id.regShgVillage);
        aVillage = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, villageNameList);
        aVillage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shgVillage.setAdapter(aVillage);

        btnDatePicker = view.findViewById(R.id.repMonthYear);
        shgQ6 = view.findViewById(R.id.regShgQ6);
        S1 = view.findViewById(R.id.regShgS1);
        S2 = view.findViewById(R.id.regShgS2);
        S3 = view.findViewById(R.id.regShgS3);
        S4 = view.findViewById(R.id.regShgS4);
        S5 = view.findViewById(R.id.regShgS5);
        S6 = view.findViewById(R.id.regShgS6);
        S7 = view.findViewById(R.id.regShgS7);
        S8 = view.findViewById(R.id.regShgS8);
        S9 = view.findViewById(R.id.regShgS9);
        S10 = view.findViewById(R.id.regShgS10);
        S11 = view.findViewById(R.id.regShgS11);
        S12 = view.findViewById(R.id.regShgS12);
        S13 = view.findViewById(R.id.regShgS13);
        S14 = view.findViewById(R.id.regShgS14);
        S15 = view.findViewById(R.id.regShgS15);
        shgQ8A = view.findViewById(R.id.regShgQ8A);
        shgQ8B = view.findViewById(R.id.regShgQ8B);
        shgQ9A = view.findViewById(R.id.regShgQ9A);
        shgQ9B = view.findViewById(R.id.regShgQ9B);
        shgQ9C = view.findViewById(R.id.regShgQ9C);
        shgQ10 = view.findViewById(R.id.regShgQ10);
        shgQ11 = view.findViewById(R.id.regShgQ11);
        shgQ12 = view.findViewById(R.id.regShgQ12);
        shgQ13 = view.findViewById(R.id.regShgQ13);
        shgQ14 = view.findViewById(R.id.regShgQ14);
        shgQ15 = view.findViewById(R.id.regShgQ15);
        shgQ16 = view.findViewById(R.id.regShgQ16);
        shgQ17 = view.findViewById(R.id.regShgQ17);
        database = AppsDatabase.getInstance(getContext());

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

    private class insertShgTask extends AsyncTask<ShgRegister,Void,Void>{
        private Context ctx;

        public insertShgTask(Context context){
            ctx = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog = new ProgressDialog(ctx);
            mDialog.setMessage("Doing something...");
            mDialog.setCancelable(false);
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mDialog.show();
        }

        @Override
        protected Void doInBackground(ShgRegister... shgRegisters) {
            long id = database.shgRegisterdao().insert(shgRegisters[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
            mDialog.dismiss();
            navController.navigateUp();
        }
    }
}