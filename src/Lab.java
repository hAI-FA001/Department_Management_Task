import java.lang.reflect.Field;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Lab {
    private String name;
    private PCSystem[] computers;
    private Employee attendant;

    private Software[] softwares;

    private boolean hasFreeSysSlot = false;
    private int numFreeSysSlot = 0;
    private int numFreeSwSlot;
    private boolean hasFreeSwSlot;

    public Lab(){
        name = "";
        computers = null;
        attendant = new Employee();
        softwares = null;
    }

    public Lab(String name, PCSystem[] computers, Employee attendant, Software[] softwares) {
        this.name = name;
        this.computers = computers;
        this.attendant = attendant;
        this.softwares = softwares;
    }

    public Lab(String name, PCSystem[] computers, Employee attendant) {
        this.name = name;
        this.computers = computers;
        this.attendant = attendant;
    }

    public Lab(Lab l){
        this.name=l.name;
        this.computers = (l.computers != null)? new PCSystem[l.computers.length] : null;

        if(l.computers != null)
            for(int i=0; i<l.computers.length; i++)
                this.computers[i] = (PCSystem) l.computers[i].clone();

        this.attendant= (Employee) l.attendant.clone();

        this.softwares = (l.softwares != null)? new Software[l.softwares.length] : null;

        if(l.softwares != null)
            for(int i=0; i < l.softwares.length; i++)
                this.softwares[i] = (Software) l.softwares[i].clone();

        this.hasFreeSysSlot = l.hasFreeSysSlot;
        this.numFreeSysSlot = l.numFreeSysSlot;

        this.hasFreeSwSlot = l.hasFreeSwSlot;
        this.numFreeSwSlot = l.numFreeSwSlot;
    }

    public void removeSoftware(int swsNo){

        if(softwares[swsNo] == null){
            System.out.println("That software has been removed already.");
            return;
        }

        for(int i=swsNo; i<softwares.length; i++){
            int j = i+1;

            if(softwares[i] == null){
                break;
            }

            if(j == softwares.length){
                softwares[i] = null;
                hasFreeSwSlot = true;
                numFreeSwSlot++;
                break;
            }

            softwares[i] = softwares[j];
        }

        cleanSwList();
    }

    public void cleanSwList(){
        if(!hasFreeSwSlot){
            System.out.println("Could not find any null software entries.");
            return;
        }

        if(this.softwares.length-numFreeSwSlot == 0){
            this.softwares = null;
            return;
        }

        Software[] sw = new Software[this.softwares.length-numFreeSwSlot];

        for(int i=0, j=0; j<this.softwares.length; j++){
            if(this.softwares[j] != null)
            {
                sw[i] = this.softwares[j];
                i++;
            }
        }

        hasFreeSwSlot = false;
        numFreeSwSlot = 0;

        this.softwares = sw;
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
            System.out.println("Could not find any null system entries.");
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

    public void showAddDialogs(boolean addSystem, boolean addSoftware){
        Scanner s = new Scanner(System.in);

        if(addSystem)
        {
            System.out.print("How many systems do u want to add?\t");
            PCSystem[] systems = new PCSystem[s.nextInt()];

            if (systems.length > 0)
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

            for (PCSystem system : systems) this.addSystem(system);
        }

        if(addSoftware)
        {
            System.out.print("How many softwares do u want to add to lab " + this.name + "?\t");
            Software[] softwares = new Software[s.nextInt()];

            if (softwares.length > 0)
                System.out.println("Enter the required info as prompted.");
            for (int i = 0; i < softwares.length; i++) {
                System.out.print("\nSoftware name: ");
                String softwareName = DepartmentList.getNonNewLine();

                System.out.print("\nSoftware version: ");
                String softwareVer = DepartmentList.getNonNewLine();

                System.out.print("\nSoftware type: ");
                String softwareType = DepartmentList.getNonNewLine();

                System.out.print("\nSoftware vendor: ");
                String softwareVendor = DepartmentList.getNonNewLine();

                System.out.print("\nSoftware installation size (MB): ");
                float softwareInstSize = s.nextFloat();

                System.out.print("Enter the number of required software (if any) for this software: ");
                Software[] reqSoftwares = new Software[s.nextInt()];

                if (reqSoftwares.length > 0)
                    System.out.println("Enter the required info as prompted.");
                for (int j = 0; j < reqSoftwares.length; j++) {
                    System.out.print("\nRequired Software's name: ");
                    String reqSoftwareName = DepartmentList.getNonNewLine();

                    System.out.print("\nRequired Software's version: ");
                    String reqSoftwareVer = DepartmentList.getNonNewLine();

                    reqSoftwares[j] = new Software(reqSoftwareName, reqSoftwareVer);
                }

                softwares[i] = new Software(softwareName, softwareVer, softwareVendor, softwareType, reqSoftwares, softwareInstSize);
            }

            for (Software sw : softwares)
                addSoftware(sw);
        }

    }

    public void addSoftware(Software s) {
        if (s == null)
            return;

        if (this.softwares == null) {
            this.softwares = new Software[]{s};
            return;
        }

        Software[] softwares = new Software[this.softwares.length+1];

        for(int i=0; i < this.softwares.length; i++)
            softwares[i] = (Software) this.softwares[i].clone();

        softwares[this.softwares.length] = s;

        this.softwares = softwares;
    }
    
    public void addSystem(PCSystem p){

        if(p == null)
            return;

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
        StringBuilder out = new StringBuilder(String.format("""
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                %-15s
                
                Attendant Info
                --------------
                %s
                """, name, attendant));

        if(this.softwares != null) {
            out.append("\nSoftwares Info");

            for (Software s : this.softwares)
                if (s != null)
                    out.append("\n").append(s);
        }

        if(this.computers != null) {
            out.append("\n\nComputer Systems Info");

            for (PCSystem computer : this.computers)
                if (computer != null)
                    out.append(computer);
        }

        return out.toString();
    }

    @Override
    public boolean equals(Object o){
        if(o==null || o.getClass() != Lab.class)
            return false;

        Lab l = (Lab) o;

        return this.name.equals(l.name);
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

    public Software[] getSoftwares(){
        return softwares;
    }

    public void setSoftwares(Software[] softwares){
        this.softwares = softwares;
    }

    public void changeSoftwareInfo(int swNo){
        Field[] fields = softwares[swNo].getClass().getDeclaredFields();

        System.out.println("Enter -1 to keep current values.");
        for(int i=0; i < fields.length; i++){
            if(fields[i].toString().toLowerCase().contains("string")){
                System.out.print("Enter software's " +
                                (
                                fields[i].toString().toLowerCase().contains("ver")? "version" :
                                fields[i].toString().substring(fields[i].toString().lastIndexOf(".")+1)
                                )
                        + ": ");

                String input = DepartmentList.getNonNewLine();
                if(input.equals("-1"))
                    continue;

                if(fields[i].toString().toLowerCase().contains("name"))
                    getSoftwares()[swNo].setName(input);
                else if(fields[i].toString().toLowerCase().contains("ver"))
                    getSoftwares()[swNo].setVer(input);
                else if(fields[i].toString().toLowerCase().contains("vendor"))
                    getSoftwares()[swNo].setVendor(input);
                else if(fields[i].toString().toLowerCase().contains("type"))
                    getSoftwares()[swNo].setType(input);

            }
            else if(fields[i].toString().toLowerCase().contains("float")){
                System.out.print("Enter software's installation size: ");

                String input = DepartmentList.getNonNewLine();
                if(input.equals("-1"))
                    continue;
                getSoftwares()[swNo].setInstallationSizeMB(Float.parseFloat(input));
            }
        }

        if(getSoftwares()[swNo].getRequiredSoftware() != null && getSoftwares()[swNo].getRequiredSoftware().length > 0)
            {
                System.out.println("Do u also want to change required software info? (y/n)\t");

                if (DepartmentList.getNonNewLine().toLowerCase().charAt(0) == 'y') {
                    System.out.println("Enter -1 to keep current values.");

                    Software[] reqSwList = getSoftwares()[swNo].getRequiredSoftware();

                    for (int i = 0; i < reqSwList.length; i++) {

                        Software reqSw = reqSwList[i];

                        System.out.print("Enter required software's name: ");

                        String input = DepartmentList.getNonNewLine();

                        if(!input.equals("-1")) {
                            reqSw.setName(input);
                        }

                        System.out.print("Enter required software's version: ");

                        input = DepartmentList.getNonNewLine();

                        if(!input.equals("-1")) {
                            reqSw.setVer(input);
                        }
                    }
                }
            }
    }

    public void changeSysInfo(int sysNo){
        Field[] fields = computers[sysNo].getClass().getDeclaredFields();

        System.out.println("Enter -1 to keep current values.");
        for(int i=0; i < fields.length; i++){
            if(fields[i].toString().toLowerCase().contains("string")) {
                System.out.print("Enter system's " +
                        (fields[i].toString().toLowerCase().contains("asset")? "id" :
                                fields[i].toString().toLowerCase().contains("model")? "model" : "LCD name")
                        + ": ");

                String input = DepartmentList.getNonNewLine();
                if(input.equals("-1"))
                    continue;

                if(fields[i].toString().toLowerCase().contains("asset"))
                    getComputers()[sysNo].setAssetID(input);
                else if(fields[i].toString().toLowerCase().contains("model"))
                    getComputers()[sysNo].setModelName(input);
                else
                    getComputers()[sysNo].setLCDName(input);
                }
            else if (fields[i].toString().toLowerCase().contains("int")){
                    System.out.print("Enter system's " +
                            (fields[i].toString().toLowerCase().contains("ram")? "RAM size (MB)" : "disk size (GB)")
                    + ": ");

                    String input = DepartmentList.getNonNewLine();
                    if(input.equals("-1"))
                        continue;

                    if(fields[i].toString().toLowerCase().contains("ram"))
                        getComputers()[sysNo].setRAMSizeMB(Integer.parseInt(input));
                    else
                        getComputers()[sysNo].setDiskSizeGB(Integer.parseInt(input));
                }
            else if(fields[i].toString().toLowerCase().contains("bool")){
                System.out.print("Enter system's GPU availability (y/n): ");

                String input = DepartmentList.getNonNewLine();
                if(input.equals("-1"))
                    continue;

                getComputers()[sysNo].setGPUAvailable(input.toLowerCase().charAt(0) =='y');
            }
        }
    }

    public int searchSystem(PCSystem sys){
        if(this.computers == null)
            return -1;

        for(int i=0; i < this.computers.length; i++)
            if(this.computers[i] != null)
                if(this.computers[i].equals(sys))
                    return i;

        return -1;
    }

    public int searchSoftware(Software sw){
        if(this.softwares == null)
            return -1;

        for(int i=0; i < this.softwares.length; i++)
            if(this.softwares[i] != null)
                if(this.softwares[i].equals(sw))
                    return i;

        return -1;
    }

    public String csvFormat(boolean addFieldHeaders){
        StringBuilder out = new StringBuilder();

        if(addFieldHeaders)
        {
            out.append("name,attendant,department\n");
        }

        out.append(getName().replace(",", " ")).append(",")
                .append(getAttendant().getName().replace(",", " "));


        return out.toString();
    }
}
