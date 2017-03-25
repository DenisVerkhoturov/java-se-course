# Set

## Basic functionality
A collection that contains no duplicate elements. More formally, sets contain no pair of elements `e1` and `e2`
such that `e1.equals(e2)`, and at most one `null` element. As implied by its name, this interface models
the mathematical set abstraction.

## Examples of typical use
Used for arrays which important to keep no duplicates.
For example, set of students of a group or set of cars on a parking.

---

# List

## Basic functionality
An ordered collection (also known as a sequence). The user of this interface has precise control over where in the list
each element is inserted. The user can access elements by their integer index (position in the list), and search for
elements in the list. Unlike `Set`, `List` typically allow duplicate elements.

## Examples of typical use
Most cases where you just need to store or iterate through a "bunch of things" and later iterate through them.

---

# Queue

## Basic functionality
A collection designed for holding elements prior to processing. Besides basic `Collection` operations, queues provide
additional insertion, extraction, and inspection operations. Each of these methods exists in two forms: one throws an
exception if the operation fails, the other returns a special value (either null or false, depending on the operation).
The latter form of the insert operation is designed specifically for use with capacity-restricted `Queue`
implementations; in most implementations, insert operations cannot fail.

## Examples of typical use
Used for managing tasks or data batch processing.
For example, pool of requests.

---

# Map

## Basic functionality
An object that maps keys to values. A map cannot contain duplicate keys; each key can map to at most one value.

## Examples of typical use
Used in cases where you need to associate some data in pairs where key is unique and value is not.
For example, dictionary or properties.
