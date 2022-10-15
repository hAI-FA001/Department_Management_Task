import java.io.*;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Driver {
    public static void main(String[] args) throws IOException {

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

            String opToPerform = s.next();

            if(opToPerform.charAt(0) == 'h')
            {
                showMenu = true;
                continue;
            }

            for(char c: opToPerform.toCharArray())
                    if((c < '0' || c > '9') && !(opToPerform.indexOf(c) == 0 && c == 'h'))
                    {
                        System.out.println("Only numeric inputs are accepted aside from 'h'");
                        opToPerform = "";
                        break;
                    }
            if(!opToPerform.equals(""))
                exit = processOperation(Integer.parseInt(opToPerform), deptList);
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
                int[] indexes = deptList.getIndexOf(true, false, false, false);

                if(indexes.length == 1 && indexes[0] != -1)
                    deptList.depts[indexes[0]].showAddDialogs();
            }
            break;
            case 4:
            {
                int[] indexes = deptList.getIndexOf(true, true, false, false);

                if(indexes.length == 2 && indexes[0] != -1 && indexes[1] != -1)
                    {
                        System.out.println("Removing lab " +
                                deptList.depts[indexes[0]].getLabs()[indexes[1]].getName() + ".");
                        deptList.depts[indexes[0]].removeLab(indexes[1]);
                    }
            }
            break;
            case 5:
            {
                int[] indexes = deptList.getIndexOf(true, true, false, false);

                if(indexes.length == 2 && indexes[0] != -1 && indexes[1] != -1)
                    deptList.depts[indexes[0]].getLabs()[indexes[1]].showAddDialogs(true, false);
            }
            break;
            case 6:
            {
                int[] indexes = deptList.getIndexOf(true, true, true, false);

                if(indexes.length == 3 && indexes[0] != -1 && indexes[1] != -1 && indexes[2] != -1)
                    {
                        System.out.println("Removing system " +
                                deptList.depts[indexes[0]].getLabs()[indexes[1]].getComputers()[indexes[2]].getAssetID() + ".");
                        deptList.depts[indexes[0]].getLabs()[indexes[1]].removeSys(indexes[2]);
                    }
            }
            break;
            case 7:
            {
                int[] indexes = deptList.getIndexOf(true, true, false, false);

                if(indexes.length == 2 && indexes[0] != -1 && indexes[1] != -1){
                    deptList.depts[indexes[0]].getLabs()[indexes[1]].showAddDialogs(false, true);
                }

            }
            break;
            case 8:
            {
                int[] indexes = deptList.getIndexOf(true, true, false, true);

                if(indexes.length == 3 && indexes[0] != -1 && indexes[1] != -1 && indexes[2] != -1) {
                    System.out.println("Removing software " +
                            deptList.depts[indexes[0]].getLabs()[indexes[1]].getSoftwares()[indexes[2]].getName() +"."
                    );
                    deptList.depts[indexes[0]].getLabs()[indexes[1]].removeSoftware(indexes[2]);
                }
            }
            break;
            case 9:
            {
                int[] indexes = deptList.getIndexOf(true, false, false, false);

                if(indexes.length == 1 && indexes[0] != -1)
                    System.out.println("No. of labs in "+deptList.depts[indexes[0]].getName()+" department: "
                            +((deptList.depts[indexes[0]].getLabs()!=null)?
                            deptList.depts[indexes[0]].getLabs().length:0 + " (no labs)"));
            }
            break;
            case 10:
            {
                int[] indexes = deptList.getIndexOf(true, true, false, false);

                if(indexes.length == 2 && indexes[0] != -1 && indexes[1] != -1)
                    System.out.println("No. of systems in lab "+
                            deptList.depts[indexes[0]].getLabs()[indexes[1]].getName()+": "+
                            ((deptList.depts[indexes[0]].getLabs()[indexes[1]].getComputers() != null)?
                                 deptList.depts[indexes[0]].getLabs()[indexes[1]].getComputers().length:0 + " (no systems)"));
            }
            break;
            case 11:
            {
                int[] indexes = deptList.getIndexOf(true, false, false, false);

                if(indexes.length == 1 && indexes[0] != -1)
                    deptList.changeDeptInfo(indexes[0]);
            }
            break;
            case 12:
            {
                int[] indexes = deptList.getIndexOf(true, true, false, false);

                if(indexes.length == 2 && indexes[0] != -1 && indexes[1] != -1)
                    deptList.depts[indexes[0]].changeLabInfo(indexes[1]);
            }
            break;
            case 13:
            {
                int[] indexes = deptList.getIndexOf(true, true, true, false);



                if(indexes.length == 3 && indexes[0] != -1 && indexes[1] != -1 && indexes[2] != -1)
                    deptList.depts[indexes[0]].getLabs()[indexes[1]].changeSysInfo(indexes[2]);
            }
            break;
            case 14:
            {
                int[] indexes = deptList.getIndexOf(true, true, false, true);

                if(indexes.length == 3 && indexes[0] != -1 && indexes[1] != -1 && indexes[2] != -1)
                    deptList.depts[indexes[0]].getLabs()[indexes[1]].changeSoftwareInfo(indexes[2]);
            }
            break;
            case 16:
            {
                if(deptList.depts == null || deptList.depts.length == 0) {
                    System.out.println("No data to save. Add a department first.");
                    break;
                }
                else {
                    System.out.print("Save it as csv or txt? (csv/txt)\t");
                    boolean asCSV = DepartmentList.getNonNewLine().substring(0, 3).contains("csv");
                    saveFileHandler(deptList, asCSV);

                }

                System.out.println("Saved Successfully. (if the file doesn't show up, exit the program)");
            }
            break;
            case 17:
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
            case 15:
                return true;
            default:
                System.out.println("Incorrect option entered.");
        }

        return false;
    }

    public static void saveFileHandler(DepartmentList deptList, boolean doSaveAsCSV) throws IOException {
        if(doSaveAsCSV){
            saveAsCSV(deptList);
            return;
        }

        System.out.print("Enter the file name: ");
        String fileName = DepartmentList.getNonNewLine();

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

    public static void saveAsCSV(DepartmentList deptList) throws IOException {
        File folder = new File("csv_output\\");
        if(!folder.exists())
            if(!folder.mkdir()) {
                System.out.println("Failed to create output directory...");
                return;
            }

        String[] fileNames = {
                PCSystem.class.getSimpleName(), Employee.class.getSimpleName(), Software.class.getSimpleName(),
                "Required"+Software.class.getSimpleName(), Lab.class.getSimpleName(), Department.class.getSimpleName()
        };

        StringBuilder deptsString = new StringBuilder(), labsString = new StringBuilder(),
                softwaresString = new StringBuilder(), reqSoftwaresString = new StringBuilder(),
                systemsString = new StringBuilder(), employeesString = new StringBuilder();
        for(int i=0; i < deptList.depts.length; i++) {

            deptsString.append(deptList.depts[i].csvFormat(deptsString.isEmpty()));


            employeesString.append(deptList.depts[i]
                    .getHOD().csvFormat(employeesString.isEmpty())).append(",")
                        .append(deptList.depts[i].getName()).append(",-").append("\n")
                        .append(deptList.depts[i].getInCharge().csvFormat(employeesString.isEmpty()))
                            .append(",").append(deptList.depts[i].getName())
                            .append(",-").append("\n");



            if(deptList.depts[i].getLabs() != null)
                for(int j=0; j < deptList.depts[i].getLabs().length; j++) {

                    labsString.append(deptList.depts[i].getLabs()[j].csvFormat(labsString.isEmpty()))
                            .append(",").append(deptList.depts[i].getName())
                            .append("\n");


                    employeesString.append(deptList.depts[i].getLabs()[j].
                            getAttendant().csvFormat(employeesString.isEmpty()))
                                .append(",").append(deptList.depts[i].getName()).append(",")
                                .append(deptList.depts[i].getLabs()[j].getName()).append("\n");


                    if(deptList.depts[i].getLabs()[j].getSoftwares() != null)
                        for(int k=0; k < deptList.depts[i].getLabs()[j].getSoftwares().length; k++) {

                            softwaresString.append(
                                    deptList.depts[i].getLabs()[j]
                                            .getSoftwares()[k].csvFormat(softwaresString.isEmpty(), false))
                                                .append(",").append(deptList.depts[i].getLabs()[j].getName())
                                                .append("\n");

                            if(deptList.depts[i].getLabs()[j].getSoftwares()[k].getRequiredSoftware() != null)
                                for(int l=0; l < deptList.depts[i].getLabs()[j].getSoftwares()[k].getRequiredSoftware().length; l++)
                                    reqSoftwaresString.append(
                                            deptList.depts[i].getLabs()[j].getSoftwares()[k]
                                                    .getRequiredSoftware()[l].csvFormat(reqSoftwaresString.isEmpty(), true))
                                                        .append(",").append(deptList.depts[i].getLabs()[j].getSoftwares()[k].getName())
                                                        .append("\n");
                        }

                    if(deptList.depts[i].getLabs()[j].getComputers() != null)
                        for(int k=0; k < deptList.depts[i].getLabs()[j].getComputers().length; k++)
                            systemsString.append(
                                    deptList.depts[i].getLabs()[j]
                                            .getComputers()[k].csvFormat(systemsString.isEmpty()))
                                                .append(",").append(deptList.depts[i].getLabs()[j].getName())
                                                .append("\n");
                }
        }

        for(String fileName : fileNames){
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(folder+"\\"+fileName+".csv"));

            if(fileName.toLowerCase().contains("sys"))
                bufferedWriter.write(String.valueOf(systemsString));

            else if(fileName.toLowerCase().contains("empl"))
                bufferedWriter.write(String.valueOf(employeesString));

            else if(fileName.toLowerCase().contains("soft") && !fileName.toLowerCase().contains("req"))
                bufferedWriter.write(String.valueOf(softwaresString));

            else if(fileName.toLowerCase().contains("req"))
                bufferedWriter.write(String.valueOf(reqSoftwaresString));

            else if(fileName.toLowerCase().contains("lab"))
                bufferedWriter.write(String.valueOf(labsString));

            else if(fileName.toLowerCase().contains("dep"))
                bufferedWriter.write(String.valueOf(deptsString));

            bufferedWriter.flush();
            bufferedWriter.close();
        }
    }

    public static void saveToFile(String fileName, DepartmentList deptList, boolean doAppend) throws IOException {

        BufferedWriter bufferedWriter;

        if(!doAppend)
            bufferedWriter = new BufferedWriter(new FileWriter(fileName + ".txt"));
        else
            bufferedWriter = new BufferedWriter(new FileWriter(fileName + ".txt", true));

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
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
                "--------------",
                "......................................",
                "``````````````````````",
                "~~~~~~~"
        };
        boolean readingHOD = true, hasReadSoftware = false;

        Department dept = null;
        Lab lab = null;
        Employee employee = new Employee();
        PCSystem system = new PCSystem();
        Software software = new Software(),
                reqSoftware = new Software();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String fileInput;


        while((fileInput = bufferedReader.readLine()) != null){
            if(fileInput.equals("_-_-_-_-_-_-_-_-_-_-_-_-")) {
                if (dept != null) {
                    if(hasReadSoftware && lab != null) {
                        lab.addSoftware((Software) software.clone());
                        software = new Software();
                        hasReadSoftware = false;
                    }
                    System.out.println("Here");
                    dept.addLab(lab);
                    deptList.addDept(dept);
                    lab = null;
                    dept = new Department();
                    continue;
                } else
                    dept = new Department();
            }


            if(fileInput.equals(separators[0])){
                if(dept != null && dept.getName().equals(""))
                    dept.setName(bufferedReader.readLine().strip());
                continue;
            }

            if(fileInput.equals(separators[1])){
                employee.setId(bufferedReader.readLine().strip());
                String[] nameAndDesign = bufferedReader.readLine().split("\t");
                employee.setName(nameAndDesign[0].strip());
                employee.setDesignation(nameAndDesign[1].strip());

                if(readingHOD){
                    if (dept != null) {
                        dept.setHOD((Employee) employee.clone());
                    }
                    readingHOD = false;
                }
                else {
                    if (dept != null) {
                        dept.setInCharge((Employee) employee.clone());
                    }
                    readingHOD = true;
                }

                continue;
            }

            if(fileInput.equals(separators[2])){

                if(lab != null)
                    if (dept != null) {
                        dept.addLab((Lab) lab.clone());
                    }

                lab = new Lab();

                lab.setName(bufferedReader.readLine().strip());

                continue;
            }

            if(fileInput.equals(separators[3])){
                employee.setId(bufferedReader.readLine().strip());
                String[] nameAndDesign = bufferedReader.readLine().split("\t");
                employee.setName(nameAndDesign[0].strip());
                employee.setDesignation(nameAndDesign[1].strip());

                if (lab != null) {
                    lab.setAttendant((Employee) employee.clone());
                }

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

                system.setGPUAvailable(!bufferedReader.readLine().contains("Not Available"));

                if (lab != null)
                    lab.addSystem((PCSystem) system.clone());

                continue;
            }

            if(fileInput.equals(separators[6])){
                String[] reqSoftInfo = bufferedReader.readLine().split("\t\t");
                reqSoftware.setName(reqSoftInfo[0].split(">")[1].strip());
                reqSoftware.setVer(reqSoftInfo[1].split(":")[1].strip());

                software.addRequiredSoftware((Software) reqSoftware.clone());

                continue;
            }
            else {
                if(hasReadSoftware && !fileInput.toLowerCase().contains("required softwares info:") && lab != null) {
                    lab.addSoftware((Software) software.clone());
                    software = new Software();
                    hasReadSoftware = false;
                }
            }

            if(fileInput.equals(separators[5])){
                String[] softInfo = bufferedReader.readLine().split("\t\t");
                software.setName(softInfo[0].strip());
                software.setVer(softInfo[1].split(":")[1].strip());

                softInfo = bufferedReader.readLine().split("\t\t");
                software.setType(softInfo[0].split(":")[1].strip());
                software.setVendor(softInfo[1].split(":")[1].strip());

                software.setInstallationSizeMB(Float.parseFloat(bufferedReader.readLine().split(":")[1].split("MB")[0].strip()));

                hasReadSoftware = true;

            }

        }


        if (dept != null) {
            dept.addLab(lab);
        }
        deptList.addDept(dept);

        return deptList;
    }
}
