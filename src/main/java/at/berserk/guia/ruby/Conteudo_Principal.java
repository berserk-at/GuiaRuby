package at.berserk.guia.ruby;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import at.berserk.lib.BSK_Botao;
import at.berserk.lib.BSK_Texto;
import at.berserk.lib.BSK_TextoWeb;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class Conteudo_Principal extends Padrao_Tela {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout layConteudo=(LinearLayout) super.onCreateView(inflater, container, savedInstanceState);
        layConteudo.addView(new BSK_TextoWeb(getPai(), getString(R.string.conteudo_principal)));

        return(layConteudo);
    }
}
