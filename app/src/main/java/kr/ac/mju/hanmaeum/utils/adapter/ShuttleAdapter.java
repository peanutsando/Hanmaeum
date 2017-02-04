package kr.ac.mju.hanmaeum.utils.adapter;

import android.util.Log;
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
import kr.ac.mju.hanmaeum.utils.object.shuttle.Shuttle;
import kr.ac.mju.hanmaeum.utils.service.database.BookmarkDatabase;

/**
 * Created by Youthink on 2017-01-20.
 */

public class ShuttleAdapter extends BaseAdapter {

    private ArrayList<Shuttle> shuttles;
    private ArrayList<Shuttle> bookmark;
    private Context context;
    private BookmarkDatabase database;

    // 어떤페이지에서 가져왔는지, 어떤 자료를 가져왔는지
    public ShuttleAdapter(Context context, ArrayList<Shuttle> shuttles, ArrayList<Shuttle> bookmark) {
        this.shuttles = shuttles;
        this.context = context;
        this.bookmark = bookmark;

        database = new BookmarkDatabase();
    }

    // 리스트뷰(어레이리스트)의 갯수
    @Override
    public int getCount() {
        return shuttles.size();
    }

    // 해당되는 오브젝트의 값
    @Override
    public Object getItem(int i) {
        return shuttles.get(i);
    }

    // 해당되는 아이템의 번호
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.shuttle_content, viewGroup, false);
            holder = new ViewHolder(view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final Shuttle shuttle = shuttles.get(i);
        final Shuttle book = bookmark.get(i);

        holder.shuttle_number.setText(shuttle.getNo());
        holder.shuttle_type.setText(shuttle.getType());
        holder.shuttle_start_time.setText(shuttle.getStart_time());
        holder.shuttle_ramp_time.setText(shuttle.getRamp_time());

        Log.i("TAG", book.isBookmark() + "");

        if (book.isBookmark()) {
            holder.bookmark.setImageResource(R.drawable.ic_filled_favorite);
        } else {
            holder.bookmark.setImageResource(R.drawable.ic_empty_favorite);
        }

        holder.bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if existing data exists, there is a value in the Database
                if (book.isBookmark()) {
                    Log.i("TAG", "이제 클릭해제");
                    database.setBookmarkCheck(context, shuttle.getNo(), false);
                    holder.bookmark.setImageResource(R.drawable.ic_empty_favorite);
                } else {
                    Log.i("TAG", "이제 클릭한거");
                    database.setBookmarkCheck(context, shuttle.getNo(), true);
                    holder.bookmark.setImageResource(R.drawable.ic_filled_favorite);
                }
            }
        });

        return view;
    }

    class ViewHolder {
        @BindView(R.id.shuttle_number)
        TextView shuttle_number;
        @BindView(R.id.shuttle_type)
        TextView shuttle_type;
        @BindView(R.id.shuttle_start_time)
        TextView shuttle_start_time;
        @BindView(R.id.shuttle_ramp_time)
        TextView shuttle_ramp_time;
        @BindView(R.id.bookmark)
        ImageView bookmark;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
