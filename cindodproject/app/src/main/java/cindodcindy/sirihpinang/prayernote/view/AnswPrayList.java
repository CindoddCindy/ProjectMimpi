package cindodcindy.sirihpinang.prayernote.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.model.DataPray;
import cindodcindy.sirihpinang.prayernote.model.DataPrayAnsw;
import cindodcindy.sirihpinang.prayernote.model.PojoAnsw;
import cindodcindy.sirihpinang.prayernote.model.PrayPojo;

public class AnswPrayList extends AppCompatActivity {
    private RecyclerView rvAnsw;


    private DataPrayAnsw dataPrayAnsw;
    private AdapterAnsw adapterAnsw;
    private List<PojoAnsw> pojoAnswArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answ_pray_list);

        dataPrayAnsw=new DataPrayAnsw(this);


        rvAnsw = findViewById(R.id.rv_answ);
        adapterAnsw = new AdapterAnsw(AnswPrayList.this,pojoAnswArrayList);
        rvAnsw.setAdapter(adapterAnsw);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AnswPrayList.this);
        rvAnsw.setLayoutManager(linearLayoutManager);

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
