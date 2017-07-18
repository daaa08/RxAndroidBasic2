package com.example.da08.rxandroidbasic2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class MainActivity extends AppCompatActivity{

    private TextView textView;


    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initObservable();   // 호출
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.textView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new CustomAdapter(datas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    Observable<String> forFrom;
    private void initObservable(){    // 초기화
        String fromData[] = {"aa","bb","cc","dd","ee","ff"};
        forFrom = Observable.fromArray(fromData);   // 발행할 준비
    }

    // xml 에서 바인드함
    public void btnJust(View v){

    }

    public void btnFrom(View v){
        forFrom.subscribe(
                str -> datas.add(str),                  // 옵저버블(발행자 : emitter)로부터 데이터를 가져옴
                t   -> {/*일단 아무것도 안함 */},
                ()  -> adapter.notifyDataSetChanged()   // 완료되면 리스트에 알림
        );
    }

    public void btnDefer(View v){

    }
}

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {
    List<String> datas;

    public CustomAdapter(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.textView.setText(datas.get(position));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.textView);
        }
    }
}