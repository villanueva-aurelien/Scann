package src;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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

    /**
     * Constructor need path in String and name file .txt
     * to write this scann
     * @param address
     * @param name
     */
    public Scann(String address, String name)
    {
        this._address = address;
        this._name = name;
    }

    /**
     * This methode launch Scann
     */
    public void runScann()
    {
        _debutScann = System.currentTimeMillis();                   // time to start scann
        String address = _address;
        createFile(address);
        
        
        checkList();
        _finScann = System.currentTimeMillis();                     // time to end scann

        _debutWrite = System.currentTimeMillis();                   // time to start write

        choiceCallWritter(_listFile);

        _finWrite = System.currentTimeMillis();                     // time to end write

        displayTime();
    }

    /**
     * this methode calculate the elapsed time 
     */
    private void displayTime()
    {
        long st = (_finScann - _debutScann)/1000;
        long wt = (_finWrite - _debutWrite)/1000;

        System.out.println("Temps du scann en secondes : "+st);
        System.out.println("Temps d'ecriture en secondes : "+wt);

    }

    /**
     * this method sort File file at directory first and file last
     * @param file
     */
    private void sortTab(File[] file)
    {
        
        Arrays.sort(file, new Comparator<File>()
        {
            @Override
            public int compare(File file1, File file2)
            {
                if(file1.isDirectory() && file2.isDirectory())
                    return 0;
                if(file1.isDirectory() && file2.isFile())
                    return -1;
                if(file1.isFile() && file2.isDirectory())
                    return 1;
                if(file1.isFile() && file2.isFile())
                    return 0;
                
                return 0;
               
                
            }
            
        });
    }

    /**
     * This methode create File file, need String path
     * Check several options
     * Call another methode createTab()
     * @param address
     */
    private void createFile(String address)
    {
        File file = new File(address);
        
        if(file.exists() || file.canRead() || !file.isHidden())
        {
            createTab(file);           
        }
    }

    /**
     * This method check this Array is null or 0 in size
     * @param tab
     * @return
     */
    private boolean checkFile(File[] tab)
    {
        if(tab == null || tab.length == 0)
        {
            return false;
        }
            
        return true;
    }

    /**
     * This method create Array of type File
     * Check this Array
     * Call method sortTab() and addTabToList()
     * @param file
     */
    private void createTab(File file)
    {
        File[] tabFile = file.listFiles();
        
        if(!checkFile(tabFile))
        {
            return;
        }
        else
        {
            sortTab(tabFile);
            addTabToList(tabFile);
        }
    }

    /**
     * This method add Array type File 
     * And increment an int by 1
     * @param tab
     */
    private void addTabToList(File[] tab)
    {
        _listFile.add(tab);
        _compteur++;
    }

    /**
     * This method browse Array type File in ArrayList<File[]>
     * And call createFile() with name of file
     */
    private void checkList()
    {
        for(int j = 0; j < _listFile.size(); j++)
        {
            for(int i = 0; i < _listFile.get(j).length; i++)
            {
                createFile(_listFile.get(j)[i].getPath());
            }
        }
    }

    /**
     * This method write in file.txt this scann
     * @param list
     */
    private void choiceCallWritter(ArrayList<File[]> list)
    {        
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

    /**
     * This method writes in file.txt the contents of the array
     * @param nameFile
     * @param size
     * @param name
     * @param text
     */
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
