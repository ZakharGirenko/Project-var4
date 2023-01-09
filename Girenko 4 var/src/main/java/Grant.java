import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Grant {

//    Название компании,Название улицы,Размер гранта,Фискальный год,Тип бизнеса,Количество рабочих мест
    private String companyName;
    private String streetName;
    private double money;
    private int year;
    private String businessType;
    private int jobsCount;

    public Grant(String companyName, String streetName, String money, String year, String businessType, String jobsCount) {
        this.companyName = companyName;
        this.streetName = streetName;
        setMoney(money);
        setYear(year);
        setJobsCount(jobsCount);
        this.businessType = businessType;
    }

    public void setMoney(String money) {
        money = money.replace("$","");
        money = money.replace(",","");
        this.money = Double.parseDouble(money);
    }

    public void setYear(String year) {
        this.year = Integer.parseInt(year);
    }

    public void setJobsCount(String jobsCount) {
        if(jobsCount.length()>0)
            this.jobsCount = Integer.parseInt(jobsCount);
        else
            this.jobsCount = -1;
    }

    public void safedb(PreparedStatement ps) throws SQLException {
        ps.setString(1, this.companyName);
        ps.setString(2, this.streetName);
        ps.setDouble(3, this.money);
        ps.setInt(4, this.year);
        ps.setString(5, this.businessType);
        ps.setInt(6, this.jobsCount);
        ps.executeUpdate();
    }
}
