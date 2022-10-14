import java.io.*;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException {

       /* {
            PCSystem[] systems = new PCSystem[10];

            for (int i = 0; i < systems.length; i++)
                systems[i] = new PCSystem("Dell No." + i, "Model " + i,
                        "LCD " + i, (i + 1) * 1024, (i + 1) * 2048, (i % 2 == 0) ? true : false);

            Lab[] labs = new Lab[10];

            for (int i = 0; i < labs.length; i++) {
                labs[i] = new Lab("C-" + (i + 10), systems,
                        new Employee("Lab Att. Name", "Lab Att. ID", "Lab Att. Design"));
            }

            Department dept = new Department("Computer Science",
                    new Employee("Firstname Lastname", "hereIsID", "designation here"),
                    new Employee("Second employee's name", "hereIsID2", "second's designation"),
                    labs);

            //System.out.println(dept);

            Department dept2 = new Department(dept);

            System.out.println(dept2);
        }*/

        Scanner s = new Scanner(System.in);

        DepartmentList deptList = new DepartmentList();

        boolean exit = false, showMenu = true;

        while(!exit)
        {
            if(showMenu)
            {
                deptList.showMenu();
                showMenu = false;
            }
            System.out.print(">>> ");

            String oper = s.next();

            if(oper.charAt(0) == 'h')
            {
                showMenu = true;
                continue;
            }

            for(char c: oper.toCharArray())
                    if((c < '0' || c > '9') && !(oper.indexOf(c) == 0 && c == 'h'))
                    {
                        System.out.println("Only numeric inputs are accepted aside from 'h'");
                        oper = "";
                        break;
                    }
            if(!oper.equals(""))
                exit = processOperation(Integer.parseInt(oper), deptList);
        }

        System.out.println("Exited...");

    }

    public static boolean processOperation(int op, DepartmentList deptList) throws IOException {
        Scanner s = new Scanner(System.in);

        switch (op) {
            case 1:
            {
                deptList.showAddDeptDialogs();
            }
            break;
            case 2:
            {
                deptList.printDepts();
            }
            break;
            case 3:
            {
                if(deptList.depts == null)
                {
                    System.out.println("No departments found");
                    break;
                }

                System.out.println("Which dept. no. do u want to add a lab to?");

                int deptNo = s.nextInt();
                deptNo--;

                if(deptNo >= deptList.depts.length || deptNo < 0)
                {
                    System.out.println("Could not find that department no.");
                    break;
                }
                deptList.depts[deptNo].showAddDialogs();
            }
            break;
            case 4:
            {
                if(deptList.depts == null)
                {
                    System.out.println("No departments found");
                    break;
                }

                System.out.println("Which dept. no. do u want to remove a lab from?");

                int deptNo = s.nextInt();
                deptNo--;

                if(deptNo >= deptList.depts.length || deptNo < 0)
                {
                    System.out.println("Could not find that department no.");
                    break;
                }

                if(deptList.depts[deptNo].getLabs() == null){
                    System.out.println("No labs found.");
                    break;
                }

                System.out.println("Which lab no. do u want to remove?");

                int labNo = s.nextInt();
                labNo--;

                if(labNo >= deptList.depts[deptNo].getLabs().length || labNo < 0)
                {
                    System.out.println("Could not find that lab no.");
                    break;
                }

                deptList.depts[deptNo].removeLab(labNo);
                System.out.println("Removed lab no."+(labNo+1)+
                        " The lab no. of labs after "+(labNo+1)+" (if any) have been shifted down by 1.");
            }
            break;
            case 5:
            {
                if(deptList.depts == null)
                {
                    System.out.println("No departments found.");
                    break;
                }

                System.out.println("Which dept. no. do u want to add a system to?");
                int deptNo = s.nextInt();
                deptNo--;

                System.out.println("Which lab no. do u want to add a system to?");
                int labNo = s.nextInt();
                labNo--;

                if(deptNo < deptList.depts.length && deptNo >= 0)
                    if(deptList.depts[deptNo].getLabs()==null)
                    {
                        System.out.println("No labs found.");
                        break;
                    }
                    else if(labNo >= deptList.depts[deptNo].getLabs().length || labNo < 0)
                    {
                        System.out.println("Could not find that lab no.");
                        break;
                    }
                    else;
                else
                {
                    System.out.println("Could not find that dept. no.");
                    break;
                }

                deptList.depts[deptNo].getLabs()[labNo].showAddDialogs();
            }
            break;
            case 6:
            {
                if(deptList.depts == null)
                {
                    System.out.println("No departments found.");
                    break;
                }

                System.out.println("Which dept. no. do u want to remove a system from?");
                int deptNo = s.nextInt();
                deptNo--;

                System.out.println("Which lab no. do u want to remove a system from?");
                int labNo = s.nextInt();
                labNo--;

                if(deptNo < deptList.depts.length && deptNo >= 0)
                    if(deptList.depts[deptNo].getLabs()==null){
                        System.out.println("No labs found.");
                        break;
                    }
                    else if(labNo >= deptList.depts[deptNo].getLabs().length || labNo < 0)
                    {
                        System.out.println("Could not find that lab no.");
                        break;
                    }
                    else;
                else
                {
                    System.out.println("Could not find that dept. no.");
                    break;
                }

                System.out.println("Which system no. do u want to remove?");

                int sysNo = s.nextInt()-1;

                if(deptList.depts[deptNo].getLabs()[labNo].getComputers()==null){
                    System.out.println("No computer systems found.");
                    break;
                }

                if(sysNo >= deptList.depts[deptNo].getLabs()[labNo].getComputers().length || sysNo < 0)
                {
                    System.out.println("Could not find that system no.");
                    break;
                }

                deptList.depts[deptNo].getLabs()[labNo].removeSys(sysNo);
                System.out.println("Removed system no."+(sysNo+1)+
                        " The system no. of systems after "+(sysNo+1)+" (if any) have been shifted down by 1.");
            }
            break;
            case 7:
            {
                if(deptList.depts == null){
                    System.out.println("No departments found.");
                    break;
                }

                System.out.println("Enter dept. no.");
                int deptNo = s.nextInt()-1;

                if(deptNo >= deptList.depts.length || deptNo < 0) {
                    System.out.println("Could not find that department.");
                    break;
                }

                System.out.println("No. of labs in Department no."+(deptNo+1)+": "
                        +((deptList.depts[deptNo].getLabs()!=null)?
                        deptList.depts[deptNo].getLabs().length:0));
            }
            break;
            case 8:
            {
                if(deptList.depts == null)
                {
                    System.out.println("No departments found");
                    break;
                }

                System.out.println("Enter dept. no.");
                int deptNo = s.nextInt()-1;

                System.out.println("Enter lab no.");
                int labNo = s.nextInt()-1;


                if(deptNo >= deptList.depts.length || deptNo < 0){
                    System.out.println("Could not find that department");
                    break;
                }
                else if(deptList.depts[deptNo].getLabs() == null){
                    System.out.println("No labs found.");
                    break;
                }
                else if(labNo >= deptList.depts[deptNo].getLabs().length || labNo < 0){
                    System.out.println("Could not find that lab.");
                    break;
                }

                System.out.println("No. of systems in lab no."+(labNo+1)+": "+
                        ((deptList.depts[deptNo].getLabs()[labNo].getComputers() != null)?
                                deptList.depts[deptNo].getLabs()[labNo].getComputers().length:0));
            }
            break;
            case 9:
            {
                if(deptList.depts == null)
                {
                    System.out.println("No departments found");
                    break;
                }

                System.out.println("Which dept. no. do u want to change info of?");

                int deptNo = s.nextInt();
                deptNo--;

                if(deptNo >= deptList.depts.length || deptNo < 0)
                {
                    System.out.println("Could not find that department no.");
                    break;
                }

                deptList.changeDeptInfo(deptNo);
            }
            break;
            case 10:
            {
                if(deptList.depts == null)
                {
                    System.out.println("No departments found");
                    break;
                }

                System.out.println("Which dept. no.'s lab info do u want to change?");

                int deptNo = s.nextInt();
                deptNo--;

                if(deptNo >= deptList.depts.length || deptNo < 0)
                {
                    System.out.println("Could not find that department no.");
                    break;
                }

                if(deptList.depts[deptNo].getLabs() == null){
                    System.out.println("No labs found.");
                    break;
                }

                System.out.println("Which lab no. do u want to change info of?");

                int labNo = s.nextInt();
                labNo--;

                if(labNo >= deptList.depts[deptNo].getLabs().length || labNo < 0)
                {
                    System.out.println("Could not find that lab no.");
                    break;
                }

                deptList.depts[deptNo].changeLabInfo(labNo);
            }
            break;
            case 11:
            {
                if(deptList.depts == null)
                {
                    System.out.println("No departments found");
                    break;
                }

                System.out.println("Which dept. no.'s system do u want to change info of?");

                int deptNo = s.nextInt();
                deptNo--;

                if(deptNo >= deptList.depts.length || deptNo < 0)
                {
                    System.out.println("Could not find that department no.");
                    break;
                }

                if(deptList.depts[deptNo].getLabs() == null){
                    System.out.println("No labs found.");
                    break;
                }

                System.out.println("Which lab no.'s system do u want to change info of?");

                int labNo = s.nextInt();
                labNo--;

                if(labNo >= deptList.depts[deptNo].getLabs().length || labNo < 0)
                {
                    System.out.println("Could not find that lab no.");
                    break;
                }

                System.out.println("Which system no.'s info do u want to change?");

                int sysNo = s.nextInt()-1;

                if(deptList.depts[deptNo].getLabs()[labNo].getComputers()==null){
                    System.out.println("No computer systems found.");
                    break;
                }

                if(sysNo >= deptList.depts[deptNo].getLabs()[labNo].getComputers().length || sysNo < 0)
                {
                    System.out.println("Could not find that system no.");
                    break;
                }

                deptList.depts[deptNo].getLabs()[labNo].changeSysInfo(sysNo);
            }
            break;
            case 13:
            {
                if(deptList.depts == null || deptList.depts.length == 0) {
                    System.out.println("No data to save. Add a department first.");
                    break;
                }
                else {
                    System.out.print("Enter the file name: ");
                    saveFileHandler(DepartmentList.getNonNewLine(), deptList);
                }

                System.out.println("Saved Successfully. (if the file doesn't show up, exit the program)");
            }
            break;
            case 14:
            {
                System.out.print("Enter file name: ");
                DepartmentList dL = readFromFile(DepartmentList.getNonNewLine());
                if(dL == null)
                    System.out.println("Error in loading data...");
                else
                {
                    System.out.println("Successfully loaded data. ("+
                            ((dL.depts == null)? 0 : dL.depts.length)+" department(s) found)");
                    deptList.depts = dL.depts;
                }
            }
            break;
            case 12:
                return true;
            default:
                System.out.println("Incorrect option entered.");
        }

        return false;
    }

    public static void saveFileHandler(String fileName, DepartmentList deptList) throws IOException {
        File file = new File(fileName+".txt");

        if(file.exists())
        {
            Scanner s = new Scanner(System.in);

            System.out.print("""
                               File already exists.
                               
                               Enter 1 to append at the end of the file.
                               Enter 2 to create a file with a different name.
                               Enter 3 to over-write the file.
                               
                               """);
            System.out.print(">>> ");

            int op = s.nextInt();

            switch (op){
                case 1:
                    saveToFile(fileName, deptList, true);
                    break;
                case 2:
                    System.out.print("Enter the new file name: ");
                    fileName = DepartmentList.getNonNewLine();
                case 3:
                    saveToFile(fileName, deptList, false);
                    break;
                default:
                    System.out.println("Incorrect option entered.");
            }
        }
        else saveToFile(fileName, deptList, false);
    }

    public static void saveToFile(String fileName, DepartmentList deptList, boolean doAppend) throws IOException {

        BufferedWriter bufferedWriter;

        if(!doAppend)
            bufferedWriter = new BufferedWriter(new FileWriter(new File(fileName + ".txt")));
        else
            bufferedWriter = new BufferedWriter(new FileWriter(new File(fileName + ".txt"), true));

        for(int i=0; i < deptList.depts.length; i++) {
            bufferedWriter.write("\n_-_-_-_-_-_-_-_-_-_-_-_-" +
                    deptList.depts[i].toString());
        }

        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static DepartmentList readFromFile(String fileName) throws IOException {
        DepartmentList deptList = new DepartmentList();

        File file = new File(fileName+".txt");

        if(!file.exists()){
            System.out.println("File does not exist.");
            return null;
        }

        String[] separators = new String[] {
                "=======================",
                "------------",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
                "--------------",
                "......................................"
        };
        boolean readingHOD = true;

        Department dept = null;
        Lab lab = null;
        Employee employee = new Employee();
        PCSystem system = new PCSystem();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String fileInput;


        while((fileInput = bufferedReader.readLine()) != null){
            if(fileInput.equals("_-_-_-_-_-_-_-_-_-_-_-_-")) {
                if (dept != null) {
                    dept.addLab(lab);
                    deptList.addDept(dept);
                    lab = null;
                    dept = new Department();
                    continue;
                } else
                    dept = new Department();
            }


            if(fileInput.equals(separators[0])){
                if(dept.getName().equals(""))
                    dept.setName(bufferedReader.readLine().strip());
                continue;
            }

            if(fileInput.equals(separators[1])){
                employee.setId(bufferedReader.readLine().strip());
                String[] nameAndDesign = bufferedReader.readLine().split("\t");
                employee.setName(nameAndDesign[0].strip());
                employee.setDesignation(nameAndDesign[1].strip());

                if(readingHOD){
                    dept.setHOD((Employee) employee.clone());
                    readingHOD = false;
                }
                else {
                    dept.setInCharge((Employee) employee.clone());
                    readingHOD = true;
                }

                continue;
            }

            if(fileInput.equals(separators[2])){

                if(lab != null)
                    dept.addLab((Lab) lab.clone());

                lab = new Lab();

                lab.setName(bufferedReader.readLine().strip());

                continue;
            }

            if(fileInput.equals(separators[3])){
                employee.setId(bufferedReader.readLine().strip());
                String[] nameAndDesign = bufferedReader.readLine().split("\t");
                employee.setName(nameAndDesign[0].strip());
                employee.setDesignation(nameAndDesign[1].strip());

                lab.setAttendant((Employee) employee.clone());

                continue;
            }

            if(fileInput.equals(separators[4])){
                String[] sysInfo = bufferedReader.readLine().split(", ");

                system.setAssetID(sysInfo[0].strip());
                system.setModelName(sysInfo[1].strip());
                system.setLCDName(sysInfo[2].strip());

                String[] ramAndDisk = bufferedReader.readLine().split("\t\t");
                system.setRAMSizeMB(Integer.parseInt(ramAndDisk[0].substring(5, ramAndDisk[0].length()-3).strip()));
                system.setDiskSizeGB(Integer.parseInt(ramAndDisk[1].substring(6, ramAndDisk[1].length()-3).strip()));

                if(bufferedReader.readLine().contains("Not Available"))
                    system.setGPUAvailable(false);
                else
                    system.setGPUAvailable(true);

                lab.addSystem((PCSystem) system.clone());

                continue;
            }

        }


        dept.addLab(lab);
        deptList.addDept(dept);

        return deptList;
    }
}
