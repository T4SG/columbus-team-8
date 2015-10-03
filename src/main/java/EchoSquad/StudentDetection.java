public class StudentDetection {
	public boolean isStudentAtRisk(String[] studentData)
	{
		//Useful Student Data Metrics to check if they're performing well or not
		
		/*  0 - GPA
			1 - Attendance
			2 - Days Late Per Month
			3 - Number of Detentions
			
			(in real life we'd look at the statistics from the LBFF data
			and perform predictive analytics to see which features correlate
			the most with a student graduating highschool or not. Using Machine Learning
			methods like Linear Regression, Anomaly Detection (DBScan), SVMs, Neural Networks
			)
			
			Research we used!
			http://www.betterhighschools.org/pubs/ews_guide.asp
			
			For now we're just going to use benchmarks from research.
			GPA benchmark - 
				- Students with less than a 2.0 are very at risk for not graduating
			
			Attendance benchmark - 
				- Students with more than 10% are very at risk for not graduating
			
			Days Late Per Month -
				- Couldn't find data (but we'd use the data from the Lebron James Family Fund...)
				Good estimate is 5
				
			Number of Detentions - 
				- Couldn't find data (but we'd use the data from the Lebron James Family Fund...)
				Good estimate is 2
			*/
			
			if (studentData[0] < 2.0 || studentData[1] > .10 || studentData[2] > 5 || studentData[3] >= 2)
			{
				//This student is at risk
				return true
			}
		return false;
	}
}