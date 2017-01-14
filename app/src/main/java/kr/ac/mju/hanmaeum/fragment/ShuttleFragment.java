package kr.ac.mju.hanmaeum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.ac.mju.hanmaeum.R;

public class ShuttleFragment extends Fragment {

    public ShuttleFragment() {
        // Required empty public constructor
    }

    public static ShuttleFragment newInstance() {
        ShuttleFragment fragment = new ShuttleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shuttle, container, false);
    }

}
