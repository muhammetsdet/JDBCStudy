package JDBC_Project.TelRehberiProject;

import java.sql.*;
import java.util.ArrayList;

public class Utilities {//DB action alan JDBC meth Class
    Connection connection = null; // *** sql query db'ye göndermek içinDB bağlantısı yapacak obj. create edildi.
    Statement statement = null; // *** sql query 'lerini result'unu return edecek obj. create edildi
    PreparedStatement pStatement = null; // *** p'li statement obj create edildi ->guven ve hız avantajı için

    private void dBaseConnect() {// mysql connection-> mysql'e bağlanan meth..
        String url = "jdbc:mysql://localhost:3306/";// mysql bağlantı  adresi(path) tanımlandı
        String username = "root";// mysql bağlantı için username   tanımlandı
        String password = "12345678";// mysql bağlantı için password   tanımlandı

        try {
            connection = DriverManager.getConnection(url, username, password);//*** mysql bağlanacak obj parametreleri girilerek bağlantı yapıldı
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    private void useTelefonRehberi() {// mysql bağlantısından sonra db(schema)'ya bağlanan meth.->use javacan;
        String sql_query = "Use telefonrehberi;";//*** mysql bağlantısındaki ilgili db ye bağlanması içn sql guery tanımlandı

        try {
            statement = connection.createStatement();//mysql bağlantısındaki ilgili Db için statement atandı
            statement.executeUpdate(sql_query);//*** mysql'deki db'e bağlanması için sql query execute edildi(şimşek)
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    private void dBaseClose() {// db bağlantısını kapatan meth.
        try {
            if (connection != null) {// bağlantı açık kontrolu

                connection.close();//bağlantı kapatıldı..
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }

    private void createDBase() {// schema(DB) ve table oluşturan meth. -> CREATE TABLE, CREATE DATABASE
        dBaseConnect();// mysql connection-> mysql baglanan meth. call

        try {
            String sql_query = "CREATE DATABASE if  not exists telefonrehberi";// mysql'deki db create  için sql query tanımlandı
            statement = connection.createStatement();//mysql bağlantısındaki ilgili Db için statement atandı
            statement.executeUpdate(sql_query);//mysql bağlantısında db  create eden sql query calıştırldı->şimşek
            useTelefonRehberi();// mysql'de db(schema)'ye baglanan meth.(USE javacan) call...
            sql_query = "CREATE TABLE if not exists tel_nolar" +//ilgili db(teleforehberi) table cretae edecek query tanımlandı
                    "(id int not null primary key auto_increment," +
                    "isim varchar(45)," +
                    "tel varchar(20));";
            statement.executeUpdate(sql_query);//mysql bağlantısında  table create sql query calıştırldı->şimşek

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dBaseClose();// gubvenlik için db kapatan meth  call
        }

    }
    public ArrayList<Kayit> listData() {// table'daki tum column record'ları return eden meth. -> SELECT
        ArrayList<Kayit> liste = new ArrayList<Kayit>();//select'tn gelen record'ları atanacagı boş liste
        createDBase();//DB yoksa create edecek varsa etmeyecek .listelenecek DB önce create edilmeli.
        // schema ve table oluşturan meth. -> CREATE TABLE, CREATE DATABASE meth call...
        dBaseConnect();// mysql connection-> mysql baglanan meth. call
        useTelefonRehberi();// mysql'de db(schema)'ye baglanan meth.(USE javacan) call...
        ResultSet resultSet = null;//*** sql query sonuçlarını return edecek obj. create edildi.
        String sql_query = "SELECT * FROM tel_nolar ;";// mysql'deki db'deki ilgili table ve column'a record listelemek (read) sql query tanımlandı
        try {
            resultSet = statement.executeQuery(sql_query);// mysql'deki db'deki ilgili table ve column'a record listelemek (read) sql query run edildi(şimşek)
            while (resultSet.next()) {//iterator loop ile datalar listelendi
                Kayit k = new Kayit();//kayıt class yeni bir obj
                k.setId(resultSet.getInt(1));//iterator ie db den gelen result recodrlar obj'de ilgili ins. variable atandı
                k.setIsim(resultSet.getString(2));//iterator ie db den gelen result recodrlar obj'de ilgili ins. variable atandı
                k.setTel(resultSet.getString(3));//iterator ie db den gelen result recodrlar obj'de ilgili ins. variable atandı
                liste.add(k);//itera tor ie db den gelen result recodrlar obj liste eklendi
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            dBaseClose();//mysql  bağlantısı kapatıldı
        }
        return liste;
    }
    public void addData(Kayit kayitObj) {// table'ye record(data) insertion(giriş ekleme) eden meth. -> INSERT  INTO
        dBaseConnect();// mysql connection-> mysql baglanan meth. call
        useTelefonRehberi();// mysql'de db(schema)'ye baglanan meth.(USE javacan) call...
        String sql_query = "insert into tel_nolar(isim,tel) values(?,?);";// mysql'deki db'deki ilgili table ve column'a record girişi için sql query tanımlandı
        try {
            pStatement = connection.prepareStatement(sql_query);
            pStatement.setString(1, kayitObj.getIsim());//table'da isim column'a kayıtObj isim variable record olarak insert yapıldı
            pStatement.setString(2, kayitObj.getTel());//table'da tel column'a kayıtObj isim variable record olarak insert yapıldı
            useTelefonRehberi();// mysql'de db(schema)'ye baglanan meth.(USE javacan) call...
            pStatement.executeUpdate();// mysql'deki db'deki ilgili table ve column'a record için sql query  execute edildi(şimşek)

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            dBaseClose();//mysql  bağlantısı kapatıldı
        }

    }

    public void updateData(Kayit kayitObj) {// table'de ilgili column'dan record update eden meth.-> UPDATE SET
        dBaseConnect();// mysql connection-> mysql baglanan meth. call
        useTelefonRehberi();// mysql'de db(schema)'ye baglanan meth.(USE javacan) call...
        String sql_query = "UPDATE tel_nolar SET isim= ?,tel= ? WHERE id= ?;";// mysql'deki db'deki ilgili table ve column'a record update için sql query tanımlandı
        try {
            pStatement = connection.prepareStatement(sql_query);
            pStatement.setString(1, kayitObj.getIsim());//table'da isim column'a kayıtObj isim variable record olarak insert yapıldı
            pStatement.setString(2, kayitObj.getTel());//table'da tel column'a kayıtObj tel variable record olarak insert yapıldı
            pStatement.setInt(3, kayitObj.getId());//table'da id column'a kayıtObj id variable record olarak insert yapıldı

            pStatement.executeUpdate();// mysql'deki db'deki ilgili table ve column'a record update için sql query execute edildi(şimşek)

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            dBaseClose();//mysql  bağlantısı kapatıldı
        }


    }

    public void deleteData(int id) {// table'de ilgili column'dan record delet eden meth.-> DELETE FROM TABLE
        dBaseConnect();// mysql connection-> mysql baglanan meth. call
        useTelefonRehberi();// mysql'de db(schema)'ye baglanan meth.(USE javacan) call...
        String sql_query = "DELETE FROM tel_nolar  WHERE id=?;";// mysql'deki db'deki ilgili table ve column'a record update için sql query tanımlandı
        try {
            pStatement = connection.prepareStatement(sql_query);
            pStatement.setInt(1, id);//table'da delete edilecek id için query'ye set edildi
            pStatement.executeUpdate();// mysql'deki db'deki ilgili table ve column'a record silmek için sql query execute edildi(şimşek)

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            dBaseClose();//mysql  bağlantısı kapatıldı
        }


    }



    public int getLastIndex() {
        int lastID = 0;
        dBaseConnect();// mysql connection-> mysql baglanan meth. call
        useTelefonRehberi();// mysql'de db(schema)'ye baglanan meth.(USE javacan) call...
        try {
            ResultSet resultSet = statement.executeQuery("SELECT id FROM tel_nolar ORDER BY id DESC LIMIT 1;");// mysql'deki db'deki ilgili table ve column'a son id record listelemek için sql query execute edldi(şimşek)
            if (resultSet.next()) {
                lastID = resultSet.getInt(1);
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            dBaseClose();// useTelefonRehberi(); meth tersine db bağlatısı kesildi
        }
        return lastID;
    }

}

