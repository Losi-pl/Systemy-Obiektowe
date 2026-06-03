import java.util.*;
import java.util.function.*;
import java.util.stream.*;

@SuppressWarnings({"unused", "unchecked"})
public abstract class CustomList<T> {
    private Node first = null, last = null;

    public void addLast(T value) {
        var wrap = new Node();
        wrap.previous = last;
        last.next = wrap;
        wrap.value = value;
        last = wrap;
        if(first == null)
            first = wrap;
    }
    public void addFirst(T value) {
        var wrap = new Node();
        wrap.value = value;
        wrap.next = first;
        first = wrap;
        if(last == null)
            last = wrap;
    }
    public boolean add(T value)
    {
        try {
            addLast(value);
            return true;
        }
        catch (Exception ignored)
        {
            return false;
        }
    }

    public T getFirst() {
        return first.value;
    }
    public T getLast() {
        return first.value;
    }

    public T get(int index)
    {
        try {
            Node n = first;
            for(int i = 0; i < index; i++)
                n = (Node) n.next;
            return n.value;
        }
        catch (NullPointerException ignored)
        { throw new IndexOutOfBoundsException(); }
    }

    public boolean removeLast() {
        var l = last;
        if(first == last)
        {
            first = null;
            last = null;
            return l != null;
        }
        if(l == null)
            return false;
        last = (Node)l.previous;
        ((Node) l.previous).next = null;
        return true;
    }

    public boolean removeFirst() {
        var f = first;
        if(first == last)
        {
            first = null;
            last = null;
            return  f != null;
        }
        if(f == null)
            return false;
        first = (Node)f.next;
        ((Node) f.next).previous = null;
        return true;
    }

    public int size() {
        var c = first;
        var count = c != null ? 1 : 0;
        while (c != null)
        {
            c = (Node)c.next;
            count++;
        }
        return count;
    }

    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node c = first;

            @Override
            public boolean hasNext() {
                return c != null;
            }

            @Override
            public T next() {
                var v = c.value;
                c = (Node)c.next;
                return v;
            }
        };
    }

    public Stream<T> stream() {
        var list = new ArrayList<T>();
        {
            var c = first;
            while (c != null)
            {
                list.add(c.value);
                c = (Node)c.next;
            }
        }
        return list.stream();
    }

    private class Node {
        public Object previous; //For whatever damned reason if I try using Node here the code ealier will refuze to detect this class
        public Object next;
        public T value;
    }
}
