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
import kr.ac.mju.hanmaeum.utils.object.terminal.Terminal;

/**
 * Created by Youthink on 2017-02-07.
 */

public class TerminalAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Terminal> array;

    public TerminalAdapter(Context context, ArrayList<Terminal> array) {
        this.context = context;
        this.array = array;
    }

    @Override public int getCount() {
        return array.size();
    }

    @Override public Object getItem(int i) {
        return array.get(i);
    }

    @Override public long getItemId(int i) {
        return i;
    }

    @Override public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.terminal_content, viewGroup, false);

            viewHolder = new ViewHolder(view);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Terminal t = array.get(i);
        viewHolder.toLocation.setText(t.getToLocation());
        viewHolder.toTime.setText(t.getToTime());

        return view;
    }

    class ViewHolder {
        @BindView(R.id.toLocation) TextView toLocation;
        @BindView(R.id.toTime) TextView toTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
