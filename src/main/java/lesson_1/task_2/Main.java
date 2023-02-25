package lesson_1.task_2;

public class Main {
    public static void main(String[] args) {
    }
}

interface Moveable {
    void move();
}

interface Stopable {
    void stop();
}

abstract class Car {

    //todo нарушается приницп SOLID. Изменить модификатор доступа на private
    public Engine engine;
    private String color;
    private String name;

    //todo вынести в отдельный интерфейс Startable
    protected void start() {
        System.out.println("Car starting");
    }

    //todo вынести в отдельный интерфейс Openable
    abstract void open();

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

//todo добавить имплементацию интерфейса Openable
class LightWeightCar extends Car implements Moveable {

    @Override
        //todo изменить модификатор доступа на public
    void open() {
        System.out.println("Car is open");
    }

    @Override
    public void move() {
        System.out.println("Car is moving");
    }
}

//todo поменять наследование на имеплементацию интерфейсов Moveable м Stopable. Добавить анотацию @Override
class Lorry extends Car, Moveable, Stopable {
    public void move() {
        System.out.println("Car is moving");
    }

    public void stop() {
        System.out.println("Car is stop");
    }
}
