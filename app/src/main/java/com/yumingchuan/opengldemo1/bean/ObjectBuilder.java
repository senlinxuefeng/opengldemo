package com.yumingchuan.opengldemo1.bean;

import android.opengl.GLES20;

import com.yumingchuan.opengldemo1.utils.Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yumingchuan
 * @version v
 * @since 2019/5/17
 */
public class ObjectBuilder {
    private static final int FLOAT_PER_VERTEX = 3;
    private static float[] vertexData;
    private static int offset = 0;
    private static List<DrawCommand> drawList = new ArrayList<>();

    public ObjectBuilder(int sizeInVertex) {
        this.vertexData = new float[sizeInVertex * FLOAT_PER_VERTEX];
    }

    public static int sizeOfCircleInVertices(int numPoints) {
        return 1 + (numPoints + 1);
    }


    public static int sizeOfOpenCylinderInVertices(int numPoints) {
        return (numPoints + 1) * 2;
    }

    /**
     * 创建冰球
     *
     * @param puck
     * @param numPoint
     * @return
     */
    public static GenerateData createPuck(Geometry.Cylinder puck, int numPoint) {
        int size = sizeOfCircleInVertices(numPoint) + sizeOfOpenCylinderInVertices(numPoint);
        ObjectBuilder objectBuilder = new ObjectBuilder(size);
        Geometry.Circle puckTop = new Geometry.Circle(puck.center.translateY(puck.height / 2), puck.radius);
        objectBuilder.appendCircle(puckTop, numPoint);
        objectBuilder.appendOpenCylinder(puck, numPoint);
        return objectBuilder.build();
    }

    public static GenerateData createMallet(Geometry.Point center, int radius, float height, int numPoints) {
        int size = sizeOfCircleInVertices(numPoints) * 2 + sizeOfOpenCylinderInVertices(numPoints) * 2;
        ObjectBuilder objectBuilder = new ObjectBuilder(size);

        float baseHeight = height * 0.25f;

        Geometry.Circle baseCircle = new Geometry.Circle(center.translateY(-baseHeight), radius);
        Geometry.Cylinder baseCylinder = new Geometry.Cylinder(baseCircle.center.translateY(-baseHeight / 2f), radius, baseHeight);
        objectBuilder.appendCircle(baseCircle, numPoints);
        objectBuilder.appendOpenCylinder(baseCylinder, numPoints);

        float handlerHeight = height * 0.75f;
        float handlerRadius = radius / 3f;

        Geometry.Circle handlerCircle = new Geometry.Circle(center.translateY(height * 0.5f), handlerRadius);
        Geometry.Cylinder handlerCylinder = new Geometry.Cylinder(handlerCircle.center.translateY(-handlerHeight / 2f), handlerRadius, handlerHeight);
        objectBuilder.appendCircle(handlerCircle, numPoints);
        objectBuilder.appendOpenCylinder(handlerCylinder, numPoints);

        return objectBuilder.build();
    }


    public static void appendCircle(Geometry.Circle circle, int numPoints) {

        final int startVertex = offset / FLOAT_PER_VERTEX;
        final int numVertices = sizeOfCircleInVertices(numPoints);

        vertexData[offset++] = circle.center.x;
        vertexData[offset++] = circle.center.y;
        vertexData[offset++] = circle.center.z;

        for (int i = 0; i < numPoints; i++) {
            float angleInRadians = (float) i / (float) numPoints * ((float) Math.PI * 2f);
            vertexData[offset++] = (float) (circle.center.x + circle.radius * Math.cos(angleInRadians));
            vertexData[offset++] = circle.center.y;
            vertexData[offset++] = (float) (circle.center.z + circle.radius * Math.sin(angleInRadians));
        }

        drawList.add(new DrawCommand() {
            @Override
            public void draw() {
                GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, startVertex, numVertices);
            }
        });
    }


    private void appendOpenCylinder(Geometry.Cylinder cylinder, int numPoints) {
        final int startVertex = offset / FLOAT_PER_VERTEX;
        final int numVertices = sizeOfOpenCylinderInVertices(numPoints);

        final float yStart = cylinder.center.y - (cylinder.height / 2f);
        final float yEnd = cylinder.center.y + (cylinder.height / 2f);


        for (int i = 0; i < numPoints; i++) {
            float angleInRadians = (float) i / (float) numPoints * ((float) Math.PI * 2f);
            float xPosition = (float) (cylinder.center.x + cylinder.radius * Math.cos(angleInRadians));
            float zPosition = (float) (cylinder.center.z + cylinder.radius * Math.sin(angleInRadians));
            vertexData[offset++] = xPosition;
            vertexData[offset++] = yStart;
            vertexData[offset++] = zPosition;

            vertexData[offset++] = xPosition;
            vertexData[offset++] = yEnd;
            vertexData[offset++] = zPosition;
        }

        drawList.add(new DrawCommand() {
            @Override
            public void draw() {
                GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, startVertex, numVertices);
            }
        });
    }


    interface DrawCommand {
        void draw();
    }


    public static class GenerateData {
        final float[] vertexData;
        final List<DrawCommand> drawList;

        public GenerateData(float[] vertexData, List<DrawCommand> drawList) {
            this.vertexData = vertexData;
            this.drawList = drawList;
        }
    }

    public GenerateData build() {
        return new GenerateData(vertexData, drawList);
    }

}
