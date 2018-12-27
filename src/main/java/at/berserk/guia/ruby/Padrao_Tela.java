package at.berserk.guia.ruby;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import at.berserk.lib.BSK_Fragment;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class Padrao_Tela extends BSK_Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout layConteudo = new LinearLayout(getActivity());
        layConteudo.setOrientation(LinearLayout.VERTICAL);
        return(layConteudo);
    }

}
