public class Employee {
   public String name, id, designation;

   public Employee(){
       name = "";
       id = "";
       designation = "";
   }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
