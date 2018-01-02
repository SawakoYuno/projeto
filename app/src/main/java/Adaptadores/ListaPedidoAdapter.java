package Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

    }

    public ListaPedidoAdapter(Context contexto, List<Artigo> listaArtigos) {
        this.contexto = contexto;
        this.listaPedidos = listaArtigos;
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null) {
            inflater = LayoutInflater.from(contexto);
            v = inflater.inflate(R.layout.item_e_listapedidos, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.txtNome = v.findViewById(R.id.txtnomePedido);
            viewHolder.txtQuant = v.findViewById(R.id.txtquantPedido);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Artigo artigo = listaPedidos.get(i);

        viewHolder.txtNome.setText(artigo.getNome());
        viewHolder.txtQuant.setText(artigo.getQuantidade());

        v.setTag(viewHolder);

        return v;
        }
}
