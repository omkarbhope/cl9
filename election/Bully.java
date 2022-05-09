
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Bully {
    static boolean[] state = new boolean[7];
    int coordinator;

    public static void up(int up) {
        if (state[up - 1]) {
            System.out.println("process" + up + "is already up");
        } else {
            int i;
            Bully.state[up - 1] = true;
            System.out.println("process " + up + "held election");
            for (i = up; i < 7; ++i) {
                System.out.println("election message sent from process" + up + "to process" + (i + 1));
            }
            for (i = up + 1; i <= 7; ++i) {
                if (!state[i - 1]) continue;
                System.out.println("alive message send from process" + i + "to process" + up);
                break;
            }
        }
    }

    public static void down(int down) {
        if (!state[down - 1]) {
            System.out.println("process " + down + "is already dowm.");
        } else {
            Bully.state[down - 1] = false;
        }
    }

    public static void mess(int mess) {
        if (state[mess - 1]) {
            if (state[6]) {
                System.out.println("0K");
            } else if (!state[6]) {
                int i;
                System.out.println("process" + mess + "election");
                for (i = mess; i < 7; ++i) {
                    System.out.println("election send from process" + mess + "to process " + (i + 1));
                }
                for (i = 7; i >= mess; --i) {
                    if (!state[i - 1]) continue;
                    System.out.println("Coordinator message send from process" + i + "to all");
                    break;
                }
            }
        } else {
            System.out.println("Prccess" + mess + "is down");
        }
    }

    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 7; ++i) {
            Bully.state[i] = true;
        }
        System.out.println("7 active process are:");
        System.out.println("Process up  = p1 p2 p3 p4 p5 p6 p7");
        System.out.println("Process 7 is coordinator");
        do {
            System.out.println(".........");
            System.out.println("1 up a process.");
            System.out.println("2.down a process");
            System.out.println("3 send a message");
            System.out.println("4.Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("bring proces up");
                    int up = sc.nextInt();
                    if (up == 7) {
                        System.out.println("process 7 is co-ordinator");
                        Bully.state[6] = true;
                        break;
                    }
                    Bully.up(up);
                    break;
                }
                case 2: {
                    System.out.println("bring down any process.");
                    int down = sc.nextInt();
                    Bully.down(down);
                    break;
                }
                case 3: {
                    System.out.println("which process will send message");
                    int mess = sc.nextInt();
                    Bully.mess(mess);
                }
            }
        } while (choice != 4);
    }
}

/* 

7 active process are:
Process up  = p1 p2 p3 p4 p5 p6 p7
Process 7 is coordinator
.........
1 up a process.
2.down a process
3 send a message
4.Exit
2
bring down any process.
5
.........
1 up a process.
2.down a process
3 send a message
4.Exit
1
bring proces up
5
process 5held election
election message sent from process5to process6
election message sent from process5to process7
alive message send from process6to process5
.........
1 up a process.
2.down a process
3 send a message
4.Exit
3
which process will send message
3
0K
.........
1 up a process.
2.down a process
3 send a message
4.Exit
4

*/