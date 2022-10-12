import java.util.Scanner;

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
        Lab labs[] = new Lab[s.nextInt()];

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

            System.out.print("How many systems do u want to add for Lab no." + (i + 1) + " ?\t");
            PCSystem systems[] = new PCSystem[s.nextInt()];

            System.out.println("Enter the required info as prompted.");
            for (int j = 0; j < systems.length; j++) {
                System.out.print("\nSystem ID: ");
                String sysID = DepartmentList.getNonNewLine();
                System.out.print("\nSystem Model: ");
                String sysModel = DepartmentList.getNonNewLine();
                System.out.print("\nLCD Name: ");
                String LCDName = DepartmentList.getNonNewLine();
                System.out.print("\nRAM Size (MB): ");
                int RAMSz = s.nextInt();
                System.out.print("\nDisk Size (GB): ");
                int diskSz = s.nextInt();
                System.out.print("\nHas GPU? (y/n) ");
                String hasGPU = DepartmentList.getNonNewLine();

                systems[j] = new PCSystem(sysID, sysModel, LCDName, RAMSz, diskSz, hasGPU.charAt(0) == 'y');
            }

            labs[i] = new Lab(labName, systems, new Employee(empName, empID, empDesignation));
        }
        
        for(int i=0; i < labs.length; i++)
            this.addLab(labs[i]);
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
        String out = String.format("""

                =======================
                %-10s
                =======================
                ------------
                %s
                ------------
                %s""", name, HOD, inCharge);

        if(labs != null)
            for(int i=0; i < labs.length; i++){
                if(labs[i] != null)
                    out += "\n" + labs[i];
            }

        return out;
    }

    @Override
    public boolean equals(Object o){
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
}
