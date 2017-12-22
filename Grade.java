package project;

public class Grade {
	
	public String gradeType;
	public int grade;
	public Connection connection = null;
	
	int[] grades;//array where grades will be stored
    int numGrades;//will determine the size of the array.
    double gradeAverage; //will be set equal to the average method.
    int total;//will be set equal to the sum method.

    String cont = "";//empty string

	
	public Connection getConnection() {

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test_db", "root", "");
			return connection;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	}


	@Override
	public String toString() {
		return "Grade [gradeType=" + gradeType + ", grade=" + grade + "]";
	}
	
	
	//--------methodsss-----
	
	public int sum(int[] gradeArray, int size) {
		
		int sum = 0;
        int [] temp = gradeArray;	//tem array is set equal to original array
        
        for (int i = 0; i<gradeArray.length; i++){
            sum +=temp[i];	//array numbers are added to calculate sum
        }
        return sum;	//calculated sum is returned
		
	}
	
	public double average(int[] gradeArray, int size) {
		double average;
		int[] temp = gradeArray;
	    int sum = 0;
		for(int i=0 ; i<gradeArray.length ; i++) {
			sum += temp[i];
		}
		average = (double)sum/(double)size;
        return average; 
	}
	
	public String letterGrade(double gradeAverage){
		String letterGrade = "";

        int quotient = (int)gradeAverage/10; //quotient will deterimine LetterGrade
        int remainder = (int)gradeAverage%10; // remainder will determine if a "+ or -"

        switch(quotient){//switch statement for cases if grade is A, B, C, D, F
        case 10://100% so just return A+;
            return "A+";
        case 9: //90-89  A
            letterGrade="A";
            break;
        case 8: //80-89  B
            letterGrade = "C";
            break;
        case 7: //70-79  C
            letterGrade="C";
            break;
        case 6: //60-69  D :
            letterGrade = "D";
            break;
        default:
            return "F";

        }

        switch (remainder){ //remainder switch statement will be used to 
        //determine if a + or a - is added to the letter grade. 
        //these are the cases that will receive "+"
        case 0: case 1: case 2: case 3: case 4:
            letterGrade+="-";
            break;
        case 7: case 8: case 9: 
            letterGrade +="+";
        }

        return letterGrade;//letter grade with final + or _
    }
		
		//DashLine method will print out a dash line for better readability
	    public void DashLine(){
	        for(int i = 0; i<20; i++){
	            System.out.print("-");
	        }
	    }
	
	    
	    public void showinfo() {
	    	
	    	do{//loop will run as long as 'y' is entered

	            System.out.print("HOW MANY GRADES DO YOU WISH TO ENTER?: ");

	            numGrades = kbd.nextInt();
	            grades = new int[numGrades];
	            System.out.print("\n\nPLEASE ENTER " + numGrades + " GRADES:\n");


	            for(int i = 0; i<grades.length; i++){

	                grades[i]=kbd.nextInt();
	                if(grades[i]>100 || grades[i]<0){
	                    System.out.println("GRADES CAN NOT EXCEED 100 or BE LESS THAN 1");
	                    i--;
	                }               

	            }

	            total = sum(grades, numGrades);//calls on sum method
	            gradeAverage = average(grades,numGrades);//calls on average method


	            DashLine();//prints dashlines

	            System.out.print("\nThe Total Sum is: " + total + "\n");

	            System.out.printf("The Average is: %.2f \n",  gradeAverage);

	            String ltrGrade;

	            ltrGrade= letterGrade(gradeAverage);

	            if(!(ltrGrade.equalsIgnoreCase("F"))){//test if ltr grade is F, 
	                //if its not F, print the following
	                System.out.println("\nYour Final Letter Grade is a: "+ ltrGrade
	                        +"\nYOU PASS!!");

	            }
	            else{//failing grade prints this.
	                System.out.println("\nYour Final Letter Grade is a: "+ ltrGrade
	                        +"\nYOUR PRETTY DUMB!!");           
	            }

	            DashLine();//prints dash lines

	            System.out.println("\n\nDO YOU WANT TO ENTER MORE GRADES? Y or N");
	            cont = kbd.next();
	            for(int j = 0; j< 80; j++){//simulates clear screen.
	                System.out.println();
	            }

	            }while(cont.equalsIgnoreCase("y"));

	            kbd.close();
	        }
	    	
	    }
	    	
	    	
	


	
	
	
	

}
