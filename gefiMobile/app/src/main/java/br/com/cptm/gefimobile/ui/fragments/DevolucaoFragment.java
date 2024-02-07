package br.com.cptm.gefimobile.ui.fragments;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import br.com.cptm.gefimobile.R;
import br.com.cptm.gefimobile.models.Controle;
import br.com.cptm.gefimobile.models.Status;
import br.com.cptm.gefimobile.services.RetrofitConfig;
import br.com.cptm.gefimobile.ui.recycler.DevolucaoAdapter;
import br.com.cptm.gefimobile.util.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DevolucaoFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Controle> controleList
            = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler_view_devolucao, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_devolucao);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        SessionManager sessionManager = new SessionManager(getContext());


        RetrofitConfig retrofitInitializer = new RetrofitConfig();

        Call<List<Controle>> callEquipamentos = retrofitInitializer
                .getControleService().getControlesByUsuario(Status.INDISPONIVEL,
                        sessionManager.getLoginResponse().getId());

        callEquipamentos.enqueue(new Callback<List<Controle>>() {

            @Override
            public void onResponse(Call<List<Controle>> call, Response<List<Controle>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    Log.d("onResponseDevolucao",  response.body().toString());
                    controleList = response.body();
                    mAdapter = new DevolucaoAdapter(controleList, getActivity());
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Controle>> call, Throwable t) {
                Log.d("onFailureDevolucao",  t.getMessage());
            }
        });

        return view;

    }



}
