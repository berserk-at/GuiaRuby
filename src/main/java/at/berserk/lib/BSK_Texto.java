package at.berserk.lib;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Texto extends TextView {
    public static final int fmt_normal=0;
    public static final int fmt_titulo=1;
    public static final int fmt_subtitulo=2;
    public static final int fmt_rotulo=3;
    public static final int fmt_menuGrupo=4;
    public static final int fmt_menu=5;

    public BSK_Texto(BSK_TelaPadrao context, String texto) {
        this(context, texto,fmt_rotulo);
    }
    public BSK_Texto(BSK_TelaPadrao context, String texto, int formato) {
        this(context, texto,formato, Color.BLACK);
    }

    public BSK_Texto(BSK_TelaPadrao context, String texto, int formato, int cor) {
        super(context);
        this.setTextColor(cor);
        this.setText(texto);
        if (formato==fmt_normal) {
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        } else if (formato==fmt_titulo) {
            this.setPadding(0, BSK_Funcoes.getDPs(context, 8), 0, BSK_Funcoes.getDPs(context, 8));
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            this.setGravity(Gravity.CENTER_HORIZONTAL);
        } else if (formato==fmt_subtitulo){
            this.setPadding(0, BSK_Funcoes.getDPs(context, 6), 0, BSK_Funcoes.getDPs(context, 6));
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            this.setGravity(Gravity.CENTER_HORIZONTAL);
        } else if (formato==fmt_menuGrupo){
            this.setPadding(0,0,0,0);
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            this.setGravity(Gravity.LEFT);
        } else if (formato==fmt_menu){
            this.setPadding(0,0,0,0);
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            this.setGravity(Gravity.LEFT);
        } else if (formato==fmt_rotulo){
            this.setPadding(0,0,0,0);
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            this.setGravity(Gravity.LEFT);
        }
    }
}
