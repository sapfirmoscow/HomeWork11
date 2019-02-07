package ru.sberbank.homework11;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class WriteFragment extends Fragment {

    private EditText mEditText;
    private Callback mCallback;

    public WriteFragment() {
    }

    public static WriteFragment newInstance(Callback callback) {

        Bundle args = new Bundle();
        
        WriteFragment fragment = new WriteFragment();
        fragment.setArguments(args);
        fragment.mCallback = callback;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_write, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initListeners();
    }

    private void initListeners() {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCallback.onUpdate(s.toString());//тут же отобрадаем изменения
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void initView(View v) {
        mEditText = v.findViewById(R.id.setValueEditText);
    }
}
