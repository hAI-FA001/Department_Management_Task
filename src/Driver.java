import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

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

        boolean exit = false;

        while(!exit)
        {
            deptList.showMenu();
            int op = s.nextInt();

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

                    if(deptNo >= deptList.depts.length)
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

                    if(deptNo >= deptList.depts.length)
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

                    if(labNo >= deptList.depts[deptNo].getLabs().length)
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

                    if(deptNo < deptList.depts.length)
                        if(deptList.depts[deptNo].getLabs()==null)
                        {
                            System.out.println("No labs found.");
                            break;
                        }
                        else if(labNo >= deptList.depts[deptNo].getLabs().length)
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

                    if(deptNo < deptList.depts.length)
                        if(deptList.depts[deptNo].getLabs()==null){
                            System.out.println("No labs found.");
                            break;
                        }
                        else if(labNo >= deptList.depts[deptNo].getLabs().length)
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

                    if(sysNo >= deptList.depts[deptNo].getLabs()[labNo].getComputers().length)
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

                    if(deptNo >= deptList.depts.length)
                        System.out.println("Could not find that department.");

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


                    if(deptNo >= deptList.depts.length){
                        System.out.println("Could not find that department");
                        break;
                    }
                    else if(deptList.depts[deptNo].getLabs() == null){
                        System.out.println("No labs found.");
                        break;
                    }
                    else if(labNo >= deptList.depts[deptNo].getLabs().length){
                        System.out.println("Could not find that lab.");
                        break;
                    }

                    System.out.println("No. of systems in lab no."+(labNo+1)+": "+
                            ((deptList.depts[deptNo].getLabs()[labNo].getComputers() != null)?
                                    deptList.depts[deptNo].getLabs()[labNo].getComputers().length:0));
                }
                break;
                case 9:
                    exit = true;
                    System.out.println("Exited...");
                    break;
                default:
                    System.out.println("Incorrect option entered.");
            }
        }

    }
}
