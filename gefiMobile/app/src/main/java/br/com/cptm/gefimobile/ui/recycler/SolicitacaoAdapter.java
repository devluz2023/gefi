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
import br.com.cptm.gefimobile.ui.holder.SolicitacaoVolder;
import br.com.cptm.gefimobile.util.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SolicitacaoAdapter extends RecyclerView.Adapter<SolicitacaoVolder> {

    private final Context contexto;
    private List<Controle> controles;

    public SolicitacaoAdapter(List<Controle> controles, Context contexto) {
        this.controles =controles;
        this.contexto = contexto;

    }

    @Override
    public SolicitacaoVolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solicitacao,
                parent, false);
        return new SolicitacaoVolder(view);
    }


    @Override
    public void onBindViewHolder(SolicitacaoVolder holder, int position) {
        holder.bind(controles.get(position));

        holder.btnSolicitarControle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                builder1.setMessage("Realizar Solicitação do Equipamento?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Sim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Controle controle = controles.get(position);
                                RetrofitConfig retrofitInitializer = new RetrofitConfig();
                                Usuario usuario = new Usuario();
                                usuario.setId(new SessionManager(v.getContext()).getLoginResponse().getId());
                                controle.setUsuario(usuario);
                                ControleService  controleService = retrofitInitializer.getControleService();

                                controleService.adicionaControle(controle)
                                        .enqueue(new Callback<Controle>() {
                                            @Override
                                            public void onResponse(Call<Controle> call, Response<Controle> response) {
                                                controles.remove(controles.get(position));
                                                notifyItemRemoved(position);

                                                Log.d("onResponse",response.message());
                                            }

                                            @Override
                                            public void onFailure(Call<Controle> call, Throwable t) {
                                                Log.d("onFailure",t.getMessage());
                                            }
                                        });

                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Não",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
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
