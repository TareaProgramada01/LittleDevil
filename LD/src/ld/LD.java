/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ld;

import java.io.File;
import java.util.Map;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public abstract class Reproductor implements BasicPlayerListener 
{
    private final BasicPlayer basicPlayer;
    private double bytesLength;

    /**
     *
     */
    public Reproductor() throws BasicPlayerException 
    {
        basicPlayer = new BasicPlayer();
        basicPlayer.addBasicPlayerListener(this);
        basicPlayer.play();
    }

    public void play() 
    {
        try {
        basicPlayer.play();
        }
        catch (BasicPlayerException e) 
        {
        }
    }

    public void stop() 
    {
        try 
        {
            basicPlayer.stop();
        } 
        catch (BasicPlayerException e) 
        {
        }
    }

    public void pause() 
    {
        try 
        {
            basicPlayer.pause();
        } 
        catch (BasicPlayerException e) 
        {
        }
    }

    public void resume() 
    {
        try 
        {
            basicPlayer.resume();
        } 
        catch (BasicPlayerException e) 
        {
        }
    }

    public void loadFile(String ruta) throws BasicPlayerException 
    {
        basicPlayer.open(new File(ruta));
    }

/**
* Necesario por implementar BasicPlayerListener. Es ejecutado una vez se
* carga un fichero. En este caso, obtiene el tamaño en bytes del fichero.
     * @param arg0
     * @param arg1
*/

    public void opened(Object arg0, Map arg1) 
    {
        if (arg1.containsKey("audio.length.bytes")) 
        {
            bytesLength = Double.parseDouble(arg1.get("audio.length.bytes").toString());
        }
    }

/**
* Necesario por implementar BasicPlayerListener. Según la documentación,
* este método es llamado varias veces por segundo para informar del
* progreso en la reproducción.
     * @param bytesread
     * @param microseconds
     * @param pcmdata
     * @param properties
*/

    public void progress(int bytesread, long microseconds, byte[] pcmdata,Map properties) 
    {
        float progressUpdate = (float) (bytesread * 1.0f / bytesLength * 1.0f);
        int progressNow = (int) (bytesLength * progressUpdate);
        // Descomentando la siguiente línea se mosrtaría el progreso
        // System.out.println(" -> " + progressNow);
    }

    public void setController(BasicController arg0) 
    {
        // TODO Auto-generated method stub

    }


    public void stateUpdated(BasicPlayerEvent arg0) 
    {
        // TODO Auto-generated method stub

    }
    
    public void Test() throws BasicPlayerException 
    {
        Reproductor rep = new Reproductor() {};
        try 
        {
            rep.loadFile("/home/kenneth/Descargas/Something.mp3");
            rep.play();
        } 
        catch (BasicPlayerException e) 
        {
        }
    }
}
public class LD {

    class Nodo {
        String info;
        Nodo ant,sig;
    }
    private Nodo raiz;
    private Nodo actual;
    
    public LD () {
        raiz=null;
    }
              
    
    public void insertarUltimo(String cancion) {
        Nodo nuevo=new Nodo();
        nuevo.info=cancion;
        if (raiz==null) {
            nuevo.sig=nuevo;
            nuevo.ant=nuevo;            
            raiz=nuevo;
        } else {
            Nodo ultimo=raiz.ant;
            nuevo.sig=raiz;
            nuevo.ant=ultimo;
            raiz.ant=nuevo;
            ultimo.sig=nuevo;
        }
    }    
    
    public boolean vacia ()
    {
        if (this.raiz == null)
            return true;
        else
            return false;
    }
    
    public void imprimir ()
    {
        if (!vacia()) 
        {
            int i=1;
            Nodo reco=this.raiz;
            do {
                System.out.print (i+"-"+reco.info +"\n");
                reco = reco.sig;
                ++i;
            } while (reco!=this.raiz);
            System.out.println();
        }    
    }
    
    public void siguiente()
    {
        if (this.actual==null)
        {
            this.actual= this.raiz;
            this.actual= this.actual.sig;
        } 
        else
        {
            this.actual= this.actual.sig;
        }
    }
    
        public void anterior()
    {
        if (this.actual==null)
        {
            this.actual= this.raiz;
            this.actual= this.actual.ant;
        } 
        else
        {
            this.actual= this.actual.ant;
        }
    }
    
    public int cantidad ()
    {
        int cant = 0;
        if (!vacia()) {
            Nodo reco=this.raiz;
            do {
                cant++;
                reco = reco.sig;                
            } while (reco!=this.raiz);
        }    
        return cant;
    }
    
    public void borrar (int pos)
    {
        if (pos <= cantidad ())    {
            if (pos == 1) {
                if (cantidad()==1) {
                    raiz=null;
                } else {
                    Nodo ultimo=raiz.ant;    
                    raiz = raiz.sig;
                    ultimo.sig=raiz;
                    raiz.ant=ultimo;
                } 
            } else {
                Nodo reco = raiz;
                for (int f = 1 ; f <= pos - 1 ; f++)
                    reco = reco.sig;
                Nodo anterior = reco.ant;
                reco=reco.sig;
                anterior.sig=reco;
                reco.ant=anterior;
            }
        }
    } 
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
