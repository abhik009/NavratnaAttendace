package com.pci.navratnaattendace;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.pci.navratnaattendace.adapter.ShgDetailAdapter;
import com.pci.navratnaattendace.db.AppsDatabase;
import com.pci.navratnaattendace.db.Shg;
import com.pci.navratnaattendace.db.User;
import com.pci.navratnaattendace.models.UsersViewModel;
import org.aviran.cookiebar2.CookieBar;
import org.aviran.cookiebar2.OnActionClickListener;
import java.util.ArrayList;
import java.util.List;
import es.dmoral.toasty.Toasty;

public class ShgdetailFrag extends Fragment implements ShgDetailAdapter.OnItemClickListener, ShgDetailAdapter.OnItemLongClickListener {
    private AppsDatabase database;
    private int rowId;
    private int position;
    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton fbtn;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;
    private RecyclerView recyclerView;
    private ShgDetailAdapter adapter;
    private NavController controller;
    private String district, block, vo, village;
    private List<Shg> shgList = new ArrayList<>();

    public ShgdetailFrag() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        new GetAllItems().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.frag_shg, container, false);
        database = AppsDatabase.getInstance(getContext());
        recyclerView = view.findViewById(R.id.shgDetailRecycler);
        ViewModelProviders.of(getActivity()).get(UsersViewModel.class).getUserdata().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    district = user.getDistrict();
                    block = user.getBlock();
                    vo = user.getVoName();
                    village = user.getVillage();
                } else {
                    Toasty.error(getContext(), "user object is null", Toasty.LENGTH_LONG).show();
                }
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new ShgDetailAdapter(getContext(), shgList, this, this);
        recyclerView.setAdapter(adapter);
        fbtn = view.findViewById(R.id.floatingActionButton);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditShg();
            }
        });
        return view;
    }

    private void addEditShg() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.add_shg, null);
        builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        final TextInputEditText edName = view.findViewById(R.id.addShgName);
        final TextInputEditText edTM = view.findViewById(R.id.addShgTM);
        builder.setCancelable(true)
                .setPositiveButton("जमा करें", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("रद करें",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edName.getText().toString().isEmpty()) {
                    edName.setError("समूह का नाम दर्ज करें ");
                    return;
                } else if (edTM.getText().toString().isEmpty()) {
                    edTM.setError("समूह के सदस्यों की संख्या दर्ज करें ");
                } else {
                    new InsertShg().execute(new Shg(0, district, block, vo, village,
                            edName.getText().toString(),
                            Integer.parseInt(edTM.getText().toString())));
                }
            }
        });
    }

    @Override
    public void onItemClicked(Shg shg) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("shgDetails", shg);
        controller.navigate(R.id.memberFrag, bundle);
    }

    @Override
    public void onItemLongClicked(Shg shg, int position) {
        rowId = shg.getShgId();
        this.position = position;
        deleteShg(shg);
    }

    private void deleteShg(final Shg shg) {
        CookieBar.build(getActivity())
                .setTitle("क्या आप इस समूह और इससे जुड़ी जानकारी को मिटाना चाहती हैं?")
                .setIcon(R.drawable.ic_cancel)
                .setAction("मिटाएँ", new OnActionClickListener() {
                    @Override
                    public void onClick() {
                        new DeleteShg().execute(shg);
                    }
                })
                .setCookiePosition(CookieBar.BOTTOM)
                .show();
    }

    private class InsertShg extends AsyncTask<Shg, Void, Long> {

        @Override
        protected Long doInBackground(Shg... shgs) {
            long id = database.shgdao().insertShg(shgs[0]);
            shgList.add(shgs[0]);
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

    private class GetAllItems extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            shgList.clear();
            shgList.addAll(database.shgdao().getShgTable());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }

    private class DeleteShg extends AsyncTask<Shg, Void, Void> {

        @Override
        protected Void doInBackground(Shg... shgs) {
            database.shgdao().deleteShg(shgs[0]);
            database.memberdao().deleteShgMember(shgs[0].getShgName());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            shgList.remove(position);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        CookieBar.dismiss(getActivity());
    }
}
