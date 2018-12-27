package at.berserk.lib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Berserk Sistemas
 * Projeto: Guia Ruby
 */
public class BSK_Conexao {
    private final String codificacao;
    public String getCodificacao(){
        return(codificacao);
    }

    private List<String> cookies;
    //    private HttpsURLConnection conn;

    public BSK_Conexao(String codificacao) {
        CookieHandler.setDefault(new CookieManager());
        System.setProperty("http.keepAlive", "false");
        this.codificacao = codificacao;
    }

    public String postar(String url, String postParams) {
        StringBuilder retorno = new StringBuilder();
        URL obj;
        try {
            obj = new URL(url);
            HttpURLConnection conn;
            conn = (HttpURLConnection) obj.openConnection();

            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Host", obj.getHost());
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            //conn.setRequestProperty("Connection", "keep-alive");
            if (this.cookies != null) {
                for (String cookie : this.cookies) {
                    conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
                }
            }
            conn.setRequestProperty("charset", this.getCodificacao());
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);

            PrintWriter dadosParamentros = new PrintWriter(conn.getOutputStream());
            dadosParamentros.print(postParams);
            dadosParamentros.close();

            try {
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), this.getCodificacao()));

                        String inputLine;
                        retorno = new StringBuilder();
                        boolean primeiro = true;
                        while ((inputLine = in.readLine()) != null) {
                            retorno.append(inputLine);
                            if (!primeiro) {
                                retorno.append("\n");
                            } else {
                                primeiro = false;
                            }
                        }
                    } catch (Exception ex) {
                        System.err.println("Falha ao obter dados obterConteudo()" + ex.getMessage());
                    }
                }
            } catch (Exception ex) {
                System.err.println("Problema ao obter dados(lendo):" + ex.getMessage());
                ex.printStackTrace();
            }

            setCookies(conn.getHeaderFields().get("Set-Cookie"));
            conn.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return (retorno.toString());
    }

    public String obterConteudo(String url) {
        try {
            HttpURLConnection conn;
            conn = (HttpURLConnection) (new URL(url)).openConnection();
                conn.setRequestMethod("GET");
                conn.setUseCaches(false);
                conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                conn.setRequestProperty("Accept",
                        "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                conn.setRequestProperty("Connection", "close");
                if (cookies != null) {
                    for (String cookie : this.cookies) {
                        conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
                    }
                }
                int responseCode = conn.getResponseCode();
                StringBuilder response = null;
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), this.getCodificacao()));

                    String inputLine;
                    response = new StringBuilder();
                    boolean primeiro = true;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                        if (!primeiro) {
                            response.append("\n");
                        } else {
                            primeiro = false;
                        }
                    }
                } catch (Exception ex) {
                    System.err.println("Falha ao obter dados obterConteudo()" + ex.getMessage());
                }

                setCookies(conn.getHeaderFields().get("Set-Cookie"));
                conn.disconnect();
                if (response != null && responseCode == 200) {
                    return response.toString();
                } else {
                    return (null);
                }
        } catch (Exception ex) {
            System.err.println("Problema obterConteudo() " + ex.getMessage());
            ex.printStackTrace();
            return (null);
        }
    }

    public Bitmap obterImagem(String url) {
        try {
            URL obj = new URL(url);
            HttpURLConnection conn;
            conn = (HttpURLConnection) obj.openConnection();

            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Connection", "close");
            if (cookies != null) {
                for (String cookie : this.cookies) {
                    conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
                }
            }
            int responseCode = conn.getResponseCode();
            setCookies(conn.getHeaderFields().get("Set-Cookie"));
            if (responseCode == 200) {
                return BitmapFactory.decodeStream(conn.getInputStream());
            } else {
                return (null);
            }
        } catch (Exception ex) {
            System.err.println("Problema obterConteudo() " + ex.getMessage());
            ex.printStackTrace();
            return (null);
        }
    }

    public List<String> getCookies() {
        return cookies;
    }

    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }

    public String obterParte(String conteudo, String procurar) {
        return (obterParte(conteudo, procurar, "\""));
    }

    public String obterParte(String conteudo, String procurar, String finalizador) {
        String retorno = null;
        if (conteudo!=null) {
            int pos = conteudo.indexOf(procurar);
            if (pos > 0) {
                pos = pos + procurar.length();
                if (finalizador == null) {
                    retorno = conteudo.substring(pos);
                } else {
                    int fim = conteudo.indexOf(finalizador, pos + 1);
                    if (fim > 0) {
                        retorno = conteudo.substring(pos, fim);
                    }
                }
            }
        }
        return (retorno);
    }

}
