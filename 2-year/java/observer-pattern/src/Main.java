import java.util.ArrayList;

interface Observable {
    void listen(Object data);
}

class Observer {
    ArrayList<Observable> observables;

    public Observer() {
        this.observables = new ArrayList<Observable>();
    }

    public void add(Observable observable) {
        this.observables.add(observable);
    }

    public void remove(Observable observable) {
        var filteredObservables = new ArrayList<Observable>();

        for (var stored : this.observables) {
            if (stored == observable) {
                continue;
            }

            filteredObservables.add(stored);
        }

        this.observables = filteredObservables;
    }

    public void notify(Object data) {
        for (var observable : this.observables) {
            observable.listen(data);
        }
    }
}

class Item implements Observable {
    @Override
    public void listen(Object data) {
        System.out.println(data);
    }
}

public class Main {
    public static void main(String[] args) {
        var observer = new Observer();
        var item = new Item();

        observer.add(item);
        observer.notify("Hello World");
        observer.remove(item);
    }
}