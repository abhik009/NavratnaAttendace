package com.pci.navratnaattendace;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;
import com.pci.navratnaattendace.db.AppsDatabase;
import com.pci.navratnaattendace.db.User;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.*;

import es.dmoral.toasty.Toasty;

public class SignupFrag extends Fragment {

    private AppCompatSpinner Q1, Q2;
    private TextInputEditText Q3, Q4, Q5, Q6, Q7, OTP;
    private AppCompatButton btnSubmit;
    private ArrayAdapter Q1Adapter, Q2Adapter;
    private List block = new ArrayList();
    private String mPhoneNumber;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private NavController controller;
    private AppsDatabase database;
    private String mVerificationId;

    public SignupFrag() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        userLoggedIn();
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_left_enter));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_signup, container, false);
        block.add(getResources().getResourceName(R.array.block));
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("users");
        initViews(view);
        FirebaseApp.initializeApp(getActivity());
        Q1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                block.clear();
                Q2.setSelection(0);
                if (Q1.getSelectedItemPosition() == 1) {
                    block.addAll(Arrays.asList(getResources().getStringArray(R.array.dnalanda)));
                    Q2Adapter.notifyDataSetChanged();
                } else if (Q1.getSelectedItemPosition() == 2) {
                    block.addAll(Arrays.asList(getResources().getStringArray(R.array.dnawada)));
                    Q2Adapter.notifyDataSetChanged();
                } else {
                    block.add("ब्लाक का चुनाव करें");
                    Q2Adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVerificationId != null) {
                    if (OTP.getText().toString().isEmpty() || OTP.getText().toString().length() < 6) {
                        Toasty.error(getContext(),"कृपया सही OTP दर्ज करें",Toasty.LENGTH_LONG).show();
                    } else
                        verifyPhoneNumberCode();
                } else {
                    if (validationCheck(v)) {
                        myRef.child(mPhoneNumber) //.addValueEventListener(new ValueEventListener() {
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            Toasty.error(getContext(),"फ़ोन नंबर पहले से मोजूद है",Toasty.LENGTH_LONG).show();
                                        } else
                                            startPhoneNumberVerification();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toasty.error(getContext(),""+databaseError.toString(),Toasty.LENGTH_LONG).show();
                                    }
                                });
                    }

                }
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.e("LoginError", e.toString());
                Toasty.error(getContext(),e.toString(),Toasty.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                mVerificationId = s;
                Q1.setEnabled(false);
                Q2.setEnabled(false);
                Q3.setEnabled(false);
                Q4.setEnabled(false);
                Q5.setEnabled(false);
                Q6.setEnabled(false);
                Q7.setEnabled(false);
                OTP.setVisibility(View.VISIBLE);
                btnSubmit.setText("SUBMIT OTP");
            }
        };
        return view;
    }

    private void verifyPhoneNumberCode() {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, OTP.getText().toString());
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    myRef = firebaseDatabase.getReference("users").child(mPhoneNumber);
                    // Write a message to the Firebase database
