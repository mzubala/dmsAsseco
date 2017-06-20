package pl.com.bottega.dms.model;

import javax.persistence.*;

@Entity
public class User extends BaseEntity {

    private String login;
    private String password;

    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    private Employee employee;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
