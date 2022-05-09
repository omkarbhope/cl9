import mpi.MPI;
public class Factorial {

    public static int fact(int number) {
        int factorial = 1;
        for (int i = 2; i <= number; i++) {
            factorial = factorial * i;
        }

        return factorial;
    }
    public static void main(String args[]) throws Exception {
        MPI.Init(args);
    
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        
        int root = 0;
        int sendbuf[] = null;
        sendbuf = new int[size];
        
        if(rank==root) {
            sendbuf[0] = 2;
            sendbuf[1] = 3;
            sendbuf[2] = 5;
            sendbuf[3] = 7;

            System.out.print("Processor "+rank+" has data: ");
            for(int i = 0; i < size; i++){
                System.out.print(sendbuf[i]+" ");
            }
            System.out.println();
        }
        int recvbuf[] = new int[1];

        MPI.COMM_WORLD.Scatter(sendbuf,0,1, MPI.INT,recvbuf,0,1, MPI.INT, root);

        recvbuf[0] = fact(recvbuf[0]);
        
        MPI.COMM_WORLD.Gather(recvbuf,0,1, MPI.INT,sendbuf,0,1, MPI.INT, root);

        if(rank == root) {

            System.out.print("Processor "+rank+" has data: ");
            for(int i = 0; i < size; i++){
                System.out.print(sendbuf[i]+" ");
            }
            System.out.println();
        }

        MPI.Finalize();
    }
}
