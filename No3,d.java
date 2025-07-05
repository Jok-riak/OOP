package airqo;

import java.util.Arrays;
import java.util.Random;

public class AirQOAnalyzer {
public static void main(String[] args) {
int[] aqi = new int[30];
Random rand = new Random();

for (int i = 0; i < aqi.length; i++) {
aqi[i] = rand.nextInt(300) + 1;
}

System.out.println("AQI Readings:");
System.out.println(Arrays.toString(aqi));

Arrays.sort(aqi);
double median = (aqi[14] + aqi[15]) / 2.0;
System.out.println("Median AQI: " + median);

int hazardousDays = 0;
for (int value : aqi) {
if (value > 200) hazardousDays++;
}
System.out.println("Hazardous Days: " + hazardousDays);
}
}