package OSAssignment;
import java.util.*;

public class FCFScpu {
	
	FCFScpu(ArrayList<Process> proc){
		 int ct[] = new int[proc.size()];                                                   // completion times
         int ta[] = new int[proc.size()];                                                   // turn around times
         int wt[] = new int[proc.size()];                                                   // waiting times
         int temp;
	 float avgwt=0,avgta=0;
  //sorting according to arrival times
     for(int i = 0 ; i <proc.size(); i++)
       {
        for(int  j=0;  j < proc.size()-(i+1) ; j++)
             {
               if( proc.get(j).arrival_time > proc.get(j+1).arrival_time )
                      {
                        temp = proc.get(j).arrival_time;
                        proc.get(j).arrival_time =proc.get(j+1).arrival_time;
                        proc.get(j+1).arrival_time= temp;
                        temp = proc.get(j).burst_time;
                        proc.get(j).burst_time = proc.get(j+1).burst_time;
                        proc.get(j+1).burst_time= temp;
                        temp = proc.get(j).pid;
                        proc.get(j).pid = proc.get(j+1).pid;
                        proc.get(j+1).pid = temp;
                      }
                }
       }
  // finding completion times
     for(int  i = 0 ; i < proc.size(); i++)
                {
                  if( i == 0)
                                 {
                                   ct[i] = proc.get(i).arrival_time + proc.get(i).burst_time;
                                  }
                  else
                         {
                            if( proc.get(i).arrival_time > ct[i-1])
                                                     {
                                                       ct[i] =proc.get(i).arrival_time + proc.get(i).burst_time;
                                                      }
                             else 
                                    {
                                     ct[i] = ct[i-1] +proc.get(i).burst_time; 
                                    }
                          }
			    ta[i] = ct[i] - proc.get(i).arrival_time ;                            // turnaround time= completion time- arrival time
			    wt[i] = ta[i] - proc.get(i).burst_time ;                           // waiting time= turnaround time- burst time
			    avgwt += wt[i] ;                              // total waiting time
			    avgta += ta[i] ;                               // total turnaround time
            }
         float tottt=0,totwt=0;  
         for (int i = 0; i < proc.size(); i++) {
                  totwt +=  ((float)wt[i]-(avgwt/proc.size()))*((float)wt[i]-(avgwt/proc.size()));
                  tottt += ((float)ta[i] - (avgta/proc.size()))*((float)ta[i] - (avgta/proc.size()));
                  
              } 
           totwt = totwt/(float)(proc.size());
          tottt = tottt/(float)(proc.size());
    	
     
     System.out.println("\n  average waiting time for FCFS: "+ (avgwt/(proc.size())));                             // printing average waiting time.
     System.out.println("  average turnaround time for FCFS :"+(avgta/(proc.size()))); 
     System.out.println("  the square of standard deviation of turnaround time :" + tottt);
     System.out.println("  the square of standard deviation of waiting time :"+ totwt);                           // printing average turnaround time.
                                // printing average turnaround time.
  }
}

