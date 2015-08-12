import java.util.Scanner;
import javafx.geometry.Point2D;

/**
 *
 * @author Arun Kumar
 */
public class BallPoolGameRunner {

    BallPoolGame game;

    final int tableMaxX = 284;
    final int tableMaxY = 142;

    /**
     * Given three collinear points p, q, r, the function checks if point q lies
     * on line segment 'pr'
     *
     * @param p point p
     * @param q point q
     * @param r point r
     * @return
     */
    private boolean onSegment(Point2D p, Point2D q, Point2D r) {
        if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX())
                && q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY())) {
            return true;
        }
        return false;
    }

    private boolean betweenInclusive(double x, double min, double max) {
        return x >= min && x <= max;
    }

    /**
     * To find orientation of ordered triplet (p, q, r). The function returns
     * following values 0 --> p, q and r are collinear 1 --> Clockwise 2 -->
     * Counterclockwise
     *
     * @param p
     * @param q
     * @param r
     * @return
     */
    int orientation(Point2D p, Point2D q, Point2D r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX())
                - (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0) {
            return 0;  // colinear
        }
        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }

    /**
     * The function that returns true if line segment 'p1q1' and 'p2q2'
     * intersect.
     *
     * @param p1
     * @param q1
     * @param p2
     * @param q2
     * @return
     */
    boolean doIntersect(Point2D p1, Point2D q1, Point2D p2, Point2D q2) {
        // Find the four orientations needed for general and
        // special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4) {
            return true;
        }

        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) {
            return true;
        }

        // p1, q1 and p2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) {
            return true;
        }

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) {
            return true;
        }

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) {
            return true;
        }

        return false; // Doesn't fall in any of the above cases
    }

    /**
     * Returns true if the point p lies inside the polygon[] with n vertices
     *
     * @param polygon
     * @param n
     * @param p
     * @return
     */
    private boolean isInside(Point2D polygon[], int n, Point2D p) {
        // There must be at least 3 vertices in polygon[]
        if (n < 3) {
            return false;
        }

        // Create a point for line segment from p to infinite
        Point2D extreme = new Point2D(10000, p.getY());

        // Count intersections of the above line with sides of polygon
        int count = 0, i = 0;
        do {
            int next = (i + 1) % n;

            // Check if the line segment from 'p' to 'extreme' intersects
            // with the line segment from 'polygon[i]' to 'polygon[next]'
            if (doIntersect(polygon[i], polygon[next], p, extreme)) {
                // If the point 'p' is colinear with line segment 'i-next',
                // then check if it lies on segment. If it lies, return true,
                // otherwise false
                if (orientation(polygon[i], p, polygon[next]) == 0) {
                    return onSegment(polygon[i], p, polygon[next]);
                }

                count++;
            }
            i = next;
        } while (i != 0);

        // Return true if count is odd, false otherwise
        return count % 2 == 1;
    }

    public BallPoolGameRunner() {
        Scanner console = new Scanner(System.in);
        //initialize pocketlesstable co-ordinates
        Point2D pocketTabCords = new Point2D(tableMaxX, tableMaxY);
        game = new BallPoolGame(pocketTabCords);
        int ballX, ballY, pointX, pointY, speed, time;

        System.out.println("Enter initial position of ball :");
        ballX = console.nextInt();
        ballY = console.nextInt();

        Point2D[] polygon = {game.getTable().getPoint0(), game.getTable().getPoint1(),
            game.getTable().getPoint2(), game.getTable().getPoint3()};
        int n = polygon.length;
        Point2D ballPosition = new Point2D(ballX, ballY);

        if (!isInside(polygon, n, ballPosition)) {
            System.out.println("Invalid ball position, ball position should be inside the table");
            return;
        }

        System.out.println("Enter position of point on cushion:");
        pointX = console.nextInt();
        pointY = console.nextInt();
        Point2D point = new Point2D(pointX, pointY);
        if (!((betweenInclusive(point.getX(), game.getTable().getPoint0().getX(), game.getTable().getPoint3().getX())
                && betweenInclusive(point.getY(), game.getTable().getPoint0().getY(), game.getTable().getPoint1().getY()))
                && (onSegment(game.getTable().getPoint0(), game.getTable().getPoint1(), point)
                || onSegment(game.getTable().getPoint1(), game.getTable().getPoint2(), point)
                || onSegment(game.getTable().getPoint2(), game.getTable().getPoint3(), point)
                || onSegment(game.getTable().getPoint3(), game.getTable().getPoint0(), point)))) {
            System.out.println("Invalid point on cushion, Point should be on cushion");
            return;
        }

        System.out.println("Enter speed of ball (in cm/s).(Between 0 and 1000 cm/s):");
        speed = console.nextInt();

        if (speed < 0 || speed > 999) {
            System.out.println("Invalid speed");
            return;
        }

        System.out.println("Enter travelling time (in seconds):");
        time = console.nextInt();

        if (time < 0) {
            System.out.println("Invalid time");
            return;
        }

        game.calculateFinalPosition(ballPosition, point, speed, time);

    }

    public static void main(String[] args) {

        new BallPoolGameRunner();
    }
}
