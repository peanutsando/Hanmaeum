package kr.ac.mju.hanmaeum.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;

public class TerminalFragment extends Fragment {

    @BindView(R.id.terminal01)
    ImageView terminal01;
    @BindView(R.id.terminal02)
    ImageView terminal02;
    @BindView(R.id.terminal03)
    ImageView terminal03;
    @BindView(R.id.terminal04)
    ImageView terminal04;
    @BindView(R.id.terminal05)
    ImageView terminal05;
    @BindView(R.id.terminal06)
    ImageView terminal06;

    public TerminalFragment() {
        // Required empty public constructor
    }

    public static TerminalFragment newInstance() {
        TerminalFragment fragment = new TerminalFragment();
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
        View view = inflater.inflate(R.layout.fragment_terminalk, container, false);
        ButterKnife.bind(this, view);

        setImageView();
        return view;
    }

    private void setImageView(){
        Glide.with(getActivity())
                .load(R.drawable.terminal01)
                .into(terminal01);

        Glide.with(getActivity())
                .load(R.drawable.terminal02)
                .into(terminal02);

        Glide.with(getActivity())
                .load(R.drawable.terminal03)
                .into(terminal03);

        Glide.with(getActivity())
                .load(R.drawable.terminal04)
                .into(terminal06);

        Glide.with(getActivity())
                .load(R.drawable.terminal05)
                .into(terminal05);

        Glide.with(getActivity())
                .load(R.drawable.terminal06)
                .into(terminal04);

    }
}
