package com.a_ches.mynotebooke;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class NotePagerActivity  extends AppCompatActivity {

    private static final String EXTRA_NOTE_ID = "com.a_ches.mynotebooke.note_id";
    private ViewPager mViewPager;
    private List<Note> mNotes;

    public static Intent newIntent(Context packageContext, UUID noteId) {
        Intent intent = new Intent(packageContext, NotePagerActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, noteId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_pager);

        UUID noteId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_NOTE_ID);


        mViewPager = (ViewPager) findViewById(R.id.note_view_pager);
        mNotes = NoteLab.get(this).getNotes();
        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new  FragmentStatePagerAdapter(fragmentManager) {// замена FragmentStatePagerAdapter
            /*
            @Override
            public int getItemCount() {
                //return 0;
                return mNotes.size();
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {

                //return null;
                Note note = mNotes.get(position);
                return NoteFragment.newInstance(note.getmId());
            }

             */

            @Override
            public Fragment getItem(int position) {
                Note note = mNotes.get(position);
                return NoteFragment.newInstance(note.getmId());
            }

            @Override
            public int getCount() {
                return mNotes.size();
            }


        });

        for (int i = 0; i < mNotes.size() ; i++) {
            if (mNotes.get(i).getmId().equals(noteId)) {
                mViewPager.setCurrentItem(i);
                break;
            }

        }
    }
}
