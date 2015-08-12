import javafx.geometry.Point2D;

/**
 *
 * @author Arun Kumar
 */
public class BallPoolGame {

    private Pocketlesstable table;

    public BallPoolGame(Point2D point) {
        table = new Pocketlesstable(new Point2D(0, 0), new Point2D(0, point.getY()), new Point2D(point.getX(), point.getY()), new Point2D(point.getX(), 0));
    }

    private Point2D CalculatePoint_AlongLine(Point2D ballPosition, Point2D pointOnCushion, int distance) {
        // a. calculate the vector from o to g:
        double vectorX = pointOnCushion.getX() - ballPosition.getX();
        double vectorY = pointOnCushion.getY() - ballPosition.getY();
        // b. calculate the length:
        double magnitude = Math.sqrt(vectorX * vectorX + vectorY * vectorY);
        // c. normalize the vector to unit length:
        vectorX /= magnitude;
        vectorY /= magnitude;

        // d. calculate and Draw the new vector, which is x1y1 + vxvy * (mag + distance).
        Point2D point = new Point2D(
                (int) ((double) ballPosition.getX() + vectorX * distance) // x = col
                , (int) ((double) ballPosition.getY() + vectorY * distance) // y = row
        );

        return point;
    }

    private int calculateDistance(int speedOfBall, int travelTime) {
        return travelTime * speedOfBall;
    }

    public void calculateFinalPosition(Point2D ballPosition, Point2D pointOnCushion, int speedOfBall, int travelTime) {
        //calcualte total distance to travel
        int distance = calculateDistance(speedOfBall, travelTime);
        //calculate the point
        Point2D point = CalculatePoint_AlongLine(ballPosition, pointOnCushion, distance);

        int X = (int) point.getX();
        int Y = (int) point.getY();
        X = X < 0 ? X * (-1) : X;
        Y = Y < 0 ? Y * (-1) : Y;

        if (X > getTable().getPoint3().getX()) {
            X = (int) (X % getTable().getPoint3().getX());
            X = (int) (getTable().getPoint3().getX() - X);
        }

        if (Y > getTable().getPoint2().getY()) {
            Y = (int) (Y % getTable().getPoint2().getY());
            Y = (int) (getTable().getPoint2().getY() - Y);
        }
        System.out.println("final position of the ball: ("
                + X + "," + Y + ")");
    }

    private Point2D rotate_point(float cx, float cy, float angle, Point2D p) {
        double s = Math.sin(angle);
        double c = Math.cos(angle);

        // translate point back to origin:
        Point2D point = new Point2D(p.getX() - cx, p.getY() - cy);

        // rotate point
        double xnew = point.getX() * c - point.getY() * s;
        double ynew = point.getX() * s + point.getY() * c;

        // translate point back:
        return new Point2D(xnew + cx, ynew + cy);

    }

    /**
     * @return the table
     */
    public Pocketlesstable getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(Pocketlesstable table) {
        this.table = table;
    }

}
