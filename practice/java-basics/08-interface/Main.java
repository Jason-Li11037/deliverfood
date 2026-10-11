import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Connection> devices = List.of(new Computer(), new Fridge(), new Dishwasher());
        for (Connection device : devices) {
            device.send("hello");
        }
    }
}
