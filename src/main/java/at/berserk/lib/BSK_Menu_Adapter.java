package at.berserk.lib;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Menu_Adapter extends BaseExpandableListAdapter {
    private Context _context;
    ArrayList<BSK_Menu> menus = new ArrayList();

    public BSK_Menu adicionarMenu(String titulo, int corFundo, int corLetra, Class<? extends BSK_Fragment> classe){
        BSK_Menu menu= new BSK_Menu(titulo, corFundo, corLetra, classe);
        menus.add(menu);
        return(menu);
    }

    public BSK_Menu_Adapter(Context context) {
        this._context = context;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return(menus.get(groupPosition).subMenus.get(childPosititon));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final BSK_SubMenu subMenu = ((BSK_SubMenu) getChild(groupPosition, childPosition));
        if (convertView == null) {
            convertView = new LinearLayout(this._context);
        } else {
            ((LinearLayout)convertView).removeAllViews();
        }
        ((LinearLayout)convertView).setOrientation(LinearLayout.VERTICAL);
        final AbsListView.LayoutParams lpItem=new AbsListView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ((LinearLayout)convertView).setLayoutParams(lpItem);
        convertView.setPadding(BSK_Funcoes.getDPs(this._context, 8),BSK_Funcoes.getDPs(this._context, 8),BSK_Funcoes.getDPs(this._context, 8),BSK_Funcoes.getDPs(this._context, 8));
        convertView.setBackgroundColor(subMenu.corFundo);

        final BSK_Texto txtGrupo=new BSK_Texto((BSK_TelaPadrao)this._context, subMenu.titulo, BSK_Texto.fmt_menu);
        txtGrupo.setTextColor(subMenu.corLetra);
        ((LinearLayout)convertView).addView(txtGrupo);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        try{
            return(menus.get(groupPosition).subMenus.size());
        } catch(Exception ex){
            return(0);
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return(menus.get(groupPosition));
    }

    @Override
    public int getGroupCount() {
        return(menus.size());
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        BSK_Menu menuGrupo = ((BSK_Menu) getGroup(groupPosition));
        if (convertView == null) {
            convertView = new LinearLayout(this._context);
        } else {
            ((LinearLayout)convertView).removeAllViews();
        }
        ((LinearLayout)convertView).setOrientation(LinearLayout.VERTICAL);
        AbsListView.LayoutParams lpGrupo=new AbsListView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ((LinearLayout)convertView).setLayoutParams(lpGrupo);
        convertView.setPadding(BSK_Funcoes.getDPs(this._context, 8),BSK_Funcoes.getDPs(this._context, 8),BSK_Funcoes.getDPs(this._context, 8),BSK_Funcoes.getDPs(this._context, 8));
        convertView.setBackgroundColor(menuGrupo.corFundo);

        BSK_Texto txtGrupo=new BSK_Texto((BSK_TelaPadrao)this._context, menuGrupo.titulo, BSK_Texto.fmt_menuGrupo);
        txtGrupo.setTextColor(menuGrupo.corLetra);
        ((LinearLayout)convertView).addView(txtGrupo);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
