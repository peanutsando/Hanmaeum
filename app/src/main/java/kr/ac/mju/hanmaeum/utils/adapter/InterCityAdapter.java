package kr.ac.mju.hanmaeum.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.object.InterCity.BusInfomation;

/**
 * Created by Youthink on 2017-02-05.
 */

public class InterCityAdapter extends BaseAdapter {

    private List<BusInfomation> bus;
    private Context context;

    public InterCityAdapter(Context context, List<BusInfomation> busInfomations) {
        this.context = context;
        this.bus = busInfomations;
    }

    @Override public int getCount() {
        return bus.size();
    }

    @Override public Object getItem(int i) {
        return bus.get(i);
    }

    @Override public long getItemId(int i) {
        return i;
    }

    @Override public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.intercity_content, viewGroup, false);

            viewHolder = new ViewHolder(view);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        BusInfomation info = bus.get(i);

        viewHolder.name.setText(info.getName());
        viewHolder.value.setText(info.getValue());

        return view;
    }

    class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.value)
        TextView value;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
