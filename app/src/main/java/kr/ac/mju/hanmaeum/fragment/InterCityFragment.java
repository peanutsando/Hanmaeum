package kr.ac.mju.hanmaeum.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import kr.ac.mju.hanmaeum.R;

public class InterCityFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    @BindView(R.id.search)
    ImageView search;

    @BindView(R.id.search_text)
    EditText search_text;

    public InterCityFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InterCityFragment newInstance() {
        InterCityFragment fragment = new InterCityFragment();
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
        View view = inflater.inflate(R.layout.fragment_inter_city, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.search)
    public void setSearch() {
        int length = search_text.getText().length();

        Log.i("TAG", search_text.getText() + " " + length);
        SweetAlertDialog alertDialog;

        if (length == 0) {
            alertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(getString(R.string.search_hint));

            alertDialog.show();
        } else {
            alertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
            alertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            alertDialog.setTitleText(getString(R.string.loading));
            alertDialog.show();
        }
    }

}
