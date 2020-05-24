package cindodcindy.sirihpinang.prayernote.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.model.DataPray;
import cindodcindy.sirihpinang.prayernote.model.DataPrayAnsw;
import cindodcindy.sirihpinang.prayernote.model.PojoAnsw;
import cindodcindy.sirihpinang.prayernote.view.AnswPrayList;

public class EditAnsw extends AppCompatActivity {

    private TextView textView_edit_date_answ, textView_save_edit_answ, textView_date_pr, textView_pr_pr;
    private EditText editText_edit_pray_answ;
    private Calendar calendar;
    private PojoAnsw pojoAnsw;
    private DataPrayAnsw dataPrayAnsw;
    private TextView textView_batal_edit_answ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_answ);
        textView_edit_date_answ=findViewById(R.id.write_date_answ_edit);
        editText_edit_pray_answ=findViewById(R.id.write_pray_answ_edit);
        textView_save_edit_answ=findViewById(R.id.save_pray_answ_edit);

        textView_date_pr=findViewById(R.id.edit_date_1);
        textView_pr_pr=findViewById(R.id.edit_pray_2);
        textView_batal_edit_answ=findViewById(R.id.tv_batal_pray_answ_edit);

        dataPrayAnsw = new DataPrayAnsw(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            pojoAnsw = dataPrayAnsw.getDataAnsw(bundle.getInt("IDAnsw"));
            textView_date_pr.setText(pojoAnsw.getDate_fr_pray());
            textView_pr_pr.setText(pojoAnsw.getPray_fr_pray());
            textView_edit_date_answ.setText(pojoAnsw.getDate_answ());
            editText_edit_pray_answ.setText(pojoAnsw.getAnsw_pray());

        }

 /*

        if(getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            pojoAnsw=dataPrayAnsw.getDataAnsw(bundle.getInt("IdAnsw"));
            textView_date_pr.setText(bundle.getString("date_1"));
            textView_pr_pr.setText(bundle.getString("pray_1"));
            textView_edit_date_answ.setText(bundle.getString("date_answ"));
            editText_edit_pray_answ.setText(bundle.getString("pray_answ"));
        }

 */


        calendar = Calendar.getInstance();

        textView_edit_date_answ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditAnsw.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "dd-MM-yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        textView_edit_date_answ.setText(sdf.format(calendar.getTime()));
                    }
                },
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });



        textView_save_edit_answ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String tanggal =textView_date_pr.getText().toString();
                    String doa = textView_pr_pr.getText().toString();
                    String tanggal_2=textView_edit_date_answ.getText().toString();
                    String doa_2=editText_edit_pray_answ.getText().toString();
                    if (TextUtils.isEmpty(tanggal)){
                        textView_date_pr.setError("tanggal Kosong");
                    }
                    else if(TextUtils.isEmpty(doa)){
                        textView_pr_pr.setError("Doa Kosong");
                    }else if(TextUtils.isEmpty(tanggal_2)){
                        textView_edit_date_answ.setError("Tanggal Doa Dijawab Kosong");
                    }else  if(TextUtils.isEmpty(doa_2)){
                        editText_edit_pray_answ.setError("Doa Dijawab Kosong");
                    }else {
                        dataPrayAnsw.updateDataAnsw(pojoAnsw.getIdAnsw(), textView_date_pr.getText().toString(), textView_pr_pr.getText().toString(), textView_edit_date_answ.getText().toString(), editText_edit_pray_answ.getText().toString());
                        finish();
                    }





            }
        });

        textView_batal_edit_answ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditAnsw.this, AnswPrayList.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
