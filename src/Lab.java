import java.util.Scanner;

public class Lab {
    private String name;
    private PCSystem[] computers;
    private Employee attendant;

    public Lab(String name, PCSystem[] computers, Employee attendant) {
        this.name = name;
        this.computers = computers;
        this.attendant = attendant;
    }

    public Lab(Lab l){
        this.name=l.name;
        this.computers = new PCSystem[l.computers.length];

        for(int i=0; i<l.computers.length; i++)
            this.computers[i] = (PCSystem) l.computers[i].clone();

        this.attendant= (Employee) l.attendant.clone();
    }
    
    public void showAddDialogs(){
        Scanner s = new Scanner(System.in);
        
        System.out.print("How many systems do u want to add?\t");
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

            systems[j] = new PCSystem(sysID, sysModel, LCDName, RAMSz, diskSz, (hasGPU.charAt(0) == 'y') ? true : false);
        }
        
        for(int i=0; i<systems.length; i++)
            this.addSystem(systems[i]);
    }
    
    public void addSystem(PCSystem p){
        PCSystem[] computers = new PCSystem[this.computers.length+1];
        
        for(int i=0; i<this.computers.length; i++){
           computers[i] = (PCSystem) this.computers[i].clone(); 
        }
        computers[this.computers.length] = p;
        
        this.computers = computers;
    }

    @Override
    public String toString(){
        String out = String.format("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "%-15s\n" +
                "--------------\n" +
                "%s\n", name, attendant);

        for(int i=0; i<this.computers.length; i++)
            out+= "\n" + this.computers[i];

        return out;
    }

    @Override
    public Object clone(){
        return new Lab(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PCSystem[] getComputers() {
        return computers;
    }

    public void setComputers(PCSystem[] computers) {
        this.computers = computers;
    }

    public Employee getAttendant() {
        return attendant;
    }

    public void setAttendant(Employee attendant) {
        this.attendant = attendant;
    }
}
