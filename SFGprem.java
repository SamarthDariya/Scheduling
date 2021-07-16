package OSAssignment;
import java.util.*;

 
public class SFGprem
{   
			    // Method to find the waiting time for all
			    // processes
			    static void findWaitingTime(ArrayList<Process> proc, int n,
			                                     int wt[])
			    {
			        int rt[] = new int[n];
			      
			        // Copy the burst time into rt[]
			        for (int i = 0; i < n; i++)
			            rt[i] = proc.get(i).burst_time;
			      
			        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
			        int shortest = 0, finish_time;
			        boolean check = false;
			      
			        // Process until all processes gets
			        // completed
			        while (complete != n) {
			      
			            // Find process with minimum
			            // remaining time among the
			            // processes that arrives till the
			            // current time`
			            for (int j = 0; j < n; j++)
			            {
			                if ((proc.get(j).arrival_time <= t) &&
			                  (rt[j] < minm) && rt[j] > 0) {
			                    minm = rt[j];
			                    shortest = j;
			                    check = true;
			                }
			            }
			      
			            if (check == false) {
			                t++;
			                continue;
			            }
			      
			            // Reduce remaining time by one
			            rt[shortest]--;
			      
			            // Update minimum
			            minm = rt[shortest];
			            if (minm == 0)
			                minm = Integer.MAX_VALUE;
			      
			            // If a process gets completely
			            // executed
			            if (rt[shortest] == 0) {
			      
			                // Increment complete
			                complete++;
			                check = false;
			      
			                // Find finish time of current
			                // process
			                finish_time = t + 1;
			      
			                // Calculate waiting time
			                wt[shortest] = finish_time -
			                             proc.get(shortest).burst_time -
			                             proc.get(shortest).arrival_time;
			      
			                if (wt[shortest] < 0)
			                    wt[shortest] = 0;
			            }
			            // Increment time
			            t++;
			        }
			    }
			      
			    // Method to calculate turn around time
			    static void findTurnAroundTime(ArrayList<Process> proc, int n,
			                            int wt[], int tat[])
			    {
			        // calculating turnaround time by adding
			        // burst_time and waitingtime
			        for (int i = 0; i < n; i++)
			            tat[i] = proc.get(i).burst_time + wt[i];
			    }
			      
			    // Method to calculate average time
			    static void findavgTime(ArrayList<Process> proc, int n)
			    {
			        int wt[] = new int[n], tat[] = new int[n];
			        int  total_wt = 0, total_tat = 0;
			      
			        // Function to find waiting time of all
			        // processes
			        findWaitingTime(proc, n, wt);
			      
			        // Function to find turn around time for
			        // all processes
			        findTurnAroundTime(proc, n, wt, tat);
			      
	
			      
			        // Calculate total waiting time and
			        // total turnaround time
			        for (int i = 0; i < n; i++) {
			            total_wt = total_wt + wt[i];
			            total_tat = total_tat + tat[i];
			            
			        }
			        float awt,att,tottt=0,totwt=0;
			        System.out.println("  Average waiting time for SJF with preemption = " +
			                          (float)total_wt / (float)n);
			        awt = (float)total_wt / (float)n;
			        System.out.println("  Average turn around time for SJF with preemption = " +
			                           (float)total_tat / (float)n);
			        att = (float)total_tat / (float)n;
			        for (int i = 0; i < n; i++) {
			            totwt +=  ((float)wt[i]-awt)*((float)wt[i]-awt);
			            tottt += ((float)tat[i] - att)*((float)tat[i] - att);
			            
			        }
			        totwt = totwt/(float)n;
			        tottt = tottt/(float)n;

			        System.out.println("  the square of standard deviation of turnaround time :" + tottt);
     				System.out.println("  the square of standard deviation of waiting time :"+ totwt);


    }
			    SFGprem(ArrayList<Process> proc)//constructor
			    {
			    	findavgTime(proc,proc.size());
			    }
}
   
