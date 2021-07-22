package com.a_ches.mynotebooke;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

public class Note implements Parcelable {
    //модель

    private UUID mId;
    private  String mTitle;
    private Date mDate;
    private boolean mSolved;

    public  Note() {
        //генерирование уникаального идентификатора
        this.mId = UUID.randomUUID();
        this.mDate = new Date();

    }

    protected Note(Parcel in) { // по уроку иначе
        mTitle = in.readString();
        mSolved = in.readByte() != 0;
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

    public Note(String id, String title, Boolean solved, Date date) {
        this.mId = UUID.fromString(id);
        this.mTitle = title;
        this.mSolved = solved;
        this.mDate = date;

    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }


    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getmDate() {
        return mDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {//по уроку иначе
        dest.writeString(mTitle);
        dest.writeByte((byte) (mSolved ? 1 : 0));
    }
}
