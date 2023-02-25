package lesson_1.task_3;

public class Main {
    public static void main(String[] args) {

        Figure figure1 = new Square(2);
        System.out.println(figure1.getSquare());

        Figure figure2 = new Triangle(3, 4, 5);
        System.out.println(figure2.getSquare());

        Figure figure3 = new Circle(4);
        System.out.println(figure3.getSquare());

    }
}

abstract class Figure {
    public abstract double getSquare();
}

class Square extends Figure {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public double getSquare() {
        return Math.pow(side, 2);
    }
}

class Triangle extends Figure {
    private int sideA;
    private int sideB;
    private int sideC;

    public Triangle(int sideA, int sideB, int sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public double getSquare() {
        double p = (sideA + sideB + sideC) / 2.0;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }
}

class Circle extends Figure {
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double getSquare() {
        return Math.PI * (2 * radius);
    }
}