package Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import amsi.dei.estg.ipleiria.pt.projeto.R;
import modelo.Artigo;


public class ListaEmentaAdapter extends BaseAdapter {
    private Context contexto;
    private LayoutInflater inflater;
    private List<Artigo> listaArtigo;
    private ViewHolder viewHolder;

    class ViewHolder
    {
        ImageView imagem;
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
        return listaArtigo.get(i);
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
            v = inflater.inflate(R.layout.item_grelha_artigo, viewGroup, false);

            viewHolder = new ViewHolder();

            viewHolder.imagem = v.findViewById(R.id.imgItemG);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Artigo artigo = listaArtigo.get(i);


       //viewHolder.imagem.setImageResource(artigo.getImagem());


        // -----------------------------IMAGEM-----------------------
       // viewHolder.imagem.setImageResource(artigo.getImagem());

        Glide.with(contexto)
                .load(artigo.getImagem())
                .placeholder(R.drawable.bife_a_portuguesa)
                .thumbnail(0f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imagem);

         //-----------------------------IMAGEM-----------------------

        v.setTag(viewHolder);


        return v;
    }
}
