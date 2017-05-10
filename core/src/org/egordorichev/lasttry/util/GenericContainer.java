package org.egordorichev.lasttry.util;

/**
 * Generic container class modelled after:
 * http://www.oracle.com/technetwork/articles/java/juneau-generics-2255374.html
 *
 * Created by logotie on 13/04/2017.
 */
//TODO Refactor
public class GenericContainer<T> {

        private T t;

        public void set(T t) {
            this.t = t;
        }

        public T get() {
            return t;
        }

    public static class Pair<T>{

            private T a, b;

            public void set(T a, T b){
                this.a = a;
                this.b = b;
            }

            public T getFirst(){return a;}

            public T getSecond(){return b;}
    }

    public static class UniqueTypePair<T, U>{

            private T value1; private U value2;

            UniqueTypePair(T first, U second){
                this.value1 = first;
                this.value2 = second;
            }

        public T getValue1() {
            return this.value1;
        }

        public U getValue2() {
            return this.value2;
        }

        public void setValue1(T value1) {
            this.value1 = value1;
        }

        public void setValue2(U value2) {
            this.value2 = value2;
        }
    }

    public static class Triple<T, U>{

            private T a, b;
            private U c;

            public void set(T a, T b, U c){
                this.a = a;
                this.b = b;
                this.c = c;
            }

            public T getX(){return a;}
            public T getY(){return b;}
            public U getIDent(){return c;}
    }
}
