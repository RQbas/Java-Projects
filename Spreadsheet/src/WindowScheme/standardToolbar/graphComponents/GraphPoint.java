package WindowScheme.standardToolbar.graphComponents;

public class GraphPoint implements Comparable<GraphPoint> {
    private double x;
    private double y;

    public GraphPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + " " + y + " ";
    }

    @Override
    public int compareTo(GraphPoint gp) {
        return Double.compare(this.getX(), gp.getX());
    }
}
