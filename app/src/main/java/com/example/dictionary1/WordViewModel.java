package com.example.dictionary1;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository wordRepository;
    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public LiveData<List<Word>> getAllWordsLive() {
        return wordRepository.getAllWordsLive();
    }

    // 寫接口
    void insertWords(Word... words){
        wordRepository.insertWords(words);
    }
    void updateWords(Word... words){
        wordRepository.updateWords(words);
    }
    void deleteAllWords(){
        wordRepository.deleteAllWords();
    }
}