//                    myRef = firebaseDatabase.getReference("users").child(mPhoneNumber);
//                    myRef.child("district").setValue(Q1.getSelectedItem().toString());
//                    myRef.child("block").setValue(Q2.getSelectedItem().toString());
//                    myRef.child("panchyat").setValue(Q3.getText().toString());
//                    myRef.child("village").setValue(Q4.getText().toString());
//                    myRef.child("voName").setValue(Q5.getText().toString());
//                    myRef.child("cmName").setValue(Q6.getText().toString());
//                    myRef.child("cmMobile").setValue(Q7.getText().toString());
                    new InsertUser().execute(new User(
                            Q1.getSelectedItem().toString(),
                            Q2.getSelectedItem().toString(),
                            Q3.getText().toString(),
                            Q4.getText().toString(),
                            Q5.getText().toString(),
                            Q6.getText().toString(),
                            Q7.getText().toString()
                    ));
                    userLoggedIn();
                }
            }
        });
    }

    private void userLoggedIn() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            controller.navigate(R.id.landingFrag);
        }
    }

    private void startPhoneNumberVerification() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mPhoneNumber,
                60,
                TimeUnit.SECONDS,
                getActivity(),
                mCallbacks
        );
    }

    private boolean validationCheck(View view) {
        boolean check = false;
        if (Q1.getSelectedItemPosition() == 0) {
            ((TextView) Q1.getSelectedView()).setError(" ");
            Toasty.error(getContext()," जिला का चुनाव करें",Toasty.LENGTH_LONG).show();
            Q1.requestFocus();
        } else if (Q2.getSelectedItemPosition() == 0) {
            ((TextView) Q2.getSelectedView()).setError(" ");
            Toasty.error(getContext()," ब्लाक का चुनाव करें ",Toasty.LENGTH_LONG).show();
            Q2.requestFocus();
        } else if (Q3.getText().toString().trim().isEmpty()) {
            Q3.setError("पंचायत का नाम दर्ज करें");
            Q3.requestFocus();
        } else if (Q4.getText().toString().trim().isEmpty()) {
            Q4.setError("गांव का नाम दर्ज करें");
            Q4.requestFocus();
        } else if (Q5.getText().toString().trim().isEmpty()) {
            Q5.setError("ग्राम संगठन का नाम दर्ज करें");
            Q5.requestFocus();
        } else if (Q6.getText().toString().trim().isEmpty()) {
            Q6.setError("सी.एम का नाम दर्ज करें");
            Q6.requestFocus();
        } else if (Q7.getText().toString().trim().isEmpty()) {
            Q7.setError("सी.एम का मोबाइल नंबर दर्ज करें");
            Q7.requestFocus();
        } else if (!isValid(Q7.getText().toString())) {
            Q7.setError("कृपया सही मोबाइल नंबर दर्ज करें");
            Q7.requestFocus();
        } else {
            mPhoneNumber = "+91" + Q7.getText().toString();
            check = true;
        }
        return check;
    }

    private boolean isValid(String phone) {
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(phone);
        return (m.find() && m.group().equals(phone));
    }

    private void initViews(View view) {
        database = AppsDatabase.getInstance(getContext());
        Q1 = view.findViewById(R.id.Q1);
        Q1Adapter = new ArrayAdapter(getContext(), android.R.layout.simple_gallery_item, getResources().getStringArray(R.array.district));
        Q1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Q1.setAdapter(Q1Adapter);
        Q2 = view.findViewById(R.id.Q2);
        Q2Adapter = new ArrayAdapter(getContext(), android.R.layout.simple_gallery_item, block);
        Q2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Q2.setAdapter(Q2Adapter);
        Q3 = view.findViewById(R.id.Q3);
        Q4 = view.findViewById(R.id.Q4);
        Q5 = view.findViewById(R.id.Q5);
        Q6 = view.findViewById(R.id.Q6);
        Q7 = view.findViewById(R.id.Q7);
        OTP = view.findViewById(R.id.OTP);
        btnSubmit = view.findViewById(R.id.btnSubmit);
    }

    private class InsertUser extends AsyncTask<User, Void, Long> {
        @Override
        protected Long doInBackground(User... users) {
            myRef.child("district").setValue(users[0].getDistrict());
            myRef.child("block").setValue(users[0].getBlock());
            myRef.child("panchyat").setValue(users[0].getPanchyat());
            myRef.child("village").setValue(users[0].getVillage());
            myRef.child("voName").setValue(users[0].getVoName());
            myRef.child("cmName").setValue(users[0].getCmName());
            myRef.child("cmMobile").setValue(users[0].getCmMobile());
            long id = database.userdao().insert(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Long aLong) {
//            super.onPostExecute(aLong);
            if (aLong != null) {
                Toasty.success(getContext(), "Data Inserted",Toasty.LENGTH_LONG).show();
            }
        }
    }
}
