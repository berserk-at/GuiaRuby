package at.berserk.lib;

import android.content.Context;
import android.util.AttributeSet;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Tela extends android.support.v4.widget.DrawerLayout {

    public BSK_Tela(Context context){
        this(context, null);
    }

    public BSK_Tela(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public BSK_Tela(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
