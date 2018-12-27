package at.berserk.lib;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.text.InputType;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_EntradaNumero extends BSK_Entrada{
    public BSK_EntradaNumero(Context context, String nome) {
        super(context, nome);
        setTextColor(Color.BLACK);
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setRawInputType(Configuration.KEYBOARD_12KEY);
    }

    public int obterInteiro(){
        try{
            return(Integer.parseInt(getText().toString()));
        } catch(Exception ex){
            return(0);
        }
    }
}
