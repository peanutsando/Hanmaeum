package kr.ac.mju.hanmaeum.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.content.Intent;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.Constants;
import kr.ac.mju.hanmaeum.utils.adapter.TerminalAdapter;
import kr.ac.mju.hanmaeum.utils.object.terminal.Terminal;

public class TerminalFragment extends Fragment {

    @BindView(R.id.terminalList)
    GridView terminalList;

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

        setTerminalAdapter();
        return view;
    }

    private void setTerminalAdapter() {

        String[] name = getList();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity()
                , android.R.layout.simple_list_item_1, name);

        terminalList.setAdapter(adapter);

        terminalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setIcon(R.drawable.bus);
                alert.setTitle(getString(R.string.terminal_times));

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        getActivity(), android.R.layout.select_dialog_singlechoice
                );

                Log.i("TAG", getReturnArray(i)[0]);
                arrayAdapter.addAll(getReturnArray(i));

                alert.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://yongin.dongbubus.com/jsp/yongin/main/index.jsp"));
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton(
                        getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }
                );

                alert.show();
            }
        });
    }

    private String[] getList() {
        return new String[]{
                getResources().getString(R.string.seoul),
                getResources().getString(R.string.gwangju),
                getResources().getString(R.string.changwon),
                getResources().getString(R.string.busan),
                getResources().getString(R.string.westBusan),
                getResources().getString(R.string.ansan),
                getResources().getString(R.string.jeonju),
                getResources().getString(R.string.youngwolTaebak),
                getResources().getString(R.string.dongDaegu),
                getResources().getString(R.string.wonju),
                getResources().getString(R.string.gjpohang),
                getResources().getString(R.string.incheonYeoju),
                getResources().getString(R.string.chungjuJeomchon),
                getResources().getString(R.string.sokcho),
                getResources().getString(R.string.gwangmyeong),
                getResources().getString(R.string.gunsan),
                getResources().getString(R.string.jinju),
                getResources().getString(R.string.gimhae),
                getResources().getString(R.string.gimpoAir),
                getResources().getString(R.string.incheonAir),
                getResources().getString(R.string.incheon),
                getResources().getString(R.string.daejeon),
                getResources().getString(R.string.cheonan),
                getResources().getString(R.string.cheongju),
                getResources().getString(R.string.sejongYuseong),
                getResources().getString(R.string.gumiUlsan),
                getResources().getString(R.string.gangneung),
                getResources().getString(R.string.goyang)
        };
    }

    private String[] getReturnArray(int x) {
        switch (x) {
            case 0:
                return Constants.seoulIntercity;
            case 1:
                return Constants.gwangjuIntercity;
            case 2:
                return Constants.changwonIntercity;
            case 3:
                return Constants.busanIntercity;
            case 4:
                return Constants.westBusanIntercity;
            case 5:
                return Constants.ansanIntercity;
            case 6:
                return Constants.jeonjuIntercit;
            case 7:
                return Constants.youngwolTaebakIntercity;
            case 8:
                return Constants.dongDaeguIntercity;
            case 9:
                return Constants.wonjuIntercity;
            case 10:
                return Constants.gyeongjuPohangIntercity;
            case 11:
                return Constants.icheonYeojuIntercity;
            case 12:
                return Constants.chungjuJeomchonSangjuIntercity;
            case 13:
                return Constants.sokchoIntercity;
            case 14:
                return Constants.gwangmyeongIntercity;
            case 15:
                return Constants.gunsanIntercity;
            case 16:
                return Constants.jinjuIntercity;
            case 17:
                return Constants.gimhaeIntercity;
            case 18:
                return Constants.gimpoAirportIntercity;
            case 19:
                return Constants.incheonAirportIntercity;
            case 20:
                return Constants.incheonIntercity;
            case 21:
                return Constants.daejeonIntercity;
            case 22:
                return Constants.cheonanIntercity;
            case 23:
                return Constants.cheongjuIntercity;
            case 24:
                return Constants.sejongYuseongIntercity;
            case 25:
                return Constants.gumiUlsanIntercity;
            case 26:
                return Constants.gangneungIntercity;
            case 27:
                return Constants.goyangIntercity;
            default:
                return null;
        }
    }
}
