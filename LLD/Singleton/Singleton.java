package LLD.Singleton;

import java.io.Serializable;

/**
 * Thread-safe Singleton with protection against reflection, cloning, and deserialization.
 * Each mechanism below exists because there is more than one way to create a second instance.
 */
public class Singleton implements Serializable {

    // volatile ensures writes to `instance` are visible across threads after double-checked locking.
    // Without it, another thread could see a partially constructed or stale reference.
    private volatile static Singleton instance;

    // Private constructor blocks `new Singleton()` from outside this class — the core Singleton rule.
    private Singleton() {
        // Guards against reflection or a second call to the constructor after an instance exists.
        if (instance != null)
            throw new IllegalStateException("Object Already Created");
    }

    // Double-checked locking: cheap null check outside the lock, synchronized block only on first creation.
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                // Re-check inside the lock — another thread may have created the instance while we waited.
                if (instance == null) instance = new Singleton();
            }
        }
        return instance;
    }

    // Prevents `clone()` from producing a second instance (Object.clone is otherwise accessible via reflection).
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();

        // Prototype pattern: create a new object by copying an existing one instead of calling `new`.
        // `super.clone()` would return a shallow copy — primitive fields are duplicated, but reference
        // fields in the clone still point to the same objects as the original (shared mutable state).
        // We do not use it here because Singleton must never have a second instance.
//        return super.clone();
    }

    // When this class is deserialized, Java would normally create a new object via readObject.
    // readResolve() replaces that new object with the existing singleton instance.
    protected Object readResolve() {
        return instance;
    }
}
