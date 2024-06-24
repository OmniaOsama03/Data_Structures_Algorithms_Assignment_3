import java.util.*;
public class ParkingSpace implements Comparable<ParkingSpace>
{
    //Relevant attributes
    private String ID;
    private double occupancyRate;
    private boolean isReserved;
    private String area;
    private double costPerHour;

    //To assist in generating random values:
    Random random = new Random();
    int randomID = 1000 + random.nextInt(9999); //Restricted to 4 decimal numbers
    double randomCost = (int)((3.0 + (2.0 * random.nextDouble())) * 10.0) / 10.0; //Restricted from 3.0/hr to 5.0/hr. Formatted to one decimal place.
    double randomRate = (int)(random.nextDouble() * 100.0) / 100.0;


    //Constructor initializing randomized values
    ParkingSpace()
    {
        this.ID = "PS" + randomID;
        this.occupancyRate = randomRate;
        this.isReserved = false;
        this.area = randomizeArea();
        this.costPerHour = randomCost;
    }

    //Setters and Getters:
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setOccupancyRate(double occupancyRate) {
        this.occupancyRate = occupancyRate;
    }

    public double getOccupancyRate() {
        return occupancyRate;
    }

    public void setReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
    }

    public double getCostPerHour() {
        return costPerHour;
    }



    //Method to randomize the area:
    private String randomizeArea()
    {
        int num = 1 + random.nextInt(8); //Will decide the general area in the parking lot.

        switch (num) {
            case 1:
                return "North";

            case 2:
                return "South";

            case 3:
                return "East";

            case 4:
                return "West";

            case 5:
                return "Northeast";

            case 6:
                return "Northwest";

            case 7:
                return "Southeast";

            case 8:
                return "Southwest";
        }

            return ""; //Practically unreachable.
    }


    @Override
    public int compareTo(ParkingSpace other) {
        if(this.occupancyRate > other.getOccupancyRate())
            return 1;
        else if (this.occupancyRate < other.occupancyRate)
            return -1;
        else
            return 0;
    }


    @Override
    public String toString()
    {
        return "ParkingSpace:" +
                "ID:" + ID + '\n' +
                "OccupancyRate:" + occupancyRate + '\n' +
                "IsReserved:" + isReserved + '\n' +
                "Area:" + area + '\n' +
                "CostPerHour: " + costPerHour ;
    }


}
