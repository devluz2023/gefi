package br.com.cptm.gefimobile.ui.holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import br.com.cptm.gefimobile.R;
import br.com.cptm.gefimobile.models.Revisao;

public class RevisaoHolder extends RecyclerView.ViewHolder  {

    private TextView textDescricao;
    private TextView textQuantidadeDeFalhasPorUsuario;
    public Button btnValidaRevisao;


    public RevisaoHolder(View itemView) {
        super(itemView);
        textDescricao = (TextView) itemView.findViewById(R.id.txt_controle_nome);
        textQuantidadeDeFalhasPorUsuario = (TextView) itemView.findViewById(R.id.txt_controle_quantidade);
        btnValidaRevisao = (Button)itemView.findViewById(R.id.btn_validar_revisao);


    }

    public void bind(Revisao revisao) {
        textDescricao.setText(revisao.getUsuario().getNome());
        textQuantidadeDeFalhasPorUsuario.setText(revisao.getEquipamento().getDescricao());
    }


}