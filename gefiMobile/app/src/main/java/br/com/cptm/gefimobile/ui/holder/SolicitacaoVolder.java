package br.com.cptm.gefimobile.ui.holder;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import br.com.cptm.gefimobile.R;
import br.com.cptm.gefimobile.models.Controle;
import br.com.cptm.gefimobile.models.Equipamento;

public class SolicitacaoVolder extends RecyclerView.ViewHolder {


    private TextView textDescricao;
    private TextView textModelo;
    private TextView textCodigoCPTM;
    private TextView textFabricante;
    public Button btnSolicitarControle;

    public SolicitacaoVolder(View itemView) {
        super(itemView);
        textDescricao = (TextView) itemView.findViewById(R.id.txt_solicitacao_descricao);
        textModelo = (TextView) itemView.findViewById(R.id.txt_solicitacao_modelo);
        textCodigoCPTM = (TextView) itemView.findViewById(R.id.txt_solicitacao_codigoCPTM);
        textFabricante = (TextView) itemView.findViewById(R.id.txt_solicitacao_fabricante);
        btnSolicitarControle = (Button) itemView.findViewById(R.id.btn_solicitar_controle);
    }

    public void bind(Controle controle) {
        Equipamento equipamento = controle.getEquipamento();
        textDescricao.setText(equipamento.getDescricao());
        textModelo.setText(equipamento.getModelo());
        textCodigoCPTM.setText(equipamento.getCodigoCPTM());
        textFabricante.setText(equipamento.getFabricante());
  
    }

}
