package at.berserk.lib;

import android.content.Context;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Entrada extends EditText {
    BSK_Botao botaoEnter=null; // Executa caso enter seja pressionado

    String nome;

    public String getNome(){
        return(nome);
    }
    public BSK_Entrada(Context context, String nome) {
        super(context);
        setTextColor(Color.BLACK);
        setSingleLine(true);
        this.nome=nome;
    }

    public void executarEnter(BSK_Botao botao){
        this.botaoEnter=botao;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_ENTER && botaoEnter!=null) {
            ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    this.getWindowToken(), 0);
            botaoEnter.requestFocus();
            botaoEnter.performClick();
            return true;
        }
        // Handle all other keys in the default way
        return super.onKeyDown(keyCode, event);
    }
}
