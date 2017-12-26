package modelo;

import java.util.Date;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class Fatura {

    /* ATRIBUTOS */
    private int id;
    private int id_meio_pagamento;
    private int id_pedidos;
    private Date data_fatura;
    private String obs;
    private int nif;

    /* MÉTODOS */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_meio_pagamento() {
        return id_meio_pagamento;
    }

    public void setId_meio_pagamento(int id_meio_pagamento) {
        this.id_meio_pagamento = id_meio_pagamento;
    }

    public int getId_pedidos() {
        return id_pedidos;
    }

    public void setId_pedidos(int id_pedidos) {
        this.id_pedidos = id_pedidos;
    }

    public Date getData_fatura() {
        return data_fatura;
    }

    public void setData_fatura(Date data_fatura) {
        this.data_fatura = data_fatura;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    /* CONSTRUTOR */
    public Fatura(int id, int id_meio_pagamento, int id_pedidos, Date data_fatura, String obs, int nif) {
        this.id = id;
        this.id_meio_pagamento = id_meio_pagamento;
        this.id_pedidos = id_pedidos;
        this.data_fatura = data_fatura;
        this.obs = obs;
        this.nif = nif;
    }
}
