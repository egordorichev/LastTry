package org.egordorichev.lasttry.util;

/**
 * Generic container class modelled after:
 * http://www.oracle.com/technetwork/articles/java/juneau-generics-2255374.html
 *
 * Created by logotie on 13/04/2017.
 */
public class GenericContainer<T> {

        private T t;

        public void set(T t) {
            this.t = t;
        }

        public T get() {
            return t;
        }
}
