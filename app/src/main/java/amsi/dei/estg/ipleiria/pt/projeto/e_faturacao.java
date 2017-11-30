package amsi.dei.estg.ipleiria.pt.projeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class e_faturacao extends AppCompatActivity {
    private String getArray_spinnerPagamentos[];
    private String array_spinnerIva[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_faturacao);

        /*----------PRENCHER O SPINNER DE TIPO DE PAGAMENTOS---------*/
        getArray_spinnerPagamentos=new String[3];
        getArray_spinnerPagamentos[0]="Dinheiro";
        getArray_spinnerPagamentos[1]="Cartão de crédito";
        getArray_spinnerPagamentos[2]="Cheque";
        Spinner sPagamentos = (Spinner) findViewById(R.id.spinnerPagamentos);
        ArrayAdapter adapterPagamentos = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getArray_spinnerPagamentos);
        sPagamentos.setAdapter(adapterPagamentos);

         /*----------PRENCHER O SPINNER DO TIPO DE IVA---------*/
        array_spinnerIva=new String[2];
        array_spinnerIva[0]="26%";
        array_spinnerIva[1]="3%";
        Spinner sIva = (Spinner) findViewById(R.id.spinnerIva);
        ArrayAdapter adapterIva = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinnerIva);
        sIva.setAdapter(adapterIva);
    }
}
