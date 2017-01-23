package kr.ac.mju.hanmaeum.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.object.subway.ArrivalInfo;

/**
 * Created by Youthink on 2017-01-22.
 */

public class SubwayArrivalAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ArrivalInfo> arrivalInfos;

    public SubwayArrivalAdapter(Context context, ArrayList<ArrivalInfo> infos) {
        this.context = context;
        arrivalInfos = infos;
    }

    @Override public int getCount() {
        return arrivalInfos.size();
    }

    @Override public Object getItem(int i) {
        return arrivalInfos.get(i);
    }

    @Override public long getItemId(int i) {
        return i;
    }

    @Override public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.subway_arrival_content, viewGroup, false);

            holder = new ViewHolder(view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ArrivalInfo arrival = arrivalInfos.get(i);

        String minute = String.valueOf(Integer.parseInt(arrival.getBarvlDt()) / 60);
        String up_down = context.getString(R.string.up_down_type) + arrival.getUpdnLine();
        String perMinute = minute + context.getString(R.string.perminute);
        String final_station = context.getString(R.string.final_station) + arrival.getBstatnNm();

        holder.arvCd.setText(getCodeToText(arrival.getArvlCd()));
        holder.barvIDt.setText(perMinute);

        holder.updnLine.setText(up_down);
        holder.trainLineNm.setText(arrival.getTrainLineNm());

        holder.arvMsg2.setText(arrival.getArvlMsg2());
        holder.arvMsg3.setText(arrival.getArvlMsg3());

        holder.bstatnNm.setText(final_station);

        return view;
    }

    private String getCodeToText(int code) {
        switch (code) {
            case 0:
                return context.getString(R.string.entry);
            case 1:
                return context.getString(R.string.arrival);
            case 2:
                return context.getString(R.string.leave);
            case 3:
                return context.getString(R.string.prev_leave);
            case 4:
                return context.getString(R.string.prev_entry);
            case 5:
                return context.getString(R.string.prev_arrival);
            default:
                return context.getString(R.string.running);
        }

    }

    class ViewHolder {
        @BindView(R.id.arvCd) TextView arvCd;
        @BindView(R.id.barvIDt) TextView barvIDt;
        @BindView(R.id.updnLine) TextView updnLine;
        @BindView(R.id.trainLineNm) TextView trainLineNm;
        @BindView(R.id.arvMsg2) TextView arvMsg2;
        @BindView(R.id.arvMsg3) TextView arvMsg3;
        @BindView(R.id.bstatnNm) TextView bstatnNm;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
