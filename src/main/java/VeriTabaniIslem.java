import java.sql.*;

public class VeriTabaniIslem {

    final static String JDBC_CONNECTION_STR = "jdbc:mysql://127.0.0.1:3306/sinav?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
    ;
    final static String USERNAME = "root";
    final static String PASSWORD = "123123123";

    public static boolean baglantiyiKontrolEt() {
        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD)) {
            if (conn != null) {
                return true;
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void karakterEkle(Hero hero) {

        String sql = "insert into hero (name, username) " +
                "values (?, ?) ";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, hero.getAdi());
            preparedStatement.setString(2, hero.getSoyadi());

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " satır eklendi.");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void filmEkle(Movie movie){
        String sql = "insert into movie (hero_id, movie, budget) " +
                "values (?, ?, ?) ";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, movie.getHero_id());
            preparedStatement.setString(2, movie.getFilm());
            preparedStatement.setInt(3, movie.getButce());

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " satır eklendi.");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void karakterFilmButce(){
        String sql = "select \n" +
                "\tconcat(name,' ',username) as HERO , \n" +
                "    ifnull(SUM(M.BUDGET),0) as TOTAL_BUDGET\n" +
                "    from hero h left join movie m on h.id=m.hero_id group by NAME ORDER BY SUM(M.BUDGET) DESC;\n" +
                "\n";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String adi = resultSet.getString("Hero");
                int budget = resultSet.getInt("Total_Budget");
                System.out.printf("%s - %s\n", adi, budget);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void karakterFilmSayisi(){
        String sql="select \n" +
                "\tconcat(name,' ',username) as HERO , \n" +
                "\tcount(m.hero_id)\n" +
                "\tfrom hero h inner join movie m on h.id=m.hero_id group by name";
        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String adi = resultSet.getString("Hero");
                int sayac= resultSet.getInt("Count(m.hero_id)");
                System.out.printf("%s - %s\n", adi , sayac );
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void karakterListele() {
        String sql = "Select * from hero";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String adi = resultSet.getString("name");
                String soyadi = resultSet.getString("username");

                System.out.printf("%d - %s - %s \n", id, adi, soyadi);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}