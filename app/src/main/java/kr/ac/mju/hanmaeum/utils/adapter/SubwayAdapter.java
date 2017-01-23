package kr.ac.mju.hanmaeum.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.object.subway.Subway;

/**
 * Created by Youthink on 2017-01-22.
 */

public class SubwayAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Subway> subways;

    public SubwayAdapter(Context context, ArrayList<Subway> list) {
        this.context = context;
        subways = list;
    }

    @Override public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.subway_content, viewGroup, false);

            holder = new ViewHolder(view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Subway subway = subways.get(i);

        Glide.with(context)
                .load(subway.getSubway_images())
                .into(holder.subway_images);
        holder.subway_name.setText(subway.getSTATION_NM());
//        holder.subway_count.setText(subway.getLINE_NUM());

        return view;
    }

    @Override public long getItemId(int i) {
        return i;
    }

    @Override public Object getItem(int i) {
        return subways.get(i);
    }

    @Override public int getCount() {
        return subways.size();
    }

    class ViewHolder {
        @BindView(R.id.subway_images) ImageView subway_images;
//        @BindView(R.id.subway_count) TextView subway_count;
        @BindView(R.id.subway_name) TextView subway_name;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
