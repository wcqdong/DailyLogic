package com.wcq.utils;

public class Vector3D {
    public final double x;
    public final double y;
    public final double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D sub(Vector3D other) {
        return new Vector3D(x - other.x, y - other.y, z - other.z);
    }

    /**
     * Vector3D -> Vector2D
     * @return
     */
    public Vector2D toVector2D() {
        return new Vector2D(x, y);
    }
}
