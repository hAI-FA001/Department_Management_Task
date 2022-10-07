public class Employee {
    String name, id, designation;

    public Employee(String name, String id, String designation) {
        this.name = name;
        this.id = id;
        this.designation = designation;
    }

    public Employee(Employee e){
        this.name = e.name;
        this.id = e.id;
        this.designation = e.designation;
    }

    @Override
    public String toString(){
        return String.format("%-10s\n%-10s\t%20s", id, name, designation);
    }

    @Override
    public Object clone(){
        return new Employee(this);
    }

    @Override
    public boolean equals(Object o){
        Employee tmp = (Employee) o;
        return tmp.id.equals(this.id);
    }
}
