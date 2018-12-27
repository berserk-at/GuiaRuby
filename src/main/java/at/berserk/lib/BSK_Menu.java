package at.berserk.lib;

import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Menu {
    String titulo;
    ArrayList<BSK_SubMenu> subMenus= new ArrayList();
    BSK_Fragment tela;
    Class<? extends BSK_Fragment> classe;
    Bundle args;
    int corFundo;
    int corLetra;

    public BSK_Menu(String titulo, int corFundo, int corLetra, Class<? extends BSK_Fragment> classe) {
        this.titulo = titulo;
        this.classe = classe;
        this.corFundo = corFundo;
        this.corLetra = corLetra;
    }

    public BSK_Menu addSubMenu(String titulo){
        return(addSubMenu(titulo, null, null));
    }

    public BSK_Menu addSubMenuSite(String titulo, String endereco){
        Bundle args = new Bundle();
        args.putString("endereco", endereco);
        return(addSubMenu(titulo, BSK_AbreSite.class, args, Color.WHITE, Color.BLACK));
    }

    public BSK_Menu addSubMenu(String titulo, Class<? extends BSK_Fragment> classe){
        return(addSubMenu(titulo, classe, 0));
    }

    public BSK_Menu addSubMenu(String titulo, Class<? extends BSK_Fragment> classe, Bundle args){
        return(addSubMenu(titulo, classe, args, Color.WHITE, Color.BLACK));
    }
    public BSK_Menu addSubMenu(String titulo, Class<? extends BSK_Fragment> classe, int conteudo){
        Bundle args = new Bundle();
        args.putInt("conteudo", conteudo);
        return(addSubMenu(titulo, classe, args, Color.WHITE, Color.BLACK));
    }
    public BSK_Menu addSubMenu(String titulo, Class<? extends BSK_Fragment> classe, Bundle args, int corFundo, int corLetra){
        BSK_SubMenu subMenu = new BSK_SubMenu(titulo, classe, args, corFundo, corLetra);
        subMenus.add(subMenu);
        return(this);
    }
}
