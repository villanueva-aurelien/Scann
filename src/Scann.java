package src;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Scann
{
    private ArrayList<File[]> _listFile = new ArrayList<File[]>();
    private int _compteur = 0;
    private long _debutScann;
    private long _finScann;
    private long _debutWrite;
    private long _finWrite;

    private String _name;
    private String _address;

    public Scann(String address, String name)
    {
        this._address = address;
        this._name = name;
    }

    public void runScann()
    {
        createFile();
        do
        {
            createFile();
        }
        while(_listFile.size() < _compteur);
        {
            checkList();
            choiceCallWritter(_listFile);
        }
    }

    private void createFile()
    {
        String address = _address;

        File file = new File(address);

        if(file.exists())
            createTab(file);

                
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
            return;
        else
            addTabToList(tabFile);
    }

    private void addTabToList(File[] tab)
    {
        _listFile.add(tab);
        _compteur++;
    }

    private void checkList()
    {
        choiceCallWritter(_listFile);
    }

    private void choiceCallWritter(ArrayList<File[]> list)
    {
        String text = "----------------------------file[0].getParent()------------------------------------------";
        for(File[] tab : list)
        {
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

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pr = new PrintWriter(bw, true);
            if(size == -1 && text == null)
            {
                pr.write(nameFile);
                pr.close();
            }
            if(text != null)
            {
                pr.write(text);
                pr.close();
            }
            if(size != -1)
            {
                nameFile = "-- "+ nameFile + "->" + size + " octet";
                pr.write(nameFile);
                pr.close();
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
