package at.berserk.lib;

import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Observacoes extends EditText {
    String nome;
    BSK_Botao botaoEnter=null; // Executa caso enter seja pressionado

    public String getNome(){
        return(nome);
    }
    public BSK_Observacoes(Context context, String nome) {
        super(context);
        setLines(4);
        this.nome=nome;
    }

    public void executarEnter(BSK_Botao botao){
        this.botaoEnter=botao;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_ENTER && botaoEnter!=null) {
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
