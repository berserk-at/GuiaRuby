package at.berserk.lib;

import android.content.Context;
import android.widget.Button;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Botao extends Button {
    public BSK_Botao(Context context, String texto) {
        super(context);
        this.setText(texto);
    }
}
