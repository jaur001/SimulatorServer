package backend.model.simulables.person.client.routineList.routine;

public class Counter {

    private int count;
    private int nextVisitDays;

    public Counter(int nextVisitDays) {
        this.count = 0;
        this.nextVisitDays = nextVisitDays;
    }

    public boolean countDown(){
        if(++count != nextVisitDays) return false;
        count = 0;
        return true;
    }

    public int getCount() {
        return nextVisitDays-count;
    }
}
