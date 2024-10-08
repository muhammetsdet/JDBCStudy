package JDBC_Study;

import java.sql.*;

public class J01_ReadData {
/*
Bir SORU : JDBC'de execute, execute Query ve execute Update arasındaki fark nedir?
Bir CEVAP  :
--> Execute(): Her türlü SQL Sorgusu için kullanılabilir.
--> ExecuteQuery() : sorguyu seçmek için kullanılabilir.
--> ExecuteUpdate(): tabloyu değiştirmek/güncellemek için kullanılabilir.
 */
    public static void main(String[] args) throws SQLException {

        System.out.println("   ***   task01   ***   ");
        // Task01-> talebeler table'daki record'ları listeleyen code create ediniz

        System.out.println("   ***   task02   ***   ");
        // Task02-> talebeler table'daki notları 90 dan yuksek olan record'ları listeleyen code create ediniz



        System.out.println("   ***   task03   ***   ");
        // Task03-> talebeler table'daki id değeri 124 olan record'ları listeleyen code create ediniz


        System.out.println("   ***   task04   ***   ");
        // Task04-> talebeler table'daki notu 70 ile 90 arasında  olan record'ları listeleyen code create ediniz.



        System.out.println("   ***   task05   ***   ");
        // Task05-> talebeler table'daki ismin 3. harfi l   olan record'ları listeleyen code create ediniz.


    }
}
