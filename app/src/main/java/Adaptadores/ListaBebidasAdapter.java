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

/**
 * Created by Utilizador on 05/01/2018.
 */

public class ListaBebidasAdapter extends BaseAdapter {

    private Context contexto;
    private LayoutInflater inflater;
    private List<Artigo> listaBebidas;
    private ListaBebidasAdapter.ViewHolder viewHolder;

    public class ViewHolder
    {

        ImageView imagem;
        TextView nome;

    }

    public ListaBebidasAdapter(Context context, List<Artigo> listaBebidas){
        this.contexto = context;
        this.listaBebidas = listaBebidas;
    }


    public void refresh(List<Artigo> listaBebidas)
    {
        this.listaBebidas = listaBebidas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listaBebidas.size();
    }

    @Override
    public Object getItem(int i) {
        return listaBebidas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaBebidas.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;

        if (v == null) {
            inflater = LayoutInflater.from(contexto);
            v = inflater.inflate(R.layout.item_c_listabebidas, viewGroup, false);

            viewHolder = new ViewHolder();

            viewHolder.imagem = v.findViewById(R.id.imagemBebida);
            viewHolder.nome = v.findViewById(R.id.txtNome);

        }else{
            viewHolder = (ListaBebidasAdapter.ViewHolder) view.getTag();
        }



        Artigo artigo = listaBebidas.get(i);

        String ParteFinalNome = artigo.getImagem();

        int drawableResourceId = contexto.getResources().getIdentifier(ParteFinalNome.substring(0, ParteFinalNome.length()-4), "drawable", contexto.getPackageName());

        viewHolder.imagem.setImageResource(drawableResourceId);

        viewHolder.nome.setText(artigo.getDetalhes());

        v.setTag(viewHolder);

        return v;
    }
}