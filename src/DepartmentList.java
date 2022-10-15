import java.lang.reflect.Field;
import java.util.Scanner;

@SuppressWarnings("unused")
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
                                                    [Options]
                    1 to add a department                      2 to display all current departments
                    3 to add a lab to a department             4 to remove a lab from a department
                    5 to add a system to a lab                 6 to remove a system from a lab
                    7 to add a software to a lab               8 to remove a software from a lab
                    9 to show no. of labs in a department      10 to show no. of systems in a lab
                    11 to change a department's info           12 to change a lab's info
                    13 to change a system's info               14 to change a software's info
                    
                    16 to save data to a file                  17 to load data from a file
                                                    15 to quit
                    
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

                dept = new Department(deptName, new Employee(hodName, hodID, hodDes), new Employee(inName, inID, inDes),
                        null);

                dept.showAddDialogs();

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
        for (Department dept : depts)
            if (dept != null)
                System.out.println(dept);
    }

    public static String getNonNewLine(){
        Scanner s = new Scanner(System.in);
        String nextIn = s.nextLine();

        while(nextIn.length() == 0 || nextIn.charAt(0) =='\n')
            nextIn = s.nextLine();

        return nextIn;
    }

    public void changeDeptInfo(int deptNo){
        Field[] fields = depts[deptNo].getClass().getDeclaredFields();

        System.out.println("Enter -1 to keep current values.");
        for(int i=0; i < fields.length; i++){
            if(fields[i].toString().contains("name")) {
                System.out.print("Enter department's name: ");
                String input = getNonNewLine();
                if(!input.equals("-1"))
                    depts[deptNo].setName(input);
            }

            if(fields[i].toString().contains("Employee")) {
                Field[] empFields = Employee.class.getDeclaredFields();
                for (Field field : empFields) {
                    System.out.print("Enter " + (fields[i].toString().toUpperCase().
                            substring(fields[i].toString().lastIndexOf('.')+1)
                            .charAt(0))
                            + fields[i].toString().substring(
                                    fields[i].toString().lastIndexOf('.')+2) + "'s "+
                            field.toString().substring(field.toString().lastIndexOf('.')+1)
                            +": ");

                    String input = getNonNewLine();
                    if(input.equals("-1"))
                        continue;

                    if(fields[i].toString().contains("HOD")) {
                        if (field.toString().toLowerCase().contains("name"))
                            depts[deptNo].getHOD().setName(input);
                        else if (field.toString().toLowerCase().contains("id"))
                            depts[deptNo].getHOD().setId(input);
                        else if (field.toString().toLowerCase().contains("design"))
                            depts[deptNo].getHOD().setDesignation(input);
                    }
                    else {
                        if (field.toString().toLowerCase().contains("name"))
                            depts[deptNo].getInCharge().setName(input);
                        else if (field.toString().toLowerCase().contains("id"))
                            depts[deptNo].getInCharge().setId(input);
                        else if (field.toString().toLowerCase().contains("design"))
                            depts[deptNo].getInCharge().setDesignation(input);
                    }
                }
            }
            }
        }

    @Override
    public Object clone(){
        return new DepartmentList(this);
    }

    public int searchDept(Department dept){
        if(this.depts == null)
            return -1;

        for(int i=0; i < depts.length; i++)
            if(depts[i] != null)
                if(depts[i].equals(dept))
                    return i;

        return -1;
    }

    public int[] getIndexOf(boolean doGetDeptIndex, boolean doGetLabIndex, boolean doGetSysIndex, boolean doGetSwIndex){
        int[] idxs = {-1};

        if(doGetDeptIndex)
        {
            DepartmentList deptList = this;

            if (deptList.depts == null) {
                System.out.println("No departments found");
                return new int[]{-1};
            }

            System.out.print("Enter the name of department: ");

            int deptNo = deptList.searchDept(new Department(DepartmentList.getNonNewLine(),
                    null, null, null));

            if (deptNo >= deptList.depts.length || deptNo < 0) {
                System.out.println("Could not find that department.");
                return new int[]{-1};
            }

            idxs[0] = deptNo;

            if (doGetLabIndex) {
                if (deptList.depts[deptNo].getLabs() == null) {
                    System.out.println("No labs found.");
                    return new int[]{-1};
                }

                System.out.print("Enter the name of lab: ");

                int labNo = deptList.depts[deptNo].searchLab(new Lab(DepartmentList.getNonNewLine(),
                        null, null, null));

                if (labNo >= deptList.depts[deptNo].getLabs().length || labNo < 0) {
                    System.out.println("Could not find that lab.");
                    return new int[]{-1};
                }

                idxs = new int[] {idxs[0], -1};

                idxs[1] = labNo;

                if (doGetSysIndex) {
                    Lab deptLab = deptList.depts[deptNo].getLabs()[labNo];

                    if (deptLab.getComputers() == null) {
                        System.out.println("No computer systems found.");
                        return new int[]{-1};
                    }

                    System.out.print("Enter the system ID: ");

                    int sysNo = deptLab.searchSystem(new PCSystem(DepartmentList.getNonNewLine(),
                            null, null, 0, 0, false));

                    if (sysNo >= deptLab.getComputers().length || sysNo < 0) {
                        System.out.println("Could not find that system.");
                        return new int[]{-1};
                    }

                    idxs = new int[] {idxs[0], idxs[1], -1};

                    idxs[2] = sysNo;
                }

                if(doGetSwIndex){
                    Lab deptLab = deptList.depts[deptNo].getLabs()[labNo];

                    if (deptLab.getSoftwares() == null) {
                        System.out.println("No softwares found.");
                        return new int[]{-1};
                    }

                    System.out.print("Enter the software's name: ");

                    int swNo = deptLab.searchSoftware(new Software(DepartmentList.getNonNewLine(),
                            null, null, null, null, 0));

                    if (swNo >= deptLab.getSoftwares().length || swNo < 0) {
                        System.out.println("Could not find that software.");
                        return new int[]{-1};
                    }

                    idxs = new int[] {idxs[0], idxs[1], -1};

                    idxs[2] = swNo;
                }
            }
        }

        return idxs;
    }
}
