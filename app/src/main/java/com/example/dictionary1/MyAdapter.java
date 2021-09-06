package com.example.dictionary1;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    // 創一個空間給arraylist
    List<Word> allWords = new ArrayList<>();
    public void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }

    // 做一個給switch切換的T/F
    private boolean useCardView;
    private WordViewModel wordViewModel;
    public MyAdapter(boolean useCardView, WordViewModel wordViewModel) {
        this.useCardView = useCardView;
        this.wordViewModel = wordViewModel;//將數據庫的資料傳進來
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (useCardView){
            itemView = layoutInflater.inflate(R.layout.cell_card, parent, false);
        }else {
            itemView = layoutInflater.inflate(R.layout.cell_normal, parent, false);
        }
        MyViewHolder holder = new MyViewHolder(itemView);
        holder.aSwitchCH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //getTag是廣義的類，為了抓取特定列的switch變化，先在onBindViewHolder中設置setTag，抓取某列
                Word word = (Word) holder.itemView.getTag(R.id.word_for_View_holder);
                if (isChecked){
                    holder.tvCh.setVisibility(View.GONE);
                    word.setChInvisible(true);
                }else{
                    holder.tvCh.setVisibility(View.VISIBLE);
                    word.setChInvisible(false);
                }
                wordViewModel.updateWords(word);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Word word = allWords.get(position);//取得資料
        holder.itemView.setTag(R.id.word_for_View_holder,word); // setTag(KEY,資料)
        holder.tvNum.setText(String.valueOf(position+1)); // 當前列表中的位置
        holder.tvCh.setText(word.getCh());
        holder.tvEn.setText(word.getEn());
        if (word.isChInvisible()){
            holder.tvCh.setVisibility(View.GONE); // gone是不會佔據位置
            holder.aSwitchCH.setChecked(true);
        }else{
            holder.tvCh.setVisibility(View.VISIBLE); // gone是不會佔據位置
            holder.aSwitchCH.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }
    // 內部類前面加static可以防止內存外洩
    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvNum, tvCh, tvEn;
        Switch aSwitchCH;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.tvNum);
            tvCh = itemView.findViewById(R.id.tvCh);
            tvEn = itemView.findViewById(R.id.tvEn);
            aSwitchCH = itemView.findViewById(R.id.switchChInvisible);
        }

    }

}
