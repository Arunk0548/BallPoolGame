import javafx.geometry.Point2D;

/**
 *
 * @author Arun Kumar
 */
public class Pocketlesstable {
    
    private Point2D point0;
    private Point2D point1;
    private Point2D point2;
    private Point2D point3;
    
    public Pocketlesstable(Point2D point0,Point2D point1,Point2D point2,Point2D point3)
    {
        this.point0 = point0;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    /**
     * @return the point0
     */
    public Point2D getPoint0() {
        return point0;
    }

    /**
     * @param point0 the point0 to set
     */
    public void setPoint0(Point2D point0) {
        this.point0 = point0;
    }

    /**
     * @return the point1
     */
    public Point2D getPoint1() {
        return point1;
    }

    /**
     * @param point1 the point1 to set
     */
    public void setPoint1(Point2D point1) {
        this.point1 = point1;
    }

    /**
     * @return the point2
     */
    public Point2D getPoint2() {
        return point2;
    }

    /**
     * @param point2 the point2 to set
     */
    public void setPoint2(Point2D point2) {
        this.point2 = point2;
    }

    /**
     * @return the point3
     */
    public Point2D getPoint3() {
        return point3;
    }

    /**
     * @param point3 the point3 to set
     */
    public void setPoint3(Point2D point3) {
        this.point3 = point3;
    }
}
