package cindodcindy.sirihpinang.prayernote.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.controller.EditActivity;
import cindodcindy.sirihpinang.prayernote.controller.EditAnsw;
import cindodcindy.sirihpinang.prayernote.controller.WriteAnsw;
import cindodcindy.sirihpinang.prayernote.controller.WriteDoa;
import cindodcindy.sirihpinang.prayernote.model.DataPray;
import cindodcindy.sirihpinang.prayernote.model.DataPrayAnsw;
import cindodcindy.sirihpinang.prayernote.model.PojoAnsw;
import cindodcindy.sirihpinang.prayernote.model.PrayPojo;

public class PrayListAdapter extends RecyclerView.Adapter<PrayListAdapter.PrayAdapterChild> {

    private Context context;
    private List<PrayPojo> prayPojoList;
    private DataPray dataPray;


    public PrayListAdapter(Context context, List<PrayPojo> prayPojoList) {
        this.context = context;
        this.prayPojoList=prayPojoList;

    }


    @NonNull
    @Override
    public PrayAdapterChild onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter, parent, false);
        PrayAdapterChild prayAdapterChild = new PrayAdapterChild(mView);


        return prayAdapterChild;

    }

    @Override
    public void onBindViewHolder(@NonNull final PrayAdapterChild holder, final int position) {
        final PrayPojo prayPojo = prayPojoList.get(position);


        holder.textView_date.setText(prayPojo.getDate());
        holder.textView_pray.setText(prayPojo.getPray());


        holder.textView_write_answ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("date_pray", prayPojo.getDate());
                bundle.putString("pray",prayPojo.getPray());
                Intent intent = new Intent(context, WriteAnsw.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.cardView_see_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AnswPrayList.class);
                context.startActivity(intent);
            }
        });

        holder.imageView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final DataPray dataPray = new DataPray(context);
                        dataPray.deleteData(prayPojo.getPrayId());
                        dialogInterface.dismiss();
                        prayPojoList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();

            }
        });




        holder.imageView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("ID", prayPojo.getPrayId());
                context.startActivity(intent);


            }
        });


    }


// nambah search




    @Override
    public int getItemCount() {
        return prayPojoList.size();
    }

    public class PrayAdapterChild extends RecyclerView.ViewHolder{

        public TextView textView_date, textView_pray, textView_write_answ;
        public ImageView imageView_edit, imageView_delete;
        public CardView cardView_see_ans;


        public PrayAdapterChild(@NonNull View itemView) {
            super(itemView);
            textView_date=itemView.findViewById(R.id.tv_pray_date);
            textView_pray=itemView.findViewById(R.id.tv_pray);
            imageView_edit=itemView.findViewById(R.id.iv_edit_pray);
            imageView_delete=itemView.findViewById(R.id.iv_delete_pray);
            textView_write_answ=itemView.findViewById(R.id.tv_prayer_answered_click);
            cardView_see_ans=itemView.findViewById(R.id.cv_see_list_answ);

        }
    }
}
