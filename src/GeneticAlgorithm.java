import java.util.*;

public class GeneticAlgorithm {
	
	public class myPair {
		int a = 0;
		String b = "";
		
		myPair(int c, String d){ 
			a = c;
			b = d;
		}
		
		myPair(){}
	}
	
	class sortByInt implements Comparator<myPair>{
		public int compare (myPair x, myPair z) {
			return x.a - z.a;
		}
	}
	
	int popSize = 50;
	String sentence = "MAKE MY COMPUTER SPEAK";
	final String alphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final int N = alphabet.length();
	
	public static void main(String[] args){
		new GeneticAlgorithm().run();
	}
	
	public void run() {
		//System.out.println("test");
		List<String> members = generateRandom();
		boolean stringNotFound = true;
		//System.out.println(members);
		System.out.println(members);
		while(stringNotFound) {
			members = selectMembers(members);
			members = reproduce(members); //Handles Mutation
			if(members.contains(sentence)) {
				stringNotFound = false;
			}
			System.out.println(members);
		}
		System.out.println("Left run");
		return;
	}

	public List<String> generateRandom() {
		int stringLen = sentence.length();

	    List<String> ret = new ArrayList<String>();
	    for(int j = 0; j < popSize; j++) {
	    	String genString = "";
	    	Random r = new Random();
		    for (int i = 0; i < stringLen; i++) {
		        genString += alphabet.charAt(r.nextInt(N));
		    }
		    ret.add(genString);
		}
	    
	    System.out.println("Left radomas");
	    return ret;
	}
	
	public List<String> selectMembers(List<String> pop) { //evaluates individuals and builds pool
		
		List<myPair> dynamicMembers = new ArrayList<myPair>();
		
		for(String member : pop) {
			int score = evaluate(member);
			myPair elem = new myPair(score, member);
			dynamicMembers.add(elem);
		}
		
		Collections.sort(dynamicMembers, new sortByInt());
		
		List<String> ret = new ArrayList<String>();
		for(myPair x : dynamicMembers.subList(0, dynamicMembers.size()/10)) {
			ret.add(x.b);
		}
		
		System.out.println("Left slect memberers");
		return ret;
	}
	
   public int evaluate(String x) {
	   String str1 = x;
	   String str2 = sentence;
	   int i = 0, count = 0;
	   while (i < str1.length()) {
		   if (str1.charAt(i) != str2.charAt(i))
			   count++;
	       i++;
	   }
	   
	   System.out.println("Left evaluate");
	   return count;
	   
    }
	

	public List<String> reproduce(List<String> pop){ //reproduces by combining two random parents and halfsies
		
		List<String> newPopulation = new ArrayList<String>();
		while(newPopulation.size() <= popSize) {
			
			Random r = new Random();
			int choose = r.nextInt(pop.size());
			int choose2 = r.nextInt(pop.size());
			//select two random
			String p1 = pop.get(choose);
			String p2 = pop.get(choose2);
			
			//combine
			String newStr = p1.substring(0, p1.length()/2) + p2.substring(p1.length()/2);
			
			
			//Mutate
			int size = newStr.length();
			char [] newStringArr = newStr.toCharArray();
			for(int i = 0; i < size; i++) {
				if (r.nextInt(10) == 0) {
					newStringArr[i] = alphabet.charAt(r.nextInt(N));
				}
			}
			newStr = String.valueOf(newStringArr);
			
			
			newPopulation.add(newStr);
		}
		
		
		//System.out.println("Left reproduction");
		return newPopulation;
	}
	
}
