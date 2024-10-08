package JDBC_Project.TelRehberiProject;

public class Kayit {//pojo Class-> db'deki column record'lar için obj tanımayacak Class
    //1. fields-> db column
  private int id;//db için table'a ilgili column obj field olarak tanımlandı.
  private String  isim;//db için table'a ilgili column obj field olarak tanımlandı.
  private String  tel;//db için table'a ilgili column obj field olarak tanımlandı.
    //2. const.
    //p'siz Allah'ın sefil gundi default const..

    //3. getter-setter meth

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    //4. toString

    @Override
    public String toString() {
        return
                "id=" + id +
                ", isim='" + isim + '\'' +
                ", tel='" + tel ;
    }
}
