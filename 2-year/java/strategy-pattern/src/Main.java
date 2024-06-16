
interface IBrakeBehavior {
    void brake();
}

class BrakeWithAbs  implements IBrakeBehavior {
    @Override
    public void brake() {
        System.out.println("breaked");
    }
}

class CommonBrake implements IBrakeBehavior {
    @Override
    public void brake() {
        System.out.println("breaked");
    }
}

class Car {
    private IBrakeBehavior brakeBehavior;

    public Car(IBrakeBehavior brakeBehavior) {
        this.brakeBehavior = brakeBehavior;
    }

    public void setBrakeBehavior(IBrakeBehavior brakeBehavior) {
        this.brakeBehavior = brakeBehavior;
    }
}

public class Main {
    public static void main(String[] args) {
        IBrakeBehavior brakeBehavior = new BrakeWithAbs();

        Car car = new Car(brakeBehavior);
    }
}