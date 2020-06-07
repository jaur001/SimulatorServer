package backend.server.searcher.comparators;


public abstract class DoubleSearchComparator implements SearchComparator<Double> {
    @Override
    public boolean contains(Double object1, Double object2) {
        return isNearNumber(object1,object2) || isNearNumber(object2,object1);
    }

    protected boolean isNearNumber(Double double1, Double double2){
        return double1 >= double2 && double1 <= double2+getInterval();
    }

    protected boolean checkSearch(Double double1, String searchText) {
        try{
            Double double2 = Double.parseDouble(searchText);
            return contains(double1,double2);
        }catch (Exception e){
            return false;
        }
    }

    protected abstract Double getInterval();
}
