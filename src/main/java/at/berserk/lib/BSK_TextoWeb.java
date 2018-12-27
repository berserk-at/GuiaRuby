package at.berserk.lib;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_TextoWeb extends WebView {
    final int som;
    public BSK_TextoWeb(BSK_TelaPadrao context, String conteudo) {
        this(context, "", conteudo, 0);
    }
    public BSK_TextoWeb(BSK_TelaPadrao context, String local, String conteudo) {
        this(context, local, conteudo, 0);
    }
    ClienteWebView wbCliente;
    public BSK_TextoWeb(final BSK_TelaPadrao context, String local, String conteudo, int som) {
        super(context);
        this.som=som;
        getSettings().setDomStorageEnabled(true);
        getSettings().setJavaScriptEnabled(true);

        if (local.equals("")) {
            System.out.println("Conteudo="+conteudo);
            loadData(conteudo, "text/html; charset=UTF-8", "");
        } else {
            System.out.println("Local="+local);
            loadDataWithBaseURL(local, conteudo, "text/html", "UTF-8", null);
        }
        if (som!=0){
            setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            BSK_TextoWeb.this.Som();
                        }
                        }).start();
                }
            });
        }
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);

        wbCliente= new ClienteWebView(context);
        setWebViewClient(wbCliente);
    }

    public void Som(){
        try {
            MediaPlayer musica = MediaPlayer.create(this.getContext(), BSK_TextoWeb.this.som);
            musica.start();
        } catch(Exception ex){

        }
    }

    private class ClienteWebView extends WebViewClient {
        private BSK_TelaPadrao context;

        public ClienteWebView(BSK_TelaPadrao context) {
            this.context = context;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView wv, String url) {
            System.err.println("Carregando Uri=" + url);
            if (url.equals("berserk://ligar")) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:19981638488"));
                context.startActivity(intent);
                return true;
            } else if (url.equals("berserk://whatsapp")) {
                Uri uri = Uri.parse("smsto:19981638488");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.setPackage("com.whatsapp");
                context.startActivity(Intent.createChooser(intent, ""));
                return true;
            } else if (url.equals("berserk://email")) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"contato@berserk.com.br"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "ERPGMC- Contato via aplicativo");
                try {
                    context.startActivity(Intent.createChooser(intent, "Enviar e-mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context, "Não foi possível enviar o e-mail", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            return super.shouldOverrideUrlLoading(wv, url);
        }
    }
}
