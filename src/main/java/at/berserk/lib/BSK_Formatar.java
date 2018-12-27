package at.berserk.lib;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Formatar {

    public static boolean eNumero(char numero) {
        return (numero == '0' || numero == '1' || numero == '2' || numero == '3' || numero == '4' || numero == '5' || numero == '6' || numero == '7' || numero == '8' || numero == '9');
    }

    public static int StringParaInt(String valor) {
        if (valor == null || valor.equals("") || valor.equals("null")) {
            return (0);
        } else {
            return (Integer.parseInt(valor));
        }
    }

    public static String SoNumero(String valor) {
        if (valor != null) {
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < valor.length(); i++) {
                if (eNumero(valor.charAt(i))) {
                    tmp.append(valor.charAt(i));
                }
            }
            return (tmp.toString());
        } else {
            return (null);
        }
    }

    public static String FormatarCPF(String valor) {
        valor = SoNumero(valor);
        if (valor == null || valor.equals("")) {
            return ("");
        } else {
            StringBuilder cpf = new StringBuilder(valor);
            while (cpf.length() < 11) {
                cpf.insert(0, "0");
            }
            cpf.insert(3, ".");
            cpf.insert(7, ".");
            cpf.insert(11, "-");
            return (cpf.toString());
        }
    }

}