package at.berserk.guia.ruby;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import at.berserk.lib.BSK_Aplicacao;
import at.berserk.lib.BSK_Menu;
import at.berserk.lib.BSK_Rolagem;
import at.berserk.lib.BSK_Tela;
import at.berserk.lib.BSK_TelaPadrao;
import at.berserk.guia.ruby.telas.*;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class Principal extends BSK_TelaPadrao {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);

        definirFundoTopo(R.drawable.cor);

        atualizarMenu();
        int mostrandoGrupo = obterPreferencia("mostrandoGrupo",0);
        int mostrandoFilho = obterPreferencia("mostrandoFilho",-1);
        Mostrar(mostrandoGrupo,mostrandoFilho);
    }

    private static final int MENU_English = 2;
    private static final int MENU_PortuguesBR = 3;
    @Override
    public final void adicionarMenu(Menu menu) {
        menu.add(Menu.NONE, MENU_English, Menu.NONE, "English");
        menu.add(Menu.NONE, MENU_PortuguesBR, Menu.NONE, "Português-BR");
    }

    @Override
    public void clickMenu(MenuItem item){
        switch (item.getItemId()) {
            case MENU_English:
                ((BSK_Aplicacao) getApplication()).definirIdioma("en");
                this.recreate();
                Toast.makeText(getApplicationContext(), "Changed language to English", Toast.LENGTH_SHORT).show();
                break;
            case MENU_PortuguesBR:
                ((BSK_Aplicacao) getApplication()).definirIdioma("pt", "BR");
                this.recreate();
                Toast.makeText(getApplicationContext(), "Idioma mudado para Portugues", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        BSK_Tela drawer = (BSK_Tela) findViewById(R.id.principal);
//        if (drawer.isDrawerOpen(Gravity.LEFT)) {
//            drawer.closeDrawer(Gravity.LEFT);
//        }
    }

    @Override
    public void atualizarMenu(){
        limparMenu();
        addMenu(getResources().getString(R.string.menu_inicio),Conteudo_Principal.class);
                
        BSK_Menu basico=addMenu(getResources().getString(R.string.menu_basico));
        basico.addSubMenu(getResources().getString(R.string.menu_console_comentario), ConteudoBasico.class, R.string.conteudo_basico);
        basico.addSubMenu(getResources().getString(R.string.menu_palavras_reservadas), ConteudoBasico.class, R.string.conteudo_palavras_reservadas);
        basico.addSubMenu(getResources().getString(R.string.menu_tipagem), ConteudoBasico.class, R.string.conteudo_tipagem);
        basico.addSubMenu(getResources().getString(R.string.menu_classes), ConteudoBasico.class, R.string.conteudo_classes);
        
        BSK_Menu rails=addMenu(getResources().getString(R.string.menu_rails));
                
//        addMenu(getResources().getString(R.string.menu_inicio),Conteudo_Principal.class);
//        BSK_Menu basico=addMenu(getResources().getString(R.string.menu_basico),Conteudo_Basico.class);

        BSK_Menu sites=addMenu(getResources().getString(R.string.menu_sites));
        sites.addSubMenuSite("Berserk Sistemas","http://www.berserk.com.br/");
    }
    
    public void desenharPropaganda(LinearLayout principal){
        LinearLayout layTopo = new LinearLayout(this);
        layTopo.setOrientation(LinearLayout.VERTICAL);
        principal.addView(layTopo);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.FLUID);
        adView.setAdUnitId("ca-app-pub-8852277014002650~4183123210");
        layTopo.addView(adView, 0);

        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        adView.loadAd(adRequestBuilder.build());
        adView.setEnabled(true);

    }
}
