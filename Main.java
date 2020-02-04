/*
For this project we are given a number of co ordinants to deliver food, and tasked with finding the fasted time to complete all the deliveries. The below solution uses a 2D array to calculate the shortest distance from the current position to the next delivery. The output is the sequence by which to proceed with the deliveries
*/

class Main
{
  public static void main(String[] args)
  {
    double shop_lat = 53.38195;
    double shop_lon =  -6.59192;

    //Order Latitudes
    double[] lat = {53.3473, 53.37077, 53.35152, 53.37278, 53.40126, 53.34484, 53.34133, 53.34492, 53.29206, 53.36483, 53.33067, 53.36287, 53.37416, 53.39549, 53.33239, 53.34678, 53.36869, 53.37521, 53.33751, 53.37184, 53.36143, 53.37473, 53.34514, 53.36115, 53.39351, 53.33886, 53.36115, 53.37497, 53.37565, 53.3354, 53.36143, 53.36115, 53.39459, 53.37122, 53.36656, 53.36141, 53.37323, 53.36292, 53.38122, 53.35098, 53.34312, 53.34197, 53.37954, 53.33835, 53.36976, 53.37811, 53.39847, 53.38579, 53.37626, 53.37336, 53.33032, 53.37201, 53.37416, 53.36164, 53.37043, 53.35372, 53.2908, 53.39792, 53.39315, 53.34439, 53.33591, 53.3632, 53.36833, 53.35298, 53.38122, 53.33605, 53.33324, 53.38039, 53.36883, 53.35401, 53.34745, 53.39839, 53.3473, 53.29128, 53.36158, 53.3727, 53.35321, 53.31159, 53.36455, 53.39999, 53.37414, 53.37402, 53.38983, 53.34033, 53.28973, 53.36518, 53.36873, 53.33256, 53.37565, 53.38895, 53.34648, 53.35202, 53.37247, 53.37449, 53.36559, 53.39648, 53.33239, 53.39512, 53.33835, 53.37538};

    //Order Longitudes
    double[] lon = {-6.55057, -6.48279, -6.54989, -6.59611, -6.6683, -6.54766, -6.51856, -6.5557, -6.67685, -6.51278, -6.54686, -6.52468, -6.49494, -6.67647, -6.55163, -6.53415, -6.48314, -6.6103, -6.53173, -6.50065, -6.51849, -6.59338, -6.53615, -6.48907, -6.5542, -6.55468, -6.48907, -6.4991, -6.60716, -6.55111, -6.51849, -6.48907, -6.66995, -6.586, -6.49183, -6.51834, -6.58859, -6.50203, -6.59226, -6.54915, -6.54747, -6.55492, -6.58793, -6.53984, -6.59828, -6.57952, -6.66787, -6.58673, -6.59308, -6.48219, -6.55311, -6.48517, -6.49731, -6.50526, -6.48193, -6.54564, -6.67746, -6.6711, -6.66909, -6.53841, -6.53566, -6.51178, -6.50589, -6.54921, -6.59226, -6.53414, -6.53978, -6.59368, -6.51468, -6.54603, -6.53401, -6.66767, -6.55057, -6.67836, -6.50533, -6.58757, -6.55412, -6.60538, -6.51435, -6.66807, -6.60028, -6.49363, -6.5951, -6.54596, -6.67445, -6.48913, -6.49619, -6.55056, -6.60701, -6.60579, -6.54552, -6.55099, -6.60044, -6.60005, -6.51914, -6.66612, -6.55163, -6.67084, -6.53984, -6.60707};

    //Array to store all distances
    double distances[][] = new double[100][100];
    //Find Shortest distance between points
    double shortest_distance = Double.MAX_VALUE;
    //Keep track of orders
    int order_number = 0;
    int orders[] = new int[100];

    //Current Node (Location of drone)
    double current_lon = shop_lon;
    double current_lat = shop_lat;

    for(int i = 0; i < 100; i++)
    {
      for(int j = 0; j < 100; j++)
      {
        if(distances[i][j] == Double.MAX_VALUE)
        {
          continue;
        }
        else
        {
          distances[i][j] = haversine(current_lat, current_lon, lat[j], lon[j]);
        }
        

        // if(distances[i][j] == 0)
        // {
        //   continue;
        // }

        //Find and store the shortest distance from the drones location to the closest order
        if(distances[i][j] < shortest_distance)
        {
          shortest_distance = distances[i][j];
          order_number = j;
        }
      } //End Interior for loop

      //Set order to completed
      for(int y = 0; y < 100; y++)
      {
        distances[y][order_number] = Double.MAX_VALUE;
      }

      current_lat = lat[order_number];
      current_lon = lon[order_number];
      shortest_distance = Double.MAX_VALUE;

      //Keep track of delivery order
      orders[i] = order_number + 1;
    }

    //Print Solution String
    for(int i = 0; i < 100; i++)
    {
      if(i == 99)
      {
          System.out.println(orders[i]);
          continue;
      }
      System.out.print(orders[i] + ",");
    }

    System.out.println("DONE");


  }

  //Haversine formula 
  static double haversine(double lat1, double lon1, double lat2, double lon2)
  {
    //Distance between latitudes and longitudes 
    double dLat = Math.toRadians(lat2 - lat1); 
    double dLon = Math.toRadians(lon2 - lon1); 
  
    //Convert to Radians 
    lat1 = Math.toRadians(lat1); 
    lat2 = Math.toRadians(lat2); 
  
    //Formula
    double a = Math.pow(Math.sin(dLat / 2), 2) +  Math.pow(Math.sin(dLon / 2), 2) *  Math.cos(lat1) *  Math.cos(lat2); 
    //Radius of Earth
    double rad = 6371; 
    double c = 2 * Math.asin(Math.sqrt(a)); 
    return rad * c;  
  }
}