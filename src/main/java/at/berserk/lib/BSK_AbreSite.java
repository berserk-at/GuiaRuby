package at.berserk.lib;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import at.berserk.guia.ruby.Padrao_Tela;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_AbreSite extends Padrao_Tela {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout layConteudo=(LinearLayout) super.onCreateView(inflater, container, savedInstanceState);

        final String endereco = getArguments().getString("endereco");
        layConteudo.addView(new BSK_TextoWeb(getPai(), endereco));

         BSK_Botao botao=new BSK_Botao(getPai(),endereco);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(endereco);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        layConteudo.addView(botao);

        return(layConteudo);
    }
}
