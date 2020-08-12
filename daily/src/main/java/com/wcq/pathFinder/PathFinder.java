package com.wcq.pathFinder;

import com.wcq.utils.Vector3D;

import java.util.List;

public interface PathFinder {

    public Vector3D raycast(Vector3D start, Vector3D end);

    public Vector3D getHeight(Vector3D pos);

    public List<Vector3D> findPath(Vector3D start, Vector3D end);

}
