package br.com.cptm.gefimobile.ui.holder;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import br.com.cptm.gefimobile.R;
import br.com.cptm.gefimobile.models.Controle;
import br.com.cptm.gefimobile.models.Equipamento;

public class DevolucaoHolder extends RecyclerView.ViewHolder {

    private TextView textDescricao;
    private final TextView textModelo;
    private final TextView textCodigoCPTM;
    private final TextView textFabricante;
    private final TextView textNumeroRequisicao;
    public Button btnDevolverControle;

    public DevolucaoHolder(View itemView) {
        super(itemView);
        textDescricao = itemView.findViewById(R.id.txt_ferramenta_descricao);
        textDescricao = itemView.findViewById(R.id.txt_ferramenta_descricao);
        textModelo = itemView.findViewById(R.id.txt_ferramenta_modelo);
        textCodigoCPTM = itemView.findViewById(R.id.txt_ferramenta_codigoCPTM);
        textFabricante = itemView.findViewById(R.id.txt_ferramenta_fabricante);
        btnDevolverControle = itemView.findViewById(R.id.btn_devolver_controle);
        textNumeroRequisicao = itemView.findViewById(R.id.txt_ferramenta_numero_requisicao);
    }

    public void bind(Controle controle) {
        Equipamento equipamento = controle.getEquipamento();
        textNumeroRequisicao.setText("REF: " + controle.getId());
        textDescricao.setText(equipamento.getDescricao());
        textModelo.setText(equipamento.getModelo());
        textCodigoCPTM.setText(equipamento.getCodigoCPTM());
        textFabricante.setText(equipamento.getFabricante());

    }

}
