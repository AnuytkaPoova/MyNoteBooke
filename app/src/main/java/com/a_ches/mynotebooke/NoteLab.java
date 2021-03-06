package com.a_ches.mynotebooke;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NoteLab {
    private static NoteLab sNoteLab;
    private static List<Note> mNotes;


    private NoteLab(Context context) {
       //Генерирование тестовых объектов
       //mAppContex = appContex;
        mNotes = new ArrayList<>(); //оставить
          for (int i = 0; i < 10 ; i++) {
            Note note = new Note();
            note.setmTitle(" Note  " + i);
            note.setmSolved(i % 2 == 0);// для каждого второго обьекта
            //note.setmDate(new Date()); /// мое добавление
            mNotes.add(note);
        }
    }

    public static NoteLab get(Context context) {
        if (sNoteLab == null) {
            sNoteLab = new NoteLab(context);
        }

        return sNoteLab;
    }

    public List<Note> getNotes() {
        return mNotes;
    }
    public Note getNote(UUID id) {
        for (Note note: mNotes) {
            if (note.getmId().equals(id)) {
                return note;
            }
        }
        return null;
    }
}
