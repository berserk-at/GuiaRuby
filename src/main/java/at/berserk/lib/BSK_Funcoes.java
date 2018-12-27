package at.berserk.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Funcoes {
    public static Bitmap decodeBase64(String input){
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    public static void Substituir(StringBuilder linha, String encontrar, String substituir) {
        if (substituir == null) {
            substituir = "";
        }
        int p = -1;
        while ((p = linha.indexOf(encontrar, p)) >= 0) {
            linha.replace(p, p + encontrar.length(), substituir);
            p = p + substituir.length();
        }
    }

    public static String intCorToString(int cor){
        return(String.format("#%06X", 0xFFFFFF & cor));
    }

    public static int getDPs(Context contexto, int dps){
        final float scale = contexto.getResources().getDisplayMetrics().density;
        return((int) (dps * scale + 0.5f));
    }

    public static String formatarDinheiro(int valor){
        String inteiro=Integer.toString(valor/100);
        String decimal=Integer.toString(valor%100);
        while (decimal.length()<2){
            decimal="0"+decimal;
        }
        return("R$ "+inteiro+","+decimal);
    }
    public static String formatarDinheiro(long valorL){
        int valor=(int)valorL;
        String inteiro=Integer.toString(valor/100);
        String decimal=Integer.toString(valor%100);
        while (decimal.length()<2){
            decimal="0"+decimal;
        }
        return("R$ "+inteiro+","+decimal);
    }
    public static final SimpleDateFormat frm_dd_MM_YYYY = new SimpleDateFormat("dd/MM/yyyy");
    public static String retHoraSegundoFormatada() {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        String hora = formato.format(new Date());
        return (hora);
    }
    public static String retDataFormatada() {
        String data = frm_dd_MM_YYYY.format(new Date());
        return (data);
    }

}
