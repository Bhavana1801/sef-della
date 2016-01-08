/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dellaproject;

/**
 *
 * @author bhavs
 */
import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
    import java.net.URL;
import java.net.UnknownHostException;
@SuppressWarnings("PublicInnerClass")
public class InternetConnection{
    
    public String getInternetStatus(){
           try{
                  final String authUser = "201585060";
        final String authPassword = "msit123";
        Authenticator.setDefault(
                   new Authenticator() {
                       
                       @Override
            public PasswordAuthentication getPasswordAuthentication() {
                           return new PasswordAuthentication(
                               authUser, authPassword.toCharArray());
            }
                   }
        );
        System.setProperty("http.proxyUser", authUser);
        System.setProperty("http.proxyPassword", authPassword);
        System.setProperty("http.proxyHost", "10.10.10.3");
               System.setProperty("http.proxyPort", "3128");
        URL url = new URL("http://www.google.co.in");
                  HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.connect();
        if(con.getResponseCode() == 200){
                   return "Online";
        }
           }catch(Exception e){}
        return "Offline";
    }
}
