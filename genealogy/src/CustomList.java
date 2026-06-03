import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
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
        return new Stream<>() {
            @Override
            public Stream<T> filter(Predicate<? super T> predicate) {
                return Stream.empty();
            }

            @Override
            public <R> Stream<R> map(Function<? super T, ? extends R> function) {
                return Stream.empty();
            }

            @Override
            public IntStream mapToInt(ToIntFunction<? super T> toIntFunction) {
                return IntStream.empty();
            }

            @Override
            public LongStream mapToLong(ToLongFunction<? super T> toLongFunction) {
                return LongStream.empty();
            }

            @Override
            public DoubleStream mapToDouble(ToDoubleFunction<? super T> toDoubleFunction) {
                return DoubleStream.empty();
            }

            @Override
            public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> function) {
                return Stream.empty();
            }

            @Override
            public IntStream flatMapToInt(Function<? super T, ? extends IntStream> function) {
                return IntStream.empty();
            }

            @Override
            public LongStream flatMapToLong(Function<? super T, ? extends LongStream> function) {
                return LongStream.empty();
            }

            @Override
            public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> function) {
                return DoubleStream.empty();
            }

            @Override
            public Stream<T> distinct() {
                return Stream.empty();
            }

            @Override
            public Stream<T> sorted() {
                return Stream.empty();
            }

            @Override
            public Stream<T> sorted(Comparator<? super T> comparator) {
                return Stream.empty();
            }

            @Override
            public Stream<T> peek(Consumer<? super T> consumer) {
                return Stream.empty();
            }

            @Override
            public Stream<T> limit(long l) {
                return Stream.empty();
            }

            @Override
            public Stream<T> skip(long l) {
                return Stream.empty();
            }

            @Override
            public void forEach(Consumer<? super T> consumer) {

            }

            @Override
            public void forEachOrdered(Consumer<? super T> consumer) {

            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <A> A[] toArray(IntFunction<A[]> intFunction) {
                return null;
            }

            @Override
            public T reduce(T t, BinaryOperator<T> binaryOperator) {
                return null;
            }

            @Override
            public Optional<T> reduce(BinaryOperator<T> binaryOperator) {
                return Optional.empty();
            }

            @Override
            public <U> U reduce(U u, BiFunction<U, ? super T, U> biFunction, BinaryOperator<U> binaryOperator) {
                return null;
            }

            @Override
            public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> biConsumer, BiConsumer<R, R> biConsumer1) {
                return null;
            }

            @Override
            public <R, A> R collect(Collector<? super T, A, R> collector) {
                return null;
            }

            @Override
            public Optional<T> min(Comparator<? super T> comparator) {
                return Optional.empty();
            }

            @Override
            public Optional<T> max(Comparator<? super T> comparator) {
                return Optional.empty();
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public boolean anyMatch(Predicate<? super T> predicate) {
                return false;
            }

            @Override
            public boolean allMatch(Predicate<? super T> predicate) {
                return false;
            }

            @Override
            public boolean noneMatch(Predicate<? super T> predicate) {
                return false;
            }

            @Override
            public Optional<T> findFirst() {
                return Optional.empty();
            }

            @Override
            public Optional<T> findAny() {
                return Optional.empty();
            }

            @Override
            public Iterator<T> iterator() {
                return null;
            }

            @Override
            public Spliterator<T> spliterator() {
                return null;
            }

            @Override
            public boolean isParallel() {
                return false;
            }

            @Override
            public Stream<T> sequential() {
                return Stream.empty();
            }

            @Override
            public Stream<T> parallel() {
                return Stream.empty();
            }

            @Override
            public Stream<T> unordered() {
                return Stream.empty();
            }

            @Override
            public Stream<T> onClose(Runnable runnable) {
                return Stream.empty();
            }

            @Override
            public void close() {

            }
        };
    }

    private class Node {
        public Object previous; //For whatever damned reason if I try using Node here the code ealier will refuze to detect this class
        public Object next;
        public T value;
    }
}
