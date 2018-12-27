package at.berserk.lib;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Sobre extends BSK_Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout layConteudo = new LinearLayout(getActivity());
        layConteudo.setOrientation(LinearLayout.VERTICAL);

        StringBuilder sobre = new StringBuilder();
        sobre.append("<p>Desenvolvido por <b>Berserk Sistemas</b> utilizando framework próprio para aplicativos móveis desenvolvido pela empresa.</p>");
        sobre.append("<p>Oferecemos serviços terceirizados de desenvolvimento de aplicativos móveis, aplicações para web e integrações entre sistemas.</p>");
        sobre.append("<p>Entre em nosso site http://www.berserk.com.br/ e saiba mais sobre a empresa.</p>");

        sobre.append("<p>Developed by <b>Berserk Sistemas</b> using own framework for mobile applications developed by the company.</p>");
        sobre.append("<p>We offer outsourced services to mobile application development, web applications and integrations between systems.</p>");
        sobre.append("<p>Enter our site http://www.berserk.com.br/ and learn more about the company.</p>");

        BSK_TextoWeb teste=new BSK_TextoWeb(getPai(), sobre.toString());
        layConteudo.addView(teste);
        ///layConteudo.addView(new BSK_TextoWeb(getPai(), "Desenvolvido por Berserk Sistemas"));

        return(layConteudo);
    }
}
