package cindodcindy.sirihpinang.prayernote.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.controller.WriteDoa;
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_doa);

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
