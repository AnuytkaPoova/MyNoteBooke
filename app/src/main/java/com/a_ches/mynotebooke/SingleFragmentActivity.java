package com.a_ches.mynotebooke;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public abstract class SingleFragmentActivity extends AppCompatActivity { // FragmentActivity
    protected abstract Fragment createFragment();
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Toolbar toolbar = findViewById(R.id.toolbar); //R.id.toolbar
        setSupportActionBar(toolbar);
        //новое
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //мои добавления
        DrawerLayout drawer = findViewById(R.id.nav_drawer); //было R.id.drawer_layout  // стало fragment_container
        NavigationView navigationView = findViewById(R.id.nav_view); //было R.id.nav_view  nav_view_2

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                //.setDrawerLayout(drawer)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment); //было R.id.nav_host_fragment  fragment_container
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /**
        Идентификатор контейнерного представления выполняет две функции:
        - сообщает FragmentManager, где в представлении активности должно находиться представление фрагмента;
        - обеспечивает однозначную идентификацию фрагмента в списке FragmentManager.
         */
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.nav_drawer); // было fragment_container
        // Fragment - получаем экземпляр NoteFragment по id контейнеру представления
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.nav_host_fragment, fragment) // было fragment_container, nav_drawer
                    .commit();
        }
    }
    /*
    if (fragment == null) {
fragment = createFragment();
fm.beginTransaction()
.add(R.id.nav_host_fragment, fragment) // было fragment_container
.commit();
}
     */

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment); //было R.id.nav_host_fragment  fragment_container
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
