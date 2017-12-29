package Adaptadores;

import android.content.Context;
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
 * Created by Utilizador on 29/12/2017.
 */

public class ListaEmentaAdapter extends BaseAdapter {
    private Context contexto;
    private LayoutInflater inflater;
    private List<Artigo> listaArtigo;
    private ViewHolder viewHolder;
    //private String ParteFinalNome;


    class ViewHolder
    {
        ImageView imagem;
        TextView descricao;
    }

    public ListaEmentaAdapter(Context context, List<Artigo> listaArtigo){
        this.contexto = context;
        this.listaArtigo = listaArtigo;
    }


    public void refresh(List<Artigo> listaDeEmenta)
    {
        listaArtigo = listaDeEmenta;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listaArtigo.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
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
            v = inflater.inflate(R.layout.item_c_listaementa, viewGroup, false);

            viewHolder = new ViewHolder();

            viewHolder.imagem = v.findViewById(R.id.imagem);
            viewHolder.descricao = v.findViewById(R.id.txtDescricao);

        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Artigo artigo = listaArtigo.get(i);

        /**********************************************************************/
        Glide.with(contexto)
                .load(artigo.getImagem())
                .placeholder(R.drawable.bife_a_portuguesa)
                .thumbnail(0f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imagem);
        /**********************************************************************/

        viewHolder.descricao.setText(artigo.getDetalhes());

        v.setTag(viewHolder);

        return v;
    }
}
