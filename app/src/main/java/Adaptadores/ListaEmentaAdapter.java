package Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import amsi.dei.estg.ipleiria.pt.projeto.R;
import modelo.Artigo;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class ListaEmentaAdapter extends BaseAdapter {
    private Context contexto;
    private LayoutInflater inflater;
    private List<Artigo> listaEmenta;

    private ViewHolder viewHolder;

    class ViewHolder
    {
        ImageView imagem;
        TextView txtDescricao;
    }


    public ListaEmentaAdapter(Context context, List<Artigo> listaEmenta){
        this.contexto = context;
        this.listaEmenta = listaEmenta;
    }


    public void refresh(List<Artigo> listaDeEmenta)
    {
        listaEmenta = listaDeEmenta;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listaEmenta.size();
    }

    @Override
    public Object getItem(int i) {
        return listaEmenta.get(i);
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
            v = inflater.inflate(R.layout.activity_c_item_listaementa, viewGroup, false);

            viewHolder = new ViewHolder();

            viewHolder.imagem = v.findViewById(R.id.imagem);
            viewHolder.txtDescricao = v.findViewById(R.id.txtDescricao);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Artigo artigo = listaEmenta.get(i);


        Glide.with(contexto)
                .load(artigo.getImagem())
                .placeholder(R.drawable.ipl_semfundo)
                .thumbnail(0f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imagem);
        return null;
    }
}
