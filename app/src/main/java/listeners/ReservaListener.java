package listeners;
import android.content.Context;
import java.util.List;
import modelo.Reserva;
/**
 * Created by Utilizador on 05/01/2018.
 */

public interface ReservaListener {
    void onRefreshListaReserva(List<Reserva> listaReserva);


    void onUpdateListaReservaBD(Reserva reserva, int operacao);
}
