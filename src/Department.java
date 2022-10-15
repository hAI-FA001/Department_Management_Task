import java.lang.reflect.Field;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Department {
    private String name;
    private Employee HOD, inCharge;
    private Lab[] labs;

    private boolean hasFreeLabSlot = false;
    private int numFreeLabSlot = 0;

    public Department(){
        name = "";
        HOD = new Employee();
        inCharge = new Employee();
        labs = null;
    }

    public Department(String name, Employee HOD, Employee inCharge, Lab[] labs) {
        this.name = name;
        this.HOD = HOD;
        this.inCharge = inCharge;
        this.labs = labs;
    }

    public Department(Department d){
        this.name = d.name;
        this.HOD = (Employee) d.HOD.clone();
        this.inCharge = (Employee) d.inCharge.clone();

        this.labs = (d.labs != null)? new Lab[d.labs.length] : null;

        if(d.labs != null)
            for(int i=0; i<d.labs.length; i++)
                this.labs[i] = (Lab) d.labs[i].clone();

        this.hasFreeLabSlot = d.hasFreeLabSlot;
        this.numFreeLabSlot = d.numFreeLabSlot;
    }

    public void removeLab(int labNo){

        if(labs[labNo] == null){
            System.out.println("That lab has been removed already.");
            return;
        }

        for(int i=labNo; i<labs.length; i++){
            int j = i+1;

            if(labs[i] == null){
                break;
            }

            if(j == labs.length){
                labs[i] = null;
                hasFreeLabSlot = true;
                numFreeLabSlot++;
                break;
            }

            labs[i] = labs[j];
        }

        cleanLabsList();
    }

    public void cleanLabsList(){

        if(!hasFreeLabSlot){
            System.out.println("Could not find any labs");
            return;
        }

        if(this.labs.length-numFreeLabSlot == 0)
        {
            this.labs = null;
            return;
        }

        Lab[] labs = new Lab[this.labs.length-numFreeLabSlot];

        for(int i=0, j=0; j<this.labs.length; j++){
            if(this.labs[j] != null)
            {
                labs[i] = this.labs[j];
                i++;
            }
        }

        hasFreeLabSlot = false;
        numFreeLabSlot = 0;

        this.labs = labs;
    }

    public void showAddDialogs(){
        Scanner s = new Scanner(System.in);

        System.out.print("How many labs do u want to add?\t");
        Lab[] labs = new Lab[s.nextInt()];

        for (int i = 0; i < labs.length; i++) {
            System.out.print("Enter the lab's name: ");
            String labName = DepartmentList.getNonNewLine();

            System.out.println("Enter lab attendants info as prompted.");
            System.out.print("Name: ");
            String empName = DepartmentList.getNonNewLine();
            System.out.print("ID: ");
            String empID = DepartmentList.getNonNewLine();
            System.out.print("Designation: ");
            String empDesignation = DepartmentList.getNonNewLine();

            labs[i] = new Lab(labName, null, new Employee(empName, empID, empDesignation));

            labs[i].showAddDialogs(true, true);
        }

        for (Lab lab : labs) this.addLab(lab);
    }
    
    public void addLab(Lab l){

        if(l == null)
            return;

        if(this.labs == null)
        {
            this.labs = new Lab[]{l};
            return;
        }

        Lab[] labs = new Lab[this.labs.length+1];

        for(int i=0; i<this.labs.length; i++){
            labs[i] = (Lab) this.labs[i].clone();
        }
        labs[this.labs.length] = l;
        
        this.labs = labs;
    }
    
    @Override
    public String toString(){
        StringBuilder out = new StringBuilder(String.format("""

                =======================
                %-10s
                =======================
                
                HOD Info
                ------------
                %s
                
                Incharge Info
                ------------
                %s""", name, HOD, inCharge));

        if(labs != null) {
            out.append("\n\nLabs Info");

            for (Lab lab : labs)
                if (lab != null)
                    out.append("\n").append(lab);
        }

        return out.toString();
    }

    @Override
    public boolean equals(Object o){
        if(o == null || o.getClass() != Department.class)
            return false;

        Department d = (Department) o;

        return this.name.equals(d.name);
    }

    @Override
    public Object clone(){
        return new Department(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getHOD() {
        return HOD;
    }

    public void setHOD(Employee HOD) {
        this.HOD = HOD;
    }

    public Employee getInCharge() {
        return inCharge;
    }

    public void setInCharge(Employee inCharge) {
        this.inCharge = inCharge;
    }

    public Lab[] getLabs() {
        return labs;
    }

    public void setLabs(Lab[] labs) {
        this.labs = labs;
    }

    public boolean HasFreeLabSlot() {
        return hasFreeLabSlot;
    }

    public void setHasFreeLabSlot(boolean hasFreeLabSlot) {
        this.hasFreeLabSlot = hasFreeLabSlot;
    }

    public int getNumFreeLabSlot() {
        return numFreeLabSlot;
    }

    public void setNumFreeLabSlot(int numFreeLabSlot) {
        this.numFreeLabSlot = numFreeLabSlot;
    }

    public void changeLabInfo(int labNo){
        Field[] fields = labs[labNo].getClass().getDeclaredFields();

        System.out.println("Enter -1 to keep current values.");
        for(int i=0; i < fields.length; i++){
            if(fields[i].toString().contains("name")) {
                System.out.print("Enter lab's name: ");
                String input = DepartmentList.getNonNewLine();

                if(!input.equals("-1"))
                    labs[labNo].setName(input);
            }

            if(fields[i].toString().contains("Employee")) {
                Field[] empFields = Employee.class.getDeclaredFields();
                for (Field field : empFields) {
                    System.out.print("Enter " +
                            fields[i].toString().toLowerCase().substring(
                            fields[i].toString().lastIndexOf('.')+1) + "'s "+
                            field.toString().substring(field.toString().lastIndexOf('.')+1)
                            +": ");

                    String input = DepartmentList.getNonNewLine();
                    if(input.equals("-1"))
                        continue;

                    if(fields[i].toString().toLowerCase().contains("attendant")) {
                        if (field.toString().toLowerCase().contains("name"))
                            labs[labNo].getAttendant().setName(input);
                        else if (field.toString().toLowerCase().contains("id"))
                            labs[labNo].getAttendant().setId(input);
                        else if (field.toString().toLowerCase().contains("design"))
                            labs[labNo].getAttendant().setDesignation(input);
                    }
                }
            }
        }
    }

    public int searchLab(Lab lab){
        if(this.labs == null)
            return -1;

        for(int i=0; i < labs.length; i++)
            if(labs[i] != null)
                if(labs[i].equals(lab))
                    return i;

        return -1;
    }

    public String csvFormat(boolean addFieldHeaders){
        StringBuilder out = new StringBuilder();

        if(addFieldHeaders)
        {
            out.append("name,hod,incharge\n");
        }

        out.append(getName().replace(",", " ")).append(",")
                .append(getHOD().getName().replace(",", " ")).append(",").
                append(getInCharge().getName().replace(",", " ")).append("\n");


        return out.toString();
    }
}
