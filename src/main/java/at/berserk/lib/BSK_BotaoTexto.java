package at.berserk.lib;

import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_BotaoTexto extends FrameLayout {

    ImageButton botao;
    TextView texto;
    public BSK_BotaoTexto(BSK_TelaPadrao context) {
        super(context);

        botao=new ImageButton(context);
        botao.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        botao.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.addView(botao);

        texto=new TextView(context);
        RelativeLayout.LayoutParams layTexto = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        texto.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        texto.setLayoutParams(layTexto);
        texto.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        texto.setTypeface(null, Typeface.BOLD);
        //texto.setBottom(0);
        this.addView(texto);
    }

    public void setImagem(int imagem){
        botao.setBackgroundDrawable(getResources().getDrawable(imagem));
    }
    public void setTexto(String texto){
        this.texto.setText(texto);
    }
    public void definirAcao(OnClickListener evento){
        botao.setOnClickListener(evento);
        texto.setOnClickListener(evento);
    }
    public void setTamanhoTexto(int tamanho){
        //tamanho em sp
        texto.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
    }
    public void setTextoCor(int cor){
        texto.setTextColor(cor);
    }
}
