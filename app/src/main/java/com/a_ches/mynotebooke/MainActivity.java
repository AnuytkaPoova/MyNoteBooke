package com.a_ches.mynotebooke;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
/** Вызывается при исходном создании активности. */

public class MainActivity extends SingleFragmentActivity {
//активность выполняющая хостинг фрагмента
    //для этого необходимо:
    //1.Определить место фрагмента в макете
    //2. Управлять жизненым циклом ффрагмента


    @Override
    protected Fragment createFragment() {
        return new NoteFragment();
    }




}