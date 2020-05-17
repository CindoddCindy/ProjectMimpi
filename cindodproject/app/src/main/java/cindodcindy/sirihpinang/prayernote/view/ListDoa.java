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
import cindodcindy.sirihpinang.prayernote.model.PrayPojo;

public class ListDoa extends AppCompatActivity {

    private RecyclerView rvList;
    private FloatingActionButton fabAdd;

    private DataPray dataPray;
    private PrayListAdapter prayListAdapter;
    private List<PrayPojo> prayPojoArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_doa);

        dataPray = new DataPray(this);

        rvList = findViewById(R.id.rv_list);

        prayListAdapter = new PrayListAdapter(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(prayListAdapter);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListDoa.this, WriteDoa.class));
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        getNotes();
    }

    private void getNotes() {
        prayPojoArrayList= dataPray.getPray();//.getNotes();
        prayListAdapter.setNotes(prayPojoArrayList);

        if (prayPojoArrayList.size() != 0) {
            rvList.setVisibility(View.VISIBLE);
        } else {
            rvList.setVisibility(View.GONE);
        }
    }
}
