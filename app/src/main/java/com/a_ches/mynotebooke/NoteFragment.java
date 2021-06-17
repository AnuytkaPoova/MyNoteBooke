package com.a_ches.mynotebooke;


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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.ChipGroup;

//выдает информацию о заметке, обновляется пользователем

public class NoteFragment extends Fragment {

    private Note mNote;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNote = new Note();
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
        mDateButton.setText(mNote.getmDate().toString());
        mDateButton.setEnabled(false); // блокировка кнопки

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.note_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Назначение выполнении замметки
                mNote.setmSolved(isChecked);
            }
        });
        return  v;
    }

    /*
    одключение виджетов
    В методе onCreateView(…) также настраивается реакция виджета EditText на ввод
пользователя. После того как представление будет заполнено, метод получает
ссылку на EditText и добавляет слушателя.
     */

}
