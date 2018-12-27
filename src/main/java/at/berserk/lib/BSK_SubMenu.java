package at.berserk.lib;

import android.os.Bundle;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_SubMenu {
    String titulo;
    BSK_Fragment tela;
    Class<? extends BSK_Fragment> classe;
    Bundle args;
    int corFundo;
    int corLetra;

    public BSK_SubMenu(String titulo, Class<? extends BSK_Fragment> classe, Bundle args, int corFundo, int corLetra) {
        this.titulo = titulo;
        this.classe=classe;
        this.args=args;
        this.corFundo = corFundo;
        this.corLetra = corLetra;
    }
}
