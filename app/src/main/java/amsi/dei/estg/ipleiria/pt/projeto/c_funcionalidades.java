package amsi.dei.estg.ipleiria.pt.projeto;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.UnsupportedEncodingException;

public class c_funcionalidades extends AppCompatActivity {
    private Bundle extra;
    String idmesa;

    private MqttAndroidClient client;
    private String TAG = "c_funcionalidades";
    private PahoMqttClient pahoMqttClient;

    private Button PedirFatura, ChamarEmpregado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_funcionalidades);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pahoMqttClient = new PahoMqttClient();

        PedirFatura = (Button) findViewById(R.id.btnPedirFatura);
        ChamarEmpregado = (Button) findViewById(R.id.btnChamarEmpregado);

        client = pahoMqttClient.getMqttClient(getApplicationContext(), Constants.MQTT_BROKER_URL, Constants.CLIENT_ID);

        PedirFatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Fatura pedida com sucesso";
                if (!msg.isEmpty()) {
                    try {
                        pahoMqttClient.publishMessage(client, msg, 1, Constants.PUBLISH_TOPIC);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        ChamarEmpregado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Empregado Chamado com sucesso";
                if (!msg.isEmpty()) {
                    try {
                        pahoMqttClient.publishMessage(client, msg, 1, Constants.PUBLISH_TOPIC);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Intent intent = new Intent(c_funcionalidades.this, MqttMessageService.class);
        startService(intent);
    }

    public void OnclickFuncionalidades(View view) {
        Intent intentR = new Intent(this, c_pedirbebida.class);
        startActivity(intentR);
    }

    public void onClickFuncionalidades(View view) {
        String type = ((Button) view).getText().toString();

        switch (type) {
            case "Chamar Empregado":
                Toast.makeText(this, "Chamar o empregado com sucesso", Toast.LENGTH_SHORT).show();
               break;
            case "Pedir Bebida":
                Intent intentBebida = new Intent(this, c_pedirbebida.class);
                startActivity(intentBebida);
                break;
            case "Pedir Fatura":
                Toast.makeText(this, "Pedir fatura com sucesso", Toast.LENGTH_SHORT).show();
                break;
            case "Ver Lista de Pedidos":
                Intent intentPedidos = new Intent(this, e_pedidos.class);
                startActivity(intentPedidos);

        }
    }
}
