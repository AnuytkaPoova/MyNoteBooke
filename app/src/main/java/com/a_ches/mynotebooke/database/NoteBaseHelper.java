package com.a_ches.mynotebooke.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.concurrent.atomic.DoubleAccumulator;

import static com.a_ches.mynotebooke.database.NoteDbSchema.NoteTable.*;

/**
 Класс не подключен (база)
 */

public class NoteBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "noteBase.db";


    public NoteBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         // было "Create table " + NoteDbSchema.NoteTable.NAME , после выделения NoteTable
        //(Alt+Enter) и выбрали первый вариант "ADD ......." Android Studio генерирует директиву import
        //import com.bignerdranch.android.criminalintent.database.CrimeDbSchema.CrimeTable;
        //Директива позволяет ссылаться на строковые константы из CrimeDbSchema.
        //CrimeTable в форме CrimeTable.Cols.UUID (вместо того, чтобы вводить полное
        //имя CrimeDbSchema.CrimeTable.Cols.UUID)
        db.execSQL("Create table " + NAME + "(" + " _id integer primary key autoincrement, " +
                Cols.UUID + ", " +
                Cols.TITLE + ", " +
                Cols.DATE + ", " +
                Cols.SOLVED +
                ")"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
