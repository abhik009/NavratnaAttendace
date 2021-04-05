package com.pci.navratnaattendace;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.pci.navratnaattendace.adapter.MemberAdapter;
import com.pci.navratnaattendace.db.AppsDatabase;
import com.pci.navratnaattendace.db.Member;
import com.pci.navratnaattendace.db.Shg;

import org.aviran.cookiebar2.CookieBar;
import org.aviran.cookiebar2.OnActionClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class MemberFrag extends Fragment implements MemberAdapter.OnItemClickListener, MemberAdapter.OnItemLongClickListener {

    private int position;
    private Shg shgObj;
    private RecyclerView memRecyclerView;
    private AppsDatabase database;
    private FloatingActionButton fabClick;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private TextView title;
    private LinearLayout lQ3, lQ5, lQ7, lQ9, lQ11, lQ13, lQ18;
    private MemberAdapter adapter;
    private TextInputEditText memVilage, memName, memAge, memQ2, memQ3, memQ4, memQ5, memQ6, memQ7, memQ10, memQ11, memQ12, memQ13, memMobile;
    private Spinner memQ1, memQ8, memQ9, memQ14, memQ15, memQ16, memQ17, memQ18;
    private List<Member> memberList = new ArrayList<>();

    public MemberFrag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_member, container, false);
        shgObj = (Shg) getArguments().getSerializable("shgDetails");
        database = AppsDatabase.getInstance(getContext());
        memRecyclerView = view.findViewById(R.id.memberRecycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        memRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new MemberAdapter(getContext(), memberList, this, this);
        memRecyclerView.setAdapter(adapter);
        new MemberItems().execute(shgObj.getShgName());
        fabClick = view.findViewById(R.id.memberFab);
        fabClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditMember();
            }
        });
        return view;
    }

    public void addEditMember() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.add_member, null);
        initView(view);
        builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        builder.setCancelable(false)
                .setPositiveButton("जमा करें", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("रद्द करें", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog = builder.create();
        alertDialog.show();
        memQ2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty() && Integer.parseInt(s.toString()) > 0) {
                    lQ3.setVisibility(View.VISIBLE);
                    memQ3.setVisibility(View.VISIBLE);
                } else {
                    memQ3.setVisibility(View.GONE);
                    lQ3.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        memQ4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty() && Integer.parseInt(s.toString()) > 0) {
                    lQ5.setVisibility(View.VISIBLE);
                    memQ5.setVisibility(View.VISIBLE);
                } else {
                    lQ5.setVisibility(View.GONE);
                    memQ5.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        memQ6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty() && Integer.parseInt(s.toString()) > 0) {
                    lQ7.setVisibility(View.VISIBLE);
                    memQ7.setVisibility(View.VISIBLE);
                } else {
                    lQ7.setVisibility(View.GONE);
                    memQ7.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        memQ8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1)
                    lQ9.setVisibility(View.VISIBLE);
                else
                    lQ9.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        memQ10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty() && Integer.parseInt(s.toString()) > 0) {
                    lQ11.setVisibility(View.VISIBLE);
                    memQ11.setVisibility(View.VISIBLE);
                } else {
                    lQ11.setVisibility(View.GONE);
                    memQ11.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        memQ12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty() && Integer.parseInt(s.toString()) > 0) {
                    lQ13.setVisibility(View.VISIBLE);
                    memQ13.setVisibility(View.VISIBLE);
                } else {
                    lQ13.setVisibility(View.GONE);
                    memQ13.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        memQ17.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1)
                    lQ18.setVisibility(View.VISIBLE);
                else
                    lQ18.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validations()) {
                            new InsertMembers().execute(new Member(0,
                                    shgObj.getDistrict(),
                                    shgObj.getBlock(),
                                    shgObj.getVo(),
                                    shgObj.getShgName(),
                                    memVilage.getText().toString(),
                                    memName.getText().toString(),
                                    Integer.parseInt(memAge.getText().toString()),
                                    memQ1.getSelectedItemPosition(),
                                    Integer.parseInt(memQ2.getText().toString()),
                                    Integer.parseInt(memQ2.getText().toString()) == 0 ? 0 : Integer.parseInt(memQ3.getText().toString()),
                                    Integer.parseInt(memQ4.getText().toString()),
                                    Integer.parseInt(memQ4.getText().toString()) == 0 ? 0 : Integer.parseInt(memQ5.getText().toString()),
                                    Integer.parseInt(memQ6.getText().toString()),
                                    Integer.parseInt(memQ6.getText().toString()) == 0 ? 0 : Integer.parseInt(memQ7.getText().toString()),
                                    memQ8.getSelectedItemPosition(),
                                    memQ9.getSelectedItemPosition(),
                                    Integer.parseInt(memQ10.getText().toString()),
                                    Integer.parseInt(memQ10.getText().toString()) == 0 ? 0 : Integer.parseInt(memQ11.getText().toString()),
                                    Integer.parseInt(memQ12.getText().toString()),
                                    Integer.parseInt(memQ12.getText().toString()) == 0 ? 0 : Integer.parseInt(memQ13.getText().toString()),
                                    memQ14.getSelectedItemPosition(),
                                    memQ15.getSelectedItemPosition(),
                                    memQ16.getSelectedItemPosition(),
                                    memQ17.getSelectedItemPosition(),
                                    memQ18.getSelectedItemPosition(),
                                    memMobile.getText().toString()
                            ));
                        } else
                            Toasty.error(getContext(), "Data Insert Failed", Toasty.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onItemClicked(Member member, int position) {
    }

    @Override
    public void onItemLongClicked(Member member, int position) {
        this.position = position;
        deleteMember(member);
    }

    private void deleteMember(final Member member) {
        CookieBar.build(getActivity())
                .setTitle("क्या आप इस सदस्य और इससे जुड़ी जानकारी को मिटाना चाहती हैं?")
                .setIcon(R.drawable.ic_cancel)
                .setEnableAutoDismiss(true)
                .setActionColor(R.color.warningColor)
                .setAction("मिटाएँ", new OnActionClickListener() {
                    @Override
                    public void onClick() {
                        new DeleteMember().execute(member);
                    }
                })
                .setCookiePosition(CookieBar.BOTTOM)
                .show();
    }

    public class InsertMembers extends AsyncTask<Member, Void, Long> {
        @Override
        protected Long doInBackground(Member... members) {
            long id = database.memberdao().insertMember(members[0]);
            memberList.add(members[0]);
            return id;
        }
        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            if (aLong != null) {
                adapter.notifyDataSetChanged();
                alertDialog.dismiss();
            }
        }
    }

    public class MemberItems extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            memberList.clear();
            memberList.addAll(database.memberdao().getShgMembers(strings[0]));
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }

    public class DeleteMember extends AsyncTask<Member, Void, Void> {
        @Override
        protected Void doInBackground(Member... members) {
            database.memberdao().deleteMembers(members[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            memberList.remove(position);
            adapter.notifyDataSetChanged();
        }
    }

    private void initView(View view) {
        title = view.findViewById(R.id.addMemTitle);
        title.setText(shgObj.getShgName() + " समूह");
        memVilage = view.findViewById(R.id.memVillage);
        memName = view.findViewById(R.id.memName);
        memAge = view.findViewById(R.id.memAge);
        memQ1 = view.findViewById(R.id.optionMemQ1);
        memQ2 = view.findViewById(R.id.optionMemQ2);
        memQ3 = view.findViewById(R.id.optionMemQ3);
        memQ4 = view.findViewById(R.id.optionMemQ4);
        memQ5 = view.findViewById(R.id.optionMemQ5);
        memQ6 = view.findViewById(R.id.optionMemQ6);
        memQ7 = view.findViewById(R.id.optionMemQ7);
        memQ8 = view.findViewById(R.id.optionMemQ8);
        memQ9 = view.findViewById(R.id.optionMemQ9);
        memQ10 = view.findViewById(R.id.optionMemQ10);
        memQ11 = view.findViewById(R.id.optionMemQ11);
        memQ12 = view.findViewById(R.id.optionMemQ12);
        memQ13 = view.findViewById(R.id.optionMemQ13);
        memQ14 = view.findViewById(R.id.optionMemQ14);
        memQ15 = view.findViewById(R.id.optionMemQ15);
        memQ16 = view.findViewById(R.id.optionMemQ16);
        memQ17 = view.findViewById(R.id.optionMemQ17);
        memQ18 = view.findViewById(R.id.optionMemQ18);
        memMobile = view.findViewById(R.id.memMobile);
        lQ3 = view.findViewById(R.id.layoutMemQ3);
        lQ5 = view.findViewById(R.id.layoutMemQ5);
        lQ7 = view.findViewById(R.id.layoutMemQ7);
        lQ9 = view.findViewById(R.id.layoutMemQ9);
        lQ11 = view.findViewById(R.id.layoutMemQ11);
        lQ13 = view.findViewById(R.id.layoutMemQ13);
        lQ18 = view.findViewById(R.id.layoutMemQ18);
    }

    private boolean validations() {
        boolean check = true;

        if (memVilage.getText().toString().trim().isEmpty()) {
            memVilage.setError("गाँव का नाम लिखें");
            check = false;
            memVilage.requestFocus();
        }

        if (memName.getText().toString().trim().isEmpty()) {
            memName.setError("समूह सदस्य का नाम लिखें");
            check = false;
            memName.requestFocus();
        }

        if (memAge.getText().toString().trim().isEmpty()) {
            memAge.setError("समूह सदस्य की उम्र लिखें");
            check = false;
            memAge.requestFocus();
        } else if (Integer.parseInt(memAge.getText().toString()) < 15) {
            memAge.setError("समूह सदस्य की उम्र 15 वर्ष से अधिक होनी चाहिए");
            check = false;
            memAge.requestFocus();
        }

        if (memQ1.getSelectedItemPosition() == 0) {
            ((TextView) memQ1.getSelectedView()).setError("Error");
            Toasty.error(getContext(), "कृपया सही उत्तर का चुनाव करें", Toasty.LENGTH_LONG).show();
            check = false;
            memQ1.requestFocus();
        }

        if (memQ2.getText().toString().trim().isEmpty()) {
            memQ2.setError("कृपया 0-6 माह के बच्चों की संख्या लिखें");
            check = false;
            memQ2.requestFocus();
        } else if (Integer.parseInt(memQ2.getText().toString()) > 0) {
            if (memQ3.getText().toString().trim().isEmpty()) {
                memQ3.setError("कृपया बच्चों की संख्या लिखें");
                check = false;
                memQ3.requestFocus();
            } else if (Integer.parseInt(memQ3.getText().toString()) > Integer.parseInt(memQ2.getText().toString())) {
                memQ3.setError("कृपया बच्चों की सही संख्या लिखें");
                check = false;
                memQ3.requestFocus();
            }
        }

        if (memQ4.getText().toString().isEmpty()) {
            memQ4.setError("कृपया 6-23 माह के बच्चों की संख्या लिखें");
            check = false;
            memQ4.requestFocus();
        } else if (Integer.parseInt(memQ4.getText().toString()) > 0) {
            if (memQ5.getText().toString().trim().isEmpty()) {
                memQ5.setError("कृपया बच्चों की संख्या लिखें");
                check = false;
                memQ5.requestFocus();
            } else if (Integer.parseInt(memQ5.getText().toString()) > Integer.parseInt(memQ4.getText().toString())) {
                memQ5.setError("कृपया बच्चों की सही संख्या लिखें");
                check = false;
                memQ5.requestFocus();
            }
        }

        if (memQ6.getText().toString().isEmpty()) {
            memQ6.setError("कृपया 2 वर्ष से छोटे बच्चों की संख्या लिखें ");
            check = false;
            memQ6.requestFocus();
        } else if (Integer.parseInt(memQ6.getText().toString()) > 0) {
            if (memQ7.getText().toString().isEmpty()) {
                memQ7.setError("कृपया बच्चों की संख्या लिखें");
                check = false;
                memQ7.requestFocus();
            } else if (Integer.parseInt(memQ7.getText().toString()) > Integer.parseInt(memQ6.getText().toString())) {
                memQ7.setError("कृपया बच्चों की सही संख्या लिखें");
                check = false;
                memQ7.requestFocus();
            }
        }

        if (memQ8.getSelectedItemPosition() == 0) {
            ((TextView) memQ8.getSelectedView()).setError("Error");
            check = false;
            Toasty.error(getContext(), "कृपया उत्तर का चुनाव करें", Toasty.LENGTH_LONG).show();
        } else if (memQ8.getSelectedItemPosition() == 1 && memQ9.getSelectedItemPosition() == 0) {
            ((TextView) memQ9.getSelectedView()).setError("Error");
            check = false;
            Toasty.error(getContext(), "कृपया उत्तर का चुनाव करें", Toasty.LENGTH_LONG).show();
        }

        if (memQ10.getText().toString().isEmpty()) {
            memQ10.setError("गर्भवती एवं धात्री महिला की संख्या लिखें");
            check = false;
            memQ10.requestFocus();
        } else if (Integer.parseInt(memQ10.getText().toString()) > 0) {
            if (memQ11.getText().toString().isEmpty()) {
                memQ11.setError("महिलाओं की संख्या लिखें");
                check = false;
                memQ11.requestFocus();
            } else if (Integer.parseInt(memQ10.getText().toString()) > 0 && memQ11.getText().toString().isEmpty()) {
                memQ11.setError("महिलाओं की सही संख्या लिखें");
                check = false;
                memQ11.requestFocus();
            }
        }

        if (memQ12.getText().toString().isEmpty()) {
            memQ12.setError("महिलाओं की संख्या लिखें");
            check = false;
            memQ12.requestFocus();
        } else if (Integer.parseInt(memQ12.getText().toString()) > 0) {
            if (memQ13.getText().toString().isEmpty()) {
                memQ13.setError("महिलाओं की संख्या लिखें");
                check = false;
                memQ13.requestFocus();
            } else if (Integer.parseInt(memQ12.getText().toString()) > 0 && memQ13.getText().toString().isEmpty()) {
                memQ13.setError("महिलाओं की सही संख्या लिखें");
                check = false;
                memQ13.requestFocus();
            }
        }

        if (memQ14.getSelectedItemPosition() == 0) {
            ((TextView) memQ14.getSelectedView()).setError(" ");
            Toasty.error(getContext(), "कृपया बच्चों की संख्या लिखें", Toasty.LENGTH_LONG).show();
            check = false;
            memQ14.requestFocus();
        }

        if (memQ15.getSelectedItemPosition() == 0) {
            ((TextView) memQ15.getSelectedView()).setError("");
            Toasty.error(getContext(), "कृपया उत्तर का चुनाव करें", Toasty.LENGTH_LONG).show();
            check = false;
            memQ15.requestFocus();
        }

        if (memQ16.getSelectedItemPosition() == 0) {
            ((TextView) memQ16.getSelectedView()).setError(" ");
            Toasty.error(getContext(), "कृपया उत्तर का चुनाव करें", Toasty.LENGTH_LONG).show();
            check = false;
            memQ16.requestFocus();
        }

        if (memQ17.getSelectedItemPosition() == 0) {
            ((TextView) memQ17.getSelectedView()).setError(" ");
            Toasty.error(getContext(), "कृपया उत्तर का चुनाव करें", Toasty.LENGTH_LONG).show();
            check = false;
            memQ17.requestFocus();
        }

        if (memQ17.getSelectedItemPosition() == 1 && memQ18.getSelectedItemPosition() == 0) {
            ((TextView) memQ18.getSelectedView()).setError(" ");
            Toasty.error(getContext(), "कृपया उत्तर का चुनाव करें", Toasty.LENGTH_LONG).show();
            check = false;
            memQ18.requestFocus();
        }

        if (!isValid(memMobile.getText().toString())) {
            memMobile.setError("कृपया सही मोबाइल नंबर दर्ज करें");
            check = false;
            memMobile.requestFocus();
        }

        return check;
    }

    private boolean isValid(String phone) {
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(phone);
        return (m.find() && m.group().equals(phone));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        CookieBar.dismiss(getActivity());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        CookieBar.dismiss(getActivity());
    }
}
