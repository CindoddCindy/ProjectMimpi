package cindodcindy.sirihpinang.prayernote.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.controller.InsertPrayAnsw;
import cindodcindy.sirihpinang.prayernote.model.DataPray;
import cindodcindy.sirihpinang.prayernote.model.DataPrayAnsw;
import cindodcindy.sirihpinang.prayernote.model.PojoAnsw;
import cindodcindy.sirihpinang.prayernote.model.PrayPojo;

public class AnswPrayList extends AppCompatActivity {
    private RecyclerView rvAnsw;

    private TextView textView_doa_ls;
    private DataPrayAnsw dataPrayAnsw;
    private AdapterAnsw adapterAnsw;
    private List<PojoAnsw> pojoAnswArrayList = new ArrayList<>();
    private FloatingActionButton floatingActionButton_answ;
    private SearchView searchView_answ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answ_pray_list);

        dataPrayAnsw=new DataPrayAnsw(this);
        floatingActionButton_answ=findViewById(R.id.btn_add_answer);
        textView_doa_ls=findViewById(R.id.tv_ls_doa_dijawab_profile);

        textView_doa_ls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(AnswPrayList.this, ExpandLayout.class);
              startActivity(intent);
              finish();
            }


        });



        rvAnsw = findViewById(R.id.rv_answ);
        adapterAnsw = new AdapterAnsw(AnswPrayList.this,pojoAnswArrayList);
        rvAnsw.setAdapter(adapterAnsw);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AnswPrayList.this);
        rvAnsw.setLayoutManager(linearLayoutManager);

        floatingActionButton_answ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AnswPrayList.this, InsertPrayAnsw.class);
                startActivity(intent);
            }
        });

        searchView_answ=findViewById(R.id.sv_answ);

        searchView_answ.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAnswPr(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAnswPr(newText);
                return false;
            }
        });

    }

    private void searchAnswPr(String keyword) {
        DataPrayAnsw dataPrayAnsw = new DataPrayAnsw(getApplicationContext());
        List<PojoAnsw> contacts = dataPrayAnsw.searchAns(keyword);
        if (contacts != null) {
            rvAnsw.setAdapter(new AdapterAnsw(getApplicationContext(), contacts));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        pojoAnswArrayList = dataPrayAnsw.getAllAnsw();
        //pojoAnswArrayList= dataPrayAnsw.getAllAnsw();
        adapterAnsw = new AdapterAnsw(this,pojoAnswArrayList);
        rvAnsw.setAdapter(adapterAnsw);
    }
}
