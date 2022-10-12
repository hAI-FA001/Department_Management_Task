import java.util.Scanner;

public class DepartmentList {
    public Department[] depts;
    public DepartmentList(Department[] depts) {
        this.depts = new Department[depts.length];

        for (int i = 0; i < depts.length; i++) {
            this.depts[i] = (Department) depts[i].clone();
        }
    }

    public DepartmentList(){
        this.depts = null;
    }

    public DepartmentList(DepartmentList dL){

        if(dL.depts == null) {
            this.depts = null;
            return;
        }

        this.depts = new Department[dL.depts.length];

        for(int i=0; i < dL.depts.length; i++)
            this.depts[i] = (Department) dL.depts[i].clone();
    }

    public void showMenu(){
        System.out.print("""
                    
                    Enter 10 to save data to a file.
                    Enter 11 to load data from a file.
                    
                    Enter 1 to add a department.
                    Enter 2 to display all current departments.
                    Enter 3 to add a lab to a department.
                    Enter 4 to remove a lab from a department
                    Enter 5 to add a system to a lab.
                    Enter 6 to remove a system from a lab
                    Enter 7 to display current no. of labs in a department.
                    Enter 8 to display current no. of systems in a lab in a department.
                    Enter 9 to quit.
                    
                    Enter 'h' to display this menu.
                    """);
    }
    public void showAddDeptDialogs(){
        Scanner s = new Scanner(System.in);
        System.out.println("How many dept.s do u want to add?\t");
        int deptNo = s.nextInt();
        for(int k=0; k < deptNo; k++)
        {
            System.out.println("\n===========================\n" +
                    "Department No."+(k+1)+
                    "\nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
            Department dept;
            {
                System.out.println("Enter department name: ");
                String deptName = getNonNewLine();
                System.out.println("Enter HOD's info as prompted.");
                System.out.print("Name: ");
                String hodName = getNonNewLine();
                System.out.print("ID: ");
                String hodID = getNonNewLine();
                System.out.print("Designation: ");
                String hodDes = getNonNewLine();

                System.out.println("Enter incharge's info as prompted.");
                System.out.print("Name: ");
                String inName = getNonNewLine();
                System.out.print("ID: ");
                String inID = getNonNewLine();
                System.out.print("Designation: ");
                String inDes = getNonNewLine();

                System.out.print("How many labs do u want to add?\t");
                Lab[] labs = new Lab[s.nextInt()];

                for (int i = 0; i < labs.length; i++) {
                    System.out.print("Enter the name of lab no."+(i+1)+": ");
                    String labName = getNonNewLine();

                    System.out.println("Enter lab attendants info as prompted.");
                    System.out.print("Name: ");
                    String empName = getNonNewLine();
                    System.out.print("ID: ");
                    String empID = getNonNewLine();
                    System.out.print("Designation: ");
                    String empDesignation = getNonNewLine();

                    System.out.print("How many systems do u want to add for Lab no." + (i + 1) + " ?\t");
                    PCSystem[] systems = new PCSystem[s.nextInt()];

                    System.out.println("Enter the required info as prompted.");
                    for (int j = 0; j < systems.length; j++) {
                        System.out.println("System No."+(j+1));
                        System.out.print("\nSystem ID: ");
                        String sysID = getNonNewLine();
                        System.out.print("\nSystem Model: ");
                        String sysModel = getNonNewLine();
                        System.out.print("\nLCD Name: ");
                        String LCDName = getNonNewLine();
                        System.out.print("\nRAM Size (MB): ");
                        int RAMSz = s.nextInt();
                        System.out.print("\nDisk Size (GB): ");
                        int diskSz = s.nextInt();
                        System.out.print("\nHas GPU? (y/n) ");
                        String hasGPU = getNonNewLine();

                        systems[j] = new PCSystem(sysID, sysModel, LCDName, RAMSz, diskSz, hasGPU.charAt(0) == 'y');
                    }

                    labs[i] = new Lab(labName, systems, new Employee(empName, empID, empDesignation));
                }
                dept = new Department(deptName, new Employee(hodName, hodID, hodDes), new Employee(inName, inID, inDes),
                        labs);

                this.addDept(dept);
            }
        }
    }

    public void addDept(Department d) {

        if(d == null)
            return;

        if(this.depts != null)
        {
            Department[] depts = new Department[this.depts.length + 1];
            for (int i = 0; i < this.depts.length; i++)
                depts[i] = (Department) this.depts[i].clone();

            depts[this.depts.length] = (Department) d.clone();

            this.depts = depts;
        }
        else {
            this.depts = new Department[1];
            depts[0] = (Department) d.clone();
        }
    }

    public void printDepts(){
        if(depts == null)
        {
            System.out.println("No departments found.");
            return;
        }
        for(int i=0; i<depts.length; i++)
            if(depts[i] != null)
                System.out.println(depts[i]);
    }

    public static String getNonNewLine(){
        Scanner s = new Scanner(System.in);
        String nextIn = s.nextLine();

        while(nextIn.length() == 0 || nextIn.charAt(0) =='\n')
            nextIn = s.nextLine();

        return nextIn;
    }

    @Override
    public Object clone(){
        return new DepartmentList(this);
    }
}
