package theradproject;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class ThreadCheckerClass implements Runnable {
    private ArrayList<Thread> threadClassArrayList; // Asal sayı kontrolu yapan threadlerin listesi
    private long value; // Asallığı kontrol edilen değer
    private SqlClass sqlClass; // sql işlemlerinde kullanılacak olan sınıf
    private JTextArea jTextArea;

    // Kurucu method
    public ThreadCheckerClass(ArrayList<Thread> threadClassArrayList, long value, JTextArea jTextArea) {
        this.threadClassArrayList = threadClassArrayList;
        this.sqlClass = SqlClass.getInstance();
        this.value = value;
        this.jTextArea = jTextArea;
    }

    @Override
    public void run() {
        // bayrak değeri olarak kullanılan boolean tipinde değişken
        // threadlerin çalışıp çalışmadığının kontrolünde kullanılıyor
        boolean isRunning = true;

        // eğer threadler çalışıyorsa
        while(isRunning) {
            // thread listesi içerisinde dolaşıyor
            for (int i=0; i< threadClassArrayList.size();i++) {
                // eğer sayı asal değil olarak belirlenmişse while döngüsü
                // içerisinden çıkılıyor
                if(!Const.isPrime) {
                    isRunning = false;
                    System.out.println( "Thread Checker const.isprime = false");
                    Const.isRunning = false;
                    
                    break;
                }

                // Threadler hala çalışıyormu kontrolü
                if( !threadClassArrayList.get(i).isAlive()){
                    threadClassArrayList.get(i).interrupt();
                    threadClassArrayList.remove(i); // çalışmayan thread listeden çıkarılıyor
                }

                // listede thread yoksa while döngüsünden çıkılıyor
                if (threadClassArrayList.isEmpty()){
                    System.out.println( "Thread Checker liste = 0");
                    isRunning = false;
                    Const.isRunning = false;
                    
                    break;
                }
            }
        }

        // Eğer asallığı aranan sayı asal ise veritabanına kaydediliyor
        if(Const.isPrime){
            sqlClass.insertToTable(value); // Value veritabanı tablosundan gelen en yuksek değerin 2 fazlası
            jTextArea.setText("Sayı Asal Sayıdır!");
            System.out.println("Asal Sayıdır");
        } else{
            jTextArea.setText("Sayı Asal Sayı değildir!\n"
                            + Const.threadName + " 'da bulunan\n"
                            + Const.dividerNumber + " sayısına tam \n"
                            + "olarak bölünmektedir");
            System.out.println("Asal sayı değildir");
        }

    }
}
