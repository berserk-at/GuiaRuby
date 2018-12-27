package at.berserk.guia.ruby.telas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import at.berserk.guia.ruby.Padrao_Tela;
import at.berserk.guia.ruby.R;
import at.berserk.lib.BSK_TextoWeb;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class ConteudoBasico extends Padrao_Tela {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout layConteudo=(LinearLayout) super.onCreateView(inflater, container, savedInstanceState);
        int conteudo=getArguments().getInt("conteudo");
        layConteudo.addView(new BSK_TextoWeb(getPai(), getString(conteudo)));
        return(layConteudo);
    }
}
