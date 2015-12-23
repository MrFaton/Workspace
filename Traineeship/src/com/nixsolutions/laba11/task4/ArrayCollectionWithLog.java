package com.nixsolutions.laba11.task4;

import com.nixsolutions.laba5.task2.ArrayCollectionImpl;
import interfaces.logging.LoggingArrayCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Ponarin Igor
 * @since 20.12.2015
 */
public class ArrayCollectionWithLog<E> extends ArrayCollectionImpl<E>
        implements LoggingArrayCollection<E> {
    private static final Logger logger = LoggerFactory
            .getLogger(ArrayCollectionWithLog.class);

    @Override
    public org.slf4j.Logger getLogger() {
        logger.trace("call getLogger()");
        try {
            return logger;
        } catch (Exception e) {
            logger.error("Exception in getLogger()", e);
            throw e;
        }
    }

    @Override
    public int size() {
        logger.trace("call size()");
        try {
            return super.size();
        } catch (Exception ex) {
            logger.error("Exception in size()", ex);
            throw ex;
        }
    }

    @Override
    public boolean isEmpty() {
        logger.trace("call isEmpty()");
        try {
            return super.isEmpty();
        } catch (Exception e) {
            logger.error("Exception in isEmpty()", e);
            throw e;
        }
    }

    @Override
    public boolean contains(Object o) {
        logger.trace("call contains(Object o), o=" + o);
        try {
            return super.contains(o);
        } catch (Exception e) {
            logger.error("Exception in contains(Object o)", e);
            throw e;
        }
    }

    @Override
    public Iterator<E> iterator() {
        logger.trace("call iterator()");
        try {
            return super.iterator();
        } catch (Exception e) {
            logger.error("Exception in iterator()", e);
            throw e;
        }
    }

    @Override
    public Object[] toArray() {
        logger.trace("call toArray()");
        try {
            return super.toArray();
        } catch (Exception e) {
            logger.error("Exception in toArray()", e);
            throw e;
        }
    }

    @Override
    public <T> T[] toArray(T[] a) {
        logger.trace("call toArray(T[] a), a=" + a);
        try {
            return super.toArray(a);
        } catch (Exception e) {
            logger.error("Exception in toArray(T[] a)", e);
            throw e;
        }
    }

    @Override
    public boolean add(E e) {
        logger.trace("call add(E e), e=" + e);
        try {
            return super.add(e);
        } catch (Exception ex) {
            logger.error("Exception in add(E e)", ex);
            throw ex;
        }
    }

    @Override
    public boolean remove(Object o) {
        logger.trace("call remove(Object o), o=" + o);
        try {
            return super.remove(o);
        } catch (Exception e) {
            logger.error("Exception in remove(Object o)", e);
            throw e;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        logger.trace("call containsAll(Collection<?> c), c=" + c);
        try {
            return super.containsAll(c);
        } catch (Exception e) {
            logger.error("Exception in containsAll(Collection<?> c)", e);
            throw e;
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        logger.trace("call addAll(Collection<? extends E> c), c=" + c);
        try {
            return super.addAll(c);
        } catch (Exception e) {
            logger.error("Exception in addAll(Collection<? extends E> c)", e);
            throw e;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        logger.trace("call removeAll(Collection<?> c), c=" + c);
        try {
            return super.removeAll(c);
        } catch (Exception e) {
            logger.error("Exception in removeAll(Collection<?> c)", e);
            throw e;
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        logger.trace("call retainAll(Collection<?> c), c=" + c);
        try {
            return super.retainAll(c);
        } catch (Exception e) {
            logger.error("Exception in retainAll(Collection<?> c)", e);
            throw e;
        }
    }

    @Override
    public void clear() {
        logger.trace("call clear()");
        try {
            super.clear();
        } catch (Exception e) {
            logger.error("Exception in clear()", e);
            throw e;
        }
    }

    @Override
    public Object[] getArray() {
        logger.trace("call getArray()");
        try {
            return super.getArray();
        } catch (Exception e) {
            logger.error("Exception in getArray()", e);
            throw e;
        }
    }

    @Override
    public void setArray(E[] arg0) {
        logger.trace("call setArray(E[] arg0), arg0=" + arg0);
        try {
            super.setArray(arg0);
        } catch (Exception e) {
            logger.error("Exception in setArray(E[] arg0)", e);
            throw e;
        }
    }
}
