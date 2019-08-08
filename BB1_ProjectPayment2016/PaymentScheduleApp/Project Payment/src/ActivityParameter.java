/**
 *  This class provide some method to process
 *  the data.
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Bui Duc Hung - HUST - SOICT - K57 
 * @author Le Tuan Dung - HUST - SOICT - K57
 * @since 20-9-2015
 * @version 1.0
 */

public class ActivityParameter {
	
	private int budget ;
	private float discountRate ;
	private int[][] activityParam ;
	private int numberActivities ;
	private int costUnit1 ;
	private int costUnit2 ;
	private int costResource1 ;
	private int costResource2 ;
	private int[][] timeAndCost ;									
	
	/**
	 * Collect the data from file called
	 * "Activity_Data.dat"
	 */
	public void receiveActivityParameter() {
		
		try {
			InputStream inputStream = 
					getClass().getResourceAsStream("/Activity_Data.dat") ;
			InputStreamReader inputStreamReader =
					new InputStreamReader( inputStream ) ;
			BufferedReader buffer =
					new BufferedReader( inputStreamReader ) ;
			
			String firstLine = buffer.readLine() ;
			String[] generalInfo  = firstLine.split(" ") ;
			numberActivities = Integer.parseInt( generalInfo[0] ) ;
			costUnit1 = Integer.parseInt( generalInfo[1] ) ;
			costUnit2 = Integer.parseInt( generalInfo[2] ) ;
			budget = Integer.parseInt( generalInfo[3] ) ;
			discountRate = Float.parseFloat( generalInfo[4] ) ;
			
			activityParam = new int[ numberActivities ][ 2 ] ;
			for( int i = 0 ; i < numberActivities ; i++ ) {
				String nextLine = buffer.readLine() ;
				String[] setOfActivityInfo = nextLine.split(" ") ;
				costResource1 = costUnit1 * Integer.parseInt( setOfActivityInfo[1] ) ;
				costResource2 = costUnit2 * Integer.parseInt( setOfActivityInfo[2] ) ;
				activityParam[i][0] = Integer.parseInt( setOfActivityInfo[0] ) ;
				activityParam[i][1] = costResource1 + costResource2 ;
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public int getBudget() {
		return budget ;
	}
	
	public void setBudget( int budget ) {
		this.budget = budget ;
	}
	
	public float getDiscountRate() {
		return discountRate ;
	}
	
	public void setDiscountRate( float discountRate ) {
		this.discountRate = discountRate ;
	}
	
	public int getNumberActivities() {
		return numberActivities ;
	}
	
	public void setNumberActivities( int numberActivities ) {
		this.numberActivities = numberActivities ;
	}
	/**
	 * Describe : Calculate cost and time of each stage of project
	 * @param activitiesSchedule : schedule of activity
	 * @return the array contain cost and time of each stage of project
	 */
	public int[][] calculateCostAndTimeOfEachNodeOfActivitiesList( int[] activitiesSchedule ) {
		
		int numberOfNode = activitiesSchedule.length ;
		timeAndCost = new int[ numberOfNode ][ 2 ] ;
		int offset = 0 ;
		int time ;
		int cost ;
		for( int i = 0 ; i < numberOfNode ; i++ ) {
			time = 0 ;
			cost = 0 ;
			for( int j = offset ; j < ( offset + activitiesSchedule[i] ) ; j++  ) {
				cost += activityParam[j][1] ;
				if( time < activityParam[j][0] )
					time = activityParam[j][0] ;
			}
			timeAndCost[i][0] = time ;
			timeAndCost[i][1] = cost ;
			offset += activitiesSchedule[i] ;
		}
		return timeAndCost ;
	}
}
