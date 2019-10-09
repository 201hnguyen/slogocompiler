# Collections_API

### Q. In your experience using these collections, are they hard or easy to use?
They are quite easy to use, as the name of the methods in the API are straightforward, and it is easy to see what each method is going to do. We do not have to worry specifically about how concrete collection classes implemented these methods.

### Q. In your experience using these collections, do you feel mistakes are easy to avoid?
As long as we know the data structure and algorithms of the concrete colleciont classes, mistakes are easy to avoid.

### Q. How many interfaces do specific concrete collection classes implement (such as LinkedList)? What do you think is the purpose of each interface?

LinkedList implements seven interfaces : List, Deque, Cloneable, and Serializable are implemented specifically by LinkedList, and Iterable, Collection, Queue are implemented from the parent of LinkedList.

- List : LinkedList implements the behaviours of a list.
- Deque : To support the insertion of elements at both ends.
- Cloneable : To enable LinkedList
- Serializable : Classes that implement Serializable can have their state 'serialized', which is an important trait of LinkedList.
- Iterable : To allow the object to be a target of 'for-loop' statement
- Collection : To implement the basic properties of a Collection.
- Queue : To provide additional insertion, extraction, and inspection operations besides the ones from Collection.

### Q. How many different implementations are there for a specific collection class (such as Set)? Do you think the number justifies it being an interface or not?

It seems that there are 3 specific collections for Set: HashSet, TreeSet, LinkedSet.

We think that this number justifies it being an interface because they all use the same method, but the actual implementation is slighlty different.


### Q. How many levels of superclasses do specific concrete collection classes have? What do you think is the purpose of each inheritance level?

Linkedlist has four levels of superclasses.

- The highest superclass is the Object class. Every single class used in Java extends the Object class.
- The next highest superclass is the AbstractCollection class, which provides a skeletal implementation of the Collection interface. This class has five direct subclasses, which are the different data structures in collection.
- The next superclass is the AbstractList class, which provides skeletal implementation of the List class. 
- The last superclass is the AbstractSequentialList class, which is differnet from AbstractList because it implements random access methods on top of the iterator. This class's direct subclass is the LinkedList.

### Q. Why does it make sense to have the utility classes instead of adding that functionality to the collection types themselves? Are there any overlapping methods (ones that are in both a specific collection and a utility class)? If so, is there any guidance on which one you should use?


A utility class is essentially an interface. Considering this, it makes snsed to have it instead of adding that functionaly to the collection types themseleves because 

... 
