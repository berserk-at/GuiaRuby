package at.berserk.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Rolagem extends ScrollView {
    // Valores que são usados quando a rolagem falhar
    private int rolagemX = -1;
    private int rolagemY = -1;
    private ViewTreeObserver.OnGlobalLayoutListener listener;

    public BSK_Rolagem(Context contexto) {
        super(contexto);
    }

    public BSK_Rolagem(Context contexto, AttributeSet attrs) {
        super(contexto, attrs);
    }

    public BSK_Rolagem(Context contexto, AttributeSet attrs, int defStyle) {
        super(contexto, attrs, defStyle);
    }

    // Rotina necessária para a rolagem ser efetuada, mesmo durante alterações de layout Ex: Carregamentos de conteúdos de webview
    public void rolar(int x, int y) {
        rolagemX = x;
        rolagemY = y;
        scrollTo(x, y);
        new Thread() {
            @Override
            public void run() {
                if (listener == null) {//verifica se não há outro listener já ativo, por exemplo quando carrega a primeira vez a tela
                    listener = new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            scrollTo(rolagemX, rolagemY);
                        }
                    };

                    getViewTreeObserver().addOnGlobalLayoutListener(listener);
                    try {
                        this.sleep(5000);// Tempo de 5s aguardando carregar todo o layout antes de remover a rolagem fixa
                        getViewTreeObserver().removeGlobalOnLayoutListener(listener);
                        listener=null;
                    } catch(Exception ex){

                    }
                }
            }
        }.start();
    }
}
