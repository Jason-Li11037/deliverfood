import java.util.List;

interface Animal {
    void say();
}

class Dog implements Animal {
    @Override
    public void say() {
        System.out.println("I'm a dog");
    }
}

abstract class Cat implements Animal {
    @Override
    public void say() {
        System.out.println("I'm a " + this.getColor() + " cat");
    }

    abstract String getColor();
}

class WhiteCat extends Cat {
    @Override
    String getColor() {
        return "White";
    }

}

class BlackCat extends Cat {
    @Override
    String getColor() {
        return "Black";
    }

}

public class Main {
    static void foo(List<Animal> animals) {
        for (Animal animal : animals) {
            animal.say();
        }
    }

    static void bar(List<Cat> cats) {
        for (Cat cat : cats) {
            System.out.println(cat.getColor());
        }
    }

    public static void main(String[] args) {
        Animal animal = new Dog();
        Cat cat1 = new WhiteCat();
        Cat cat2 = new BlackCat();
        foo(List.of(animal, cat1, cat2));
        bar(List.of(cat1, cat2));
    }
}
