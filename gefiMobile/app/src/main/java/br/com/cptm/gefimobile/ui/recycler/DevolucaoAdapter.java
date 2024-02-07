package br.com.cptm.gefimobile.ui.recycler;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.cptm.gefimobile.R;
import br.com.cptm.gefimobile.models.Controle;
import br.com.cptm.gefimobile.models.Usuario;
import br.com.cptm.gefimobile.services.ControleService;
import br.com.cptm.gefimobile.services.RetrofitConfig;
import br.com.cptm.gefimobile.ui.holder.DevolucaoHolder;
import br.com.cptm.gefimobile.util.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Criado por Fabio
 */

public class DevolucaoAdapter extends RecyclerView.Adapter<DevolucaoHolder> {

    private final Context contexto;
    private List<Controle> controles;

    public DevolucaoAdapter(List<Controle> controles, Context contexto) {
        this.controles = controles;
        this.contexto = contexto;
    }

    @Override
    public DevolucaoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_devolucao,
                parent, false);
        return new DevolucaoHolder(view);
    }

    public void updateData(List<Controle> controles) {
        controles.clear();
        controles.addAll(controles);
        notifyDataSetChanged();
    }
    public void addItem(int position, Controle controle) {
        controles.add(position, controle);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        controles.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public void onBindViewHolder(DevolucaoHolder holder, int position) {
        holder.bind(controles.get(position));

        holder.btnDevolverControle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder devolucaoBuilder = new AlertDialog.Builder(contexto);
                devolucaoBuilder.setMessage("Devolver o equipamento Equipamento?");
                devolucaoBuilder.setCancelable(true);

                devolucaoBuilder.setPositiveButton(
                        "Sim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Controle controle = controles.get(position);
                                RetrofitConfig retrofitInitializer = new RetrofitConfig();
                                Usuario usuario = new Usuario();
                                usuario.setId(new SessionManager(v.getContext()).getLoginResponse().getId());
                                controle.setUsuario(usuario);

                                ControleService controleService =
                                        retrofitInitializer.getControleService();

                                controleService.atualizaControle(controle)
                                .enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {

                                        Log.d("onResponse",response.message());
                                        controles.remove(controles.get(position));
                                        notifyItemRemoved(position);




                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Log.d("onFailure",t.getMessage());
                                    }
                                });



                                dialog.cancel();
                            }
                        });

                devolucaoBuilder.setNegativeButton(
                        "Não",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = devolucaoBuilder.create();
                alert11.show();

            }

        });
    }

    @Override
    public long getItemId(int position) {
        return controles.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return controles.size();
    }





}
