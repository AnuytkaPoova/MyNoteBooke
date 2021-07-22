package com.a_ches.mynotebooke;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.*;

public class NoteListFragment extends Fragment {

    private static final int REQUEST_NONE = 1;
    private RecyclerView mNoteRecyclerView;
    private NoteAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);  // было fragment_note_list //menu

        mNoteRecyclerView = (RecyclerView) view.findViewById(R.id.note_recycler_view);
        mNoteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu); // заменила fragment_note_list на menu
    }
   /*
    //новое (для меню (добавления новой запсики) но меню в другом месте подключена)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_note:
                Note note = new Note();
                NoteLab.get(getActivity()).addNote(note);
                Intent intent = NotePagerActivity
                        .newIntent(getActivity(), note.getmId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    */

    private void updateUI() {
        NoteLab noteLab = NoteLab.get(getActivity());
        List<Note> notes = noteLab.getNotes();
        if (mAdapter == null) {
            mAdapter = new NoteAdapter(notes);
            mNoteRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        //private CheckBox mSolvedCheckBox;
        private ImageView mSolvedImageView;
        private Note mNote;

        //версия 2017 года
        public NoteHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_note, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.note_title); // изменила id

            mDateTextView = (TextView)
                    itemView.findViewById(R.id.note_date);
            mSolvedImageView = (ImageView)
                    itemView.findViewById(R.id.note_solved);
        }

        /* Версия 2016 года
        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //mTitleTextView = (TextView) itemView;
            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_note_title_text_view); // изменила id
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_note_date_text_view);
            mSolvedCheckBox = (CheckBox)
                    itemView.findViewById(R.id.list_item_note_solved_check_box);
        }*/

        public void bindNote(Note note) {
            mNote = note;
            mTitleTextView.setText(mNote.getmTitle());
            mDateTextView.setText(mNote.getmDate().toString());
            //mSolvedCheckBox.setChecked(mNote.ismSolved());
            mSolvedImageView.setVisibility(note.ismSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
            /*
            Intent intent = MainActivity.newIntent(getActivity(), mNote.getmId());
            startActivity(intent);

             */


            //Toast.makeText(getActivity(), mNote.getmTitle() + " cliched!", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(getActivity(), MainActivity.class);
            //startActivityForResult(intent, REQUEST_NONE);

             //не работает  NotePagerActivity
            Intent intent = NotePagerActivity.newIntent(getActivity(), mNote.getmId());
            startActivity(intent);
            //Также необходимо добавить NotePagerActivity в манифест, чтобы ОС могла запустить эту активность.
            // Пока манифест будет открыт, заодно удалите объявление MainActivity (у меня NoteListActivity).
            // Для этого достаточно заменить в манифесте NoteActivity (NoteListActivity) на
            //NotePagerActivity


        }
    }


    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder> {

        private List<Note> mNotes;

        public NoteAdapter(List<Note> notes) {
            this.mNotes = notes;
        }

        @NonNull
        @Override
        public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //по уроку
            //View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_note, parent,false);
            //return new NoteHolder(itemView);
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            //верися 2017
            return new NoteHolder(layoutInflater, parent);
            /*версия 2016года
           View view = layoutInflater
                   .inflate(R.layout.list_item_note, parent, false); //list_item_note, из книги simple_list_item_1
           return new NoteHolder(view);
          */
        }

        @Override
        public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
            Note note = mNotes.get(position);
            holder.bindNote(note);
            //holder.mTitleTextView.setText(note.getmTitle()); //убрать
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }
    }

    /*
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

     */


}
