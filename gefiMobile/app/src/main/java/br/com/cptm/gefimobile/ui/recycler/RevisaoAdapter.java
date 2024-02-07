package br.com.cptm.gefimobile.ui.recycler;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import br.com.cptm.gefimobile.R;
import br.com.cptm.gefimobile.models.Revisao;
import br.com.cptm.gefimobile.services.RetrofitConfig;
import br.com.cptm.gefimobile.services.RevisaoService;
import br.com.cptm.gefimobile.ui.holder.RevisaoHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RevisaoAdapter extends RecyclerView.Adapter<RevisaoHolder> {

    private final List<Revisao> revisoes;
    private final Context contexo;

    public RevisaoAdapter(List<Revisao> falhaDoEquipamentoList, Context context) {
        this.revisoes = falhaDoEquipamentoList;
        this.contexo = context;
    }

    @NotNull
    @Override
    public RevisaoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_revisao, parent, false);
        return new RevisaoHolder(view);
    }


    @Override
    public void onBindViewHolder(RevisaoHolder holder, int position) {
        holder.bind(revisoes.get(position));
        holder.btnValidaRevisao.setOnClickListener(v -> {



            AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
            builder1.setMessage("Validar");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Sim",
                    (dialog, id) -> {
                        Revisao revisao  =
                                revisoes.get(position);

                        RetrofitConfig retrofitInitializer = new RetrofitConfig();

                        RevisaoService revisaoService =
                                retrofitInitializer.getRevisaoService();

                        revisaoService.
                                atualizaRevisao(revisao).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                                Log.d("onResponse",response.message());
                                revisoes.remove(revisoes.get(position));
                                notifyItemRemoved(position);
                            }

                            @Override
                            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                                Log.d("onFailure",t.getMessage());
                            }
                        });


                        dialog.cancel();
                    });

            builder1.setNegativeButton(
                    "Não",
                    (dialog, id) -> dialog.cancel());

            AlertDialog alert11 = builder1.create();
            alert11.show();

        });
    }

    @Override
    public long getItemId(int position) {
        return revisoes.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return revisoes.size();
    }


}
