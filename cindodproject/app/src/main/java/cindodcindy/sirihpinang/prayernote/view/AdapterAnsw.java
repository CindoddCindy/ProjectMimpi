package cindodcindy.sirihpinang.prayernote.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cindodcindy.sirihpinang.prayernote.R;
import cindodcindy.sirihpinang.prayernote.controller.EditActivity;
import cindodcindy.sirihpinang.prayernote.controller.EditAnsw;
import cindodcindy.sirihpinang.prayernote.controller.WriteAnsw;
import cindodcindy.sirihpinang.prayernote.model.DataPray;
import cindodcindy.sirihpinang.prayernote.model.DataPrayAnsw;
import cindodcindy.sirihpinang.prayernote.model.PojoAnsw;

public class AdapterAnsw extends RecyclerView.Adapter<AdapterAnsw.ChildAdapterAnsw> {

    public Context context;
    public List<PojoAnsw> pojoAnswList;
    public DataPrayAnsw dataPrayAnsw;

    public AdapterAnsw(Context context, List<PojoAnsw>pojoAnswList){
        this.context=context;
        this.pojoAnswList=pojoAnswList;
    }

    @NonNull
    @Override
    public ChildAdapterAnsw onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.answ_list, parent, false);
        ChildAdapterAnsw childAdapterAnsw = new ChildAdapterAnsw(mView);

        return childAdapterAnsw;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChildAdapterAnsw holder, final int position) {
        final PojoAnsw pojoAnsw=pojoAnswList.get(position);


        holder.textView_date_pr.setText(pojoAnsw.getDate_fr_pray());
        holder.textView_pray_pr.setText(pojoAnsw.getPray_fr_pray());
        holder.textView_date_answ.setText(pojoAnsw.getDate_answ());
        holder.textView_pray_answ.setText(pojoAnsw.getAnsw_pray());

        holder.imageView_edit_answ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditAnsw.class);
                intent.putExtra("IDAnsw", pojoAnsw.getIdAnsw());
                context.startActivity(intent);




                /*
                Bundle bundle = new Bundle();
                bundle.putString("IdAnsw", String.valueOf(pojoAnsw.getIdAnsw()));
                bundle.putString("date_1", pojoAnsw.getDate_fr_pray());
                bundle.putString("pray_1",pojoAnsw.getPray_fr_pray());
                bundle.putString("date_answ",pojoAnsw.getDate_answ());
                bundle.putString("pray_answ",pojoAnsw.getAnsw_pray());
                Intent intent = new Intent(context, EditAnsw.class);
                intent.putExtras(bundle);
                context.startActivity(intent);

                 */


            }
        });

        holder.imageView_del_answ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final DataPrayAnsw dataPrayAnsw = new DataPrayAnsw(context);
                        dataPrayAnsw.deleteDataAnsw(pojoAnsw.getIdAnsw());
                        dialogInterface.dismiss();
                        pojoAnswList.remove(position);
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

        holder.cardView_ke_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ListDoa.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return pojoAnswList.size();
    }

    public class ChildAdapterAnsw extends RecyclerView.ViewHolder{

        public TextView textView_date_pr, textView_pray_pr, textView_date_answ, textView_pray_answ;
        public ImageView imageView_edit_answ, imageView_del_answ;
        public CardView cardView_list_pr;
        public  CardView cardView_ke_list;

        public ChildAdapterAnsw(@NonNull View itemView) {
            super(itemView);

            textView_date_pr=itemView.findViewById(R.id.tv_date_1);
            textView_pray_pr=itemView.findViewById(R.id.tv_pray_1);
            textView_date_answ=itemView.findViewById(R.id.tv_date_ans);
            textView_pray_answ=itemView.findViewById(R.id.tv_answ_pr);
            imageView_edit_answ=itemView.findViewById(R.id.iv_edit_answ);
            imageView_del_answ=itemView.findViewById(R.id.iv_delete_answ);
            cardView_list_pr=itemView.findViewById(R.id.cv_answ_pr);
            cardView_ke_list=itemView.findViewById(R.id.cv_go_to_list_1);
        }
    }
}
