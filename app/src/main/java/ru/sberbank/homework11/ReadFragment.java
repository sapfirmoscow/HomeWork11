package ru.sberbank.homework11;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReadFragment extends Fragment {

    private TextView mTextView;
    public ReadFragment() {
        // Required empty public constructor
    }


    public void setTextView(String value) {
        mTextView.setText(value);
    }

    public static ReadFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ReadFragment fragment = new ReadFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_read, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View v) {
        mTextView = v.findViewById(R.id.getValueTextView);
    }
}
