package at.berserk.lib;

import android.support.v4.app.Fragment;
/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Fragment extends Fragment {

    public BSK_TelaPadrao getPai() {
      return((BSK_TelaPadrao) getContext());
    }
    public BSK_Aplicacao getAplicacao(){
        return((BSK_Aplicacao)getPai().getApplication());
    }
    public void antesFechar(){
    }
}
