# Classes of collections

| Class         | Ordering | Random Access | Key-Value Pairs | Allow Duplicates | Allow Null Values | Thread Safe | Blocking Operations |
|:--------------|:--------:|:-------------:|:---------------:|:----------------:|:-----------------:|:-----------:|:-------------------:|
| ArrayList     | +        | +             | -               | +                | +                 | -           | -                   |
| LinkedList    | +        | -             | -               | +                | +                 | -           | -                   |
| Vector        | +        | +             | -               | +                | +                 | +           | +                   |
| HashSet       | -        | -             | -               | -                | +                 | -           | -                   |
| LinkedHashSet | +        | -             | -               | -                | +                 | -           | -                   |
| TreeSet       | +        | -             | -               | -                | -                 | -           | -                   |
| HashMap       | -        | +             | +               | -                | +                 | -           | -                   |
| LinkedHashMap | +        | +             | +               | -                | +                 | -           | -                   |
| TreeMap       | +        | +             | +               | -                | -                 | -           | -                   |
| Hashtable     | -        | +             | +               | -                | -                 | +           | -                   |
| ArrayDeque    | +        | -             | -               | +                | -                 | -           | -                   |
| PriorityQueue | +        | -             | -               | +                | -                 | -           | -                   |
