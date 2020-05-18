package cindodcindy.sirihpinang.prayernote.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.controller.WriteAnsw;
import cindodcindy.sirihpinang.prayernote.controller.WriteDoa;
import cindodcindy.sirihpinang.prayernote.model.PojoAnsw;
import cindodcindy.sirihpinang.prayernote.model.PrayPojo;

public class PrayListAdapter extends RecyclerView.Adapter<PrayListAdapter.PrayAdapterChild> {

    private Context context;
    private List<PrayPojo> prayPojoList;
    private  List<PojoAnsw> pojoAnswsList;


    public PrayListAdapter(Context context, List<PrayPojo> prayPojoList,List<PojoAnsw> pojoAnswsList) {
        this.context = context;
        this.prayPojoList=prayPojoList;
        this.pojoAnswsList=pojoAnswsList;

    }







    @NonNull
    @Override
    public PrayAdapterChild onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter, parent, false);
        PrayAdapterChild prayAdapterChild = new PrayAdapterChild(mView);


        return prayAdapterChild;

    }

    @Override
    public void onBindViewHolder(@NonNull final PrayAdapterChild holder, int position) {
        final PrayPojo prayPojo = prayPojoList.get(position);
        final PojoAnsw pojoAnsw= pojoAnswsList.get(position);


        holder.textView_date.setText(prayPojo.getDate());
        holder.textView_pray.setText(prayPojo.getPray());

        holder.textView_date_answ.setText(pojoAnsw.getDate_answ());
        holder.textView_pray_answ.setText(pojoAnsw.getPray_answ());

        holder.cardView_answ_pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("date_pray_answ", pojoAnsw.getDate_answ());
                bundle.putString("pray_ans",pojoAnsw.getPray_answ());
                Intent intent = new Intent(context, WriteAnsw.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return prayPojoList.size();
    }

    public class PrayAdapterChild extends RecyclerView.ViewHolder{

        public TextView textView_date, textView_pray;
        public ImageView imageView_edit, imageView_delete;
        public CardView cardView_pray;
        public View contentLayout;
        private TextView txtContent;
        private CardView cardView_answ_pr;
        public TextView textView_date_answ, textView_pray_answ;



        public PrayAdapterChild(@NonNull View itemView) {
            super(itemView);
            textView_date=itemView.findViewById(R.id.tv_pray_date);
            textView_pray=itemView.findViewById(R.id.tv_pray);
            imageView_edit=itemView.findViewById(R.id.iv_edit_pray);
            imageView_delete=itemView.findViewById(R.id.iv_delete_pray);
            cardView_pray=itemView.findViewById(R.id.cv_pray_content);
            txtContent=itemView.findViewById(R.id.tv_prayer_answered_click);
            cardView_answ_pr=itemView.findViewById(R.id.cv_answ_pr);

            textView_date_answ=itemView.findViewById(R.id.tv_date_ans);
            textView_pray_answ=itemView.findViewById(R.id.tv_answ_pr);




        }
    }
}
