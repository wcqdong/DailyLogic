package com.wcq.vector;

public class PointUtils {

    public static void main(String[] args) {
//        Vector2D center = new Vector2D(0, 0);
//        Vector2D dir = new Vector2D(1, 1);
//        Vector2D pos = new Vector2D(1, 1);
//
//        Vector2D result = transformBoePoint(center, dir, pos);
//        System.out.println(result);

        Line line1 = new Line(new Vector2D(3, 3), new Vector2D(4, 4));
        Line line2 = new Line(new Vector2D(3, 3), new Vector2D(5, 5));
        System.out.println(line1.lineSectionPoint(line2));
        System.out.println(line1.segmentSectionPoint(line2));
    }


    public Vector3D transformBoePoint(Vector3D center, double rotation, double scale, double dx, double dy) {
        double A = scale * Math.cos(rotation);
        double B = scale * Math.sin(rotation);
        return new Vector3D(A * center.getX() - B * center.getY() + dx, B * center.getX() + A * center.getY() + dy, 0.0);
    }

    public static Vector2D transformBoePoint(Vector2D center, Vector2D dir, Vector2D pos) {
        double dx = pos.x - center.x;
        double dy = pos.y - center.y;
        double d = Math.sqrt(dir.x * dir.x + dir.y * dir.y);
        double cos = dx / d;
        double sin = dy / d;
        return new Vector2D(cos * center.x - sin * center.y + dx, sin * center.x + cos * center.y + dy);
    }
    /**
     * 保留小数点后六位
     *
     * @param num
     * @return
     */
    public static double retain6(double num) {
        String result = String.format("%.6f", num);
        return Double.valueOf(result);
    }
}
