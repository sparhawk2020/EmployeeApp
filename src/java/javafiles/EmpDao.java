package javafiles;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



import javafiles.Emp;


@Service
@Component
public class EmpDao {

    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public int save(Emp p){
        String sql="insert into employee(id, name,salary,designation) values("+p.getId()+",'"+p.getName()+"',"+p.getSalary()+",'"+p.getDesignation()+"')";
        return template.update(sql);
    }
    public int update(Emp p, Integer oldid){
        String sql="update employee set name='"+p.getName()+"', id="+p.getId()+", salary="+p.getSalary()+",designation='"+p.getDesignation()+"' where id= " + oldid;
        return template.update(sql);
    }
    public int delete(int id){
        String sql="delete from employee where id="+id+"";
        return template.update(sql);
    }
    public Emp getEmpById(int id){
        String sql="select * from employee where id=?";
        return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Emp>(Emp.class));
    }
    public List<Emp> getEmployees(){
        return template.query("select * from employee",new RowMapper<Emp>(){
            public Emp mapRow(ResultSet rs, int row) throws SQLException {
                Emp e=new Emp();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setSalary(rs.getFloat(3));
                e.setDesignation(rs.getString(4));
                return e;
            }
        });
    }
}
