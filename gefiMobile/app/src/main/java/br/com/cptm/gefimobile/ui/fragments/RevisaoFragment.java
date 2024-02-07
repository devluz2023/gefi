package br.com.cptm.gefimobile.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.cptm.gefimobile.R;
import br.com.cptm.gefimobile.models.Revisao;
import br.com.cptm.gefimobile.models.Roles;
import br.com.cptm.gefimobile.models.Status;
import br.com.cptm.gefimobile.services.RetrofitConfig;
import br.com.cptm.gefimobile.ui.recycler.RevisaoAdapter;
import br.com.cptm.gefimobile.util.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RevisaoFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Revisao> revisoes
            = new ArrayList<>();


    public RevisaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_revisao, container, false);

        recyclerView = view.findViewById(R.id.recycler_revisao);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)

        SessionManager sessionManager = new SessionManager(getContext());

        RetrofitConfig retrofitInitializer = new RetrofitConfig();

        Call<List<Revisao>> callFalhaDoEquipamentos;

        if(sessionManager.getLoginResponse().getPrivilegio().equals(Roles.USER)){
            callFalhaDoEquipamentos = retrofitInitializer.getRevisaoService()
                    .getRevisaoByUsuario(Status.INDISPONIVEL,
                            sessionManager.getLoginResponse().getId());

        }else{
            callFalhaDoEquipamentos = retrofitInitializer.getRevisaoService()
                    .getRevisaoByDepartamento(Status.INDISPONIVEL,
                            sessionManager.getLoginResponse().getDepartamento());

        }


        callFalhaDoEquipamentos.enqueue(new Callback<List<Revisao>>() {

            @Override
            public void onResponse(Call<List<Revisao>> call, Response<List<Revisao>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    revisoes = response.body();
                    Log.d("onResponseRevisao", revisoes.toString());
                    mAdapter = new RevisaoAdapter(revisoes, getContext());
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Revisao>> call, Throwable t) {
                Log.d("onFailureRevisao", t.getMessage());
            }
        });


        return view;
    }



}