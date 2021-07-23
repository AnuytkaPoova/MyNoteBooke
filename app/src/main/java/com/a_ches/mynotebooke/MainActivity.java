package com.a_ches.mynotebooke;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.UUID;
/* Вызывается при исходном создании активности. */

public class MainActivity extends SingleFragmentActivity {
//активность выполняющая хостинг фрагмента
    //для этого необходимо:
    //1.Определить место фрагмента в макете
    //2. Управлять жизненым циклом ффрагмента

    private static  final String EXTRA_NOTE_ID = "com.a_ches.mynotebooke.note_id";

    public static Intent newIntent(Context packageContext, String noteId) {  // было (Context packageContext, UUID noteId)
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, noteId);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        //return new NoteFragment();
        String noteId = (String) getIntent() // было UUID noteId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_NOTE_ID);
        return NoteFragment.newInstance(noteId);
    }




}