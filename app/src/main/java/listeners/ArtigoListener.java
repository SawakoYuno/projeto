package listeners;
import android.content.Context;
import java.util.List;
import modelo.Artigo;
/**
 * Created by Utilizador on 27/12/2017.
 */

public interface ArtigoListener {
    void onRefreshListaArtigos(List<Artigo> listaArtigo);


    void onUpdateListaArtigosBD(Artigo artigo, int operacao);
}
