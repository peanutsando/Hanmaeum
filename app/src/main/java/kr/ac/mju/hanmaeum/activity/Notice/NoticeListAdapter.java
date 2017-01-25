
package kr.ac.mju.hanmaeum.activity.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import kr.ac.mju.hanmaeum.R;

/**
 * Created by user on 2017-01-14.
 */

public class NoticeListAdapter extends BaseAdapter {
    private ArrayList<NoticeItem> noticeItemList = new ArrayList<NoticeItem>();
    @BindView(R.id.item_number)
    TextView numberTextView;

    @BindView(R.id.item_noticeTitle)
    TextView titleTextView;

    @BindView(R.id.item_timestamp)
    TextView timestampTextView;

    public NoticeListAdapter() {}

    // Adapter에 사용되는 데이터의 개수를 리턴
    @Override
    public int getCount() {
        return noticeItemList.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴.
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        // "content_notice_item" 레이아웃을 inflate하여 view 참조 획득
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_notice_item, viewGroup, false);
        }

        // Data Set(noticeItemList)에서 position에 위치한 데이터 참조 획득
        NoticeItem noticeItem = noticeItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        numberTextView.setText(noticeItem.getNumber());
        titleTextView.setText(noticeItem.getTitle());
        timestampTextView.setText(noticeItem.getTimestamp());

        return view;
    }

    public ArrayList<NoticeItem> getNoticeItemList() {
        return noticeItemList;
    }
    // 지정한 위치에 있는 데이터와 관계된 아이템의 Index를 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 지정한 위치에 있는 데이터 리턴
    @Override
    public Object getItem(int position) {
        return noticeItemList.get(position);
    }

    // 데이터 추가를 위한 함수
    public void addItem(String number, String title, String timestamp, String url) {
        NoticeItem item = new NoticeItem();

        item.setNumber(number);
        item.setTitle(title);
        item.setTimestamp(timestamp);
        item.setUrl(url);

        noticeItemList.add(item);
    }
}
