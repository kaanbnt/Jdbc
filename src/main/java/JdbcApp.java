import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcApp {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        VeriTabaniIslem veriTabaniIslem=new VeriTabaniIslem();
        MenuTasarimi menuTasarimi=new MenuTasarimi();
        boolean cikis=false;
        boolean isBaglantiHazir = veriTabaniIslem.baglantiyiKontrolEt();
        if (!isBaglantiHazir) {
            System.out.println("Bağlantı problemi var. Lütfen kontrol edin.");
        } else {
            System.out.println("bağlantı Çalıştı");
            menuTasarimi.menuYazdir();
            while (!cikis) {
                System.out.println("Hangi İşlemi Yapmak İstiyorsunuz: ");
                int secimMenu=scanner.nextInt();
                switch (secimMenu) {
                    case 1:
                        karakterEkle(scanner, veriTabaniIslem);
                        break;
                    case 2:
                        filmEkle(scanner, veriTabaniIslem);
                        break;
                    case 3:
                        veriTabaniIslem.karakterFilmButce();
                        break;
                    case 4:
                        veriTabaniIslem.karakterFilmSayisi();
                        break;
                    case 5:
                        cikis=true;
                        break;
                }
            }
        }
    }

    private static void filmEkle(Scanner scanner, VeriTabaniIslem veriTabaniIslem) {
        veriTabaniIslem.karakterListele();
        System.out.println("Karakterin ID: ");
        int karakterId=scanner.nextInt();
        System.out.println("Film Adı: ");
        String filmAd=scanner.next();
        System.out.println("Bütçesi: ");
        int filmButce=scanner.nextInt();
        Movie movie=new Movie();
        movie.setHero_id(karakterId);
        movie.setFilm(filmAd);
        movie.setButce(filmButce);
        veriTabaniIslem.filmEkle(movie);
    }

    private static void karakterEkle(Scanner scanner, VeriTabaniIslem veriTabaniIslem) {
        System.out.println("Karakterin Adı: ");
        String karakterAd=scanner.next();
        System.out.println("Karakterin Soyadı: ");
        String karakterSoyad=scanner.next();
        Hero hero=new Hero();
        hero.setAdi(karakterAd);
        hero.setSoyadi(karakterSoyad);
        veriTabaniIslem.karakterEkle(hero);
    }
}
