package at.berserk.lib;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public abstract class BSK_TelaPadrao extends AppCompatActivity {

    private BSK_Rolagem rolRolagem;
    private LinearLayout layConteudo;
    private BSK_Menu_Adapter listAdapter;
    private Toolbar tbTopo;

    int mostrandoGrupo=-1;
    int mostrandoFilho=-1;

    public boolean isPaisagem(){
      return(getResources().getConfiguration().orientation==2);
    }
    public int obterLargura(){
        Display display = ((WindowManager) getSystemService(BSK_TelaPadrao.WINDOW_SERVICE)).getDefaultDisplay();
        Point dimensoes=new Point();
        display.getSize(dimensoes);
        return (dimensoes.x);
    }
    public int obterAltura(){
        Display display = ((WindowManager) getSystemService(BSK_TelaPadrao.WINDOW_SERVICE)).getDefaultDisplay();
        Point dimensoes=new Point();
        display.getSize(dimensoes);
        return (dimensoes.y);
    }
    public int tamBotao(){
        if (isPaisagem()) {
            return (obterAltura() / 6);
        } else {
            return (obterLargura() / 6);
        }
    }
    public int alturaTitulo(){
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)){
            return(TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics()));
        } else {
            if (isPaisagem()) {
                return (obterAltura() / 8);
            } else {
                return (obterAltura() / 10);
            }
        }
    }

    DrawerLayout lateral;
    ExpandableListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        LinearLayout principal= new LinearLayout(this);
        principal.setLayoutParams
                (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        principal.setOrientation(LinearLayout.VERTICAL);
        principal.setBackgroundColor(Color.WHITE);

        tbTopo=new Toolbar(this);
        tbTopo.setLayoutParams
                (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,BSK_Funcoes.getDPs(this, 48)));

        principal.addView(tbTopo);

        desenharPropaganda(principal);

        lateral=new DrawerLayout(this);
        LinearLayout.LayoutParams lateralParametro=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lateralParametro.weight=1;
        lateral.setLayoutParams(lateralParametro);
        lateral.setBackgroundColor(Color.WHITE);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, lateral, tbTopo, 0, 0);

        lateral.setDrawerListener(toggle);
        toggle.syncState();

        setSupportActionBar(tbTopo);

        rolRolagem= new BSK_Rolagem(this);
        LinearLayout.LayoutParams lpRolagem = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        rolRolagem.setLayoutParams(lpRolagem);
        lateral.addView(rolRolagem);

        layConteudo = new LinearLayout(this);
        layConteudo.setId(gerarId());
        LinearLayout.LayoutParams lpConteudo = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layConteudo.setLayoutParams(lpConteudo);
        layConteudo.setOrientation(LinearLayout.VERTICAL);
        layConteudo.setBackgroundColor(Color.WHITE);
        rolRolagem.addView(layConteudo);

        lista=new ExpandableListView(this);
        lista.setBackgroundColor(Color.WHITE);

        listAdapter = new BSK_Menu_Adapter(this);
        lista.setAdapter(listAdapter);

        DrawerLayout.LayoutParams listaParametro=new DrawerLayout.LayoutParams(BSK_Funcoes.getDPs(this, 250), LinearLayout.LayoutParams.MATCH_PARENT);
        listaParametro.gravity= Gravity.LEFT;

        lista.setLayoutParams(listaParametro);

        lista.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition,
                                        long id) {
                return(Mostrar(groupPosition, -1));
            }});
        lista.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
          @Override
          public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
              return(Mostrar(groupPosition, childPosition));
          }
        });

        lateral.addView(lista);

        lateral.closeDrawer(lista);

        principal.addView(lateral);

        this.setContentView(principal);
    }

    public BSK_Menu addMenu(String titulo){
        return(addMenu(titulo, null));
    }

    public BSK_Menu addMenu(String titulo, Class<? extends BSK_Fragment> classe){
      return(addMenu(titulo, Color.BLACK, Color.WHITE, classe));
    }
    public BSK_Menu addMenu(String titulo, int corFundo, int corLetra, Class<? extends BSK_Fragment> classe){
        return(listAdapter.adicionarMenu(titulo, corFundo, corLetra, classe));
    }

    public void limparMenu(){
        listAdapter.menus.clear();
        listAdapter.notifyDataSetChanged();
    }
    private final String PREFS_PRIVATE = "PREFS_PRIVATE";

    public String obterPreferencia(String nome, String padrao) {
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
        return(sharedPreferences.getString(nome, padrao));
    }
    public long obterPreferencia(String nome, long padrao) {
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
        return(sharedPreferences.getLong(nome, padrao));
    }
    public int obterPreferencia(String nome, int padrao) {
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
        return(sharedPreferences.getInt(nome, padrao));
    }
    
    public void definirPreferencia(String nome, String valor) {
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsPrivateEditor = sharedPreferences.edit();
        prefsPrivateEditor.putString(nome, valor);
        prefsPrivateEditor.apply();
    }
    public void definirPreferencia(String nome, long valor) {
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsPrivateEditor = sharedPreferences.edit();
        prefsPrivateEditor.putLong(nome, valor);
        prefsPrivateEditor.apply();
    }
    public void definirPreferencia(String nome, int valor) {
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsPrivateEditor = sharedPreferences.edit();
        prefsPrivateEditor.putInt(nome, valor);
        prefsPrivateEditor.apply();
    }

    public boolean temCamera(){
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public void armazenarComponentes(LinearLayout layout){
        if (layout!=null){
            for (int i = 0; i < layout.getChildCount(); i++) {
                if (layout.getChildAt(i) instanceof BSK_Entrada) {
                    definirPreferencia(this.getLocalClassName() + "." + ((BSK_Entrada) layout.getChildAt(i)).getNome(), ((BSK_Entrada) layout.getChildAt(i)).getText().toString());
                } else if (layout.getChildAt(i) instanceof LinearLayout) {
                    armazenarComponentes((LinearLayout)layout.getChildAt(i));
                }
            }

        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        try {
            if (filhoAtual!=null){
                filhoAtual.antesFechar();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment fragmento=getSupportFragmentManager().findFragmentById(filhoAtual.getId());
                if (fragmento!=null) {
                    ft.remove(fragmento);
                }
                ft.commitAllowingStateLoss();
            }
            super.onSaveInstanceState(savedInstanceState);
            armazenarComponentes(getConteudoComponente());
            savedInstanceState.putInt("mostrandoGrupo",mostrandoGrupo);
            savedInstanceState.putInt("mostrandoFilho",mostrandoFilho);
        } catch (Exception ex){
            filhoAtual=null;
            ex.printStackTrace();
        }
    }

    public BSK_Fragment filhoAtual=null;

    public void definirComponente(LinearLayout layout, String nome, String valor){
        if (layout!=null){
            for (int i = 0; i < layout.getChildCount(); i++) {
                if (layout.getChildAt(i) instanceof BSK_Entrada) {
                    System.err.println("nome="+((BSK_Entrada) layout.getChildAt(i)).getNome());
                }
                if (layout.getChildAt(i) instanceof BSK_Entrada
                        && ((BSK_Entrada) layout.getChildAt(i)).getNome().equals(nome)) {
                    if (valor != null && !valor.equals("")) {
                        ((BSK_Entrada) layout.getChildAt(i)).setText(valor);
                    } else {
                        ((BSK_Entrada) layout.getChildAt(i)).setText("");
                    }
                } else if (layout.getChildAt(i) instanceof LinearLayout) {
                    definirComponente((LinearLayout)layout.getChildAt(i),nome, valor);
                }
            }

        }
    }
    public void restaurarComponentes(LinearLayout layout){
        if (layout!=null){
            for (int i = 0; i < layout.getChildCount(); i++) {
                if (layout.getChildAt(i) instanceof BSK_Entrada) {
                    String valor = obterPreferencia(this.getLocalClassName() + "." + ((BSK_Entrada) layout.getChildAt(i)).getNome(), "");
                    if (valor != null && !valor.equals("")) {
                        ((BSK_Entrada) layout.getChildAt(i)).setText(valor);
                    }
                } else if (layout.getChildAt(i) instanceof LinearLayout) {
                    restaurarComponentes((LinearLayout)layout.getChildAt(i));
                }
            }

        }
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        try {
            super.onRestoreInstanceState(savedInstanceState);
            int mostrandoGrupo = obterPreferencia("mostrandoGrupo",-1);
            int mostrandoFilho = obterPreferencia("mostrandoFilho",-1);
            Mostrar(mostrandoGrupo, mostrandoFilho);
            restaurarComponentes(layConteudo);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            int mostrandoGrupo = obterPreferencia("mostrandoGrupo",-1);
            int mostrandoFilho = obterPreferencia("mostrandoFilho",-1);
            Mostrar(mostrandoGrupo, mostrandoFilho);
            restaurarComponentes(layConteudo);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void adicionarMenu(Menu menu){

    }

    private static final int MENU_Sobre = 1;

    @Override
    public final  boolean onCreateOptionsMenu(Menu menu) {
        adicionarMenu(menu);
        menu.add(Menu.NONE, MENU_Sobre, Menu.NONE, "Sobre");
        return true;
    }

    public final LinearLayout getConteudoComponente(){
        return(layConteudo);
    };

    @Override
    public final boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:{
                if(lateral.isDrawerOpen(lista)){
                    lateral.closeDrawer(lista);
                }else{
                    lateral.openDrawer(lista);
                }
                return true;
            }
            case MENU_Sobre: {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(getConteudoComponente().getId(), new BSK_Sobre());
                getSupportActionBar().setTitle("Sobre");
                getSupportActionBar().setSubtitle("");
                ft.commitAllowingStateLoss();
                return true;
            }
            default:
                clickMenu(item);
                return false;
        }
    }

    public void clickMenu(MenuItem item){

    }

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int gerarId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public boolean Mostrar(Class<? extends BSK_Fragment> classe){
        int posicao=0;
        boolean achou=false;
        System.err.println("Procurando Mostrar");
        System.err.println(classe.getCanonicalName());
        for(BSK_Menu menuSel:listAdapter.menus){
            System.err.println(menuSel.classe.getCanonicalName());
            if (menuSel.classe.getCanonicalName().equals(classe.getCanonicalName())){
                Mostrar(posicao, -1);
                System.err.println("Achou Mostrar");
                achou=true;
                break;
            }
            posicao++;
        }
        return(achou);
    }
    public boolean Mostrar(int grupo, int filho){
        definirPreferencia("mostrandoGrupo",grupo);
        definirPreferencia("mostrandoFilho",filho);
        if (grupo==-1){
            return(false);
        } else if (filho==-1 && grupo<listAdapter.menus.size()){
            if (listAdapter.menus.get(grupo).classe != null) {
                try {
                    filhoAtual = listAdapter.menus.get(grupo).classe.getConstructor().newInstance();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(getConteudoComponente().getId(), filhoAtual);
                    ft.commitAllowingStateLoss();
                    invalidateOptionsMenu();
                    lateral.closeDrawer(lista);
                    rolRolagem.rolar(0, 0);
                    return (true);
                } catch (Exception ex) {
                    return (false);
                }
            } else {
                return (false);
            }
        } else if (grupo<listAdapter.menus.size()
        && filho<listAdapter.menus.get(grupo).subMenus.size()){
            if (listAdapter.menus.get(grupo).subMenus.get(filho).classe!=null) {
                try {
                    filhoAtual = listAdapter.menus.get(grupo).subMenus.get(filho).classe.getConstructor().newInstance();
                    if (listAdapter.menus.get(grupo).subMenus.get(filho).args != null) {
                        filhoAtual.setArguments(listAdapter.menus.get(grupo).subMenus.get(filho).args);
                    }

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(getConteudoComponente().getId(), filhoAtual);
                    ft.commitAllowingStateLoss();
                    invalidateOptionsMenu();
                    lateral.closeDrawer(lista);
                    rolRolagem.rolar(0, 0);

                    return (true);
                } catch (Exception ex) {
                    return (false);
                }
            } else if (listAdapter.menus.get(grupo).subMenus.get(filho).args!=null && listAdapter.menus.get(grupo).subMenus.get(filho).args.getString("endereco")!=null){
                Uri uri = Uri.parse(listAdapter.menus.get(grupo).subMenus.get(filho).args.getString("endereco"));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return(true);
            } else {
                return(false);
            }
        } else {
            return(false);
        }
    }

    public void definirFundoTopo(int fundo){
        tbTopo.setBackgroundResource(fundo);
    }

    public void atualizarMenu(){
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (lateral.isDrawerOpen(GravityCompat.START)) {
            lateral.closeDrawer(GravityCompat.START);
        }
    }

    public boolean isPodeVoltar(){
        int mostrandoGrupo = obterPreferencia("mostrandoGrupo",-1);
        int mostrandoFilho = obterPreferencia("mostrandoFilho",-1);
        if ((mostrandoGrupo<listAdapter.menus.size())
                && mostrandoFilho>0){
            return(true);
        } else if (mostrandoGrupo>0){
            return(true);
        } else {
            return(false);
        }
    }
    public boolean isPodeAvancar(){
        int mostrandoGrupo = obterPreferencia("mostrandoGrupo",-1);
        int mostrandoFilho = obterPreferencia("mostrandoFilho",-1);
        if ((mostrandoGrupo<listAdapter.menus.size())
                && mostrandoFilho<listAdapter.menus.get(mostrandoGrupo).subMenus.size()-1){
            return(true);
        } else if (mostrandoGrupo<listAdapter.menus.size()-1){
            return(true);
        } else {
            return(false);
        }
    }
    public void Voltar(){
        int mostrandoGrupo = obterPreferencia("mostrandoGrupo",-1);
        int mostrandoFilho = obterPreferencia("mostrandoFilho",-1);
        if (isPodeVoltar()){
            if ((mostrandoGrupo<listAdapter.menus.size())
                    && mostrandoFilho>0){
                Mostrar(mostrandoGrupo, mostrandoFilho-1);
            } else if (mostrandoGrupo>0){
                Mostrar(mostrandoGrupo-1, listAdapter.menus.get(mostrandoGrupo-1).subMenus.size()-1);
            }
        }
    }
    public void Avancar(){
        int mostrandoGrupo = obterPreferencia("mostrandoGrupo",-1);
        int mostrandoFilho = obterPreferencia("mostrandoFilho",-1);
        if (isPodeAvancar()){
            if ((mostrandoGrupo<listAdapter.menus.size())
                    && mostrandoFilho<listAdapter.menus.get(mostrandoGrupo).subMenus.size()-1){
                Mostrar(mostrandoGrupo, mostrandoFilho+1);
            } else if (mostrandoGrupo<listAdapter.menus.size()-1){
                Mostrar(mostrandoGrupo+1, 0);
            }
        }
    }
    public BSK_Aplicacao getAplicacao(){
        return((BSK_Aplicacao) getApplication());
    }
    
    public void desenharPropaganda(LinearLayout principal){
        
    }
    
}
