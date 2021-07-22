package com.a_ches.mynotebooke;

import java.util.List;

public interface NotesRepository {
    /*
    void clear();
    void getNotes(Callback<List<Note>> callback);

     */


    void getNotes(Callback<List<Note>> callback);

    void clear();



    void add(String title, boolean solved, Callback<Note> callback);

    void remove(Note note, Callback<Object> callback);

}
