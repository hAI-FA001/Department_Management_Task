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
            System.out.println("\nEnter 1 to add a department.\n" +
                    "Enter 2 to display all current departments.\n" +
                    "Enter 3 to add a lab to a department.\n" +
                    "Enter 4 to add a system to a lab.\n" +
                    "Enter 5 to quit.");
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
                        System.out.println("No departments found.");
                        break;
                    }

                    System.out.println("Which dept. no. do u want to add a system to?");
                    int deptNo = s.nextInt();

                    System.out.println("Which lab no. do u want to add a system to?");
                    int labNo = s.nextInt();

                    if(deptNo < deptList.depts.length)
                        if(labNo >= deptList.depts[deptNo].getLabs().length)
                        {
                            System.out.println("Could not find that lab no.");
                            break;
                        }
                    else
                        {
                            System.out.println("Could not find that dept. no.");
                            break;
                        }

                    deptList.depts[deptNo].getLabs()[labNo].showAddDialogs();
                }
                break;
                case 5:
                    exit = true;
                    System.out.println("Exited...");
                    break;
                default:
                    System.out.println("Incorrect option entered.");
            }
        }

    }
}
