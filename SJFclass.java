package OSAssignment;
import java.util.*;

public class SJFclass{
	ArrayList<Process> Ready = new ArrayList<Process>();
	ArrayList<Process> Buffer= new ArrayList<Process>();
	
			public SJFclass(ArrayList<Process> R)
			{
			  	Iterator<Process> itr = R.iterator();
				while(itr.hasNext()) {                            
		    		Buffer.add(itr.next());
		        	}
		    	}
			
	public int current_time = 0;
	
	public void run(){
	Collections.sort(Buffer,new Comparator<Process>(){
    		public int compare(Process p , Process q){
    		return Integer.valueOf(p.arrival_time).compareTo(q.arrival_time);
                        }
                });
        
                
        current_time = Buffer.get(0).arrival_time;
        
        do{
       
        
		for (;!(Buffer.isEmpty()) ; ) {			//Moves the processes from Buffer Queue to Ready Queue
				Process p = Buffer.get(0);
				if(p.arrival_time<=current_time){
				Ready.add(p);
				Buffer.remove(0);
				}
				else break;
			}
			
		Collections.sort(Ready,new Comparator<Process>(){			//Sorts the processes in Ready Queue based on burst time
	    		public int compare(Process p , Process q){
	    		return Integer.valueOf(p.burst_time).compareTo(q.burst_time);
		                }
		        });
		        
		if(!(Ready.isEmpty()))
		{
			Process p = Ready.get(0);		
			current_time+= p.burst_time;
			p.end_time = current_time;
			p.waiting_time = p.end_time - p.init_arrival_time - p.init_burst_time;
			p.turnaround_time = p.end_time - p.init_arrival_time;
			Ready.remove(0);
		}
		else
		{
			current_time++;
		}
		
        }while(!(Buffer.isEmpty() && Ready.isEmpty()) );
    }
        
}




