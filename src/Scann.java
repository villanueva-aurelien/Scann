package src;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class Scann
{
    private ArrayList<File[]> _listFile = new ArrayList<File[]>();
    private int _compteur = 0;
    private long _debutScann;
    private long _finScann;
    private long _debutWrite;
    private long _finWrite;
    private int cpt = 50;

    private String _name;
    private String _address;

    public Scann(String address, String name)
    {
        this._address = address;
        this._name = name;
    }

    public void runScann()
    {
        String address = _address;
        createFile(address);
        
        
        
        if(_listFile.size() < cpt);
        { 
            //checkList();    
        }

        choiceCallWritter(_listFile);

        
    }

    private void sortTab(File[] file)
    {
        _listFile.sort(file, new Comparator<>() {

            @Override
            public int compare(T o1, T o2)
            {

            }
            
        });
    }

    private void createFile(String address)
    {
        File file = new File(address);
        System.out.println("creation du file");
        if(file.exists())
        {
            System.out.println("appel ajout au tab");
            createTab(file);           
        }
    }

    private boolean checkFile(File[] tab)
    {
        if(tab == null || tab.length < 0)
        {
            return false;
        }
            
        return true;
    }

    private void createTab(File file)
    {
        File[] tabFile = file.listFiles();
        
        if(!checkFile(tabFile))
        {
            System.out.println("tab vide");
            return;
        }
        else
        {
            System.out.println("ajout au tab");
            addTabToList(tabFile);
        }
    }

    private void addTabToList(File[] tab)
    {
        System.out.println("ajout a la liste");
        _listFile.add(tab);
        _compteur++;
    }

    private void checkList()
    {
        System.out.println("checklist");
        for(File [] file : _listFile)
        {
            for(int i = 0; i < file.length; i++)
            {
                createFile(file[i].getName());
            }
        }
    }

    private void choiceCallWritter(ArrayList<File[]> list)
    {
        System.out.println("debut du write");
        
        for(File[] tab : list)
        {
            String text = "----------------------------"+tab[0].getParent()+"------------------------------------------";
            writterFile(_address, text);

            for(int i = 0; i < tab.length; i++)
            {
                if(tab[i].isDirectory())
                {
                    writterFile(_address, tab[i].getName());
                }
                if(tab[i].isFile())
                {
                    writterFile(_address, tab[i].length(), tab[i].getName());
                }
            }
        }
    }

    private void writterFile(String nameFile, long size, String name, String text)
    {
        File file = new File(_name);

        // créer le fichier s'il n'existe pas
        
        try
        {
            if (!file.exists()) 
                file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            if(size == -1 && text == null)
            {
                bw.write(name+"\n");
                bw.close();
            }
            if(text != null)
            {
                bw.write(text+"\n");
                bw.close();
            }
            if(size != -1)
            {
                name = "-- "+ name + "->" + size + " octet" +"\n";
                bw.write(name);
                bw.close();
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    private void writterFile(String file, long size, String nameFile)
    {
        this.writterFile(file, size, nameFile, null);
    }

    private void writterFile(String file, String nameFile, String text)
    {
        this.writterFile(file, -1, nameFile, text);
    }

    private void writterFile(String file, String nameFile)
    {
        this.writterFile(file, -1, nameFile, null);
    }
}
