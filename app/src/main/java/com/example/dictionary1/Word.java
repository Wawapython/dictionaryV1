package com.example.dictionary1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {
    @PrimaryKey(autoGenerate =  true)
    private  int id;

    @ColumnInfo(name = "english_word")
    private String en;

    @ColumnInfo(name = "chinese_Meaning")
    private String ch;

    @ColumnInfo(name =  "chinese_invisible")
    private boolean chInvisible;

    public boolean isChInvisible() {
        return chInvisible;
    }

    public void setChInvisible(boolean chInvisible) {
        this.chInvisible = chInvisible;
    }

    public Word(String en, String ch) {
        this.en = en;
        this.ch = ch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }
}
