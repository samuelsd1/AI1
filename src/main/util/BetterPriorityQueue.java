package main.util;


import java.util.PriorityQueue;

public class BetterPriorityQueue<E> {
    private PriorityQueue<QueueElement<E>> queue;

    public BetterPriorityQueue() {
        this.queue = new PriorityQueue<>((e1, e2) -> (int) (e1.getPriority() - e2.getPriority()));
    }

    public boolean put(E elem, double priority) {
        QueueElement<E> queueElem = new QueueElement<>(elem, priority);
        return this.queue.add(queueElem);
    }

    public E get() {
        QueueElement<E> queueElem = this.queue.poll();
        return queueElem.getObj();
    }

    public boolean contains(E elem) {
        QueueElement<E> queueElem = new QueueElement<>(elem, 0);
        return this.queue.contains(queueElem);
    }

    public boolean isEmpty(){
        return this.queue.isEmpty();
    }

    private class QueueElement<E> {
        private E obj;
        private double priority;

        public QueueElement(E obj, double priority) {
            this.obj = obj;
            this.priority = priority;
        }

        public E getObj() {
            return this.obj;
        }

        public double getPriority() {
            return this.priority;
        }

        @Override
        public int hashCode() {
            return this.obj.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            return this.obj.equals(other);
        }
    }
}
