package ru.maklas.bodymaker.runtime.save_beans;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class NamedPoint extends Vector2 implements Json.Serializable{

    private String name;

    public NamedPoint(String name) {
        this.name = name;
    }

    public NamedPoint(String name, float x, float y) {
        super(x, y);
        this.name = name;
    }

    public NamedPoint() {

    }

    public NamedPoint copyAndRevert(){
        return new NamedPoint(name, -x, -y);
    }

    public void setName(String name) {
        this.name = name;
    }

    public NamedPoint(NamedPoint point) {
        this(point.name, point.x, point.y);
    }

    @Override
    public void write(Json json) {
        json.writeValue("name", name, String.class);
        json.writeValue("x", x, float.class);
        json.writeValue("y", y, float.class);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        name = jsonData.getString("name");
        x = jsonData.get("x").asFloat();
        y = jsonData.get("y").asFloat();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
