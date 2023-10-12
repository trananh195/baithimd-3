package model;

public class Staff {
    private int id;
    private String name;
    private String mail;
    private int phone;
    private String address;
    private double salary;
    private int idDepartment;

    public Staff(int id, String name, String mail, String address, int phone, double salary, int idDepartment) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.address = address;
        this.salary = salary;
        this.idDepartment = idDepartment;
    }

    public Staff() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", idDepartment=" + idDepartment +
                '}';
    }
}
