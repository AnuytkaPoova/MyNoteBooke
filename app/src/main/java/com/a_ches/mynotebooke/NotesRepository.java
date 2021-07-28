package com.a_ches.mynotebooke;

import java.util.List;

public interface NotesRepository {
    /*
    void clear();
    void getNotes(Callback<List<Note>> callback);

     */


    void getNotes(Callback<List<Note>> callback);

    void clear(Note note, Callback<Note> callback);



    void add(String title, String solved, Callback<Note> callback); // было (String title, boolean solved, Callback<Note> callback)

    void remove(Note note, Callback<Object> callback);

}
