package com.a_ches.mynotebooke;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

public class Note implements Parcelable {
    //модель

    private String mId; // было до firestor private UUID mId
    private  String mTitle;
    private Date mDate;
    private String mSolved; // было boolean mSolved

    public  Note() {
        //генерирование уникаального идентификатора
        //this.mId = UUID.randomUUID(); // было до firestor private UUID mId
        this.mDate = new Date();
    }
    public Note(String id, String title, String solved, Date date) {  //было boolean mSolved
        this.mId = id;
        this.mTitle = title;
        this.mSolved = solved;
        this.mDate = date;
    }
    protected Note(Parcel in) { // по уроку иначе
        mTitle = in.readString();
        mSolved = in.readString();
        //mSolved = in.readByte() != 0; // //было boolean mSolved
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() { //по уроку иначе
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };



    public String getmId() {
        return mId;
    }
    /*
    public UUID getmId() {
        return mId;
    }
     */

    public String getmTitle() {
        return mTitle;
    }


    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getmDate() {
        return mDate;
    }

    public String getmSolved() { //было boolean mSolved
        return mSolved;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setmSolved(String mSolved) { //было boolean mSolved
        this.mSolved = mSolved;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {//по уроку иначе
        dest.writeString(mTitle);
        dest.writeString(mSolved);
        //dest.writeByte((byte) (mSolved ? 1 : 0)); //было boolean mSolved
    }
}
