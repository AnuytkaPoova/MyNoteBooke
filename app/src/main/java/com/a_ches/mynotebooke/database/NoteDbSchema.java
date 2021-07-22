package com.a_ches.mynotebooke.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.a_ches.mynotebooke.Note;
import com.a_ches.mynotebooke.NoteLab;

import java.util.List;
/**
 Класс не подключен (база)
 */

public class NoteDbSchema {

    public static final class NoteTable {

        public static final String NAME = "notes";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";

        }
    }








}
