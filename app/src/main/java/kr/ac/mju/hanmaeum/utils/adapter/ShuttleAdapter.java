package kr.ac.mju.hanmaeum.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.utils.Shuttle;

/**
 * Created by Youthink on 2017-01-20.
 */

public class ShuttleAdapter extends BaseAdapter {

    private ArrayList<Shuttle> shuttles;
    private Context context;

    // 어떤페이지에서 가져왔는지, 어떤 자료를 가져왔는지
    public ShuttleAdapter(Context context, ArrayList<Shuttle> shuttles) {
        this.shuttles = shuttles;
        this.context = context;
    }

    // 리스트뷰(어레이리스트)의 갯수
    @Override public int getCount() {
        return shuttles.size();
    }

    // 해당되는 오브젝트의 값
    @Override public Object getItem(int i) {
        return shuttles.get(i);
    }

    // 해당되는 아이템의 번호
    @Override public long getItemId(int i) {
        return i;
    }

    @Override public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.shuttle_content, viewGroup, false);
            holder = new ViewHolder(view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Shuttle shuttle = shuttles.get(i);

        holder.shuttle_number.setText(shuttle.getNo());
        holder.shuttle_type.setText(shuttle.getType());
        holder.shuttle_start_time.setText(shuttle.getStart_time());
        holder.shuttle_ramp_time.setText(shuttle.getRamp_time());

        return view;
    }

    class ViewHolder {
        @BindView(R.id.shuttle_number) TextView shuttle_number;
        @BindView(R.id.shuttle_type) TextView shuttle_type;
        @BindView(R.id.shuttle_start_time) TextView shuttle_start_time;
        @BindView(R.id.shuttle_ramp_time) TextView shuttle_ramp_time;
        @BindView(R.id.bookmark) ImageView bookmark;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
