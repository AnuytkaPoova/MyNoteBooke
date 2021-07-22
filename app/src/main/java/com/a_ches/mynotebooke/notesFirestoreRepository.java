package com.a_ches.mynotebooke;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class notesFirestoreRepository implements NotesRepository {

    public static final NotesRepository INSTANCE = new notesFirestoreRepository();
    private final static String NOTES = "notes";
    private final static String DATE = "date";
    private final static String TITLE = "title";
    //private final static String IMAGE = "image"; Solved
    private final static boolean SOLVED = false;
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    public void getNotes(com.a_ches.mynotebooke.Callback<List<Note>> callback) {
        firebaseFirestore.collection(NOTES)
                .orderBy(DATE, Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            ArrayList<Note> result = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String title = (String) document.get(TITLE);
                                //String image = (String) document.get(IMAGE);
                                Boolean solved = (Boolean) document.get(String.valueOf(SOLVED));
                                Date date = ((Timestamp) document.get(DATE)).toDate();

                                result.add(new Note(document.getId(), title, solved, date)); // было new Note(document.getId(), title, image, date)
                            }
                            callback.onSuccess(result);

                        } else {
                            task.getException();
                        }
                    }
                });
    }

    @Override
    public void clear() {

    }
    @Override
    public void add(String title, boolean solved, com.a_ches.mynotebooke.Callback<Note> callback) { // String title, String imageUrl, Callback<Note> callback
        HashMap<String, Object> data = new HashMap<>();

        Date date = new Date();

        data.put(TITLE, title);
        //data.put(IMAGE, imageUrl);
        data.put(String.valueOf(SOLVED), solved); //Под вопросом, SOLVED String или solved к boolean
        data.put(DATE, date);

        firebaseFirestore.collection(NOTES)
                .add(data)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Note note = new Note(task.getResult().getId(), title, solved, date);  //new Note(task.getResult().getId(), title, imageUrl, date);

                            callback.onSuccess(note);
                        }
                    }
                });
    }

    @Override
    public void remove(Note note, com.a_ches.mynotebooke.Callback<Object> callback) {
        firebaseFirestore.collection(NOTES)
                .document(String.valueOf(note.getmId()))// .document(note.getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.onSuccess(note);
                        }
                    }
                });

    }


    public Note update(@NonNull Note note, @Nullable String title, @Nullable Date date) {
        return null;
    }
}
