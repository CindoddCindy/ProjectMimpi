package cindodcindy.sirihpinang.prayernote.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.controller.LoginUser;
import cindodcindy.sirihpinang.prayernote.controller.WriteDoa;
import cindodcindy.sirihpinang.prayernote.controller.auth.SaveSharedPreference;
import cindodcindy.sirihpinang.prayernote.model.DataPray;
import cindodcindy.sirihpinang.prayernote.model.DataPrayAnsw;
import cindodcindy.sirihpinang.prayernote.model.PojoAnsw;
import cindodcindy.sirihpinang.prayernote.model.PrayPojo;

public class ListDoa extends AppCompatActivity {

    private RecyclerView rvList;
    private FloatingActionButton fabAdd;


    private DataPray dataPray;
    private DataPrayAnsw dataPrayAnsw;
    private PrayListAdapter prayListAdapter;
    private List<PrayPojo> prayPojoArrayList = new ArrayList<>();
    private List<PojoAnsw> pojoAnswArrayList = new ArrayList<>();
    private SearchView searchView;
    private TextView textView_go_to_profile;
    private TextView textView_to_ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_doa);



        textView_go_to_profile=findViewById(R.id.ge_to_profile);
        textView_to_ans=findViewById(R.id.tv_go_to_answ);
        dataPray = new DataPray(this);
        dataPrayAnsw=new DataPrayAnsw(this);

        fabAdd=findViewById(R.id.btn_add);

        rvList = findViewById(R.id.rv_list);
        prayListAdapter = new PrayListAdapter(ListDoa.this,prayPojoArrayList);
        rvList.setAdapter(prayListAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListDoa.this);
        rvList.setLayoutManager(linearLayoutManager);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListDoa.this,WriteDoa.class);
                startActivity(intent);
                finish();

                 }
        });

        searchView=findViewById(R.id.sv_list_pr);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchPray(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchPray(newText);
                return false;
            }
        });

        textView_go_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListDoa.this,ExpandLayout.class);
                startActivity(intent);
                finish();
            }
        });

        textView_to_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListDoa.this,AnswPrayList.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void searchPray(String keyword) {
        DataPray dataPray = new DataPray(getApplicationContext());
        List<PrayPojo> prayPojos = dataPray.search(keyword);
        if (prayPojos != null) {
            rvList.setAdapter(new PrayListAdapter(getApplicationContext(), prayPojos));
        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        prayPojoArrayList = dataPray.getAll();
        prayListAdapter = new PrayListAdapter(this, prayPojoArrayList);
        rvList.setAdapter(prayListAdapter);
    }
}
