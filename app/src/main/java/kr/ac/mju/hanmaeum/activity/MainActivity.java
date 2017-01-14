package kr.ac.mju.hanmaeum.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.OnClick;
import kr.ac.mju.hanmaeum.R;
import kr.ac.mju.hanmaeum.activity.Notice.NoticeItem;
import kr.ac.mju.hanmaeum.activity.Notice.NoticeListAdapter;

public class MainActivity extends BaseActivity {
    private ListView noticeListview;
    private NoticeListAdapter noticeListAdapter = null;
    private ArrayList<NoticeItem> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setNavigationDrawer(savedInstanceState);

        // 리스트뷰 참조 및 Adapter 담기
        noticeListview = (ListView) findViewById(R.id.noticeListview);

        // Adapter 생성
        noticeListAdapter = new NoticeListAdapter();
        noticeListview.setAdapter(noticeListAdapter);


        // 첫 번째 아이템 추가
        noticeListAdapter.addItem("01", "제목없음", "17-01-02");
        noticeListAdapter.addItem("02", "제목있음", "18-02-02");
        noticeListAdapter.addItem("03", "제목였음", "19-03-02");
    }

}
