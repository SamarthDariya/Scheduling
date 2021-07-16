package OSAssignment;
import java.util.*;

	    public class Process{
	    	public int pid;  
			public int arrival_time;
			public int burst_time;
			public int init_arrival_time;
			public int init_burst_time;
			public int end_time;
			public int waiting_time;
			public int turnaround_time;
			/*process class has the above attributes */
			Process(int p, int at, int bt ){
				pid = p;
				arrival_time = at;
				burst_time = bt;
				/*
				burst_time and arrival time refers to the time of
				entry in the ready queue and also the remaining burst time
				both of these quantities can be changed as the algorithm progresses
				so init_arrival_time and init_burst_time is used to store initial arrival time 
				and total burst time of the process 
				for calculation of turnaround time and waiting time we will use init one parameters
				*/
			    init_arrival_time = arrival_time;
			    init_burst_time = burst_time;
				}
				/* the below constructor copies a process object*/
			Process(Process P){
           				this(P.pid,P.arrival_time,P.burst_time);
				}

		}