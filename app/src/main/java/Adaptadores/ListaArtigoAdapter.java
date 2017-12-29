package Adaptadores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import amsi.dei.estg.ipleiria.pt.projeto.R;
import modelo.Artigo;
import modelo.ArtigoDBHelper;


public class ListaArtigoAdapter extends BaseAdapter {
    private Context contexto;
    private LayoutInflater inflater;
    private List<Artigo> listaArtigo;
    private ViewHolder viewHolder;
    private String ParteFinalNome;



    class ViewHolder
    {
        ImageView imagem;
    }


    public ListaArtigoAdapter(Context context, List<Artigo> listaArtigo){
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

       String ParteFinalNome = artigo.getImagem();

       //  pictureBox.Image = Image.FromFile(Path.GetDirectoryName(Application.ExecutablePath) + @"\imagens\" + imagem);
      // viewHolder.imagem.setImageResource(artigo.getImagem());
      //ImageView.setImageURI(Uri.parse(new File("/sdcard/cats.jpg").toString()));
        //viewHolder.imagem.setImageResource(Uri.parse(new File("/sdcard/cats.jpg").toString()));

        // -----------------------------IMAGEM-----------------------
       // viewHolder.imagem.setImageResource(artigo.getImagem());


        /*
string ParteFinalNome;

 pictureBox.Image = Image.FromFile(Path.GetDirectoryName(Application.ExecutablePath) + @"\imagens\" + imagem);*/

//context.getApplicationInfo().dataDir + "/databases/";


        //viewHolder.imagem.setImageResource(contexto.getApplicationInfo().dataDir + "/drawable/" + ParteFinalNome.toString());


        Glide.with(contexto)
                .load(contexto.getApplicationInfo().dataDir + "/drawable/" + ParteFinalNome.toString())
                .placeholder(R.drawable.costoleta_de_porco_com_molho_balsamico)
                .thumbnail(0f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imagem);

         //-----------------------------IMAGEM-----------------------





        v.setTag(viewHolder);

        return v;
    }


}
