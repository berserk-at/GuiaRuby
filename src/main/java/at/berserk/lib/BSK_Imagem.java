package at.berserk.lib;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Imagem extends ImageView {

    public BSK_Imagem(BSK_TelaPadrao context, int imagem) {
        super(context);
        setImageDrawable(getResources().getDrawable(imagem));
        setScaleType(ScaleType.FIT_CENTER);
        setAdjustViewBounds(true);
    }
    public BSK_Imagem(BSK_TelaPadrao context, Bitmap imagem) {
        super(context);
        setImageBitmap(imagem);
        setScaleType(ScaleType.CENTER_INSIDE);
        setAdjustViewBounds(true);
    }
/*
    public BSK_Imagem(PadraoTela context, int imagem) {

        super(context);
        setImageDrawable(getResources().getDrawable(imagem));
        setScaleType(ScaleType.FIT_CENTER);
        setAdjustViewBounds(true);
    }
*/
}
