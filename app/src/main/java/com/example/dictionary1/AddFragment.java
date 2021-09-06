package com.example.dictionary1;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnSubmit;
    private EditText edEn, edCh;
    private WordViewModel wordViewModel;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wordViewModel = new ViewModelProvider(getActivity()).get(WordViewModel.class);
        btnSubmit = requireActivity().findViewById(R.id.btnSubmit);
        edCh = requireActivity().findViewById(R.id.edCh);
        edEn = requireActivity().findViewById(R.id.edEn);
        btnSubmit.setEnabled(false); // 未啟用
        edEn.requestFocus(); // 取得焦點，之後用
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE); // 生小鍵盤
        imm.showSoftInput(edEn, 0); // 點選edEn會有小鍵盤

        TextWatcher textWatcher = new TextWatcher() { // 對EditText監聽
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String en = edEn.getText().toString().trim();
                String ch = edCh.getText().toString().trim();
                btnSubmit.setEnabled(!en.isEmpty() && !ch.isEmpty());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        edEn.addTextChangedListener(textWatcher);
        edCh.addTextChangedListener(textWatcher);

        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String en2 = edEn.getText().toString().trim();
                String ch2 = edCh.getText().toString().trim();
                Word word = new Word(en2, ch2);
                wordViewModel.insertWords(word);
                NavController navController = Navigation.findNavController(v);
                navController.navigateUp();
                InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
    }
}