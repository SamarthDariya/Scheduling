package OSAssignment;
import java.util.*;
class Scheduling{
	public static void main(String[] args) {
		ArrayList<Process> input = new ArrayList<Process>();/*input array list*/
		Scanner sc = new Scanner(System.in);
		int choice;
		int pid_input =0;
				do{
					System.out.println("  Enter your choice");
					System.out.println("  Enter 0 if you want to add another process to queue");
					System.out.println("  Enter any other integer to run the Scheduling algorithms");
					System.out.print("  ");
					choice = sc.nextInt();
					if(choice == 0){
					//System.out.println("  Enter process pid ");
					//System.out.print("  ");
					int pid = ++pid_input;//pid is auto incremented
					System.out.println("  Enter data for process "+ pid);
					System.out.println("  Enter process arrival time ");
					System.out.print("  ");
					int arrival_time = sc.nextInt();
					System.out.println("  Enter process burst time");
					System.out.print("  ");
					int burst_time = sc.nextInt();
					Process p = new Process(pid,arrival_time,burst_time);
					input.add(p);  //adding the process to queue
					}
				}while(choice == 0);
				
				System.out.println("  Enter quantum for round robin algorithm ");//input quatum for round robin
				System.out.print("  ");
				int quantum = sc.nextInt();

				ArrayList<Process> CpuRrL = new ArrayList<Process>();     //these array lists would be
				ArrayList<Process> SJFclassL = new ArrayList<Process>();  // used to copy process data
				ArrayList<Process> SFGpremL = new ArrayList<Process>();   // and then we will send each
				ArrayList<Process> FCFScpuL = new ArrayList<Process>();   // arraylist to each cpu class
				ArrayList<Process> RRclassL = new ArrayList<Process>();


				Iterator<Process> itr = input.iterator(); // creates an iterator
					while(itr.hasNext()) {
								Process p = new Process(itr.next());	// copies input array list					
							  	CpuRrL.add(p);  // to each of the above 5 arraylists which would 
										  }     // be passed to 5 cpus
					
					itr = input.iterator();		
					while(itr.hasNext()) {            // we are making new process because we dont want 
						Process p = new Process(itr.next());	// one class to inturupt with data of 					
					  	SJFclassL.add(p);      // other class
								  }

					itr = input.iterator();		
					while(itr.hasNext()) {
						Process p = new Process(itr.next());						
					  	SFGpremL.add(p);
								  }
					itr = input.iterator();		
					while(itr.hasNext()) {
						Process p = new Process(itr.next());						
					  	FCFScpuL.add(p);
								  }

					itr = input.iterator();		
					while(itr.hasNext()) {
						Process p = new Process(itr.next());						
					  	RRclassL.add(p);
								  }

				CpuRr cpurr = new CpuRr(CpuRrL);     // passing data to cpus
				SJFclass sjfclass = new SJFclass(SJFclassL);
				RRclass rrclass = new RRclass(RRclassL,quantum);
				FCFScpu fcfscpu = new FCFScpu(FCFScpuL);
				System.out.println("");
				SFGprem sfgprem = new SFGprem(SFGpremL);
				System.out.println("");
				cpurr.run();
				sjfclass.run();
				rrclass.run();
					float awt = 0, att = 0;
					float tottt=0,totwt=0;
					int n = input.size();
					itr = SJFclassL.iterator();		
					while(itr.hasNext()) {
						Process p = itr.next();						
					  	awt += p.waiting_time;
					  	att += p.turnaround_time;
								  }
						awt = awt/(float)n;
						att = att/(float)n;
					itr = SJFclassL.iterator();		
					while(itr.hasNext()) {
						Process p = itr.next();						
					  	totwt += (p.waiting_time-awt)*(p.waiting_time-awt);
					  	tottt += (p.turnaround_time-att)*(p.turnaround_time-att);
								  }
						tottt = tottt/(float)n;
						totwt = totwt/(float)n;
					System.out.println("  Average waiting time for SJF without premption = " +
			                          awt );
			        System.out.println("  Average turn around time for SJF without premption = " +
			                           att );
					System.out.println("  the square of standard deviation of turnaround time :" + tottt);
     				System.out.println("  the square of standard deviation of waiting time :"+ totwt);
				System.out.println("");

			        awt = 0; att =0;tottt=0;totwt=0;
			        itr = RRclassL.iterator();		
					while(itr.hasNext()) {
						Process p = itr.next();						
					  	awt += p.waiting_time;
					  	att += p.turnaround_time;
								  }
						awt = awt/(float)n;
						att = att/(float)n;
						itr = RRclassL.iterator();		
					while(itr.hasNext()) {
						Process p = itr.next();						
					  	totwt += (p.waiting_time-awt)*(p.waiting_time-awt);
					  	tottt += (p.turnaround_time-att)*(p.turnaround_time-att);
								  }
						tottt = tottt/(float)n;
						totwt = totwt/(float)n;
					System.out.println("  Average waiting time for Round Robin with quantum "+quantum +" = " +
			                          awt);
			        System.out.println("  Average turn around time for Round Robin with quantum "+quantum+" = " +
			                           att );
			     System.out.println("  the square of standard deviation of turnaround time :" + tottt);
    			 System.out.println("  the square of standard deviation of waiting time :"+ totwt);
				System.out.println("");
			        awt = 0; att =0;tottt=0;totwt=0;
			        itr = CpuRrL.iterator();		
					while(itr.hasNext()) {
						Process p = itr.next();						
					  	awt += p.waiting_time;
					  	att += p.turnaround_time;
								  }
						awt = awt/(float)n;
						att = att/(float)n;
						itr = CpuRrL.iterator();		
					while(itr.hasNext()) {
						Process p = itr.next();						
					  	totwt += (p.waiting_time-awt)*(p.waiting_time-awt);
					  	tottt += (p.turnaround_time-att)*(p.turnaround_time-att);
								  }
						tottt = tottt/(float)n;
						totwt = totwt/(float)n;
					System.out.println("  Average waiting time of our proposed algorithm is = " +
			                          awt);
			        System.out.println("  Average turn around time of our proposed algorithm is = " +
			                           att);
			        System.out.println("  the square of standard deviation of turnaround time :" + tottt);
     				System.out.println("  the square of standard deviation of waiting time :"+ totwt);
				System.out.println("");

	}
}