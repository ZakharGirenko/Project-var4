import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;

public class Start {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Database.create(getGrants());
//      Excel file
//        var graph = Database.executeQuery("SELECT year, AVG(jobscount) as avgJobs FROM Grants WHERE jobscount >=0 GROUP BY year;");
//        while(graph.next()){
//            System.out.println(graph.getString("year")+" "+graph.getString("avgJobs"));
//        }
        System.out.println(Database.executeQuery("SELECT AVG(money) as avgMoney FROM Grants WHERE businesstype = \"Salon/Barbershop\";").getString("avgMoney"));
        System.out.println(Database.executeQuery("SELECT businesstype,MAX(jobscount) FROM Grants WHERE money < 55000;").getString("businesstype"));
    }

    private static ArrayList<Grant> getGrants() throws IOException {
        var fileName = "src/main/resources/grants.csv";

        var list = new ArrayList<Grant>();

        try (var fr = new FileReader(fileName, StandardCharsets.UTF_8);
             var reader = new CSVReader(fr)) {
            reader.readNext();
            var nextLine = reader.readNext();
            while (nextLine != null) {
                list.add(new Grant(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5]));
                nextLine = reader.readNext();
            }
        }
        return list;
    }
}
