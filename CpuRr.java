package OSAssignment;

import java.util.*;

class CpuRr{
	/*
		we are making two array list one will store process according 
		to the arrival time(Buffer) other will sort them basis 
		on burst time(Ready) after it will use round robin type algo
	*/

	ArrayList<Process> Ready = new ArrayList<Process>();
	ArrayList<Process> Buffer= new ArrayList<Process>();
	/* current_time is time tracker*/
	public int current_time = 0;
	int quant,checker;
	/*constructor inputs a arraylist of processes and send them in buffer queue*/
	CpuRr(ArrayList<Process> b){
			Iterator<Process> itr = b.iterator();
			while(itr.hasNext()) {							
					  	Buffer.add(itr.next());
								  }
							}
	/*this method we will call to start the scheduling algorithm*/
	 	void run(){
		/*here we sort the Buffer list on the basis of arrival time */
				Collections.sort(Buffer,new Comparator<Process>(){
								public int compare(Process p , Process q){
								return Integer.valueOf(p.arrival_time).compareTo(q.arrival_time);
								}
							});
											 
				/*
				in the below code fragment the first process from buffer queue is send 
				to ready queue and cpu is alloted to the first process for time equal to 
				its burst time 
				notice current_time is set to the sum of process burst time and arrival time
				end_time of process is set to current time
				*/
				Process current;
				current = Buffer.get(0);
				current_time = current.burst_time + current.arrival_time;
				current.end_time = current_time;
				current.waiting_time = current.end_time - current.init_arrival_time - current.init_burst_time;
				current.turnaround_time = current.end_time - current.init_arrival_time;
				Ready.add(current);
				Buffer.remove(0);
 				Ready.remove(0);
			 		 	
 				/*Buffer will be empty if there is only one process so we check 
 				the possiblity and then go to the following algorithm*/
			    if(!Buffer.isEmpty()){
			    	/*do while loop is used because we have to run this algo atleast once and 
			    	then run untill Buffer queue is not empty */
				 	do{
				 		/*the following code fragment sorts the Buffer list according to arrival 
				 		time these arrival time are initially initial arrival time but as algorithm
				 		progresses these are time when process arrives to Buffer queue*/
				 		Collections.sort(Buffer,new Comparator<Process>(){
						public int compare(Process p , Process q){
						return Integer.valueOf(p.arrival_time).compareTo(q.arrival_time);
						}
						});
				 		/*
				 		the below for loop adds all the process with arrival time less 
				 		than current time to the ready queue and then removes them from the 
				 		buffer queue
				 		*/
						 for (;!(Buffer.isEmpty()) ; ) {
						  	Process p = Buffer.get(0);
							if(p.arrival_time<=current_time){
							Ready.add(p);
							Buffer.remove(0);
							}
							else break;
							}
							if(!Buffer.isEmpty()&&Ready.isEmpty())current_time++;
				/*sorting the ready queue with respect to burst time of processes*/		
				/*these are burst times which are left for final execution*/		  
						Collections.sort(Ready,new Comparator<Process>(){
						public int compare(Process p , Process q){
						return Integer.valueOf(p.burst_time).compareTo(q.burst_time);
						}
						}); 
						/*
						the below for loop calculates the quantum and the checker
						quantum is equal to ceiling of average burst time of the processes and the 
						checker is equal to half of quantum 
						*/								 											
						for (int i =0;i<Ready.size() ; i++) {
							Process p = Ready.get(i);
							quant+= p.burst_time;
						}
						if(Ready.size()!=0&&quant%Ready.size()==0) quant = (quant/Ready.size());
						else if(Ready.size()!=0) quant = (quant/Ready.size())+1;
						checker = (quant/2) + 1;
						/*this for loop is our main algorithm*/
						for (;!(Ready.isEmpty()) ; ) {
							Process p = Ready.get(0);
							/*if the process burst time is smaller than quantum no need 
							to give full quantum */							
							if(p.burst_time<quant){
								current_time += p.burst_time;
								p.end_time = current_time;
								p.waiting_time = p.end_time - p.init_arrival_time - p.init_burst_time;
								p.turnaround_time = p.end_time - p.init_arrival_time;
								Ready.remove(0);
							}else {
								/*
								now first assign the cpu for a quantum then check if remaining 
								time is less than checker if it is not then re initialize the arrival time 
								and then send it to buffer queue
								process burst time is re initialised if necessary current time is always updated 
								also end time is set to current time whenever process is ending
								*/
								p.burst_time = p.burst_time - quant;
								current_time+= quant;
								if (p.burst_time<checker) {
									current_time += p.burst_time;
									p.end_time = current_time;
									p.waiting_time = p.end_time - p.init_arrival_time - p.init_burst_time;
									p.turnaround_time = p.end_time - p.init_arrival_time;
									Ready.remove(0);
								}else{
									p.arrival_time=current_time;
									Buffer.add(p);
									Ready.remove(0);
									}
								}
									  	
						  }/*end of the for block*/
						  
					 	/* whenever process finishes it is removed from both queue so 
					 	size of buffer queue is decreasing as algrithm proceeds it will 
					 	eventually get empty */	 		
					 	}while(!Buffer.isEmpty());}

			}/*end of the run function block*/
	}