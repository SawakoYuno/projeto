package listeners;

import java.util.List;

import modelo.Pedidos;
import modelo.User;

/**
 * Created by Joaquim on 02-01-2018.
 */

public interface UserListener {
    void onRefreshListaLogin(List<User> users);

    void onUpdateListaLoginBD(User user, int operacao);
}
