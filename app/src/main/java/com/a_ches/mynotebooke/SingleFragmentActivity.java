package com.a_ches.mynotebooke;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity extends FragmentActivity {
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        /*
        Идентификатор контейнерного представления выполняет две функции:
        - сообщает FragmentManager, где в представлении активности должно находиться представление фрагмента;
        - обеспечивает однозначную идентификацию фрагмента в списке FragmentManager.
         */
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        // Fragment - получаем экземпляр NoteFragment по id контейнеру представления
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
