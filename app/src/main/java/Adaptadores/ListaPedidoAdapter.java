package Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import amsi.dei.estg.ipleiria.pt.projeto.R;
import modelo.Artigo;
import modelo.Pedidos;

/**
 * Created by Utilizador on 31/12/2017.
 */

public class ListaPedidoAdapter extends BaseAdapter {

    private Context contexto;
    private LayoutInflater inflater;
    private List<Artigo> listaPedidos;

    private ViewHolder viewHolder;

    class ViewHolder

    {
        TextView txtNome;
        TextView txtQuant;
        Button buttonEliminar;

    }

    public ListaPedidoAdapter(Context contexto, List<Artigo> listaPedidos) {
        this.contexto = contexto;
        this.listaPedidos = listaPedidos;
    }

    public void refresh(List<Artigo> listaPedidos)
    {
        this.listaPedidos = listaPedidos;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return listaPedidos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaPedidos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaPedidos.get(i).getId();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null) {
            inflater = LayoutInflater.from(contexto);
            v = inflater.inflate(R.layout.item_e_listapedidos, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.txtNome = v.findViewById(R.id.txtnomePedido);
            viewHolder.txtQuant = v.findViewById(R.id.txtquantPedido);
            viewHolder.buttonEliminar = v.findViewById(R.id.buttonEliminar);
            viewHolder.buttonEliminar.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    System.out.println("--->houve click" + i);
                    listaPedidos.remove(i);

                    refresh(listaPedidos);

                }
            });

        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Artigo artigo = listaPedidos.get(i);

        viewHolder.txtNome.setText(artigo.getNome());
        viewHolder.txtQuant.setText(artigo.getQuantidade().toString());

        v.setTag(viewHolder);



        return v;
        }
}
