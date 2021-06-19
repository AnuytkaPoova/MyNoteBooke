package com.a_ches.mynotebooke;

import java.util.Date;
import java.util.UUID;

public class Note {
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
}
