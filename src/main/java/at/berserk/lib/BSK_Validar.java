package at.berserk.lib;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Validar {
    public static boolean validarCPF(String cpf){
        boolean retorno = false;
        String valor = BSK_Formatar.SoNumero(cpf);
        if (valor==null || valor.equals("")){
            retorno=false;
        } else if (valor.length() != 11) {
            return(false);
        } else {
            retorno = true;
            valor = BSK_Formatar.SoNumero(valor);
            int soma = 0;
            for (int i = 8; i >= 0; i--) {
                soma = soma + BSK_Formatar.StringParaInt(valor.substring(i, i + 1)) * (10 - i );
            }
            int mult = soma % 11;
            if (mult <= 1) {
                mult = 0;
            } else {
                mult = 11 - mult;
            }
            if (mult != BSK_Formatar.StringParaInt(valor.substring(9, 10))) {
                retorno = false;
            } else {
                soma = 0;
                for (int i = 9; i >= 0; i--) {
                    soma = soma + (BSK_Formatar.StringParaInt(valor.substring(i, i + 1)) * (11 - i));
                }
                mult = soma % 11;
                if (mult <= 1) {
                    mult = 11;
                }
                if (BSK_Formatar.StringParaInt(valor.substring(10, 11)) != (11 - mult)) {
                    retorno = false;
                }
            }
        }
        return (retorno);
    }
    public static boolean validarCNPJ(String cnpj){
        boolean retorno = false;
        String valor = BSK_Formatar.SoNumero(cnpj);
        if (valor==null || valor.equals("")){
            retorno=false;
        } else if (valor.length() != 14) {
            retorno=false;
        } else {
            retorno = true;

            int soma = 0;
            int mult = 2;
            for (int i =11; i >= 0; i--) {
                soma = soma + BSK_Formatar.StringParaInt(valor.substring(i, i + 1)) * mult;
                mult=mult+1;
                if (mult>9){
                    mult=2;
                }
            }
            mult = soma % 11;
            if (mult <= 1) {
                mult = 0;
            } else {
                mult = 11 - mult;
            }
            if (mult != BSK_Formatar.StringParaInt(valor.substring(12, 13))) {
                retorno = false;
            } else {
                soma = 0;
                mult = 2;
                for (int i = 12; i >= 0; i--) {
                    soma = soma + (BSK_Formatar.StringParaInt(valor.substring(i, i + 1)) * mult);
                    mult=mult+1;
                    if (mult>9){
                        mult=2;
                    }
                }
                mult = soma % 11;
                if (mult <= 1) {
                    mult = 0;
                } else {
                    mult = 11 - mult;
                }
                if (BSK_Formatar.StringParaInt(valor.substring(13, 14)) != mult) {
                    retorno = false;
                }
            }
        }
        return (retorno);
    }
    public static boolean validarData(String data){
        boolean retorno = false;
        if (data==null || data.equals("")){
            retorno=false;
        } else if (nvCaracter(data, '/')!=2){
            retorno=false;
        } else {
            String dia="";
            String mes="";
            String ano="";
            int p=0;

            for (int i=0;i<data.length();i++){
                if (data.charAt(i)=='/'){
                    p++;
                } else if (BSK_Formatar.eNumero(data.charAt(i))){
                    if (p==0){
                        dia+=data.charAt(i);
                    } else if (p==1){
                        mes+=data.charAt(i);
                    } else {
                        ano+=data.charAt(i);
                    }
                }
            }
            if (dia.equals("")){
                retorno=false;
            } else if (mes.equals("")){
                retorno=false;
            } else if (ano.equals("")){
                retorno=false;
            } else {
                int idia=BSK_Formatar.StringParaInt(dia);
                int imes=BSK_Formatar.StringParaInt(mes);
                int iano=BSK_Formatar.StringParaInt(ano);
                if (imes<1 || imes>12){
                    retorno=false;
                } else if (idia>31){
                    retorno=false;
                } else if (idia>30 && imes==4 && imes==6 && imes==9 && imes==11){
                    retorno=false;
                } else if (imes==02 && idia>29){
                    retorno=false;
                } else if (imes==02 && (iano%4)!=0 && idia>28){
                    retorno=false;
                } else if (iano>9999){
                    retorno=false;
                } else {
                    retorno=true;
                }
            }
        }
        return(retorno);
    }

    private static int nvCaracter(String valor,char letra){
        int retorno=0;
        for(int i=0;i<valor.length();i++){
            if (valor.charAt(i)==letra){
                retorno++;
            }
        }
        return(retorno);
    }

}