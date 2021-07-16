package OSAssignment;
import java.util.*;

public class RRclass{
	ArrayList<Process> Ready = new ArrayList<Process>();
	ArrayList<Process> Buffer= new ArrayList<Process>();
	public int quantum;
	RRclass(ArrayList<Process> b, int quantum){
		Iterator<Process> itr = b.iterator();
			while(itr.hasNext()) {							
					  	Buffer.add(itr.next());
								  }
			this.quantum = quantum;
			}
	public int current_time = 0;

		void run(){
			do{
			Collections.sort(Buffer,new Comparator<Process>(){
				public int compare(Process p , Process q){
				return Integer.valueOf(p.arrival_time).compareTo(q.arrival_time);
				}
			});
			current_time = Buffer.get(0).arrival_time;
						
			for (;!(Buffer.isEmpty()) ; ) {
						  	Process p = Buffer.get(0); 				
							if(p.arrival_time<=current_time){ 
							Ready.add(p);
							Buffer.remove(0);
							}
							else break;
							}

							if(!(Ready.isEmpty())){
				for (;!(Ready.isEmpty()) ; ) {
								
							Process p = Ready.get(0);
												
							if(p.burst_time<quantum){
								current_time += p.burst_time;
								p.end_time = current_time;
								p.waiting_time = p.end_time - p.init_arrival_time - p.init_burst_time;
								p.turnaround_time = p.end_time - p.init_arrival_time;
								Ready.remove(0);
							}else {
								
								p.burst_time = p.burst_time - quantum;
								current_time+= quantum;
								p.arrival_time=current_time;
								Buffer.add(p);
								Ready.remove(0);
								}
							}}else current_time++;
							}while(!Buffer.isEmpty());		  	
				 }
		}
	
