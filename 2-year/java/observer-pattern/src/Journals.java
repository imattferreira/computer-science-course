import java.util.ArrayList;

interface JournalSubscriber {
    void listen(Object data);
}

class Journal {
    ArrayList<JournalSubscriber> subscribers;

    public Journal() {
        this.subscribers = new ArrayList<JournalSubscriber>();
    }

    public void subscribe(JournalSubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void unsubscribe(JournalSubscriber subscriber) {
        var filteredSubscribers = new ArrayList<JournalSubscriber>();

        for (var stored : this.subscribers) {
            if (stored == subscriber) {
                continue;
            }

            filteredSubscribers.add(stored);
        }

        this.subscribers = filteredSubscribers;
    }

    public void notify(Object data) {
        for (var subscriber : this.subscribers) {
            subscriber.listen(data);
        }
    }
}

class People implements JournalSubscriber {
    @Override
    public void listen(Object data) {
        System.out.println("People -> " + data);
    }
}

class University implements JournalSubscriber {
    @Override
    public void listen(Object data) {
        System.out.println("University -> " + data);
    }
}


public class Journals {
    public static void main(String[] args) {
        var journal = new Journal();
        var people = new People();
        var university = new University();

        journal.subscribe(people);
        journal.subscribe(people);
        journal.subscribe(university);

        System.out.println("------------------------------");
        journal.notify("Journal | 10.16.2023");
        System.out.println("------------------------------");
        journal.notify("Journal | 10.17.2023");
        System.out.println("------------------------------");
        journal.notify("Journal | 10.18.2023");
        System.out.println("------------------------------");

        journal.unsubscribe(university);
        journal.unsubscribe(people);
    }
}