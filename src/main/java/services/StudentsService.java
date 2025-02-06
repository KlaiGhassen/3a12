package services;

import models.Students;
import utils.MyDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentsService implements CrudInterface<Students> {
    private Connection connection;

    public StudentsService() {
        connection = MyDb.getInstance().getConnection();
    }

    @Override
    public void create(Students obj) throws Exception {
        String sql = "insert into students(firstName,lastName,age) values('" + obj.getFirstName() + "','" + obj.getLastName() + "','" + obj.getAge() + "')";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);

    }

    @Override
    public void update(Students obj) throws Exception {
        String sql = "update students set age = ?, firstName = ?, lastName = ? where id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, obj.getAge());
        pstmt.setString(2, obj.getFirstName());
        pstmt.setString(3, obj.getLastName());
        pstmt.setInt(4, obj.getId());
        pstmt.executeUpdate();
    }

    @Override
    public void delete(int obj) throws Exception {
        String sql = "delete from students where id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, obj);
        pstmt.executeUpdate();

    }

    @Override
    public List<Students> getAll() throws Exception {
        String sql = "select * from students";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Students> list = new ArrayList<>();
        while (rs.next()) {
            Students s = new Students();
            s.setId(rs.getInt(1));
            s.setFirstName(rs.getString("firstName"));
            s.setLastName(rs.getString("lastName"));
            s.setAge(rs.getInt("age"));
            list.add(s);
        }
        return list;
    }
}
