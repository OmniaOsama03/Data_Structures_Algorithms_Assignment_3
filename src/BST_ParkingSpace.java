
//Omnia Osama Ahmed 1084505
public class BST_ParkingSpace extends BST<ParkingSpace> {
    public BST_ParkingSpace(){};

    public BST_ParkingSpace(ParkingSpace[] array) {
        super(array);
    }

    public double sum() {
        return sum(root);
    }

    protected double sum(TreeNode<ParkingSpace> root) {
        if (root == null) return 0.0;
        return root.element.getOccupancyRate() + sum(root.left)+ sum(root.right); //Modified to return the occupancy rate.
    }

    protected double sumLeaves(TreeNode<ParkingSpace> node) {
        if (node == null) return 0.0;
        if (node.isLeaf()) return node.element.getOccupancyRate(); //Modified to return the occupancy rate.
        return sumLeaves(node.left) + sumLeaves(node.right);
    }

    public double countOdd() {
        return countOdd(root);
    }

    protected double countOdd(TreeNode<ParkingSpace> node) {
        if (node == null) return 0.0;

        if (node.element.getOccupancyRate() % 2 != 0) //Modified to check the occupancy rate.
            return 1 + countOdd(node.left) + countOdd(node.right);
        else return 0 + countOdd(node.left) + countOdd(node.right);
    }


    public void print() { //This method was taken from the TreeNode, but modified +  prints ID & Occupancy rates of nodes
        print("", this.root, false);
    }

    public void print(String prefix, TreeNode<ParkingSpace> n, boolean isNodeRight) {
        if (n != null) {
            System.out.println(prefix + (isNodeRight? "|-- " : "└-- ") + n.element.getID() + "(" + n.element.getOccupancyRate() + ")");
            print(prefix + (isNodeRight ? "|   " : "    "), n.right, true);
            print(prefix + (isNodeRight ? "|  " : "    "), n.left, false);
        }
    }

}


