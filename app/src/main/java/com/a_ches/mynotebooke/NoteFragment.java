package com.a_ches.mynotebooke;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.ChipGroup;

import java.util.Date;
import java.util.UUID;

//выдает информацию о заметке, обновляется пользователем

public class
NoteFragment extends Fragment {
    public static final String  ARG_NOTE_ID = "note_id";
    private static final String DIALOG_DATE = "DialogDate";
    private  static final int REQUEST_DATE = 0;
    private Note mNote;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    public static NoteFragment newInstance(UUID noteId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTE_ID, noteId);

        NoteFragment fragment = new NoteFragment();
        fragment.setArguments(args);
        return  fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mNote = new Note();
        //UUID noteID = (UUID) getActivity().getIntent()
          //      .getSerializableExtra(MainActivity.EXTRA_NOTE_ID);
        UUID noteID = (UUID) getArguments().getSerializable(ARG_NOTE_ID);
        mNote = NoteLab.get(getActivity()).getNote(noteID);
    }

   /*
   В методе onCreateView(…) мы явно заполняем представление фрагмента, вызывая
LayoutInflater.inflate(…) с передачей идентификатора ресурса макета. Второй
параметр определяет родителя представления, что обычно необходимо для правильной настройки виджета.
 Третий параметр указывает, нужно ли включать заполненное представление в родителя.
  Мы передаем false, потому что представление будет добавлено в коде активности.
    */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_note, container, false);

        mTitleField = (EditText)v.findViewById(R.id.note_title);
        mTitleField.setText(mNote.getmTitle()); //новое
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //специально сотавляем пустым
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNote.setmTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                //специально сотавляем пустым
            }
        });
        //Дата выводится на кнопку, чтобы можно было вставить пиккер
        mDateButton = (Button)v.findViewById(R.id.note_date);
        updateDate();
        //mDateButton.setEnabled(false); // блокировка кнопки
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();//getFragmentManager()
                //DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mNote.getmDate());
                dialog.setTargetFragment(NoteFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);//show(manager, DIALOG_DATE)
            }
        });

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.note_solved);
        mSolvedCheckBox.setChecked(mNote.ismSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { //CompoundButton нет в примере
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Назначение выполнении замметки
                mNote.setmSolved(isChecked);
            }
        });
        return  v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if ( requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mNote.setmDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        mDateButton.setText(mNote.getmDate().toString());
    }

    //приказываете активности-хосту вернуть значение;
    public  void  returnResult() {
        getActivity().setResult(Activity.RESULT_OK, null);
    }

    /*
    одключение виджетов
    В методе onCreateView(…) также настраивается реакция виджета EditText на ввод
пользователя. После того как представление будет заполнено, метод получает
ссылку на EditText и добавляет слушателя.
     */

}
