package odev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Odev extends Ogrenci {
	
	// Sabit Değişkenler \\
	static String database 						= "ogrenci.dat";
	static Scanner sc 							= new Scanner(System.in);
	static StringBuffer stringBufferOfData 		= new StringBuffer();
	static String filename 						= null;
	
	public static void main(String[] args) throws IOException 
	{
		File database	=	new File("ogrenci.dat");
		Scanner input 	= 	new Scanner(System.in);
        int son = 0;
        
        while(son < 1)
        {
			int secim = Integer.parseInt(JOptionPane.showInputDialog("Lutfen yapmak istediginiz islemi tuslayin:\n1: Yeni Ogrenci Ekle\n2: Kayıtları Oku\n3: Ogrenci Kaydi Duzenle\n0: Çıkış "));
			while(secim < 0 || secim > 3)
			{
				JOptionPane.showInputDialog("Yanlış giriş lütfen tekrar giriniz:\n1: Yeni Ogrenci Ekle\n2: Kayıtları Oku\n3: Ogrenci Kaydi Duzenle\n0: Çıkış ");
        	    secim = input.nextInt();
            }
             
			// YENI OGRENCI KAYIDI GIR \\
            if(secim == 1)
            {
            	
            	// Kullanıcıdan Bilgileri Alalım
            	String ogrenci_no			=	JOptionPane.showInputDialog("Öğrenci Numarası :");
            	boolean varmi = Kontrol(database,ogrenci_no);
            	while(varmi == false)
            	{
            		ogrenci_no				=	JOptionPane.showInputDialog("Bu numaralı öğrenci sistemde kayitlidir.\nLutfen tekrar giriniz:");
            		varmi = Kontrol(database,ogrenci_no);
            	}
            	String ogrenci_adsoyad		=	JOptionPane.showInputDialog("Ad ve Soyad : ");
            	String ogrenci_memleket		=	JOptionPane.showInputDialog("Memleket : ");

            	Ogrenci o = new Ogrenci();
            	o.setOgrenciId(ogrenci_no);
            	o.setAdi(ogrenci_adsoyad);
            	o.setMemleketi(ogrenci_memleket);
            	
            	// Dat dosyamıza yazdıralım
            	Ekle(o);
            	
            }
            else if(secim == 2)
            {
            	
            	// Öğrenci Kayıtlarını okuyalım
            	Oku();
            	
            }
            else if(secim == 3)
            {
            	
                RandomAccessFile yaz	=	new RandomAccessFile(database, "rw");
                String kayitgetir = "";
            	while (yaz.getFilePointer()<yaz.length()) {
            		kayitgetir += yaz.readLine();
            	}
            	String numara	=	JOptionPane.showInputDialog("Düzenlemek istediğiniz öğrencinin numarasını giriniz.");
            	boolean varmi2 = Kontrol(database,numara);
            	while(varmi2 == true)
            	{
            		numara				=	JOptionPane.showInputDialog("Bu numaralı öğrenci sistemde kayitli degildir.\nLutfen tekrar deneyiniz.");
            		varmi2 = Kontrol(database,numara);
            	}
            	Bul(kayitgetir);
            	int baslangic = kayitgetir.indexOf(numara);
        		int bitis = Uzunluk(kayitgetir, ".", kayitgetir.indexOf(numara));
        		
        		/*
        		 * kayitbul		: Dosyadaki tüm kayıdı bulur.
        		 * baslangic	: Nerden başlağını tutar.
        		 * bitis		: karakter uzunluğunu hesaplar
        		 * numara		: kullanıcıdan istenen numaradır
        		 * 
        		 */
        		String kayitbul;
        		kayitbul = (kayitgetir.substring(baslangic, bitis));
//        		System.out.println(kayitbul);
//        		System.out.println(baslangic);
//        		System.out.println(bitis);
//        		System.out.println(numara);
//        		System.out.println(Bul(kayitgetir));
//        		System.exit(0);
        		String d_ogrenci_no			=	JOptionPane.showInputDialog("Ogrencinin yeni Numarasını giriniz");
        		String d_ogrenci_adsoyad	=	JOptionPane.showInputDialog("Ogrencinin yeni Adını ve Soyadını giriniz.");
        		String d_ogrenci_memleket	=	JOptionPane.showInputDialog("Ogrencinin yeni Memleketini giriniz.");
        		
        		Ogrenci o = new Ogrenci();
        		o.setOgrenciId(d_ogrenci_no);
            	o.setAdi(d_ogrenci_adsoyad);
            	o.setMemleketi(d_ogrenci_memleket);
        		
        		Degistir(kayitbul,o);

            }
            else if(secim == 0)
            {
            	System.exit(0);
            }
            else
            {
            	JOptionPane.showInputDialog("Yanlış giriş lütfen tekrar giriniz :");
        	    secim = input.nextInt();
            }
            	
         }
            
            
            
      }
	
	// YENI OGRENCI KAYDETME METODU \\
	public static void Ekle(Ogrenci o) throws IOException 
	{
		File database	=	new File("ogrenci.dat");
        RandomAccessFile yaz	=	new RandomAccessFile(database, "rw");
        yaz.seek(database.length());
        yaz.writeBytes(o + ".");
	}
	
	// KAYITLI BİLGİLERİ OKUMA METODU \\
	public static void Oku() throws IOException
	{	
		String path = "ogrenci.dat";
	    FileReader file = new FileReader(path);
	    BufferedReader reader = new BufferedReader(file);
	    String satir;
	    String metin = "";
	    while((satir = reader.readLine()) != null){
	        metin += satir + "\n";
	    }
	    System.out.println(metin);
	}
	
	@SuppressWarnings("finally")
	private static boolean Bul(String b) 
	{
        Scanner DosyaOku = null;
       
       try {
    	   
    	   DosyaOku = new Scanner(new File(database));
           stringBufferOfData.append(b).append("\r\n");
           DosyaOku.close();
           return true;
           
       } catch (FileNotFoundException ex) {
           return false;
       } finally {
    	   DosyaOku.close();
           return true;
       }
       
   
   }
	
	// BİR KAYDIN KAÇ KARAKTER UZUNLUĞUNDA OLDUĞUNDA HESAPLAYAN METHOD \\
	public static int Uzunluk(String b, String l, int fIndex)
	{
	    int sIndex;
	    if (fIndex == -1)
	    {
	        sIndex = b.indexOf(l);
	    }
	    else
	    {
	        sIndex = b.indexOf(l, fIndex);
	    }
	    
	    return sIndex;
	}
	
	// DOSYADAKİ KAYIT DEĞİŞTİRME METHODU \\
	public static void Degistir(String g,Ogrenci o) 
	{
        
        String lineToEdit = g;
        
        String replacementText = (o.toString());
        
        int startIndex = stringBufferOfData.indexOf(lineToEdit);
        int endIndex = startIndex + lineToEdit.length();
        stringBufferOfData.replace(startIndex, endIndex, replacementText);
        //filename = sc.nextLine();
        Scanner DosyaOku = null;
        
        try {
            BufferedWriter bufwriter = new BufferedWriter(new FileWriter(database));
            bufwriter.write(stringBufferOfData.toString());
            bufwriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
	
	public static boolean Kontrol(File database, String numara) throws IOException
	{
		RandomAccessFile yaz	=	new RandomAccessFile(database, "rw");
        String kayitgetir = "";
    	while (yaz.getFilePointer()<yaz.length()) {
    		kayitgetir += yaz.readLine();
    	}
    	
    	int deger = kayitgetir.indexOf(numara);
    	if(deger < 0)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
	}
	
    
}