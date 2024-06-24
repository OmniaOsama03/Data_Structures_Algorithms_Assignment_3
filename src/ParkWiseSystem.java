 import java.util.*;

public class ParkWiseSystem {

    //Relevant Attributes:
    private BST_ParkingSpace parkingLot;
    private int initialSpots;
    private BST_ParkingSpace UnoptimizedLot; //A copy of the parkingLot BST, without optimizations made.


    // Constructor for ParkwiseSystem
    public ParkWiseSystem(int initialSpots)
    {
        this.parkingLot = new BST_ParkingSpace();
        this.UnoptimizedLot = new BST_ParkingSpace();
        this.initialSpots = initialSpots;
    }

    //Getter
    public BST_ParkingSpace getParkingLot()
    {
        return this.parkingLot;
    }

    public void processData() { //Creating and storing x Parking Spaces, their attributes randomized.

        ParkingSpace[] temp = new ParkingSpace[initialSpots];

        for (int x = 0; x < initialSpots; x++) //Inserting the same set of ParkingSpace objects into both BSTs.
        {
            ParkingSpace spot = new ParkingSpace();
            parkingLot.insert(spot);
            UnoptimizedLot.insert(spot);
        }
    }


    // Method to analyze parking data
    public void analyzeParkingData() {
        if (parkingLot == null || parkingLot.isEmpty()) {
            System.out.println("No parking data available.");
            return;
        }

        double avgOccupancy = (int) (parkingLot.sum() / parkingLot.getSize() * 100.0) / 100.0; //Calculating the avg rate, formatted to 2 decimal places

        System.out.println("\n \t \t \t ---ANALYSIS OF PARKING SPOTS---");
        System.out.println("Average Occupancy Rate: " + avgOccupancy);

        System.out.println("\n Analyzing Parking Spaces:");
        analyzeNode(parkingLot.getRoot(), avgOccupancy);
    }

    private void analyzeNode(TreeNode<ParkingSpace> node, double avgOccupancy) {
        if (node != null) {
            double occupancyRate = node.element.getOccupancyRate(); //Occupancy rate of current node.

            System.out.print("Parking Space with id " + node.element.getID() + " has an occupancy rate of " + node.element.getOccupancyRate());

            //Analysis done based on occupancy rate vs the average calculated rate:
            if (occupancyRate < avgOccupancy)
            {
                System.out.println(" needs to be optimized.");
                System.out.println("Recommendation: Consider repurposing this area for other uses." + "\nRegion in parking lot: " + node.element.getArea());

            } else if (occupancyRate > avgOccupancy)
            {
                System.out.println(" has high demand in that area.");
                System.out.println("Recommendation: Allocate more resources or spaces in this area." + "\nRegion in parking lot: " + node.element.getArea());

            } else
            {
                System.out.println(" is at the average occupancy rate.");
                System.out.println("Recommendation: Monitor and maintain the current occupancy rate in this area." + "\nRegion in parking lot: " + node.element.getArea());
            }

            System.out.println("-------------------------------------------------------"); //Just formatting :D

            //Visiting the left and right nodes:
            analyzeNode(node.left, avgOccupancy);
            analyzeNode(node.right, avgOccupancy);

        }
    }


    // Method to optimize parking allocation
    public void optimizeParking() {
        System.out.println("\n \t \t \t ---PLAN TO OPTIMIZE PARKING LOT---");

        double threshold = 0.3; //Deciding on a threshold
        System.out.println("The threshold set is " + threshold);
        ArrayList<ParkingSpace> removedParkingSpaces = new ArrayList<>();

        optimizeNodes(parkingLot.getRoot(), threshold, removedParkingSpaces); //Will remove spaces with rates < threshold

        // After all low occupancy rates are removed:
        for (ParkingSpace space : removedParkingSpaces) {
            if (removedParkingSpaces.isEmpty()) {
                System.out.println("This parking lot cannot be optimized further. Wowww");
                break;
            }
            System.out.println("The area with ID " + space.getID() + " and occupancy rate " + space.getOccupancyRate() + " will be repurposed for other uses.");
        }
    }

    private void optimizeNodes(TreeNode<ParkingSpace> node, double threshold, ArrayList<ParkingSpace> removedParkingSpaces) {
        if (node != null) {
            optimizeNodes(node.left, threshold, removedParkingSpaces);

            double occupancyRate = node.element.getOccupancyRate();

            if (occupancyRate < threshold) {
                // If occupancy rate is less than the threshold, the node gets removed from the BST and added to the arraylist.
                parkingLot.delete(node.element);
                removedParkingSpaces.add(node.element);
            }

            //Visiting all left and right nodes:
            optimizeNodes(node.left, threshold, removedParkingSpaces);
            optimizeNodes(node.right, threshold, removedParkingSpaces);
        }
    }

    // Method to visualize the parking allocation
    public void displayOptimizedParking() {
        System.out.println("\n \t \t \t ---VISUALIZING THE OPTIMIZED PARKING LOT---");
        if (parkingLot.equals(UnoptimizedLot)) {
            System.out.println("This tree cannot be optimized further. so cooool");
            parkingLot.print(); //Found in the BST_ParkingSpace class
            return;
        }

        System.out.println("\n \n ---Before Optimization: ---");
        UnoptimizedLot.print();

        System.out.println("\n \n---After Optimization: ---");
        parkingLot.print();
    }

    // Main method
    public static void main(String[] args) {
        ParkWiseSystem parkwise = new ParkWiseSystem(10);

        parkwise.processData();
        parkwise.analyzeParkingData();
        parkwise.optimizeParking();
        parkwise.displayOptimizedParking();
    }
}

