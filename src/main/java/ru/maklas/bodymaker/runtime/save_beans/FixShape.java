package ru.maklas.bodymaker.runtime.save_beans;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class FixShape implements Json.Serializable{

    private String name;
    private Array<Vector2> points;
    private float density;
    private float restitution;
    private float friction;
    private boolean sensor;

    public FixShape() {
        points = new Array<Vector2>();
    }


    //***********//
    //* SETTERS *//
    //***********//


    public void setName(String name) {
        this.name = name;
    }

    public void addPoint(Vector2 vec){
        points.add(new Vector2(vec));
    }

    public FixShape mov(float x, float y){
        for (Vector2 point : points) {
            point.add(x, y);
        }
        return this;
    }

    public FixShape scl(float scalar){
        for (Vector2 point : points) {
            point.scl(scalar);
        }
        return this;
    }

    public PolygonShape toPolygonShape(){
        final PolygonShape shape = new PolygonShape();
        shape.set((Vector2[]) points.toArray(Vector2.class));
        return shape;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{name='" + name + '\'' +
                ", points=" + points +
                '}';
    }

    @Override
    public void write(Json json) {
        json.writeValue("name", name, String.class);
        json.writeValue("density", density, Float.class);
        json.writeValue("restitution", restitution, Float.class);
        json.writeValue("friction", friction, Float.class);
        json.writeValue("sensor", sensor, Boolean.class);
        json.writeArrayStart("points");
        for (Vector2 point : points) {
            json.writeObjectStart();
            json.writeValue("x", point.x);
            json.writeValue("y", point.y);
            json.writeObjectEnd();
        }
        json.writeArrayEnd();
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        name = jsonData.get("name").asString();
        density = jsonData.get("density").asFloat();
        restitution = jsonData.get("restitution").asFloat();
        friction = jsonData.get("friction").asFloat();
        sensor = jsonData.get("sensor").asBoolean();
        final JsonValue points = jsonData.get("points");
        Array<Vector2> pointsArr = new Array<Vector2>();
        for (JsonValue entry = points.child; entry != null; entry = entry.next){
            pointsArr.add(new Vector2(entry.get("x").asFloat(), entry.get("y").asFloat()));
        }
        this.points = pointsArr;
    }

    public Array<Vector2> getPoints() {
        return points;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public void setRestitution(float restitution) {
        this.restitution = restitution;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public float getDensity() {
        return density;
    }

    public float getRestitution() {
        return restitution;
    }

    public float getFriction() {
        return friction;
    }

    public void setSensor(boolean sensor) {
        this.sensor = sensor;
    }

    public boolean isSensor() {
        return sensor;
    }
}
