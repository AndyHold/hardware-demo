package co.nz.hardwarestore.quickpractical.model;

/**
 * A simple class to store a key value pair.
 *
 * @param <K> key.
 * @param <V> value.
 * @author Andrew Holden
 */
public record Tuple<K, V> (K key, V value) {}
