package com.pci.navratnaattendace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        navController = Navigation.findNavController(this, R.id.mNavGraph);
        bottomNav = findViewById(R.id.nav_view);
        Navigation.setViewNavController(bottomNav, navController);
        bottomNav.setOnNavigationItemSelectedListener(itemSelectedListener);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
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
            }
            return false;
        }
    };
}