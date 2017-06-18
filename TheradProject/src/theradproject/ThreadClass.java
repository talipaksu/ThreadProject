package theradproject;

import java.awt.Color;
import java.util.ArrayList;

public class ThreadClass implements Runnable {

    private long value; // Asallığı araştılacak sayı
    private String name; // Thread in adı
    private int threadNo;

    private ArrayList<Long> dataList; // Asallık kontolunde kullanılacak sayılar
    private boolean isPrime; // asal olup olmadığı

    private GuiElements guiElements;

    // Kurucu method
    public ThreadClass(String name, int threadNo, ArrayList<Long> dataList, long value, GuiElements guiElements) {
        this.name = name;
        this.threadNo = threadNo;
        this.dataList = dataList;
        this.value = value;
        this.isPrime = true;
        this.guiElements = guiElements;
    }

    @Override
    public void run() {
        // Asallığı kontrol edilecek olan sayının kare kökü alınıyor
        long sqrt = (long) Math.sqrt(value);

        // Asallık kontrolu yapılıyor
        for (long i = 0; i < dataList.size(); i++) {

            System.out.println("Thread Run : data " + dataList.get((int) i));

            // eğer threadlerden biri asallığı bozdu ise
            // bütün threadlerden çıkış yapılıyor
            if (!Const.isPrime) {
                System.out.println("Prime değil + " + name);

            } else {

                System.out.println("Thread " + name + "  " + value + " % " + dataList.get((int) i));

                // karekökü ile asal sayının modu 0 ise asal değildir
                if (((double) value) % dataList.get((int) i) == 0) {
                    if (Const.isPrime) {
                        Const.isPrime = false;
                        Const.dividerNumber = dataList.get((int) i);
                        Const.threadName = name;
                        guiElements.getDurumLabel().setText("False");
                        guiElements.getDurumLabel().setBackground(Color.red);
                        System.out.println("if Thread " + name + " asal değil i: " + dataList.get((int) i) + " isPrime = false");
                        break;
                    }
                    break;
                }
            }

        }

        // eğer asal değilse diğer threadlerin çıkışı
        // için de Const daki değer false yapılıyor
        // if(!isPrime){
        //    System.out.println("isPrime = false if Thread " + name);
        //    Const.isPrime = false;
        // }
    }
}
