package com.suek.ex21listview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 1)
    ListView listView;
    // 3)
    ArrayAdapter adapter;    // 오로지 String 만을 위한 adaptor 임*****

    // 2) 대량의 데이터들
    ArrayList<String> datas= new ArrayList<>();

    // 6)
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2) 리스트뷰에서 보여줄 대량의 데이터항목 추가 (문자열, String 객체 3개 생성)
        /*datas.add(new String("데이터 1"));   //스트링 객체생성
        datas.add(new String("데이터 2"));
        datas.add("데이터 3");    // new String 생략가능*/

        // 1) id 찾기
        listView= findViewById(R.id.listview);
        // 4) 리스트뷰에 보여질 뷰들을 만들어주는 객체생성
        adapter= new ArrayAdapter(this, R.layout.list_item, datas);         //crateFromResource 를 쓸때는 대량의 데이터가 오직 xml 문서에 있을때만 가능
        // 4.1) 리스트뷰에 아답터 설정
        listView.setAdapter(adapter);

        // 4번이 끝났으면 activity_main.xml 뷰들작성



        // 9) 리스트뷰의 아이템이 비어있을때 보여지는 뷰를 설정
        TextView tv= findViewById(R.id.tv_empty);
        listView.setEmptyView(tv);


        // 8) 리스트뷰에 아이템 롱클릭 리스너 추가로 클릭한 뷰의 데이터 삭제
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // 8.1) 클릭한 뷰의 데이터 삭제
                datas.remove(position);   //position. 아무 데이터를 눌러도 삭제됨
                adapter.notifyDataSetChanged();

                return true;   // return true 잊지말고!
            }
        });

        // 6
        et= findViewById(R.id.et);

    }

    // 5) 버튼추가 발동되는 메소드
    public void clickBtn(View view) {
        //  6)  EditText 에 써있는 글씨 얻어오기--> 참조변수 만들어주기
        String s= et.getText().toString();

        //  6.1) 얻어온 글씨를 대량의 데이터(ArrayList datas)에 추가
        datas.add(s);

        // 6.2) 아답터에게 새로운 데이터가 있으니 리스트뷰를 갱신하도록 요청
        adapter.notifyDataSetChanged();      //데이터가 바뀌었다고 공지함

        // 6.3) EditText 의 글씨를 비우기
        et.setText("");

        // 7) 리스트뷰의 스크롤 위치 지정
        listView.setSelection(datas.size()-1);  //length 는 배열일때 size 는 arraylist 일때
    }

}
