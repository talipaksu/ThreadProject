package theradproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by TalCom on 13.12.2015.
 */
public class SqlClass {

    Connection connection = null; // Sql bağlantısı için gerekli olan nesne
    Statement statement = null; // Sorgu ifadelerinin yazıldığı nesne

    // Singleton yapı ile oluşturulmuş nesne
    // Bu sayede sql nesnemizden programın tamamında bir tane oluşturmuş oluyoruz
    // Ve veritabanına yazma okuma işlemlerinde hatayı aza indirmiş oluyoruz
    private static SqlClass instance = new SqlClass();

    // sql sınıfından bir nesne oluşturulmak istenildiği zaman SqlClass nesne = new SqlClass() yapamayız
    // Çünkü kurucu method private tanımlanmıştır. Bu nesneyi kullanmamız gerektiğinde zaten sistem
    // tarafından oluşturulmuş olan nesneyi kullanmamız gereklidir.
    // SqlClass nesne = SqlClass.getInstance(); ifadesi ile kullanabiliriz.
    // İfadenin static olması ise her yerden nesne oluşturulmadan çağırılabilmesini sağlar.
    public static SqlClass getInstance() {
        return instance;
    }

    // Singleton yapısı için kurucu fonksiyon private tanımlanmıştır. Bu sınıf içerisi dışında
    // çağırılamaz. getInstance metodu kullanılmalıdır.
    private SqlClass() {

    }

    // Sql tablosuna bağlantı yapılıyor daha sonra bu bağlantı kullanılıyor
    public void ConnectToTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Veritabanı bağlantısı başarı ile gerçekleşti.");

        } catch (Exception e) {
            System.out.println("Veritabanı bağlantısı yapılırken bir sorun oluştu.");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    // Tabloya bulunan asal sayının eklenmesi işlemi yapılıyor
    public boolean insertToTable(long value) {

        try {
            statement = connection.createStatement();

            String sql = "INSERT INTO prime_numbers(primes) VALUES (" + value + ")";

            statement.executeUpdate(sql);
            statement.close();

            System.out.println("Tabloya veri eklenmesi başarı ile gerçekleşti.");
            return true;

        } catch (Exception e) {
            System.out.println("Tabloya veri eklenmesi sırasında bir sorun oluştu.");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

    }

    // Tabloda kaç asal sayının olduğu hesaplanıyor
    // tavan değere kadar kaç değer olduğuna bakılıyor
    public int primeCount(long ceilValue) {
        int count; // tablodaki veri sayısı

        try {
            statement = connection.createStatement();

            // count ile tablodaki veriler sayılıyor. Ve total olarak tutuluyor
            ResultSet rs = statement.executeQuery("SELECT COUNT(primes) AS total FROM prime_numbers WHERE primes <="+ ceilValue);
            count = rs.getInt("total"); // totalda tutulan sayı count a atanıyor

            rs.close();
            statement.close();

            System.out.println("Sayma işlemi tamamlandı count değeri :" + count);

            return count;

        } catch (Exception e) {
            System.out.println("Sayma işlemi sırasında bir hata oluştu.");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return 0;
        }
    }

    // Tablodaki veriler içerisinden en büyük değer getiriliyor
    public long getMaxFromTable() {
        long data;

        try {
            statement = connection.createStatement();
            // max ile tablodaki en büyük değer bulunup maxvalue olarak tutuluyor
            ResultSet rs = statement.executeQuery("SELECT MAX(primes) AS MaxValue FROM prime_numbers;");
            data = rs.getLong("MaxValue"); // maxvalue içerisinde tutulan en büyük değer data ya atanıyor
            rs.close();
            statement.close();

            System.out.println("en büyük değer bulundu max değer: " + data);

            return data;

        } catch (Exception e) {
            System.out.println("en büyük değer bulunurken bir hata oluştu");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return 0;
        }
    }
    
    // En son kalınan sayıyı getiriyor
    public long getLastFromTable() {
        long data;

        try {
            statement = connection.createStatement();
            // max ile tablodaki en büyük değer bulunup maxvalue olarak tutuluyor
            ResultSet rs = statement.executeQuery("SELECT value FROM last_check;");
            data = rs.getLong("value"); // maxvalue içerisinde tutulan en büyük değer data ya atanıyor
            rs.close();
            statement.close();

            System.out.println("en son değer getirildi son değer: " + data);

            return data;

        } catch (Exception e) {
            System.out.println("en son değer bulunurken bir hata oluştu");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return 0;
        }
    }

    // Tablodaki bütün değerlerin sıralı bir şekilde getirilmesi
    public ArrayList<Long> getDataFromTable(long ceilValue) {
        long data;
        // tablodan çekilen verilen tutulacağı liste
        ArrayList<Long> dataList = new ArrayList<>();

        try {
            statement = connection.createStatement();
            // küçükten büyüğe doğru sıralı bir şekilde verileri çekiyor
            ResultSet rs = statement.executeQuery("SELECT * FROM prime_numbers WHERE primes <= "+ ceilValue +" ORDER BY primes ASC;");

            // eğer rs içinde hala veri varsa
            while (rs.next()) {
                // primes sütunundaki veriyi getiriyor
                data = rs.getLong("primes");
                dataList.add(data); // listeye atıyor
                System.out.println("Data : " + data);
            }
            rs.close();
            statement.close();
            System.out.println("sıralı bir şekilde data list çekildi");
            return dataList;

        } catch (Exception e) {
            System.out.println("data listesi alınırken bir sorun oluştu");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }

    }

    // açılan bağlantının kapatılması
    public void closeConnection() {
        try {
            connection.close();
            System.out.println("Bağlantı başarı ile kapatıldı");

        } catch (Exception e) {
            System.out.println("Bağlantı kapatılamadı");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }


}

