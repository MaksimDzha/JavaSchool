import java.util.*;


public class MyCountMap<E> implements CountMap<E> {
    private Map<Object, Integer> elementData;

    public MyCountMap() {
        this.elementData = new HashMap<>();
    }

    @Override
    public void add(Object o) {
        if (o != null) {
            Integer freq = elementData.get(o);
            elementData.put(o, freq == null ? 1 : freq + 1);
        }
    }

    @Override
    public int getCount(Object o) {
        if (elementData.containsKey(o)) return (int) elementData.get(o);
        return 0;
    }

    @Override
    public int remove(Object o) {
        int count = getCount(o);
        this.elementData.remove(o);
        return count;
    }

    @Override
    public int size() {
        return this.elementData.size();
    }

    @Override
    public void addAll(CountMap source) {
        int mapSize = source.size();
        if (this.elementData.size() == 0 && mapSize != 0) {
            for (Object o : source.keySet()) elementData.put(o, source.getCount(o));
            return;
        }
        for (Object o : source.keySet()) {
            if (elementData.containsKey(o)) elementData.put(o, this.getCount(o) + source.getCount(o));
            else this.add(o);
        }
    }

    @Override
    public Map toMap() {
        Map returnMap = new TreeMap();
        returnMap.putAll(elementData);
        return returnMap;
    }

    @Override
    public void toMap(Map destination) {
        destination.clear();
        destination.putAll(elementData);
    }

    @Override
    public Set<Object> keySet() {
        return elementData.keySet();
    }
}
