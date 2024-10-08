package JDBC_Study;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class J05_CRUD {
    /*
    Cok miktarda kayit eklemek icin PreparedStatement metodu kullanilabilir.
    PreparedStatement hem hizli hem de daha guvenli (SQL injection saldirilari icin) bir yontemdir.
    ResultSet tum sonucları bellekte tutuyor.. banka hesapları vs hassas datalar için  ama guvenli degil..
    bu yuzden database in guvenligi acisindan PreparedStatement tercih edilir..
    Bunun icin; Data insert'e uygun bir POJO(Plain Old Java Object) class'i obj ile datalar db'ye insert edilir.

    PreparedStatement : Statement interface'ini extend eder.
    Statement ile olusturdugumuz sorgu icin Db'de bellekte sorgunun bir örnegi olusturulur.
    Sorgu her calistirildiginda yeni bir örnegi olusturulur.
    PreparedStatement : Birden fazla kez calistirilabilen parametrelendirilmis SQL sorgularini temsil eder.
    PreparedStatement ile sorgu olusturudumuzda, bu sorgunun örnegi DB'de bellekte tutulur,
    sorgu her çalıştırıldığında aynı önceki örneği kullanılır.

    Bir SORU :writing_hand:
-->Java JDBC de Statement ve PreparedStatement kullanımları  arasında ne fark var?
Bir CEVAP  :clipboard:
--> Statement: Statik SQL sorgularını çalıştırmak için kullanılır. Dinamik parametre geçişi yoktur.
 Bir sorgu çok defa çalıştırılmayacaksa kullanılması mantıklıdır.
--> PreparedStatement: Dinamik SQL sorgularını çalıştırmak için kullanılır.
Yani sql sorgularımıza parametre geçişi yapabiliriz. Bir sorgu çok defa çalıştırılcaksa kullanılması mantıklıdır.
Çünkü Statement nesnesi ile sorgu her çalıştırıldığında derlenirken PreparedStatement nesnesi sorguyu tek bir kez derler.
 */


    public static void main(String[] args) throws ClassNotFoundException, SQLException {


}
}
