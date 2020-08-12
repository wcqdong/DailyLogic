package com.wcq.vector;

public class Line {
    final double a;
    final double b;
    final double c;

    final Vector2D point1;
    final Vector2D point2;
    public Line(Vector2D point1, Vector2D point2){
        this.point1 = point1;
        this.point2 = point2;

        a = point2.y - point1.y;
        b = point1.x - point2.x;
        c = point2.x*point1.y - point1.x*point2.y;
    }

    /**
     * 计算两个直线交点
     *  根据克莱姆法则
     *  a1*x + b1*y + c1 = 0
     *  a2*x + b2*y + c2 = 0
     *
     *   a1*x + b1*y = -c1
     *   a2*x + b2*y = -c2
     *
     *  d = a1*b2 - b1*a2
     *  x = (b1*c2 - b2*c1) / d
     *  y = (a2*c1 - a1*c2) / d
     * @param line
     * @return
     */
    public Vector2D lineSectionPoint(Line line){
        double d = a*line.b - b*line.a;
        if(d == 0){
            return null;
        }
        double x = (b*line.c - line.b*c) / d;
        double y = (line.a*c - a*line.c) / d;
        return new Vector2D(x, y);
    }

    /**
     * 计算两个线段交点
     * @param line
     * @return
     */
    public Vector2D segmentSectionPoint(Line line){
        Vector2D section = lineSectionPoint(line);
        // 不相交
        if(section == null){
            return null;
        }

        // 判断是否在线段的两点之间
        double minX = Math.min(point1.x, point2.x);
        double maxX = Math.max(point1.x, point2.x);
        double minY = Math.min(point1.y, point2.y);
        double maxY = Math.max(point1.y, point2.y);
        if(section.x < minX || section.x > maxX || section.y < minY || section.y > maxY){
            return null;
        }

        minX = Math.min(line.point1.x, line.point2.x);
        maxX = Math.max(line.point1.x, line.point2.x);
        minY = Math.min(line.point1.y, line.point2.y);
        maxY = Math.max(line.point1.y, line.point2.y);
        if(section.x < minX || section.x > maxX || section.y < minY || section.y > maxY){
            return null;
        }
        return section;
    }

    /**
     * 是否平行
     * @param line
     * @return
     */
    public boolean isParallel(Line line){
        double d = a*line.b - b*line.a;
        return d == 0 ? false : true;
    }

}
