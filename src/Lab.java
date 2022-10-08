import java.util.Scanner;

public class Lab {
    private String name;
    private PCSystem[] computers;
    private Employee attendant;

    private boolean hasFreeSysSlot = false;
    private int numFreeSysSlot = 0;

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

        this.hasFreeSysSlot = l.hasFreeSysSlot;
        this.numFreeSysSlot = l.numFreeSysSlot;
    }

    public void removeSys(int sysNo){

        if(computers[sysNo] == null){
            System.out.println("That system has been removed already.");
            return;
        }

        for(int i=sysNo; i<computers.length; i++){
            int j = i+1;

            if(computers[i] == null){
                break;
            }

            if(j == computers.length){
                computers[i] = null;
                hasFreeSysSlot = true;
                numFreeSysSlot++;
                break;
            }

            computers[i] = computers[j];
        }

        cleanSysList();
    }

    public void cleanSysList(){
        if(!hasFreeSysSlot){
            System.out.println("Could not find any free systems.");
            return;
        }

        if(this.computers.length-numFreeSysSlot == 0){
            this.computers = null;
            return;
        }

        PCSystem[] sys = new PCSystem[this.computers.length-numFreeSysSlot];

        for(int i=0, j=0; j<this.computers.length; j++){
            if(this.computers[j] != null)
            {
                sys[i] = this.computers[j];
                i++;
            }
        }

        hasFreeSysSlot = false;
        numFreeSysSlot = 0;

        this.computers = sys;
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

            systems[j] = new PCSystem(sysID, sysModel, LCDName, RAMSz, diskSz, hasGPU.charAt(0) == 'y');
        }
        
        for(int i=0; i<systems.length; i++)
            this.addSystem(systems[i]);
    }
    
    public void addSystem(PCSystem p){
        if(this.computers == null){
            this.computers = new PCSystem[]{p};
            return;
        }

        PCSystem[] computers = new PCSystem[this.computers.length+1];
        
        for(int i=0; i<this.computers.length; i++){
           computers[i] = (PCSystem) this.computers[i].clone(); 
        }
        computers[this.computers.length] = p;
        
        this.computers = computers;
    }

    @Override
    public String toString(){
        String out = String.format("""

                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                %-15s
                --------------
                %s
                """, name, attendant);

        if(this.computers != null)
            for(int i=0; i<this.computers.length; i++)
                if(this.computers[i] != null)
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
