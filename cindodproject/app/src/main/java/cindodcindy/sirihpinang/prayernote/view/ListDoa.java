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
    private TextView textView_user_name, textView_passowrd,textView_logout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_doa);

        textView_user_name=findViewById(R.id.tv_user_name_list_doa);
        textView_passowrd=findViewById(R.id.tv_pass_list_doa);
        textView_logout=findViewById(R.id.tv_logout);



        if(getIntent().getExtras()!=null){
            /**
             * Jika Bundle ada, ambil data dari Bundle
             */
            Bundle bundle = getIntent().getExtras();
            textView_user_name.setText(bundle.getString("data1"));
            textView_passowrd.setText(bundle.getString("data2"));
        }



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

    }

    private void searchPray(String keyword) {
        DataPray dataPray = new DataPray(getApplicationContext());
        List<PrayPojo> prayPojos = dataPray.search(keyword);
        if (prayPojos != null) {
            rvList.setAdapter(new PrayListAdapter(getApplicationContext(), prayPojos));
        }

        textView_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveSharedPreference.setLoggedIn(getApplicationContext(), false);
                Intent intent = new Intent(getApplicationContext(), LoginUser.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        prayPojoArrayList = dataPray.getAll();
        //pojoAnswArrayList= dataPrayAnsw.getAllAnsw();
        prayListAdapter = new PrayListAdapter(this, prayPojoArrayList);
        rvList.setAdapter(prayListAdapter);
    }
}
